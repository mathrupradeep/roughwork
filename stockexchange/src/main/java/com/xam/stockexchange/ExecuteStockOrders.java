package com.xam.stockexchange;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.xam.stockexchange.model.OrderBook;
import com.xam.stockexchange.model.Trades;
import com.xam.stockexchange.utils.BuyOrderBookComparator;
import com.xam.stockexchange.utils.SellOrderBookComparator;

/**
 * Sort Orders according to lowest sell price and highest buy price<br>
 * Execute Orders<br>
 * Prints Orders<br>
 * 
 * @author Pradeep
 */
public class ExecuteStockOrders {

	private List<OrderBook> buyOrders = new LinkedList<OrderBook>();
	private List<OrderBook> sellOrders = new LinkedList<OrderBook>();
	
	private List<Trades> tradesList = new LinkedList<Trades>();


	/**
	 * This method performs trade matching. It gets each buy order & matches
	 * with sell order
	 */
	
	public void performTrades() {
		buyOrders.sort(new BuyOrderBookComparator());
		
		for (OrderBook buyOrder : buyOrders) {

			List<OrderBook> filteredSellOrdersList = sellOrders
					.stream()
					.filter((sellStock) -> sellStock.getPrice().compareTo(buyOrder.getPrice()) == -1)
					.collect(Collectors.toList());

			filteredSellOrdersList.sort(new SellOrderBookComparator());

			for (OrderBook sellOrder : filteredSellOrdersList) {
				if (buyOrder.getQuantity() > 0) {

					int sellQuantity = sellOrder.getQuantity();
					int buyQuantity = buyOrder.getQuantity();

					if (buyQuantity >= sellQuantity) {
						Trades trade = new Trades(sellOrder.getOrderId(),
								sellQuantity, sellOrder.getPrice(),
								buyOrder.getOrderId());
						sellOrders.remove(sellOrder);
						tradesList.add(trade);
						buyOrder.setQuantity(buyQuantity - sellQuantity);
					} else {
						Trades trade = new Trades(sellOrder.getOrderId(),
								buyQuantity, sellOrder.getPrice(),
								buyOrder.getOrderId());
						tradesList.add(trade);
						sellOrders.set(
								sellOrders.indexOf(sellOrder),
								sellOrder.setNewQuantity(sellQuantity
										- buyQuantity));
						break;
					}
				} else {
					break;
				}
			}

		}
		printTrades();
	}

	/**
	 * Prints all the trades in below format <sell-order-id> <qty> <sell-price>
	 * <buy-order-id> For eg: #2 80 237.45 #3
	 */
	public void printTrades() {
		tradesList.stream().forEach(
				e -> System.out.println("#" + e.getSellOrderId() + " "
						+ e.getQuantity() + " " + e.getPrice() + " " + "#"
						+ e.getBuyOrderId()));
	}
	
	
	public List<OrderBook> getBuyOrders() {
		return buyOrders;
	}

	public void setBuyOrders(List<OrderBook> buyOrders) {
		this.buyOrders = buyOrders;
	}

	public List<OrderBook> getSellOrders() {
		return sellOrders;
	}

	public void setSellOrders(List<OrderBook> sellOrders) {
		this.sellOrders = sellOrders;
	}

	public List<Trades> getTradesList() {
		return tradesList;
	}

	public void setTradesList(List<Trades> tradesList) {
		this.tradesList = tradesList;
	}

}
