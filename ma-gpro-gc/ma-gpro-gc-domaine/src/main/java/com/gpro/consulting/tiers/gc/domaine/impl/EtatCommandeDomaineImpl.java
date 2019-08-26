package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.IEtatCommandeDomaine;
import com.gpro.consulting.tiers.gc.persitance.IEtatCommandePersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class EtatCommandeDomaineImpl implements IEtatCommandeDomaine {

	@Autowired
	IEtatCommandePersistance etatCommandePersistance;
	
	@Override
	public String creerEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		return etatCommandePersistance.creerEtatCommande(pEtatCommandeValue);
	}

	@Override
	public void supprimerEtatCommande(Long pId) {
		etatCommandePersistance.supprimerEtatCommande(pId);
	}

	@Override
	public String modifierEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		return etatCommandePersistance
				.modifierEtatCommande(pEtatCommandeValue);
	}

	@Override
	public EtatCommandeValue rechercheEtatCommandeParId(Long pId) {
		return etatCommandePersistance
				.rechercheEtatCommandeParId(pId);
	}

	@Override
	public List<EtatCommandeValue> listeEtatCommande() {
		return etatCommandePersistance.listeEtatCommande();
	}
	
	
}
