package org.solent.spring.map.repository;

import org.solent.spring.map.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    public Object findByUsername(String username);

}
