package com.hacker.mail.dao;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"eventid", "empID"})
	)

@Entity
public class Feedback {
	 @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	 public int empID;
	 public int rating;
	 public String description;
	 public String improvement;
	 public String eventid;
	 public String businessUnit;
	 public String beneficiary;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Event event;

	/**
	 * @return the empID
	 */
	public int getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the longDesc
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param longDesc the longDesc to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the eventID
	 */
	public String getEventID() {
		return eventid;
	}
	/**
	 * @param eventID the eventID to set
	 */
	public void setEventID(String eventID) {
		this.eventid = eventID;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the businessUnit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}
	/**
	 * @param businessUnit the businessUnit to set
	 */
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}
	public String getImprovement() {
		return improvement;
	}
	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}
	public String getEventid() {
		return eventid;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	

}
