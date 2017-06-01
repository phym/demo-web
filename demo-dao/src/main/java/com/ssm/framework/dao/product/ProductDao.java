package com.ssm.framework.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import com.ssm.framework.model.product.Product;

/**
 * Webflow 产品数据接口
 * ProductDao.
 *
 * @author zax
 */
public interface ProductDao {
    
    public List<Product> getProducts();

    public Product getProduct(int productId);
    
    @Insert("insert into t_product (description, price) values (#{description}, #{price})")
    int addProduct(Product product);
}
