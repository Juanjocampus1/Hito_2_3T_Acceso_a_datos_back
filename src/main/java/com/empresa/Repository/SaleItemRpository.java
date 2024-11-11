package com.empresa.Repository;

import com.empresa.Entities.Customer;
import com.empresa.Entities.SaleItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleItemRpository extends CrudRepository<SaleItem, Long> {
    List<SaleItem> findAll();
    Optional<SaleItem> findById(Long id);
    SaleItem save(SaleItem saleItem);
    void deleteById(Long id);
}
