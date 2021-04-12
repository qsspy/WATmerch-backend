package com.qsspy.watmerchbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "barcode")
    private long barcode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "VAT")
    private float vat;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "basic_details_id",referencedColumnName = "id")
    private ProductBasicDetails basicDetails;

    @OneToOne
    @JoinColumn(name = "details_id",referencedColumnName = "id")
    private ProductDetails details;
}
