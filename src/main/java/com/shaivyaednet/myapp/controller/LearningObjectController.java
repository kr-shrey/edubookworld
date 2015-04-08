package com.shaivyaednet.myapp.controller;

import java.io.IOException;
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

import com.shaivyaednet.myapp.domain.*;
import com.shaivyaednet.myapp.repository.CommentRepository;
import com.shaivyaednet.myapp.repository.CourseRepository;
import com.shaivyaednet.myapp.repository.LearningObjectRepository;
import com.shaivyaednet.myapp.repository.TopicRepository;
import com.shaivyaednet.myapp.repository.UserRepository;

@Controller
@SessionAttributes("us")
@Configuration
@ComponentScan("com.shaivyaednet.myapp.repository")
public class LearningObjectController {
	@Autowired private Neo4jTemplate template;
	@Autowired private UserRepository usr;
	@Autowired private CourseRepository cr;
	@Autowired private TopicRepository tr;
	@Autowired private LearningObjectRepository lr;
	@Autowired private CommentRepository cmr;
	private User insession;
	@RequestMapping(value = "/{courseNodeId}/t/{topicNodeId}/l/{leobjNodeId}.learningObject", method = RequestMethod.GET)
	public String viewLeobj(@PathVariable("courseNodeId") String courseNodeId, @PathVariable(value="topicNodeId") String topicNodeId, @PathVariable("leobjNodeId") String leobjNodeId, Model model, HttpSession session) throws IOException 
	{
		LearningObject leobj=lr.findByPropertyValue("name",leobjNodeId);
		User u=(User) session.getAttribute("us");
		insession=u;
		if(!leobj.name.isEmpty())
		{
			if(u.courses.contains(leobj.course) || (!((u.getRole()[0]).toString().contains("STUDENT"))))
			{
				model.addAttribute("leobj",leobj);
				model.addAttribute("leotype", leobj.leObjectType);
				boolean flag=false;
				if(u.repo.contains(leobj))
					flag=true;
				model.addAttribute("userstatus", flag);
				if(leobj.leObjectType.equals("DocumentLeO"))
				{
					model.addAttribute("leofile",((DocumentLeO)leobj).getDocumentFile());
					model.addAttribute("newsfeed", ((DocumentLeO)leobj).comments);
				}
				else if(leobj.leObjectType.equals("VideoLeO"))
				{
					model.addAttribute("leofile",((VideoLeO)leobj).getVideoFile());
					model.addAttribute("newsfeed", ((VideoLeO)leobj).comments);
				}
				else if(leobj.leObjectType.equals("DoubtLeO"))
				{
					model.addAttribute("newsfeed", ((DoubtLeO)leobj).comments);
				}
				return "learningObject";
			}
		}
		return (new CourseController()).viewTopics(courseNodeId, model, session);
	}
	@RequestMapping(value = "/{leobjNodeId}.addToRepository", method = RequestMethod.GET)
	public String addToRepo(@PathVariable("leobjNodeId") long fr, Model model, HttpSession session) throws IOException 
	{
		User s = (User) session.getAttribute("us");
		insession=s;
		LearningObject c=(LearningObject) lr.findOne( fr);
		if(s.courses.contains(c.course))
		{
			if(s.addToRepository(c))
			{
				return viewLeobj(c.course.name,c.topic.name,c.name,model,session);
			}
			return viewLeobj(c.course.name,c.topic.name,c.name,model,session);
		}
		return (new CourseController()).viewTopics(c.course.name,model, session);
	}
	@RequestMapping(value = "/{leobjNodeId}.comment", method = RequestMethod.POST)
	public String addComment(@PathVariable("leobjNodeId") long fr,@RequestParam(value="comm") String cmm, Model model, HttpSession session) throws IOException 
	{
		User s = (User) session.getAttribute("us");
		LearningObject c=(LearningObject) lr.findOne( fr);
		if(s.courses.contains(c.course))
		{
			if(c instanceof DocumentLeO)
			{
				((DocumentLeO) c).addComment(s, cmm);
			}
			else if(c instanceof VideoLeO)
			{
				((VideoLeO) c).addComment(s, cmm);
			}
			else if(c instanceof DoubtLeO)
			{
				((DoubtLeO) c).addComment(s, cmm);
			}
			return viewLeobj(c.course.name,c.topic.name,c.name,model,session);
		}
		return (new CourseController()).viewTopics(c.course.name,model, session);
	}
	@RequestMapping("/ajaxHandlers/learningObjects/upvote")
	public @ResponseBody
	long upvote(@RequestParam(value="lid") long tId) 
	{
		(lr.findOne( tId)).upVote(insession);
		return (lr.findOne(tId)).upVotes;
	}
	@RequestMapping("/ajaxHandlers/learningObjects/downvote")
	public @ResponseBody
	long downvote(@RequestParam(value="lid") long tId) 
	{
		(lr.findOne( tId)).downVote(insession);
		return (lr.findOne(tId)).downVotedBy.size();
	}
	@RequestMapping("/ajaxHandlers/learningObjects/comment/upvote")
	public @ResponseBody
	long upvotecomment(@RequestParam(value="cid") long tId) 
	{
		(cmr.findOne( tId)).upVote(insession);
		return (cmr.findOne(tId)).getUpVotes();
	}
	@RequestMapping("/ajaxHandlers/learningObjects/comment/downvote")
	public @ResponseBody
	long downvotecomment(@RequestParam(value="cid") long tId) 
	{
		(cmr.findOne( tId)).downVote(insession);
		return (cmr.findOne(tId)).downVotedBy.size();
	}
}