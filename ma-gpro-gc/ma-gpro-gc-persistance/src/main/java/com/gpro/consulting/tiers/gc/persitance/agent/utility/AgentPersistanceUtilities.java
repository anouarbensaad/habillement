package com.gpro.consulting.tiers.gc.persitance.agent.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;
import com.gpro.consulting.tiers.gc.persitance.agent.entite.AgentEntity;

/**
 * @author Ameni Berrich
 *
 */
public class AgentPersistanceUtilities {
	private static final Logger logger = LoggerFactory
			.getLogger(AgentPersistanceUtilities.class);

	// Agent ToEntite
	public static AgentEntity toEntite(AgentValue dto) {

		AgentEntity entity = new AgentEntity();

		entity.setId(dto.getId());
		entity.setNom(dto.getNom());
		entity.setObservations(dto.getObservations());

		return entity;
	}

	// Agent ToValue
	public static AgentValue toValue(AgentEntity entity) {

		AgentValue dto = new AgentValue();

		dto.setId(entity.getId());
		dto.setNom(entity.getNom());
		dto.setObservations(entity.getObservations());

		return dto;
	}

}
