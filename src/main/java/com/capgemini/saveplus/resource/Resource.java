package com.capgemini.saveplus.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class Resource {

    @PreAuthorize("hasAnyRole('DOCTOR','VOLENTEER', 'ADMIN', 'HPERSONAL')")
    @GetMapping("")
    public String hello() {
        return "hello Users";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin")
    public String adminHello() {
        return "Hello from Admin";
    }

}
