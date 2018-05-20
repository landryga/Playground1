package com.vetClinic.goods;

public class Good {
	
	private int id;
	private String name;
	private int quantity;
	private float price;
	private int quantity_change;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity_change() {
		return quantity_change;
	}
	public void setQuantity_change(int quantity_change) {
		this.quantity_change = quantity_change;
	}
	
}
