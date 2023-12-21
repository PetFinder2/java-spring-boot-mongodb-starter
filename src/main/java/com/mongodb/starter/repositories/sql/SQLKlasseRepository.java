package com.mongodb.starter.repositories.sql;

import com.mongodb.starter.models.sql.SQLKlasse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SQLKlasseRepository extends JpaRepository<SQLKlasse, Long> {
    Optional<SQLKlasse> findByClassName(String className);

    void deleteByClassName(String className);
}
