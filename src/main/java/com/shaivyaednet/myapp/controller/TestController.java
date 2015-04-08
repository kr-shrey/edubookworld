package com.shaivyaednet.myapp.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shaivyaednet.myapp.domain.*;
import com.shaivyaednet.myapp.repository.*;
import java.util.*;
public class TestController {
	private UserRepository usr;
	private LearningObjectRepository lr;
	
	@RequestMapping(value = "/createTest", method = RequestMethod.POST)
	public String test(@RequestParam("testName")String tname,@RequestParam("instructions")String inst,@RequestParam("time")int tym,@RequestParam("date")Date dt,@RequestParam("tstpssword") String pss,@RequestParam("marks")int numq,Model model, HttpSession session) 
	{
		User u=(User)session.getAttribute("us");
		u=usr.findByPropertyValue("usrName", u.usrName);
		TestLeO nwtst=new TestLeO(tname, u, pss, dt, tym, inst);
		lr.save(nwtst);
		usr.addToRepository(u, nwtst);
		model.addAttribute("usrcourses", u.courses);
		model.addAttribute("numque", numq);
		model.addAttribute("testname",tname);
		model.addAttribute("instruct", inst);
		model.addAttribute("time",tym);
		model.addAttribute("testdate", dt);
		return "edit_test";
	}
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public String savetest(@RequestParam("testName")String tname,@RequestParam("queset")long[] queids,@RequestParam("time")int tym,@RequestParam("date")Date dt,@RequestParam("tstpssword") String pss,@RequestParam("marks")int numq,Model model, HttpSession session) 
	{
		User u=(User)session.getAttribute("us");
		u=usr.findByPropertyValue("usrName", u.usrName);
		TestLeO nwtst=(TestLeO)((LearningObject)lr.findByPropertyValue("name", tname));
		for(long id:queids)
		{
			nwtst.addQuestion((QuestionLeO)((LearningObject)lr.findByPropertyValue("nodeId", id)),1);
		}
		lr.save(nwtst);
		usr.addToRepository(u, nwtst);
		model.addAttribute("usrcourses", u.courses);
		model.addAttribute("usrrepo", u.repo);
		model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
		model.addAttribute("neewsfeed",u.notify);
		model.addAttribute("usrcredits", u.credits);
		return "home";
	}
	@RequestMapping(value = "/takeTest", method = RequestMethod.POST)
	public String takeTest(@RequestParam("testName")String tname,@RequestParam("queset")HashMap<Long, String> queids,Model model, HttpSession session) 
	{
		User u=(User)session.getAttribute("us");
		u=usr.findByPropertyValue("usrName", u.usrName);
		TestLeO nwtst=(TestLeO)((LearningObject)lr.findByPropertyValue("name", tname));
		HashMap<QuestionLeO,Integer> tmtsc=new HashMap<QuestionLeO,Integer>();
		for(long id:queids.keySet())
		{
			int val=0;
			QuestionLeO tq=((QuestionLeO)((LearningObject)lr.findByPropertyValue("nodeId", id)));
			if(tq.checkAnswer(queids.get(id)))
				val=1;
			tmtsc.put(tq, val);
		}
		nwtst.addUserStats(u, tmtsc);
		lr.save(nwtst);
		usr.addToRepository(u, nwtst);
		model.addAttribute("usrcourses", u.courses);
		model.addAttribute("usrrepo", u.repo);
		model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
		model.addAttribute("neewsfeed",u.notify);
		model.addAttribute("usrcredits", u.credits);
		return "home";
	}
}
