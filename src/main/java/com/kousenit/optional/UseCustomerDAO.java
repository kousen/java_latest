package com.kousenit.optional;

import java.util.Optional;

public class UseCustomerDAO {
    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();

        System.out.println("Using Optional::isPresent and Optional::get");
        dao.findAllById(1, 2, 4, 7, 11)
                .forEach(System.out::println);

        System.out.println("Using Optional::stream");
        dao.findAllByIdBetter(1, 2, 4, 7, 11)
                .forEach(System.out::println);

        System.out.println("Using Optional::or");
        Optional<Customer> optional = dao.findById(99);
        Customer customer = optional.orElse(Customer.DEFAULT);
        System.out.println(customer);

        Optional<Customer> opt = dao.findById(99);
        System.out.println(opt);

        System.out.println("Using Optional::ifPresentOrElse");
        dao.printCustomers(1, 2, 4, 7, 11);
        dao.printCustomersWithDefaults(1, 2, 4, 7, 11);
    }
}
