package com.mongodb.starter.repositories;

import com.mongodb.starter.models.Klasse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface KlasseRepository extends MongoRepository<Klasse, String> {
    @Query("{ 'className': ?0 }")
    Optional<Klasse> findByClassName(String className);

    void deleteByClassName(String className);
}
