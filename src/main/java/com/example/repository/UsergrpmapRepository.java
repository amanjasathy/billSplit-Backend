package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Usergrpmap;

@Repository
public interface UsergrpmapRepository extends JpaRepository<Usergrpmap, Long> {
	@Query("Select ugm.gid from Usergrpmap ugm where ugm.uid=:uId1")
	List<Long> findGidByUid(@Param("uId1") Long uId1);

}
