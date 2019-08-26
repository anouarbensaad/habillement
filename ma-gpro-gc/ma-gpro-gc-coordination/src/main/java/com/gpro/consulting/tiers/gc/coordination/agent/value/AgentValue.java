package com.gpro.consulting.tiers.gc.coordination.agent.value;

/**
 * @author Ameni Berrich
 *
 */
public class AgentValue implements Comparable<AgentValue>{

	private Long id;

	private String nom;

	private String observations;

	@Override
	public int compareTo(AgentValue o) {
		//AgentValue element= (AgentValue)o;
		//return (element.compareTo(date));
		
		return 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

}
