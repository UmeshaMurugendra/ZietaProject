package com.zietaproj.zieta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zietaproj.zieta.model.WorkflowRequestHistory;

@Repository
public interface WorkflowRequestHistoryRepository extends JpaRepository<WorkflowRequestHistory, Long> {
	
	
	public List<WorkflowRequestHistory> findByTsIdOrderByActionDateDesc(long tsId);
	

}
