package com.prasad.ecommercespringboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;


public class PaymentInformation {

    @Column(name = "cardholder_name")
    private String cardHolder_name;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(name="cvv")
    private String cvv;



}
