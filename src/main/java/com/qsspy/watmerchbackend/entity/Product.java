package com.qsspy.watmerchbackend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @Column(name = "barcode")
    private String barcode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "VAT")
    private float vat;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "basic_details_id",referencedColumnName = "id", nullable = false)
    private ProductBasicDetails basicDetails;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "details_id",referencedColumnName = "id", nullable = false)
    private ProductDetails details;
}
