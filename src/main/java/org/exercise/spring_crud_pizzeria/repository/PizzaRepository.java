package org.exercise.spring_crud_pizzeria.repository;

import java.util.List;

import org.exercise.spring_crud_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findByNameContaining(String name);

}
