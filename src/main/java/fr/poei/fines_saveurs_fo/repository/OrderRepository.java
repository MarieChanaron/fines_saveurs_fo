package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.Customer;
import fr.poei.fines_saveurs_fo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findAllByCustomer(Customer customer);
}
