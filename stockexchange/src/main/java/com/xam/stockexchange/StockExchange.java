package com.xam.stockexchange;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.xam.stockexchange.model.OrderBook;

/**
 * StockExchange class is responsible for following operations:<br>
 * Collect Orders both buy & Sell<br>
 * Invokes ExecuteStockOrders class to do further process asynchronously 
 * 
 * @author Pradeep
 */
public class StockExchange {
	
	private List<OrderBook> buyOrders = new LinkedList<OrderBook>();
	private List<OrderBook> sellOrders = new LinkedList<OrderBook>();
	
	private Set<String> stockNameSet = new HashSet<String>();
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(2);
	
	public static void main(String args[]) throws IOException, NumberFormatException, ParseException{
		
		StockExchange stockExchange = new StockExchange();
		stockExchange.readInput();
		stockExchange.beingTrading();
		
	}
	/**
	 * Reads input from scanner and creates orders based on orderType
	 */
	@SuppressWarnings("resource")
	public void readInput() throws IOException,NumberFormatException, ParseException{
		
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat orderDateFormat = new SimpleDateFormat("HH:mm");
		String readLine = "";
		
		while (!(readLine = sc.nextLine()).isEmpty()) {
			String[] orderDetails = readLine.split(" ");
			
			OrderBook orderbook = new OrderBook(Integer.parseInt(orderDetails[0].replace("#", "")),orderDateFormat.parse(orderDetails[1]),orderDetails[2],orderDetails[3],Integer.parseInt(orderDetails[4]),new BigDecimal(orderDetails[5]));
			
			if (orderbook.getOrderType().equalsIgnoreCase("sell"))
				sellOrders.add(orderbook);
			else
				buyOrders.add(orderbook);
			
			stockNameSet.add(orderbook.getStockName());
		}
	}
	
	/**
	 * This method asynchronously calls the performTrades method in ExecuteStockOrders
	 * The trading happens asynchronously according to each stockName
	 */
	
	public void beingTrading(){
		for(String stockName:stockNameSet){
			ExecuteStockOrders executeStockOrders = new ExecuteStockOrders();
			executeStockOrders.setBuyOrders(buyOrders.stream().
					filter(buyorder-> buyorder.getStockName().equalsIgnoreCase(stockName)).
					collect(Collectors.toList()));
			executeStockOrders.setSellOrders(sellOrders.stream().
					filter(buyorder-> buyorder.getStockName().equalsIgnoreCase(stockName)).
					collect(Collectors.toList()));
			threadPool.submit(()->{
				executeStockOrders.performTrades();
			});
		}
	}
	
	
	public List<OrderBook> getBuyOrders() {
		return this.buyOrders;
	}
	public List<OrderBook> getSellOrders() {
		return this.sellOrders;
	}
	
	public Set<String> getStockNameSet() {
		return this.stockNameSet;
	}

}
