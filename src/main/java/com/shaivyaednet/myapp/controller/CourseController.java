package com.shaivyaednet.myapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.shaivyaednet.myapp.domain.Course;
import com.shaivyaednet.myapp.domain.LearningObject;
import com.shaivyaednet.myapp.domain.Topic;
import com.shaivyaednet.myapp.domain.User;
import com.shaivyaednet.myapp.repository.CourseRepository;
import com.shaivyaednet.myapp.repository.LearningObjectRepository;
import com.shaivyaednet.myapp.repository.TopicRepository;
import com.shaivyaednet.myapp.repository.UserRepository;
@Controller
public class CourseController {
	@Autowired private Neo4jTemplate template;
	@Autowired private UserRepository usr;
	@Autowired private CourseRepository cr;
	@Autowired private TopicRepository tr;
	@Autowired private LearningObjectRepository lr;
	private User insession=new User();
	@RequestMapping(value = "/{courseNodeId}.course", method = RequestMethod.GET)
	public String viewTopics( @PathVariable("courseNodeId") String courseNodeId, Model model, HttpSession session) throws IOException 
	{
		Course c=(Course)cr.findByPropertyValue("name", courseNodeId);
		
		model.addAttribute("thcourse",c);
		System.out.println(c.topics.size());
		model.addAttribute("topics", c.topics);
		model.addAttribute("m_user", c.mentors);
		model.addAttribute("users",c.students);
		insession=(User)session.getAttribute("us");
		boolean flg=false;
		/*if(courseNodeId.equals("Physics Mechanics"))
		{
			Topic tp=c.topics.iterator().next();
			System.out.println(tp.name);
			File f= new File("xxx.txt");
			FileOutputStream fout=new FileOutputStream(f);
			fout.write(1000);
			fout.close();
//			if(tp.material.size()==0)
//			{
//				LearningObject leo=new LearningObject();
//				File f=new File("Newton.pdf");
//				flg=tp.addDocumentLeO(f, insession, "Laws Of Newton", 3, leo);
//				lr.save(leo);
//				tr.save(tp);
//				cr.save(c);
//			}
		}
		System.out.println(flg);*/
		boolean flag=false;
		if((c.students.contains(insession)) || (c.mentors.contains(insession)))
			flag=true;
		model.addAttribute("userStatus", flag);
		model.addAttribute("usrcourses", insession.courses);
		model.addAttribute("usrrepo", insession.repo);
		model.addAttribute("usrtyp", ((insession.getRole())[0].toString()));
		model.addAttribute("neewsfeed",insession.notify);
		model.addAttribute("usrcredits", insession.credits);
		return "course";
	}
	@RequestMapping(value = "/{courseNodeId}.subscribeCourse", method = RequestMethod.GET)
	public String subscribe(@PathVariable("courseNodeId") String courseNodeId, Model model, HttpSession session) 
	{
		Course c=(Course)cr.findByPropertyValue("name", courseNodeId);
		model.addAttribute("thcourse",c);
		model.addAttribute("topics", c.topics);
		model.addAttribute("m_user", c.mentors);
		model.addAttribute("users",c.students);
		insession=(User)session.getAttribute("us");
		if(insession.getRole()[0].toString().equals("ROLE_STUDENT"))
			usr.addCourse(insession, c);
		else
			usr.addCourse(insession.getNodeId(), c.nodeId, true);
		model.addAttribute("userStatus", true);
		model.addAttribute("usrcourses", insession.courses);
		model.addAttribute("usrrepo", insession.repo);
		model.addAttribute("usrtyp", ((insession.getRole())[0].toString()));
		model.addAttribute("neewsfeed",insession.notify);
		model.addAttribute("usrcredits", insession.credits);
		return "course";
	}
}
