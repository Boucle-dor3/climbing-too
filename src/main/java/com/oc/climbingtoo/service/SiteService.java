package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.QSite;
import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.repositories.SiteDAO;
import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.JPAQueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteDAO siteDAO;

    @Autowired
    private EntityManager entityManager;

    public List<Site> getAll(Integer department) {
        JPAQuery<Site> query = new JPAQuery<>(this.entityManager);
        QSite site = QSite.site;

        JPAQuery<Site> siteRequest =  query.select(site).from(site);

        if (department != null) {
            siteRequest = siteRequest.where(QSite.site.department.id.eq(department));
        }

        return siteRequest.fetch();
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
