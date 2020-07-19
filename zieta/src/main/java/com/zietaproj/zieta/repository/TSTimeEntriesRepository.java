package com.zietaproj.zieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zietaproj.zieta.model.TSTimeEntries;

@Repository
public interface TSTimeEntriesRepository extends JpaRepository<TSTimeEntries, Long> {

	List<TSTimeEntries> findByTsId(Long tsId);

	List<TSTimeEntries> findByTsIdAndIsDelete(Long tsId, short notDeleted);

	List<TSTimeentries> findByTsIdAndIsDelete(Long tsId, short notDeleted);

	
	
}
