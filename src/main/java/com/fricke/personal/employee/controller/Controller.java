package com.fricke.personal.employee.controller;

import com.fricke.personal.employee.service.AddressService;
import com.fricke.personal.employee.service.GamerService;
import com.fricke.personal.employee.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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

    public Controller() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/model")
    public String getPage(Model model, HttpSession session) {
        model.addAttribute("message", "Hello World");
        return "header";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/topic")
    public String getEmployee(Model model, HttpSession session) {
        model.addAttribute("topic", new Topic());
        if (session.getAttribute("name") == null) {
            model.addAttribute("noSession", "You must be logged in to be able to create articles");
        }
        return "creation";
    }

    @RequestMapping(value = "/model/topic", method = RequestMethod.POST)
    public String getEmployeeSubmit(@ModelAttribute Topic topic, Model model, HttpSession session) {
        model.addAttribute("topic", topic);
        Gamer gamer = gamerService.getGamer(session.getAttribute("name").toString());
        System.out.println(topic.getDescription());
        topic.setGamer(gamer);
        service.addTopic(topic);
        return "redirect:/model/topics";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/topics")
    public String getTopics(Model model, HttpSession session) {
        model.addAttribute("message", "All Topics");
        model.addAttribute("topics", service.getAllTopic());
        return "show";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/register")
    public String getRegisterPage(Model model, HttpSession session) {
        model.addAttribute("gamer", new Gamer());
        model.addAttribute("address", new Address());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model/register")
    public String getPostPerson(@ModelAttribute Gamer person, @ModelAttribute Address address, Model model, HttpSession session) {
        model.addAttribute("gamer", person);
        model.addAttribute("address", address);
        gamerService.addGamer(person);
        address.setGamer(person);
        addressService.addAddress(address);
        return "redirect:/model";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/login")
    public String getLogin(Model model, HttpSession session) {
        model.addAttribute("gamer", new Gamer());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model/login")
    public String getPostPersonLog(@ModelAttribute Gamer person, Model model, RedirectAttributes attributes, HttpSession session) {
        Iterator<Gamer> gamers = gamerService.getAllGamer();
        Spliterator<Gamer> spliterator = Spliterators.spliteratorUnknownSize(gamers, Spliterator.ORDERED);
        Stream<Gamer> stream = StreamSupport.stream(spliterator, false);
        Gamer gamer = stream.filter(g -> g.getNickname().equals(person.getNickname()) &&
                g.getPassword().equals(person.getPassword())).findAny().orElse(null);
        if (gamer != null) {
            attributes.addFlashAttribute("successful", "Login is successful");
            session.setAttribute("name", gamer.getNickname());
            return "redirect:/model";
        } else {
            model.addAttribute("message", "Not Exist");
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/logout")
    public String getTest(Model model, HttpSession session, HttpStatus status, RedirectAttributes redirectAttributes) {
        session.removeAttribute("name");
        redirectAttributes.addFlashAttribute("successful", "Logout is successful");
        return "redirect:/model";
    }
    //TODO Session muss auf jede Page aufgelöst werden
}
