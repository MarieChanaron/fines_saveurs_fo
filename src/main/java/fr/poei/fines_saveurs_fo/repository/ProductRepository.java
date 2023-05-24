package fr.poei.fines_saveurs_fo.repository;

import fr.poei.fines_saveurs_fo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query("update Product p set p.stock = ?1 where p.id = ?2")
    int updateStock(int stock, int id);

    @Query("SELECT p FROM Product p WHERE CONCAT( p.name, ' ', p.description, ' ',  p.brand, ' ',  p.reference, ' ',  p.category.name) LIKE %?1%")
    public List<Product> search(String keyword);

}
