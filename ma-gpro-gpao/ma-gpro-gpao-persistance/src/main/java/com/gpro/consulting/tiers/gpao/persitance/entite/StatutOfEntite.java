/**
 * 
 */
package com.gpro.consulting.tiers.gpao.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * Classe: Chaine
 * 
 * @author: $AMENI
 * 
 */

@Entity
@Table(name = IConstante.TABLE_GP_STATUTOF)
public class StatutOfEntite implements Serializable {
	
	private static final long serialVersionUID = -3737012263293290486L;

	@Id
	@SequenceGenerator(name = "GP_STATUTOF_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_STATUTOF, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_STATUTOF_ID_GENERATOR")
	private Long id;

	/** The designation. */
	@Column(name = "designation")
	private String designation;
	
	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/**************** Getter & Setter *************/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}