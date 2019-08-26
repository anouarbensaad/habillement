package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
@Entity
@Table(name=IConstante.TABLE_GP_PERSONNEL)
public class PersonnelEntity implements Serializable {

	private static final long serialVersionUID = 8173475315443424028L;

	@Id
	@SequenceGenerator(name="PERSONNEL_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_PERSONNEL, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSONNEL_ID_GENERATOR")
    private Long id;
	
	@Column(name = "MATRICULE")
	private String matricule;
	
	@Column(name = "NOM")
	private String nom;
	
	@Column(name = "PRENOM")
	private String prenom;
	
	@Column(name = "INDIRECT")
	private boolean indirect;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public boolean isIndirect() {
		return indirect;
	}

	public void setIndirect(boolean indirect) {
		this.indirect = indirect;
	}
	
}
