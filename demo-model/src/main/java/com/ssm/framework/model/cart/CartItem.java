package com.ssm.framework.model.cart;

import java.io.Serializable;

import com.ssm.framework.model.product.Product;

/**
 * Webflow 产品项
 * CartItem.
 *
 * @author zax
 */
public class CartItem implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -1043504982867572579L;
    
    private Product product; 
    private int quantity; 

    public CartItem(){}
    public CartItem(Product product, int quantity) { 
        this.product = product; 
        this.quantity = quantity; 
    } 

    public int getTotalPrice() { 
        return this.quantity * this.product.getPrice(); 
    } 

    public void increaseQuantity() { 
        this.quantity++; 
    }
    public Product getProduct() {
        return this.product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 
}
