package com.gpro.consulting.tiers.gc.domaine.agent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;
import com.gpro.consulting.tiers.gc.domaine.agent.IAgentDomaine;
import com.gpro.consulting.tiers.gc.persitance.agent.IAgentPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class AgentDomaineImpl implements IAgentDomaine {

	@Autowired
	IAgentPersistance agentPersistance;
	
	@Override
	public List<AgentValue> listeAgent() {
		
		return agentPersistance.listeAgent();
	}

	@Override
	public String creer(AgentValue value) {
		return agentPersistance.creer(value);
	}

	@Override
	public void supprimer(Long pId) {
		agentPersistance.supprimer(pId);		
	}

	@Override
	public String modifier(AgentValue value) {
		return agentPersistance.modifier(value);
	}

}
