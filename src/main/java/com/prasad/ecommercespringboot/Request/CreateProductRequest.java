package com.prasad.ecommercespringboot.Request;

import com.prasad.ecommercespringboot.model.Size;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {

    private String title;

    private String description;
    private int price ;
    private int discountedPersent;
    private int quantity;
    private int discountPrice;
    private String color;
    private String brand;
    private Set<Size> size =new HashSet<>();
    private String imageUrl ;
    private String topLevelCategory;
    private String SecondLevelCategory;
    private String thirdLevelCategory;

   public CreateProductRequest(){

   }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountedPersent() {
        return discountedPersent;
    }

    public void setDiscountedPersent(int discountedPersent) {
        this.discountedPersent = discountedPersent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Set<Size> getSize() {
        return size;
    }

    public void setSize(Set<Size> size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTopLevelCategory() {
        return topLevelCategory;
    }

    public void setTopLevelCategory(String topLevelCategory) {
        this.topLevelCategory = topLevelCategory;
    }

    public String getSecondLevelCategory() {
        return SecondLevelCategory;
    }

    public void setSecondLevelCategory(String secondLevelCategory) {
        SecondLevelCategory = secondLevelCategory;
    }

    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }
}
