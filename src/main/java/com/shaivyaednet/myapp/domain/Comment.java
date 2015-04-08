package com.shaivyaednet.myapp.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.neo4j.graphdb.Direction;
@NodeEntity
public class Comment {
	@GraphId @Indexed public Long nodeId;
	public String comment;
	@RelatedTo(type="COMMENTBY", direction=Direction.OUTGOING)
	public @Fetch User userId;
	public Date update;
	@RelatedTo(type="COMMENTS", direction=Direction.INCOMING)
	public @Fetch LearningObject leobj;
	@RelatedTo(type="UPVOTECOM", direction=Direction.OUTGOING)
	public @Fetch Set<User> upVotedBy;
	@RelatedTo(type="DOWNVOTECOM", direction=Direction.OUTGOING)
	public @Fetch Set<User> downVotedBy;
	private Long upVotes;
	private Date upLoadDate;
	public boolean reported;
	@RelatedTo(type="REPORTCOM", direction=Direction.OUTGOING)
	public @Fetch Set<User> reportedBy;
	public Comment(User uId,String cmnt,LearningObject leo)
	{
		this.comment=cmnt;
		this.userId=uId;
		this.leobj=leo;
		this.upLoadDate=new Date();
		this.upVotedBy=new HashSet<User>();
		this.downVotedBy=new HashSet<User>();
		this.upVotes=(long) 0;
		this.reported=false;
		reportedBy=new HashSet<User>();
		this.update=new Date();
	}
	public boolean equals(Comment cmn)
	{
		if(this.userId.equals(cmn.userId))
		{
			if(this.comment.equals(cmn.comment))
			{
				if(this.upLoadDate.equals(cmn.upLoadDate))
				{
					return true;
				}
			}
		}
		return false;
	}
	public boolean upVote(User uId)
	{
		if(this.upVotedBy.add(uId))
		{
			this.upVotes++;
			this.reportDoubtSolved();
			return true;
		}
		return false;
	}
	public boolean downVote(User uId)
	{
		if(this.downVotedBy.add(uId))
		{
			this.upVotes--;
			return true;
		}
		return false;
	}
	public Long getUpVotes()
	{
		return this.upVotes;
	}
	public void reportMaterial(User uId)
	{
		this.reported=true;
		this.reportedBy.add(uId);
		if(this.reportedBy.size()>3)
		{
			this.leobj.course.sendUserNotification(this.leobj, "reportcomment");
		}
	}
	protected void reportApproved()
	{
		this.userId.chargeCredit(this,"report comment",50);
		for(User u:this.reportedBy)
		{
			u.addCredits(this,"comment",(int)Math.floor(100/(this.reportedBy.size())));
		}
	}
	private void reportDoubtSolved()
	{
		if(this.upVotes>5)
		{
			if(this.leobj.leObjectType.equals("DoubtLeO"))
			{
				((DoubtLeO)(this.leobj)).resolved=true;
				this.userId.addCredits(this,"comment",5);
				this.leobj.course.sendUserNotification(this.leobj, "doubtresolve");
			}
		}
	}
}
