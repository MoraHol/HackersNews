package models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class Notice extends Item {
	private String url;
	private String title;
	private String type;
	private ArrayList<Comment> comments;

	public Notice(User user,int id, String title, String url, Date createdAt) {
		super(user,id,createdAt);
		this.title = title;
		this.url = url;
		comments = new ArrayList<Comment>();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

	public String getDomainUrl() {
		try {
			URL url = new URL(this.url);
			return url.getHost();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
