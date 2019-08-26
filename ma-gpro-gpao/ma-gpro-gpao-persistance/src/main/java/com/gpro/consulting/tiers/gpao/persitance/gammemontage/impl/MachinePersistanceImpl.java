package com.gpro.consulting.tiers.gpao.persitance.gammemontage.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.MachineEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility.GammeMontagePersistanceUtilities;

/**
 * Implementation of {@link IMachinePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class MachinePersistanceImpl extends AbstractPersistance implements IMachinePersistance{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GammeMontagePersistanceUtilities gammeMontagePersistanceUtilities;
	
	@Override
	public String create(MachineValue request) {

		MachineEntity entity = (MachineEntity) this.creer(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(MachineEntity.class, id.longValue());
	}
	
	@Override
	public String update(MachineValue request) {
		
		MachineEntity entity = (MachineEntity) this.modifier(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public MachineValue getById(Long id) {
		
		MachineEntity entity = this.rechercherParId(id, MachineEntity.class);

	    return gammeMontagePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<MachineValue> getAll() {
		
		List<MachineEntity> listEntity = this.lister(MachineEntity.class);
		
		return gammeMontagePersistanceUtilities.listMachineToValue(listEntity);
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}