package de.ait_tr.shop.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name = "product")
@Schema(description = "Class that describes Product")
public class Product {

    @Schema(description = "Product unique identifier", example = "777", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Schema(description = "Product title", example = "MSI i7")
    @Column(name = "title")
    private String title;

    @Schema(description = "Product price", example = "1500.00")
    @Column(name = "price")
    private BigDecimal price;

    @Schema(description = "Is product available", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "active")
    private boolean active;

    @Column(name = "image")
    private String image;

    @Column
    private int quantity;

    public Product() {
    }

    public Product(Long id, String title, BigDecimal price, boolean active) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.active = active;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format("Product: id - %s, title - %s, price - %s, active - %s",
                id, title, price, active ? "yes" : "no");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return active == product.active && quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active, image, quantity);
    }
}
