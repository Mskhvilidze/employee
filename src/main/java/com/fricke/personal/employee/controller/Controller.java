package com.fricke.personal.employee.controller;

import com.fricke.personal.employee.service.AddressService;
import com.fricke.personal.employee.service.GamerService;
import com.fricke.personal.employee.service.LikeService;
import com.fricke.personal.employee.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;
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
    @Autowired
    private LikeService likeService;

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
        topic.setGamer(gamer);
        service.addTopic(topic);
        return "redirect:/model/topics";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/topics")
    public String getTopics(Model model, HttpSession session) {
        model.addAttribute("message", "All Topics");
        model.addAttribute("topics", service.getAllTopic());
        model.addAttribute("likes", likeService.getAllLikes());
        service.queryTopicByIdAndName((long) 1, "Inter vs Fiorentina");
        return "show";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/model/register")
    public String getRegisterPage(Model model, HttpSession session) {
        model.addAttribute("gamer", new Gamer());
        model.addAttribute("address", new Address());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/model/register")
    public String getPostPerson(@ModelAttribute Gamer person, @ModelAttribute Address address, Model model, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        model.addAttribute("gamer", person);
        model.addAttribute("address", address);
        if (!gamerService.isExistGamer(person.getNickname())) {
            addressService.addAddress(address);
            person.setAddress(address);
            gamerService.addGamer(person);
            return "redirect:/model";
        } else {
            redirectAttributes.addFlashAttribute("no_successful", "User already exists");
            return "redirect:/model/register";
        }

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

    @RequestMapping(method = RequestMethod.GET, value = "model/topics/{id}")
    public String deleteTopic(@PathVariable(name = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Topic topic = service.getTopic(id);
        if (session.getAttribute("name") == null) {
            redirectAttributes.addFlashAttribute("successful", "Man muss angemeldet sein," +
                    " um Artikel löschen zu können");
        } else if (!gamerService.isCompareNicknameWithSession(topic.getGamer().getNickname(), session.getAttribute("name"))) {
            redirectAttributes.addFlashAttribute("successful", "Artikel von anderen darfst du nicht löschen");
        } else {
            service.deleteTopic(id);
            //Ermitteln, ob Artikel wirklich gelöscht wurde
            Stream<Topic> stream = Stream.generate(service.getAllTopic()::next);
            boolean isExist = stream.allMatch(t -> t.getId() == id);
            if (!isExist) {
                redirectAttributes.addFlashAttribute("successful", "Artikel wurde dauerhaft gelöscht");
            } else {
                redirectAttributes.addFlashAttribute("successful", "Etwas ist shifgelaufen!");
            }

        }
        return "redirect:/model/topics";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/topics/update/{id}")
    public String updatePage(@PathVariable(name = "id") Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        model.addAttribute("topics", service.getTopic(id));
        Topic topic = service.getTopic(id);
        if (session.getAttribute("name") != null && session.getAttribute("name").equals(topic.getGamer().getNickname())) {
            return "update";
        }
        redirectAttributes.addFlashAttribute("successful", "Sie sind leider nicht angemeldet!");
        return "redirect:/model/topics";
    }

    @RequestMapping(method = RequestMethod.POST, value = "model/topics/update/{id}")
    public String updateTopic(@PathVariable(name = "id") Long id, HttpSession session, RedirectAttributes redirectAttributes,
                              @ModelAttribute Topic topicUpdated) {
        Topic topic = service.getTopic(id);
        Gamer gamer = gamerService.getGamer(topic.getGamer().getNickname());
        if (session.getAttribute("name") != null &&
                gamerService.isCompareNicknameWithSession(topic.getGamer().getNickname(), session.getAttribute("name")) && gamer != null) {
            topicUpdated.setGamer(gamer);
            service.updateTopic(topicUpdated, id);
            redirectAttributes.addFlashAttribute("successful", "Artikel wurde aktualisiert");
            return "redirect:/model/topics";
        }
        redirectAttributes.addFlashAttribute("successful", "Etwa ist schief gelaufen!");
        return "redirect:/model/topics/update/" + id;
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/profile")
    public String getString(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("name") != null) {
            Gamer gamer = gamerService.getGamer(session.getAttribute("name").toString());
            model.addAttribute("user", gamer);
            model.addAttribute("count", service.getNumberOfTopics(gamer.getNickname()));

            return "profile";
        }
        redirectAttributes.addFlashAttribute("successful", "Sie müssen sich anmelden!");
        return "redirect:/model";
    }

    @RequestMapping(method = RequestMethod.POST, value = "model/topics")
    public String getLike(@RequestBody String name) {
        //Array text spliten
        String[] topicLikes = name.split(":");

        if (topicLikes.length == 2) {
            Likes likes = new Likes(topicLikes[1], topicLikes[0]);

            if (!likeService.isContains(likes)) {
                likeService.likeSave(likes);
                Topic topicUpdated = service.getTopic(Long.parseLong(topicLikes[1]));
                topicUpdated.setCountLikes(String.valueOf(likeService.getCountLikes(topicLikes[1])));
            }
        }
        return "show";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "model/topics")
    public String getLike(@RequestBody String name, RedirectAttributes redirectAttributes) {
        //Array text spliten
        String[] topicLikes = name.split(":");
        if (topicLikes.length == 3) {
            Likes likes = new Likes(Long.parseLong(topicLikes[2]), topicLikes[1], topicLikes[0]);
            if (likeService.isContains(likes)) {
                likeService.disLike(likes);
                Topic topicUpdated = service.getTopic(Long.parseLong(topicLikes[1]));
                topicUpdated.setCountLikes(String.valueOf(likeService.getCountLikes(topicLikes[1])));
            }
        }
        return "show";
    }
}
