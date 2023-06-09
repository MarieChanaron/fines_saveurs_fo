package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.service.AddressService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/customers/destination-address")
public class AccountDestinationAddress {

    final MapStructMapper mapStructMapper;
    final CustomerService customerService;
    final AddressService addressService;

    @GetMapping
    public String getDestinationAddress(HttpSession session, Model model) {

        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        Address destinationAddress = addressService.getDestinationAddress(customer);
        model.addAttribute("address", destinationAddress);

        return "account-destination-address";
    }

    @PostMapping
    public String setDestinationAddress(HttpSession session, @ModelAttribute Address address) {
        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        long addressId = addressService.getDestinationAddress(customer).getId();
        if (addressId != 0) {
            address.setId(addressId);
            addressService.updateAddress(address); // Update address
        } else {
            addressService.saveCustomerAddress(address, customer, "destination");
        }

        return "redirect:/customers";
    }

}
