package com.kousenit.optional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomerDAO {
    private final Map<Integer, Customer> map;

    public CustomerDAO() {
        List<Customer> customers = Arrays.asList(
                new Customer("Londo"),
                new Customer("G'Kar"),
                new Customer("Delenn"),
                new Customer("Lennier"),
                new Customer("Kosh"),
                new Customer("Vir"),
                new Customer("Zathras"));

        map = customers.stream()
                .collect(Collectors.toMap(Customer::getId, Function.identity()));
    }

    public Optional<Customer> findById(int id) {
        return findByIdLocal(id)
                .or(() -> findByIdRemote(id))
                .or(() -> Optional.of(Customer.DEFAULT));
    }

    public Collection<Customer> findAllById(Integer... ids) {
        return Arrays.stream(ids)
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public Collection<Customer> findAllByIdBetter(Integer... ids) {
        return Arrays.stream(ids)
                .map(this::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    public Optional<Customer> findByIdRemote(int id) {
        return Optional.empty();
    }

    public Optional<Customer> findByIdLocal(int id) {
        return Optional.ofNullable(map.get(id));
    }

    public void printCustomer(Integer id) {
        findByIdLocal(id).ifPresent(System.out::println);
    }

    public void printCustomerWithDefault(Integer id) {
        findByIdLocal(id).ifPresentOrElse(System.out::println,
                () -> System.out.println("Customer with id=" + id + " not found"));
    }

    public void printCustomers(Integer... ids) {
        Arrays.asList(ids)
                .forEach(this::printCustomer);
    }

    public void printCustomersWithDefaults(Integer... ids) {
        Arrays.asList(ids)
                .forEach(this::printCustomerWithDefault);
    }
}
