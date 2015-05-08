package com.voya.model;

import java.util.List;

public class NavigationLink {
	private String label;
	private String href;
	private List<NavigationLink> subNavigationLinkList = null;

	public String toString() {
		StringBuffer br = new StringBuffer();
		br.append("\n\t<NavigationLink>").append("\n\t\t<Label>").append(label)
				.append("</Label>")
		.append("\n\t\t<Href>").append(href)
		.append("</Href>");
		if (subNavigationLinkList != null) {
			for(NavigationLink tempNavigationLink: subNavigationLinkList){
				br.append(tempNavigationLink.toString());
			}
			
		} else {
			br.append("\n\t\t</SubNavigationLinkList>\n");
		}

		br.append("\n\t</NavigationLink>");
		return br.toString();
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the subNavigationLinkList
	 */
	public List<NavigationLink> getSubNavigationLinkList() {
		return subNavigationLinkList;
	}

	/**
	 * @param subNavigationLinkList
	 *            the subNavigationLinkList to set
	 */
	public void setSubNavigationLinkList(
			List<NavigationLink> subNavigationLinkList) {
		this.subNavigationLinkList = subNavigationLinkList;
	}
}
