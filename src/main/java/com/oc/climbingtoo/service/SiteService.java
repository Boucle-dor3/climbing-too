package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.DAO.SiteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    public List<Site> getAll() {
     return siteDAO.findAll();
    }

    public Site get(Integer idSite) {
        return siteDAO.findById(idSite);
    }

    public Site create(Site site) {
        return siteDAO.save(site);
    }

    public Site delete(Site site) {
        return siteDAO.save(site);
    }

    public Site findById(Integer idSite) {
        return siteDAO.findById(idSite);
    }
}
