package com.jachs.Reptiles.entity;

/***
 * 文件实体
 * @author zch
 *
 */
public class FileVo {
	private String UrlPath;
	private String FileName;
	private StringBuffer Info;
	
	public FileVo() {
		super();
	}
	public FileVo(String urlPath, String fileName, StringBuffer info) {
		super();
		UrlPath = urlPath;
		FileName = fileName;
		Info = info;
	}
	public String getUrlPath() {
		return UrlPath;
	}
	public void setUrlPath(String urlPath) {
		UrlPath = urlPath;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public StringBuffer getInfo() {
		return Info;
	}
	public void setInfo(StringBuffer info) {
		Info = info;
	}
}
