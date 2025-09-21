package org.exercise.spring_crud_pizzeria.controller;

import java.util.Optional;

import org.exercise.spring_crud_pizzeria.model.Pizza;
import org.exercise.spring_crud_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/pizzas/search")
    public String filterTable(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("pizzaList", pizzaRepository.findByNameContaining(keyword));
        return "index";
    }

    @GetMapping("/pizzas/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "create";
    }

    @PostMapping("/pizzas/create")
    public String createPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        Optional<Pizza> optPizza = pizzaRepository.findByName(formPizza.getName());
        if (optPizza.isPresent()) {
            bindingResult.addError(new ObjectError("name", formPizza.getName() + " already present"));
        }

        if (bindingResult.hasErrors()) {
            return "create";
        }

        pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza created successfully");
        return "redirect:/pizzas";
    }

}
