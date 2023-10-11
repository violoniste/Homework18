package com.example.homework.services;

import com.example.homework.exceptions.EmployeeAlreadyAddedException;
import com.example.homework.exceptions.EmployeeNotFoundException;
import com.example.homework.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Objects;

@org.springframework.stereotype.Service
public class EmployeeService {
    private static final int MAX = 5;
    private final ArrayList<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        if (employees.size() >= MAX)
            throw new EmployeeStorageIsFullException();

        for (Employee e: employees) {
            if (Objects.equals(e.getFirstName(), firstName) && e.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }

        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        for (Employee e: employees) {
            if (Objects.equals(e.getFirstName(), firstName) && e.getLastName().equals(lastName)) {
                employees.remove(e);
                return e;
            }
        }

        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        for (Employee e: employees) {
            if (Objects.equals(e.getFirstName(), firstName) && e.getLastName().equals(lastName)) {
                return e;
            }
        }

        throw new EmployeeNotFoundException();
    }

    public ArrayList<Employee> list() {
        return employees;
    }
}
