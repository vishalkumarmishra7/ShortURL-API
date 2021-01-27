/**
 * 
 */
package com.mishra.shorturlapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishra.shorturlapi.entity.Url;

/**
 * @author vishal
 *
 */

@Repository
public interface URLRepository extends JpaRepository<Url, Long>{
}
