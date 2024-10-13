package de.ait_tr.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;
import java.util.Objects;

public class CartDTO {

    private Long id;

    @JsonBackReference
    private CustomerDTO customer;

    List<ProductDTO> products;

    @Override
    public String toString() {
        return String.format("CartDTO: id: %d, products: %s, customer: %s",
                id, products == null ? "null" : products, customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(id, cartDTO.id) && Objects.equals(customer, cartDTO.customer) && Objects.equals(products, cartDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
