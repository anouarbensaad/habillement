package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
* Classe: SanadardTaille
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_STANDARD_TAILLE)
public class StandardTailleEntite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2529450627296247337L;

		@Id
		@SequenceGenerator(name="STANDARD_TAILLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_STANDARD_TAILLE,allocationSize=1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="STANDARD_TAILLE_ID_GENERATOR")
		private Long id;
		
		/** The designation. */
		@Column(name = "designation")
		private String designation;

		/** The bl suppression. */
		@Column(name = "bl_suppression")
		private Boolean blSuppression;

		/** The date suppression. */
		@Column(name = "date_suppression")
		private Calendar dateSuppression;

		/** The date creation. */
		@Column(name = "date_creation")
		private Calendar dateCreation;

		/** The date modification. */
		@Column(name = "date_modification")
		private Calendar dateModification;

		/************* Getters & Setters *****************/
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public Boolean isBlSuppression() {
			return blSuppression;
		}

		public void setBlSuppression(Boolean blSuppression) {
			this.blSuppression = blSuppression;
		}

		public Calendar getDateSuppression() {
			return dateSuppression;
		}

		public void setDateSuppression(Calendar dateSuppression) {
			this.dateSuppression = dateSuppression;
		}

		public Calendar getDateCreation() {
			return dateCreation;
		}

		public void setDateCreation(Calendar dateCreation) {
			this.dateCreation = dateCreation;
		}

		public Calendar getDateModification() {
			return dateModification;
		}

		public void setDateModification(Calendar dateModification) {
			this.dateModification = dateModification;
		}
		
		/************* Equals & ToString *****************/
		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}
		
		@Override
		public String toString() {
			return super.toString();
		}	
		
}
