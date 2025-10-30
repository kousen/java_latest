package com.kousenit.collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class CollectorsTest {
    private Department it;
    private Department management;
    private Department sales;
    private List<Employee> employees;

    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        // Set up employees and departments
        it = new Department("IT");
        management = new Department("Management");
        sales = new Department("Sales");
        
        employees = Arrays.asList(
                new Employee("Venkat", it),
                new Employee("Raju", it),
                new Employee("Matt", management),
                new Employee("Nate", it));
        
        // Set up developers and tasks
        Developer venkat = new Developer("Venkat");
        Developer daniel = new Developer("Daniel");
        Developer brian = new Developer("Brian");
        Developer matt = new Developer("Matt");
        Developer nate = new Developer("Nate");
        Developer craig = new Developer("Craig");
        Developer ken = new Developer("Ken");

        Task java = new Task("Java stuff", 100);
        Task altJvm = new Task("Groovy/Kotlin/Scala/Clojure", 50);
        Task javaScript = new Task("JavaScript (sorry)", 100);
        Task spring = new Task("Spring", 50);
        Task jpa = new Task("JPA/Hibernate", 20);
        
        java.addDevelopers(venkat, daniel, brian, ken);
        javaScript.addDevelopers(venkat, nate);
        spring.addDevelopers(craig, matt, nate, ken);
        altJvm.addDevelopers(venkat, daniel, ken);
        
        tasks = Arrays.asList(java, altJvm, javaScript, spring, jpa);
    }

    @Test
    @DisplayName("Group employees by department")
    void groupEmployeesByDepartment() {
        Map<Department, List<Employee>> empMap = employees.stream()
                .collect(groupingBy(Employee::getDepartment));
        
        assertAll(
                () -> assertEquals(2, empMap.size()),
                () -> assertEquals(3, empMap.get(it).size()),
                () -> assertEquals(1, empMap.get(management).size()),
                () -> assertNull(empMap.get(sales))
        );
    }
    
    @Test
    @DisplayName("Group tasks by budget")
    void groupTasksByBudget() {
        Map<Long, List<Task>> taskMap = tasks.stream()
                .collect(groupingBy(Task::getBudget));
        
        assertAll(
                () -> assertEquals(3, taskMap.size()),
                () -> assertEquals(2, taskMap.get(100L).size()),
                () -> assertEquals(2, taskMap.get(50L).size()),
                () -> assertEquals(1, taskMap.get(20L).size())
        );
    }
    
    @Test
    @DisplayName("Filter tasks by budget threshold")
    void filterTasksByBudget() {
        Map<Long, List<Task>> taskMap = tasks.stream()
                .filter(task -> task.getBudget() > 25)
                .collect(groupingBy(Task::getBudget));
        
        assertAll(
                () -> assertEquals(2, taskMap.size()),
                () -> assertTrue(taskMap.containsKey(100L)),
                () -> assertTrue(taskMap.containsKey(50L)),
                () -> assertFalse(taskMap.containsKey(20L))
        );
    }
    
    @Test
    @DisplayName("Filter using Collectors.filtering")
    void filterUsingCollectorsFiltering() {
        Map<Long, List<Task>> taskMap = tasks.stream()
                .collect(groupingBy(Task::getBudget,
                        filtering(task -> task.getBudget() > 25, toList())));
        
        assertAll(
                () -> assertEquals(3, taskMap.size()),
                () -> assertEquals(2, taskMap.get(100L).size()),
                () -> assertEquals(2, taskMap.get(50L).size()),
                () -> assertEquals(0, taskMap.get(20L).size())
        );
    }
    
    @Test
    @DisplayName("Group tasks by name")
    void groupTasksByName() {
        Map<String, List<Task>> tasksByName = tasks.stream()
                .collect(groupingBy(Task::getName));
        
        assertAll(
                () -> assertEquals(5, tasksByName.size()),
                () -> assertEquals(1, tasksByName.get("Java stuff").size()),
                () -> assertEquals(1, tasksByName.get("Spring").size())
        );
    }
    
    @Test
    @DisplayName("Mapping developers to tasks")
    void mapDevelopersToTasks() {
        Map<String, Set<Developer>> taskToDevs = tasks.stream()
                .collect(groupingBy(Task::getName,
                        flatMapping(task -> task.getDevelopers().stream(), toSet())));

        assertAll(
                () -> assertEquals(5, taskToDevs.size()),
                () -> assertEquals(4, taskToDevs.get("Java stuff").size()),
                () -> assertEquals(2, taskToDevs.get("JavaScript (sorry)").size()),
                () -> assertEquals(4, taskToDevs.get("Spring").size()),
                () -> assertEquals(3, taskToDevs.get("Groovy/Kotlin/Scala/Clojure").size()),
                () -> assertEquals(0, taskToDevs.get("JPA/Hibernate").size())
        );
    }

    @Test
    @DisplayName("GroupByDepartment demo executes without errors")
    void testGroupByDepartmentDemo() {
        // Test the demo main method for code coverage
        assertDoesNotThrow(() -> GroupByDepartment.main(new String[]{}));
    }

    @Test
    @DisplayName("GroupByTasks demo executes without errors")
    void testGroupByTasksDemo() {
        // Test the demo main method for code coverage
        // This demo showcases filtering, mapping, and flatMapping collectors
        assertDoesNotThrow(() -> GroupByTasks.main(new String[]{}));
    }
}