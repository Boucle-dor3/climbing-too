package com.oc.climbingtoo.DAO;

import com.oc.climbingtoo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository ("userRepository")
public interface UserDAO extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}
