package com.kousenit.collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task {
    private String name;
    private long budget;
    private List<Developer> developers = new ArrayList<>();

    public Task(String name, long budget) {
        this.name = name;
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void addDevelopers(Developer... devs) {
        developers = Arrays.stream(devs)
                .collect(Collectors.toList());
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    @Override
    public String toString() {
        return name;
    }
}
