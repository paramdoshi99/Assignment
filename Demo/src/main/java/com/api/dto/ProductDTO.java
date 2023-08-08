package com.api.dto;

import java.time.LocalDate;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private String productDescription;
    private LocalDate expiryDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", product name=" + productName + ", description" + productDescription
                + ", expiry date=" + expiryDate + "]";
    }
}
