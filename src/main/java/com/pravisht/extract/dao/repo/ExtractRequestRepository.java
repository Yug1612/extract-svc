package com.pravisht.extract.dao.repo;

import com.pravisht.extract.dao.entities.ExtractRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExtractRequestRepository extends MongoRepository<ExtractRequestEntity, String> {
}
