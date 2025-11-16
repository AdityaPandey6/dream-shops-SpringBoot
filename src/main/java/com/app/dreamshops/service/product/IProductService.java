package com.app.dreamshops.service.product;

import com.app.dreamshops.model.Product;
import com.app.dreamshops.requests.AddProductRequest;
import com.app.dreamshops.requests.ProductUpdateRequest;

import java.util.List;

public interface IProductService
{
    Product addProduct(AddProductRequest product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product , Long productId);

    List<Product> getProductByCategory(String category);
    List<Product> getProductByBrandName(String brand);
    List<Product> getProductByCategoryAndBrand(String category , String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand , String name);
    Long countProductByBrandAndName(String brand , String name);

}
