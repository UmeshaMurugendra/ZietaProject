package com.zieta.tms.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zieta.tms.dto.CustInfoDTO;
import com.zieta.tms.dto.LeaveInfoDTO;
import com.zieta.tms.dto.LeaveTypeMasterDTO;
import com.zieta.tms.model.CustInfo;
import com.zieta.tms.model.LeaveInfo;
import com.zieta.tms.model.LeaveTypeMaster;
import com.zieta.tms.response.CustomerInformationModel;
import com.zieta.tms.service.LeaveInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "Leave Informationn API")
public class LeaveController {

	@Autowired
	LeaveInfoService leaveInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LeaveController.class);
	
	
	@RequestMapping(value = "addLeaveInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addLeaveInfo(@Valid @RequestBody LeaveInfo leaveinfo) {
		leaveInfoService.addLeaveInfo(leaveinfo);
	}
	
	
	@ApiOperation(value = "Updates the UserRoles for the provided Id", notes = "Table reference: cust_info")
	@RequestMapping(value = "editLeaveInfoById", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editLeaveInfoById(@Valid @RequestBody LeaveInfoDTO leaveinfoDTO) throws Exception {
		leaveInfoService.editleaveInfoById(leaveinfoDTO);
		
		
	}
	
	@ApiOperation(value = "List Leaves Info", notes = "Table reference:leave_info")
	@RequestMapping(value = "getAllLeaveInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LeaveInfoDTO>  getAllLeaveInfo() {
		List<LeaveInfoDTO> leaveInformationList = null;
		try {
			leaveInformationList = leaveInfoService.getAllLeaveInfo();
		} catch (Exception e) {
			LOGGER.error("Error Occured in LeaveInfoController#getAllLeaveInfo",e);
		}
		return leaveInformationList;
	}
	
	
	@ApiOperation(value = "List leaves based on the clientId", notes = "Table reference: leave_info")
	@RequestMapping(value = "getAllLeavesByClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LeaveInfoDTO>> getAllLeavesByClient(@RequestParam(required = true) Long  clientId) {
		try {
			List<LeaveInfoDTO> cusInfoList = leaveInfoService.getAllLeavesByClient(clientId);

			return new ResponseEntity<List<LeaveInfoDTO>>(cusInfoList, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<LeaveInfoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	/////////////////////////////
	
	
	@RequestMapping(value = "addLeaveTypeMaster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addLeaveTypeMaster(@Valid @RequestBody LeaveTypeMaster leavemaster) {
		leaveInfoService.addLeaveTypeMaster(leavemaster);
	}
	
	
	@ApiOperation(value = "Updates the UserLeaves for the provided Id", notes = "Table reference: cust_info")
	@RequestMapping(value = "editLeaveMasterById", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editLeaveMasterById(@Valid @RequestBody LeaveTypeMasterDTO leavemasterDTO) throws Exception {
		leaveInfoService.editLeaveMasterById(leavemasterDTO);
		
		
	}
	
	
	
	@ApiOperation(value = "List Leaves Info", notes = "Table reference:leave_type_master")
	@RequestMapping(value = "getAllLeaveMasterInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LeaveTypeMasterDTO>  getAllLeaveMasterInfo() {
		List<LeaveTypeMasterDTO> leaveInformationList = null;
		try {
			leaveInformationList = leaveInfoService.getAllLeaveMaster();
		} catch (Exception e) {
			LOGGER.error("Error Occured in LeaveMasterController#getAllLeaveMaster",e);
		}
		return leaveInformationList;
	}
	
	@ApiOperation(value = "List leaves based on the clientId", notes = "Table reference: leave_info")
	@RequestMapping(value = "getAllLeaveTypesByClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LeaveTypeMasterDTO>> getAllLeaveTypesByClient(@RequestParam(required = true) Long  clientId) {
		try {
			List<LeaveTypeMasterDTO> cusInfoList = leaveInfoService.getAllLeaveTypesByClient(clientId);

			return new ResponseEntity<List<LeaveTypeMasterDTO>>(cusInfoList, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<LeaveTypeMasterDTO>>(HttpStatus.NOT_FOUND);
		}
	}
}