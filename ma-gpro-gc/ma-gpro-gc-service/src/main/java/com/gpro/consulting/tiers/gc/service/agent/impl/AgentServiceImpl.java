package com.gpro.consulting.tiers.gc.service.agent.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;
import com.gpro.consulting.tiers.gc.domaine.agent.IAgentDomaine;
import com.gpro.consulting.tiers.gc.service.agent.IAgentService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class AgentServiceImpl implements IAgentService {

	@Autowired
	IAgentDomaine agentDomaine;

	@Override
	public List<AgentValue> listeAgent() {
		return agentDomaine.listeAgent();
	}

	@Override
	public String creer(AgentValue value) {
		return agentDomaine.creer(value);
	}

	@Override
	public void supprimer(Long pId) {
		agentDomaine.supprimer(pId);
	}

	@Override
	public String modifier(AgentValue value) {
		return agentDomaine.modifier(value);
	}
	
	
}
