package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Calendar;


public class RechercheMulticriterePhaseOfValue {
	
	
	/** The eb_phase_id. */
	private Long phaseId;
	
	/** The methode. */
	private String methode;
	
	
	/** The date DebutReel. */
	private Calendar dateDebut;
	
	/** The date DebutReel. */
	private Calendar dateFin;
	
	/** The pi_pi_id. */
	private Long clientId;
	
    private String numero;
    
  

	
	/********** Getter & Setter ************/


	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public Calendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	




	

	
	


	

}
