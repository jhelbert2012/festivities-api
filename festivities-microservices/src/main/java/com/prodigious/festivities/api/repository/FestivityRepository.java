package com.prodigious.festivities.api.repository;

import com.prodigious.festivities.api.bean.Festivity;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "festivities", path = "festivities")
public interface FestivityRepository extends MongoRepository<Festivity, String> {

    List<Festivity> findByName(@Param("name") String name);
    List<Festivity> findByPlace(@Param("place") String place);
    List<Festivity> findByStart(@Param("start") Date start);
    List<Festivity> findByStartBetween(@Param("start") Date start, @Param("end") Date end);
//      @Query("select b from Festivity b " +
//         "where b.start between ?1 and ?2 and b.end between ?1 and ?2")
//    List<Festivity> findByEndBetween(@Param("start") Date start, @Param("end") Date end);
    List<Festivity> findByStartAfter(@Param("start") Date start);
}
