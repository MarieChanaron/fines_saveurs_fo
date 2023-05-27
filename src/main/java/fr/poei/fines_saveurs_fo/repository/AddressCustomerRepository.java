package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.AddressCustomer;
import fr.poei.fines_saveurs_fo.entity.AddressCustomerId;
import fr.poei.fines_saveurs_fo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressCustomerRepository extends JpaRepository<AddressCustomer, AddressCustomerId> {

    Optional<AddressCustomer> findByCustomerAndType(Customer customer, String type);
}
