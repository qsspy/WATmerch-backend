package com.qsspy.watmerchbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_details")
@Data
public class ShopUserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "comany_name")
    private String company;

    @Column(name = "nip")
    private String nip;

    @Column(name = "avatar")
    @Lob
    private Byte[] avatar;
}
