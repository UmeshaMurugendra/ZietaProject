package com.zietaproj.zieta.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zietaproj.zieta.dto.ProjectMasterDTO;
import com.zietaproj.zieta.model.ProjectInfo;
import com.zietaproj.zieta.model.ProjectMaster;
import com.zietaproj.zieta.request.EditProjStatusRequest;
import com.zietaproj.zieta.request.ProjectMasterEditRequest;
import com.zietaproj.zieta.request.RoleMasterEditRequest;
import com.zietaproj.zieta.response.ProjectDetailsByUserModel;
import com.zietaproj.zieta.response.ProjectTypeByClientResponse;
import com.zietaproj.zieta.response.ProjectsByClientResponse;
import com.zietaproj.zieta.response.RolesByClientResponse;
import com.zietaproj.zieta.service.ProjectMasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "Projects API")
public class ProjectMasterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectMasterController.class);
	
	@Autowired
	ProjectMasterService projectmasterService;

	@RequestMapping(value = "getAllProjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDetailsByUserModel> getAllProjects() {
		List<ProjectDetailsByUserModel> projectList = null;

		try {
			projectList = projectmasterService.getAllProjects();
		} catch (Exception e) {
			LOGGER.error("Error Occured in ProjectMasterController#getAllProjects",e);
		}
		return projectList;
	}

	@RequestMapping(value = "addProjectMaster", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addProjectMaster(@Valid @RequestBody ProjectInfo projectinfo) {
		projectmasterService.addProjectinfo(projectinfo);
	}

	@ApiOperation(value = "List projects based on the  userId", notes = "Table reference: Project_Info,"
			+ " org_info, user_info, cust_info, client_info")
	@RequestMapping(value = "getAllProjectsByProjectManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProjectDetailsByUserModel>> getAllProjectsByProjectManager(@RequestParam(required = true) Long  userId) {
		try {
			List<ProjectDetailsByUserModel> projectByUserDetails = projectmasterService.getProjectsByUser(userId);

			return new ResponseEntity<List<ProjectDetailsByUserModel>>(projectByUserDetails, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<ProjectDetailsByUserModel>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getAllProjectsByClient")
	@ApiOperation(value = "List Projects based on the clientId", notes = "Table reference: project_type_master,user_info,project_info,org_info,cust_info")
	public ResponseEntity<List<ProjectDetailsByUserModel>> getAllProjectsByClient(@RequestParam(required = true) Long clientId) {
		try {
			List<ProjectDetailsByUserModel> projectsbyclientList = projectmasterService.getProjectsByClient(clientId);
			return new ResponseEntity<List<ProjectDetailsByUserModel>>(projectsbyclientList, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<ProjectDetailsByUserModel>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getProjectTypesByClient")
	@ApiOperation(value = "List ProjectTypes based on the clientId", notes = "Table reference: project_type_master")
	public ResponseEntity<List<ProjectTypeByClientResponse>> getProjectTypesByClient(@RequestParam(required = true) Long clientId) {
		try {
			List<ProjectTypeByClientResponse> projecttypesbyclientList = projectmasterService.getProjecttypessByClient(clientId);
			return new ResponseEntity<List<ProjectTypeByClientResponse>>(projecttypesbyclientList, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<List<ProjectTypeByClientResponse>>(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Updates the Projects for the provided Id", notes = "Table reference: project_info")
	@RequestMapping(value = "editProjectMaster", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editProjectMaster(@Valid @RequestBody ProjectMasterEditRequest projectmasterEditRequest) throws Exception {
		projectmasterService.editProjectsById(projectmasterEditRequest);
		
	}
	
	@ApiOperation(value = "Deletes entries from project_info based on Id", notes = "Table reference: project_info")
	@RequestMapping(value = "deleteProjectMasterById", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteProjectMasterById(@RequestParam(required=true) Long id, @RequestParam(required=true) String modifiedBy) throws Exception {
		projectmasterService.deleteProjectsById(id, modifiedBy);
	}
	
	
	
	
	@RequestMapping(value = "editProjectStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void editProjectStatus(@Valid @RequestBody EditProjStatusRequest editprojStatusRequest) throws Exception {
		projectmasterService.editProjectStatus(editprojStatusRequest);
	}
}
