package com.empresa.Service.impl;

import com.empresa.Entities.SaleItem;
import com.empresa.Repository.SaleItemRpository;
import com.empresa.Service.Inter.ISaleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleItemServiceImpl implements ISaleItemService {

    @Autowired
    private SaleItemRpository saleItemRpository;

    @Override
    public List<SaleItem> findAll() {
        return saleItemRpository.findAll();
    }

    @Override
    public Optional<SaleItem> findById(Long id) {
        return saleItemRpository.findById(id);
    }

    @Override
    public SaleItem save(SaleItem saleItem) {
        return saleItemRpository.save(saleItem);
    }

    @Override
    public void deleteById(Long id) {
        saleItemRpository.deleteById(id);
    }
}
