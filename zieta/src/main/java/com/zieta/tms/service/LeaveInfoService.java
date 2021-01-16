package com.zieta.tms.service;

import java.util.List;

import javax.validation.Valid;

import com.zieta.tms.dto.CustInfoDTO;
import com.zieta.tms.dto.LeaveInfoDTO;
import com.zieta.tms.dto.LeaveTypeMasterDTO;
import com.zieta.tms.model.LeaveInfo;
import com.zieta.tms.model.LeaveTypeMaster;

public interface LeaveInfoService {

	public void addLeaveInfo(@Valid LeaveInfo leaveinfo);

	public void editleaveInfoById(@Valid LeaveInfoDTO leaveinfoDTO) throws Exception;

	public void addLeaveTypeMaster(@Valid LeaveTypeMaster leavemaster);

	public void editLeaveMasterById(@Valid LeaveTypeMasterDTO leavemasterDTO) throws Exception;

	public List<LeaveInfoDTO> getAllLeaveInfo();

	public List<LeaveTypeMasterDTO> getAllLeaveMaster();

	public List<LeaveInfoDTO> getAllLeavesByClient(Long clientId);

	public List<LeaveTypeMasterDTO> getAllLeaveTypesByClient(Long clientId);



	
	
}