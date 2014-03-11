/**
 * 
 */
package com.fms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Inspection {

	@Id
	@GeneratedValue
	private Long id;
	private String time;

	public void setId(Long id) {
		this.id = id;
	}

	private String summary;

	@ManyToOne
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
