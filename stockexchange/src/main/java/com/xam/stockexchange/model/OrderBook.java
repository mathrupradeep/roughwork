package com.xam.stockexchange.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Order Model class
 * @author Pradeep
 * 
 */
public class OrderBook {

	private int OrderId;
	private Date orderTime;
	private String stockName;
	private String orderType;
	private int quantity;
	private BigDecimal price;
	
	public OrderBook(){
		
	}
	
	public OrderBook(int OrderId,Date orderTime,String stockName,String orderType,int quantity,BigDecimal price){
		this.OrderId = OrderId;
		this.orderTime = orderTime;
		this.stockName = stockName;
		this.orderType = orderType;
		this.quantity = quantity;
		this.price = price;
	}
	
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}	
	/*
	 * Builder Pattern
	 */
	public OrderBook setNewQuantity(int quantity){
		this.quantity = quantity;
		return this;
	}
	
}
