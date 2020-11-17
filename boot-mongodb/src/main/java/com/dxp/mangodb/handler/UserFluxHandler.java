package com.dxp.mangodb.handler;

import com.dxp.mangodb.dao.UserRepository;
import com.dxp.mangodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserFluxHandler {

    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> listAll(@NonNull ServerRequest request) {
        Optional<String> name = request.queryParam("name");
        return name.map(s -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.userRepository.findAllByNameLike(s), User.class))
                .orElseGet(() -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.userRepository.findAll(), User.class));
    }

    public Mono<ServerResponse> findOne(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.userRepository.findById(id), User.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<User> customer = request.bodyToMono(User.class);

        Mono<User> next = customer.doOnNext(user -> {
            user.setId(UUID.randomUUID().toString());
            this.userRepository.save(user);
        });

        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(next, User.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<User> customer = request.bodyToMono(User.class);

        Mono<User> next = customer.doOnNext(user -> {
            user.setId(UUID.randomUUID().toString());
            this.userRepository.save(user);
        });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(next, User.class);
    }

    public Mono<ServerResponse> remove(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.status(HttpStatus.NO_CONTENT).body(this.userRepository.deleteById(id), User.class);
    }

}
