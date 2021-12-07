package com.zieta.tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zieta.tms.model.WorkFlowRequestComments;

@Repository
public interface WorkflowRequestCommentRepository extends JpaRepository<WorkFlowRequestComments, Long> {
	
	public List<WorkFlowRequestComments> findByTsIdOrderByIdDesc(long tsId);
	
	@Query("select wfrc from WorkFlowRequestComments wfrc where wfrc.tsId =?1 ORDER BY wfrc.id DESC")
	public List<WorkFlowRequestComments> findByTsIdDesc(long tsId);
	

}
