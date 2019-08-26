package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleValue;
import com.gpro.consulting.tiers.commun.domaine.IFamillePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.service.IFamillePartieInteresseeService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class FamillePartieInteresseeServiceImpl implements IFamillePartieInteresseeService {
  @Autowired
  IFamillePartieInteresseeDomaine famillePartieInteresseeDomaine;



public IFamillePartieInteresseeDomaine getFamillePartieInteresseeDomaine() {
	return famillePartieInteresseeDomaine;
}

public void setFamillePartieInteresseeDomaine(IFamillePartieInteresseeDomaine famillePartieInteresseeDomaine) {
	this.famillePartieInteresseeDomaine = famillePartieInteresseeDomaine;
}

@Override
public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue) {
	return famillePartieInteresseeDomaine.rechercheFamillePartieInteresseeParId(pFamilleValue);
}

@Override
public List<FamilleValue> listeFamillePartieInteressee() {
	return famillePartieInteresseeDomaine.listeFamillePartieInteressee();
}

@Override
public String creerFamillePartieInteressee(FamilleValue pFamilleValue) {
	return famillePartieInteresseeDomaine.creerFamillePartieInteressee(pFamilleValue);
}

@Override
public void supprimerFamillePartieInteressee(Long pFamilleValue) {
    famillePartieInteresseeDomaine.supprimerFamillePartieInteressee(pFamilleValue);	
}

@Override
public String modifierFamillePartieInteressee(FamilleValue pFamilleValue) {
	 return famillePartieInteresseeDomaine.modifierFamillePartieInteressee(pFamilleValue);
}

 

}
