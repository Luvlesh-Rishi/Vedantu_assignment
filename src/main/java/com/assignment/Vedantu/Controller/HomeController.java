package com.assignment.Vedantu.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.Vedantu.HttpHandler.OrderRequest;
import com.assignment.Vedantu.HttpHandler.OrderResponse;
import com.assignment.Vedantu.Repository.AccountRepo;
import com.assignment.Vedantu.Buisiness.OrderProcessor;

@RestController
public class HomeController {

	/*
	 * Base controller for providing Api for order creation
	 * 
	 * API - > /createOrder POST
	 * accept content type:- JSON Object 
	 * RequestBody -> userid, list of items
	 * ResponseBody-> orderStatus, orderid
	 */
	
	@Autowired
	AccountRepo accRepo;	// Account repo for account related transacts.
	
	@Autowired
	OrderProcessor orderProcessor;	// Service for processing orders
	
	
	/*
	 * Basic authentication on user id
	 * 
	 * param: String (userid)
	 * return boolean
	 * 
	 */
	public boolean auth(String id) {
		return accRepo.existsById(id);
		
	}

	/*
	 * Rest Api for creating orders
	 * 
	 * param: OrderRequest 
	 * return: OrderResponse
	 */
	@PostMapping("/createOrder")
	public OrderResponse create(@RequestBody OrderRequest order) {
		
		OrderResponse orderRes;
		
		String userID = order.getUserId();
		
		if(userID == null || userID.equals("") || !auth(userID)) {
			orderRes = new OrderResponse("Invalid User");
		}else {
			orderRes = orderProcessor.processOrder(order);
		}
			
		return orderRes;
	}
}
