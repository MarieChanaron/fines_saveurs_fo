package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Customer c set c.email = ?1, c.firstname = ?2, c.lastname = ?3, c.password = ?4 where c.id = ?5")
    int updateCustomer(String email, String firstname, String lastname, String password, long id);
}
