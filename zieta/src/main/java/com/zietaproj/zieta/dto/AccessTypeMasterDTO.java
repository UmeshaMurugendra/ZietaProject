package com.zietaproj.zieta.dto;


public class AccessTypeMasterDTO {

	private Long id;
    private Long client_id;
    private String access_type;
    private String created_by;
    private String modified_by;
    private boolean IS_DELETE;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClient_id() {
		return client_id;
	}
	public void setClient_id(Long client_id) {
		this.client_id = client_id;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public boolean isIS_DELETE() {
		return IS_DELETE;
	}
	public void setIS_DELETE(boolean iS_DELETE) {
		IS_DELETE = iS_DELETE;
	}
    
    
}
