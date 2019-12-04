/**
 * 
 */
package com.tobo.services.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tobo.services.model.Todo;

/**
 * @author PRASADBolla
 *
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
 List<Todo> findByUsername(String username);
}
