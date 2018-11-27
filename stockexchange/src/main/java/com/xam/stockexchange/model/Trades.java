package com.xam.stockexchange.model;

import java.math.BigDecimal;

/**
 * Trade model class
 * @author Pradeep
 * 
 */
public class Trades {

	private int sellOrderId;
	private int quantity;
	private BigDecimal price;
	private int buyOrderId;
	
	public Trades(int sellOrderId, int quantity, BigDecimal price,int buyOrderId) {
		super();
		this.sellOrderId = sellOrderId;
		this.quantity = quantity;
		this.price = price;
		this.buyOrderId = buyOrderId;
	}
	public int getSellOrderId() {
		return sellOrderId;
	}
	public void setSellOrderId(int sellOrderId) {
		this.sellOrderId = sellOrderId;
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
	public int getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(int buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	
}
