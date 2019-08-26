package com.gpro.consulting.tiers.commun.coordination.value;

public class TailleValue implements Comparable<TailleValue>{

	/** Id. */
	private Long id;
	
	/** The designation. */
	private String designation;

	/** The eb_sttaille_id . */
	private Long eb_sttaille_id ;
	
	/** the ordre. */
	private Integer ordre;

/******************* Getter & Setter ************************/
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

	public Long getEb_sttaille_id() {
		return eb_sttaille_id;
	}

	public void setEb_sttaille_id(Long eb_sttaille_id) {
		this.eb_sttaille_id = eb_sttaille_id;
	}

	/**
	 * @return the ordre
	 */
	public Integer getOrdre() {
	
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	@Override
	public int compareTo(TailleValue arg0) {
		// TODO Auto-generated method stub
		 TailleValue element= (TailleValue)arg0;
		return (ordre.compareTo(element.getOrdre()));
	}

	
	

}
