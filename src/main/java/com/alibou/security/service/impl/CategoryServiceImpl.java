package com.alibou.security.service.impl;

import com.alibou.security.entities.Categorie;
import com.alibou.security.payload.CategoryDto;
import com.alibou.security.repository.CategorieRepository;
import com.alibou.security.service.CategoryService;
import jdk.jfr.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
