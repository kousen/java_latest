package com.kousenit.virtualthreads;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CustomerService {

    public List<Customer> fetchCustomerData(List<Integer> customerIds) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<CompletableFuture<Customer>> futures = customerIds.stream()
                    .map(id -> CompletableFuture.supplyAsync(() -> fetchCustomer(id), executor))
                    .toList();

            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .toList())
                    .join();
        }
    }

    private Customer fetchCustomer(int id) {
        try {
            // Simulate API call or database query
            Thread.sleep(1000);
            return new Customer(id, "Customer " + id, "customer" + id + "@example.com");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while fetching customer " + id, e);
        }
    }

    // Customer as a record
    public record Customer(int id, String name, String email) {}

    // Main method for demonstration
    public static void main(String[] args) {
        CustomerService service = new CustomerService();
        List<Integer> customerIds = IntStream.rangeClosed(1, 5)
                .boxed()
                .toList();
        List<Customer> customers = service.fetchCustomerData(customerIds);
        customers.forEach(System.out::println);
    }
}
