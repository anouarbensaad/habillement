package com.gpro.consulting.tiers.commun.persistance.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.persistance.ITypeArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
// TODO: Auto-generated Javadoc
/**
* The Class TypeArticleImpl.
* @author mohamed
*/
public class TypeArticlePersistanceImpl extends AbstractPersistance implements  ITypeArticlePersistance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * creer type article
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.creer(PersistanceUtilities.toEntity(pTypeArticleValue));
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult.getId().toString();
	}

	/* (non-Javadoc)
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleValueId) {
		this.supprimerEntite(TypeArticleEntity.class, pTypeArticleValueId);
	}

	/* (non-Javadoc)
	 * modifier type article
	 */
	
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.modifier(PersistanceUtilities.toEntity(pTypeArticleValue));
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult.getId().toString();
	}

	/* (non-Javadoc)
	 * recherche by id type article
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleValueId) {
		TypeArticleEntity typeArticleEntity= (TypeArticleEntity) this.rechercherParId(pTypeArticleValueId,TypeArticleEntity.class);
		TypeArticleValue typeArticleValueResult=PersistanceUtilities.toValue(typeArticleEntity);
		return typeArticleValueResult;
	}

	/* (non-Javadoc)
	 * liste type article 
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		java.util.List<TypeArticleEntity> typeArticleEntitys = this.lister(TypeArticleEntity.class);
		List <TypeArticleValue> ListTypeArticle= PersistanceUtilities.tolistTypeArticleValues(typeArticleEntitys);
		return ListTypeArticle;
	}

	/* (non-Javadoc)
	 * get entitymanager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
