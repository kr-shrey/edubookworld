package com.shaivyaednet.myapp.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import com.shaivyaednet.myapp.domain.*;
public interface LearningObjectRepository extends GraphRepository<LearningObject> {

}
