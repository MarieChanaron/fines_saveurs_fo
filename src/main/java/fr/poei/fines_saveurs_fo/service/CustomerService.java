package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public List <Customer> fetchAll() {
        return customerRepository.findAll();
    }
    public Customer fetchById (int id) {
        return customerRepository.findById(id).get();
    }
    public void save (Customer customer) {
        customerRepository.save(customer);
    }
}
