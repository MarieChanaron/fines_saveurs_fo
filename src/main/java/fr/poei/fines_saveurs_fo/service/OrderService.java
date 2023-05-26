package fr.poei.fines_saveurs_fo.service;

import fr.poei.fines_saveurs_fo.entity.Cart;
import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Order;
import fr.poei.fines_saveurs_fo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    final CustomerService customerService;
    final OrderRepository orderRepository;

    public Optional<Order> saveOrder(Cart cart, String email) {
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setCart(cart);
        Optional<Customer> customerOptional = customerService.fetchByEmail(email);
        if (customerOptional.isEmpty()) return Optional.of(new Order());
        order.setCustomer(customerOptional.get());
        return Optional.of(orderRepository.save(order));
    }
}
