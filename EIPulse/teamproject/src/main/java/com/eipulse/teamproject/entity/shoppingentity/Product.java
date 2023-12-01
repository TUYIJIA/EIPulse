package com.eipulse.teamproject.entity.shoppingentity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String productName;

    @JsonBackReference(value = "subType")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_type_id")
    private SubType subType;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at",insertable = false,updatable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at",insertable = false,updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private String status;


    public Product() {
    }
    //postData
    public Product(String productName, SubType subType, String description, Integer price, Integer stockQuantity, String imageUrl) {
        this.productName = productName;
        this.subType = subType;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }
    public Product(Integer id, String productName, SubType subType, String description, Integer price, Integer stockQuantity, String imageUrl) {
        this.id = id;
        this.productName = productName;
        this.subType = subType;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    public Product(String productName, SubType subType, String description, Integer price, Integer stockQuantity, String imageUrl, String status) {
        this.productName = productName;
        this.subType = subType;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.status = status;
    }
}