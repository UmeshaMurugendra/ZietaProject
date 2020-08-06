package com.zietaproj.zieta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessStepsDTO {

	private Long id;
    private Long clientId;
    private Long projectId;
    private Long templateId;
    private Long projectTaskId;
    private Long stepId;
    private String approverId;
    
    //description details
    
    private String clientDescription;
    private String projectDescription;
    private String taskDescription;
    private String processDescription;
}