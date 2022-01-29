package com.fricke.personal.employee.controller;

import com.fricke.personal.employee.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
public class EmployeeController {
    @Autowired
    private TopicService service;

    @RequestMapping(value = "/topic")
    public String getPage(Model model){
        model.addAttribute("message", "Hello World");
        return "home";
    }

    @RequestMapping(value = "/topic/allEmployee")
    public Iterator<Topic> getAllEmployee() {
        return service.getAllTopic();
    }

    @RequestMapping(value = "/topics/{id}")
    public Topic getEmployee(@PathVariable String id) {
        return service.getEmployee(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topic/addEmployee")
    public void addTopic(@RequestBody Topic topic) {
        service.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addAllTopic(@RequestBody Iterable<Topic> topics) {
        service.addAllTopic(topics);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topic/update/{id}")
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
        service.updateTopic(topic, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topic/delete/{id}")
    public void deleteTopic(@PathVariable String id) {
        service.deleteTopic(id);
    }
}
