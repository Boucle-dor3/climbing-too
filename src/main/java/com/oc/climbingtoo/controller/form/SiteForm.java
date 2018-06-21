package com.oc.climbingtoo.controller.form;

import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.enumeration.SiteType;
import lombok.Getter;
import lombok.Setter;


public class SiteForm {

    @Getter @Setter
    private String siteName;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private SiteType type;

    public Site toSite() {
        Site site = new Site();
        site.setSiteName(this.getSiteName());
        site.setDescription(this.getDescription());
        site.setType(this.getType());
        return site;
    }

}
