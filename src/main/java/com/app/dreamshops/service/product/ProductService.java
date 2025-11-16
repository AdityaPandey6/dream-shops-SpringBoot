package com.app.dreamshops.service.product;

import com.app.dreamshops.exceptions.ProductNotFoundException;
import com.app.dreamshops.model.Category;
import com.app.dreamshops.model.Product;
import com.app.dreamshops.repository.CategoryRepository;
import com.app.dreamshops.repository.ProductRepository;
import com.app.dreamshops.requests.AddProductRequest;
import com.app.dreamshops.requests.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product addProduct(AddProductRequest request) {
        // Check if category exist
        // If yes ,then set it as the new Product category
        // If no , then create new category and set it as the new Product category.
         Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName())).orElseGet(
                 ()->{
                     Category newCategory = new Category(request.getCategory().getName());
                     categoryRepository.save(newCategory);
                     return newCategory;
                 }
         );
         request.setCategory(category);
         return productRepository.save(createProduct(request , category));
    }

    private Product createProduct(AddProductRequest request , Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category

        );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete ,
                        ()-> {throw new ProductNotFoundException("Product Not Found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct , request))
                .map(productRepository :: save).orElseThrow(()-> new ProductNotFoundException("Product Not Found!"));
    }

    private Product updateExistingProduct(Product existingProduct , ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setCategory(request.getCategory());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrandName(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category , brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand , name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand , name);
    }
}
