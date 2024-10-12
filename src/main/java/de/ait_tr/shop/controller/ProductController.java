package de.ait_tr.shop.controller;


import de.ait_tr.shop.exception_handling.Response;
import de.ait_tr.shop.exception_handling.exeptions.FirstTestException;
import de.ait_tr.shop.model.dto.ProductDTO;
import de.ait_tr.shop.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Controller for operations with products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create product", tags = { "Product" }, description = "Add new product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)), @Content(mediaType = "application/xml",
                    schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })

    @PostMapping
    public ProductDTO saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
    }

    @Operation(summary = "Get product by id", tags = { "Product" }, description = "Find product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductDTO.class)), @Content(mediaType = "application/xml",
                    schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })

    @GetMapping("/{product_id}")
    public ProductDTO getById(
            @Parameter(description = "The id that needs to be fetched.", required = true)
            @PathVariable("product_id") long id) {
        // проверяем если id равен 10, выбрасываем исключение
        // так делать НЕЛЬЗЯ только в Service
//        if (id == 10){
//            throw new FirstTestException("ID cannot be 10");
//        }
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public ProductDTO remove(@PathVariable Long id){
        return productService.remove(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

//    @DeleteMapping
//    public Product removeUniversal(@RequestParam(required = false) Long id,
//                                  @RequestParam(required = false) String title) {
//        if (id != null) {
//            return productService.remove(id);
//        } else if (title != null) {
//            return productService.removeByTitle(title);
//        }
//        return null;
//    }

    @DeleteMapping("/by-title")
    public ProductDTO removeByTitle(@RequestParam String title) {
        return productService.removeByTitle(title);
    }

    @PutMapping("/restore/{id}")
    public ProductDTO restoreById(@PathVariable Long id) {
        return productService.restoreById(id);
    }

    @GetMapping("/count")
    public long getProductsCount() {
        return productService.getProductsCount();
    }

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
        return productService.getTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return productService.getAveragePrice();
    }

    @ExceptionHandler(FirstTestException.class)
    public ResponseEntity<Response> handleException(FirstTestException e){
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
