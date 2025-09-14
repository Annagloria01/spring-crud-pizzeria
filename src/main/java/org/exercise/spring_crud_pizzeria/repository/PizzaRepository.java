package org.exercise.spring_crud_pizzeria.repository;

import org.exercise.spring_crud_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

}
