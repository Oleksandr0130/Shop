package de.ait_tr.shop.service.interfaces;

import de.ait_tr.shop.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    public ProductDTO saveProduct(ProductDTO productDTO);
    public ProductDTO getById(long id);
    public ProductDTO updateProduct(Long id, ProductDTO productDTO);
    public ProductDTO remove(Long id);
    public List<ProductDTO> getAll();

    ProductDTO removeByTitle(String title);
    ProductDTO restoreById(Long id);
    long getProductsCount();
    BigDecimal getTotalPrice();
    BigDecimal getAveragePrice();
}
