package com.gpro.consulting.tiers.gc.domaine.livraisonVente.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.DetailProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.livraisonVente.ILivraisonVenteDomaine;
import com.gpro.consulting.tiers.gc.persitance.ICommandeVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.impl.CommandeVentePersistanceImpl;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.ILivraisonVentePersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class LivraisonVenteDomaineImp implements ILivraisonVenteDomaine{

	private static final Logger logger = LoggerFactory.getLogger(LivraisonVenteDomaineImp.class);

	@Autowired
	private ILivraisonVentePersistance livraisonVentePersistance;
	
	@Autowired
	private ICommandeVentePersistance commandeVentePersistance;

	@Override
	public String creerLivraisonVente(LivraisonVenteValue pLivraisonVenteValue) {
		return livraisonVentePersistance.creerLivraisonVente(pLivraisonVenteValue);
	}

	@Override
	public void supprimerLivraisonVenteValue(Long pId) {
		livraisonVentePersistance.supprimerLivraisonVenteValue(pId);
		
	}

	@Override
	public String modifierLivraisonVenteValue(
			LivraisonVenteValue pLivraisonVenteValue) {
		return livraisonVentePersistance.modifierLivraisonVenteValue(pLivraisonVenteValue);
	}

	@Override
	public LivraisonVenteValue rechercheLivraisonVenteValueParId(Long pId) {
		return livraisonVentePersistance.rechercheLivraisonVenteValueParId(pId);
	}

	@Override
	public ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			RechercheMulticritereLivraisonVenteValue pRechercheLivraisonVenteValueMulitCritere) {
		return livraisonVentePersistance.rechercherLivraisonVenteMultiCritere(pRechercheLivraisonVenteValueMulitCritere);
	}

	@Override
	public List<ProduitLivraisonValue> getProduitLivraisonListByRefBonCmdList(List<String> refBonCmdList) {
		
		List<ProduitLivraisonValue> produitLivraisonList = new ArrayList<ProduitLivraisonValue>();
		
		List<CommandeVenteValue> commandeVenteList = commandeVentePersistance.getAllByReference(refBonCmdList);
		
		if(commandeVenteList != null){
			
			for(CommandeVenteValue commandeVente : commandeVenteList){
				logger.info("-----------------commandeVente.ref ** ** **"+commandeVente.getReference());

				for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
					
					ProduitLivraisonValue produitLivraison = new ProduitLivraisonValue();
					
					produitLivraison.setQuantite(produitCommande.getQuantite());
					produitLivraison.setPrix(produitCommande.getPrix());
					produitLivraison.setProduitId(produitCommande.getProduitId());
					produitLivraison.setDevise(produitCommande.getDevise());
					produitLivraison.setDateLivraison(produitCommande.getDateLivraison());
					produitLivraison.setReferenceCommande(commandeVente.getReference());
					
					List<DetailProduitLivraisonValue> detailsList = new ArrayList<DetailProduitLivraisonValue>();
					
					for(DetailsProduitCommandeValue detailProduitCommande : produitCommande.getListDetailsProduitCommande()){
						
						DetailProduitLivraisonValue detailProduitLivraison = new DetailProduitLivraisonValue();
						
						detailProduitLivraison.setQuantite(detailProduitCommande.getQuantite());
						detailProduitLivraison.setTailleId(detailProduitCommande.getTailleId());
						detailProduitLivraison.setCouleurId(detailProduitCommande.getCouleurId());
						
						detailsList.add(detailProduitLivraison);
					}
					
					produitLivraison.setListDetailsProduitLivraison(detailsList);
					
					produitLivraisonList.add(produitLivraison);
					
				}
			}
			
		}
		return produitLivraisonList;
	}
	
	@Override
	public List<String> getReferences() {
		
		List<String> listReferences = new ArrayList<String>();
		
		List<LivraisonVenteValue> livraisonVenteList =  livraisonVentePersistance.getAll();
		
		for(LivraisonVenteValue livraisonVente : livraisonVenteList){
			
			listReferences.add(livraisonVente.getReference());
		}
		
		return listReferences;
	}

}
