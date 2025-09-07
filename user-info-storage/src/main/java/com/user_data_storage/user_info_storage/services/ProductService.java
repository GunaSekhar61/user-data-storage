package com.user_data_storage.user_info_storage.services;

import com.user_data_storage.user_info_storage.models.Product;
import com.user_data_storage.user_info_storage.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository ;

    ProductService(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    public Product creteProduct(Product product){

        return productRepository.save(product);
    }


    public Optional<Product> findProductBySku(String sku){

        return productRepository.findBySku(sku);
    }
}
