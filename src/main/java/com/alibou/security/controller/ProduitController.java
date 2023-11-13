package com.alibou.security.controller;

import com.alibou.security.payload.ProductDto;
import com.alibou.security.payload.ProductResponse;
import com.alibou.security.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alibou.security.utils.Constants.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProduitController {

    private ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createProduct")
    public ResponseEntity<ProductDto> createProduit(@RequestBody ProductDto productDto)
    {
        return ResponseEntity.ok(produitService.createProduct(productDto));
    }

    //Get all posts rest api
    @GetMapping
    public ProductResponse getAllProduits(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    )
    {
        return produitService.getAllProducts(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduitById(@PathVariable("id") long id)
    {
        return ResponseEntity.ok(produitService.getProductById(id));
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduit(@RequestBody ProductDto productDto, @PathVariable long id)
    {
        ProductDto productResponse = produitService.updateProduct(productDto, id);

        return ResponseEntity.ok(productResponse);
    }

   // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable("id") long id)
    {
        produitService.deleteProductById(id);

        return ResponseEntity.ok("Produit entity deleted successfully");
    }

}
