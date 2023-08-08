package com.api.service;

import com.api.Exception.HnDBankException;
import com.api.dto.CustomerDTO;
import com.api.entity.Customer;
import com.api.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRespository;

    @Override
    public int addCustomer(CustomerDTO customerDto) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerDto.getCustomerId());
        if (optional.isPresent())
            throw new HnDBankException("Service.CUSTOMER_FOUND");
        Customer customer = new Customer();
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        customer.setEmailId(customerDto.getEmailId());
        customer.setName(customerDto.getName());
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerType(customerDto.getCustomerType());
        Customer s = customerRespository.save(customer);
        return s.getCustomerId();
    }
    @Override
    public CustomerDTO getCustomer(Integer customerId) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
//      Customer customer = new Customer();
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setEmailId(customer.getEmailId());
        customerDto.setName(customer.getName());
        customerDto.setCustomerType(customer.getCustomerType());
        return customerDto;
    }
    @Override
    public List<CustomerDTO> findAll() throws HnDBankException {
        Iterable<Customer> customers = customerRespository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerDTO customerDto = new CustomerDTO();
            customerDto.setCustomerId(customer.getCustomerId());
            customerDto.setDateOfBirth(customer.getDateOfBirth());
            customerDto.setEmailId(customer.getEmailId());
            customerDto.setCustomerType(customer.getCustomerType());
            customerDto.setName(customer.getName());
            customerDTOs.add(customerDto);
        });
		if (customerDTOs.isEmpty())
                throw new HnDBankException("Service.CUSTOMERS_NOT_FOUND");
		return customerDTOs;
}

    @Override
    public void updateCustomer(Integer customerId, CustomerDTO customerDTO) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        customer.setEmailId(customerDTO.getEmailId());
        customer.setName(customerDTO.getName());
        customer.setCustomerType(customerDTO.getCustomerType());
    }

    @Override
    public void deleteCustomer(Integer customerId) throws HnDBankException {
        Optional<Customer> optional = customerRespository.findById(customerId);
        optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
        customerRespository.deleteById(customerId);
    }

//    @Override
//    public void findBy(String emailId, int custId) throws HnDBankException {
//        List<Customer> custlist = customerRespository.findByEmailId(emailId);
//        System.out.println(custlist);
//        List<String> name = customerRespository.findNameByEmailId(emailId);
//        System.out.println(name);
//
//        customerRespository.updateCustomerEmailId("h1234@gmail.com", custId);
//
////        optional.orElseThrow(() -> new HnDBankException("Service.CUSTOMER_NOT_FOUND"));
//
//    }


}
