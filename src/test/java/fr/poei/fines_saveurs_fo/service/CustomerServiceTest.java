package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService underTest;

    @Mock
    private BCryptPasswordEncoder passwordEncoderMock;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @Mock
    private RoleService roleServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void ShouldReturnAllCustomers() {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer());
        expectedCustomers.add(new Customer());

        when(customerRepositoryMock.findAll()).thenReturn(expectedCustomers);

        List<Customer> result = underTest.fetchAll();

        assertEquals(expectedCustomers, result);
    } // fetchAll

    @Test
    public void ShouldReturnCustomerGivenId() {
        int customerId = 1;
        Optional<Customer> expectedCustomer = Optional.of(new Customer());
        expectedCustomer.get().setId(customerId);

        when(customerRepositoryMock.findById((long) customerId)).thenReturn(expectedCustomer);

        Optional<Customer> result = underTest.fetchById(customerId);

        assertEquals(expectedCustomer, result);
    } // fetchById

    @Test
    public void ShouldSaveCustomer() {
        Customer customer = new Customer();

        underTest.save(customer);

        Mockito.verify(customerRepositoryMock, times(1)).save(customer);
    } // save

    @Test
    public void ShouldReturnCustomerGivenEmail() {
        String email = "test@example.com";
        Optional<Customer> expectedCustomer = Optional.of(new Customer());
        expectedCustomer.get().setEmail(email);

        when(customerRepositoryMock.findByEmail(email)).thenReturn(expectedCustomer);

        Optional<Customer> result = underTest.fetchByEmail(email);

        assertEquals(expectedCustomer, result);
    } // fetchByEmail

     // TODO: registerNewCustomer
     // TODO: Test OrderService.java


}