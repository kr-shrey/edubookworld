package com.shaivyaednet.myapp.repository;
import java.util.Set;

import com.shaivyaednet.myapp.domain.*;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.repository.*;
public interface UserRepository extends GraphRepository<User>
{
	@Query("start u=node:User({0}), s=node:Course({1}) create unique (u)-[r:SUBSCRIBE]-(s);")
	void addCourse(User u,Course c);
	
	@Query("start u=node:User(usrName={0}), s=node:Organization({1}) create unique (u)<-[r:INSTITUTE]->(s);")
	void addInsti(User u,Organization us);
	
	@Query("start u=node:User({0}), s=node:LearningObject({1}) create unique (u)<-[r:USREPOSITORY]->(s);")
	void addToRepository(User u, LearningObject l);
	
	@Query("start u=node:User({0})match (u)<-[:SUBSCRIBE]->(g) return g;")
	Set<Course> getCourses(User u);
		
	@Query("start u=node:User({0})match (u)<-[:USREPOSITORY]->(g) return g;")
	Set<LearningObject> getRepository(User u);
	
	@Query("start u=node:User({0})match (u)<-[:INSTITUTE]->(g) return g;")
	Set<Organization> getInsties(User u);
	
	@Query("start u=node:User(nodeId={0}), s=node:Course(nodeId={1}) create unique (u)<-[r:MENTOR]->(s);")
	void addCourse(long us,long h, boolean n);
}