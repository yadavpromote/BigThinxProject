package com.rkyadav.bigthinx.repositories;

import com.rkyadav.bigthinx.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author rkyadav
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    /**
     * Returns database user
     * @param name
     * @return 
     */
    public User findByName(String name);
}
