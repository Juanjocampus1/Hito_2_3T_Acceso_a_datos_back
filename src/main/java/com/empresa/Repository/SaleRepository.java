package com.empresa.Repository;

import com.empresa.Entities.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {
    List<Sale> findAll();
    Optional<Sale> findById(Long id);
    Sale save(Sale sale);
    void deleteById(Long id);
}
