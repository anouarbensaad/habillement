package com.gpro.consulting.tiers.gpao.coordination.value;



/**
 * @author Samer
 *
 */
public class RechercheMulticritereDetailOfValue {

	private Long id;

	/** The stock qte. */
	private Long qteStock;
	
	private Long ordre;

	private String numOF;

	private Long clientId;

	private String etatStock;
	
	
	

	public String getEtatStock() {
		return etatStock;
	}


	public void setEtatStock(String etatStock) {
		this.etatStock = etatStock;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getQteStock() {
		return qteStock;
	}


	public void setQteStock(Long qteStock) {
		this.qteStock = qteStock;
	}


	public Long getOrdre() {
		return ordre;
	}


	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}


	public Long getClientId() {
		return clientId;
	}


	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public String getNumOF() {
		return numOF;
	}


	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}
	
	
	
}
