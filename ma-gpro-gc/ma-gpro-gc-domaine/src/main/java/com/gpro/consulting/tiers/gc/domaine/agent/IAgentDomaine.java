package com.gpro.consulting.tiers.gc.domaine.agent;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IAgentDomaine {

	public String creer(AgentValue value);
	
	public void supprimer(Long pId);
	
	public String modifier(AgentValue value);
	
	public List<AgentValue> listeAgent();
}
