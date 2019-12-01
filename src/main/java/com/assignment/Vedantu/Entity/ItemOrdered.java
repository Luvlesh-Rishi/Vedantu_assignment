package com.assignment.Vedantu.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemOrdered {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int orderid;
	private int itemid;
	private int count;
	
	public ItemOrdered() {
		
	}
	
	public ItemOrdered(int orderid, int itemid, int count) {
		this.orderid = orderid;
		this.itemid = itemid;
		this.count = count;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderid;
	}
	public void setOrderId(int orderid) {
		this.orderid = orderid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Order_Item_Rel [id=" + orderid + ", itemid=" + itemid + ", count=" + count + "]";
	}
	
	
}
