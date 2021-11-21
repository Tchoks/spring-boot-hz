package com.tchokonthe.hz.resources;

import com.tchokonthe.hz.model.Customer;
import com.tchokonthe.hz.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author martin
 * @created on 12/11/2021 at 22:42
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerResources {

    private final CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    @PostMapping
    public void addCustomers(@RequestBody List<Customer> customers) {
        customerService.addCustomers(customers);
    }

    @GetMapping
    public Customer findCustomer(@RequestParam("name") String name) {
        return customerService.findCustomerByName(name);
    }

    @GetMapping("{profession:.+}")
    public List<Customer> findCustomers(@PathVariable("profession") String profession) {
        return customerService.findCustomersByProfession(profession);
    }

    @DeleteMapping
    public Customer deleteCustomer(@RequestParam("name") String name) {
        return customerService.deleteCustomer(name);
    }

}
