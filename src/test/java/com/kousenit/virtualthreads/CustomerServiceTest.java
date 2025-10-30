package com.kousenit.virtualthreads;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static com.kousenit.virtualthreads.CustomerService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {

    @Test
    void testFetchCustomerData() {
        CustomerService service = new CustomerService();
        List<Integer> customerIds = IntStream.rangeClosed(1, 500)
                .boxed()
                .toList();

        long startTime = System.currentTimeMillis();
        var customers = service.fetchCustomerData(customerIds);
        long endTime = System.currentTimeMillis();

        // Check if we got the correct number of customers
        assertEquals(500, customers.size(), "Should fetch 500 customers");

        // Verify first and last customer data
        Customer firstCustomer = customers.getFirst();
        Customer lastCustomer = customers.getLast();

        assertEquals(1, firstCustomer.id(), "First customer ID should be 1");
        assertEquals("Customer 1", firstCustomer.name(), "First customer name should match");
        assertEquals("customer1@example.com", firstCustomer.email(), "First customer email should match");

        assertEquals(500, lastCustomer.id(), "Last customer ID should be 500");
        assertEquals("Customer 500", lastCustomer.name(), "Last customer name should match");
        assertEquals("customer500@example.com", lastCustomer.email(), "Last customer email should match");

        // Check if the execution time is close to 1 second (allowing some margin)
        long executionTime = endTime - startTime;
        assertTrue(executionTime >= 1000 && executionTime < 1500,
                "Execution time should be close to 1 second, but was " + executionTime + " ms");
        System.out.println("Execution time: " + executionTime + " ms");
    }

    @Test
    void testMainMethodExecutes() {
        // Test the main method that demonstrates virtual threads with customer fetching
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() ->
                CustomerService.main(new String[]{})
        );
    }
}
