/**
 * 
 */
package com.voya.model;

/**
 * @author i707259
 *
 */
public class Comment {
	private String author;
	private String body;

	public Comment(String author, String body) {
		this.author = author;
		this.body = body;
	}

	public Comment() {

	}

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
}
