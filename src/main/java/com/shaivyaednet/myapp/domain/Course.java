package com.shaivyaednet.myapp.domain;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.neo4j.graphdb.Direction;
@NodeEntity
public class Course {
	@GraphId @Indexed public Long nodeId;
	public Long getNodeId() {
		return nodeId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((stdrd == null) ? 0 : stdrd.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (stdrd == null) {
			if (other.stdrd != null) {
				return false;
			}
		} else if (!stdrd.equals(other.stdrd)) {
			return false;
		}
		return true;
	}
	public String getName() {
		return name;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public Set<User> getMentors() {
		return mentors;
	}
	public String getStdrd() {
		return stdrd;
	}
	public Set<User> getStudents() {
		return students;
	}
	@Indexed(unique=true) public String name;
	@RelatedTo(type="UNDER", direction=Direction.BOTH)
	public @Fetch Set<Topic> topics;
	@RelatedTo(type="MENTOR", direction=Direction.BOTH)
	public @Fetch Set<User> mentors;
	public String stdrd;
	@RelatedTo(type="SUBSCRIBE", direction=Direction.BOTH)
	public @Fetch Set<User> students;
	public Course(String nm,String std)
	{
		this.name=nm;
		this.stdrd=std;
		this.topics=new HashSet<Topic>();
		this.mentors=new HashSet<User>();
		this.students=new HashSet<User>();
	}
	public Course() {
		// TODO Auto-generated constructor stub
	}
	public boolean addMentor(User uId)
	{
		return this.mentors.add(uId);
	}
	public boolean addStudents(User uId)
	{
		return students.add(uId);
	}
	public boolean removeMentor(User uId)
	{
		return this.mentors.remove(uId);
	}
	public boolean addTopic(String nm)
	{
		Topic temp=new Topic(nm,this);
		return this.topics.add(temp);
	}
	public boolean addTopic(Topic tc)
	{
		return this.topics.add(tc);
	}
	protected void sendUserNotification(LearningObject leo,String details)
	{
		String ret="";
		if(details.equals("upload"))
		{
			ret=ret+leo.name+" was added to :"+this.name+" under Topic: "+leo.topic.name;
		}
		else if(details.equals("modify"))
		{
			ret=ret+"Users commented on :"+leo.name;
		}
		else if(details.equals("report"))
		{
			ret=ret+leo.name+" was reported by Users";
		}else if(details.equals("doubtresolve"))
		{
			ret=ret+leo.name+" was resolved by Users";
		}
		else if(details.equals("reportcomment"))
		{
			ret=ret+"A comment on "+leo.name+" under Course: "+this.name+" and Topic: "+leo.topic.name+" was reprted by Users.";
		}
		for(User temp:mentors)
		{
			temp.addNotification(leo, ret);
		}
		for(User temp:students)
		{
			temp.addNotification(leo, ret);
		}
	}
}