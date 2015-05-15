/**
 * 
 */
package com.voya.model;

import java.util.ArrayList;

/**
 * @author i707259
 *
 */
public class Post {
	private String author;
	private String title;
	private String body;
	private ArrayList<Comment> commentList;
	private PostSource postSource;
	public Post(String author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;
	}

	public Post() {

	}

	// private String
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the commentList
	 */
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}

	/**
	 * @param commentList
	 *            the commentList to set
	 */
	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the postSource
	 */
	public PostSource getPostSource() {
		return postSource;
	}

	/**
	 * @param postSource the postSource to set
	 */
	public void setPostSource(PostSource postSource) {
		this.postSource = postSource;
	}
}
