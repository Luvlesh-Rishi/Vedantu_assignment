package com.assignment.Vedantu.Buisiness;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.Vedantu.Entity.Item;
import com.assignment.Vedantu.Entity.ItemOrdered;
import com.assignment.Vedantu.Entity.ProductOrder;
import com.assignment.Vedantu.HttpHandler.OrderRequest;
import com.assignment.Vedantu.HttpHandler.OrderResponse;
import com.assignment.Vedantu.Repository.InvRepo;
import com.assignment.Vedantu.Repository.ItemOrderedRepo;
import com.assignment.Vedantu.Repository.OrderRepo;

@Service
public class OrderProcessor {

	
	/* 
	 * The purpose of this class is to check the availability of the inventory for the requested items
	 * and update all the relevant tables accordingly.
	 * 
	 * Exception handling can be handled separately (TO DO)
	 * 
	 * An embedded H2 DB is used in this scope.
	 * 
	 * The DB interactions can be moved to the DAO layer
	 * 
	 * */
	
	
	
	@Autowired
	InvRepo invRepo;   // Inventory Repo with updated status of the items
	
	@Autowired
	ItemOrderedRepo itemOrderedRepo;	// Item Repo for maintaining each Item for an order Id
	
	@Autowired
	OrderRepo orderRepo;	// Order repo to track ongoing orders
	
	
	/*
	 * Process order according the order request
	 * 
	 * param: OrderRequest
	 * return: String (orderId)
	 */
	public OrderResponse processOrder(OrderRequest orderReq) {
		
		String userId = orderReq.getUserId();
		List<Item> orderedItems = orderReq.getItems();
		
		OrderResponse orderResp = null;
		
		// Check if the product is available
		if(!isAvailable(orderedItems)) {
			orderResp = new OrderResponse("Items are not avialable");
		}else {
			int orderId = createOrder(userId, orderedItems);
			// If OrderRepo fails
			if(orderId == 0) {
				orderResp = new OrderResponse("Order failed");
			// If Items are no longer available
			}else if (orderId == -1) {
				orderResp = new OrderResponse("Items are not avialable");
			}
			// Item placed successfully
			else {
				orderResp = new OrderResponse("Order placed", orderId+"");
			}
				
		}
		
		return orderResp;
	}
	
	
	/*
	 * Creating an order for requested items 
	 * 
	 * params: String (userid), List<Item> orderedItems
	 * return: int (orderid)
	 */
	private int createOrder(String userId, List<Item> orderedItems) {
		
		LocalDateTime dateAndTime = LocalDateTime.now();
		ProductOrder order = new ProductOrder();

		//Step 1: Place the order
		order.setUserid(userId);
		order.setPlaced(dateAndTime.toString());
		orderRepo.save(order);
		
		for(Item item: orderedItems) {
			
			//Step 2: For each item ordered update the Item Ordered table with count and orderId 
			ItemOrdered itemOrdered= new ItemOrdered(order.getId(), item.getId(), item.getCount());
			itemOrderedRepo.save(itemOrdered);
			
			//Step 3: Update Inventory
			Item invItem = invRepo.findById(item.getId()).orElse(new Item());
			if(invItem.getCount() != 0 && invItem.getCount() >= item.getCount()) {
				
				int newCount = invItem.getCount() - item.getCount();
				
				// Race Condition in case item count is no longer available as requested one
				// (TEST : newCount < 0 -> newCount < 1 : This will not let the inventory item count to 0
				if(newCount < 0) {
					itemOrderedRepo.delete(itemOrdered);
					stopAndRestoreInv(order.getId());
					order.setId(-1);
					break;
				}else {
					invItem.setCount(newCount);
					invRepo.save(invItem);
					
				}
				
			}
		}
		return order.getId();
	}
	
	/*
	 * Canceling the ongoing order and restoring inventory
	 * 
	 * params int (orderId)
	 * return
	 */
	
	private void stopAndRestoreInv(int orderId) {
		
		List<ItemOrdered> itemsOrdered = itemOrderedRepo.findByOrderid(orderId);
		for(ItemOrdered itemOrdered: itemsOrdered) {
			
			itemOrderedRepo.delete(itemOrdered);
			
			int itemId = itemOrdered.getItemid();
			int count = itemOrdered.getCount();
			
			Item invItem = invRepo.findById(itemId).orElse(null);
			if(invItem != null) {
				invItem.setCount(invItem.getCount()+count);
				invRepo.save(invItem);
			}
			
		}
		orderRepo.deleteById(orderId);

	}
	
	/*
	 * Availability check in the inventory for each item requested
	 * 
	 * params: List<Item> (orderedItems)
	 * return boolean
	 */
	private boolean isAvailable(List<Item> orderedItems) {
		
		boolean isAvailable = true;
		
		for(Item item: orderedItems) {

			Item temp = invRepo.findById(item.getId()).orElse(new Item());

			if(temp == null || temp.getCount() < item.getCount()) {
				isAvailable = false;
				break;
			}
		}
		
		return isAvailable;
	}
}
