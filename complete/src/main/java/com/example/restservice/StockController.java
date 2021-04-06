package com.example.restservice;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class StockController {
    private HashMap<String, warehouseItem> warehouseStock = new HashMap<String, warehouseItem>();

    @GetMapping(value = "/listStock") //, produces = MediaType.APPLICATION_JSON_VALUE)
    public stockCheckerRespondant listStock() {
        /*
        String warehouseContent = "{\"Stock\":[";
        for (String itemName : warehouseStock.keySet()) {
            warehouseContent += "{\"Name\":" + "\"" + itemName + "\"" + "," + "\"Amount\":" + Integer.toString(warehouseStock.get(itemName).getItemQauntity()) + "},";
        }
        if (warehouseContent != "{\"Stock\":["){
            warehouseContent = warehouseContent.substring(0, warehouseContent.length() - 1);
        }
        warehouseContent += "]}";
        JSONObject response = new JSONObject(warehouseContent);
        */
        int stockCount = 0;
        for (warehouseItem itemQuantity : warehouseStock.values()) {
            stockCount += itemQuantity.getItemQauntity();
        }

        return new stockCheckerRespondant(stockCount, warehouseStock);
    }

    @PostMapping("/addItem")
    public stockRespondant addItem(@RequestParam(value = "itemName", defaultValue = "") String itemName) {
        if (itemName.isEmpty()) {
            return new stockRespondant(false, "You must supply a valid name for the item!");
        }
        if (warehouseStock.containsKey(itemName)) {
            return new stockRespondant(false, "This item already exists!");
        }
        warehouseStock.put(itemName, new warehouseItem(itemName));
        return new stockRespondant(true, (itemName + " added to warehouse!"));
    }

    @PostMapping("/setStock")
    public stockRespondant setStock(@RequestParam(value = "itemName", defaultValue = "") String itemName, @RequestParam(value = "stockLevel") int stockLevel){
        if (!warehouseStock.containsKey(itemName)) {
            return new stockRespondant(false, "The referenced item does not exist!");
        }
        if (itemName.isEmpty()) {
            return new stockRespondant(false, "You must supply a valid name for the item!");
        }

        warehouseStock.get(itemName).setItemQauntity(stockLevel);
        return new stockRespondant(true, ("The stock levels of '" + itemName + "' has been set to '" + stockLevel + "'!"));
    }

    @PutMapping("/addStock")
    public stockRespondant addStock(@RequestParam(value = "itemName", defaultValue = "") String itemName, @RequestParam(value = "numItem", defaultValue = "0") int numItem){
        if (itemName.isEmpty()) {
            return new stockRespondant(false, "You must supply a valid name for the item!");
        }
        if (!warehouseStock.containsKey(itemName)) {
            return new stockRespondant(false, "The referenced item does not exist!");
        }

        warehouseStock.get(itemName).increaseItemQauntity(numItem);
        return new stockRespondant(true, ("The stock levels of '" + itemName + "' has increased by '" + numItem + "'!"));
    }

    @PutMapping("/removeStock")
    public stockRespondant removeStock(@RequestParam(value = "itemName", defaultValue = "") String itemName, @RequestParam(value = "numItem", defaultValue = "0") int numItem){
        if (itemName.isEmpty()) {
            return new stockRespondant(false, "You must supply a valid name for the item!");
        }
        if (!warehouseStock.containsKey(itemName)) {
            return new stockRespondant(false, "The referenced item does not exist!");
        }
        int currentStock = warehouseStock.get(itemName).getItemQauntity();
        if (currentStock < numItem) {
            return new stockRespondant(false, ("Currently there are " + currentStock + " '" + itemName + "' items in stock. Removing " + numItem + " of those items will make the item's quantity negative! Aborting action!"));
        }

        warehouseStock.get(itemName).decreaseItemQauntity(numItem);
        return new stockRespondant(true, ("The stock levels of '" + itemName + "' has decreased by '" + numItem + "'!"));
    }

    //From example
    /*
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String
                                     name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    } */
}