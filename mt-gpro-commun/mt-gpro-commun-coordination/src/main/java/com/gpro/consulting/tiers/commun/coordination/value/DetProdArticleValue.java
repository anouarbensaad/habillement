package com.gpro.consulting.tiers.commun.coordination.value;

public class DetProdArticleValue {

private Long id;
	
	/** The quantite. */
	private long quantite;

	/** The bloquante. */
	private boolean bloquante;
	
	/** The ordre. */
	private long ordre;
	
	/** The phase. */
	private String phase;
	
	/** The eb_article_id. */
	private long eb_article_id;
	
	/** The eb_produitdet_id. */
	private long eb_produitdet_id;
	
	/************* Getters & Setters *****************/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	public boolean isBloquante() {
		return bloquante;
	}

	public void setBloquante(boolean bloquante) {
		this.bloquante = bloquante;
	}

	public long getOrdre() {
		return ordre;
	}

	public void setOrdre(long ordre) {
		this.ordre = ordre;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public long getEb_article_id() {
		return eb_article_id;
	}

	public void setEb_article_id(long eb_article_id) {
		this.eb_article_id = eb_article_id;
	}

	public long getEb_produitdet_id() {
		return eb_produitdet_id;
	}

	public void setEb_produitdet_id(long eb_produitdet_id) {
		this.eb_produitdet_id = eb_produitdet_id;
	}

}
	
