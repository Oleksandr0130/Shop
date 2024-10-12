package de.ait_tr.shop.service.interfaces;

import de.ait_tr.shop.model.dto.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {

    public CustomerDTO saveCustomer(CustomerDTO customerDTO);
    public CustomerDTO getById(long id);
    public CustomerDTO updateCustomer(CustomerDTO customerDTO);
    public List<CustomerDTO> getAll();
    void deleteById(long id);
    void deleteByName(String name);
    void restoreById(long id);
    long getActiveCustomerCount();
    BigDecimal getTotalCostOfCustomersProducts(long customerId);
    BigDecimal getAverageCostOfCustomersProducts(long customerId);
    void addProductToCustomersCart(long customerId, long productId);
    void removeProductToCustomersCart(long customerId, long productId);
    void clearCustomersCart(long customerId);
}
