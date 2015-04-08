package com.shaivyaednet.myapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

import org.springframework.data.neo4j.annotation.*;
import org.neo4j.graphdb.Direction;

public class TestLeO extends LearningObject{
	//R stats
	@RelatedTo(type="CONTAINS", direction=Direction.OUTGOING)
	public @Fetch HashMap<QuestionLeO,Integer> set;
	@RelatedTo(type="REGISTERED",direction=Direction.OUTGOING)
	public @Fetch Set<User> testTakers;
	private HashMap<User,HashMap<QuestionLeO,Integer>> stats;
	private String testPassword;
	private Date startTime;
	public String instructions;
	private int time_in_minutes;
	public TestLeO(String ttle,Course crse,Topic tpc,User uId,Date dt,int time)
	{
		this.name=ttle;
		this.course=crse;
		this.topic=tpc;
		this.uploadedBy=uId;
		this.level=0;
		this.leObjectType="TestLeO";
		this.set=new HashMap<QuestionLeO,Integer>();
		this.testTakers=new HashSet<User>();
		this.testPassword="";
		this.startTime=dt;
		this.instructions="to be set";
		this.time_in_minutes=time;
		this.stats=new HashMap<User,HashMap<QuestionLeO,Integer>>();
	}
	public TestLeO(String ttle,User uId,Date dt,int time,String inst)
	{
		this.name=ttle;
		this.course=null;
		this.topic=null;
		this.uploadedBy=uId;
		this.level=0;
		this.leObjectType="TestLeO";
		this.set=new HashMap<QuestionLeO,Integer>();
		this.testTakers=new HashSet<User>();
		this.testPassword="";
		this.startTime=dt;
		this.time_in_minutes=time;
		this.instructions=inst;
		this.stats=new HashMap<User,HashMap<QuestionLeO,Integer>>();
	}	
	public TestLeO(String ttle,User uId,String pass,Date dt,int time,String inst)
	{
		this.name=ttle;
		this.course=null;
		this.topic=null;
		this.instructions=inst;
		this.uploadedBy=uId;
		this.level=0;
		this.leObjectType="TestLeO";
		this.set=new HashMap<QuestionLeO,Integer>();
		this.testTakers.clear();
		this.testPassword=pass;
		this.startTime=dt;
		this.time_in_minutes=time;
		this.stats=new HashMap<User,HashMap<QuestionLeO,Integer>>();
	}
	public TestLeO() {
		// TODO Auto-generated constructor stub
	}
	public boolean registerForTest(User uId,String pass)
	{
		Date temp=new Date();
		if(temp.compareTo(this.startTime)>0)
		{
			if(this.testPassword=="")
			{
				return this.testTakers.add(uId);
			}
			else if(this.testPassword.equals(pass))
			{
				return this.testTakers.add(uId);
			}
		}
		return false;
	}
	public boolean addUserStats(User uId,HashMap<QuestionLeO,Integer> qmo)
	{
		if(!(stats.containsKey(uId)))
		{
			stats.put(uId, qmo);
			return true;
		}
		return false;
	}
	public boolean addQuestion(QuestionLeO que, int mm)
	{
		if(!(set.containsKey(que)))
		{
			set.put(que, mm);
			return true;
		}
		return false;
	}
	public int getTestTimeInMinutes()
	{
		return this.time_in_minutes;
	}
	public TestLeO getAdaptiveTest()
	{
		return new TestLeO();
	}
}
