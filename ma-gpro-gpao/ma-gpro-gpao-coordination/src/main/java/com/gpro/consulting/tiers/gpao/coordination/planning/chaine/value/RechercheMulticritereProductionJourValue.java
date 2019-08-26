package com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value;

import java.util.Calendar;

/**
 * @author Hamdi Etteieb
 *
 */
public class RechercheMulticritereProductionJourValue {
	

	
	private Calendar dateDe ;
	private Calendar dateA ;
	private String chaineId ;
	
	private String numOF;
	
	private Long idChaine;
	
	private Long idPhase;
	
	private String semaine;
	
	private Long partieInterresId;	
	

	
	
	
	public Long getPartieInterresId() {
		return partieInterresId;
	}
	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}
	public Calendar getDateDe() {
		return dateDe;
	}
	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}
	public Calendar getDateA() {
		return dateA;
	}
	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}
	public String getChaineId() {
		return chaineId;
	}
	public void setChaineId(String chaineId) {
		this.chaineId = chaineId;
	}
	public String getNumOF() {
		return numOF;
	}
	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}
	public Long getIdChaine() {
		return idChaine;
	}
	public void setIdChaine(Long idChaine) {
		this.idChaine = idChaine;
	}
	public Long getIdPhase() {
		return idPhase;
	}
	public void setIdPhase(Long idPhase) {
		this.idPhase = idPhase;
	}
	public String getSemaine() {
		return semaine;
	}
	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}
	


	
	
	
	
}
