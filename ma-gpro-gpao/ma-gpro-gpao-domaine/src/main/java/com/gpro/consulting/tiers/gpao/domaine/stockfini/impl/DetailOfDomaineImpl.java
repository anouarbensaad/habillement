package com.gpro.consulting.tiers.gpao.domaine.stockfini.impl;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.domaine.stockfini.IDetailOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.IDetailOfPersistance;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DetailOfDomaineImpl implements IDetailOfDomaine{
	
	@Autowired
	IDetailOfPersistance detailOfPersistance;
	
	@Autowired
	ITaillePersistance taillePersistance;
	
	@Autowired
	ICouleurPersistance couleurPersistance;
	
	@Autowired
	IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	IProduitPersistance produitPersistance;

	@Override
	public String modifierDetailOf(DetailOfValue pDetailOfValue) {
		
		return detailOfPersistance.modifierDetailOf(pDetailOfValue);
	}

	@Override
	public DetailOfValue rechercheDetailOfParId(Long pDetailOfId) {
		
		return detailOfPersistance.rechercheDetailOfParId(pDetailOfId);
	}

	@Override
	public ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
			RechercheMulticritereDetailOfValue pRechercheOrdreFaricationMulitCritere) {

		ResultatMulticritereDetailOfValue  resultat = detailOfPersistance.rechercherDetailOfMultiCritere(pRechercheOrdreFaricationMulitCritere);
	
		for (DetailOfValue detOf : resultat.getDetailOfValues())
		{
			CouleurValue couleur = couleurPersistance.getById(detOf.getCouleurId());
			
			TailleValue taille = taillePersistance.getById(detOf.getTailleId());
			
			OrdreFabricationValue of = ordreFabricationPersistance.rechercheOrdreFabricationParId(detOf.getOrdre());
			
			ProduitValue produit = produitPersistance.rechercheProduitById(of.getProduitId());
			
			detOf.setCouleurDesignation(couleur.getDesignation());
			detOf.setTailleDesignation(taille.getDesignation());
			
			
			detOf.setNumOF(of.getNumero());
			detOf.setDesignationOF(produit.getDesignation());
			detOf.setReferenceProduit(produit.getReference());
			
		}
	
		return resultat;
	
	}

}
