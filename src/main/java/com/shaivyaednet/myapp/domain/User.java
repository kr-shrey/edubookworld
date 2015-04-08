package com.shaivyaednet.myapp.domain;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.neo4j.graphdb.Direction;
@NodeEntity
public class User {
	@GraphId @Indexed Long nodeId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getNodeId() {
		return nodeId;
	}
	@Indexed(unique=true) public String usrName;
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "search")
	public String name;
	@RelatedTo(type="SUBSCRIBE", direction=Direction.BOTH)
	public @Fetch Set<Course> courses;
	@RelatedTo(type="INSTITUTE", direction=Direction.BOTH)
	public @Fetch Set<Organization> insties;
	public String address;
	@Indexed public String email;
	public String contct_info;
	public String phN0;
	private String password;
	private Roles[] roles;
	public String stdrd;
	public int credits;
	@RelatedTo(type="USREPOSITORY", direction=Direction.OUTGOING)
	public @Fetch Set<LearningObject> repo;
	public List<Notification> notify;
	public Date lastLogin;
	protected Date accCreation;
	public User(String uname,String pass,String em,String nm,Roles... roles)
	{
		this.usrName=uname;
		this.password=encode(pass);
		this.email=em;
		this.accCreation=new Date();
		this.lastLogin=new Date();
		this.roles=roles;
		if(this.roles[0].equals("ROLE_STUDENT"))
		{
			this.credits=75;
		}
		else if(this.roles[0].equals("ROLE_TEACHER"))
		{
			this.credits=150;
		}
		else if(this.roles[0].equals("ROLE_RESEARCHER"))
		{
			this.credits=200;
		}
		else
		{
			this.credits=Integer.MAX_VALUE;
		}
		this.name=nm;
		this.notify=new ArrayList<Notification>();
		this.insties=new HashSet<Organization>();
		this.repo=new HashSet<LearningObject>();
	}
	public String getUsrName() {
		return usrName;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public enum Roles implements GrantedAuthority {
        ROLE_STUDENT, ROLE_TEACHER, ROLE_RESEARCHER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }
	public Roles[] getRole() {
        return roles;
    }
	private String encode(String tst)
	{
		return new Md5PasswordEncoder().encodePassword(tst,"randomize");
	}
	public boolean addToRepository(LearningObject leo)
	{
		if((leo.leObjectType.equals("VideoLeO")) || (leo.leObjectType.equals("DocumentLeO")))
		{
			return this.repo.add(leo);
		}
		else
		{
			if(this.roles[0].equals("ROLE_TEACHER"))
			{
				return this.repo.add(leo);
			}
		}
		return false;
	}
	public boolean verifyPassword(String p)
	{
		if(this.password.equals(encode(p)))
		{
			lastLogin=new Date();
			return true;
		}
		return false;
	}
	public boolean addInsti(Organization org)
	{
		if(this.insties.add(org))
		{
			org.usrs.add(this);
			return true;
		}
		return false;
	}
	protected void addCredits(Object obj,String objType,int value)
	{
		this.credits=this.credits+value;
		String ret="You have been awarded: "+value+" credits for: ";
		if(objType.equals("comment"))
		{
			ret=ret+"comment on "+((Comment)obj).leobj.name;
			this.addNotification(((Comment)obj).leobj, ret);
		}
		else if(objType.equals("learningobject"))
		{
			ret=ret+"comment on "+((LearningObject)obj).name;
			this.addNotification(((LearningObject)obj), ret);
		}
		else if(objType.equals("uploadLearningObject"))
		{
			ret=ret+"uploading: "+((LearningObject)obj).name;
			this.addNotification(((LearningObject)obj), ret);
		}
	}
	protected void chargeCredit(Object obj,String reason,int value)
	{
		this.credits=this.credits+value;
		String ret="You have been charged: "+value+" credits for: ";
		if(reason.contains("comment"))
		{
			ret=ret+""+((Comment)obj).leobj.name+"on being reported ad approved.";
			this.addNotification(((Comment)obj).leobj, ret);
		}
		else if(reason.contains("learningobject"))
		{
			ret=ret+""+((LearningObject)obj).name+" on being reported and approved.";
			this.addNotification(((LearningObject)obj), ret);
		}
		else if(reason.contains("puchase"))
		{
			ret=ret+"puchase of new Course: "+((Course)obj).name;
			this.addNotification((LearningObject)null,ret);
		}
		else if(reason.contains("doubtpost"))
		{
			ret=ret+"posting doubt under: "+(((LearningObject)obj).topic).name;
			this.addNotification(((LearningObject)obj), ret);
		}
	}
	public void setAddress(String adr)
	{
		this.address=adr;
	}
	public void setContactInfo(String ci)
	{
		this.contct_info=ci;
	}
	public void setphnNumber(String pn)
	{
		this.phN0=pn;
	}
	public boolean changePassword(String old,String newp)
	{
		if(this.verifyPassword(old))
		{
			this.password=newp;
			return true;
		}
		return false;
	}
	public int getNextCorseCharge()
	{
		if(this.roles[0].equals("ROLE_TEACHER"))
		{
			return 0;
		}
		int charge=0;
		int m=this.courses.size();
		int n=0;
		for(Course tmp:this.courses)
		{
			n=n+tmp.topics.size();
		}
		if(m<3)
		{
			charge=0;
		}
		else
		{
			if(m<7)
			{
				charge=4*n;
			}
			else if(m<15)
			{
				charge=4*n;
			}
			else
			{
				charge=3*n;
			}
		}
		return charge;
	}
	public boolean addCourse(Course cr)
	{
		if((this.roles[0]).toString().equals("ROLE_TEACHER"))
		{
			if(this.courses.add(cr))
			{
				cr.addMentor(this);
				return true;
			}
			return false;
		}
		int n;
		if(this.credits>(n=this.getNextCorseCharge()))
		{
			if(this.courses.add(cr))
			{
				cr.addStudents(this);
				this.chargeCredit(cr.name,"purchase",n);
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accCreation == null) ? 0 : accCreation.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((usrName == null) ? 0 : usrName.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		User other = (User) obj;
		if (accCreation == null) {
			if (other.accCreation != null) {
				return false;
			}
		} else if (!accCreation.equals(other.accCreation)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (usrName == null) {
			if (other.usrName != null) {
				return false;
			}
		} else if (!usrName.equals(other.usrName)) {
			return false;
		}
		if (roles == null) {
			if (other.roles != null) {
				return false;
			}
		} else if (!roles.equals(other.roles)) {
			return false;
		}
		return true;
	}
	public void addNotification(LearningObject leo,String details)
	{
		Notification temp=new Notification(leo,details);
		notify.add(temp);
		Collections.sort(notify, new Comparator<Notification>() {
        @Override public int compare(Notification p1, Notification p2) {
            return p1.compareTo(p2);
        }
		});
	}
}
class Notification
{
	public LearningObject leo;
	public String detail;
	public Date add;
	public Notification(LearningObject le,String det)
	{
		this.leo=le;
		this.detail=det;
		this.add=new Date();
	}
	public int compareTo(Notification no)
	{
		return (0-(this.add.compareTo(no.add)));
	}
}