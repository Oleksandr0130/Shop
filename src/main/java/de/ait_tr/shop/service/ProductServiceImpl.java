package de.ait_tr.shop.service;

import de.ait_tr.shop.exception_handling.exeptions.FirstTestException;
import de.ait_tr.shop.exception_handling.exeptions.ThirdTestException;
import de.ait_tr.shop.model.dto.ProductDTO;
import de.ait_tr.shop.model.dto.ProductSupplyDto;
import de.ait_tr.shop.model.entity.Product;
import de.ait_tr.shop.repository.ProductRepository;
import de.ait_tr.shop.service.interfaces.ProductService;
import de.ait_tr.shop.service.mapping.ProductMappingService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mapper;
    private final ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mapper, ProductRepository productRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void attachImage(String imageUrl, String productTitle) {
        // ищем продукт в БД по названию
        Product product = productRepository.findByTitle(productTitle)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // присваеваем ссылку на изображение
        product.setImage(imageUrl);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        System.out.println("Method save work");
        Product product = mapper.mapDtoToEntity(productDTO);
//        product.setActive(true);
        return mapper.mapEntityToDto(repository.save(product));
    }

    @Override
    public ProductDTO getById(long id) {

        Product product = repository.findById(id).orElse(null);

        if (product == null){
            throw new ThirdTestException("Product with id " + id + " not found");
        }

//        if (product == null){
//            throw new SecondTestException("Product with id " + id + " not found");
//        }

        if (!product.isActive()){
            throw new FirstTestException("This is first test Exception message");
        }
        return mapper.mapEntityToDto(product);
    }

//    @Override
//    public ProductDTO getById(long id) {
//        logger.info("Method getById called with parameter: {}", id);
//        logger.warn("Method getById called with parameter: {}", id);
//        logger.error("Method getById called with parameter: {}", id);
//        Product product = repository.findById(id).orElse(null);
//        if (product == null || !product.isActive()){
//            return null;
//        }
//        return mapper.mapEntityToDto(product);
//    }


    @Override
    public List<ProductSupplyDto> getAllActiveProductsForSupply() {
        return repository.findAllByActiveTrue().stream()
                .map(mapper::mapEntityToSupplyDto)
                .toList();
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO remove(Long id) {
        return null;
    }

    @Override
    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .map(mapper::mapEntityToDto)
                .toList();
    }

    @Override
    public ProductDTO removeByTitle(String title) {
        return null;
    }

    @Override
    public ProductDTO restoreById(Long id) {
        return null;
    }

    @Override
    public long getProductsCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }
}
