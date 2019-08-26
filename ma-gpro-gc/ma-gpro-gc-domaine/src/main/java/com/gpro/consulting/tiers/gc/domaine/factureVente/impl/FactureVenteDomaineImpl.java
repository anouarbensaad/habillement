package com.gpro.consulting.tiers.gc.domaine.factureVente.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.DiversFactureValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ProduitFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.ProduitLivraisonVue;
import com.gpro.consulting.tiers.gc.domaine.factureVente.IFactureVenteDomaine;
import com.gpro.consulting.tiers.gc.persitance.factureVente.IFactureVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.ILivraisonVentePersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class FactureVenteDomaineImpl implements IFactureVenteDomaine {

	private static final Logger logger = LoggerFactory.getLogger(FactureVenteDomaineImpl.class);

	@Autowired
	private IFactureVentePersistance factureVentePersistance;

	@Autowired
	private ILivraisonVentePersistance livraisonVentePersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	
	@Override
	public String creerFactureVente(FactureVenteValue pFactureVenteValue) {
		enrichirFactureVente(pFactureVenteValue);
		return factureVentePersistance.creerFactureVente(pFactureVenteValue);
	}

	private void enrichirFactureVente(FactureVenteValue pFactureVenteValue) {
		// RefBC from refBL
		RechercheMulticritereLivraisonVenteValue requestBL = new RechercheMulticritereLivraisonVenteValue();
		if (estNonVide(pFactureVenteValue.getRefLivraison())) {
			requestBL.setReference(pFactureVenteValue.getRefCommande());
			ResultatRechecheLivraisonVenteValue livVenteValue = livraisonVentePersistance
					.rechercherLivraisonVenteMultiCritere(requestBL);

			if (livVenteValue != null) {

				if (livVenteValue.getLivraisonVenteValues() != null) {
					if (livVenteValue.getLivraisonVenteValues().size() > 0) {
						/** refBC */
						String refBC = livVenteValue.getLivraisonVenteValues().get(0).getReferenceCommande();
						pFactureVenteValue.setRefCommande(refBC);
					}
				}
			}
		}

		// Calcule des Prix
		Double prixTotalHT = 0D;
		Double montantTTC = 0D;
		Double montantTVA = 0D;
		Double tauxTVA = 0D;
		tauxTVA = pFactureVenteValue.getTauxTVA().doubleValue();

		/** prixTotalHT **/
		for (ProduitFactureVenteValue produitFactureElement : pFactureVenteValue.getProduitFactureVente()) {
			if ((produitFactureElement.getPrix() != null) && (produitFactureElement.getQuantite() != null))
				prixTotalHT = prixTotalHT + (produitFactureElement.getPrix() * produitFactureElement.getQuantite());

		}
		logger.debug("== prixTotalHT" + prixTotalHT);
		/** montantTTC **/
		if (tauxTVA != null) {
			montantTTC = prixTotalHT * (1 + (tauxTVA / 100));

			logger.debug("== montantTTC " + montantTTC);
		}
		/** montantTVA **/
		if (tauxTVA != null) {
			montantTVA = prixTotalHT * (tauxTVA / 100);
			logger.debug("== montantTVA " + montantTVA);
		}

		pFactureVenteValue.setPrixTotalHT(prixTotalHT);
		pFactureVenteValue.setMontantTTC(montantTTC);
		pFactureVenteValue.setMontantTVA(montantTVA);

		// Calcule des Prix
		Double totalFacture = 0D;
		Double totalColis = 0D;
//		Double totalPalette = 0D;
		Double totalPrixDivers = 0D;
		Double totalPrixProduitF = 0D;
		Double totalMP = 0D;
		/** totalColis, totalPalette **/
		for (ProduitFactureVenteValue produitFactureElement : pFactureVenteValue.getProduitFactureVente()) {
			if ((produitFactureElement.getColis() != null)) {
				totalColis = totalColis + produitFactureElement.getColis();
			}

			if (/*(produitFactureElement.getColis() != null) && */(produitFactureElement.getQuantite() != null)) {
				totalPrixProduitF = totalPrixProduitF
						+ (produitFactureElement.getPrix() * produitFactureElement.getQuantite());
			}
			
			ProduitValue produit=produitPersistance.rechercheProduitById(produitFactureElement.getProduitId());
			if(produit.getPrixMajore()!=null)
			{produitFactureElement.setPrixTMP(produit.getPrixMajore()*produitFactureElement.getQuantite());
			totalMP+=produit.getPrixMajore();
			}
			
		}
		logger.debug("== totalPrixProduitF" + totalPrixProduitF);

		/** totalPrixDivers **/
		if(pFactureVenteValue.getDiversFactureValue()!=null)
		for (DiversFactureValue diversFactureElement : pFactureVenteValue.getDiversFactureValue()) {
			if ((diversFactureElement.getPrix() != null)) {
				totalPrixDivers = totalPrixDivers
						+ (diversFactureElement.getPrix() * diversFactureElement.getQuantite());
			}
		}
		logger.debug("== totalPrixDivers" + totalPrixDivers);

		/** totalFacture **/
		totalFacture = totalPrixDivers + totalPrixProduitF;

		logger.debug("== totalFacture " + totalFacture);

		pFactureVenteValue.setTotalFacture(totalFacture);
		pFactureVenteValue.setTotalColis(totalColis);
//		pFactureVenteValue.setTotalPalette(totalPalette);
        pFactureVenteValue.setValeurMatierePremiere(totalMP);
        
        //TODO Valeur Ajoutée Article voir avec Wided 
		logger.debug("== pFactureVenteValue ** ** ** " + pFactureVenteValue);

	}

	@Override
	public void supprimerFactureVenteValue(Long pId) {
		factureVentePersistance.supprimerFactureVenteValue(pId);
	}

	@Override
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue) {

		enrichirFactureVente(pFactureVenteValue);
		return factureVentePersistance.modifierFactureVenteValue(pFactureVenteValue);
	}

	@Override
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId) {
		return factureVentePersistance.rechercheFactureVenteValueParId(pId);
	}

	@Override
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue pRechercheFactureVenteValueMulitCritere) {
		return factureVentePersistance.rechercherFactureVenteMultiCritere(pRechercheFactureVenteValueMulitCritere);
	}

	@Override
	public LivraisonVenteVue getProduitFactureByReferenceBLList(List<String> refBLList) {

		LivraisonVenteVue factureVenteVue = new LivraisonVenteVue();

		List<ProduitLivraisonVue> produitFactureList = new ArrayList<ProduitLivraisonVue>();

		List<LivraisonVenteValue> livraisonVenteList = livraisonVentePersistance.getAllByReference(refBLList);

		if (livraisonVenteList != null) {
			/** refBL */
			factureVenteVue.setRefLivraison(livraisonVenteList.get(0).getReference());
			/** agentBL */
			factureVenteVue.setAgentBLId(livraisonVenteList.get(0).getAgentId());
			/** produitBL */
			for (LivraisonVenteValue livraisonVente : livraisonVenteList) {

				for (ProduitLivraisonValue produitLivraison : livraisonVente.getProduitLivraison()) {

					ProduitLivraisonVue produitFacture = new ProduitLivraisonVue();
					produitFacture.setQuantite(produitLivraison.getQuantite());
					produitFacture.setPrix(produitLivraison.getPrix());
					produitFacture.setProduitId(produitLivraison.getProduitId());
					produitFacture.setDevise(produitLivraison.getDevise());
					produitFacture.setReferenceCommande(produitLivraison.getReferenceCommande());
					
					produitFactureList.add(produitFacture);
				}

				factureVenteVue.setProduitLivraison(produitFactureList);
			}

		}

		return factureVenteVue;
	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}
}
