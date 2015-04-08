package com.shaivyaednet.myapp.domain;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
public class QuestionLeO extends LearningObject{
	private String[] options;
	private String answer;
	private byte[] imagebytestream;
	public String filename;
	public QuestionLeO(String ttle,Course crse,Topic tpc,User uId,int lvl)
	{
		this.name=ttle;
		this.course=crse;
		this.topic=tpc;
		this.uploadedBy=uId;
		this.level=lvl;
		this.leObjectType="QuestionLeO";
	}
	public void addOptions(String[] opt)
	{
		this.options=new String[opt.length];
		for(int i=0;i<opt.length;i++)
		{
			options[i]=opt[i];
		}
	}
	public void addAnswer(String ans)
	{
		this.answer=ans;
	}
	public String[] getOptions()
	{
		return this.options;
	}
	public boolean checkAnswer(String ans)
	{
		if(this.answer.equals(ans))
		{
			return true;
		}
		return false;
	}
	public void addPictureFile(File f)throws IOException
	{
		FileInputStream fr=new FileInputStream(f);
		if(fr.read(this.imagebytestream)>0)
		{
			this.filename=f.getName();
		}
	}
	public File getPicFile() throws IOException
	{
		File f=new File(this.filename);
		FileOutputStream fr=new FileOutputStream(f);
		fr.write(this.imagebytestream);
		fr.close();
		return f;
	}
}
