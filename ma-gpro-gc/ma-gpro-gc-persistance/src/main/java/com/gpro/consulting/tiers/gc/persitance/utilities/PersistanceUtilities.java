package com.gpro.consulting.tiers.gc.persitance.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.DocumentCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.DocumentCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ElementCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.persitance.entite.CommandeAchatEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.CommandeVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.DetailsProduitCommandeEntity;
import com.gpro.consulting.tiers.gc.persitance.entite.DocumentCommandeAchatEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.DocumentCommandeVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.ElementCommandeAchatEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.EtatCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.TypeCommandeEntite;

/**
 * PersistanceUtilities
 * @author $Ameni
 */

public class PersistanceUtilities {
	
	  
/* ******************************
 *        CommandeVente          *
 * ******************************/
	  
	  /*********************** CommandeVente ***********************
	   * @param pCommandeVenteEntite
	   * @return vCommandeVenteValue
	   */
	  public static CommandeVenteValue toValue( CommandeVenteEntite pCommandeVenteEntite) {
		
		CommandeVenteValue vCommandeVenteValue = new CommandeVenteValue();
		vCommandeVenteValue.setId(pCommandeVenteEntite.getId());
		vCommandeVenteValue.setSiteId(pCommandeVenteEntite.getSiteId());
		vCommandeVenteValue.setReference(pCommandeVenteEntite.getReference());
		vCommandeVenteValue.setSaison(pCommandeVenteEntite.getSaison());
		vCommandeVenteValue.setPrixTotal(pCommandeVenteEntite.getPrixTotal());
		vCommandeVenteValue.setDateIntroduction(pCommandeVenteEntite.getDateIntroduction());
		vCommandeVenteValue.setDateLivraison(pCommandeVenteEntite.getDateLivraison());
		vCommandeVenteValue.setObservations(pCommandeVenteEntite.getObservations());
		vCommandeVenteValue.setPartieIntersseId(pCommandeVenteEntite.getPartieIntersseId());
		vCommandeVenteValue.setTypeCommande_id(pCommandeVenteEntite.getTypeCommande_id());
		vCommandeVenteValue.setEtatCommande_id(pCommandeVenteEntite.getEtatCommande_id());
		vCommandeVenteValue.setQuantite(pCommandeVenteEntite.getQuantite());
		vCommandeVenteValue.setAgentId(pCommandeVenteEntite.getAgentId());
		vCommandeVenteValue.setOf_reference(pCommandeVenteEntite.getOf_reference());
		/*** Liste Document produit */
	    if (pCommandeVenteEntite.getDocumentCommandeVentes() != null) {
	      Set < DocumentCommandeVenteValue > vListeDocuments = new HashSet < DocumentCommandeVenteValue >();
	      for (DocumentCommandeVenteEntite vDocumentEntite : pCommandeVenteEntite.getDocumentCommandeVentes()) {
	    	  DocumentCommandeVenteValue vDodumentCommande = toValue(vDocumentEntite);
	    	  vListeDocuments.add(vDodumentCommande);
	      }
	      vCommandeVenteValue.setDocumentCommandeVentes(vListeDocuments);
	    }
	    /** Liste ProduitCommande */
	    if (pCommandeVenteEntite.getProduitCommandes() != null) {
	    	Set<ProduitCommandeValue> vListeProduits = new HashSet <ProduitCommandeValue>();
	    	for(ProduitCommandeEntite vProduitEntite : pCommandeVenteEntite.getProduitCommandes()){
	    		ProduitCommandeValue vProduitCommande = toValue(vProduitEntite);
	    		vListeProduits.add(vProduitCommande);
	    	}
	    	 vCommandeVenteValue.setProduitCommandes(vListeProduits);
	    }
	    return vCommandeVenteValue;
	  }
	
