package com.kousenit.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class GroupByDepartment {
    public static void main(String[] args) {
        Department it = new Department("IT");
        Department management = new Department("Management");
        Department sales = new Department("sales");
        List<Employee> employees = Arrays.asList(new Employee("Venkat", it),
                new Employee("Raju", it),
                new Employee("Matt", management),
                new Employee("Nate", it));

        Map<Department, List<Employee>> empMap = employees.stream()
                .collect(groupingBy(Employee::getDepartment));

        printEmpMap("No filtering", empMap);

    }

    private static void printEmpMap(String title, Map<Department, List<Employee>> map) {
        System.out.println();
        System.out.println(title);
        map.forEach((dept, empList) ->
                System.out.printf("%10s: %s%n", dept.getName(), empList));
    }
}
