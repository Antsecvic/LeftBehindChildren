package Object;

import java.io.Serializable;



public class News implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newsData;
	private String tagIts;
	private String isLoad;
	private String isDeleted;
	private String memo;
	private String title;
	private String date;
	private String location;
	private String url;
	private String type;
	private String wordCount;
	private String ID;
	private String trueUrl;
	private Tags tags;
	private String encodedContent;
	
	public String getNewsData() {
		return newsData;
	}
	public void setNewsData(String newsData) {
		this.newsData = newsData;
	}
	public String getTagIts() {
		return tagIts;
	}
	public void setTagIts(String tagIts) {
		this.tagIts = tagIts;
	}
	public String getIsLoad() {
		return isLoad;
	}
	public void setIsLoad(String isLoad) {
		this.isLoad = isLoad;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWordCount() {
		return wordCount;
	}
	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTrueUrl() {
		return trueUrl;
	}
	public void setTrueUrl(String trueUrl) {
		this.trueUrl = trueUrl;
	}
	public Tags getTags() {
		return tags;
	}
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	public String getEncodedContent() {
		return encodedContent;
	}
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}		
}
