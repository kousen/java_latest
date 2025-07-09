package com.kousenit.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OptionalTest {
    private CustomerDAO dao;

    @BeforeEach
    void setUp() {
        dao = new CustomerDAO();
    }

    @Test
    @DisplayName("Default customer has correct name")
    void defaultCustomerHasCorrectName() {
        assertEquals("Default", Customer.DEFAULT.getName());
    }
    
    @Test
    @DisplayName("DAO returns default customer for non-existent ID")
    void daoReturnsDefaultForNonExistentId() {
        Optional<Customer> optional = dao.findById(999);
        assertTrue(optional.isPresent());
        assertEquals("Default", optional.get().getName());
    }
    
    @Test
    @DisplayName("Find by local ID returns empty optional for non-existent ID")
    void findByIdLocalReturnsEmpty() {
        Optional<Customer> customer = dao.findByIdLocal(999);
        assertTrue(customer.isEmpty());
    }
    
    @Test
    @DisplayName("Find by remote ID always returns empty optional")
    void findByIdRemoteReturnsEmpty() {
        Optional<Customer> customer = dao.findByIdRemote(0);
        assertTrue(customer.isEmpty());
    }
    
    @Test
    @DisplayName("Test orElse method")
    void testOrElse() {
        Customer customer = dao.findByIdLocal(999).orElse(Customer.DEFAULT);
        assertEquals("Default", customer.getName());
    }
    
    @Test
    @DisplayName("Test or method chaining")
    void testOrChaining() {
        Optional<Customer> optional = dao.findByIdLocal(999)
                .or(() -> dao.findByIdRemote(999))
                .or(() -> Optional.of(Customer.DEFAULT));
        
        assertTrue(optional.isPresent());
        assertEquals("Default", optional.get().getName());
    }
    
    @Test
    @DisplayName("Collection contains expected customer names")
    void collectionContainsExpectedCustomerNames() {
        Collection<String> names = dao.findAllById(0, 1, 2, 999)
                .stream()
                .map(Customer::getName)
                .toList();
        
        names.forEach(System.out::println);
        
        assertEquals(4, names.size());
        for (String name : names) {
            assertTrue(
                name.equals("Londo") || 
                name.equals("G'Kar") || 
                name.equals("Delenn") || 
                name.equals("Default"),
                "Collection should contain only the expected names but found: " + name
            );
        }
    }
    
    @Test
    @DisplayName("Find all by id better returns expected customers")
    void findAllByIdBetterReturnsExpectedCustomers() {
        Collection<String> names = dao.findAllByIdBetter(0, 1, 2, 999)
                .stream()
                .map(Customer::getName)
                .toList();
        
        names.forEach(System.out::println);
        
        assertEquals(4, names.size());
        for (String name : names) {
            assertTrue(
                name.equals("Londo") || 
                name.equals("G'Kar") || 
                name.equals("Delenn") || 
                name.equals("Default"),
                "Collection should contain only the expected names but found: " + name
            );
        }
    }
}