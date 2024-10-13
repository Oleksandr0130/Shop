package de.ait_tr.shop.controller;

import de.ait_tr.shop.model.dto.CustomerDTO;
import de.ait_tr.shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable long id) {
        return customerService.getById(id);
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return null;
    }

    //Todo
    public List<CustomerDTO> getAll() {
        return List.of();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        customerService.deleteById(id);
    }

    @DeleteMapping
    public void deleteByName(@RequestParam String name) {
        customerService.deleteByName(name);
    }

    //Todo
    public void restoreById(long id) {

    }

    //Todo
    public long getActiveCustomerCount() {
        return 0;
    }

    //Todo
    public BigDecimal getTotalCostOfCustomersProducts(long customerId) {
        return null;
    }

    //Todo
    public BigDecimal getAverageCostOfCustomersProducts(long customerId) {
        return null;
    }

    //Todo
    public void addProductToCustomersCart(long customerId, long productId) {

    }

    //Todo
    public void removeProductToCustomersCart(long customerId, long productId) {

    }

    //Todo
    public void clearCustomersCart(long customerId) {

    }
}
