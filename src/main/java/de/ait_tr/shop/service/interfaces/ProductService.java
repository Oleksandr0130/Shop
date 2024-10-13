package de.ait_tr.shop.service.interfaces;

import de.ait_tr.shop.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO getById(long id);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    ProductDTO remove(Long id);
    List<ProductDTO> getAll();

    ProductDTO removeByTitle(String title);
    ProductDTO restoreById(Long id);
    long getProductsCount();
    BigDecimal getTotalPrice();
    BigDecimal getAveragePrice();

    void attachImage(String imageUrl, String productTitle);
}
