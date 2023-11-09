package com.alibou.security.controller;

import com.alibou.security.payload.CategoryDto;
import com.alibou.security.payload.MarqueDto;
import com.alibou.security.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class MarqueController {

    private MarqueService marqueService;

    @Autowired
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }


    //Build Add brand REST API
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MarqueDto> addMarque(@RequestBody MarqueDto marqueDto){
        MarqueDto savedMarque = marqueService.addMarque(marqueDto);

        return new ResponseEntity<>(savedMarque, HttpStatus.CREATED);
    }

    // Build Get brand rest api
    @GetMapping("{id}")
    public ResponseEntity<MarqueDto> getMarque(@PathVariable("id") Long marqueId)
    {
        MarqueDto marqueDto = marqueService.getMarque(marqueId);

        return ResponseEntity.ok(marqueDto);
    }

    // Build Get all marque rest api
    @GetMapping
    public ResponseEntity<List<MarqueDto>> getMarques(){

        return ResponseEntity.ok(marqueService.getAllMarque());
    }
}
