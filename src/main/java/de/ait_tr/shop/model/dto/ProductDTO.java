package de.ait_tr.shop.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;


@Schema(description = "DTO for Product")
public class ProductDTO {

    @Schema(description = "Product unique identifier", example = "777", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Product title", example = "MSI i7")
    @NotNull(message = "Product title cannot be null")
    @NotBlank(message = "Product title cannot be empty and with space")
    @Pattern(regexp = "^[A_Z][a-z0-9 ]{2,}$", message = "Product title should be at least 3 characters long and contains only letters and spaces")
    private String title;

    @Schema(description = "Product price", example = "1500.00")
    @DecimalMin(value = "1.0", message = "Product price should be greater or equal than 1")
    @DecimalMax(value = "100000.0", message = "Product price should be less than 100_000")
    private BigDecimal price;

    private String image;


    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ProductDTO: id - %d, title - %s, price - %s",
                id, title, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, image);
    }
}
