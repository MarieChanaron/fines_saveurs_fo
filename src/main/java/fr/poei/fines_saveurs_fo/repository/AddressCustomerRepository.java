package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.AddressCustomer;
import fr.poei.fines_saveurs_fo.entity.AddressCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCustomerRepository extends JpaRepository<AddressCustomer, AddressCustomerId> {
}
