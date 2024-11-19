package com.empresa.Controller;

import com.empresa.Controller.DTO.CustomerDTO;
import com.empresa.Entities.Customer;
import com.empresa.Service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerServiceImpl.findById(id);

        if (customerOptional.isPresent()){
            Customer customer = customerOptional.get();

            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .phone(customer.getPhone())
                    .address(customer.getAddress())
                    .build();
            return ResponseEntity.ok(customerDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<CustomerDTO> customerlist = customerServiceImpl.findAll()
                .stream()
                .map(customer -> CustomerDTO.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .phone(customer.getPhone())
                        .address(customer.getAddress())
                        .build())
                .toList();

        return ResponseEntity.ok(customerlist);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .address(customerDTO.getAddress())
                .build();

        return ResponseEntity.ok(customerServiceImpl.save(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Customer> customerOptional = customerServiceImpl.findById(id);

        if (customerOptional.isPresent()) {
            customerServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
