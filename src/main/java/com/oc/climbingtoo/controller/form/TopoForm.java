package com.oc.climbingtoo.controller.form;

import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.enumeration.TopoType;
import lombok.Getter;
import lombok.Setter;


public class TopoForm {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private TopoType type;

    public Topo toTopo() {
        Topo topo = new Topo();
        topo.setTitle(this.getTitle());
        topo.setDescription(this.getDescription());
        topo.setType(this.getType());
        return topo;
    }

}
