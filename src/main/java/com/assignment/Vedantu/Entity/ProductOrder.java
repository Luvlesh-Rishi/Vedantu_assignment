package com.assignment.Vedantu.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductOrder{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String userid;
	private String placed;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPlaced() {
		return placed;
	}
	public void setPlaced(String placed) {
		this.placed = placed;
	}
	@Override
	public String toString() {
		return "ProductOrder [id=" + id + ", userid=" + userid + ", placed=" + placed + "]";
	}
	
	
	
}
