package com.example.homework.services;

import com.example.homework.exceptions.EmployeeAlreadyAddedException;
import com.example.homework.exceptions.EmployeeNotFoundException;
import com.example.homework.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@org.springframework.stereotype.Service
public class EmployeeService {
    private static final int MAX = 5;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX)
            throw new EmployeeStorageIsFullException();

        if (find(firstName, lastName) != null)
            throw new EmployeeAlreadyAddedException();

        Employee employee = new Employee(firstName, lastName);
        employees.put(getKey(firstName, lastName), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);
        if (employee == null)
            throw new EmployeeNotFoundException();

        employees.remove(getKey(firstName, lastName));
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null)
            throw new EmployeeNotFoundException();

        return employee;
    }

    public List<Employee> list() {
        Set<String> keys = employees.keySet();
        List<Employee> list = new ArrayList<>();
        for (String k : keys)
            list.add(employees.get(k));

        return list;
    }

    private String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
}
