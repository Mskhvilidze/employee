package com.fricke.personal.employee.controller;

import com.fricke.personal.employee.service.AddressService;
import com.fricke.personal.employee.service.GamerService;
import com.fricke.personal.employee.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        //model.addAttribute("address", new Address());
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
        model.addAttribute("", "");
        return "login";
    }
}
