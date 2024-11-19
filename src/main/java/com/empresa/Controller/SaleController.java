package com.empresa.Controller;

import com.empresa.Controller.DTO.SaleDTO;
import com.empresa.Entities.Customer;
import com.empresa.Entities.Sale;
import com.empresa.Service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = "http://localhost:3000")
public class SaleController {

    @Autowired
    private SaleServiceImpl saleServiceImpl;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Sale> saleOptional = saleServiceImpl.findById(id);

        if (saleOptional.isPresent()){
            Sale sale = saleOptional.get();

            SaleDTO saleDTO = SaleDTO.builder()
                    .id(sale.getId())
                    .date(sale.getDate())
                    .totalAmount(sale.getTotalAmount())
                    .customer(sale.getCustomer())
                    .saleItemslist(sale.getSaleItems())
                    .build();
            return ResponseEntity.ok(saleDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<SaleDTO> salelist = saleServiceImpl.findAll()
                .stream()
                .map(sale -> SaleDTO.builder()
                        .id(sale.getId())
                        .date(sale.getDate())
                        .totalAmount(sale.getTotalAmount())
                        .customer(sale.getCustomer())
                        .saleItemslist(sale.getSaleItems())
                        .build())
                .toList();

        return ResponseEntity.ok(salelist);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SaleDTO saleDTO) {
        Sale sale = Sale.builder()
                .id(saleDTO.getId())
                .date(saleDTO.getDate())
                .totalAmount(saleDTO.getTotalAmount())
                .customer(saleDTO.getCustomer())
                .saleItems(saleDTO.getSaleItemslist())
                .build();

        return ResponseEntity.ok(saleServiceImpl.save(sale));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody SaleDTO saleDTO, @PathVariable Long id) {
        Optional<Sale> saleOptional = saleServiceImpl.findById(id);

        if (saleOptional.isPresent()) {
            Sale sale = saleOptional.get();
            sale.setDate(saleDTO.getDate());
            sale.setTotalAmount(saleDTO.getTotalAmount());
            sale.setCustomer(saleDTO.getCustomer());
            sale.setSaleItems(saleDTO.getSaleItemslist());

            Sale updatedSale = saleServiceImpl.save(sale);
            SaleDTO updatedSaleDTO = SaleDTO.builder()
                    .id(updatedSale.getId())
                    .date(updatedSale.getDate())
                    .totalAmount(updatedSale.getTotalAmount())
                    .customer(updatedSale.getCustomer())
                    .saleItemslist(updatedSale.getSaleItems())
                    .build();

            return ResponseEntity.ok(updatedSaleDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Sale> saleOptional = saleServiceImpl.findById(id);

        if (saleOptional.isPresent()){
            saleServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/salesByCustomer/{customerId}")
    public ResponseEntity<?> getSalesByCustomer(@PathVariable Long customerId) {
        List<Sale> sales = saleServiceImpl.findAll();
        List<SaleDTO> salesByCustomer = sales.stream()
                .filter(sale -> sale.getCustomer().getId().equals(customerId))
                .map(sale -> SaleDTO.builder()
                        .id(sale.getId())
                        .date(sale.getDate())
                        .totalAmount(sale.getTotalAmount())
                        .customer(sale.getCustomer())
                        .saleItemslist(sale.getSaleItems())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(salesByCustomer);
    }
}