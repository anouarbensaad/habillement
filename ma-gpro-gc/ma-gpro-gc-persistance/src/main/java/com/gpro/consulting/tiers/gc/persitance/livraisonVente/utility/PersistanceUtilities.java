package com.gpro.consulting.tiers.gc.persitance.livraisonVente.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.DetailProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.livraisonVenteElementValue;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite.DetailProduitLivraisonEntite;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite.LivraisonVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite.ProduitLivraisonEntite;

/**
 * @author Ameni Berrich
 *
 */
public class PersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistanceUtilities.class);
	

	/**************************
	 ******* To Entity ********/
	// LivraisonVente ToEntite
	public static LivraisonVenteEntite toEntite(LivraisonVenteValue dto) {

		LivraisonVenteEntite entity = new LivraisonVenteEntite();

		entity.setId(dto.getId());
		entity.setSiteId(dto.getSiteId());
		entity.setReference(dto.getReference());
		entity.setRefCommande(dto.getRefCommande());
		entity.setSaison(dto.getSaison());
		entity.setPrixTotal(dto.getPrixTotal());
		entity.setDateCommande(dto.getDateCommande());
		entity.setDateLivraison(dto.getDateLivraison());
		entity.setObservations(dto.getObservations());
		entity.setPartieIntersseId(dto.getPartieIntersseId());
		entity.setModeReglement(dto.getModeReglement());
		entity.setPoids(dto.getPoids());
		entity.setAgentId(dto.getAgentId());
		entity.setRefCommande(dto.getRefCommande());
		
		entity.setBlSuppression(false);
		if (dto.getProduitLivraison() != null) {
			Set<ProduitLivraisonEntite> list = new HashSet<ProduitLivraisonEntite>();
			for (ProduitLivraisonValue produitLivraisonValue : dto
					.getProduitLivraison()) {
				ProduitLivraisonEntite produitLivraisonEntity = toEntite(produitLivraisonValue);
				produitLivraisonEntity.setLivraisonVente(entity);
				list.add(produitLivraisonEntity);
			}
			entity.setProduitLivraison(list);
		}

		return entity;
	}

	// ProduitLivraison ToEntite
	private static ProduitLivraisonEntite toEntite(ProduitLivraisonValue dto) {

		ProduitLivraisonEntite entity = new ProduitLivraisonEntite();

		entity.setId(dto.getId());
		entity.setDevise(dto.getDevise());
		entity.setPrix(dto.getPrix());
		entity.setQuantite(dto.getQuantite());
		entity.setReferenceCommande(dto.getReferenceCommande());
		
		if (dto.getLivraisonVenteId() != null) {
			LivraisonVenteEntite livraisonVenteEntite = new LivraisonVenteEntite();
			livraisonVenteEntite.setId(dto.getLivraisonVenteId());
			entity.setLivraisonVente(livraisonVenteEntite);
		}

		if (dto.getProduitId() != null) {
			ProduitEntity produitEntity = new ProduitEntity();
			produitEntity.setId(dto.getProduitId());
			entity.setProduit(produitEntity);
		}

		if (dto.getListDetailsProduitLivraison() != null) {

			Set<DetailProduitLivraisonEntite> list = new HashSet<DetailProduitLivraisonEntite>();
			for (DetailProduitLivraisonValue detailsProduitLivraisonValue : dto
					.getListDetailsProduitLivraison()) {
				DetailProduitLivraisonEntite detailsProduitLivraisonEntite = toEntity(detailsProduitLivraisonValue);
				detailsProduitLivraisonEntite.setProduitLivraison(entity);

				list.add(detailsProduitLivraisonEntite);
			}
			entity.setListDetailsProduitLivraison(list);
		}

		return entity;
	}

	// DetailProduitLivraison ToEntity
	private static DetailProduitLivraisonEntite toEntity(
			DetailProduitLivraisonValue dto) {

		DetailProduitLivraisonEntite entity = new DetailProduitLivraisonEntite();

		entity.setId(dto.getId());
		entity.setCouleurId(dto.getCouleurId());
		entity.setQuantite(dto.getQuantite());
		entity.setTailleId(dto.getTailleId());

		if (dto.getProduitLivraisonId() != null) {
			ProduitLivraisonEntite produitLivEntite = new ProduitLivraisonEntite();
			produitLivEntite.setId(dto.getProduitLivraisonId());
			entity.setProduitLivraison(produitLivEntite);
		}

		return entity;
	}

	/**************************
	 ******* To Value ********/
	public static LivraisonVenteValue toValue(LivraisonVenteEntite entity) {

		LivraisonVenteValue dto = new LivraisonVenteValue();
		
		dto.setId(entity.getId());
		dto.setSiteId(entity.getSiteId());
		dto.setSaison(entity.getSaison());
		dto.setReference(entity.getReference());
		dto.setRefCommande(entity.getRefCommande());
		dto.setPrixTotal(entity.getPrixTotal());
		dto.setDateCommande(entity.getDateCommande());
		dto.setDateLivraison(entity.getDateLivraison());
		dto.setObservations(entity.getObservations());
		dto.setPartieIntersseId(entity.getPartieIntersseId());
		dto.setColis(entity.getColis());
		dto.setModeReglement(entity.getModeReglement());
		dto.setPoids(entity.getPoids());
		dto.setAgentId(entity.getAgentId());
		
		if (entity.getProduitLivraison() != null) {
			Set<ProduitLivraisonValue> listPoduitLivraison = new HashSet<ProduitLivraisonValue>();
			for (ProduitLivraisonEntite produitLivraisonEntity : entity.getProduitLivraison()) {
				ProduitLivraisonValue produitLivraisonValue = toValue(produitLivraisonEntity);
				listPoduitLivraison.add(produitLivraisonValue);
			}
			dto.setProduitLivraison(listPoduitLivraison);
		}
		
		return dto;
	}
	
	//ProduitLivraison ToValue
	public static ProduitLivraisonValue toValue(
			ProduitLivraisonEntite vProduitLivraisonEntite) {
		ProduitLivraisonValue pProduitLivraisonValue = new ProduitLivraisonValue();
		pProduitLivraisonValue.setId(vProduitLivraisonEntite.getId());
		pProduitLivraisonValue.setDevise(vProduitLivraisonEntite.getDevise());
		pProduitLivraisonValue.setPrix(vProduitLivraisonEntite.getPrix());
		pProduitLivraisonValue.setReferenceCommande(vProduitLivraisonEntite.getReferenceCommande());
		if(vProduitLivraisonEntite.getProduit() != null) {
			pProduitLivraisonValue.setProduitId(vProduitLivraisonEntite.getProduit().getId());
		}
		pProduitLivraisonValue.setQuantite(vProduitLivraisonEntite.getQuantite());
		
		if(vProduitLivraisonEntite.getLivraisonVente() != null){
			pProduitLivraisonValue.setLivraisonVenteId(vProduitLivraisonEntite.getLivraisonVente().getId());
		}

		if(vProduitLivraisonEntite.getListDetailsProduitLivraison() != null){
	    	
			List<DetailProduitLivraisonValue> listeDetailsProduitLivraison = new ArrayList<DetailProduitLivraisonValue>();
     
			for (DetailProduitLivraisonEntite detailsProduitLivraisonEntity : vProduitLivraisonEntite.getListDetailsProduitLivraison()) {
				DetailProduitLivraisonValue detailsProduitLivraisonValue = toValue(detailsProduitLivraisonEntity);
				listeDetailsProduitLivraison.add(detailsProduitLivraisonValue);
			}
     
			pProduitLivraisonValue.setListDetailsProduitLivraison(listeDetailsProduitLivraison);
		}
					
		return pProduitLivraisonValue;
	}
	
	//DetailPrpduitLivraison ToValue
	private static DetailProduitLivraisonValue toValue(
			DetailProduitLivraisonEntite entity) {
		
		DetailProduitLivraisonValue dto = new DetailProduitLivraisonValue();
		
		dto.setId(entity.getId());
		dto.setCouleurId(entity.getCouleurId());
		
		dto.setTailleId(entity.getTailleId());
		dto.setQuantite(entity.getQuantite());
		
		if(entity.getProduitLivraison() != null){
			dto.setProduitLivraisonId(entity.getProduitLivraison().getId());
		}
		
		return dto;
	}

	public static livraisonVenteElementValue toElementValue(
			LivraisonVenteEntite entity) {
		
		livraisonVenteElementValue result = new livraisonVenteElementValue();
		
		result.setId(entity.getId());
		result.setReference(entity.getReference());
		result.setReferenceCommande(entity.getRefCommande());
		result.setPartieIntersseId(entity.getPartieIntersseId());
		result.setDateLivraison(entity.getDateLivraison());
		result.setDateCommande(entity.getDateCommande());
		result.setSaison(entity.getSaison());
		result.setPrixTotal(entity.getPrixTotal());
		result.setColis(entity.getColis());
		
		return result;
	}

}
