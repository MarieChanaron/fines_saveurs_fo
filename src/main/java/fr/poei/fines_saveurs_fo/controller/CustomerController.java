package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.CartDto;
import fr.poei.fines_saveurs_fo.controller.dto.CustomerDto;
import fr.poei.fines_saveurs_fo.controller.dto.MapStructMapper;
import fr.poei.fines_saveurs_fo.controller.dto.OrderDto;
import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.AddressService;
import fr.poei.fines_saveurs_fo.service.CustomerService;
import fr.poei.fines_saveurs_fo.service.OrderService;
import fr.poei.fines_saveurs_fo.service.RoleService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@AllArgsConstructor
@Controller
@RequestMapping("/customers")
public class CustomerController {

    CustomerService customerService;
    AddressService addressService;
    OrderService orderService;
    RoleService roleService;
    MapStructMapper mapStructMapper;

    // ---------------Récupérer et afficher les données du client connecté-------------
    @GetMapping
    public String customer(Model model, HttpSession session) {

        // Retrieve customer
        String email = (String) session.getAttribute("email");
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();

        // Get customer information
        CustomerDto customerDto = mapStructMapper.toDto(customer);
        model.addAttribute("customer", customerDto);

        // Get addresses
        Address destinationAddress = addressService.getDestinationAddress(customer);
        model.addAttribute("delivery", destinationAddress);

        Address invoicingAddress = addressService.getInvoicingAddress(customer);
        model.addAttribute("invoicing", invoicingAddress);

        // Get orders
        List<Order> orders = orderService.fetchOrdersByCustomer(customer);
        List<OrderDto> orderDtoList = new ArrayList<>();
        orders.stream().forEach(order -> {
            OrderDto orderDto = mapStructMapper.toDto(order);
            CartDto cartDto = mapStructMapper.toDto(order.getCart());
            orderDto.setCartDto(cartDto);
            orderDtoList.add(orderDto);
        });
        model.addAttribute("orders", orderDtoList);

        return "profile";
    }

    // ----------------------------Modifier les données du client----------------
    @GetMapping("/edit-profile")
    public String editGet(Model model, @RequestParam long id) {
        Optional<Customer> customerOptional = customerService.fetchById(id);
        if (customerOptional.isEmpty()) return "404";
        Customer customer = customerOptional.get();
        customer.setPassword(null);
        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("customer") Customer customer, Model model, HttpSession session) {

        System.out.println(customer);

        String password = customer.getPassword();
        String confirmation = customer.getPasswordConfirmation();
        if (!password.equals(confirmation)) {
            model.addAttribute("passwordError", true);
            return "edit-customer";
        }

        /*Customer connectedCustomer = customerService.fetchByEmail(String.valueOf(session.getAttribute("email"))).get();
        connectedCustomer.setFirstname(customer.getFirstname());
        connectedCustomer.setLastname(customer.getLastname());
        customerService.save(connectedCustomer);*/

        //model.addAttribute("customer", connectedCustomer);
        return "redirect:/customers";
    }


}
