package com.alibou.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;
    private String description;
    private float prix;
    private int quantiteStock;
    private String imageUrl;
    private String code;

    private Long categoryId;
    private Long marqueId;
}
