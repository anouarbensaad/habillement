package com.gpro.consulting.tiers.gc.rest.agent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;
import com.gpro.consulting.tiers.gc.service.agent.IAgentService;
/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/agentGc")
public class AgentRestImpl {
	
private static final Logger logger = LoggerFactory.getLogger(AgentRestImpl.class);
	
	@Autowired
	private IAgentService agentService;
	
	@RequestMapping(value = "/creer", method = RequestMethod.POST)
	public @ResponseBody String creer(@RequestBody AgentValue pAgentValue) {
		return agentService.creer(pAgentValue);
	}

	@RequestMapping(value = "/modifier", method = RequestMethod.PUT)
	public @ResponseBody String modifier(
			@RequestBody AgentValue pAgentValue) {
		return this.agentService.modifier(pAgentValue);
	}

	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerFactureVente(@PathVariable("id") Long id) {
		
		agentService.supprimer(id);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<AgentValue> viewAllAgents() {
		logger.info("All: Delegating request to service layer.");
		
		return agentService.listeAgent();
	}
	
	
	
}
