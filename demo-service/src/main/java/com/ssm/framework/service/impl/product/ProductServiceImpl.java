package com.ssm.framework.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.product.ProductDao;
import com.ssm.framework.model.product.Product;
import com.ssm.framework.service.product.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public Product getProduct(int productId) {
        return productDao.getProduct(productId);
    }

    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }
}
