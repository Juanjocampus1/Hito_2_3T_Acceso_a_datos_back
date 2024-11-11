package com.empresa.Controller.DTO;

import com.empresa.Entities.Customer;
import com.empresa.Entities.SaleItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleDTO {

    private Long id;
    private Date date;
    private Double totalAmount;
    private Customer customer;
    private List<SaleItem> saleItemslist = new ArrayList<>();
}