	/**************************  ProduitCommandeToValue ****************************
	 * @param vProduitCommandeEntite
	 * @return pProduitCommandeValue
	 */
	public static ProduitCommandeValue toValue(
			ProduitCommandeEntite vProduitCommandeEntite) {
		ProduitCommandeValue pProduitCommandeValue = new ProduitCommandeValue();
		pProduitCommandeValue.setId(vProduitCommandeEntite.getId());
		pProduitCommandeValue.setDateLivraison(vProduitCommandeEntite.getDateLivraison());
		pProduitCommandeValue.setDevise(vProduitCommandeEntite.getDevise());
		pProduitCommandeValue.setPrix(vProduitCommandeEntite.getPrix());
		if(vProduitCommandeEntite.getProduit() != null) {
			pProduitCommandeValue.setProduitId(vProduitCommandeEntite.getProduit().getId());
		}
		pProduitCommandeValue.setQuantite(vProduitCommandeEntite.getQuantite());
		
		if(vProduitCommandeEntite.getCommandeVente() != null){
			pProduitCommandeValue.setCommandeVenteId(vProduitCommandeEntite.getCommandeVente().getId());
			pProduitCommandeValue.setPartieIntersseId(vProduitCommandeEntite.getCommandeVente().getPartieIntersseId());
			pProduitCommandeValue.setRefCommandeVente(vProduitCommandeEntite.getCommandeVente().getReference());
		}

		if(vProduitCommandeEntite.getListDetailsProduitCommande() != null){
	    	
			List<DetailsProduitCommandeValue> listeDetailsProduitCommande = new ArrayList<DetailsProduitCommandeValue>();
     
			for (DetailsProduitCommandeEntity detailsProduitCommandeEntity : vProduitCommandeEntite.getListDetailsProduitCommande()) {
				DetailsProduitCommandeValue detailsProduitCommandeValue = toValue(detailsProduitCommandeEntity);
				listeDetailsProduitCommande.add(detailsProduitCommandeValue);
			}
     
			pProduitCommandeValue.setListDetailsProduitCommande(listeDetailsProduitCommande);
		}
					
		return pProduitCommandeValue;
	}

	private static DetailsProduitCommandeValue toValue(
			DetailsProduitCommandeEntity entity) {
		
		DetailsProduitCommandeValue dto = new DetailsProduitCommandeValue();
		
		dto.setId(entity.getId());
		dto.setCouleurId(entity.getCouleurId());
		
		dto.setTailleId(entity.getTailleId());
		dto.setQuantite(entity.getQuantite());
		
		if(entity.getProduitCommande() != null){
			dto.setProduitCommandeId(entity.getProduitCommande().getId());
		}
		
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setDateSuppression(entity.getDateSuppression());
		
		return dto;
	}

	/***************************  DocumentCommandeVenteToValue **************************
	 * 
	 * @param vDocumentCommandeVenteEntite
	 * @return pDocumentCommandeVenteValue
	 */
	private static DocumentCommandeVenteValue toValue(
			DocumentCommandeVenteEntite vDocumentCommandeVenteEntite) {
		DocumentCommandeVenteValue pDocumentCommandeVenteValue = new DocumentCommandeVenteValue();
		pDocumentCommandeVenteValue.setId(vDocumentCommandeVenteEntite.getId());
		pDocumentCommandeVenteValue.setPath(vDocumentCommandeVenteEntite.getPath());
		pDocumentCommandeVenteValue.setTypeDocumentId(vDocumentCommandeVenteEntite.getTypeDocumentId());
		pDocumentCommandeVenteValue.setUidDocument(vDocumentCommandeVenteEntite.getUidDocument());
		return pDocumentCommandeVenteValue;
	}
	
