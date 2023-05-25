package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.AddressCustomer;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.repository.AddressCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    final AddressCustomerRepository addressCustomerRepository;
    final MapStructMapper mapStructMapper;

    public Address getDestinationAddress(Customer customer) {
        Optional<AddressCustomer> addressCustomer = addressCustomerRepository.findByCustomerAndType(customer, "destination");
        if (addressCustomer.isEmpty()) return new Address();
        return addressCustomer.get().getAddress();
    }

    public Address getInvoicingAddress(Customer customer) {
        Optional<AddressCustomer> addressCustomer = addressCustomerRepository.findByCustomerAndType(customer, "invoicing");
        if (addressCustomer.isEmpty()) return new Address();
        return addressCustomer.get().getAddress();
    }
}