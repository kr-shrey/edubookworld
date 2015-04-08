package com.shaivyaednet.myapp.controller;

import javax.servlet.http.HttpSession;

















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.shaivyaednet.myapp.domain.*;
import com.shaivyaednet.myapp.repository.*;

import org.springframework.web.bind.annotation.RequestParam;
@Controller
@SessionAttributes("us")
@Configuration
@ComponentScan("com.shaivyaednet.myapp.repository")
public class UserController 
{
	@Autowired private Neo4jTemplate template;
	@Autowired private UserRepository usr;
	@Autowired private CourseRepository cr;
	@Autowired private TopicRepository tr;
	@Autowired private LearningObjectRepository lr;
	private User insession;
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) 
	{
		User u=(User)session.getAttribute("us");
		insession=u;
		model.addAttribute("usrcourses", u.courses);
		model.addAttribute("usrrepo", u.repo);
		model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
		model.addAttribute("neewsfeed",u.notify);
		model.addAttribute("usrcredits", u.credits);
		return "home";
	}
	@RequestMapping(value = "/createCourse", method = RequestMethod.POST)
	public String createCourse(@RequestParam("courseName")String cname,@RequestParam("stdrd")String st,Model model, HttpSession session) 
	{
		User u=(User)session.getAttribute("us");
		insession=u;
		Course c=new Course(cname,st);
		if(!(cr.findAllByPropertyValue("name", c.name).iterator().hasNext()))
		{
			if(!((u.getRole())[0].toString().equals("ROLE_STUDENT")))
			{
				cr.save(c);
				c=(Course)cr.findByPropertyValue("name", c.name);
				u=(User)usr.findByPropertyValue("usrName", u.usrName);
				usr.addCourse(u.getNodeId(), c.nodeId, true);
				cr.addMentor(u.getNodeId(), c.nodeId);
				u.addCourse(c);
				usr.save(u);
				cr.save(c);
				model.addAttribute("us", u);
				model.addAttribute("thcourse",c);
				model.addAttribute("topics", c.topics);
				model.addAttribute("m_user", c.mentors);
				model.addAttribute("users",c.students);
				model.addAttribute("usrcourses", u.courses);
				for (Course xy: u.courses)
				{
					String attname="topicun"+xy.name;
					model.addAttribute(attname,xy.topics);
				}
				model.addAttribute("usrrepo", u.repo);
				model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
				model.addAttribute("neewsfeed",u.notify);
				model.addAttribute("usrcredits", u.credits);
				model.addAttribute("userStatus", true);
				return "course";
			}
			return home(model,session);
		}
		else
		{
			c=(Course)cr.findByPropertyValue("name", c.name);
			model.addAttribute("thcourse",c);
			model.addAttribute("topics", c.topics);
			model.addAttribute("m_user", c.mentors);
			model.addAttribute("users",c.students);
			model.addAttribute("usrcourses", u.courses);
			for (Course xy: u.courses)
			{
				String attname="topicun"+xy.name;
				model.addAttribute(attname,xy.topics);
			}
			model.addAttribute("usrrepo", u.repo);
			model.addAttribute("usrtyp", ((u.getRole())[0].toString()));
			model.addAttribute("neewsfeed",u.notify);
			model.addAttribute("usrcredits", u.credits);
			boolean flag=true;
			model.addAttribute("userStatus", flag);
			return "course";
		}
		
	}
	@RequestMapping(value = "/createTopicUnderCourse", method = RequestMethod.POST)
	public String createTopicCourse(@RequestParam("courseid")long cnid,@RequestParam(value="topicName")String std,Model model, HttpSession session) throws IOException 
	{
		Course c=((Course)(cr.findOne(cnid)));
		User u=(User)session.getAttribute("us");
		insession=u;
		if(insession.courses.contains(c))
		{
			if(c.addTopic(std))
			{
				Topic temptpc=new Topic(std,c);
				tr.save(temptpc);
				model.addAttribute("thcourse",c);
				model.addAttribute("topics", c.topics);
				model.addAttribute("m_user", c.mentors);
				model.addAttribute("users",c.students);
				insession=(User)session.getAttribute("us");
				boolean flag=false;
				if((c.students.contains(insession)) || (c.mentors.contains(insession)))
					flag=true;
				model.addAttribute("userStatus", flag);
				model.addAttribute("usrcourses", u.courses);
				for (Course xy: u.courses)
				{
					String attname="topicun"+xy.name;
					model.addAttribute(attname,xy.topics);
				}
				model.addAttribute("usrrepo", u.repo);
				model.addAttribute("usrtyp", ((insession.getRole())[0].toString()));
				model.addAttribute("neewsfeed",insession.notify);
				model.addAttribute("usrcredits", insession.credits);
				return "course";
			}
			return "home";
		}
		else
			return (new CourseController()).viewTopics(c.name, model, session);
		
	}
	@RequestMapping(value = "/UploadFileLearningObject")
	public String addMaterial(@RequestParam(value="courseid")long cnid,@RequestParam(value="topicid")long tid,@RequestParam(value="filebutton")MultipartFile f,@RequestParam(value="fname")String nm,@RequestParam(value="std") int lvl,Model model, HttpSession session) throws IOException 
	{
		System.out.println("1");
		System.out.println("2");
//fileValidator.validate(file,new org.springframework.validation.Errors);
		System.out.println("3");
		byte[] bytes = f.getBytes();
		File nf=new File(f.getOriginalFilename());
		BufferedOutputStream stres=new BufferedOutputStream(new FileOutputStream(nf));
		stres.write(bytes);
		stres.close();
		System.out.println("here");
		String typ=nf.getName().substring(nf.getName().indexOf("."));
		Course c=(Course)cr.findByPropertyValue("nodeId", cnid);
		Topic tp=(Topic)tr.findOne( tid);
		User u=(User)session.getAttribute("us");
		insession=u;
		System.out.println("here");
		LearningObject l=new LearningObject();
		if(insession.courses.contains(c))
		{
			if(typ.contains("pdf"))
			{
				if(tp.addDocumentLeO(nf, insession, nm, lvl,l))
				{
					lr.save(l);
					tr.addLeO(tp, l);
					usr.addToRepository(u, l);
					insession.addToRepository( l);
					model.addAttribute("leobj",l);
					model.addAttribute("leotype", l.leObjectType);
					model.addAttribute("newsfeed", ((DocumentLeO)l).comments);
					return "learningObject";
				}
				else
					return "home";
			}
			else
			{
				if(tp.addVideoLeO(nf,insession,nm,lvl,l))
				{
					lr.save(l);
					tr.addLeO(tp, l);
					insession.addToRepository(l);
					usr.addToRepository(u, l);
					model.addAttribute("leobj",l);
					model.addAttribute("leotype", l.leObjectType);
					model.addAttribute("newsfeed", ((VideoLeO)l).comments);
					return "learningObject";
				}
				else 
					return "home";
			}
		}
		else
			return ((new CourseController()).viewTopics(c.name, model, session));
		
	}
	@RequestMapping(value = "/AskDoubt", method = RequestMethod.POST)
	public String addDoubt(@RequestParam(value="courseid")long cnid,@RequestParam(value="topicid")long tid,String query,Model model, HttpSession session)
	{
		Course c=(Course)cr.findByPropertyValue("nodeId", cnid);
		Topic tp=(Topic)tr.findOne( tid);
		User u=(User)session.getAttribute("us");
		insession=u;
		LearningObject l=new LearningObject();
		if(insession.courses.contains(c))
		{
			if(tp.addDoubtLeO(query,u)){
			l=new DoubtLeO(query,c,tp,u);
			u.addToRepository(l);
			lr.save(l);
			tr.addLeO(tp, l);
			usr.addToRepository(u, l);
			model.addAttribute("leobj",l);
			model.addAttribute("leotype", l.leObjectType);
			model.addAttribute("newsfeed", ((DoubtLeO)l).comments);
			return "learningObject";
			}
		}
		return "home";
	}
	@RequestMapping(value = "/personalisedTest", method = RequestMethod.POST)
	public String personalTest(@RequestParam(value="courseid")long cnid,@RequestParam(value="topicid")long tid,String query,Model model, HttpSession session)
	{
		Course c=(Course)cr.findByPropertyValue("nodeId", cnid);
		Topic tp=(Topic)tr.findOne( tid);
		User u=(User)session.getAttribute("us");
		insession=u;
		String tname=u.usrName+"_"+c.name+"_"+tp.name+"_personalTest";
		TestLeO pertest=new TestLeO(tname,c,tp,u,new Date(),60);
		if(!(lr.findAllByPropertyValue("name", tname).iterator().hasNext()))
		{
			int count=0;
			for(LearningObject leo: tp.material)
			{
				if(leo instanceof QuestionLeO)
				{
					if(leo.level<3)
					{
						if(count==35)
							break;
						pertest.addQuestion((QuestionLeO)leo, 3);
						count++;
					}
				}
			}
		}
		else
		{
			TestLeO newtst=(TestLeO)((LearningObject)(lr.findByPropertyValue("name", tname)));
			newtst=newtst.getAdaptiveTest();
		}
		return "test";
	}
}