package com.kousenit.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class GroupByTasks {
    public static void main(String[] args) {
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

        List<Task> tasks = Arrays.asList(java, altJvm, javaScript, spring, jpa);

        // No filtering
        Map<Long, List<Task>> taskMap = tasks.stream()
                .collect(groupingBy(Task::getBudget));

        printMap("Complete map (no filtering)", taskMap);

        // Filter out low budget tasks
        taskMap = tasks.stream()
                .filter(task -> task.getBudget() > 25)
                .collect(groupingBy(Task::getBudget));

        printMap("Filter out low budget tasks", taskMap);

        // Filter low budget tasks, but still include them in output report
        taskMap = tasks.stream()
                .collect(groupingBy(Task::getBudget,
                        filtering(task -> task.getBudget() > 25, toList())));

        printMap("filtering in Collectors", taskMap);

        Map<String, List<Task>> tasksByName = tasks.stream()
                .collect(groupingBy(Task::getName));
        System.out.println();
        tasksByName.forEach((name, taskList) -> System.out.printf("%30s: %s%n", name, taskList));

        Map<String, Set<List<Developer>>> map = tasks.stream()
                .collect(groupingBy(Task::getName, Collectors.mapping(Task::getDevelopers, toSet())));

        System.out.println();
        map.forEach((name, setListDevs) -> System.out.printf("%30s: %s%n", name, setListDevs));

        Map<String, Set<Developer>> task2setdevs = tasks.stream()
                .collect(groupingBy(Task::getName,
                        Collectors.flatMapping(task -> task.getDevelopers().stream(), toSet())));
        System.out.println();
        task2setdevs.forEach((name, devSet) -> System.out.printf("%30s: %s%n", name, devSet));

    }

    private static void printMap(String title, Map<Long, List<Task>> taskMap) {
        System.out.println();
        System.out.println(title);
        taskMap.forEach((budget, taskList) ->
                System.out.printf("%3d: %s%n", budget, taskList));
    }
}
