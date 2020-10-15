package com.zieta.tms.service;

import java.util.Date;
import java.util.List;

import com.zieta.tms.model.WorkflowRequest;
import com.zieta.tms.request.WorkflowRequestProcessModel;
import com.zieta.tms.response.WFRDetailsForApprover;
import com.zieta.tms.response.WorkFlowComment;
import com.zieta.tms.response.WorkFlowHistoryModel;
import com.zieta.tms.response.WorkFlowRequestorData;




public interface WorkFlowRequestService {

	public List<WFRDetailsForApprover> findActiveWorkFlowRequestsByApproverId(long approverId);
	
	public List<WFRDetailsForApprover> findWorkFlowRequestsByApproverId(long approverId, Date startActiondate, Date endActionDate);
	

	public List<WorkFlowRequestorData> findByRequestorId(long requestorId);

	public WorkflowRequest findByTsIdAndApproverId(long tsId, long approverId);
	
	public void processWorkFlow(WorkflowRequestProcessModel workflowRequestProcessModel);
	
	public List<WorkFlowHistoryModel> getWorkFlowHistoryForTS(Long tsId);
	
	public List<WorkFlowComment> getWFRCommentsChain(long tsId);

}