	 /************************** CommandeVenteToEntite ****************************  
	  * @param pCommandeVenteValue
	  * @param pListProduit
	  * @return vCommandeVenteEntite
	  */
	public static CommandeVenteEntite toEntite(CommandeVenteValue pCommandeVenteValue, Set <ProduitEntity> pListProduit) {
		CommandeVenteEntite vCommandeVenteEntite = new CommandeVenteEntite();
		if(pCommandeVenteValue.getId() !=null){
			vCommandeVenteEntite.setId(pCommandeVenteValue.getId());
		}
		  vCommandeVenteEntite.setSiteId(pCommandeVenteValue.getSiteId());
		  vCommandeVenteEntite.setReference(pCommandeVenteValue.getReference());
		  vCommandeVenteEntite.setSaison(pCommandeVenteValue.getSaison());
		  vCommandeVenteEntite.setPrixTotal(pCommandeVenteValue.getPrixTotal());
		  vCommandeVenteEntite.setDateIntroduction(pCommandeVenteValue.getDateIntroduction());
		  vCommandeVenteEntite.setDateLivraison(pCommandeVenteValue.getDateLivraison());
		  vCommandeVenteEntite.setObservations(pCommandeVenteValue.getObservations());
		  vCommandeVenteEntite.setPartieIntersseId(pCommandeVenteValue.getPartieIntersseId());
		  vCommandeVenteEntite.setTypeCommande_id(pCommandeVenteValue.getTypeCommande_id());
		  vCommandeVenteEntite.setEtatCommande_id(pCommandeVenteValue.getEtatCommande_id());
		  vCommandeVenteEntite.setQuantite(pCommandeVenteValue.getQuantite());
		  //System.out.println("#####  pCommandeVenteValue.getOf_reference():   "+pCommandeVenteValue.getOf_reference());
		  vCommandeVenteEntite.setOf_reference(pCommandeVenteValue.getReference());
		  /** Liste DocumentCommendeVente */
		  if (pCommandeVenteValue.getDocumentCommandeVentes() != null) {
			  Set < DocumentCommandeVenteEntite> vListeDocuments = new HashSet < DocumentCommandeVenteEntite >();
			  for (DocumentCommandeVenteValue vDocumentValue : pCommandeVenteValue.getDocumentCommandeVentes()) {
				  DocumentCommandeVenteEntite vDocumentCommande = toEntite(vDocumentValue);
				  vDocumentCommande.setCommandeVente(vCommandeVenteEntite);
			        vListeDocuments.add(vDocumentCommande);
			      }
			  vCommandeVenteEntite.setDocumentCommandeVentes(vListeDocuments);
		    }
		  /** Liste ProduitCommande */
		  if (pCommandeVenteValue.getProduitCommandes()!= null) {
			  Set < ProduitCommandeEntite> vListeProduits = new HashSet < ProduitCommandeEntite >();
			  for (ProduitCommandeValue vProduitCommandeValue : pCommandeVenteValue.getProduitCommandes()) {
				  ProduitEntity vProduitEntite = rechercherProduitFromList(pListProduit, vProduitCommandeValue.getProduitId());
				  ProduitCommandeEntite vProduitCommande = toEntite(vProduitCommandeValue, vProduitEntite );
				  vProduitCommande.setCommandeVente(vCommandeVenteEntite);
				  vListeProduits.add(vProduitCommande);
			      }
			  vCommandeVenteEntite.setProduitCommandes(vListeProduits);
		    }
		  
		return vCommandeVenteEntite;
	}
	

