package com.voya.model;

import java.util.List;

public class MegaNavigation {
	private String title;
	private List<NavigationLink> subNavigationList;

	public String toString() {
		StringBuffer br = new StringBuffer();
		br.append("<MegaNavigation>").append("\n\t<Title>").append(title)
				.append("</Title>");

		if (subNavigationList != null) {			
			for(NavigationLink tempNavigationLink: subNavigationList){
				br.append(tempNavigationLink.toString());
			}
		} else {
			br.append("\t</subNavigationList>\n");
		}

		br.append("\n</MegaNavigation>");
		return br.toString();
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
	 * @return the subNavigationList
	 */
	public List<NavigationLink> getSubNavigationList() {
		return subNavigationList;
	}

	/**
	 * @param subNavigationList
	 *            the subNavigationList to set
	 */
	public void setSubNavigationList(List<NavigationLink> subNavigationList) {
		this.subNavigationList = subNavigationList;
	}
}
