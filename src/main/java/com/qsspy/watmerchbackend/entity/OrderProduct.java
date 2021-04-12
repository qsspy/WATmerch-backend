package com.qsspy.watmerchbackend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_barcode", referencedColumnName = "barcode")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private Purchase purchase;

}