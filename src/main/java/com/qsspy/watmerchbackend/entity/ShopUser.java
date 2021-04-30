package com.qsspy.watmerchbackend.entity;

import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class ShopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @JsonMerge
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private ShopUserDetails userDetails;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @JsonMerge
    @OneToMany(mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;
}
