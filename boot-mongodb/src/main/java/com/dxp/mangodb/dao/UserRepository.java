package com.dxp.mangodb.dao;

import com.dxp.mangodb.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findAllByNameLike(String name);

}
