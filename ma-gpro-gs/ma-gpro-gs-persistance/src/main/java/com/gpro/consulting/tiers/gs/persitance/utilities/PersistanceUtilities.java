package com.gpro.consulting.tiers.gs.persitance.utilities;

import java.util.HashSet;
import java.util.Set;

import org.omg.CORBA.OMGVMCID;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SiteEntite;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.DocumentBonMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.gs.persitance.entite.BLAchatEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.DocumentBonMouvementEntity;
import com.gpro.consulting.tiers.gs.persitance.entite.EmplacementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MagasinEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.RaisonMouvementEntite;

/**
 * The Class PersistanceUtilities.
 * 
 * @author mohamed
 */

@Component
public class PersistanceUtilities {

	public static EntiteStockMouvementValue toEntiteStockMouvementValue(EntiteStockEntite pEntiteStockEntite) {
		EntiteStockMouvementValue vEntiteStockMouvementValue = new EntiteStockMouvementValue();
		vEntiteStockMouvementValue.setEmpl(pEntiteStockEntite.getEmplacement());
		vEntiteStockMouvementValue.setEntiteStockId(pEntiteStockEntite.getId());
		vEntiteStockMouvementValue.setFinCone(pEntiteStockEntite.getFinconeActuel());
		vEntiteStockMouvementValue.setPmp(pEntiteStockEntite.getPmp());
		vEntiteStockMouvementValue.setPoid(pEntiteStockEntite.getPoidsActuel());
		vEntiteStockMouvementValue.setPu(pEntiteStockEntite.getPrixUnitaire());
		vEntiteStockMouvementValue.setQteActuel(pEntiteStockEntite.getQteActuelle());
		vEntiteStockMouvementValue.setDateEntree(pEntiteStockEntite.getDateEntree());
		vEntiteStockMouvementValue.setOA(pEntiteStockEntite.getOa());
		vEntiteStockMouvementValue.setCone(pEntiteStockEntite.getConesActuel());
		vEntiteStockMouvementValue.setReferenceLot(pEntiteStockEntite.getReferenceLot());
		vEntiteStockMouvementValue.setCodeFournisseur(pEntiteStockEntite.getArticle().getCodeFournisseur());
		vEntiteStockMouvementValue.setRouleauxActuels(pEntiteStockEntite.getRouleauxActuel());
			
		if (pEntiteStockEntite.getArticle() != null) {
			vEntiteStockMouvementValue.setDesignation(pEntiteStockEntite.getArticle().getDesignation());
			vEntiteStockMouvementValue.setReference(pEntiteStockEntite.getArticle().getRef());

			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite() != null) {
				vEntiteStockMouvementValue
						.setSousFamille(pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getDesignation());// sous
																													// famille
																													// article
			}
			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getFamilleArticle() != null) {
				vEntiteStockMouvementValue.setFamille(pEntiteStockEntite.getArticle().getSousFamilleArtEntite()
						.getFamilleArticle().getDesignation().toString());// famille
																			// article
			}
			if (pEntiteStockEntite.getArticle().getUniteEntite() != null) {
				vEntiteStockMouvementValue.setUnite(pEntiteStockEntite.getArticle().getUniteEntite().toString());// unitearticle
			}
		}
		// vEntiteStockMouvementValue.setQteOf(pEntiteStockEntite.getQteActuelle());
		// qte OF
		// vEntiteStockMouvementValue.setQteR(qteR); qte R
		// etat
		return vEntiteStockMouvementValue;
	}

	public static EntiteStockValue toValue(EntiteStockEntite pEntiteStockEntite) {
		EntiteStockValue entiteStock = new EntiteStockValue();
		entiteStock.setId(pEntiteStockEntite.getId());

		if (pEntiteStockEntite.getArticle() != null) {
			entiteStock.setArticle(pEntiteStockEntite.getArticle().getId());
			entiteStock.setReferenceArticle(pEntiteStockEntite.getArticle().getRef());
			entiteStock.setLibelleArticle(pEntiteStockEntite.getArticle().getDesignation());
			// Hajer Amri 16/03/2017
			entiteStock.setCodeFournisseur(pEntiteStockEntite.getArticle().getCodeFournisseur());
			entiteStock.setLot(pEntiteStockEntite.getReferenceLot());
			if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite() != null) {
				if (pEntiteStockEntite.getArticle().getSousFamilleArtEntite().getFamilleArticle() != null) {
					entiteStock.setFamilleArticle(pEntiteStockEntite.getArticle().getSousFamilleArtEntite()
							.getFamilleArticle().getDesignation());
				}
			}

		}
		entiteStock.setConeReserve(pEntiteStockEntite.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockEntite.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockEntite.getDateEntree());
		entiteStock.setDateLot(pEntiteStockEntite.getDateLot());
		entiteStock.setEmplacement(pEntiteStockEntite.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockEntite.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockEntite.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockEntite.getFinconeReserve());
		if (pEntiteStockEntite.getMagasin() != null) {
			entiteStock.setMagasin(pEntiteStockEntite.getMagasin().getId());
			entiteStock.setDesignationMagasin(pEntiteStockEntite.getMagasin().getDesignation());
		}

		entiteStock.setPmp(pEntiteStockEntite.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockEntite.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockEntite.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockEntite.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockEntite.getQteActuelle());
		entiteStock.setQteReservee(pEntiteStockEntite.getQteResrvee());

		entiteStock.setReferenceContenaire(pEntiteStockEntite.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockEntite.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockEntite.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockEntite.getRouleauxReserve());
		entiteStock.setOa(pEntiteStockEntite.getOa());
		return entiteStock;
	}

	public static EntiteStockEntite toEntity(EntiteStockValue pEntiteStockValue, MagasinEntite pMagasinEntite,
			ArticleEntite pArticleEntite) {
		EntiteStockEntite entiteStock = new EntiteStockEntite();
		if (pEntiteStockValue.getId() != null) {
			entiteStock.setId(pEntiteStockValue.getId());
		}
		entiteStock.setArticle(pArticleEntite);
		entiteStock.setConeReserve(pEntiteStockValue.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockValue.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockValue.getDateEntree());
		entiteStock.setDateLot(pEntiteStockValue.getDateLot());
		entiteStock.setEmplacement(pEntiteStockValue.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockValue.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockValue.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockValue.getFinconeReserve());
		entiteStock.setMagasin(pMagasinEntite);
		entiteStock.setLibelleArticle(pEntiteStockValue.getLibelleArticle());
		entiteStock.setPmp(pEntiteStockValue.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockValue.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockValue.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockValue.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockValue.getQteActuelle());
		entiteStock.setQteResrvee(pEntiteStockValue.getQteReservee());
		entiteStock.setReferenceArticle(pEntiteStockValue.getReferenceArticle());
		entiteStock.setReferenceContenaire(pEntiteStockValue.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockValue.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockValue.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockValue.getRouleauxReserve());
		entiteStock.setOa(pEntiteStockValue.getOa());
		return entiteStock;
	}

	/**
	 * To value.
	 *
	 * @param pMouvementEntite
	 *            the mouvement entite
	 * @return
	 * @return
	 * @return the mouvement stock value
	 */
	public static MouvementStockValue toValue(MouvementEntite pMouvementEntite) {
		MouvementStockValue mouvementStockValue = new MouvementStockValue();
		mouvementStockValue.setId(pMouvementEntite.getId());
		mouvementStockValue.setType(pMouvementEntite.getType());
		mouvementStockValue.setCones(pMouvementEntite.getCones());
		mouvementStockValue.setConesReel(pMouvementEntite.getConesReel());
		mouvementStockValue.setDetailsMouvement(pMouvementEntite.getDetailsMouvement());
		mouvementStockValue.setEmplacement(pMouvementEntite.getEmplacement());
		// added on 13/04/2016, by Ameni Berrich
		mouvementStockValue.setLot(pMouvementEntite.getLot());
		mouvementStockValue.setObservation(pMouvementEntite.getObservation());

		if (pMouvementEntite.getEntiteStock() != null) {
			mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());
			mouvementStockValue.setEntiteStockValue(toValue(pMouvementEntite.getEntiteStock()));

			if (pMouvementEntite.getEntiteStock().getArticle() != null) {
				mouvementStockValue.setIdArticle(pMouvementEntite.getEntiteStock().getArticle().getId());
				mouvementStockValue.setReferenceArticle(pMouvementEntite.getEntiteStock().getArticle().getRef());
				// Hajer on 15/03/2017
				mouvementStockValue
						.setCodeFournisseur(pMouvementEntite.getEntiteStock().getArticle().getCodeFournisseur());
				mouvementStockValue
						.setDesignationArticle(pMouvementEntite.getEntiteStock().getArticle().getDesignation());
				mouvementStockValue.setCouleur(pMouvementEntite.getEntiteStock().getArticle().getCouleur());

				if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite() != null) {

					mouvementStockValue.setSousFamilleArticle(
							pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getDesignation());

					if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite()
							.getFamilleArticle() != null) {

						mouvementStockValue.setFamilleArticle(pMouvementEntite.getEntiteStock().getArticle()
								.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
						if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getFamilleArticle()
								.getTypeArticle() != null) {

							mouvementStockValue.setTypeArticle(pMouvementEntite.getEntiteStock().getArticle()
									.getSousFamilleArtEntite().getFamilleArticle().getTypeArticle().getId());
						}
					}
				}
			}

			if (pMouvementEntite.getEntiteStock().getMagasin() != null) {
				mouvementStockValue.setIdMagasin(pMouvementEntite.getEntiteStock().getMagasin().getId());
			}
		}

		mouvementStockValue.setFincones(pMouvementEntite.getFinCones());
		mouvementStockValue.setNbRouleaux(pMouvementEntite.getNbRouleaux());
		mouvementStockValue.setNbRouleauxReel(pMouvementEntite.getNbRouleauxReel());
		mouvementStockValue.setPoids(pMouvementEntite.getPoids());
		mouvementStockValue.setPrixUnitaire(pMouvementEntite.getPrixUnitaire());
		mouvementStockValue.setQuantite(pMouvementEntite.getQuantite());
		mouvementStockValue.setQuantiteReelle(pMouvementEntite.getQuantiteReelle());
		mouvementStockValue.setFinconesReel(pMouvementEntite.getFinConesReel());
		mouvementStockValue.setDescription(pMouvementEntite.getDescription());
		mouvementStockValue.setPoidsReel(pMouvementEntite.getPoidsReel());
		mouvementStockValue.setNouveau(false);
		mouvementStockValue.setQteOF(pMouvementEntite.getQteOF());
		mouvementStockValue.setBesoinOF(pMouvementEntite.getBesoinOF());
		mouvementStockValue.setOa(pMouvementEntite.getOa());
		mouvementStockValue.setBonMouvementId(pMouvementEntite.getBonMouvement().getId());
		mouvementStockValue.setTypeMouvement(pMouvementEntite.getTypeMouvement());
		return mouvementStockValue;
	}

	public static MouvementStockValue toValueAffichage(MouvementEntite pMouvementEntite) {
		MouvementStockValue mouvementStockValue = new MouvementStockValue();
		mouvementStockValue.setId(pMouvementEntite.getId());
		mouvementStockValue.setType(pMouvementEntite.getType());
		mouvementStockValue.setCones(pMouvementEntite.getCones());
		mouvementStockValue.setConesReel(pMouvementEntite.getConesReel());
		mouvementStockValue.setDetailsMouvement(pMouvementEntite.getDetailsMouvement());
		mouvementStockValue.setEmplacement(pMouvementEntite.getEmplacement());
		mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());
		mouvementStockValue.setFincones(pMouvementEntite.getFinCones());
		mouvementStockValue.setNbRouleaux(pMouvementEntite.getNbRouleaux());
		mouvementStockValue.setNbRouleauxReel(pMouvementEntite.getNbRouleauxReel());
		mouvementStockValue.setPoids(pMouvementEntite.getPoids());
		mouvementStockValue.setPrixUnitaire(pMouvementEntite.getPrixUnitaire());
		mouvementStockValue.setQuantite(pMouvementEntite.getQuantite());
		mouvementStockValue.setQuantiteReelle(pMouvementEntite.getQuantiteReelle());
		mouvementStockValue.setFinconesReel(pMouvementEntite.getFinConesReel());
		mouvementStockValue.setDescription(pMouvementEntite.getDescription());
		mouvementStockValue.setPoidsReel(pMouvementEntite.getPoidsReel());
		mouvementStockValue.setOa(pMouvementEntite.getOa());
		mouvementStockValue.setLot(pMouvementEntite.getLot());
		mouvementStockValue.setDateEntree(pMouvementEntite.getEntiteStock().getDateEntree());
		mouvementStockValue.setBonMouvementId(pMouvementEntite.getBonMouvement().getId());
		mouvementStockValue.setObservation(pMouvementEntite.getObservation());

		if (pMouvementEntite.getEntiteStock() != null) {
			mouvementStockValue.setEntiteStock(pMouvementEntite.getEntiteStock().getId());

			if (pMouvementEntite.getEntiteStock().getMagasin() != null) {
				mouvementStockValue.setIdMagasin(pMouvementEntite.getEntiteStock().getMagasin().getId());
				mouvementStockValue
						.setDesignationMagasin(pMouvementEntite.getEntiteStock().getMagasin().getDesignation());
			}

			if (pMouvementEntite.getEntiteStock().getArticle() != null) {
				mouvementStockValue.setReferenceArticle(pMouvementEntite.getEntiteStock().getArticle().getRef());
				mouvementStockValue
						.setDesignationArticle(pMouvementEntite.getEntiteStock().getArticle().getDesignation());
				mouvementStockValue
						.setCodeFournisseur(pMouvementEntite.getEntiteStock().getArticle().getCodeFournisseur());

				if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite() != null) {

					mouvementStockValue.setSousFamilleArticle(
							pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite().getDesignation());

					if (pMouvementEntite.getEntiteStock().getArticle().getSousFamilleArtEntite()
							.getFamilleArticle() != null) {

						mouvementStockValue.setFamilleArticle(pMouvementEntite.getEntiteStock().getArticle()
								.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
					}
				}
			}
		}

		if (pMouvementEntite.getBonMouvement() != null) {
			BonMouvementStockValue vBm = toValueAffichage(pMouvementEntite.getBonMouvement());
			mouvementStockValue.setBonMouvement(vBm);
		}

		return mouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pMouvementStockValue
	 *            the mouvement stock value
	 * @return the mouvement entite
	 */
	public static MouvementEntite toEntity(MouvementStockValue pMouvementStockValue,
			EntiteStockEntite pEntiteStockEntite) {
		MouvementEntite mouvementEntite = new MouvementEntite();
		if (pMouvementStockValue.getId() != null) {
			mouvementEntite.setId(pMouvementStockValue.getId());
		}
		mouvementEntite.setType(pMouvementStockValue.getType());
		mouvementEntite.setCones(pMouvementStockValue.getCones());
		mouvementEntite.setConesReel(pMouvementStockValue.getConesReel());
		mouvementEntite.setDetailsMouvement(pMouvementStockValue.getDetailsMouvement());
		mouvementEntite.setEmplacement(pMouvementStockValue.getEmplacement());
		mouvementEntite.setEntiteStock(pEntiteStockEntite);
		mouvementEntite.setFinCones(pMouvementStockValue.getFincones());
		mouvementEntite.setNbRouleaux(pMouvementStockValue.getNbRouleaux());
		mouvementEntite.setNbRouleauxReel(pMouvementStockValue.getNbRouleauxReel());
		mouvementEntite.setPoids(pMouvementStockValue.getPoids());
		mouvementEntite.setPrixUnitaire(pMouvementStockValue.getPrixUnitaire());
		mouvementEntite.setQuantite(pMouvementStockValue.getQuantite());
		mouvementEntite.setQuantiteReelle(pMouvementStockValue.getQuantiteReelle());
		mouvementEntite.setDescription(pMouvementStockValue.getDescription());
		mouvementEntite.setPoidsReel(pMouvementStockValue.getPoidsReel());
		mouvementEntite.setFinConesReel(pMouvementStockValue.getFinconesReel());
		mouvementEntite.setBesoinOF(pMouvementStockValue.getBesoinOF());
		mouvementEntite.setQteOF(pMouvementStockValue.getQteOF());
		mouvementEntite.setOa(pMouvementStockValue.getOa());
		mouvementEntite.setObservation(pMouvementStockValue.getObservation());
		return mouvementEntite;

	}

	/**
	 * To value.
	 *
	 * @param pBonMouvementEntite
	 *            the bon mouvement entite
	 * @return the bon mouvement stock value
	 */
	public static BonMouvementStockValue toValue(BonMouvementEntite pBonMouvementEntite) {
		BonMouvementStockValue bonMouvementStockValue = new BonMouvementStockValue();
		bonMouvementStockValue.setId(pBonMouvementEntite.getId());
		bonMouvementStockValue.setDate(pBonMouvementEntite.getDate());
		bonMouvementStockValue.setDescription(pBonMouvementEntite.getDescription());

		if (pBonMouvementEntite.getBlachat() != null)
			bonMouvementStockValue.setBlachatId(pBonMouvementEntite.getBlachat().getId());

		if (pBonMouvementEntite.getMouvements() != null) {
			Set<MouvementStockValue> vListMouvementValue = new HashSet<MouvementStockValue>();

			for (MouvementEntite vMouvementEntite : pBonMouvementEntite.getMouvements()) {
				MouvementStockValue vMs = toValue(vMouvementEntite);
				vListMouvementValue.add(vMs);
			}

			bonMouvementStockValue.setMouvements(vListMouvementValue);
		}
		bonMouvementStockValue.setNumero(pBonMouvementEntite.getNumero());
		bonMouvementStockValue.setRaisonMouvementId(pBonMouvementEntite.getRaisonMouvementId());
		bonMouvementStockValue.setResponsable(pBonMouvementEntite.getResponsable());
		bonMouvementStockValue.setType(pBonMouvementEntite.getType());
		bonMouvementStockValue.setValeur(pBonMouvementEntite.getValeur());
		bonMouvementStockValue.setEtat(pBonMouvementEntite.getEtat());
		bonMouvementStockValue.setPartieintId(pBonMouvementEntite.getPartieInteresseeId());
		bonMouvementStockValue.setOfId(pBonMouvementEntite.getOfId());
		bonMouvementStockValue.setNumBRSortie(pBonMouvementEntite.getNumBRSortie());
		bonMouvementStockValue.setMethode(pBonMouvementEntite.getMethode());
		bonMouvementStockValue.setDaeFacture(pBonMouvementEntite.getDaeFacture());
		bonMouvementStockValue.setFournisseurId(pBonMouvementEntite.getFournisseurId());
		
		 if (pBonMouvementEntite.getDocuments() != null) {
		        Set < DocumentBonMouvementValue > vListeDocuments = new HashSet < DocumentBonMouvementValue >();
		        for (DocumentBonMouvementEntity vDocumentEntity : pBonMouvementEntite.getDocuments()) {
		        	DocumentBonMouvementValue vDe = toValue(vDocumentEntity);
		          vListeDocuments.add(vDe);
		        }
		        bonMouvementStockValue.setDocuments(vListeDocuments);
		      }
		    
		return bonMouvementStockValue;
	}

	/**
	 * To value.
	 *
	 * @param pBonMouvementEntite
	 *            the bon mouvement entite
	 * @return the bon mouvement stock value
	 */
	public static BonMouvementStockValue toValueAffichage(BonMouvementEntite pBonMouvementEntite) {
		BonMouvementStockValue bonMouvementStockValue = new BonMouvementStockValue();
		bonMouvementStockValue.setId(pBonMouvementEntite.getId());
		bonMouvementStockValue.setDate(pBonMouvementEntite.getDate());
		bonMouvementStockValue.setDescription(pBonMouvementEntite.getDescription());
		if (pBonMouvementEntite.getBlachat() != null) {
			bonMouvementStockValue.setBlachatId(pBonMouvementEntite.getBlachat().getId());

		}

		bonMouvementStockValue.setNumero(pBonMouvementEntite.getNumero());
		bonMouvementStockValue.setRaisonMouvementId(pBonMouvementEntite.getRaisonMouvementId());
		bonMouvementStockValue.setResponsable(pBonMouvementEntite.getResponsable());
		bonMouvementStockValue.setType(pBonMouvementEntite.getType());
		bonMouvementStockValue.setValeur(pBonMouvementEntite.getValeur());
		bonMouvementStockValue.setEtat(pBonMouvementEntite.getEtat());
		bonMouvementStockValue.setPartieintId(pBonMouvementEntite.getPartieInteresseeId());
		bonMouvementStockValue.setOfId(pBonMouvementEntite.getOfId());
		bonMouvementStockValue.setNumBRSortie(pBonMouvementEntite.getNumBRSortie());
		bonMouvementStockValue.setMethode(pBonMouvementEntite.getMethode());
		bonMouvementStockValue.setDaeFacture(pBonMouvementEntite.getDaeFacture());
		bonMouvementStockValue.setFournisseurId(pBonMouvementEntite.getFournisseurId());
		return bonMouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pBonMouvementStockValue
	 *            the bon mouvement stock value
	 * @return the bon mouvement entite
	 */
	public static BonMouvementEntite toEntity(BonMouvementStockValue pBonMouvementStockValue,
			BLAchatEntite pBlAchatEntite, Set<EntiteStockEntite> pListEntite) {
		BonMouvementEntite bonMouvementEntite = new BonMouvementEntite();
		if (pBonMouvementStockValue.getId() != null) {
			bonMouvementEntite.setId(pBonMouvementStockValue.getId());
		}
		bonMouvementEntite.setDate(pBonMouvementStockValue.getDate());
		bonMouvementEntite.setDescription(pBonMouvementStockValue.getDescription());
		if (pBonMouvementStockValue.getBlachatId() != null)
			bonMouvementEntite.setBlachat(pBlAchatEntite);

		if (pBonMouvementStockValue.getMouvements() != null) {
			Set<MouvementEntite> vListMouvementEntite = new HashSet<MouvementEntite>();
			for (MouvementStockValue vMouvementValue : pBonMouvementStockValue.getMouvements()) {
				EntiteStockEntite vEntiteStockMvt = rechercheEntiteStockFromList(pListEntite,
						vMouvementValue.getEntiteStock());
				MouvementEntite vMs = toEntity(vMouvementValue, vEntiteStockMvt);
				vMs.setBonMouvement(bonMouvementEntite);
				vListMouvementEntite.add(vMs);
			}
			bonMouvementEntite.setMouvements(vListMouvementEntite);
		}
		bonMouvementEntite.setNumero(pBonMouvementStockValue.getNumero());
		bonMouvementEntite.setRaisonMouvementId(pBonMouvementStockValue.getRaisonMouvementId());
		bonMouvementEntite.setResponsable(pBonMouvementStockValue.getResponsable());
		bonMouvementEntite.setType(pBonMouvementStockValue.getType());
		bonMouvementEntite.setValeur(pBonMouvementStockValue.getValeur());
		bonMouvementEntite.setEtat(pBonMouvementStockValue.getEtat());
		bonMouvementEntite.setPartieInteresseeId(pBonMouvementStockValue.getPartieintId());
		bonMouvementEntite.setOfId(pBonMouvementStockValue.getOfId());
		bonMouvementEntite.setNumBRSortie(pBonMouvementStockValue.getNumBRSortie());
		bonMouvementEntite.setMethode(pBonMouvementStockValue.getMethode());
		bonMouvementEntite.setDaeFacture(pBonMouvementStockValue.getDaeFacture());
		bonMouvementEntite.setFournisseurId(pBonMouvementStockValue.getFournisseurId());
		return bonMouvementEntite;
	}

	/**
	 * To value.
	 *
	 * @param pMagasinEntite
	 *            the magasin entite
	 * @return the magasin value
	 */
	public static MagasinValue toValue(MagasinEntite pMagasinEntite) {
		MagasinValue magasinValue = new MagasinValue();
		magasinValue.setId(pMagasinEntite.getId());
		magasinValue.setDesignation(pMagasinEntite.getDesignation());
		if (pMagasinEntite.getSiteEntite() != null) {
			magasinValue.setPiComSiteId(pMagasinEntite.getSiteEntite().getId());
		}
		return magasinValue;
	}

	/**
	 * To entity.
	 *
	 * @param pMagasinValue
	 *            the magasin value
	 * @return the magasin entite
	 */
	public static MagasinEntite toEntity(MagasinValue pMagasinValue, SiteEntite pSiteEntite) {
		MagasinEntite magasinEntite = new MagasinEntite();
		if (pMagasinValue.getId() != null) {
			magasinEntite.setId(pMagasinValue.getId());
		}
		magasinEntite.setDesignation(pMagasinValue.getDesignation());
		magasinEntite.setSiteEntite(pSiteEntite);
		return magasinEntite;
	}

	/**
	 * To value.
	 *
	 * @param pEmplacementEntite
	 *            the emplacement entite
	 * @return the emplacement value
	 */
	public static EmplacementValue toValue(EmplacementEntite pEmplacementEntite) {
		EmplacementValue emplacementValue = new EmplacementValue();
		emplacementValue.setId(pEmplacementEntite.getId());
		emplacementValue.setDesignation(pEmplacementEntite.getDesignation());
		emplacementValue.setMagasin(pEmplacementEntite.getMagasin());
		return emplacementValue;
	}

	/**
	 * To entity.
	 *
	 * @param pEmplacementValue
	 *            the emplacement value
	 * @return the emplacement entite
	 */
	public static EmplacementEntite toEntity(EmplacementValue pEmplacementValue) {
		EmplacementEntite emplacementEntite = new EmplacementEntite();
		if (pEmplacementValue.getId() != null) {
			emplacementEntite.setId(pEmplacementValue.getId());
		}
		emplacementEntite.setDesignation(pEmplacementValue.getDesignation());
		emplacementEntite.setMagasin(pEmplacementValue.getMagasin());
		return emplacementEntite;
	}

	/**
	 * To value.
	 *
	 * @param pRaisonMouvementEntite
	 *            the raison mouvement entite
	 * @return the raison mouvement stock value
	 */
	public static RaisonMouvementStockValue toValue(RaisonMouvementEntite pRaisonMouvementEntite) {
		RaisonMouvementStockValue raisonMouvementStockValue = new RaisonMouvementStockValue();
		raisonMouvementStockValue.setId(pRaisonMouvementEntite.getId());
		raisonMouvementStockValue.setDesignation(pRaisonMouvementEntite.getDesignation());
		return raisonMouvementStockValue;
	}

	/**
	 * To entity.
	 *
	 * @param pRaisonMouvementStockValue
	 *            the raison mouvement stock value
	 * @return the raison mouvement entite
	 */
	public static RaisonMouvementEntite toEntity(RaisonMouvementStockValue pRaisonMouvementStockValue) {
		RaisonMouvementEntite raisonMouvementEntite = new RaisonMouvementEntite();
		if (pRaisonMouvementStockValue.getId() != null) {
			raisonMouvementEntite.setId(pRaisonMouvementStockValue.getId());
		}
		raisonMouvementEntite.setDesignation(pRaisonMouvementStockValue.getDesignation());
		return raisonMouvementEntite;
	}

	/**
	 * Recherche EntiteStockEntite fromm List.
	 *
	 * @param pRaisonMouvementStockValue
	 *            the raison mouvement stock value
	 * @return the raison mouvement entite
	 */
	public static EntiteStockEntite rechercheEntiteStockFromList(Set<EntiteStockEntite> pList, Long pId) {
		EntiteStockEntite vEntite = new EntiteStockEntite();
		for (EntiteStockEntite entiteStock : pList) {
			if (entiteStock.getId().equals(pId))
				vEntite = entiteStock;
			break;
		}

		return vEntite;

	}

	/**
	 * To toBonMouvementEntity.
	 *
	 * @param pBonMouvementStockValue
	 *            the bon mouvement stock value
	 * @return the bon mouvement entite
	 */
	public static BonMouvementEntite toBonMouvementEntity(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementEntite bonMouvementEntite = new BonMouvementEntite();

		if (pBonMouvementStockValue.getId() != null) {
			bonMouvementEntite.setId(pBonMouvementStockValue.getId());
		}

		bonMouvementEntite.setDate(pBonMouvementStockValue.getDate());
		bonMouvementEntite.setDescription(pBonMouvementStockValue.getDescription());

		if (pBonMouvementStockValue.getMouvements() != null) {
			Set<MouvementEntite> vListMouvementEntite = new HashSet<MouvementEntite>();

			for (MouvementStockValue vMouvementValue : pBonMouvementStockValue.getMouvements()) {

				EntiteStockEntite vEntiteStockMvt = toEntityStock(vMouvementValue.getEntiteStockValue());

				MouvementEntite vMs = toMouvementEntity(vMouvementValue, vEntiteStockMvt);

				vMs.setBonMouvement(bonMouvementEntite);
				vListMouvementEntite.add(vMs);
			}
			bonMouvementEntite.setMouvements(vListMouvementEntite);
		}
		bonMouvementEntite.setNumero(pBonMouvementStockValue.getNumero());
		bonMouvementEntite.setRaisonMouvementId(pBonMouvementStockValue.getRaisonMouvementId());
		bonMouvementEntite.setResponsable(pBonMouvementStockValue.getResponsable());
		bonMouvementEntite.setType(pBonMouvementStockValue.getType());
		bonMouvementEntite.setValeur(pBonMouvementStockValue.getValeur());
		bonMouvementEntite.setEtat(pBonMouvementStockValue.getEtat());
		bonMouvementEntite.setPartieInteresseeId(pBonMouvementStockValue.getPartieintId());
		bonMouvementEntite.setOfId(pBonMouvementStockValue.getOfId());
		bonMouvementEntite.setNumBRSortie(pBonMouvementStockValue.getNumBRSortie());
		bonMouvementEntite.setMethode(pBonMouvementStockValue.getMethode());
		bonMouvementEntite.setDaeFacture(pBonMouvementStockValue.getDaeFacture());
		return bonMouvementEntite;
	}

	/**
	 * To entity.Mouveement
	 *
	 * @param pMouvementStockValue
	 *            the mouvement stock value
	 * @return the mouvement entite
	 */
	public static MouvementEntite toMouvementEntity(MouvementStockValue pMouvementStockValue,
			EntiteStockEntite pEntiteStockEntite) {
		MouvementEntite mouvementEntite = new MouvementEntite();
		if (pMouvementStockValue.getId() != null) {
			mouvementEntite.setId(pMouvementStockValue.getId());
		}
		mouvementEntite.setType(pMouvementStockValue.getType());
		mouvementEntite.setCones(pMouvementStockValue.getCones());
		mouvementEntite.setConesReel(pMouvementStockValue.getConesReel());
		mouvementEntite.setDetailsMouvement(pMouvementStockValue.getDetailsMouvement());
		mouvementEntite.setEmplacement(pMouvementStockValue.getEmplacement());
		mouvementEntite.setEntiteStock(pEntiteStockEntite);
		mouvementEntite.setFinCones(pMouvementStockValue.getFincones());
		mouvementEntite.setNbRouleaux(pMouvementStockValue.getNbRouleaux());
		mouvementEntite.setNbRouleauxReel(pMouvementStockValue.getNbRouleauxReel());
		mouvementEntite.setPoids(pMouvementStockValue.getPoids());
		mouvementEntite.setPrixUnitaire(pMouvementStockValue.getPrixUnitaire());
		mouvementEntite.setQuantite(pMouvementStockValue.getQuantite());
		mouvementEntite.setQuantiteReelle(pMouvementStockValue.getQuantiteReelle());
		mouvementEntite.setDescription(pMouvementStockValue.getDescription());
		mouvementEntite.setPoidsReel(pMouvementStockValue.getPoidsReel());
		mouvementEntite.setFinConesReel(pMouvementStockValue.getFinconesReel());
		mouvementEntite.setOa(pMouvementStockValue.getOa());
		mouvementEntite.setObservation(pMouvementStockValue.getObservation());
		mouvementEntite.setLot(pMouvementStockValue.getLot());
		return mouvementEntite;
	}

	/**
	 * to Entity EntityStock
	 * 
	 * @param pEntiteStockValue
	 * @param pMagasinEntite
	 * @param pArticleEntite
	 * @return
	 */
	public static EntiteStockEntite toEntityStock(EntiteStockValue pEntiteStockValue) {
		EntiteStockEntite entiteStock = new EntiteStockEntite();
		if (pEntiteStockValue.getId() != null) {
			entiteStock.setId(pEntiteStockValue.getId());
		}

		entiteStock.setConeReserve(pEntiteStockValue.getConeReserve());
		entiteStock.setConesActuel(pEntiteStockValue.getConesActuel());
		entiteStock.setDateEntree(pEntiteStockValue.getDateEntree());
		entiteStock.setDateLot(pEntiteStockValue.getDateLot());
		entiteStock.setEmplacement(pEntiteStockValue.getEmplacement());
		entiteStock.setEquivalenceCone(pEntiteStockValue.getEquivalenceCone());
		entiteStock.setFinconeActuel(pEntiteStockValue.getFinconeActuel());
		entiteStock.setFinconeReserve(pEntiteStockValue.getFinconeReserve());
		entiteStock.setLibelleArticle(pEntiteStockValue.getLibelleArticle());
		entiteStock.setPmp(pEntiteStockValue.getPmp());
		entiteStock.setPoidsActuel(pEntiteStockValue.getPoidsActuel());
		entiteStock.setPoidsReserve(pEntiteStockValue.getPoidsReserve());
		entiteStock.setPrixUnitaire(pEntiteStockValue.getPrixUnitaire());
		entiteStock.setQteActuelle(pEntiteStockValue.getQteActuelle());
		entiteStock.setQteResrvee(pEntiteStockValue.getQteReservee());
		entiteStock.setReferenceArticle(pEntiteStockValue.getReferenceArticle());
		entiteStock.setReferenceContenaire(pEntiteStockValue.getReferenceContenaire());
		entiteStock.setReferenceLot(pEntiteStockValue.getReferenceLot());
		entiteStock.setRouleauxActuel(pEntiteStockValue.getRouleauxActuel());
		entiteStock.setRouleauxReserve(pEntiteStockValue.getRouleauxReserve());
		return entiteStock;
	}
	
	public static DocumentBonMouvementEntity toEntity(DocumentBonMouvementValue value) {
		DocumentBonMouvementEntity entity = new DocumentBonMouvementEntity();
		
		if (value.getId() != null) {
			entity.setId(value.getId());
		}

	    entity.setPath(value.getPath());
	    entity.setUidDocument(value.getUidDocument());
	    entity.setTypeDocumentId(value.getTypeDocumentId());
	    
		return entity;
	}
	
	public static DocumentBonMouvementValue toValue(DocumentBonMouvementEntity entity) {
		DocumentBonMouvementValue value = new DocumentBonMouvementValue();
		
	    value.setId(entity.getId());
	    value.setPath(entity.getPath());
	    value.setUidDocument(entity.getUidDocument());
	    value.setTypeDocumentId(entity.getTypeDocumentId());
	    
	    return value;
	  }


}
