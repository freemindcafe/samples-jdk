package com.freemindcafe.apache.cxf.jaxrs.sample9;

import java.util.ArrayList;
import java.util.List;
 
public class OrderInfoImpl implements OrderInfo {
 
    List <Order> list = new ArrayList<Order>();
 
    public OrderInfoImpl(){
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setItemName("Soap");
        order1.setQuantity(120);
        order1.setCustomerName("Sandeep");
        order1.setShippingAddress("Gurgaon");
        list.add(0, order1);
 
        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setItemName("Shampoo");
        order2.setQuantity(50);
        order2.setCustomerName("Sandeep");
        order2.setShippingAddress("Gurgaon");
        list.add(1, order2);
    }
 
    @Override
    public Order getOrder(int orderId) {
        System.out.println("Inside the GetOrder...");
        if (list.get(0).getOrderId() == orderId) {
            return list.get(0);
        } else if (list.get(1).getOrderId() == orderId) {
            return list.get(1);
        } else {
            return null;
        }
    }
 
    @Override
    public OrderList getAllOrders() {
        OrderList details = new OrderList();
        for(Order order : list) {
            details.getOrder().add(order);
        }
        return details;
    }
}
