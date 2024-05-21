package br.com.techie.shoppingstore.AP003.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private int stock;

    private String urlImage;

    private String chassis;

    private String cpu;

    private String operationalSystem;

    private String chipset;

    private String memory;

    private String slots;

    private String storage;

    private String network;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
