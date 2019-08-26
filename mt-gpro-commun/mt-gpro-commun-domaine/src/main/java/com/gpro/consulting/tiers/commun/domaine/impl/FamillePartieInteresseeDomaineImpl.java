package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleValue;
import com.gpro.consulting.tiers.commun.domaine.IFamillePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.IFamillePartieInteresseePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class FamillePartieInteresseeDomaineImpl implements
		IFamillePartieInteresseeDomaine {

	/** The famille partie interessee persistance. */
	@Autowired
	IFamillePartieInteresseePersistance famillePartieInteresseePersistance;

	

	/**
	 * Gets the famille partie interessee persistance.
	 *
	 * @return the famille partie interessee persistance
	 */
	public IFamillePartieInteresseePersistance getFamillePartieInteresseePersistance() {
		return famillePartieInteresseePersistance;
	}

	/**
	 * Sets the famille partie interessee persistance.
	 *
	 * @param famillePartieInteresseePersistance the new famille partie interessee persistance
	 */
	public void setFamillePartieInteresseePersistance(
			IFamillePartieInteresseePersistance famillePartieInteresseePersistance) {
		this.famillePartieInteresseePersistance = famillePartieInteresseePersistance;
	}

	/* (non-Javadoc)
	 * recherche par id famille pi
	 */
	@Override
	public FamilleValue rechercheFamillePartieInteresseeParId(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.rechercheFamillePartieInteresseeParId(pFamilleValue);

	}

	/* (non-Javadoc)
	 * liste famille
	 */
	@Override
	public List<FamilleValue> listeFamillePartieInteressee() {
		return famillePartieInteresseePersistance.listeFamillePartieInteressee();
	}

	/* (non-Javadoc)
	 * creer famille 
	 */
	@Override
	public String creerFamillePartieInteressee(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.creerFamillePartieInteressee(pFamilleValue);
	}

	/* (non-Javadoc)
	 * supprimer famille 
	 */
	@Override
	public void supprimerFamillePartieInteressee(Long pFamilleValue) {
        famillePartieInteresseePersistance.supprimerFamillePartieInteressee(pFamilleValue);		
	}

	/* (non-Javadoc)
	 * modifier famille 
	 */
	@Override
	public String modifierFamillePartieInteressee(FamilleValue pFamilleValue) {
		return famillePartieInteresseePersistance.modifierFamillePartieInteressee(pFamilleValue);
	}

	
}
