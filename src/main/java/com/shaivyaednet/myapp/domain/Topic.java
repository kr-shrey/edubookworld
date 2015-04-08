package com.shaivyaednet.myapp.domain;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;
import org.neo4j.graphdb.Direction;
@NodeEntity
public class Topic {
	@GraphId @Indexed public Long nodeId;
	public Set<LearningObject> getMaterial() {
		return material;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public Topic()
	{
		
	}
	public static Charset chrset=Charset.forName("UTF-8");
	private static String fpathroot="data\\\\files";
	@Indexed(unique=true) public String name;
	@RelatedTo(type="UNDER", direction=Direction.BOTH)
	public @Fetch Course course;
	@RelatedTo(type="UNDERTOPIC", direction=Direction.BOTH)
	public @Fetch Set<LearningObject> material;
	public Topic(String nm,Course cr)
	{
		this.course=cr;
		this.name=nm;
		this.material=new HashSet<LearningObject>();
	}
	public String getName() {
		return name;
	}
	public boolean addQuestion(int levl,String nm,User uId,String[] optn,String answr,File imgFile,LearningObject leo) throws IOException
	{
		LearningObject temp=new QuestionLeO(nm,this.course,this,uId,levl);
		((QuestionLeO)temp).addOptions(optn);
		((QuestionLeO)temp).addAnswer(answr);
		if(imgFile!=(File)null)
			((QuestionLeO)temp).addPictureFile(imgFile);
		uId.addToRepository(temp);
		uId.addCredits(temp,"uploadLearningObject", 10);
		leo=temp;
		return this.material.add(temp);
	}
	public boolean addDoubtLeO(String nm,User uId)
	{
		LearningObject temp=new DoubtLeO(nm,this.course,this,uId);
		uId.addToRepository(temp);
		uId.chargeCredit(temp,"doubtpost", 5);
		return this.material.add(temp);
	}
	public boolean addVideoLeO(File f,User uId,String nm,int levl,LearningObject leo) throws IOException
	{
		String fpth=fpathroot+"\\\\"+this.course.name+"\\\\"+this.name+"\\\\video\\\\"+f.getName();
		File newfile=new File(fpth);
		FileInputStream fin=new FileInputStream(f);
		byte[] bytes = new byte[(int)f.length()];
		fin.read(bytes);
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(newfile));
        stream.write(bytes);
        stream.close();
		LearningObject temp=new VideoLeO(nm,this.course,this,uId,levl,fpth);
		uId.addToRepository(temp);
		uId.addCredits(temp,"uploadLearningObject",10);
		leo=temp;
		return this.material.add(temp);
	}
	public boolean addDocumentLeO(File f,User uId,String nm,int levl,LearningObject leo) throws IOException
	{
		Scanner sc=null;
		PrintWriter wrtr=null;
		String fpth=fpathroot+"\\\\"+this.course.name+"\\\\"+this.name+"\\\\document\\\\"+f.getName();
		File newfile=new File(fpth);
		FileInputStream fin=new FileInputStream(f);
		byte[] bytes = new byte[(int)f.length()];
		fin.read(bytes);
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(newfile));
        stream.write(bytes);
        stream.close();
		LearningObject temp=new DocumentLeO(nm,this.course,this,uId,levl,fpth);
		uId.addToRepository(temp);
		uId.addCredits(temp,"uploadLearningObject",10);
		leo=temp;
		return this.material.add(temp);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Topic other = (Topic) obj;
		if (course == null) {
			if (other.course != null) {
				return false;
			}
		} else if (!course.equals(other.course)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}