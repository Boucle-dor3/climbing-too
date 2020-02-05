package com.oc.climbingtoo.repositories;

import com.oc.climbingtoo.entity.Country;
import com.oc.climbingtoo.entity.Site;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryDAO extends CrudRepository<Country, Integer> {

    List<Country> findAll();

}
