package com.empresa.Service.Inter;

import com.empresa.Entities.Sale;

import java.util.List;
import java.util.Optional;

public interface ISaleService {
    List<Sale> findAll();
    Optional<Sale> findById(Long id);
    Sale save(Sale sale);
    void deleteById(Long id);
}
