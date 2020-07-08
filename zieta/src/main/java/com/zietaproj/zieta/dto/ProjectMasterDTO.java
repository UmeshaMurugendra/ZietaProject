package com.zietaproj.zieta.dto;

import com.zietaproj.zieta.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ProjectMasterDTO extends BaseEntity {
	private Long id;
    private Long client_id;
    private String project_type;
//    private String created_by;
//    private String modified_by;
//    private boolean IS_DELETE;
    private String clientCode;
    private String projectCode;
    private String projectName;
}
