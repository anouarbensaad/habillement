package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeVenteDomaine;
import com.gpro.consulting.tiers.gc.persitance.ICommandeVentePersistance;

@Component
public class CommandeVenteDomaineImpl implements ICommandeVenteDomaine {
	
	private static final Logger logger = LoggerFactory.getLogger(CommandeVenteDomaineImpl.class);

	private static final Double ZERO_D = 0D;
	private static final Long ZERO_L = 0L;
	
	@Autowired
	ICommandeVentePersistance commandeVentePersistance;
	
	@Autowired
	ITaillePersistance taillePersistance;

	@Override
	public String creerCommandeVente(CommandeVenteValue dto) {
		
		Long quantiteCommandeVenteTotal = ZERO_L;
		Double coutCommandeVenteTotal = ZERO_D;
		
		for(ProduitCommandeValue produitCommande : dto.getProduitCommandes()) {
			
			if(produitCommande.getListDetailsProduitCommande() != null && dto.getFromOf()==false){
				
				Long quantiteProduitCommandeTotal = ZERO_L;
				
				for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()) {
					
					if(detailsProduitCommande.getQuantite() != null) {
						
						quantiteProduitCommandeTotal = quantiteProduitCommandeTotal + detailsProduitCommande.getQuantite();
					}
					
				}
				
				produitCommande.setQuantite(quantiteProduitCommandeTotal);
			}
			
			produitCommande.setDateLivraison(dto.getDateLivraison());
			
			quantiteCommandeVenteTotal = quantiteCommandeVenteTotal + produitCommande.getQuantite();
			
			//System.out.println("--- produitCommande.getQuantite()--"+ produitCommande.getQuantite());
			
			if(produitCommande.getPrix() != null){
				coutCommandeVenteTotal = coutCommandeVenteTotal + (produitCommande.getPrix() * produitCommande.getQuantite());
			}
		}
		
		dto.setQuantite(quantiteCommandeVenteTotal);
		dto.setPrixTotal(coutCommandeVenteTotal);
		
		return commandeVentePersistance.creerCommandeVente(dto);
	}


	@Override
	public void supprimerCommandeVente(Long pId) {
		commandeVentePersistance.supprimerCommandeVente(pId);
	}


	@Override
	public String modifierCommandeVente(CommandeVenteValue dto) {
		
		Long quantiteCommandeVenteTotal = ZERO_L;
		Double coutCommandeVenteTotal = ZERO_D;
		
		for(ProduitCommandeValue produitCommande : dto.getProduitCommandes()) {
			
			if(produitCommande.getListDetailsProduitCommande() != null  && dto.getFromOf()==false){
				
				Long quantiteProduitCommandeTotal = ZERO_L;
				
				for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()) {
					
					if(detailsProduitCommande.getQuantite() != null) {
						
						quantiteProduitCommandeTotal = quantiteProduitCommandeTotal + detailsProduitCommande.getQuantite();
					}
					
				}
				
				produitCommande.setQuantite(quantiteProduitCommandeTotal);
			}
			
			produitCommande.setDateLivraison(dto.getDateLivraison());
				
			quantiteCommandeVenteTotal = quantiteCommandeVenteTotal + produitCommande.getQuantite();
			
			if(produitCommande.getPrix() != null){
				coutCommandeVenteTotal = coutCommandeVenteTotal + (produitCommande.getPrix() * produitCommande.getQuantite());
			}
			
		}
		
		dto.setQuantite(quantiteCommandeVenteTotal);
		dto.setPrixTotal(coutCommandeVenteTotal);
		
		return commandeVentePersistance.modifierCommandeVente(dto);
	}

	@Override
	public CommandeVenteValue rechercheCommandeVenteParId(Long id) {
		
		CommandeVenteValue commandeVente = commandeVentePersistance.rechercheCommandeVenteParId(id);
		
		if(commandeVente != null){
			for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
				for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
					
					if(detailsProduitCommande.getTailleId() != null){
						TailleValue taille = taillePersistance.getById(detailsProduitCommande.getTailleId());
						
						if(taille != null){
							if(taille.getOrdre() != null){
								detailsProduitCommande.setOrder( taille.getOrdre().toString());
							}
						}
					}
				}
			}
			
			for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
				List <DetailsProduitCommandeValue> listDetails = new ArrayList < DetailsProduitCommandeValue >();
				listDetails.addAll(produitCommande.getListDetailsProduitCommande());
				Collections.sort(listDetails);
				produitCommande.setListDetailsProduitCommande(new ArrayList<>(listDetails));
			}
		}
	    
		return commandeVente;
	}


	@Override
	public List<CommandeVenteValue> listeCommandeVente() {
		
		List<CommandeVenteValue> listeCommandeVente = commandeVentePersistance.listeCommandeVente();
		
		for(CommandeVenteValue commandeVente : listeCommandeVente){
			
			for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
				for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
					
					if(detailsProduitCommande.getTailleId() != null){
						TailleValue taille = taillePersistance.getById(detailsProduitCommande.getTailleId());
						
						if(taille != null){
							if(taille.getOrdre() != null){
								detailsProduitCommande.setOrder( taille.getOrdre().toString());
							}
						}
					}
				}
			}
			
			for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
				List <DetailsProduitCommandeValue> listDetails = new ArrayList < DetailsProduitCommandeValue >();
				listDetails.addAll(produitCommande.getListDetailsProduitCommande());
				Collections.sort(listDetails);
				produitCommande.setListDetailsProduitCommande(new ArrayList<>(listDetails));
			}
		}
		
		return listeCommandeVente;
	}

	
	@Override
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(RechercheMulticritereCommandeVenteValue request) {
		
		ResultatRechecheCommandeVenteValue result = commandeVentePersistance.rechercherCommandeVenteMultiCritere(request);
		
		if(result != null){
			
			for(CommandeVenteValue commandeVente : result.getCommandeVenteValues()){
				
				for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
					
					for(DetailsProduitCommandeValue detailsProduitCommande : produitCommande.getListDetailsProduitCommande()){
						
						if(detailsProduitCommande.getTailleId() != null){
							TailleValue taille = taillePersistance.getById(detailsProduitCommande.getTailleId());
							
							if(taille != null){
								if(taille.getOrdre() != null){
									detailsProduitCommande.setOrder( taille.getOrdre().toString());
								}
							}
						}
					}
				}
				
				for(ProduitCommandeValue produitCommande : commandeVente.getProduitCommandes()){
					
					List <DetailsProduitCommandeValue> listDetails = new ArrayList < DetailsProduitCommandeValue >();
					listDetails.addAll(produitCommande.getListDetailsProduitCommande());
					Collections.sort(listDetails);
					produitCommande.setListDetailsProduitCommande(new ArrayList<>(listDetails));
				}
			}
		}
		
		return result;
	}


	@Override
	public List<String> getReferences() {

		List<String> listReferences = new ArrayList<String>();
		
		List<CommandeVenteValue> commandeVenteList =  commandeVentePersistance.getAll();
		
		for(CommandeVenteValue commandeVente : commandeVenteList){
			
			listReferences.add(commandeVente.getReference());
		}
		
		return listReferences;
	}
   
	@Override
    public boolean existeOF(String numeroOF) {
		return commandeVentePersistance.existeOF(numeroOF);
	}
	
	
}
