package com.gpro.consulting.tiers.gpao.persitance.abc.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Entity
@Table(name = IConstante.TABLE_GP_PRODUCTION_ELEMENT)
public class ProductionElementEntity implements Serializable {

	private static final long serialVersionUID = -4974486888107350466L;
	
	@Id
	@SequenceGenerator(name = "PRODUCTION_ELEMENT_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_PRODUCTION_ELEMENT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTION_ELEMENT_ID_GENERATOR")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "GP_PRODUCTION_ID")
	private ProductionEntity production;
	
	@Column(name = "HEURE")
	private Long heure;
	
	@Column(name = "DEM")
	private Long dem;
	
	@Column(name = "PROD")
	private Long prod;
	
	@Column(name = "REND")
	private Double rend;
	
	@Column(name = "DATE")
	private Calendar date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductionEntity getProduction() {
		return production;
	}

	public void setProduction(ProductionEntity production) {
		this.production = production;
	}

	public Long getHeure() {
		return heure;
	}

	public void setHeure(Long heure) {
		this.heure = heure;
	}

	public Long getDem() {
		return dem;
	}

	public void setDem(Long dem) {
		this.dem = dem;
	}

	public Long getProd() {
		return prod;
	}

	public void setProd(Long prod) {
		this.prod = prod;
	}

	public Double getRend() {
		return rend;
	}

	public void setRend(Double rend) {
		this.rend = rend;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	

}
