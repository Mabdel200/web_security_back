package com.alibou.security.service;

import com.alibou.security.payload.ProductDto;
import com.alibou.security.payload.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProduitService {

    ProductDto createProduct(ProductDto productDto);

    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDto getProductById(long id);

    ProductDto updateProduct(ProductDto productDto, long id);

    void deleteProductById(long id);

}
