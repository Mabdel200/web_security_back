package com.alibou.security.service.impl;

import com.alibou.security.entities.Categorie;
import com.alibou.security.exception.RessourceNotFoundException;
import com.alibou.security.payload.CategoryDto;
import com.alibou.security.repository.CategorieRepository;
import com.alibou.security.service.CategoryService;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategorieRepository categorieRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategorieRepository categorieRepository, ModelMapper modelMapper) {
        this.categorieRepository = categorieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Categorie categorie = modelMapper.map(categoryDto, Categorie.class);
        Categorie savedCategory = categorieRepository.save(categorie);

        return modelMapper.map(categorie, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {

        Categorie categorie = categorieRepository.findById(categoryId)
                .orElseThrow(()-> new RessourceNotFoundException("Category", "Id", categoryId));

        return modelMapper.map(categorie, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Categorie> categories = categorieRepository.findAll();

        return categories.stream().map((categorie)->modelMapper.map(categorie, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Categorie categorie = categorieRepository.findById(categoryId)
                .orElseThrow(()-> new RessourceNotFoundException("Category", "Id", categoryId));

        categorie.setName(categoryDto.getName());
        categorie.setDescription(categoryDto.getDescription());
        categorie.setImageUrl(categoryDto.getImageUrl());
        categorie.setId(categoryId);

        Categorie updatedCategory = categorieRepository.save(categorie);

        return modelMapper.map(categorie, CategoryDto.class);
    }
}
