package com.example.restservice;

import java.util.HashMap;

public class stockCheckerRespondant {
    private int totalStock;
    private final HashMap<String, warehouseItem> stockBreakdown;

    public stockCheckerRespondant(int allStocks, HashMap<String, warehouseItem> stockList){
        this.totalStock = allStocks;
        this.stockBreakdown = stockList;
    }

    public int getTotalStock() {
        return totalStock;
    }
    public HashMap<String, warehouseItem> getStockBreakdown() {
        return stockBreakdown;
    }
}