	public static CommandeVenteEntite toEntite(CommandeVenteValue dto) {
		
		CommandeVenteEntite entity = new CommandeVenteEntite();

		entity.setId(dto.getId());
		entity.setSiteId(dto.getSiteId());
		entity.setReference(dto.getReference());
		entity.setSaison(dto.getSaison());
		entity.setPrixTotal(dto.getPrixTotal());
		entity.setDateIntroduction(dto.getDateIntroduction());
		entity.setDateLivraison(dto.getDateLivraison());
		entity.setObservations(dto.getObservations());
		entity.setPartieIntersseId(dto.getPartieIntersseId());
		entity.setTypeCommande_id(dto.getTypeCommande_id());
		entity.setEtatCommande_id(dto.getEtatCommande_id());
		entity.setQuantite(dto.getQuantite());
		entity.setAgentId(dto.getAgentId());
		entity.setOf_reference(dto.getOf_reference());
		if (dto.getDocumentCommandeVentes() != null) {
			Set < DocumentCommandeVenteEntite> list = new HashSet < DocumentCommandeVenteEntite >();
			for (DocumentCommandeVenteValue documentCommandeVenteValue : dto.getDocumentCommandeVentes()) {
				DocumentCommandeVenteEntite documentCommandeVenteEntity = toEntite(documentCommandeVenteValue);
				documentCommandeVenteEntity.setCommandeVente(entity);
				list.add(documentCommandeVenteEntity);
			}
			entity.setDocumentCommandeVentes(list);
		}

		if (dto.getProduitCommandes()!= null) {
			Set < ProduitCommandeEntite> list = new HashSet < ProduitCommandeEntite >();
			for (ProduitCommandeValue produitCommandeValue : dto.getProduitCommandes()) {
				ProduitCommandeEntite produitCommandeEntity = toEntite(produitCommandeValue);
				produitCommandeEntity.setCommandeVente(entity);
				list.add(produitCommandeEntity);
			}
			entity.setProduitCommandes(list);
		}

		return entity;
	}

	
	/** ProduitCommandeEntite toEntite */
	private static ProduitCommandeEntite toEntite(ProduitCommandeValue dto) {
		
		ProduitCommandeEntite entity = new ProduitCommandeEntite();
		
		entity.setId(dto.getId());
		entity.setDateLivraison(dto.getDateLivraison());
		entity.setDevise(dto.getDevise());
		entity.setPrix(dto.getPrix());
		
		//System.out.println("-----dto.getQuantite()----"+entity.getQuantite());
		entity.setQuantite(dto.getQuantite());
		//System.out.println("-----entity.getQuantite()----"+dto.getQuantite());

		if(dto.getCommandeVenteId() != null){
			CommandeVenteEntite commandeVenteEntite = new CommandeVenteEntite();
			commandeVenteEntite.setId(dto.getCommandeVenteId());
			entity.setCommandeVente(commandeVenteEntite);
		}
		
		if(dto.getProduitId() != null){
			ProduitEntity produitEntity = new ProduitEntity();
			produitEntity.setId(dto.getProduitId());
			entity.setProduit(produitEntity);
		}
		
	    if(dto.getListDetailsProduitCommande() != null){
	    	
		     Set <DetailsProduitCommandeEntity> list = new HashSet<DetailsProduitCommandeEntity>();
		     
		     for (DetailsProduitCommandeValue detailsProduitCommandeValue : dto.getListDetailsProduitCommande()) {
		    	 DetailsProduitCommandeEntity detailsProduitCommandeEntity = toEntity(detailsProduitCommandeValue);
		    	 detailsProduitCommandeEntity.setProduitCommande(entity);
		    	 list.add(detailsProduitCommandeEntity);
		    }
		     
		     entity.setListDetailsProduitCommande(list);
		}
		
		return entity;
	}

	/******************  rechercherProduitFromList ***************************
	 * 
	 * @param pListProduit
	 * @param produitId
	 * @return vProduitEntite
	 */
	private static ProduitEntity rechercherProduitFromList(
			Set<ProduitEntity> pListProduit, Long produitId) {
		ProduitEntity vEntite = new ProduitEntity();
		    for (ProduitEntity entiteProduit : pListProduit) {
		      if (entiteProduit.getId().equals(produitId))
		        vEntite = entiteProduit;
		      break;
		    }

		    return vEntite;
	}

