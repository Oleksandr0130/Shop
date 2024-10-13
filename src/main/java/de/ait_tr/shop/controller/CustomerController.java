package de.ait_tr.shop.controller;

import de.ait_tr.shop.exception_handling.exceptions.FirstTestException;
import de.ait_tr.shop.exception_handling.exceptions.ThirdTestException;
import de.ait_tr.shop.model.entity.Customer;
import de.ait_tr.shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 20.08.2024
 */

@RestController
@RequestMapping("/customers")
public class CustomerController   {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @GetMapping
    public List<Customer> getAllActiveCustomers() {
        return service.getAllActiveCustomers();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable long id) {

        return service.getById(id);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return service.update(customer);
    }

    // /customers/333
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        service.deleteById(id);
    }

    // /customers/fff
    @DeleteMapping
    public void deleteByName(@RequestParam String name) {
        service.deleteByName(name);
    }


    public void restoreById(long id) {

    }


    public long getActiveCustomerCount() {
        return 0;
    }


    public BigDecimal getTotalCostOfCustomersProducts(long customerId) {
        return null;
    }


    public BigDecimal getAverageCostOfCustomersProducts(long customerId) {
        return null;
    }


    public void addProductToCustomersCart(long customerId, long productId) {

    }


    public void removeProductFromCustomersCart(long customerId, long productId) {

    }


    public void clearCustomersCart(long customerId) {

    }
}
