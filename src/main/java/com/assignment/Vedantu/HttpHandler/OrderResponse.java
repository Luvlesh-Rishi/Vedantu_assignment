package com.assignment.Vedantu.HttpHandler;

public class OrderResponse {
	
	private String orderStatus;
	private String orderId;
	
	public OrderResponse() {
		
	}
	
	public OrderResponse(String status) {
		orderStatus = status;
	}
	
	public OrderResponse(String status, String id) {
		orderStatus = status;
		orderId = id;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderResponse [orderStatus=" + orderStatus + ", orderId=" + orderId + "]";
	}


	
	

}
