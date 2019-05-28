package com.oc.climbingtoo.repositories;

import com.oc.climbingtoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
