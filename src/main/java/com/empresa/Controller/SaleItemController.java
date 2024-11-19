package com.empresa.Controller;

import com.empresa.Controller.DTO.SaleItemDTO;
import com.empresa.Entities.SaleItem;
import com.empresa.Service.impl.SaleItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/saleItem")
@CrossOrigin(origins = "http://localhost:3000")
public class SaleItemController {

    @Autowired
    private SaleItemServiceImpl saleItemServiceImpl;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<SaleItem> saleItemOptional = saleItemServiceImpl.findById(id);

        if (saleItemOptional.isPresent()){
            SaleItem saleItem = saleItemOptional.get();

            SaleItemDTO saleItemDTO = SaleItemDTO.builder()
                    .id(saleItem.getId())
                    .quantity(saleItem.getQuantity())
                    .price(saleItem.getPrice())
                    .book(saleItem.getBook())
                    .sale(saleItem.getSale())
                    .build();
            return ResponseEntity.ok(saleItemDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<SaleItemDTO> saleItemlist = saleItemServiceImpl.findAll()
                .stream()
                .map(saleItem -> SaleItemDTO.builder()
                        .id(saleItem.getId())
                        .quantity(saleItem.getQuantity())
                        .price(saleItem.getPrice())
                        .book(saleItem.getBook())
                        .sale(saleItem.getSale())
                        .build())
                .toList();

        return ResponseEntity.ok(saleItemlist);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SaleItemDTO saleItemDTO) {
        SaleItem saleItem = SaleItem.builder()
                .id(saleItemDTO.getId())
                .quantity(saleItemDTO.getQuantity())
                .price(saleItemDTO.getPrice())
                .book(saleItemDTO.getBook())
                .sale(saleItemDTO.getSale())
                .build();

        SaleItem saleItemSaved = saleItemServiceImpl.save(saleItem);

        if (saleItemSaved != null){
            SaleItemDTO saleItemDTOSaved = SaleItemDTO.builder()
                    .id(saleItemSaved.getId())
                    .quantity(saleItemSaved.getQuantity())
                    .price(saleItemSaved.getPrice())
                    .book(saleItemSaved.getBook())
                    .sale(saleItemSaved.getSale())
                    .build();
            return ResponseEntity.ok(saleItemDTOSaved);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<SaleItem> saleItemOptional = saleItemServiceImpl.findById(id);

        if (saleItemOptional.isPresent()){
            saleItemServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
