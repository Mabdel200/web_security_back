package com.alibou.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private float prix;
    private int quantiteStock;
    private String imageUrl;
    private LocalDate createAt = LocalDate.now();
    private LocalDate updateAt;
    private String code;
//    private Size size;
//    private Color color;
//    private Coupon coupon;
//    private Boolean inStock;
//    private int hotDeal;
//    private int futured;
//    private int topRanking;
//    private int specialOffer;
//    private int specialDeal;
//    private int popular;

    @ManyToOne
    private Marque marque;
    @ManyToOne
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private List<Commande> commandes = new ArrayList<>();
}
