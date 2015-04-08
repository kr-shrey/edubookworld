package com.shaivyaednet.myapp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.neo4j.graphdb.Direction;

@NodeEntity
public class LearningObject {
	@GraphId @Indexed public Long nodeId;
	public Long getNodeId() {
		return nodeId;
	}
	@Indexed(unique=true) public String name;
	@RelatedTo(type="UNDERCORSE", direction=Direction.INCOMING)
	public @Fetch Course course;
	@RelatedTo(type="UNDERTOPIC", direction=Direction.BOTH)
	public Topic topic;
	@RelatedTo(type="UPLOAD", direction=Direction.OUTGOING)
	public @Fetch User uploadedBy;
	public Date upDatedOn;
	@RelatedTo(type="UPVOTE", direction=Direction.OUTGOING)
	public @Fetch Set<User> upVotedBy;
	@RelatedTo(type="DOWNVOTE", direction=Direction.OUTGOING)
	public @Fetch Set<User> downVotedBy;
	public Long upVotes;
	protected Date upLoadDate;
	protected boolean reported;
	@RelatedTo(type="REPORT", direction=Direction.OUTGOING)
	public @Fetch Set<User> reportedBy;
	public String leObjectType;
	public int level;
	public int noOfReporters;
	public LearningObject()
	{
		this.upDatedOn=new Date();
		this.upVotedBy=new HashSet<User>();
		this.downVotedBy=new HashSet<User>();
		this.upVotes=(long) 0;
		this.reported=false;
		this.reportedBy=new HashSet<User>();
		this.upLoadDate=new Date();
		this.noOfReporters=0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result
				+ ((leObjectType == null) ? 0 : leObjectType.hashCode());
		result = prime * result + level;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result
				+ ((upLoadDate == null) ? 0 : upLoadDate.hashCode());
		result = prime * result
				+ ((uploadedBy == null) ? 0 : uploadedBy.hashCode());
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
		LearningObject other = (LearningObject) obj;
		if (course == null) {
			if (other.course != null) {
				return false;
			}
		} else if (!course.equals(other.course)) {
			return false;
		}
		if (leObjectType == null) {
			if (other.leObjectType != null) {
				return false;
			}
		} else if (!leObjectType.equals(other.leObjectType)) {
			return false;
		}
		if (level != other.level) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (topic == null) {
			if (other.topic != null) {
				return false;
			}
		} else if (!topic.equals(other.topic)) {
			return false;
		}
		if (upLoadDate == null) {
			if (other.upLoadDate != null) {
				return false;
			}
		} else if (!upLoadDate.equals(other.upLoadDate)) {
			return false;
		}
		if (uploadedBy == null) {
			if (other.uploadedBy != null) {
				return false;
			}
		} else if (!uploadedBy.equals(other.uploadedBy)) {
			return false;
		}
		return true;
	}

	public boolean upVote(User uId)
	{
		if(this.upVotedBy.add(uId))
		{
			this.upVotes++;
			this.upDatedOn=new Date();
			return true;
		}
		return false;
	}
	public boolean downVote(User uId)
	{
		if(this.downVotedBy.add(uId))
		{
			this.upVotes--;
			this.upDatedOn=new Date();
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
		if(this.reportedBy.add(uId))
		{
			this.upDatedOn=new Date();
			this.noOfReporters++;
			if(this.noOfReporters>5)
			{
				this.course.sendUserNotification(this, "report");
			}
		}
	}
	protected void reportApproved()
	{
		this.uploadedBy.chargeCredit(this,"report learningobject",50);
		for(User u:this.reportedBy)
		{
			u.addCredits(this,"learningobject",(int)Math.floor(100/(this.reportedBy.size())));
		}
	}
}