	/******************  ProduitCommandeToEntite ***************************
	 * @param pProduitCommandeValue
	 * @param pProduitEntite
	 * @return vProduitCommandeEntite
	 */
	private static ProduitCommandeEntite toEntite(ProduitCommandeValue pProduitCommandeValue, ProduitEntity pProduitEntite) {
		ProduitCommandeEntite vProduitCommandeEntite = new ProduitCommandeEntite();
		if (pProduitCommandeValue.getId() != null) {
		      vProduitCommandeEntite.setId(pProduitCommandeValue.getId());
		    }
		vProduitCommandeEntite.setDateLivraison(pProduitCommandeValue.getDateLivraison());
		vProduitCommandeEntite.setDevise(pProduitCommandeValue.getDevise());
		vProduitCommandeEntite.setPrix(pProduitCommandeValue.getPrix());
		vProduitCommandeEntite.setQuantite(pProduitCommandeValue.getQuantite());
		vProduitCommandeEntite.setProduit(pProduitEntite);
		
		if(pProduitCommandeValue.getCommandeVenteId() != null){
			CommandeVenteEntite commandeVente = new CommandeVenteEntite();
			commandeVente.setId(pProduitCommandeValue.getCommandeVenteId());
			vProduitCommandeEntite.setCommandeVente(commandeVente);
		}
		
	    if(pProduitCommandeValue.getListDetailsProduitCommande() != null){
	    	
		     Set <DetailsProduitCommandeEntity> listDetailsProduitCommande = new HashSet<DetailsProduitCommandeEntity>();
		     
		     for (DetailsProduitCommandeValue detailsProduitCommandeValue : pProduitCommandeValue.getListDetailsProduitCommande()) {
		    	 DetailsProduitCommandeEntity detailsProduitCommandeEntity = toEntity(detailsProduitCommandeValue);
		    	 detailsProduitCommandeEntity.setProduitCommande(vProduitCommandeEntite);
		    	 listDetailsProduitCommande.add(detailsProduitCommandeEntity);
		    }
		     
		     vProduitCommandeEntite.setListDetailsProduitCommande(listDetailsProduitCommande);
		}
		
		return vProduitCommandeEntite;
	}

	private static DetailsProduitCommandeEntity toEntity(DetailsProduitCommandeValue dto) {
		
		DetailsProduitCommandeEntity entity = new DetailsProduitCommandeEntity();
		
		entity.setId(dto.getId());
		entity.setCouleurId(dto.getCouleurId());
		entity.setQuantite(dto.getQuantite());
		entity.setTailleId(dto.getTailleId());
		
		if(dto.getProduitCommandeId() != null){
			ProduitCommandeEntite produitCommandeEntite = new ProduitCommandeEntite();
			produitCommandeEntite.setId(dto.getProduitCommandeId());
			entity.setProduitCommande(produitCommandeEntite);
		}
		
		return entity;
	}

	/******************  DocumentCommandeVenteToEntite ***************************
	 * @param pDocumentCommandeValue
	 * @return vDocumentEntite
	 */
	private static DocumentCommandeVenteEntite toEntite(DocumentCommandeVenteValue dto) {
		
		DocumentCommandeVenteEntite entity = new DocumentCommandeVenteEntite();
		
		entity.setId(dto.getId());
		entity.setPath(dto.getPath());
		entity.setTypeDocumentId(dto.getTypeDocumentId());
		entity.setUidDocument(dto.getUidDocument());
		
		return entity;
	}


	 /************************* EtatCommandToEntite *************************
	  * @param pEtatCommandValue
	  * @return vEtatCommandEntite
	  */
	  public static  EtatCommandeEntite toEntite( EtatCommandeValue  pEtatCommandValue) {
	     EtatCommandeEntite vEtatCommandEntite = new  EtatCommandeEntite();
	    if ( pEtatCommandValue.getId() != null) {
	    	vEtatCommandEntite.setId( pEtatCommandValue.getId());
	    }
	    vEtatCommandEntite.setDesignation( pEtatCommandValue.getDesignation());
	    return vEtatCommandEntite;
	  }

