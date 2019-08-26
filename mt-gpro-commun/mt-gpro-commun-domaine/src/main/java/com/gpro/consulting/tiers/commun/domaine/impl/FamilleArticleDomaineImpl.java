package com.gpro.consulting.tiers.commun.domaine.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IFamilleArticlePersistance;
// TODO: Auto-generated Javadoc
/**
 * The Class FamilleArticleDomaineImpl.
 * @author mohamed
 */
public class FamilleArticleDomaineImpl  implements  IFamilleArticleDomaine  {
	
	@Autowired
	IFamilleArticlePersistance familleArticlePersistance ;
			/**ajouter  famille article**/
	@Override
	public String creerFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		return familleArticlePersistance.creerFamilleArticle(pFamilleArticleValue);
	}

	/**supprimer   famille article**/

	@Override
	public void supprimerFamilleArticle(Long pFamilleArticleId) {
		familleArticlePersistance.supprimerFamilleArticle(pFamilleArticleId);
	}

	/**modifier  famille article**/
	@Override
	public String modifierFamilleArticle(FamilleArticleValue pFamilleArticleValue) {
		return familleArticlePersistance.modifierFamilleArticle(pFamilleArticleValue);
		
	}

	/**recherche by id  famille article**/
	@Override
	public FamilleArticleValue rechercheFamilleArticleById(Long pFamilleArticleId) {
	      return familleArticlePersistance.rechercheFamilleArticleById(pFamilleArticleId);
	}

	/**list  famille article**/
	@Override
	public List<FamilleArticleValue> listeFamilleArticle() {
		return familleArticlePersistance.listeFamilleArticle();
	}

	/**get famille article persistance*/
	public IFamilleArticlePersistance getFamilleArticlePersistance() {
		return familleArticlePersistance;
	}
     /**set famille article persistance */
	public void setFamilleArticlePersistance(
			IFamilleArticlePersistance familleArticlePersistance) {
		this.familleArticlePersistance = familleArticlePersistance;
	}

	
}
