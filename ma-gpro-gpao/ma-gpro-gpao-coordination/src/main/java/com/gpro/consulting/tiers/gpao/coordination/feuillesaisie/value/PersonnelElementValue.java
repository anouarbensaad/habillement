package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

/**
 * @author Ameni Berrich
 *
 */
public class PersonnelElementValue implements Comparable<PersonnelElementValue>{

	private Long id;
	private String matricule;
	private String nom;
	private String prenom;

	public int compareTo(PersonnelElementValue element) {
		return (element.getId().compareTo(id));
	}
	
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

}
