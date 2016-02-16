package com.prodigious.festivities.api.repository;

import com.prodigious.festivities.api.bean.Festivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "festivities", path = "festivities")
public interface FestivityRepository extends MongoRepository<Festivity, String> {

}
