package com.alibou.security.entities;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate createAt = LocalDate.now();
    private LocalDate updateAt;
//    private String slug;
//    private SubCategorie subCategorie;
    private String imageUrl;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits = new ArrayList<>();
}
