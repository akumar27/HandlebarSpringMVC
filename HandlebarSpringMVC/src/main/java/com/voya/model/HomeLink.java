/**
 * 
 */
package com.voya.model;

import java.util.ArrayList;

/**
 * @author i707259
 *
 */
public class HomeLink {
	private String title;
	private ArrayList<LinkCategory> links;

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
	 * @return the links
	 */
	public ArrayList<LinkCategory> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(ArrayList<LinkCategory> links) {
		this.links = links;
	}
}
