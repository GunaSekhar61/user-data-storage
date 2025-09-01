package com.user_data_storage.user_info_storage.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class Products {

    @GetMapping("/desc")
    public String displayProductsDescription(){
        return "This is about the products available in this website";
    }
}
