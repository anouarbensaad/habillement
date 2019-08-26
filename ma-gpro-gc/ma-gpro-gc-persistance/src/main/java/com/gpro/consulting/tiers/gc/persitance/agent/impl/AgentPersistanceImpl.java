package com.gpro.consulting.tiers.gc.persitance.agent.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;
import com.gpro.consulting.tiers.gc.persitance.agent.IAgentPersistance;
import com.gpro.consulting.tiers.gc.persitance.agent.entite.AgentEntity;
import com.gpro.consulting.tiers.gc.persitance.agent.utility.AgentPersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class AgentPersistanceImpl extends AbstractPersistance implements
IAgentPersistance {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AgentValue> listeAgent() {
		List<AgentEntity> vAgentEntity = this.lister(AgentEntity.class);
		List<AgentValue> vListAgentsValues = new ArrayList<AgentValue>();
		for (AgentEntity vAgentEntite : vAgentEntity) {
			vListAgentsValues.add(AgentPersistanceUtilities.toValue(vAgentEntite));
		}
		return vListAgentsValues;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String creer(AgentValue value) {
		AgentEntity result = (AgentEntity) this
				.creer(AgentPersistanceUtilities.toEntite(value));
		return result.getId().toString();
	}

	@Override
	public void supprimer(Long pId) {
		this.supprimerEntite(AgentEntity.class, pId);
	}

	@Override
	public String modifier(AgentValue value) {
		AgentEntity result = (AgentEntity) this
				.modifier(AgentPersistanceUtilities.toEntite(value));
		return result.getId().toString();
	}

}
