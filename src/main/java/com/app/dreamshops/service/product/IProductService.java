package com.app.dreamshops.service.product;

import com.app.dreamshops.model.Product;

import java.util.List;

public interface IProductService
{
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product , Long productId);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrandName(String brand);
    List<Product> getProductByCategoryAndBrand(String category , String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand , String name);
    Long countProductByBrandAndName(String brand , String name);

}
