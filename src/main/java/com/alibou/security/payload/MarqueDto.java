package com.alibou.security.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarqueDto {

    private Long id;
    private String name;
    private String imageUrl;
}
