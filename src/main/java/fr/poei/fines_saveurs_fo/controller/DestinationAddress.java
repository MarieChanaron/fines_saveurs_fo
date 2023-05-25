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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/destination-address")
public class DestinationAddress {

    final MapStructMapper mapStructMapper;
    final CustomerService customerService;
    final AddressService addressService;

    @GetMapping
    public String getDestinationAddress(HttpSession session, Model model) {
        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        Address address = null;

        if (customerOptional.isEmpty()) return "404";

        Customer customer = customerOptional.get();
        Optional<Address> addressOptional = addressService.getAddress(customer, "destination");

        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            address = new Address();
        }

        model.addAttribute("address", address);

        return "destination-address";
    }

    @PostMapping
    public String setDestinationAddress(HttpSession session) {
        return "";
    }
}
