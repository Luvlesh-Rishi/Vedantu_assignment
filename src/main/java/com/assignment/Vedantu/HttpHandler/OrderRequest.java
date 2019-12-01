package com.assignment.Vedantu.HttpHandler;



import java.util.List;

import com.assignment.Vedantu.Entity.Item;

public class OrderRequest {

	private String userId;
	private List<Item> items;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "OrderRequest [userId=" + userId + ", items=" + items + "]";
	}
	
	
}
