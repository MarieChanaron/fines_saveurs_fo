package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomerDetailsServiceTest {

    @InjectMocks
    private CustomerDetailsService underTest;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void ShouldLoadUserByUsername() {
        String email = "test@example.com";
        Customer customer = new Customer();
        customer.setEmail(email);

        when(customerRepositoryMock.findByEmail(email)).thenReturn(Optional.of(customer));

        UserDetails userDetails = underTest.loadUserByUsername(email);

        assertEquals(customer.getEmail(), userDetails.getUsername());
    } // loadUserByUsername

    @Test
    public void ShouldThrowUsernameNotFoundException() {
        String email = "nonexistent@example.com";

        when(customerRepositoryMock.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername(email));
    } // loadUserByUsername
}