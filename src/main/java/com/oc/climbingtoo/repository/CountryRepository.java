package com.oc.climbingtoo.repository;

import com.oc.climbingtoo.entity.Country;
import com.oc.climbingtoo.entity.Site;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CountryRepository extends Repository<Site, Integer> {

    Site save (Site entity);
    List<Country> findAll ();

    Site findById (int idCountry);
}
