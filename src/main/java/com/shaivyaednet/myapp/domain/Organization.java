package com.shaivyaednet.myapp.domain;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.neo4j.graphdb.Direction;
@NodeEntity
public class Organization extends User{
	public String address;
	public String email;
	public String contct_info;
	public String phN0;
	private String password;
	@RelatedTo(type="ORREPOSITORY", direction=Direction.OUTGOING)
	public @Fetch Set<LearningObject> repo;
	@RelatedTo(type="INSTITUTE", direction=Direction.BOTH)
	public @Fetch Set<User> usrs;
	public Date lastLogin;
	protected Date accCreation;
	private boolean registeredAsUser;
	public Organization(String nm)
	{
		this.name=nm;
		this.registeredAsUser=false;
		this.repo=new HashSet<LearningObject>();
		this.usrs=new HashSet<User>();
	}
	public boolean register(String uname, String ema, String passwrd)
	{
		if(!(this.registeredAsUser))
		{
			this.usrName=uname;
			this.email=ema;
			this.password=passwrd;
			this.registeredAsUser=true;
			return true;
		}
		return false;
	}
	public boolean addToRepository(LearningObject leo)
	{
		return this.repo.add(leo);
	}
	public boolean verifyPassword(String p)
	{
		if(this.password.equals(p))
		{
			lastLogin=new Date();
			return true;
		}
		return false;
	}
	public void setAddress(String adr)
	{
		this.address=adr;
		this.name=this.name+", "+this.address;
	}
	public void setContactInfo(String ci)
	{
		this.contct_info=ci;
	}
	public void setphnNumber(String pn)
	{
		this.phN0=pn;
	}
	public void notifyUserTest(TestLeO leo,String details)
	{
		for(User temp:this.usrs)
		{
			temp.addNotification(leo, details);
		}
	}
	public Long getNodeId() {
		return nodeId;
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
	public boolean addUser(User uId)
	{
		return this.usrs.add(uId);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if(!(this.registeredAsUser))
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accCreation == null) ? 0 : accCreation.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((usrName == null) ? 0 : usrName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(this.registeredAsUser))
		{
			if(this.name.equals(((Organization)obj).name))
			{
				if(this.address.equals(((Organization)obj).name))
				{
					return true;
				}
			}
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Organization other = (Organization) obj;
		if (accCreation == null) {
			if (other.accCreation != null) {
				return false;
			}
		} else if (!accCreation.equals(other.accCreation)) {
			return false;
		}
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
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
		return true;
	}
}