	 /************************* EtatCommandeToValue  ************************
	  * @param pEtatCommandEntite
	  * @return vEtatCommandValue
	  */
	  public static EtatCommandeValue toValue(EtatCommandeEntite pEtatCommandEntite) {
		    EtatCommandeValue vEtatCommandValue = new EtatCommandeValue();
		    vEtatCommandValue.setId(pEtatCommandEntite.getId());
		    vEtatCommandValue.setDesignation(pEtatCommandEntite.getDesignation());
		    return vEtatCommandValue;
		  }
	
	   /************************ TypeCommandeToEntite **********************
	    * @param pTypeCommandeValue
	    * @return vTypeCommandeEntite
	    */
		  public static  TypeCommandeEntite toEntite( TypeCommandeValue  pTypeCommandeValue) {
		     TypeCommandeEntite vTypeCommandeEntite = new  TypeCommandeEntite();
		    if ( pTypeCommandeValue.getId() != null) {
		    	vTypeCommandeEntite.setId( pTypeCommandeValue.getId());
		    }
		    vTypeCommandeEntite.setDesignation( pTypeCommandeValue.getDesignation());
		    return vTypeCommandeEntite;
		  }

		 /*********************** TypeCommandeeToValue **********************
		  * @param pTypeCommandeEntite
		  * @return vTypeCommandeValue
		  */
		  public static TypeCommandeValue toValue(TypeCommandeEntite pTypeCommandeEntite) {
			    TypeCommandeValue vTypeCommandeValue = new TypeCommandeValue();
			    vTypeCommandeValue.setId(pTypeCommandeEntite.getId());
			    vTypeCommandeValue.setDesignation(pTypeCommandeEntite.getDesignation());
			    return vTypeCommandeValue;
			  }
		
		  
/* ******************************
 * 			CommandeAchat     	*
 * ******************************/
		  /*********************** CommandeAchatToValue ***********************
		   * @param pCommandeAchatEntite
		   * @return vCommandeAchatValue
		   */
		  public static CommandeAchatValue toValue( CommandeAchatEntite pCommandeAchatEntite) {
			
			CommandeAchatValue vCommandeAchatValue = new CommandeAchatValue();
			vCommandeAchatValue.setId(pCommandeAchatEntite.getId());
			vCommandeAchatValue.setCoutTotal(pCommandeAchatEntite.getCoutTotal());
			vCommandeAchatValue.setDateCommande(pCommandeAchatEntite.getDateCommande());
			vCommandeAchatValue.setDateLivraisonPrevue(pCommandeAchatEntite.getDateLivraisonPrevue());
			vCommandeAchatValue.setEtat(pCommandeAchatEntite.getEtat());
			vCommandeAchatValue.setPartieIntersseId(pCommandeAchatEntite.getPartieIntersseId());
			vCommandeAchatValue.setReference(pCommandeAchatEntite.getReference());
			vCommandeAchatValue.setSiteId(pCommandeAchatEntite.getSiteId());
			vCommandeAchatValue.setObservations(pCommandeAchatEntite.getObservations());
			
			/*** Liste Document */
		    if (pCommandeAchatEntite.getDocumentCommandeAchat() != null) {
		      Set < DocumentCommandeAchatValue > vListeDocuments = new HashSet < DocumentCommandeAchatValue >();
		      for (DocumentCommandeAchatEntite vDocumentEntite : pCommandeAchatEntite.getDocumentCommandeAchat()) {
		    	  DocumentCommandeAchatValue vDodumentCommande = toValue(vDocumentEntite);
		    	  vListeDocuments.add(vDodumentCommande);
		      }
		      vCommandeAchatValue.setDocumentCommandeAchat(vListeDocuments);
		    }
		    /** Liste ElementCommande */
		    if (pCommandeAchatEntite.getElementCommandes() != null) {
		    	Set<ElementCommandeAchatValue> vListeArticles = new HashSet <ElementCommandeAchatValue>();
		    	for(ElementCommandeAchatEntite vArticleEntite : pCommandeAchatEntite.getElementCommandes()){
		    		ElementCommandeAchatValue vArticleCommande = toValue(vArticleEntite);
		    		vListeArticles.add(vArticleCommande);
		    	}
		    	 vCommandeAchatValue.setElementCommandes(vListeArticles);
		    }
		    return vCommandeAchatValue;
		  }
		  
		  
		/******************  DocumentCommandeAchatToValue ****************************
		 * @param vDocumentEntite
		 * @return pDocumentCommandeAchatValue
		 */
		private static DocumentCommandeAchatValue toValue(
					DocumentCommandeAchatEntite vDocumentEntite) {
			DocumentCommandeAchatValue pDocumentCommandeAchatValue = new DocumentCommandeAchatValue();
			pDocumentCommandeAchatValue.setId(vDocumentEntite.getId());
			pDocumentCommandeAchatValue.setPath(vDocumentEntite.getPath());
			pDocumentCommandeAchatValue.setTypeDocumentId(vDocumentEntite.getTypeDocumentId());
			pDocumentCommandeAchatValue.setUidDocument(vDocumentEntite.getUidDocument());
			return pDocumentCommandeAchatValue;
			}
		
