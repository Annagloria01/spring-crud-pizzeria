package org.exercise.spring_crud_pizzeria.controller;

import org.exercise.spring_crud_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/")
    public String getPizzas(Model model) {
        model.addAttribute("pizzaList", pizzaRepository.findAll());
        return "index";
    }
    
}
