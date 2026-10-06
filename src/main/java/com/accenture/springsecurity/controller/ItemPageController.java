package com.accenture.springsecurity.controller;

import com.accenture.springsecurity.model.Item;
import com.accenture.springsecurity.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemPageController {

    private final ItemService itemService;

    public ItemPageController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("newItem", new Item());
        return "items";
    }

    @PostMapping
    public String create(@Valid Item item) {
        itemService.create(item);
        return "redirect:/items";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/items";
    }
}
