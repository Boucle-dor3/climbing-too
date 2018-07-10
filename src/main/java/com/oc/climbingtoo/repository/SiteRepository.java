package com.oc.climbingtoo.repository;


import com.oc.climbingtoo.entity.Site;
import org.springframework.data.repository.Repository;


import java.util.List;

@org.springframework.stereotype.Repository
public interface SiteRepository extends Repository<Site, Integer> {

    Site save (Site entity);
    List<Site> findAll ();

    Site findById (Integer idSite);
    Boolean existsById (Integer idSite);
}
