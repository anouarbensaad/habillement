package com.gpro.consulting.tiers.gpao.persitance.abc.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Entity
@Table(name = IConstante.TABLE_GP_PRODUCTION)
public class ProductionEntity  implements Serializable {

	private static final long serialVersionUID = 7294873327307984224L;
	
	@Id
	@SequenceGenerator(name = "PRODUCTION_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_PRODUCTION, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTION_ID_GENERATOR")
	private Long id;
	
	@Column(name = "DATE")
	private Calendar date;
	
	@Column(name = "CHAINE_ID")
	private Long chaineId;
	
	@Column(name = "CHAINE_DESIGNATION")
	private String chaineDesignation;
	
	@Column(name = "PRODUIT_ID")
	private Long produitId;
	
	@OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProductionElementEntity> listProductionElement;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Set<ProductionElementEntity> getListProductionElement() {
		return listProductionElement;
	}

	public void setListProductionElement(
			Set<ProductionElementEntity> listProductionElement) {
		this.listProductionElement = listProductionElement;
	}
	
	

}
