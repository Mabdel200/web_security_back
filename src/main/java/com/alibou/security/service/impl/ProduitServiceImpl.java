package com.alibou.security.service.impl;

import com.alibou.security.entities.Categorie;
import com.alibou.security.entities.Marque;
import com.alibou.security.entities.Produit;
import com.alibou.security.exception.RessourceNotFoundException;
import com.alibou.security.payload.ProductDto;
import com.alibou.security.payload.ProductResponse;
import com.alibou.security.repository.CategorieRepository;
import com.alibou.security.repository.MarqueRepository;
import com.alibou.security.repository.ProduitRepository;
import com.alibou.security.service.CategoryService;
import com.alibou.security.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    private MarqueRepository marqueRepository;
    private ModelMapper modelMapper;

    public ProduitServiceImpl(ProduitRepository produitRepository,
                              CategorieRepository categorieRepository, ModelMapper modelMapper) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired


    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Categorie categorie = categorieRepository.findById(productDto.getCategoryId())
                .orElseThrow(()->new RessourceNotFoundException("Category", "Id", productDto.getCategoryId()));

        Marque marque = marqueRepository.findById(productDto.getMarqueId())
                .orElseThrow(()->new RessourceNotFoundException("Marque", "Id", productDto.getMarqueId()));

        Produit produit = modelMapper.map(productDto, Produit.class);

        produit.setCategorie(categorie);
        produit.setMarque(marque);
        Produit newProduit = produitRepository.save(produit);

        return modelMapper.map(newProduit, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        //Create pegeable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Produit> produits = produitRepository.findAll(pageable);

        //get content for page object
        List<Produit> listOfProduit = produits.getContent();

        List<ProductDto> content = listOfProduit.stream()
                .map(post -> modelMapper.map(produits, ProductDto.class)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(produits.getNumber());
        productResponse.setPageSize(produits.getSize());
        productResponse.setTotalElements(produits.getTotalElements());
        productResponse.setTotalPages(produits.getTotalPages());
        productResponse.setLast(produits.isLast());

        return productResponse;
    }

    @Override
    public ProductDto getProductById(long id) {
        Produit produit = produitRepository.findById(id).orElseThrow(() ->
                new RessourceNotFoundException("Produit", "id", id));

        return modelMapper.map(produit, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, long id) {


        Produit produit = produitRepository.findById(id).orElseThrow(() ->
                new RessourceNotFoundException("Produit", "id", id));


        Categorie categorie = categorieRepository.findById(productDto.getCategoryId())
                .orElseThrow(()->new RessourceNotFoundException("Category", "Id", productDto.getCategoryId()));

        Marque marque = marqueRepository.findById(productDto.getMarqueId())
                .orElseThrow(()->new RessourceNotFoundException("Marque", "Id", productDto.getMarqueId()));


        produit.setName(productDto.getName());
        produit.setDescription(productDto.getDescription());
        produit.setImageUrl(productDto.getImageUrl());
        produit.setCategorie(categorie);
        produit.setMarque(marque);
        produit.setPrix(productDto.getPrix());

        Produit updatedProduit = produitRepository.save(produit);


        return modelMapper.map(produit, ProductDto.class); //à vérifier
    }

    @Override
    public void deleteProductById(long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() ->new RessourceNotFoundException("Produit", "Id", id));

        produitRepository.delete(produit);
    }
}
