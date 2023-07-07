package com.dh.catalog.repository;
import com.dh.catalog.model.catalog.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String>{
    @Query("{'genre': ?0}")
    List<Catalog> findAllByGenre(String genre);

}
