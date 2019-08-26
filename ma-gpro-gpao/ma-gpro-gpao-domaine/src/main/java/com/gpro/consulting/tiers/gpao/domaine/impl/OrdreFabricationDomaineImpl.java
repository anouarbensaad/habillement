package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.persitance.ICommandeVentePersistance;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;
import com.gpro.consulting.tiers.gpao.domaine.IOrdreFabricationDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.ISaisieElementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IFicheEclatementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IGammeOfPersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class OrdreFabricationDomaineImpl implements IOrdreFabricationDomaine {

	private static final Logger logger = LoggerFactory.getLogger(OrdreFabricationDomaineImpl.class);
	
	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	private ITaillePersistance taillePersistance;
	
	@Autowired
	private ICouleurPersistance couleurPersistance;
	
	@Autowired
	private ISaisieElementPersistance saisieElementPersistance;
	
	
	@Autowired
	private ICommandeVentePersistance commandeVenteDomaine;
	
	@Autowired
	private IProduitPersistance produitPersistence;
	
	
	@Autowired
	private IGammeOfPersistance gammeOFPersistence;
	
	@Autowired
	IDetailSaisiePersistance   detailSaisiePersistence;
	
	
	@Autowired
	private IBaseInfoDomaine baseInfoDomaine;
	
	
	
	@Autowired
	
	private IFicheEclatementPersistance ficheEclatementPersistence;
	
	
	
	
	private static final Long ZEROL = 0L;
	private static final Double ZEROD = 0D;

	@Override
	public String creerOrdreFabrication(OrdreFabricationValue ordreFabrication) {
		
		Long sommeQuantite = ZEROL;
		
		if (ordreFabrication.getDetailsOF() != null) {
			for (DetailOfValue detailOf : ordreFabrication.getDetailsOF()) {
				
				sommeQuantite = sommeQuantite + detailOf.getQuantite();
			}
		}
		
		ordreFabrication.setQuantite(sommeQuantite);
		
		if(ordreFabrication.getDateIntroduction() == null)
			ordreFabrication.setDateIntroduction(Calendar.getInstance());
		
		return ordreFabricationPersistance.creerOrdreFabrication(ordreFabrication);
	}

	@Override
	public void supprimerOrdreFabrication(Long pId) {
		
		ordreFabricationPersistance.supprimerOrdreFabrication(pId);
	}

	@Override
	public String modifierOrdreFabrication(OrdreFabricationValue ordreFabrication) {

		Long sommeQuantite = ZEROL;
		
		if (ordreFabrication.getDetailsOF() != null) {
			for (DetailOfValue detailOf : ordreFabrication.getDetailsOF()) {
				
				sommeQuantite = sommeQuantite + detailOf.getQuantite();
			}
		}
		
		ordreFabrication.setQuantite(sommeQuantite);
		
		return ordreFabricationPersistance
				.modifierOrdreFabrication(ordreFabrication);
	}

	@Override
	public OrdreFabricationValue rechercheOrdreFabricationParId(
			Long pOrdreFabricationId) {
		return ordreFabricationPersistance
				.rechercheOrdreFabricationParId(pOrdreFabricationId);
	}
	
	@Override
	public List<QuantiteVue> rechercheQuantiteParIdProduit(Long pIdProduit) {
		return ordreFabricationPersistance.rechercheQuantiteParIdProduit(pIdProduit);
	}
	
	@Override
	public List<PhaseProduitVue> recherchePhaseParIdProduit(Long pIdProduit) {
		return ordreFabricationPersistance
				.recherchePhaseParIdProduit(pIdProduit);
	}
	
	@Override
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
			RechercheMulticritereOrdreFabricationValue pRechercheOrdreFaricationMulitCritere) {
		return ordreFabricationPersistance
				.rechercherOrdreFabricationMultiCritere(pRechercheOrdreFaricationMulitCritere);
	}
	
	
	@Override
	public List<OrdreFabricationValue> listeOrdreFabrication() {
		return ordreFabricationPersistance.listeOrdreFabrication();
	}

	@Override
	public List<CouleurValue> listeCouleurOf(Long ordreFabricationId) {
		
		List<CouleurValue> result = null;
		
		OrdreFabricationValue ordreFabrication = ordreFabricationPersistance.rechercheOrdreFabricationParId(ordreFabricationId);
		
		if(ordreFabrication != null){
			
			List<Long> listIdUsed = new ArrayList<Long>();
			
			for(DetailOfValue detailOf : ordreFabrication.getDetailsOF()){
				
				if(detailOf.getCouleurId() != null){
					
					listIdUsed.add(detailOf.getCouleurId());
				}
				
			}
			
			result = couleurPersistance.getAllInList(listIdUsed);
			
		}
		
		return result;
	}

	@Override
	public List<TailleValue> listeTailleOf(Long ordreFabricationId) {
		
		List<TailleValue> result = null;
		
		OrdreFabricationValue ordreFabrication = ordreFabricationPersistance.rechercheOrdreFabricationParId(ordreFabricationId);
		
		if(ordreFabrication != null){
			
			List<Long> listIdUsed = new ArrayList<Long>();
			
			for(DetailOfValue detailOf : ordreFabrication.getDetailsOF()){
				
				if(detailOf.getTailleId() != null){
					
					listIdUsed.add(detailOf.getTailleId());
				}
					
			}
			
			result = taillePersistance.getAllInList(listIdUsed);
			
		}
		
		return result;
	}

	@Override
	public OrdreFabricationValue getNumeroOF(Long id) {
		return ordreFabricationPersistance.getNumeroOF(id);
	}
	
	@Override
	public void changerEtatOF(){
		RechercheMulticritereOrdreFabricationValue request=new RechercheMulticritereOrdreFabricationValue();
		request.setStatut(2L);
		ResultatMulticritereOrdreFabricationValue result=ordreFabricationPersistance.rechercherOrdreFabricationMultiCritereGlobale(request);
		 if(result!=null && result.getOrdreFabricationValues()!=null && result.getOrdreFabricationValues().size()>0){
			 for (OrdreFabricationValue ordre:result.getOrdreFabricationValues()){
				boolean existe=saisieElementPersistance.existeOF(ordre.getNumero());
				 
				 if(existe==true)
				 {
					 ordre.setEtat(3L);
					 this.modifierOrdreFabrication(ordre);
				 }
			 }
			 
			 
		 }
		
		
		
		
		
		
		
		
	}
	
	@Override
	public String exporterOFToBC(){
		
		List <OrdreFabricationValue> listOF=ordreFabricationPersistance.listerOFTOCommercial();
		 
		
		 if(listOF!=null && listOF.size()>0){
			 for (OrdreFabricationValue ordre:listOF){
				boolean existe=commandeVenteDomaine.existeOF(ordre.getNumero());
				 
				 if(existe==false)
				 {
					 CommandeVenteValue commandeValue =new CommandeVenteValue();
					 commandeValue.setReference(ordre.getNumero());
					 commandeValue.setPartieIntersseId(ordre.getPartieInterresId());
					 commandeValue.setQuantite(ordre.getQuantite());
					 commandeValue.setFromOf(true);
					 commandeValue.setDateIntroduction(ordre.getDateIntroduction());
					 //commandeValue.setDate;
					 commandeValue.setDateLivraison(ordre.getDateLivraison());
					 commandeValue.setOf_reference(ordre.getNumero());
					 Set<ProduitCommandeValue> listProduitCommande=new HashSet<ProduitCommandeValue>();
					 ProduitCommandeValue produitCommande=new ProduitCommandeValue();
					 ProduitValue produit=produitPersistence.rechercheProduitById(ordre.getProduitId());
					 produitCommande.setProduitId(ordre.getProduitId());
					 produitCommande.setQuantite(ordre.getQuantite());
					 produitCommande.setPrix(produit.getPrixUnitaire());
					 List<DetailsProduitCommandeValue> listDet=new ArrayList<DetailsProduitCommandeValue>();
					 //TODO enrichir par les details
					 if(ordre.getDetailsOF()!=null){
						 for (DetailOfValue det:ordre.getDetailsOF()){
							 DetailsProduitCommandeValue detProduitValue=new DetailsProduitCommandeValue();
							  detProduitValue.setCouleurId(det.getCouleurId());
							  detProduitValue.setTailleId(det.getTailleId());
							  detProduitValue.setQuantite(det.getQuantite());
							  listDet.add(detProduitValue);
							  //detProduitValue.s
						 }
						 produitCommande.setListDetailsProduitCommande(listDet); 
						 
					 }
					 
					 
					 
					 listProduitCommande.add(produitCommande);
					 commandeValue.setProduitCommandes(listProduitCommande);
					 commandeVenteDomaine.creerCommandeVente(commandeValue);
					 
				 }
			 }
			 
			 
		 }
		
		
		
		
		
		
		
		return "exported";
	}
	
	public OrdreFabricationValue getByNumero(String numero) {
		
		return ordreFabricationPersistance.getByNumero(numero);
		
		
		
	}
	
	
	@Override
   public OrdreFabricationValue getByNumeroAvailableForGamme(String numero) {
		
		OrdreFabricationValue ordreValue= ordreFabricationPersistance.getByNumero(numero);
		
		if (ordreValue!=null) {
		GammeOfValue gamme=gammeOFPersistence.getByOFId(ordreValue.getId());
		
		if (gamme!=null)
		
		   return null;
		}
	    return ordreValue;	
	}
	
	
	
	
	@Override
	   public OrdreFabricationValue getByNumeroAvailableForEclatement(String numero) {
			
			OrdreFabricationValue ordreValue= ordreFabricationPersistance.getByNumero(numero);
			
			if (ordreValue!=null) {
			List <FicheEclatementValue> gamme=ficheEclatementPersistence.getByOrdreFabricationId(ordreValue.getId());
			
			if (gamme!=null && gamme.size()>0)
			
			   return null;
			}
		    return ordreValue;	
		}
	
	
	
	
	@Override
	public void updateSuivi(RechercheMulticritereDetailSaisieValue critere){
		
		  
		List <Long> listIdOF=detailSaisiePersistence.rechercherMultiCritereOF(critere);
		
		BaseInfoValue baseInfo=baseInfoDomaine.getClientActif();
		
    		Long eclatement=baseInfo.getEclatement(); 
		    Long engagement=baseInfo.getEngagement();
		    Long sortieCpe=baseInfo.getSortieCoupe();
		    Long sortieChaine=baseInfo.getSortieChaine();
		    Long suppl1=baseInfo.getSupp1();
		    Long supp2=baseInfo.getSupp2();
		    Long conditionnement=baseInfo.getConditionnement();
		
		    
		  //  System.out.println("##### SORTIE ChAIne :  "+sortieChaine);
		  //  System.out.println("###### LIST OF SIZE :   "+listIdOF.size());
		
		if (listIdOF!=null && listIdOF.size()>0){
			
			
			for (Long idOF:listIdOF)
			{
			    if (idOF!=null){
				OrdreFabricationValue ordreFabrication =ordreFabricationPersistance.rechercheOrdreFabricationParId(idOF);
			   
			   
				if(ordreFabrication!=null){
				Long ecl=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, eclatement) ;
			    Long eng=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, engagement) ;
			    Long sortCpe=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, sortieCpe) ;
			    Long sortChaine=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, sortieChaine) ;
			    Long supp1=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, suppl1) ;
			    Long suppl2=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, supp2) ;
			    Long cond=ordreFabricationPersistance.CalculeQteProduiteCumul(idOF, conditionnement) ;
//			   
			    
			    
//			    System.out.println("##### ENG :   "+eng);
			//    System.out.println("### SCH :   "+sortChaine);
			    
			    ordreFabrication.setQtCoupe(sortCpe);
			    ordreFabrication.setQtEngagement(eng);
			    ordreFabrication.setQtSortie(sortChaine);
			    ordreFabrication.setQtFinition(cond);
			    ordreFabrication.setQtSupp1(supp1);
			    ordreFabrication.setQtSupp1(suppl2);
//			    
			    
			    
			    
			
			    this.modifierOrdreFabrication(ordreFabrication);
				}
			    
			    }
			    
			}
			
		}
		
		
		
		
	}
	
	
	
	
}
