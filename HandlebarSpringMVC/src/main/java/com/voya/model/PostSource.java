/**
 * 
 */
package com.voya.model;

/**
 * @author i707259
 *
 */
public class PostSource {
	private String device;
	private String ipAddress;

	public PostSource() {
	}

	public PostSource(String device, String ipAddress) {
		this.device = device;
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