		/******************  ElementCommandeToValue ****************************
		 * @param vElementCommandeAchatEntite
		 * @return pElementCommandeAchatValue
		 */
		private static ElementCommandeAchatValue toValue(
					ElementCommandeAchatEntite vElementCommandeAchatEntite) {
			ElementCommandeAchatValue pElementCommandeAchatValue = new ElementCommandeAchatValue();
			pElementCommandeAchatValue.setId(vElementCommandeAchatEntite.getId());
			pElementCommandeAchatValue.setLivre(vElementCommandeAchatEntite.isLivre());
			pElementCommandeAchatValue.setPrixTotal(vElementCommandeAchatEntite.getPrixTotal());
			pElementCommandeAchatValue.setPrixUnitiaire(vElementCommandeAchatEntite.getPrixUnitiaire());
			pElementCommandeAchatValue.setArticleId(vElementCommandeAchatEntite.getArticleId());
			/** article. */
			/*if(vElementCommandeAchatEntite.getArticle() != null) {
				pElementCommandeAchatValue.setArticleId(vElementCommandeAchatEntite.getArticle().getId());
			}*/
			pElementCommandeAchatValue.setQuantite(vElementCommandeAchatEntite.getQuantite());
			return pElementCommandeAchatValue;
			}
		
		/******************** CommandeAchatToEntite **************
		 * @param pCommandeAchatValue
		 * @param pListArticle
		 * @return vCommandeAchatEntite
		 */ 
		public static CommandeAchatEntite toEntite(CommandeAchatValue pCommandeAchatValue){ //, Set <ArticleEntite> pListArticle) {
			CommandeAchatEntite vCommandeAchatEntite = new CommandeAchatEntite();
			if(pCommandeAchatValue.getId() !=null){
				vCommandeAchatEntite.setId(pCommandeAchatValue.getId());
			}
			vCommandeAchatEntite.setId(pCommandeAchatValue.getId());
			vCommandeAchatEntite.setCoutTotal(pCommandeAchatValue.getCoutTotal());
			vCommandeAchatEntite.setDateCommande(pCommandeAchatValue.getDateCommande());
			vCommandeAchatEntite.setDateLivraisonPrevue(pCommandeAchatValue.getDateLivraisonPrevue());
			vCommandeAchatEntite.setEtat(pCommandeAchatValue.getEtat());
			vCommandeAchatEntite.setPartieIntersseId(pCommandeAchatValue.getPartieIntersseId());
			vCommandeAchatEntite.setReference(pCommandeAchatValue.getReference());
			vCommandeAchatEntite.setSiteId(pCommandeAchatValue.getSiteId());
			vCommandeAchatEntite.setObservations(pCommandeAchatValue.getObservations());
			
			  /** Liste DocumentCommendeAchat */
			  if (pCommandeAchatValue.getDocumentCommandeAchat() != null) {
				  Set < DocumentCommandeAchatEntite> vListeDocuments = new HashSet < DocumentCommandeAchatEntite >();
				  for (DocumentCommandeAchatValue vDocumentValue : pCommandeAchatValue.getDocumentCommandeAchat()) {
					  DocumentCommandeAchatEntite vDocumentCommande = toEntite(vDocumentValue);
					  vDocumentCommande.setCommandeAchat(vCommandeAchatEntite);
				        vListeDocuments.add(vDocumentCommande);
				      }
				  vCommandeAchatEntite.setDocumentCommandeAchat(vListeDocuments);
			    }
			  /** Liste ElementCommande */
			  if (pCommandeAchatValue.getElementCommandes() != null) {
				  Set < ElementCommandeAchatEntite> vListeArticles = new HashSet < ElementCommandeAchatEntite >();
				  for (ElementCommandeAchatValue vElementCommandeValue : pCommandeAchatValue.getElementCommandes()) {
					 // ArticleEntite vArticleEntite = rechercherArticleFromList(pListArticle, vElementCommandeValue.getArticleId());
					  ElementCommandeAchatEntite vElementCommande = toEntite(vElementCommandeValue); //(vElementCommandeValue, vArticleEntite );
					  vElementCommande.setCommandeAchat(vCommandeAchatEntite);
					  vListeArticles.add(vElementCommande);
				      }
				  vCommandeAchatEntite.setElementCommandes(vListeArticles);
			    }
			  
			return vCommandeAchatEntite;
		}
	
