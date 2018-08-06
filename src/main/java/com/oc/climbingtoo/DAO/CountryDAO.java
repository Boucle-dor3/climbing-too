package com.oc.climbingtoo.DAO;

import com.oc.climbingtoo.entity.Country;
import com.oc.climbingtoo.entity.Site;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface CountryDAO extends Repository<Site, Integer> {

    Country save (Country entity);
    List<Country> findAll ();

    Site findById (int idCountry);
}
