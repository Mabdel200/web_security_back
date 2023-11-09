package com.alibou.security.service;

import com.alibou.security.payload.CategoryDto;
import com.alibou.security.payload.MarqueDto;

import java.util.List;

public interface MarqueService {
    MarqueDto addMarque(MarqueDto marqueDto);

    MarqueDto getMarque(Long marqueId);

    List<MarqueDto> getAllMarque();
}
