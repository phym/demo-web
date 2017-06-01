package com.ssm.framework.service.product;

import java.util.List;

import com.ssm.framework.model.product.Product;

/**
 * Webflow 产品接口
 * ProductService.
 *
 * @author zax
 */
public interface ProductService {

    public List<Product> getProducts();

    public Product getProduct(int productId);
    
    public int addProduct(Product product);
}
