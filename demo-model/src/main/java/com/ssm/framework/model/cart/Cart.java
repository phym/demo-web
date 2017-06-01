package com.ssm.framework.model.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.framework.model.product.Product;

/**
 * Webflow 购物车
 * Cart.
 *
 * @author zax
 */
public class Cart implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 3243883365425861739L;
    
    private Map<Long, CartItem> map = new HashMap<Long, CartItem>(); 
    
    private List<CartItem> items;
    
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        this.items = new ArrayList<CartItem>(map.values());
        return this.items;
    }

    public void addItem(Product product) { 
        Long id = product.getId(); 
        CartItem item = map.get(id); 
        if (item != null) 
            item.increaseQuantity(); 
        else 
            map.put(id, new CartItem(product, 1)); 
     } 

    public int getTotalPrice() { 
        int total = 0; 
        for (CartItem item : map.values()) 
            total += item.getProduct().getPrice() * item.getQuantity(); 
        return total; 
    } 
}
