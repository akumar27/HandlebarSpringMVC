/**
 * 
 */
package com.voya.model;

import java.util.ArrayList;

/**
 * @author Amit Kumar
 *
 */
public class LinkCategory {
	private String categoryName;
	private ArrayList<Link> categoryLinks;
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the categoryLinks
	 */
	public ArrayList<Link> getCategoryLinks() {
		return categoryLinks;
	}
	/**
	 * @param categoryLinks the categoryLinks to set
	 */
	public void setCategoryLinks(ArrayList<Link> categoryLinks) {
		this.categoryLinks = categoryLinks;
	}
}
