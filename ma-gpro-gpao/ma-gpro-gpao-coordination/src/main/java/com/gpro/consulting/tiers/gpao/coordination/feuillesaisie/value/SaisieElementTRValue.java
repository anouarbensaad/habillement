package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Zeineb Medimagh
 * @since 08/06/2017
 */
public class SaisieElementTRValue implements Comparable<SaisieElementTRValue> {
	
	private String code;
	private String matricule;
	private Calendar date;
	
	@Override
	public int compareTo(SaisieElementTRValue element) {
		
		return element.getDate().compareTo(date);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	
}