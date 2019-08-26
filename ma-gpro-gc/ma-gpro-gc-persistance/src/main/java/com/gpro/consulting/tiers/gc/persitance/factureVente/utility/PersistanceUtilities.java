package com.gpro.consulting.tiers.gc.persitance.factureVente.utility;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.DiversFactureValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteElementValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ProduitFactureVenteValue;
import com.gpro.consulting.tiers.gc.persitance.factureVente.entite.DiversFactureEntite;
import com.gpro.consulting.tiers.gc.persitance.factureVente.entite.FactureVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.factureVente.entite.ProduitFactureVenteEntite;

/**
 * @author Ameni Berrich
 *
 */
public class PersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(PersistanceUtilities.class);

	/************** To Entity **************/
	// FactureVente ToEntite
	public static FactureVenteEntite toEntite(FactureVenteValue dto) {

		FactureVenteEntite entity = new FactureVenteEntite();

		entity.setId(dto.getId());
		entity.setSiteId(dto.getSiteId());
		entity.setReference(dto.getReference());
		entity.setDateFacture(dto.getDateFacture());
		entity.setDateEcheance(dto.getDateEcheance());
		entity.setObservations(dto.getObservations());
		entity.setPartintId(dto.getPartintId());
		entity.setRefCommande(dto.getRefCommande());
		entity.setRefLivraison(dto.getRefLivraison());
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setModeReglement(dto.getModeReglement());
		entity.setPrixTotalHT(dto.getPrixTotalHT());
		entity.setMontantTTC(dto.getMontantTTC());
		entity.setMontantTVA(dto.getMontantTVA());
		entity.setTauxTVA(dto.getTauxTVA());
		entity.setAgentId(dto.getAgentId());
		entity.setTotalFacture(dto.getTotalFacture());
		entity.setPoidsBrut(dto.getPoidsBrut());
		entity.setPoidsNet(dto.getPoidsNet());
		entity.setTotalColis(dto.getTotalColis());
		entity.setTotalPalette(dto.getTotalPalette());
		entity.setValeurMatierePremiere(dto.getValeurMatierePremiere());
		entity.setValeurAjouteArticle(dto.getValeurAjouteArticle());
		entity.setOrigineTissus(dto.getOrigineTissus());
		entity.setIncoterm(dto.getIncoterm());
		entity.setVolume(dto.getVolume());

		if (dto.getProduitFactureVente() != null) {
			Set<ProduitFactureVenteEntite> list = new HashSet<ProduitFactureVenteEntite>();
			for (ProduitFactureVenteValue produitLivraisonValue : dto.getProduitFactureVente()) {
				ProduitFactureVenteEntite produitLivraisonEntity = toEntite(produitLivraisonValue);
				produitLivraisonEntity.setFactureVente(entity);
				list.add(produitLivraisonEntity);
			}
			entity.setProduitFactureVente(list);
		}

		// Liste des DiversFacture
		if (dto.getDiversFactureValue() != null) {
			Set<DiversFactureEntite> listDivers = new HashSet<DiversFactureEntite>();
			for (DiversFactureValue diversFactureValue : dto.getDiversFactureValue()) {
				DiversFactureEntite diversFactureEntite = toEntite(diversFactureValue);
				diversFactureEntite.setFactureVente(entity);
				listDivers.add(diversFactureEntite);
			}
			entity.setDiversFactureEntite(listDivers);
		}

		return entity;
	}

	// ProduitFactureVente ToEntite
	private static ProduitFactureVenteEntite toEntite(ProduitFactureVenteValue dto) {

		ProduitFactureVenteEntite entity = new ProduitFactureVenteEntite();

		entity.setId(dto.getId());
		entity.setDevise(dto.getDevise());
		entity.setPrix(dto.getPrix());
		entity.setQuantite(dto.getQuantite());
		entity.setColis(dto.getColis());
		entity.setPalette(dto.getPalette());
		entity.setReferenceCommande(dto.getReferenceCommande());
		entity.setPrixTMP(dto.getPrixTMP());

		if (dto.getFactureVenteId() != null) {
			FactureVenteEntite factureVenteEntite = new FactureVenteEntite();
			factureVenteEntite.setId(dto.getFactureVenteId());
			entity.setFactureVente(factureVenteEntite);
		}

		if (dto.getProduitId() != null) {
			ProduitEntity produitEntity = new ProduitEntity();
			produitEntity.setId(dto.getProduitId());
			entity.setProduit(produitEntity);
		}

		return entity;
	}

	// DiversFacture ToEntite
	private static DiversFactureEntite toEntite(DiversFactureValue dto) {

		DiversFactureEntite entity = new DiversFactureEntite();

		entity.setId(dto.getId());
		entity.setPrix(dto.getPrix());
		entity.setQuantite(dto.getQuantite());
		entity.setDesignation(dto.getDesignation());
		entity.setBl_suppression(dto.getBl_suppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDate_modification(dto.getDate_modification());

		if (dto.getFactureVenteId() != null) {
			FactureVenteEntite factureVenteEntite = new FactureVenteEntite();
			factureVenteEntite.setId(dto.getFactureVenteId());
			entity.setFactureVente(factureVenteEntite);
		}

		return entity;
	}

	/**************************
	 ******* To Value
	 ********/
	public static FactureVenteValue toValue(FactureVenteEntite pFactureVenteEntite) {

		FactureVenteValue vFactureVenteValue = new FactureVenteValue();

		vFactureVenteValue.setId(pFactureVenteEntite.getId());
		vFactureVenteValue.setSiteId(pFactureVenteEntite.getSiteId());
		vFactureVenteValue.setReference(pFactureVenteEntite.getReference());
		vFactureVenteValue.setRefCommande(pFactureVenteEntite.getRefCommande());
		vFactureVenteValue.setRefLivraison(pFactureVenteEntite.getRefLivraison());
		vFactureVenteValue.setPrixTotalHT(pFactureVenteEntite.getPrixTotalHT());
		vFactureVenteValue.setDateFacture(pFactureVenteEntite.getDateFacture());
		vFactureVenteValue.setDateEcheance(pFactureVenteEntite.getDateEcheance());
		vFactureVenteValue.setObservations(pFactureVenteEntite.getObservations());
		vFactureVenteValue.setPartintId(pFactureVenteEntite.getPartintId());
		vFactureVenteValue.setModeReglement(pFactureVenteEntite.getModeReglement());
		vFactureVenteValue.setBlSuppression(pFactureVenteEntite.isBlSuppression());
		vFactureVenteValue.setPrixTotalHT(pFactureVenteEntite.getPrixTotalHT());
		vFactureVenteValue.setMontantTTC(pFactureVenteEntite.getMontantTTC());
		vFactureVenteValue.setMontantTVA(pFactureVenteEntite.getMontantTVA());
		vFactureVenteValue.setTauxTVA(pFactureVenteEntite.getTauxTVA());
		vFactureVenteValue.setAgentId(pFactureVenteEntite.getAgentId());
		vFactureVenteValue.setTotalFacture(pFactureVenteEntite.getTotalFacture());
		vFactureVenteValue.setPoidsBrut(pFactureVenteEntite.getPoidsBrut());
		vFactureVenteValue.setPoidsNet(pFactureVenteEntite.getPoidsNet());
		vFactureVenteValue.setTotalColis(pFactureVenteEntite.getTotalColis());
		vFactureVenteValue.setTotalPalette(pFactureVenteEntite.getTotalPalette());
		vFactureVenteValue.setValeurMatierePremiere(pFactureVenteEntite.getValeurMatierePremiere());
		vFactureVenteValue.setValeurAjouteArticle(pFactureVenteEntite.getValeurAjouteArticle());
		vFactureVenteValue.setOrigineTissus(pFactureVenteEntite.getOrigineTissus());
		vFactureVenteValue.setIncoterm(pFactureVenteEntite.getIncoterm());
		vFactureVenteValue.setVolume(pFactureVenteEntite.getVolume());

		/** Liste ProduitFactureVente **/
		if (pFactureVenteEntite.getProduitFactureVente() != null) {
			Set<ProduitFactureVenteValue> vListeProduitLiv = new HashSet<ProduitFactureVenteValue>();
			for (ProduitFactureVenteEntite vProduitEntite : pFactureVenteEntite.getProduitFactureVente()) {
				ProduitFactureVenteValue vProduitFactureVente = toValue(vProduitEntite);
				vListeProduitLiv.add(vProduitFactureVente);
			}
			vFactureVenteValue.setProduitFactureVente(vListeProduitLiv);
		}

		/** Liste DiversFacture **/
		if (pFactureVenteEntite.getDiversFactureEntite() != null) {

			Set<DiversFactureValue> vListeDivers = new HashSet<DiversFactureValue>();
			for (DiversFactureEntite vDiversEntite : pFactureVenteEntite.getDiversFactureEntite()) {
				DiversFactureValue vProduitFactureVente = toValue(vDiversEntite);
				vListeDivers.add(vProduitFactureVente);
			}
			vFactureVenteValue.setDiversFactureValue(vListeDivers);
		}

		return vFactureVenteValue;
	}

	// ProduitFactureVente ToValue
	public static ProduitFactureVenteValue toValue(ProduitFactureVenteEntite vProduitFactureVenteEntite) {
		ProduitFactureVenteValue pProduitFactureVenteValue = new ProduitFactureVenteValue();
		pProduitFactureVenteValue.setId(vProduitFactureVenteEntite.getId());
		pProduitFactureVenteValue.setDevise(vProduitFactureVenteEntite.getDevise());
		pProduitFactureVenteValue.setPrix(vProduitFactureVenteEntite.getPrix());
		pProduitFactureVenteValue.setColis(vProduitFactureVenteEntite.getColis());
		pProduitFactureVenteValue.setPalette(vProduitFactureVenteEntite.getPalette());
		pProduitFactureVenteValue.setReferenceCommande(vProduitFactureVenteEntite.getReferenceCommande());
		pProduitFactureVenteValue.setPrixTMP(vProduitFactureVenteEntite.getPrixTMP());

		if (vProduitFactureVenteEntite.getProduit() != null) {
			pProduitFactureVenteValue.setProduitId(vProduitFactureVenteEntite.getProduit().getId());
		}
		pProduitFactureVenteValue.setQuantite(vProduitFactureVenteEntite.getQuantite());

		if (vProduitFactureVenteEntite.getFactureVente() != null) {
			pProduitFactureVenteValue.setFactureVenteId(vProduitFactureVenteEntite.getFactureVente().getId());
		}

		return pProduitFactureVenteValue;
	}

	// DiversFacture ToValue
	public static DiversFactureValue toValue(DiversFactureEntite vDiversFactureEntite) {
		DiversFactureValue pDiversFactureValue = new DiversFactureValue();
		pDiversFactureValue.setId(vDiversFactureEntite.getId());
		pDiversFactureValue.setPrix(vDiversFactureEntite.getPrix());
		pDiversFactureValue.setQuantite(vDiversFactureEntite.getQuantite());
		pDiversFactureValue.setDesignation(vDiversFactureEntite.getDesignation());
		pDiversFactureValue.setBl_suppression(vDiversFactureEntite.getBl_suppression());
		pDiversFactureValue.setDateCreation(vDiversFactureEntite.getDateCreation());
		pDiversFactureValue.setDate_modification(vDiversFactureEntite.getDate_modification());

		if (vDiversFactureEntite.getFactureVente() != null) {
			pDiversFactureValue.setFactureVenteId(vDiversFactureEntite.getFactureVente().getId());
		}

		return pDiversFactureValue;
	}

	public static FactureVenteElementValue toElementValue(FactureVenteEntite entity) {

		FactureVenteElementValue result = new FactureVenteElementValue();

		result.setId(entity.getId());
		result.setReference(entity.getReference());
		result.setReferenceCommande(entity.getRefCommande());
		result.setPartintId(entity.getPartintId());
		result.setModeReglement(entity.getModeReglement());
		result.setPrixTotalHT(entity.getPrixTotalHT());
		result.setDateEcheance(entity.getDateEcheance());
		result.setDateFacturation(entity.getDateFacture());
		return result;
	}

}
