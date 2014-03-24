/**
 * 
 */
package com.fms.model;


public class Inspection {


	private Long id;
	private String time;

	public void setId(Long id) {
		this.id = id;
	}

	private String summary;


	private Facility facility;

	public Inspection() {
	}

	public Inspection(String time, String summary) {
		this.time = time;
		this.summary = summary;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Inspection [id=" + id + ", time=" + time + ", summary=" + summary + "]";
	}

}// end class
