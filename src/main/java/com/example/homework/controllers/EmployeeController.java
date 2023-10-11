package com.example.homework.controllers;

import com.example.homework.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping(path = "/employee/add")
    public Object add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.add(firstName, lastName);
        }
        catch (RuntimeException e) {
            return e;
        }
    }

    @GetMapping(path = "/employee/remove")
    public Object remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.remove(firstName, lastName);
        }
        catch (RuntimeException e) {
            return e;
        }
    }

    @GetMapping(path = "/employee/find")
    public Object find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.find(firstName, lastName);
        }
        catch (RuntimeException e) {
            return e;
        }
    }

    @GetMapping(path = "/employee/list")
    public Object list() {
        return employeeService.list();
    }

}
