package com.shaivyaednet.myapp.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.EndResult;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaivyaednet.myapp.domain.*;
import com.shaivyaednet.myapp.repository.*;
@Controller
@SessionAttributes("us")
public class HomeController 
{
	//onclick="getTopics(${uscourse.getNodeId()})"
	@Autowired private UserRepository usr;
	@Autowired private Neo4jTemplate template;
	@Autowired private CourseRepository cr;
    @Autowired private TopicRepository tr;
    @Autowired private LearningObjectRepository lr;
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(Model model,HttpServletRequest req)
	{		
//		template.query("START n=node(*) MATCH (n)-[r]-() DELETE n,r", null);
//		template.query("START n=node(*) MATCH (n) DELETE n", null);
		if(req.isRequestedSessionIdValid())
		{
			User u=(User)(req.getSession(false)).getAttribute("us");
			Map<String,Object> newmap=new HashMap<String,Object>();
			if(u!=(User)null)
			{
			newmap.put("us",u);
			newmap.put("usrcourses", u.courses);
			newmap.put("usrrepo", u.repo);
			newmap.put("usrtyp", ((u.getRole())[0].toString()));
			newmap.put("neewsfeed",u.notify);
			newmap.put("usrcredits", u.credits);
			return new ModelAndView("home",newmap);
			}
		}
		return new ModelAndView("index","signin",new User());
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String register(Model model,HttpServletRequest req) 
	{
		if(req.isRequestedSessionIdValid())
		{
			User u=(User)(req.getSession(false)).getAttribute("us");
			model.addAttribute("usrcourses", u.courses);
			model.addAttribute("usrrepo", u.repo);
			model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
			model.addAttribute("neewsfeed",u.notify);
			model.addAttribute("usrcredits", u.credits);
			return "home";
		}
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String setUserInSession(@RequestParam(value="userId") String uName,@RequestParam(value="password")String pass, Model model) 
	{
		User user = usr.findByPropertyValue("usrName",uName );
		 if(user!=null)
		 {		 
			 if(user.verifyPassword(pass))
			 {
				 System.out.println(user.getName());
				 System.out.println((user.getRole())[0].toString());
				 model.addAttribute("us", user);
				 return "redirect:home";
			 }
			 else
			 {
				 System.out.println("Wrong id/pass");
				 model.addAttribute("signin", new User());
				 return "index";
			 }
		 }
		 else {
			 model.addAttribute("signin", new User());
			 return "index";
		 }
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Model model, HttpSession session)
	{
		User u=(User)session.getAttribute("us");
		usr.save(u);
		return new ModelAndView("index","user",new User());
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String verify(@RequestParam(value="first_name")String nm1,@RequestParam(value="last_name")String nm2,@RequestParam(value="usrId")String id,@RequestParam(value="email")String em,@RequestParam(value="password")String ps,@RequestParam(value="role")String rl, Model model) throws FileNotFoundException, UnsupportedEncodingException 
	{
		String nm=nm1+" "+nm2;
		 if(!usr.findAllByPropertyValue("usrName", id).iterator().hasNext())
		 {
			 User u=new User();
			 if(rl.contains("student"))
			 {
				 u=new User(id,ps,em,nm,User.Roles.ROLE_STUDENT);
			 }else if(rl.contains("teacher"))
			 {
				 u=new User(id,ps,em,nm,User.Roles.ROLE_TEACHER);
			 }else if(rl.contains("researcher"))
			 {
				 u=new User(id,ps,em,nm,User.Roles.ROLE_RESEARCHER);
			 }
			 else if(rl.contains("institute"))
			 {
				 u=new Organization(nm);
				 if(!(((Organization)u).register(id,em,ps)))
				 {
					 u=new User();
				 }
			 }
			 usr.save(u);			
			 model.addAttribute("us", u);
			 System.out.println("Saved");
			 
			 return "redirect:home";
		 }
		 else
		 {
			 return "index";
		 }
	}
	@RequestMapping("/ajaxHandlers/checkUserNameAvailability")
	public @ResponseBody
	boolean check(@RequestParam(value="usn") String usrnm) 
	{
		if(((User)(usr.findByPropertyValue("usrName", usrnm)))==((User)null))
			return true;
		return false;
	}
}