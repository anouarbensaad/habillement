package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IFicheBesoinDomaine;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;

public class FicheBesoinDomaineImpl implements IFicheBesoinDomaine {

	@Autowired
	IFicheBesoinPersistance ficheBesoinPersistance;

	@Autowired
	IProduitPersistance produitPersistance;

	public IFicheBesoinPersistance getFicheBesoinPersistance() {
		return ficheBesoinPersistance;
	}

	public void setFicheBesoinPersistance(IFicheBesoinPersistance ficheBesoinPersistance) {
		this.ficheBesoinPersistance = ficheBesoinPersistance;
	}

	public IProduitPersistance getProduitPersistance() {
		return produitPersistance;
	}

	public void setProduitPersistance(IProduitPersistance produitPersistance) {
		this.produitPersistance = produitPersistance;
	}

	@Override
	public FicheBesoinValue rechercheFicheBesoinParId(Long pProduitId) {

		//System.out.println("-----------recherche fiche besoin domaine ------------");

		FicheBesoinValue vFicheBesoinValue = ficheBesoinPersistance.rechercheFicheBesoinParId(pProduitId);

		if (vFicheBesoinValue.getProduitValue() != null)
			return vFicheBesoinValue;

		ProduitValue vProduitValue = produitPersistance.rechercheProduitById(pProduitId);
		vFicheBesoinValue.setProduitValue(vProduitValue);

		return vFicheBesoinValue;
	}

	@Override
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue) {
		String idFicheBesoin = ficheBesoinPersistance.creerOmodifierFicheBesoin(pFicheBesoinValue);
		return idFicheBesoin;
	}

	@Override
	public List<FicheBesoinValue> getAll() {
		return ficheBesoinPersistance.getAll();
	}

}
