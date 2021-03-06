package com.zieta.tms.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zieta.tms.dto.ExpenseWorkFlowRequestDTO;
import com.zieta.tms.request.WorkflowRequestProcessModel;
//import com.zieta.tms.response.ExpenseWFRCommentsDetails;
import com.zieta.tms.response.ExpenseWFRDetailsForApprover;
import com.zieta.tms.response.ExpenseWorkFlowComment;
import com.zieta.tms.service.ExpenseWorkFlowRequestService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Api(tags = "Expense WorkFlow API")
@Slf4j
public class ExpenseWorkFlowController {
	
	@Autowired
	ExpenseWorkFlowRequestService expenseWorkFlowRequestService;
	
	@RequestMapping(value = "getExpenseWorkFlowRequestsByApprover", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExpenseWFRDetailsForApprover> getExpenseWorkFlowRequestsByApprover(@RequestParam(required = true) Long approverId) {
		List<ExpenseWFRDetailsForApprover> workFlowRequestList = null;
		try {
			workFlowRequestList = expenseWorkFlowRequestService.findActiveWorkFlowRequestsByApproverId(approverId);
		} catch (Exception e) {

			log.error("Error Occured in TSWorkFlowController#getActiveWorkFlowRequestsByApprover", e);
		}
		return workFlowRequestList;

	}
	
	
	
	
	
	/*
	 * GET THE EXPENSE APPROVAL SIZE
	 */
	@RequestMapping(value = "getSizeExpenseWorkFlowRequestsByApprover", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int getSizeExpenseWorkFlowRequestsByApprover(@RequestParam(required = true) Long approverId) {
		List<ExpenseWFRDetailsForApprover> workFlowRequestList = null;
		int approvalSize =0;
		try {
			workFlowRequestList = expenseWorkFlowRequestService.findActiveWorkFlowRequestsByApproverId(approverId);
			if(workFlowRequestList!=null)
				approvalSize = workFlowRequestList.size();
		} catch (Exception e) {

			log.error("Error Occured in TSWorkFlowController#getActiveWorkFlowRequestsByApprover", e);
		}
		return approvalSize;

	}
	
	

	@RequestMapping(value = "processExpenseWorkFlow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void processExpenseWorkFlow(@Valid @RequestBody ExpenseWorkFlowRequestDTO expenseWorkFlowRequestDTO) throws Exception {
		expenseWorkFlowRequestService.processExpenseWorkFlow(expenseWorkFlowRequestDTO);
		
	}
	
	
	@RequestMapping(value = "getExpenseWorkFlowRequestsHistoryByApprover", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExpenseWFRDetailsForApprover> getExpenseWorkFlowRequestsHistoryByApprover(@RequestParam(required = true) Long approverId,
			@RequestParam(required = true) String  startRequestdate, 
			@RequestParam(required = true) String endRequestDate) {
		List<ExpenseWFRDetailsForApprover> workFlowRequestList = null;
		try {
			workFlowRequestList = expenseWorkFlowRequestService.findWorkFlowRequestsByApproverId(approverId, startRequestdate,endRequestDate );
		} catch (Exception e) {

			log.error("Error Occured in TSWorkFlowController#getActiveWorkFlowRequestsByApprover", e);
		}
		return workFlowRequestList;

	}
	
	@RequestMapping(value = "getExpenseCommentsByExpId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ExpenseWorkFlowComment> getExpenseCommentsByExpId(@RequestParam(required = true) Long expId) {
		List<ExpenseWorkFlowComment> workFlowRequestList = null;
		try {
			workFlowRequestList = expenseWorkFlowRequestService.getWFRCommentsChain(expId);
		} catch (Exception e) {

			log.error("Error Occured in ExpenseWorkFlowController#getExpenseCommentsByExpId", e);
		}
		return workFlowRequestList;

	}
	
}
