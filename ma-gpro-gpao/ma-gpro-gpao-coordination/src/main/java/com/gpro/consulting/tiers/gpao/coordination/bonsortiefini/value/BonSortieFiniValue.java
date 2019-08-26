package com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;

/**
 * 
 * @author Wahid Gazzah
 * @since 08/01/2015
 *
 */
public class BonSortieFiniValue implements Comparable<Object>{
	
    private Long id;
	private String reference;
	private Calendar dateSortie;
	public List<ColisValue> getListeRouleauFini() {
		return listeRouleauFini;
	}
	public void setListeRouleauFini(List<ColisValue> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}
	private String observation;
	private Integer nbColis; 
	private Double metrageTotal;
	private String type; 
	private Long partieInt;
	private String partieIntDesignation;
	private Double poidsFini;
	private Double poidsEcru;
	private String nature;

    private String blExport ;
	
	
    private String fini;
	
    
    private String referenceBon;
    
    
    private Integer nbColisReel;
    
    
    private Boolean special;
    
    
    

	public Boolean getSpecial() {
		return special;
	}
	public void setSpecial(Boolean special) {
		this.special = special;
	}
	public String getReferenceBon() {
		return referenceBon;
	}
	public void setReferenceBon(String referenceBon) {
		this.referenceBon = referenceBon;
	}
	private List<ColisValue> listeRouleauFini = new ArrayList<ColisValue>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Integer getNbColis() {
		return nbColis;
	}
	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getPartieInt() {
		return partieInt;
	}
	public void setPartieInt(Long partieInt) {
		this.partieInt = partieInt;
	}

	public Double getPoidsFini() {
		return poidsFini;
	}
	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}
	public Double getPoidsEcru() {
		return poidsEcru;
	}
	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}
	
	/** Accesseur en lecture de l'attribut partieIntDesignation.
	 * @return the partieIntDesignation
	 */
	public String getPartieIntDesignation() {
		return partieIntDesignation;
	}
	/**
	 * @param partieIntDesignation the partieIntDesignation to set
	 */
	public void setPartieIntDesignation(String partieIntDesignation) {
		this.partieIntDesignation = partieIntDesignation;
	}
	

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}
	/**
	 * @param nature the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getBlExport() {
		return blExport;
	}
	public void setBlExport(String blExport) {
		this.blExport = blExport;
	}
	
	
	
	
	public Integer getNbColisReel() {
		return nbColisReel;
	}
	public void setNbColisReel(Integer nbColisReel) {
		this.nbColisReel = nbColisReel;
	}
	public String getFini() {
		return fini;
	}
	public void setFini(String fini) {
		this.fini = fini;
	}
	@Override
	public int compareTo(Object arg0) {
		BonSortieFiniValue element= (BonSortieFiniValue)arg0;
		return (element.getId().compareTo(id));
	}
	
	
	
}
