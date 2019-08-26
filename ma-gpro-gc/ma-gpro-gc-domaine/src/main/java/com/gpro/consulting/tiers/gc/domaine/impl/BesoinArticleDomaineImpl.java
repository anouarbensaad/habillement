package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.IFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.ITypeArticlePersistance;
import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;
import com.gpro.consulting.tiers.gc.domaine.IBesoinArticleDomaine;
import com.gpro.consulting.tiers.gc.persitance.IProduitCommandePersistance;

/**
 * implementation of {@link IBesoinArticleDomaine}
 * 
 * @author Wahid Gazzah
 * @since 18/04/2016
 *
 */

@Component
public class BesoinArticleDomaineImpl implements IBesoinArticleDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(BesoinArticleDomaineImpl.class);
	
	private static final Double ZEROD = 0D;
	private static final Long ZEROL = 0L;
	private static final int FIRST_INDEX = 0;
	
	private static final String SEPARATOR_COULEUR = " , ";
	private static final String SEPARATOR_TAILLE = " - ";
	
	@Autowired
	private IProduitCommandePersistance produitCommandePersistance;
	
	@Autowired
	private IFicheBesoinPersistance ficheBesoinPersistance;
	
	@Autowired
	private ICouleurPersistance couleurPersistance;
	
	@Autowired
	private ITaillePersistance taillePersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IArticlePersistance articlePersistance;
	
	@Autowired
	private ISousFamilleArticlePersistance sousFamilleArticlePersistance;
	
	@Autowired
	private IFamilleArticlePersistance familleArticlePersistance;
	
	@Autowired
	ITypeArticlePersistance typeArticlePersistance ;

	@Override
	public List<BesoinArticleValue> getAllBesoinArticles() {
		
		List<BesoinArticleValue> listBesoinArticles = new ArrayList<BesoinArticleValue>();
		
		// Map< articleId , articleDesignation >
		List<ArticleCacheValue> listArticle = articlePersistance.listeArticleCache();
		Map<Long, String> mapArticleIdDesignation = new HashMap<Long, String>();
		for(ArticleCacheValue articleCache : listArticle){
			mapArticleIdDesignation.put(articleCache.getId(), articleCache.getDesignation());
		}
		
		List<FicheBesoinValue> listFicheBesoin= ficheBesoinPersistance.getAll();
		List<ProduitCommandeValue> listProduitCommande = produitCommandePersistance.getAll();
		
		// Map<tailleId, couleurId> , List<ElementBesoinValue>
		Map<Object, List<ElementBesoinValue>> mapListElementBesoinByTailleCouleurProduit = new HashMap<Object, List<ElementBesoinValue>>();
		
		// Map<tailleId, couleurId> , List<DetailsProduitCommandeValue>
		Map<Object, List<DetailsProduitCommandeValue>> mapListDetailsProduitCommandeByTailleCouleurProduit = new HashMap<Object, List<DetailsProduitCommandeValue>>();
		
		for(FicheBesoinValue ficheBesoin : listFicheBesoin){
			
			for(ElementBesoinValue elementBesoin: ficheBesoin.getElementBesoinValue()){
				
				Long currentProduitId = (ficheBesoin.getProduitValue()!=null)?ficheBesoin.getProduitValue().getId():null;
				
				Map<Object, Object> key = new HashMap<Object, Object>();
				Map<Long, Long> subKey1 = new HashMap<Long, Long>();
				Map<Long, Long> subKey2 = new HashMap<Long, Long>();
				
				subKey1.put(elementBesoin.getEb_taille_id(), elementBesoin.getEb_couleur_id());
				subKey2.put(currentProduitId, elementBesoin.getArticle().getId());
				key.put(subKey1, subKey2);
				
				if (mapListElementBesoinByTailleCouleurProduit.get(key) == null) {
					mapListElementBesoinByTailleCouleurProduit.put(key, new ArrayList<ElementBesoinValue>());
				}
		    	
		    	mapListElementBesoinByTailleCouleurProduit.get(key).add(elementBesoin);
				
			}
		}
		
		for(ProduitCommandeValue produitCommande : listProduitCommande){
			
			for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
				
				Map<Object, Long> key = new HashMap<Object, Long>();
				Map<Long, Long> subKey = new HashMap<Long, Long>();
				
				subKey.put(detailsProduitCommande.getTailleId(), detailsProduitCommande.getCouleurId());
				key.put(subKey, produitCommande.getProduitId());
				
				detailsProduitCommande.setProduitCommandeId(produitCommande.getProduitId());
				
				if (mapListDetailsProduitCommandeByTailleCouleurProduit.get(key) == null) {
					mapListDetailsProduitCommandeByTailleCouleurProduit.put(key, new ArrayList<DetailsProduitCommandeValue>());
				}
				
				mapListDetailsProduitCommandeByTailleCouleurProduit.get(key).add(detailsProduitCommande);
			}
		}
		
		Iterator it = mapListDetailsProduitCommandeByTailleCouleurProduit.entrySet().iterator();
	    List <DetailsProduitCommandeValue> listDetailsProduitCommandeGrouppedByTailleCouleurProduit = new ArrayList<DetailsProduitCommandeValue>();
	    
	    while (it.hasNext()) {
	    	
	    	DetailsProduitCommandeValue element = new DetailsProduitCommandeValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<DetailsProduitCommandeValue>> pair = (Map.Entry<Object, List<DetailsProduitCommandeValue>>)it.next();
	    	
	    	Long quantiteTotal = ZEROL;
	    	
	    	for(DetailsProduitCommandeValue detailsProduitCommande: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal + ((detailsProduitCommande.getQuantite()!=null)?detailsProduitCommande.getQuantite():ZEROL);
	    		
	    	}

	    	element.setProduitCommandeId(pair.getValue().get(FIRST_INDEX).getProduitCommandeId());
	 
    		element.setTailleId(pair.getValue().get(FIRST_INDEX).getTailleId());
    		element.setCouleurId(pair.getValue().get(FIRST_INDEX).getCouleurId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listDetailsProduitCommandeGrouppedByTailleCouleurProduit.add(element);
	    	
	    }
	    
	    it.remove();
	    
	    it = mapListElementBesoinByTailleCouleurProduit.entrySet().iterator();
	    List <ElementBesoinValue> listElementBesoinGrouppedByTailleCouleurProduit = new ArrayList<ElementBesoinValue>();
	    
	    while (it.hasNext()) {
	    	
	    	ElementBesoinValue element = new ElementBesoinValue();
	    	
	    	Map.Entry <Object, List<ElementBesoinValue>> pair = (Map.Entry<Object, List<ElementBesoinValue>>)it.next();
	    	
	    	Double quantiteTotal = ZEROD;
	    	
	    	for(ElementBesoinValue besoinArticleValue: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal 
	    				+ besoinArticleValue.getQuantite()*calculQuantiteCMD(listDetailsProduitCommandeGrouppedByTailleCouleurProduit, besoinArticleValue);
	    		
	    	}
	    	
	    	Long tailleId = pair.getValue().get(FIRST_INDEX).getEb_taille_id();
	    	Long couleurId = pair.getValue().get(FIRST_INDEX).getEb_couleur_id();
	    	
    		element.setEb_taille_id(tailleId);
    		element.setEb_couleur_id(couleurId);
    		element.setArticle(pair.getValue().get(FIRST_INDEX).getArticle());
    		element.setFicheBesoinId(pair.getValue().get(FIRST_INDEX).getFicheBesoinId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listElementBesoinGrouppedByTailleCouleurProduit.add(element);
	    }
	    
	    it.remove();
	    
	    for(int i = 0; i < listElementBesoinGrouppedByTailleCouleurProduit.size(); i++){
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	Long produitId = null;
	    	
	    	Long ficheBesoinId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getFicheBesoinId();
	    	
	    	if(ficheBesoinId != null){
	    		
	    		FicheBesoinValue ficheBesoin = ficheBesoinPersistance.getById(ficheBesoinId);
	    		
	    		if(ficheBesoin != null){
	    			
	    			if(ficheBesoin.getProduitValue() != null){
	    				
	    				produitId = ficheBesoin.getProduitValue().getId();
	    			}
	    				
	    		}
	    	}
	    	
	    	Double quantiteBesoin = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getQuantite();
	       
	    	Long tailleId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_taille_id();
	    	Long couleurId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_couleur_id();
	    	Long articleId = (listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle()!=null)?
	    			listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle().getId() : null;
	    	
	    	element.setProduitId(produitId);
	    	element.setArticleId(articleId);
	    	element.setTailleId(tailleId);
	    	element.setCouleurId(couleurId);
	    	element.setBesoin((quantiteBesoin));
			
			if(mapArticleIdDesignation.containsKey(articleId)){
				element.setArticleDesignation(mapArticleIdDesignation.get(articleId));
			}
			
			Map<Object, Long> key = new HashMap<Object, Long>();
			Map<Long, Long> subKey = new HashMap<Long, Long>();
			
			subKey.put(tailleId, couleurId);
			key.put(subKey, produitId);
			
			if(mapListDetailsProduitCommandeByTailleCouleurProduit.containsKey(key)){
				
				List<DetailsProduitCommandeValue> listDetailsProduitCommande = mapListDetailsProduitCommandeByTailleCouleurProduit.get(key);
				
				Long quantiteCommande = ZEROL;
				for(DetailsProduitCommandeValue detail : listDetailsProduitCommande){
					
					quantiteCommande = quantiteCommande + ((detail.getQuantite()!=null)?detail.getQuantite():ZEROL);
				}
				
				element.setQuantiteCommande(quantiteCommande);
			}
			
	    	listBesoinArticles.add(element);
	    }
	    
	    // Final Groupement, by articleId
	    List<BesoinArticleValue> finalListBesoinArticles = new ArrayList<BesoinArticleValue>();
		Map<Long, List<BesoinArticleValue>> mapFinalListBesoinArticles = new HashMap<Long, List<BesoinArticleValue>>();
	    
	    for(BesoinArticleValue besoinArticle : listBesoinArticles){
			
			Long key = besoinArticle.getArticleId();
			
			if (mapFinalListBesoinArticles.get(key) == null) {
				mapFinalListBesoinArticles.put(key, new ArrayList<BesoinArticleValue>());
			}
			
			mapFinalListBesoinArticles.get(key).add(besoinArticle);
		}
	    
	    it = mapFinalListBesoinArticles.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<BesoinArticleValue>> pair = (Map.Entry<Object, List<BesoinArticleValue>>)it.next();
	    	
	    	Long quantiteCommandeTotal = ZEROL;
	    	Double besoinTotal = ZEROD;
	    	
	    	for(BesoinArticleValue detailsProduitCommande: pair.getValue()){
	    		quantiteCommandeTotal = quantiteCommandeTotal + ((detailsProduitCommande.getQuantiteCommande()!=null)?detailsProduitCommande.getQuantiteCommande():ZEROL);
	    		besoinTotal = besoinTotal +  ((detailsProduitCommande.getBesoin()!=null)?detailsProduitCommande.getBesoin():ZEROL);
	    	}
	    	
    		element.setArticleDesignation(pair.getValue().get(FIRST_INDEX).getArticleDesignation());
    		
    		element.setBesoin(besoinTotal);
    		element.setQuantiteCommande(quantiteCommandeTotal);
	    	
	    	finalListBesoinArticles.add(element);
	    	
	    }
	    
	    it.remove();
		
//		return listBesoinArticles;
		return finalListBesoinArticles;

	}
	
	@Override
	public List<BesoinArticleVue> getBesoinProduit(Long produitId) {
		
		List<BesoinArticleValue> listBesoinArticles = new ArrayList<BesoinArticleValue>();
		
		// Map< articleId , articleDesignation >
		List<ArticleCacheValue> listArticle = articlePersistance.listeArticleCache();
		Map<Long, String> mapArticleIdDesignation = new HashMap<Long, String>();
		for(ArticleCacheValue articleCache : listArticle){
			mapArticleIdDesignation.put(articleCache.getId(), articleCache.getDesignation());
		}
		
		FicheBesoinValue ficheBesoin = ficheBesoinPersistance.rechercheFicheBesoinParId(produitId);

		List<ProduitCommandeValue> listProduitCommande = new ArrayList<ProduitCommandeValue>();
		
		RechercheMulticritereProduitCommandeValue request = new RechercheMulticritereProduitCommandeValue();
		request.setProduitId(produitId);
		ResultatRechecheProduitCommandeValue result = produitCommandePersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
			for(ProduitCommandeValue produitCommande : result.getListProduitCommandeValue()){
				
				listProduitCommande.add(produitCommande);
			}
		}

		
		// Map<tailleId, couleurId> , List<ElementBesoinValue>
		Map<Object, List<ElementBesoinValue>> mapListElementBesoinByTailleCouleurProduit = new HashMap<Object, List<ElementBesoinValue>>();
		
		// Map<tailleId, couleurId> , List<DetailsProduitCommandeValue>
		Map<Object, List<DetailsProduitCommandeValue>> mapListDetailsProduitCommandeByTailleCouleurProduit = new HashMap<Object, List<DetailsProduitCommandeValue>>();
		
		for(ElementBesoinValue elementBesoin: ficheBesoin.getElementBesoinValue()){
			
			Map<Object, Object> key = new HashMap<Object, Object>();
			Map<Long, Long> subKey1 = new HashMap<Long, Long>();
			Map<Long, Long> subKey2 = new HashMap<Long, Long>();
			
			subKey1.put(elementBesoin.getEb_taille_id(), elementBesoin.getEb_couleur_id());
			subKey2.put(produitId, elementBesoin.getArticle().getId());
			key.put(subKey1, subKey2);
			
			if (mapListElementBesoinByTailleCouleurProduit.get(key) == null) {
				mapListElementBesoinByTailleCouleurProduit.put(key, new ArrayList<ElementBesoinValue>());
			}
	    	
	    	mapListElementBesoinByTailleCouleurProduit.get(key).add(elementBesoin);
			
		}
		
		for(ProduitCommandeValue produitCommande : listProduitCommande){
			
			for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
				
				Map<Object, Long> key = new HashMap<Object, Long>();
				Map<Long, Long> subKey = new HashMap<Long, Long>();
				
				subKey.put(detailsProduitCommande.getTailleId(), detailsProduitCommande.getCouleurId());
				key.put(subKey, produitId);
				
				if (mapListDetailsProduitCommandeByTailleCouleurProduit.get(key) == null) {
					mapListDetailsProduitCommandeByTailleCouleurProduit.put(key, new ArrayList<DetailsProduitCommandeValue>());
				}
				
				mapListDetailsProduitCommandeByTailleCouleurProduit.get(key).add(detailsProduitCommande);
			}
		}
		
		Iterator it = mapListDetailsProduitCommandeByTailleCouleurProduit.entrySet().iterator();
	    List <DetailsProduitCommandeValue> listDetailsProduitCommandeGrouppedByTailleCouleurProduit = new ArrayList<DetailsProduitCommandeValue>();
	    
	    while (it.hasNext()) {
	    	
	    	DetailsProduitCommandeValue element = new DetailsProduitCommandeValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<DetailsProduitCommandeValue>> pair = (Map.Entry<Object, List<DetailsProduitCommandeValue>>)it.next();
	    	
	    	Long quantiteTotal = ZEROL;
	    	
	    	for(DetailsProduitCommandeValue detailsProduitCommande: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal + ((detailsProduitCommande.getQuantite()!=null)?detailsProduitCommande.getQuantite():ZEROL);
	    		
	    	}

    		element.setTailleId(pair.getValue().get(FIRST_INDEX).getTailleId());
    		element.setCouleurId(pair.getValue().get(FIRST_INDEX).getCouleurId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listDetailsProduitCommandeGrouppedByTailleCouleurProduit.add(element);
	    	
	    }
	    
//	    it.remove();
	    
	    it = mapListElementBesoinByTailleCouleurProduit.entrySet().iterator();
	    List <ElementBesoinValue> listElementBesoinGrouppedByTailleCouleurProduit = new ArrayList<ElementBesoinValue>();
	    
	    while (it.hasNext()) {
	    	
	    	ElementBesoinValue element = new ElementBesoinValue();
	    	
	    	Map.Entry <Object, List<ElementBesoinValue>> pair = (Map.Entry<Object, List<ElementBesoinValue>>)it.next();
	    	
	    	Double quantiteTotal = ZEROD;
	    	
	    	for(ElementBesoinValue besoinArticleValue: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal 
	    				+ besoinArticleValue.getQuantite()*calculQuantiteCMD(listDetailsProduitCommandeGrouppedByTailleCouleurProduit, besoinArticleValue);
	    		
	    	}
	    	
	    	Long tailleId = pair.getValue().get(FIRST_INDEX).getEb_taille_id();
	    	Long couleurId = pair.getValue().get(FIRST_INDEX).getEb_couleur_id();
	    	
    		element.setEb_taille_id(tailleId);
    		element.setEb_couleur_id(couleurId);
    		element.setArticle(pair.getValue().get(FIRST_INDEX).getArticle());
    		element.setFicheBesoinId(pair.getValue().get(FIRST_INDEX).getFicheBesoinId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listElementBesoinGrouppedByTailleCouleurProduit.add(element);
	    }
	    
//	    it.remove();
	    
	    for(int i = 0; i < listElementBesoinGrouppedByTailleCouleurProduit.size(); i++){
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	
	    	Double quantiteBesoin = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getQuantite();
	       
	    	Long tailleId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_taille_id();
	    	Long couleurId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_couleur_id();
	    	Long articleId = (listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle()!=null)?
	    			listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle().getId() : null;
	    	
	    	element.setProduitId(produitId);
	    	element.setArticleId(articleId);
	    	element.setTailleId(tailleId);
	    	element.setCouleurId(couleurId);
	    	element.setBesoin((quantiteBesoin));
			
			if(mapArticleIdDesignation.containsKey(articleId)){
				element.setArticleDesignation(mapArticleIdDesignation.get(articleId));
			}
			
			Map<Object, Long> key = new HashMap<Object, Long>();
			Map<Long, Long> subKey = new HashMap<Long, Long>();
			
			subKey.put(tailleId, couleurId);
			key.put(subKey, produitId);
			
	    	listBesoinArticles.add(element);
	    }
	    
	    // Final Groupement, by articleId
	    List<BesoinArticleValue> finalListBesoinArticles = new ArrayList<BesoinArticleValue>();
		Map<Long, List<BesoinArticleValue>> mapFinalListBesoinArticles = new HashMap<Long, List<BesoinArticleValue>>();
		
		List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
		for(CouleurValue couleur : listCouleur){
			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
		}
		
		List<TailleValue> listTaille = taillePersistance.listeTaille();
		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
		for(TailleValue taille : listTaille){
			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
		}
	    
	    for(BesoinArticleValue besoinArticle : listBesoinArticles){
			
			Long key = besoinArticle.getArticleId();
			
			if (mapFinalListBesoinArticles.get(key) == null) {
				mapFinalListBesoinArticles.put(key, new ArrayList<BesoinArticleValue>());
			}
			
			mapFinalListBesoinArticles.get(key).add(besoinArticle);
		}
	    
	    it = mapFinalListBesoinArticles.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<BesoinArticleValue>> pair = (Map.Entry<Object, List<BesoinArticleValue>>)it.next();
	    	
	    	Double besoinTotal = ZEROD;
	    	
	    	String articleDesignation = pair.getValue().get(FIRST_INDEX).getArticleDesignation();
	    	Long articleId = pair.getValue().get(FIRST_INDEX).getArticleId();
    		Long couleurId = pair.getValue().get(FIRST_INDEX).getCouleurId();
    		Long tailleId = pair.getValue().get(FIRST_INDEX).getTailleId();
	    	
	    	for(BesoinArticleValue detailsProduitCommande: pair.getValue()){
	    		
	    		besoinTotal = besoinTotal +  ((detailsProduitCommande.getBesoin()!=null)?detailsProduitCommande.getBesoin():ZEROL);
	    	}
	    	
    		element.setArticleDesignation(articleDesignation);
    		element.setArticleId(articleId);
    		element.setCouleurId(couleurId);
    		element.setTailleId(tailleId);
    		element.setProduitId(produitId);
    		
			if(couleurId != null && mapCouleurIdDesignation.containsKey(couleurId)){
				element.setCouleurDesignation(mapCouleurIdDesignation.get(couleurId));
			}
			
			if(tailleId != null && mapTailleIdDesignation.containsKey(tailleId)){
				element.setTailleDesignation(mapTailleIdDesignation.get(tailleId));
			}
    		
    		element.setBesoin(besoinTotal);
	    	
	    	finalListBesoinArticles.add(element);
	    	
	    }
	    
	    // Final Groupement
	    List<BesoinArticleVue> finalList= new ArrayList<BesoinArticleVue>();
		
		Map<Object, List<BesoinArticleVue>> mapFinalList = new HashMap<Object, List<BesoinArticleVue>>();
	    
	    for(BesoinArticleValue besoinArticleValue : finalListBesoinArticles){
			
			Map<Long, Double> key = new HashMap<Long, Double>();
			
			key.put(besoinArticleValue.getArticleId(), besoinArticleValue.getBesoin());
			
			if (mapFinalList.get(key) == null) {
				mapFinalList.put(key, new ArrayList<BesoinArticleVue>());
			}
			
			BesoinArticleVue besoinArticleVue = new BesoinArticleVue();
			
			besoinArticleVue.setBesoin(besoinArticleValue.getBesoin());
			besoinArticleVue.setArticleDesignation(besoinArticleValue.getArticleDesignation());
			besoinArticleVue.setArticleId(besoinArticleValue.getArticleId());
			besoinArticleVue.setCouleurDesignation(besoinArticleValue.getCouleurDesignation());
			besoinArticleVue.setTailleDesignation(besoinArticleValue.getTailleDesignation());
			
			mapFinalList.get(key).add(besoinArticleVue);

		}
		
	    it = mapFinalList.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	BesoinArticleVue elementToAdd = new BesoinArticleVue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<BesoinArticleVue>> pair = (Map.Entry<Object, List<BesoinArticleVue>>)it.next();
	    	
	    	Double besoin = pair.getValue().get(FIRST_INDEX).getBesoin();
	    	
	    	Long articleId = pair.getValue().get(FIRST_INDEX).getArticleId();
	    	String articleDesignation = pair.getValue().get(FIRST_INDEX).getArticleDesignation();
    		
	    	String couleur = "";
	    	String taille = "";
	    	
	    	for(BesoinArticleVue element: pair.getValue()){
	    		
	    		if(element.getCouleurDesignation() != null){
	    			
	    			if(couleur != ""){
	    				
	    				couleur = couleur + SEPARATOR_COULEUR + element.getCouleurDesignation();
	    			}else{
	    				
	    				couleur = element.getCouleurDesignation();
	    			}
	    			
	    		}
	    			
	    		if(element.getTailleDesignation() != null){
	    			
	    			if(taille != ""){
	    				
	    				taille = taille + SEPARATOR_TAILLE + element.getTailleDesignation();
	    			}else{
	    				
	    				taille = element.getTailleDesignation();
	    			}
	    			
	    		}
	    	}
	    	
	    	elementToAdd.setArticleId(articleId);
	    	elementToAdd.setArticleDesignation(articleDesignation);
	    	elementToAdd.setBesoin(besoin);
	    	elementToAdd.setCouleurDesignation(couleur);
	    	elementToAdd.setTailleDesignation(taille);
	    	
	    	
	    	if(articleId != null){
	    		
	    		ArticleValue article = articlePersistance.getArticleParId(articleId);
	    		
	    		if(article != null){
	    			
	    			if(article.getSousFamilleArtEntite() != null){
	    				
	    				SousFamilleArticleValue sousFamille = sousFamilleArticlePersistance.rechercheSousFamilleArticleById(article.getSousFamilleArtEntite());
	    				
	    				if(sousFamille != null){
	    					
	    					if(sousFamille.getIdFamilleArticle() != null){
	    						
	    						FamilleArticleValue famille = familleArticlePersistance.rechercheFamilleArticleById(sousFamille.getIdFamilleArticle());
	    						
	    						if(famille != null){
	    							
	    							if(famille.getIdTypeArticle() != null){
	    								elementToAdd.setTypeArticle(famille.getIdTypeArticle());
	    							}
	    							
	    						}
	    					}
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	    	
	    	finalList.add(elementToAdd);
	    }
		
		return finalList;

	}
	

	public Long calculQuantiteCMD(List <DetailsProduitCommandeValue> list,ElementBesoinValue element){
		Long quantite = ZEROL;
		Long produitId = null;
		Long ficheBesoinId = element.getFicheBesoinId();
		
		if(ficheBesoinId != null){
    		
    		FicheBesoinValue ficheBesoin = ficheBesoinPersistance.getById(ficheBesoinId);
    		
    		if(ficheBesoin != null){
    			
    			if(ficheBesoin.getProduitValue() != null){
    				
    				produitId = ficheBesoin.getProduitValue().getId();
    			}
    				
    		}
    	}
		
		for (DetailsProduitCommandeValue detail : list){
			
			Long detailProduitId = detail.getProduitCommandeId();
			
			if(produitId.equals(detailProduitId) 
					&& detail.getCouleurId().equals(element.getEb_couleur_id()) 
					&& detail.getTailleId().equals(element.getEb_taille_id())){
				return detail.getQuantite();
				
				
			}
		}
		
	
		return quantite;
	}


	@Override
	public List<BesoinArticleValue> rechercheMulticritere(
			RechercheMulticritereProduitCommandeValue request) {
		
		List<BesoinArticleValue> listBesoinArticles = new ArrayList<BesoinArticleValue>();
		
		List<FicheBesoinValue> listFicheBesoin= ficheBesoinPersistance.getAll();
		
		ResultatRechecheProduitCommandeValue resultatRechecheListProduitCommande = produitCommandePersistance.rechercherMultiCritere(request);
		
		List<ProduitCommandeValue> listProduitCommande = new ArrayList<ProduitCommandeValue>();
		
		if(resultatRechecheListProduitCommande != null){
			
			for(ProduitCommandeValue produitCommande : resultatRechecheListProduitCommande.getListProduitCommandeValue()){
				
				listProduitCommande.add(produitCommande);
			}
		}
		
		// Map<tailleId, couleurId> , List<ElementBesoinValue>
		Map<Object, List<ElementBesoinValue>> mapListElementBesoinByTailleCouleurProduit = new HashMap<Object, List<ElementBesoinValue>>();
		
		// Map<tailleId, couleurId> , List<DetailsProduitCommandeValue>
		Map<Object, List<DetailsProduitCommandeValue>> mapListDetailsProduitCommandeByTailleCouleurProduit = new HashMap<Object, List<DetailsProduitCommandeValue>>();
		
		for(FicheBesoinValue ficheBesoin : listFicheBesoin){
			
			for(ElementBesoinValue elementBesoin: ficheBesoin.getElementBesoinValue()){
				
				Long currentProduitId = (ficheBesoin.getProduitValue()!=null)?ficheBesoin.getProduitValue().getId():null;
				
				Map<Object, Object> key = new HashMap<Object, Object>();
				Map<Long, Long> subKey1 = new HashMap<Long, Long>();
				Map<Long, Long> subKey2 = new HashMap<Long, Long>();
				
				subKey1.put(elementBesoin.getEb_taille_id(), elementBesoin.getEb_couleur_id());
				subKey2.put(currentProduitId, elementBesoin.getArticle().getId());
				key.put(subKey1, subKey2);
				
				if (mapListElementBesoinByTailleCouleurProduit.get(key) == null) {
					mapListElementBesoinByTailleCouleurProduit.put(key, new ArrayList<ElementBesoinValue>());
				}
		    	
		    	mapListElementBesoinByTailleCouleurProduit.get(key).add(elementBesoin);
				
			}
		}
		
		for(ProduitCommandeValue produitCommande : listProduitCommande){
			
			for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
				
				Map<Object, Long> key = new HashMap<Object, Long>();
				Map<Long, Long> subKey = new HashMap<Long, Long>();
				
				subKey.put(detailsProduitCommande.getTailleId(), detailsProduitCommande.getCouleurId());
				key.put(subKey, produitCommande.getProduitId());
				
				detailsProduitCommande.setProduitCommandeId(produitCommande.getProduitId());
				
				if (mapListDetailsProduitCommandeByTailleCouleurProduit.get(key) == null) {
					mapListDetailsProduitCommandeByTailleCouleurProduit.put(key, new ArrayList<DetailsProduitCommandeValue>());
				}
				
				mapListDetailsProduitCommandeByTailleCouleurProduit.get(key).add(detailsProduitCommande);
			}
		}
		
		Iterator it = mapListDetailsProduitCommandeByTailleCouleurProduit.entrySet().iterator();
	    List <DetailsProduitCommandeValue> listDetailsProduitCommandeGrouppedByTailleCouleurProduit = new ArrayList<DetailsProduitCommandeValue>();
	    
	    while (it.hasNext()) {
	    	
	    	DetailsProduitCommandeValue element = new DetailsProduitCommandeValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<DetailsProduitCommandeValue>> pair = (Map.Entry<Object, List<DetailsProduitCommandeValue>>)it.next();
	    	
	    	Long quantiteTotal = ZEROL;
	    	
	    	for(DetailsProduitCommandeValue detailsProduitCommande: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal + ((detailsProduitCommande.getQuantite()!=null)?detailsProduitCommande.getQuantite():ZEROL);
	    		
	    	}

	    	element.setProduitCommandeId(pair.getValue().get(FIRST_INDEX).getProduitCommandeId());
	 
    		element.setTailleId(pair.getValue().get(FIRST_INDEX).getTailleId());
    		element.setCouleurId(pair.getValue().get(FIRST_INDEX).getCouleurId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listDetailsProduitCommandeGrouppedByTailleCouleurProduit.add(element);
	    	
	    }
	    
	    it.remove();
	    
	    it = mapListElementBesoinByTailleCouleurProduit.entrySet().iterator();
	    List <ElementBesoinValue> listElementBesoinGrouppedByTailleCouleurProduit = new ArrayList<ElementBesoinValue>();
	    
	    while (it.hasNext()) {
	    	
	    	ElementBesoinValue element = new ElementBesoinValue();
	    	
	    	Map.Entry <Object, List<ElementBesoinValue>> pair = (Map.Entry<Object, List<ElementBesoinValue>>)it.next();
	    	
	    	Double quantiteTotal = ZEROD;
	    	
	    	for(ElementBesoinValue besoinArticleValue: pair.getValue()){
	    		
	    		quantiteTotal = quantiteTotal 
	    				+ besoinArticleValue.getQuantite()*calculQuantiteCMD(listDetailsProduitCommandeGrouppedByTailleCouleurProduit, besoinArticleValue);
	    	
	    		
	    	}
	    	
	    	Long tailleId = pair.getValue().get(FIRST_INDEX).getEb_taille_id();
	    	Long couleurId = pair.getValue().get(FIRST_INDEX).getEb_couleur_id();
	    	
    		element.setEb_taille_id(tailleId);
    		element.setEb_couleur_id(couleurId);
    		element.setArticle(pair.getValue().get(FIRST_INDEX).getArticle());
    		element.setFicheBesoinId(pair.getValue().get(FIRST_INDEX).getFicheBesoinId());
    		
	    	element.setQuantite(quantiteTotal);
	    	
	    	listElementBesoinGrouppedByTailleCouleurProduit.add(element);
	    }
	    
	    it.remove();
	    
	    for(int i = 0; i < listElementBesoinGrouppedByTailleCouleurProduit.size(); i++){
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	Long produitId = null;
	    	
	    	Long ficheBesoinId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getFicheBesoinId();
	    	
	    	if(ficheBesoinId != null){
	    		
	    		FicheBesoinValue ficheBesoin = ficheBesoinPersistance.getById(ficheBesoinId);
	    		
	    		if(ficheBesoin != null){
	    			
	    			if(ficheBesoin.getProduitValue() != null){
	    				
	    				produitId = ficheBesoin.getProduitValue().getId();
	    			}
	    				
	    		}
	    	}
	    	
	    	Double quantiteBesoin = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getQuantite();
	       
	    	Long tailleId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_taille_id();
	    	Long couleurId = listElementBesoinGrouppedByTailleCouleurProduit.get(i).getEb_couleur_id();
	    	Long articleId = (listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle()!=null)?
	    			listElementBesoinGrouppedByTailleCouleurProduit.get(i).getArticle().getId() : null;
	    	
//	    	Long sousFamilleArticleId = null;
	    	
	    	element.setProduitId(produitId);
	    	element.setArticleId(articleId);
	    	element.setTailleId(tailleId);
	    	element.setCouleurId(couleurId);
	    	element.setBesoin((quantiteBesoin));
			
			if(articleId != null){
				
				ArticleValue article = articlePersistance.getArticleParId(articleId);
				
				element.setArticleDesignation(article.getDesignation());
				element.setArticleReference(article.getRef());
				element.setArticlePrixUnitaire(article.getPu());
				
				element.setSousFamilleDesignation(article.getSousFamilleArtEntiteDesignation());
				
			}

			Map<Object, Long> key = new HashMap<Object, Long>();
			Map<Long, Long> subKey = new HashMap<Long, Long>();
			
			subKey.put(tailleId, couleurId);
			key.put(subKey, produitId);
			
			if(mapListDetailsProduitCommandeByTailleCouleurProduit.containsKey(key)){
				
				List<DetailsProduitCommandeValue> listDetailsProduitCommande = mapListDetailsProduitCommandeByTailleCouleurProduit.get(key);
				
				Long quantiteCommande = ZEROL;
				for(DetailsProduitCommandeValue detail : listDetailsProduitCommande){
					
					quantiteCommande = quantiteCommande + ((detail.getQuantite()!=null)?detail.getQuantite():ZEROL);
				}
				
				element.setQuantiteCommande(quantiteCommande);
			}
			
	    	listBesoinArticles.add(element);
	    }
	    
	    // Final Groupement, by articleId
	    List<BesoinArticleValue> finalListBesoinArticles = new ArrayList<BesoinArticleValue>();
		Map<Long, List<BesoinArticleValue>> mapFinalListBesoinArticles = new HashMap<Long, List<BesoinArticleValue>>();
	    
	    for(BesoinArticleValue besoinArticle : listBesoinArticles){
			
			Long key = besoinArticle.getArticleId();
			
			if (mapFinalListBesoinArticles.get(key) == null) {
				mapFinalListBesoinArticles.put(key, new ArrayList<BesoinArticleValue>());
			}
			
			mapFinalListBesoinArticles.get(key).add(besoinArticle);
		}
	    
	    it = mapFinalListBesoinArticles.entrySet().iterator();
	    while (it.hasNext()) {
	    	
	    	BesoinArticleValue element = new BesoinArticleValue();
	    	
	    	@SuppressWarnings("unchecked")
			Map.Entry <Object, List<BesoinArticleValue>> pair = (Map.Entry<Object, List<BesoinArticleValue>>)it.next();
	    	
	    	Long quantiteCommandeTotal = ZEROL;
	    	Double besoinTotal = ZEROD;
	    	Double prixUnitaire = ZEROD;
	    	
	    	for(BesoinArticleValue detailsProduitCommande: pair.getValue()){
	    		quantiteCommandeTotal = quantiteCommandeTotal + ((detailsProduitCommande.getQuantiteCommande()!=null)?detailsProduitCommande.getQuantiteCommande():ZEROL);
	    		besoinTotal = besoinTotal +  ((detailsProduitCommande.getBesoin()!=null)?detailsProduitCommande.getBesoin():ZEROL);
	    	}
	    	
	    	prixUnitaire = (pair.getValue().get(FIRST_INDEX).getArticlePrixUnitaire() != null)?pair.getValue().get(FIRST_INDEX).getArticlePrixUnitaire():ZEROD;
	    	
    		element.setArticleDesignation(pair.getValue().get(FIRST_INDEX).getArticleDesignation());
    		element.setArticleReference(pair.getValue().get(FIRST_INDEX).getArticleReference());
    		element.setArticlePrixUnitaire(prixUnitaire);
    		
    		element.setSousFamilleDesignation(pair.getValue().get(FIRST_INDEX).getSousFamilleDesignation());
    		
    		element.setBesoin(besoinTotal);
    		element.setQuantiteCommande(quantiteCommandeTotal);
    		
    		element.setTotal(prixUnitaire * besoinTotal);
	    	
	    	finalListBesoinArticles.add(element);
	    	
	    }
	    
	    it.remove();
		
//		return listBesoinArticles;
		return finalListBesoinArticles;

	}

	
}
