package com.fricke.personal.employee.controller;

import com.fricke.personal.employee.service.AddressService;
import com.fricke.personal.employee.service.GamerService;
import com.fricke.personal.employee.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private TopicService service;
    @Autowired
    private GamerService gamerService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.GET, value = "/model")
    public String getPage(Model model) {
        model.addAttribute("message", "Hello World");
        return "header";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/topic")
    public String getEmployee(Model model) {
        model.addAttribute("topic", new Topic());
        return "creation";
    }

    @RequestMapping(value = "/model/topic", method = RequestMethod.POST)
    public String getEmployeeSubmit(@ModelAttribute Topic topic, Model model) {
        model.addAttribute("topic", topic);
        service.addTopic(topic);
        return "redirect:/model/topics";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/topics")
    public String getTopics(Model model) {
        model.addAttribute("message", "All Topics");
        model.addAttribute("topics", service.getAllTopic());
        return "show";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("gamer", new Gamer());
        model.addAttribute("address", new Address());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model/register")
    public String getPostPerson(@ModelAttribute Gamer person, @ModelAttribute Address address, Model model) {
        model.addAttribute("gamer", person);
        model.addAttribute("address", address);
        gamerService.addGamer(person);
        address.setGamer(person);
        addressService.addAddress(address);
        return "redirect:/model";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/login")
    public String getLogin(Model model) {
        model.addAttribute("gamer", new Gamer());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model/login")
    public String getPostPersonLog(@ModelAttribute Gamer person, Model model, RedirectAttributes attributes) {
        Iterator<Gamer> gamers = gamerService.getAllGamer();
        Spliterator<Gamer> spliterator = Spliterators.spliteratorUnknownSize(gamers, Spliterator.ORDERED);
        Stream<Gamer> stream = StreamSupport.stream(spliterator, false);
        Gamer gamer = stream.filter(g -> g.getNickname().equals(person.getNickname()) &&
                g.getPassword().equals(person.getPassword())).findAny().orElse(null);
        if (gamer != null) {
            attributes.addFlashAttribute("successful", "Login is successful");
            return "redirect:/model";
        } else {
            model.addAttribute("message", "Not Exist");
            return "login";
        }
    }
}
