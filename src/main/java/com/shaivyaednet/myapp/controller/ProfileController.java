package com.shaivyaednet.myapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shaivyaednet.myapp.domain.*;
import com.shaivyaednet.myapp.repository.*;
@Controller
@SessionAttributes("us")
@Configuration
@ComponentScan("com.shaivyaednet.myapp.repository")
public class ProfileController 
{
	@Autowired private Neo4jTemplate template;
	@Autowired private UserRepository usr;
	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public String myprofile(Model model, HttpSession session) {		
		User u = ((User)session.getAttribute("us"));
		model.addAttribute("course", u.courses);
		model.addAttribute("institute", u.insties);
		model.addAttribute("repository", u.repo);
		return "self";
	}
	@RequestMapping(value = "/profile/{usrName}", method = RequestMethod.GET)
	public String profile(@PathVariable("usrName") String userId, Model model, HttpSession session) {
		if(((User)session.getAttribute("us")).getUsrName().equals(userId))
			return myprofile(model,session);
		else {
			User u = usr.findByPropertyValue("usrName", userId);
			model.addAttribute("user", u);
			model.addAttribute("courses", u.courses);
			model.addAttribute("institute", u.insties);
			return "profile";
		}
	}
}