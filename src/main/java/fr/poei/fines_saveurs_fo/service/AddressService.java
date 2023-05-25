package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.repository.AddressCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    final AddressCustomerRepository addressCustomerRepository;

    public Optional<Address> getAddress(Customer customer, String addressType) {

        return null;
    }
}
