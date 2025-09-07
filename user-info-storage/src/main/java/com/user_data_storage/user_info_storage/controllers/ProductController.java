package com.user_data_storage.user_info_storage.controllers;

import com.user_data_storage.user_info_storage.dto.product.ProductDto;
import com.user_data_storage.user_info_storage.models.Product;
import com.user_data_storage.user_info_storage.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {


    ProductService productService ;

    ProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping("/desc")
    public String displayProductsDescription(){
        return "This is about the products available in this website";
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto){

        Optional<Product> sameProduct = productService.findProductBySku(productDto.getSku());
        if(sameProduct.isPresent()){
           return new ResponseEntity<>("Product already exist , please use update api",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(productService.creteProduct(productDto.productDtoToProduct(productDto)), HttpStatus.CREATED);

    }


}
