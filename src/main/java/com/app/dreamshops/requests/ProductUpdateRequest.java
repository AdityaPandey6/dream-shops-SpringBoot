package com.app.dreamshops.requests;

import com.app.dreamshops.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price; // using bigdecimal because it gives exact precision for money
    private int inventory;
    private String description;
    private Category category;
}
