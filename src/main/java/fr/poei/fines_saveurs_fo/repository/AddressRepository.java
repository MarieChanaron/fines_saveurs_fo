package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.Address;
import fr.poei.fines_saveurs_fo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Transactional
    @Query("update Address a set a.additionalInformation = ?1, a.city = ?2, a.postcode = ?3, a.street = ?4, a.streetNumber = ?5 where a.id = ?6")
    int updateAddress(String additionalInformation, String city, String postcode, String street, String streetNumber, long id);
}
