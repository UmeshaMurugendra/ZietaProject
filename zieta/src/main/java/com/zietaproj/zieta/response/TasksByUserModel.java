package com.zietaproj.zieta.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TasksByUserModel {
	long projectId;
	long taskId;
	long userId;
	String projectName;
	String taskName;
	String projectCode;
	String taskCode;

}
