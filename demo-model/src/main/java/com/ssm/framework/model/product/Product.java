package com.ssm.framework.model.product;

import java.io.Serializable;

/**
 * 
 * Webflow 产品对象
 * Product.
 *
 * @author zax
 */
public class Product implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    
    private Long id; 
    private String description; 
    private int price; 
    
    public Product(){}
    
    public Product(Long id, String description, int price) { 
        this.id = id; 
        this.description = description; 
        this.price = price; 
    }
    
    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }



    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    } 
}
