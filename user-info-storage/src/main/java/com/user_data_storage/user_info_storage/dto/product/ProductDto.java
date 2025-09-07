package com.user_data_storage.user_info_storage.dto.product;



import com.user_data_storage.user_info_storage.models.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {

    @NotBlank(message="product name should not be empty")
    private String name;

    @NotBlank(message="product description should not be empty")
    private String description;

    @Positive(message="product price should  be postive")
    @NotBlank(message="product price should not be empty")
    private double price;

    @Positive(message="stock should be positve")
    @NotBlank(message="product price should not be empty")
    private int stock;

    private String brand;

    private String category;

    @NotBlank(message = "isActive should not be blank")
    private boolean isActive;

    @NotEmpty(message="imageurls should be present ")
    private List<String> imageUrls;

    @NotBlank(message = "sku should not be empty")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "SKU must be alphanumeric with dashes")
    private String sku;


    public Product productDtoToProduct(ProductDto productDto){

        return Product.builder().name(productDto.getName())
                                .description(productDto.getDescription())
                                .price(productDto.getPrice())
                                .stock(productDto.getStock())
                                .brand(productDto.getBrand())
                                .category(productDto.getCategory())
                                .isActive(productDto.isActive())
                                .imageUrls(productDto.getImageUrls())
                                .sku(productDto.getSku())
                                .build();
    }
}
