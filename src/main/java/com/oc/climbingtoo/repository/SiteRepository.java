package com.oc.climbingtoo.repository;


import com.oc.climbingtoo.entity.Site;
import org.springframework.data.repository.Repository;


import java.util.List;


public interface SiteRepository extends Repository<Site, Integer> {

    Site save (Site entity);
    List<Site> findAll ();

    Site findById (int idSite);

}
