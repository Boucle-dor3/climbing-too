package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.DAO.TopoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopoService {

    @Autowired
    private TopoDAO topoDAO;

    public List<Topo> getAll() {
        return topoDAO.findAll();
    }

    public Topo get(Integer idTopo) {
        return topoDAO.findById(idTopo);
    }

    public Topo create(Topo topo) {
        return topoDAO.save(topo);
    }

    public Topo delete(Topo topo) {
        return topoDAO.save(topo);
    }

    public Topo findById(Integer idTopo) {
        return topoDAO.findById(idTopo);
    }
}
