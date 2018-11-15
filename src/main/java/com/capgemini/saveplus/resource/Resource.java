package com.capgemini.saveplus.resource;

import com.capgemini.saveplus.model.User;
import com.capgemini.saveplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/")
@RestController
public class Resource {

    @Autowired
    UserRepository userRepository;

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

    @PostMapping(value = "/post")
    public ResponseEntity<?> update(@RequestBody User user, Integer id) {
        if (!userRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/user")
    @ResponseBody
    public void addingUser(@RequestBody User user){

        user.getName();
        user.getId();
        user.getFirstName();
        user.getLastName();
        user.getPassword();
        user.getRoles();
        user.getEmail();
        userRepository.save(user);
    }


}
