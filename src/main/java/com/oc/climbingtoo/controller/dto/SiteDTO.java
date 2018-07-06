package com.oc.climbingtoo.controller.dto;

import com.oc.climbingtoo.entity.Site;
import com.oc.climbingtoo.entity.Way;
import com.oc.climbingtoo.enumeration.SiteType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;


public class SiteDTO {

    @Getter @Setter
    private String siteName;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private SiteType type;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String rockClimbing;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String accessApproach;

    @Column(columnDefinition="TEXT")
    @Getter @Setter
    private String hostingRefueling;

    @Getter @Setter
    private Integer height;

    @Getter @Setter
    private Integer spit;

    @Getter @Setter
    private String climbingRating;



    public Site toConvertSite(){
        Site site = new Site();
        site.setSiteName(this.getSiteName());
        site.setDescription(this.getDescription());
        site.setType(this.getType());
        site.setRockClimbing(this.getRockClimbing());
        site.setAccessApproach(this.getAccessApproach());
        site.setHostingRefueling(this.getHostingRefueling());
        return site ;
    }


}
