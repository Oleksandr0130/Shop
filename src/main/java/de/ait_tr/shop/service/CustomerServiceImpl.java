package de.ait_tr.shop.service;

import de.ait_tr.shop.model.dto.CustomerDTO;
import de.ait_tr.shop.model.entity.Customer;
import de.ait_tr.shop.repository.CustomerRepository;
import de.ait_tr.shop.service.interfaces.CustomerService;
import de.ait_tr.shop.service.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMappingService mapper;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.mapDtoToEntity(customerDTO);
//        customer.setActive(true);
        return mapper.mapEntityToDto(repository.save(customer));
    }

    @Override
    public CustomerDTO getById(long id) {
        Customer customer = repository.findById(id).orElse(null);
        if (customer == null || !customer.isActive()) {
        return null;
        }
        return mapper.mapEntityToDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        return null;
    }


    @Override
    public List<CustomerDTO> getAll() {
        return repository.findAll().stream()
                .filter(Customer::isActive)
                .map(mapper::mapEntityToDto)
                .toList();
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(long id) {

    }

    @Override
    public long getActiveCustomerCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalCostOfCustomersProducts(long customerId) {
        return null;
    }

    @Override
    public BigDecimal getAverageCostOfCustomersProducts(long customerId) {
        return null;
    }

    @Override
    public void addProductToCustomersCart(long customerId, long productId) {

    }

    @Override
    public void removeProductToCustomersCart(long customerId, long productId) {

    }

    @Override
    public void clearCustomersCart(long customerId) {

    }
}