		/******************  DocumentCommandeAchatToEntite ****************************
		 * @param pDocumentCommandeValue
		 * @return vDocumentEntite
		 */
		private static DocumentCommandeAchatEntite toEntite(
					DocumentCommandeAchatValue pDocumentCommandeValue) {
			DocumentCommandeAchatEntite vDocumentEntite = new DocumentCommandeAchatEntite();
			if (pDocumentCommandeValue.getId() != null) {
			      vDocumentEntite.setId(pDocumentCommandeValue.getId());
			    }
			vDocumentEntite.setPath(pDocumentCommandeValue.getPath());
			vDocumentEntite.setTypeDocumentId(pDocumentCommandeValue.getTypeDocumentId());
			vDocumentEntite.setUidDocument(pDocumentCommandeValue.getUidDocument());
			return vDocumentEntite;
		}

		/******************  ElementCommandeAchatToEntite ***************************
		 * @param pElementCommandeValue
		 * @param pArticleEntite
		 * @return vElementCommandeAchatEntite
		 */
		private static ElementCommandeAchatEntite toEntite(ElementCommandeAchatValue pElementCommandeValue){
					/*ElementCommandeAchatValue pElementCommandeValue, ArticleEntite pArticleEntite) {*/
			ElementCommandeAchatEntite vElementCommandeAchatEntite = new ElementCommandeAchatEntite();
			if (pElementCommandeValue.getId() != null) {
				vElementCommandeAchatEntite.setId(pElementCommandeValue.getId());
			    }
			vElementCommandeAchatEntite.setPrixUnitiaire(pElementCommandeValue.getPrixUnitiaire());
			vElementCommandeAchatEntite.setPrixTotal(pElementCommandeValue.getPrixTotal());
			vElementCommandeAchatEntite.setLivre(pElementCommandeValue.isLivre());
			vElementCommandeAchatEntite.setQuantite(pElementCommandeValue.getQuantite());
			vElementCommandeAchatEntite.setArticleId(pElementCommandeValue.getArticleId());
			return vElementCommandeAchatEntite;
			}

		
		public static List<ProduitCommandeValue> toValue(
				List<ProduitCommandeEntite> listEntity) {
			
			List<ProduitCommandeValue> list = new ArrayList<ProduitCommandeValue>();
			
			for(ProduitCommandeEntite entity : listEntity){
				
				list.add(toValue(entity));
			}
			
			return list;
		}

}
