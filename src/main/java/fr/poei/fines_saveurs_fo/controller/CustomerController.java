package fr.poei.fines_saveurs_fo.controller;

import fr.poei.fines_saveurs_fo.controller.dto.*;
import fr.poei.fines_saveurs_fo.entity.*;
import fr.poei.fines_saveurs_fo.entity.role.Role;
import fr.poei.fines_saveurs_fo.service.*;
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
    CartService cartService;
    MapStructMapper mapStructMapper;

    // ---------------Récupérer et afficher les données du client connecté-------------
    @GetMapping
    public String customer(Model model, HttpSession session, @RequestParam(required = false) Optional<Long> orderId) {

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

        // Get a specific cart order
        if (orderId.isPresent()) {
            long id = orderId.get();
            Optional<Cart> cartOptional = orderService.getOrderCart(id);
            if (cartOptional.isEmpty()) return "profile";
            Cart cart = cartOptional.get();

            List<CartProduct> cartProducts = cartService.findAllCartItems(cart);
            List<CartProductDto> cartProductDtoList = new ArrayList<>();
            int totalQuantity = 0;
            double totalPrice = 0;

            for (CartProduct item : cartProducts) {
                ProductDto productDto = mapStructMapper.toDto(item.getProduct());
                CartProductDto lineItem = mapStructMapper.toDto(item);
                totalQuantity += lineItem.getQuantity();
                totalPrice += productDto.getPrice().doubleValue() * lineItem.getQuantity();
                lineItem.setProductDto(productDto);
                cartProductDtoList.add(lineItem);
            }

            model.addAttribute("totalQuantity", totalQuantity);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("cart", cartProductDtoList);
        }

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

        String password = customer.getPassword();
        if (password == null || password.length() == 0) {
            model.addAttribute("passwordEmpty", true);
            return "edit-customer";
        }

        String confirmation = customer.getPasswordConfirmation();
        if (!password.equals(confirmation)) {
            model.addAttribute("passwordError", true);
            return "edit-customer";
        }

        String email = customer.getEmail();
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isPresent() && customerOptional.get().getId() != customer.getId()) {
            model.addAttribute("emailError", true);
            return "edit-customer";
        }

        customerService.updateCustomerDetails(customer);

        return "redirect:/customers";
    }

}
