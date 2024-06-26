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

    public String getCardHolder_name() {
        return cardHolder_name;
    }

    public void setCardHolder_name(String cardHolder_name) {
        this.cardHolder_name = cardHolder_name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
