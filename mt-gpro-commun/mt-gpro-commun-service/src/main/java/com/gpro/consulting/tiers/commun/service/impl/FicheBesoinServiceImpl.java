package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.domaine.IFicheBesoinDomaine;
import com.gpro.consulting.tiers.commun.service.IFicheBesoinService;

@Service
@Transactional
public class FicheBesoinServiceImpl implements IFicheBesoinService {

	@Autowired
	IFicheBesoinDomaine ficheBesoinDomaine;
	
	
	
	public IFicheBesoinDomaine getFicheBesoinDomaine() {
		return ficheBesoinDomaine;
	}



	public void setFicheBesoinDomaine(IFicheBesoinDomaine ficheBesoinDomaine) {
		this.ficheBesoinDomaine = ficheBesoinDomaine;
	}



	@Override
	public FicheBesoinValue rechercheFicheBesoinParId(Long pProduitId) {
		return this.ficheBesoinDomaine.rechercheFicheBesoinParId(pProduitId);
	}



	@Override
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue) {
		return this.ficheBesoinDomaine.creerOmodifierFicheBesoin(pFicheBesoinValue);
	}


    //Hajer AMRI  
	@Override
	public List<FicheBesoinValue> getAll() {
		// TODO Auto-generated method stub
		return ficheBesoinDomaine.getAll();
	}

	
}
