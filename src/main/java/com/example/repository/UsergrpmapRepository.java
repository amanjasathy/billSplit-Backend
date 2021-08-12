package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Usergrpmap;

@Repository
public interface UsergrpmapRepository extends JpaRepository<Usergrpmap, Long>{
	List<Long> findByUid(Long uid);
	

}
