package com.gpro.consulting.tiers.gpao.service.stockfini.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;

import com.gpro.consulting.tiers.gpao.domaine.stockfini.IDetailOfDomaine;

import com.gpro.consulting.tiers.gpao.service.stockfini.IDetailOfService;


/**
 * @author $Samer
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DetailOfServiceImpl implements IDetailOfService{

	@Autowired
	IDetailOfDomaine detailOfDomaine;
	


	@Override
	public String modifierDetailOf(
			DetailOfValue pDetailOfValue) {
		return detailOfDomaine.modifierDetailOf(pDetailOfValue);
	}

	@Override
	public DetailOfValue rechercheDetailOfParId(
			Long pDetailOfId) {
		return detailOfDomaine.rechercheDetailOfParId(pDetailOfId);
	}
	


	@Override
	public ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
			RechercheMulticritereDetailOfValue pRechercheOrdreFaricationMulitCritere) {
		return detailOfDomaine.rechercherDetailOfMultiCritere(pRechercheOrdreFaricationMulitCritere);
	}
	



}
