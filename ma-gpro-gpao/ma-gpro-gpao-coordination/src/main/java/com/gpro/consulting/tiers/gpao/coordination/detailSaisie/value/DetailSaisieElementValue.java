package com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value;

import java.util.Calendar;

public class DetailSaisieElementValue implements Comparable<DetailSaisieElementValue>{

	private Long id;
	private String codeBarre;
	private Long paquetId;
	private Long elementGammeOFId;
	private Long quantite;
	private Long ficheSaisieId;

	//ces champs sont recuperés de plusieurs services
	private String operation;
	private Double temps;
	private Double minutage;
	private String chaine;
	private Calendar date;
	private String matricule;
	private String ordreFabricationNumero;
	private Calendar dateSaisie;
	
	private Long ordre;
	
	
	private Long idPersonnel;
	
	private Long idOPeration;
	
	
	private Long idChaine;
	
	
	
	
	public int compareTo(DetailSaisieElementValue element) {
		return (element.getCodeBarre().compareTo(codeBarre));
	}
	
	public DetailSaisieElementValue() {
		// TODO Auto-generated constructor stub
	}
	
	public DetailSaisieElementValue(Long id, Long quantite) {
		super();
		this.id = id;
		this.quantite = quantite;
	}

	public DetailSaisieElementValue(Long id, Long elementGammeOFId, Long quantite  , Calendar dateSaisie) {
		super();
		this.id = id;
		this.elementGammeOFId = elementGammeOFId;
		this.quantite = quantite;
		this.dateSaisie = dateSaisie;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Long getPaquetId() {
		return paquetId;
	}

	public void setPaquetId(Long paquetId) {
		this.paquetId = paquetId;
	}

	public Long getElementGammeOFId() {
		return elementGammeOFId;
	}

	public void setElementGammeOFId(Long elementGammeOFId) {
		this.elementGammeOFId = elementGammeOFId;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Double getMinutage() {
		return minutage;
	}

	public void setMinutage(Double minutage) {
		this.minutage = minutage;
	}

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Long getFicheSaisieId() {
		return ficheSaisieId;
	}

	public void setFicheSaisieId(Long ficheSaisieId) {
		this.ficheSaisieId = ficheSaisieId;
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Calendar getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public Long getIdPersonnel() {
		return idPersonnel;
	}

	public void setIdPersonnel(Long idPersonnel) {
		this.idPersonnel = idPersonnel;
	}

	public Long getIdOPeration() {
		return idOPeration;
	}

	public void setIdOPeration(Long idOPeration) {
		this.idOPeration = idOPeration;
	}

	public Long getIdChaine() {
		return idChaine;
	}

	public void setIdChaine(Long idChaine) {
		this.idChaine = idChaine;
	}
	
	
	
}
