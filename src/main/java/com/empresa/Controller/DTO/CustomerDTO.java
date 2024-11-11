package com.empresa.Controller.DTO;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
