package com.xam.test.stockexchange;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.xam.stockexchange.ExecuteStockOrders;
import com.xam.stockexchange.StockExchange;
import com.xam.stockexchange.model.Trades;

public class StockExchangeTest {
	
	private  StockExchange stockExchange= new StockExchange();
	
	@Test
	public void testStockExchange() throws NumberFormatException, IOException, ParseException {
	   
	    String input = "#1 09:45 XAM sell 100 240.10" + "\n" +
	    			   "#2 09:45 XAM sell 90 237.45" +  "\n" +
					   "#3 09:47 XAM buy 80 238.10" +   "\n" +
					   "#5 09:48 XAM sell 220 241.50" +  "\n" +
					   "#6 09:49 XAM buy 50 238.50" +   "\n" +
					   "#7 09:52 TCS buy 10 1001.10" +  "\n" +
					   "#8 10:01 XAM sell 20 240.10" +  "\n" +
					   "#9 10:02 XAM buy 150 242.70" +  "\n" +
					   "";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    try{
	    	stockExchange.readInput();
	    }
	    catch(Exception e){
	    	
	    }
	    assertEquals(4,stockExchange.getBuyOrders().size());
	    assertEquals(4,stockExchange.getSellOrders().size());
	    
	    assertEquals(2,stockExchange.getStockNameSet().size());
	    
	    ExecuteStockOrders executeStockOrders = new ExecuteStockOrders();
	    executeStockOrders.setBuyOrders(stockExchange.getBuyOrders().stream().
	    		filter(buyOrder -> buyOrder.getStockName().equalsIgnoreCase("XAM")).collect(Collectors.toList()));
	    executeStockOrders.setBuyOrders(stockExchange.getSellOrders().stream().
	    		filter(buyOrder -> buyOrder.getStockName().equalsIgnoreCase("XAM")).collect(Collectors.toList()));
	    
	    executeStockOrders.performTrades();
	    
	    List<Trades> resultTrades = new LinkedList<Trades>();
	    
	    resultTrades.add(new Trades(2,80,new BigDecimal(237.45),3));
	    resultTrades.add(new Trades(2,10,new BigDecimal(237.45),6));
	    resultTrades.add(new Trades(1,100,new BigDecimal(240.10),9));
	    resultTrades.add(new Trades(8,20,new BigDecimal(240.10),9));
	    resultTrades.add(new Trades(5,30,new BigDecimal(241.50),9));
	    
	    assertEquals(true,resultTrades.containsAll(executeStockOrders.getTradesList()));
	    
	}
	
}
