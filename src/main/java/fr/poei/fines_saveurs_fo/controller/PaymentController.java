package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.service.AddressService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.validator.CreditCard;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@AllArgsConstructor
@RequestMapping("/order/payment")
public class PaymentController {

    final CustomerService customerService;
    final AddressService addressService;


    @GetMapping
    public String payment(HttpSession session, Model model) {

        // Check that the order has been confirmed by clicking on the button
        if (session.getAttribute("orderConfirmed") == null) {
            return "redirect:/order";
        }

        Optional<Customer> customerOptional = customerService.fetchByEmail(
                (String) session.getAttribute("email")
        );
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        // Check that we have a destination address
        Address destinationAddress = addressService.getDestinationAddress(customer);
        if (destinationAddress.getId() == 0) {
            return "redirect:/order/destination-address";
        }

        // Check that we have an invoicing address
        Address invoicingAddress = addressService.getInvoicingAddress(customer);
        if (invoicingAddress.getId() == 0) {
            return "redirect:/order/invoicing-address";
        }

        model.addAttribute("creditCard", new CreditCard());

        return "payment";
    }


    @PostMapping
    public String checkPaymentMethod(@ModelAttribute("creditCard") @Valid CreditCard creditCard, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("paymentError", true);
            return "payment";
        }

        return "redirect:/order/confirmation";
    }


}
