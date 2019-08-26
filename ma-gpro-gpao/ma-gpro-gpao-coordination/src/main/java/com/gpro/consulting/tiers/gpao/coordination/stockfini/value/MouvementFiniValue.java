package com.gpro.consulting.tiers.gpao.coordination.stockfini.value;


import java.util.Calendar;




/**
 * @author Samer Hassen
 *
 */

public class MouvementFiniValue  {

  private Long id;

	private String numeroBon ;
	
	private String numeroOf ;
	
	
	private Long detailOfId;
	
	
	private Calendar date;
	

	private String type ;
		

	private Long quantite;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroBon() {
		return numeroBon;
	}

	public void setNumeroBon(String numeroBon) {
		this.numeroBon = numeroBon;
	}

	public Long getDetailOfId() {
		return detailOfId;
	}

	public void setDetailOfId(Long detailOfId) {
		this.detailOfId = detailOfId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getNumeroOf() {
		return numeroOf;
	}

	public void setNumeroOf(String numeroOf) {
		this.numeroOf = numeroOf;
	}



}
