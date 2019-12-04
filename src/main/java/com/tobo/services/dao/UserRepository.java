/**
 * 
 */
package com.tobo.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobo.services.model.User;

/**
 * @author PRASADBolla
 *
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	 List<User> findByUsername(String username);
}
