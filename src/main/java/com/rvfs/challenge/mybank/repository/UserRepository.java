package com.rvfs.challenge.mybank.repository;

import com.rvfs.challenge.mybank.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for user repository operations.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Fin user by email.
     * @param email Email for search.
     * @return User data.
     */
    User findByEmail(String email);

}
