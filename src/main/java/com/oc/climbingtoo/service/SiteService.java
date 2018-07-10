package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    public List<Site> getAll() {
     return siteRepository.findAll();
    }

    public Site get(Integer idSite) {
        return siteRepository.findById(idSite);
    }

    public Site create(Site site) {
        return siteRepository.save(site);
    }

    public Site delete(Site site) {
        return siteRepository.save(site);
    }

    public Site findById(Integer idSite) {
        return siteRepository.findById(idSite);
    }
}
