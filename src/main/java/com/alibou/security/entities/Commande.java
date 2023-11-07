package com.alibou.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Order order;
    private LocalDate createAt = LocalDate.now();
    private LocalDate update;
    private LocalDate deliveryDate;
    private String deliveryAddress;

    @ManyToOne
    private Produit produit;
    @ManyToOne
    private User user;
}
