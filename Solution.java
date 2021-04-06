package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*In a production systemsome of this should be done with POST requests but the principle is the same. It should also include a database so that restarting the web server does not delete the data.*/
@RestController
public class GreetingController {

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();
        private List itemList = new ArrayList();
        private List stockList = new ArrayList();
        @GetMapping("/greeting")
        public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
                return new Greeting(counter.incrementAndGet(), String.format(template, name));
        }
        @GetMapping("/stock")
        public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
                String StockStr="StockList: ";
                for (int i = 0; i < itemList.size(); i++) {
                        StockStr+=itemList.get(i)+","+stockList.get(i)+" ";
                }
                return new Greeting(counter.incrementAndGet(), StockStr);
        }
        @GetMapping("/stock/add")
        public Greeting greeting3(@RequestParam(value = "item") String item) {
                if (itemList.contains(item))
                {
                        return new Greeting(counter.incrementAndGet(), "Item Already exists");
                }
                else
                {
                        itemList.add(item);
                        stockList.add(0);
                        String retStr=item+" added";
                        return new Greeting(counter.incrementAndGet(), retStr);
                }
        }
        @GetMapping("/stock/addStock")
        public Greeting greeting4(@RequestParam(value = "item") String item,@RequestParam(value = "stock") int stock) {
                if (!itemList.contains(item))
                {
                        return new Greeting(counter.incrementAndGet(), "Item does not exist");
                }
                else
                {
                        int index = itemList.indexOf(item);
                        int newStockLevel=(int)stockList.get(index)+stock;
                        stockList.set(index, newStockLevel);
                        String retStr=stock+" stock added to "+item;
                        return new Greeting(counter.incrementAndGet(), retStr);
                }
        }
        @GetMapping("/stock/removeStock")
        public Greeting greeting5(@RequestParam(value = "item") String item,@RequestParam(value = "stock") int stock) {
                if (!itemList.contains(item))
                {
                        return new Greeting(counter.incrementAndGet(), "Item does not exist");
                }
                else
                {
                        int index = itemList.indexOf(item);
                        int newStockLevel=(int)stockList.get(index)-stock;
                        stockList.set(index, newStockLevel);
                        String retStr=stock+" stock removed from "+item;
                        return new Greeting(counter.incrementAndGet(), retStr);
                }
        }
        @GetMapping("/stock/setStock")
        public Greeting greeting6(@RequestParam(value = "item") String item,@RequestParam(value = "stock") int stock) {
                if (!itemList.contains(item))
                {
                        return new Greeting(counter.incrementAndGet(), "Item does not exist");
                }
                else
                {
                        int index = itemList.indexOf(item);
                        stockList.set(index, stock);
                        String retStr="Stock of "+item+ "set to "+stock;
                        return new Greeting(counter.incrementAndGet(), retStr);
                }
        }



}
