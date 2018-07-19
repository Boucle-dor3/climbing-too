package com.oc.climbingtoo.repository;

import com.oc.climbingtoo.entity.Topo;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TopoRepository extends Repository<Topo, Integer> {

    Topo save (Topo entity);
    List<Topo> findAll ();

    Topo findById (int idTopo);

}
