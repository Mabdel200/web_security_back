package com.alibou.security.service.impl;

import com.alibou.security.entities.Categorie;
import com.alibou.security.entities.Marque;
import com.alibou.security.exception.RessourceNotFoundException;
import com.alibou.security.payload.CategoryDto;
import com.alibou.security.payload.MarqueDto;
import com.alibou.security.repository.CategorieRepository;
import com.alibou.security.repository.MarqueRepository;
import com.alibou.security.service.MarqueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarqueServiceImpl implements MarqueService{

    private MarqueRepository marqueRepository;
    private ModelMapper modelMapper;

    public MarqueServiceImpl(MarqueRepository marqueRepository, ModelMapper modelMapper) {
        this.marqueRepository = marqueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MarqueDto addMarque(MarqueDto marqueDto) {
        Marque marque = modelMapper.map(marqueDto, Marque.class);
        Marque savedMarque = marqueRepository.save(marque);

        return modelMapper.map(marque, MarqueDto.class);
    }

    @Override
    public MarqueDto getMarque(Long marqueId) {
        Marque marque = marqueRepository.findById(marqueId)
                .orElseThrow(()-> new RessourceNotFoundException("Marque", "Id", marqueId));

        return modelMapper.map(marque, MarqueDto.class);
    }

    @Override
    public List<MarqueDto> getAllMarque() {
        List<Marque> marques = marqueRepository.findAll();

        return marques.stream().map((marque)->modelMapper.map(marque, MarqueDto.class))
                .collect(Collectors.toList());
    }
}
