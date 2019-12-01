package com.assignment.Vedantu.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	private int id;
	
	private String name;
	private int count;
	private String type;
	private String discreption;
	private String price;
	private String supplierId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDiscreption() {
		return discreption;
	}
	public void setDiscreption(String discreption) {
		this.discreption = discreption;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", count=" + count + ", type=" + type + ", discreption="
				+ discreption + ", price=" + price + ", supplierId=" + supplierId + "]";
	}

	
	
	
	
}
