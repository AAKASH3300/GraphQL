package com.spring_boot.graphql.persistence.repository;

import com.spring_boot.graphql.persistence.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}