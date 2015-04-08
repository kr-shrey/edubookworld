package com.shaivyaednet.myapp.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.shaivyaednet.myapp.domain.*;
public interface CourseRepository extends GraphRepository<Course> {
	@Query("start u=node:Course(name={0}) where u.stdrd={1} return u;")
	Course getCoursebyNameAndStandard(String name,String std);
	
	@Query("start u=node:Course({0})match (u)<-[:SUBSCRIBE]->(g) return g;")
	Set<User> getStudents(Course nodeId);
	
	@Query("start u=node:Course({0})match (u)<-[:MENTOR]->(g) return g;")
	Set<User> getMentors(Course cr);
	
	@Query("start u=node:Course({0})match (u)<-[:UNDER]->(g) return g;")
	Set<Topic> getTopics(Course cr);
	
	@Query("start u=node:Course({0}), s=node:Topic({1}) create unique (u)<-[r:UNDER]->(s);")
	void addTopic(Course y,Topic x);
	
	@Query("start u=node:User(nodeId={0}), s=node:Course(nodeId={1}) create unique (u)<-[r:MENTOR]->(s);")
	void addMentor(long us,long h);
}
