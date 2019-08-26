package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IStatutDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IStatutPersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class StatutDomaineImpl implements IStatutDomaine {

	@Autowired
	IStatutPersistance statutPersistance;

	@Override
	public String creerStatut(StatutOfValue pStatutOfValue) {
		return statutPersistance.creerStatut(pStatutOfValue);
	}

	@Override
	public void supprimerStatut(Long pId) {
		statutPersistance.supprimerStatut(pId);
	}

	@Override
	public String modifierStatut(StatutOfValue pStatutOfValue) {
		return statutPersistance.modifierStatut(pStatutOfValue);
	}

	@Override
	public StatutOfValue rechercheStatutParId(Long pId) {
		return statutPersistance.rechercheStatutParId(pId);
	}

	@Override
	public List<StatutOfValue> listeStatut() {
		return statutPersistance.listeStatut();
	}
	

}
