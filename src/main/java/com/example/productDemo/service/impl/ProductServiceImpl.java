package com.example.productDemo.service.impl;

import com.example.productDemo.entity.Product;
import com.example.productDemo.repository.ProductRepository;
import com.example.productDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product createProduct(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setUpdatedBy(product.getUpdatedBy());
        return  productRepository.save(oldProduct);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
