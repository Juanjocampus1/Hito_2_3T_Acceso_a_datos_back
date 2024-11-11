package com.empresa.Controller.DTO;

import com.empresa.Entities.Book;
import com.empresa.Entities.Sale;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleItemDTO {

    private Long id;
    private Integer quantity;
    private Double price;
    private Book book;
    private Sale sale;
}
