package com.xam.stockexchange.utils;

import java.util.Comparator;

import com.xam.stockexchange.model.OrderBook;

/**
 * Comparator class helps sort all Buy orders with highest buy price. It follows stack model.First orders gets max priority
 * @author Pradeep
 */
public class BuyOrderBookComparator implements Comparator<OrderBook> {

	@Override
	public int compare(OrderBook orderBook1, OrderBook orderBook2) {
		int returnValue = 0;
		
		if (orderBook1.getStockName().equalsIgnoreCase(orderBook1.getStockName())) {
			
			if ((orderBook1.getOrderTime().compareTo(orderBook2.getOrderTime()) == 0)) {
				
				if(orderBook1.getPrice().compareTo(orderBook2.getPrice()) == 1){
					returnValue = -1;
				}
				else if(orderBook1.getPrice().compareTo(orderBook2.getPrice()) == -1){
					returnValue = 1;
				}
			}

			else{
				returnValue = orderBook1.getOrderTime().compareTo(orderBook2.getOrderTime());
			}
		}

		else if ((orderBook1.getOrderTime().compareTo(orderBook2.getOrderTime()) == 1)) {
			returnValue = -1;
		}

		return returnValue;
	}

}
