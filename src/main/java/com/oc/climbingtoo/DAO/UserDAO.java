package com.oc.climbingtoo.DAO;

import com.oc.climbingtoo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository ("userDAO")
public interface UserDAO extends CrudRepository<User, Long> {

    User findByUserName(String userName);
}
