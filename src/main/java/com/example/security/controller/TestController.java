package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/role/view")
    @PreAuthorize("hasRole('ROLE_VIEW')")
    public String testViewRole() {
        return "test successful for view role";
    }

    @GetMapping("/test/role/update")
    @PreAuthorize("hasRole('ROLE_UPDATE')")
    public String testUpdateRole() {
        return "test successful for update role";
    }

}
