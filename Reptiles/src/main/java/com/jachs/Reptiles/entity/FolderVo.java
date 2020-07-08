package com.jachs.Reptiles.entity;

import java.util.List;
/***
 * 文件夹实体
 * @author zch
 *
 */
public class FolderVo {
	private String UrlPath;
	private String FolderName;
	private List<FileVo>FileList;
	
	public FolderVo() {
		super();
	}
	public FolderVo(String urlPath, String folderName, List<FileVo> fileList) {
		super();
		UrlPath = urlPath;
		FolderName = folderName;
		FileList = fileList;
	}
	public String getUrlPath() {
		return UrlPath;
	}
	public void setUrlPath(String urlPath) {
		UrlPath = urlPath;
	}
	public String getFolderName() {
		return FolderName;
	}
	public void setFolderName(String folderName) {
		FolderName = folderName;
	}
	public List<FileVo> getFileList() {
		return FileList;
	}
	public void setFileList(List<FileVo> fileList) {
		FileList = fileList;
	}
	
}
