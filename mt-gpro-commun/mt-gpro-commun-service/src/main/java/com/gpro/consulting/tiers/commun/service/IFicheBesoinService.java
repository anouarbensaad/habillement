package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;

public interface IFicheBesoinService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheBesoinValue rechercheFicheBesoinParId(Long pProduitId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue);
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FicheBesoinValue> getAll();
	
}
