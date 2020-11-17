package com.dxp.mangodb.handler;

import com.dxp.mangodb.dao.UserRepository;
import com.dxp.mangodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@RestController
@RequestMapping("mvc/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Flux<User> listAll(@RequestParam(required = false) String name) {
        if ("".equals(name) || name == null) {
            return this.userRepository.findAll();
        } else {
            return this.userRepository.findAllByNameLike(name);
        }
    }

    @GetMapping(path = "/{id}")
    public Mono<User> findOne(@PathVariable String id) {
        return this.userRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> save(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        return this.userRepository.save(user);
    }

    @PutMapping
    public Mono<User> update(@RequestBody User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("id not find");
        }

        Mono<User> userMono = this.userRepository.findById(user.getId());

        return userMono.flatMap(u -> this.userRepository.save(user))
                .switchIfEmpty(Mono.empty());
    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> remove(@PathVariable String id) {
        return this.userRepository.deleteById(id);
    }

}
