package org.exercise.spring_crud_pizzeria.controller;

import java.util.Optional;

import org.exercise.spring_crud_pizzeria.model.Pizza;
import org.exercise.spring_crud_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/")
    public String getEmptyPage(Model model) {
        return "welcome";
    }

    @GetMapping("/pizzas")
    public String getPizzas(Model model) {
        model.addAttribute("pizzaList", pizzaRepository.findAll());
        return "index";
    }

    @GetMapping("pizzas/{id}")
    public String getPizzaDetails(@PathVariable Integer id, Model model) {
        Optional<Pizza> pizzaFound = pizzaRepository.findById(id);
        if (pizzaFound.isPresent()) {
            model.addAttribute("pizzaDetail", pizzaFound.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }
        return "pizza-detail";
    }

    @GetMapping("pizzas/search")
    public String filterTable(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("pizzaList", pizzaRepository.findByNameContaining(keyword));
        return "index";
    }

}
