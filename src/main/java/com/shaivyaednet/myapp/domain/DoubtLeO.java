package com.shaivyaednet.myapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

public class DoubtLeO extends LearningObject{
	public boolean resolved;
	@RelatedTo(type="ANSWERS", direction=Direction.OUTGOING)
	public @Fetch Set<Comment> comments;
	public DoubtLeO(String ttle,Course crse,Topic tpc,User uId)
	{
		this.name=ttle;
		this.course=crse;
		this.topic=tpc;
		this.uploadedBy=uId;
		this.level=0;
		this.leObjectType="DoubtLeO";
		this.comments=new HashSet<Comment>();
	}
	public boolean addComment(User uId,String cmnt)
	{
		Comment temp=new Comment(uId, cmnt, this);
		this.upDatedOn=new Date();
		this.course.sendUserNotification(this, "modify");
		return this.comments.add(temp);
	}
}
