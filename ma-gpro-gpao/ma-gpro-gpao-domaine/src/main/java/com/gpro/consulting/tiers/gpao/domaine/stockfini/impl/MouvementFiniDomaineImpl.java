package com.gpro.consulting.tiers.gpao.domaine.stockfini.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.domaine.stockfini.IMouvementFiniDomaine;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.IDetailOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.IMouvementFiniPersistance;

/**
 * @author Samer Hassen
 *
 */
@Component
public class MouvementFiniDomaineImpl implements IMouvementFiniDomaine {

	@Autowired
	IMouvementFiniPersistance mouvementFiniPersistance;

	@Autowired
	IDetailOfPersistance detailOfPersistance;

	@Override
	public String createMouvementFini(MouvementFiniValue pMouvementFiniValue) {

		DetailOfValue detailOf = new DetailOfValue();
		detailOf = detailOfPersistance.rechercheDetailOfParId(pMouvementFiniValue.getDetailOfId());

		Long ancienQte = detailOf.getQteStock();

		if (pMouvementFiniValue.getType().equals("ENTREE")) {
			detailOf.setQteStock(ancienQte + pMouvementFiniValue.getQuantite());
		} else if (pMouvementFiniValue.getType().equals("SORTIE")) {
			detailOf.setQteStock(ancienQte - pMouvementFiniValue.getQuantite());
		}

		detailOfPersistance.modifierDetailOf(detailOf);

		return mouvementFiniPersistance.createMouvementFini(pMouvementFiniValue);
	}

	@Override
	public void deleteMouvementFini(Long id) {

		MouvementFiniValue pMouvementFiniValue = mouvementFiniPersistance.findMouvementFiniParId(id);

		DetailOfValue detailOf = new DetailOfValue();
		detailOf = detailOfPersistance.rechercheDetailOfParId(pMouvementFiniValue.getDetailOfId());

		Long ancienQte = detailOf.getQteStock();

		if (pMouvementFiniValue.getType().equals("ENTREE")) {
			detailOf.setQteStock(ancienQte - pMouvementFiniValue.getQuantite());
		} else if (pMouvementFiniValue.getType().equals("SORTIE")) {
			detailOf.setQteStock(ancienQte + pMouvementFiniValue.getQuantite());
		}

		detailOfPersistance.modifierDetailOf(detailOf);

		mouvementFiniPersistance.deleteMouvementFini(id);

	}

	@Override
	public MouvementFiniValue findMouvementFiniParId(Long pMouvementFiniId) {

		return mouvementFiniPersistance.findMouvementFiniParId(pMouvementFiniId);
	}

	@Override
	public ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
			RechercheMulticritereMouvementFiniValue pRechercheOrdreFaricationMulitCritere) {

		return mouvementFiniPersistance.rechercherMouvementFiniMultiCritere(pRechercheOrdreFaricationMulitCritere);
	}

}
