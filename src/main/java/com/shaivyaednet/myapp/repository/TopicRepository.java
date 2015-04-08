package com.shaivyaednet.myapp.repository;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.shaivyaednet.myapp.domain.*;
public interface TopicRepository extends GraphRepository<Topic> {
	@Query("start u=node:Topic({0}), s=node:LearningObject({1}) create unique (u)<-[r:UNDERTOPIC]->(s);")
	void addLeO(Topic t, LearningObject l);
	
	@Query("start u=node:Topic({0})match (u)<-[:UNDERTOPIC]->(g) return g;")
	Set<LearningObject> getContent(Topic u);
}
