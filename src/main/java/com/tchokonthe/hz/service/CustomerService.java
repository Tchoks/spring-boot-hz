package com.tchokonthe.hz.service;

import com.hazelcast.map.IMap;
import com.hazelcast.query.Predicates;
import com.tchokonthe.hz.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author martin
 * @created on 12/11/2021 at 22:30
 * @project com.tchokonthe.hz
 * @email (martin.aurele12 @ gmail.com)
 */

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final IMap<String, Customer> customerMap;

    @PostConstruct
    public void init() {
        final List<Customer> customers = List.of(new Customer("Martin", "Kondengui", "SW Dev"), new Customer("Emma", "Kondengui", "Doctor"));
        customerMap.putAll(customers.stream().collect(Collectors.toMap(Customer::getName, Function.identity())));
    }

    public List<Customer> findAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    public Customer findCustomerByName(String name) {
        return customerMap.get(name);
    }

    public List<Customer> findCustomersByProfession(String profession) {
        return new ArrayList<>(customerMap.values(Predicates.equal("profession", profession)));
    }

    public void addCustomers(List<Customer> customers) {
        customerMap.putAll(customers.stream().collect(Collectors.toMap(Customer::getName, Function.identity())));
    }

    public Customer deleteCustomer(String name) {
        return customerMap.remove(name);
    }

    public void deleteCustomerByCriteria(String profession) {
        customerMap.removeAll(Predicates.equal("profession", profession));
    }

}
