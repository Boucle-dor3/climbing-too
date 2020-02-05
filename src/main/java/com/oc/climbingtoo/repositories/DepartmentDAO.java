package com.oc.climbingtoo.repositories;

import com.oc.climbingtoo.entity.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentDAO extends CrudRepository<Department, Integer> {

	List<Department> findAll();

}
