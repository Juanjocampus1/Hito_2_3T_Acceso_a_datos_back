package com.empresa.Service.Inter;

import com.empresa.Entities.SaleItem;

import java.util.List;
import java.util.Optional;

public interface ISaleItemService {
    List<SaleItem> findAll();
    Optional<SaleItem> findById(Long id);
    SaleItem save(SaleItem saleItem);
    void deleteById(Long id);
}
