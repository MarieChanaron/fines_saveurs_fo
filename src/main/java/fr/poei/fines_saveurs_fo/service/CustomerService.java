package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoleService roleService;

    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> fetchById(long id) {
        return customerRepository.findById(id);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> fetchByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer registerNewCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        List<Role> roles = new ArrayList<>();
        Optional<Role> role = roleService.findByName("CUSTOMER");
        role.ifPresent(roles::add);
        customer.setRoleList(roles);
        return  customerRepository.save(customer);
    }
}
