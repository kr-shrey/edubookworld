package com.shaivyaednet.myapp.domain;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.neo4j.graphdb.Direction;

public class VideoLeO extends LearningObject{
	@RelatedTo(type="COMMENTS", direction=Direction.OUTGOING)
	public @Fetch Set<Comment> comments;
	private String filePath;
	public VideoLeO(String ttle,Course crse,Topic tpc,User uId,int lvl,String fpth)
	{
		this.name=ttle;
		this.course=crse;
		this.topic=tpc;
		this.uploadedBy=uId;
		this.level=lvl;
		this.leObjectType="VideoLeO";
		this.comments=new HashSet<Comment>();
		this.filePath=fpth;
	}
	public boolean addComment(User uId,String cmnt)
	{
		Comment temp=new Comment(uId, cmnt, this);
		this.upDatedOn=new Date();
		this.course.sendUserNotification(this, "modify");
		return this.comments.add(temp);
	}
	public File getVideoFile()
	{
		return (new File(this.filePath));
	}
}
