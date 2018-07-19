package com.oc.climbingtoo.service;


import com.oc.climbingtoo.entity.Topo;
import com.oc.climbingtoo.repository.TopoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopoService {

    @Autowired
    private TopoRepository topoRepository;

    public List<Topo> getAll() {
        return topoRepository.findAll();
    }

    public Topo get(Integer idTopo) {
        return topoRepository.findById(idTopo);
    }

    public Topo create(Topo topo) {
        return topoRepository.save(topo);
    }

    public Topo delete(Topo topo) {
        return topoRepository.save(topo);
    }

    public Topo findById(Integer idTopo) {
        return topoRepository.findById(idTopo);
    }
}
