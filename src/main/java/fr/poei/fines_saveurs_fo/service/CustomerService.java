package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    BCryptPasswordEncoder passwordEncoder;
    CustomerRepository customerRepository;
    RoleService roleService;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> fetchAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> fetchById(long id) {
        return customerRepository.findById(id);
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

    public void updateCustomerDetails(Customer customer) {
        String email = customer.getEmail();
        String firstname = customer.getFirstname();
        String lastname = customer.getLastname();
        String password = passwordEncoder.encode(customer.getPassword());
        long id = customer.getId();
        customerRepository.updateCustomer(email, firstname, lastname, password, id);
    }
}
