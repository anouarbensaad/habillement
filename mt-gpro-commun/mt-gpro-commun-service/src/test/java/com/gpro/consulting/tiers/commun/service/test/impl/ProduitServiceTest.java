package com.gpro.consulting.tiers.commun.service.test.impl;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.DetailsPrixProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ProduitServiceTest {
	//injection produit domaine 
	@Autowired
	IProduitDomaine produitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// ******declaration des variable *******/
	DocumentProduitValue documentValue = new DocumentProduitValue();
	DocumentProduitValue documentValue2 = new DocumentProduitValue();
	DetailsPrixProduitValue detailsPrix = new DetailsPrixProduitValue();
	DetailsPrixProduitValue detailsPrix2 = new DetailsPrixProduitValue();
	List<ProduitValue> list;
	RechercheMulticritereProduitValue vRechercheMultiCritere = new RechercheMulticritereProduitValue();
	ProduitValue produitValue = new ProduitValue();

	// test si produit est ajouter
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreatProduit() {
		// Creation d'un objet valeur produit
		/******** creation list document ***************/
		documentValue.setTypeDocumentId(1L);
		documentValue.setPath("c:/document");
		documentValue2.setTypeDocumentId(1L);
		documentValue2.setPath("c:/document");
		Set<DocumentProduitValue> documentValues = new HashSet<DocumentProduitValue>();
		documentValues.add(documentValue);
		documentValues.add(documentValue2);
		/************* creation liste details prix *********************/
		detailsPrix.setPrixVente(10);
		detailsPrix.setMethode("details 3");
		detailsPrix.setDateDeb(new GregorianCalendar(2013, 10, 28));
		detailsPrix.setDateDeb(new GregorianCalendar(2013, 10, 28));
		detailsPrix.setQuantiteInferieur(10L);
		detailsPrix.setQuantiteSuperieur(20L);
		detailsPrix2.setMethode("details 2");
		detailsPrix2.setPrixVente(5);
		detailsPrix2.setDateDeb(new GregorianCalendar(2013, 10, 28));
		detailsPrix2.setDateDeb(new GregorianCalendar(2013, 10, 28));
		detailsPrix2.setQuantiteInferieur(10L);
		Set<DetailsPrixProduitValue> detailsPrixValues = new HashSet<DetailsPrixProduitValue>();
		detailsPrixValues.add(detailsPrix);
		detailsPrixValues.add(detailsPrix2);
		/************* Creation d'un objet valeur produit *************/
		produitValue.setDesignation("produit test");
		produitValue.setReference("reference test");
		produitValue.setSiteId(1L);
		produitValue.setDocumentProduits(documentValues);
		produitValue.setDetailsPrix(detailsPrixValues);
		produitValue.setDateIntroduction(new GregorianCalendar(2013, 10, 28));
		produitValue.setPartieIntersseId(1L);
		produitValue.setReference("reference 1");
		produitValue.setSiteId(1L);
		produitValue.setSousFamilleId(1L);
		// Lancer la création
		String vIdProduitResultant = produitDomaine.creerProduit(produitValue);
		// Test et vérifiacation
		assertNotNull(vIdProduitResultant);
		fail("erreur d'ajout    " + vIdProduitResultant);

	}

	/***************************************** test unitaire modification produit ***************************************************/
	// test produit est modifier totalement
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierProduitTotale() {
		/************* Creation d'un objet valeur produit *************/
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		produitModifier.setDesignation("modifier produit totale ");
		produitModifier.setReference("modifier reference totale");
		produitModifier.setSiteId(1L);
		produitModifier
				.setDateIntroduction(new GregorianCalendar(2013, 10, 28));
		produitModifier.setPartieIntersseId(1L);
		produitModifier.setSousFamilleId(1L);
		// ajouter document
		DocumentProduitValue nouveauDocument = new DocumentProduitValue();
		nouveauDocument.setPath("c:/documentProduit.pdf");
		produitModifier.getDocumentProduits().add(nouveauDocument);
		// ajouter prix
		DetailsPrixProduitValue nouveauPrix = new DetailsPrixProduitValue();
		nouveauPrix.setPrixVente(2.0);
		nouveauPrix.setQuantiteInferieur(2L);
		nouveauPrix.setQuantiteSuperieur(5L);
		nouveauPrix.setDateFin(new GregorianCalendar(2016, 10, 28));
		produitModifier.getDetailsPrix().add(nouveauPrix);
		// modifier produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitValue);
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(5L);
		// verification si produit est modifier
		assertNotNull(produitVerifier.getDocumentProduits().size());
		assertNotNull(produitVerifier.getDetailsPrix().size());
		assertNotNull(vIdProduitResultant);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	// test produit est modifier supprimer ligne details prix et ligne document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierProduitSupprimerDocumentEtPrix() {
		/************* Creation d'un objet valeur produit *************/
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		produitModifier
				.setDesignation("modifier produit suppriemr prix et document ");
		produitModifier
				.setReference("modifier reference suppriemr prix et document");
		produitModifier.setSiteId(1L);
		// suppriemr document
		for (DocumentProduitValue ducumentSupprimer : produitModifier
				.getDocumentProduits()) {
			if (ducumentSupprimer.getPath().equals("c:/documentProduit.pdf")) {
				produitModifier.getDocumentProduits().remove(ducumentSupprimer);
			}
		}
		// suppriemr details Prix
		for (DetailsPrixProduitValue prixSupprimer : produitModifier
				.getDetailsPrix()) {
			if (prixSupprimer.getPrixVente() == 2.0) {
				produitModifier.getDetailsPrix().remove(prixSupprimer);
			}
		}

		// modifier produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitValue);
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(5L);
		// verification si produit est modifier
		assertEquals(produitVerifier.getDocumentProduits().size(), 1);
		assertEquals(produitVerifier.getDetailsPrix().size(), 1);
		assertNotNull(vIdProduitResultant);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	// test produit est modifier supprimer tout document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierProduitSupprimerTousDocument() {
		/************* Creation d'un objet valeur produit *************/
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		produitModifier
				.setDesignation("modifier produit suppriemr prix et document ");
		produitModifier
				.setReference("modifier reference suppriemr prix et document");
		produitModifier.setSiteId(1L);
		// suppriemr document
		produitModifier.setDocumentProduits(null);
		// modifier produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitValue);
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(5L);
		// verification si produit est modifier
		assertEquals(produitVerifier.getDocumentProduits().size(), 0);
		assertNotNull(vIdProduitResultant);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	// test produit est modifier supprimer tout details prix
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierProduitSupprimerTousPrix() {
		/************* Creation d'un objet valeur produit *************/
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		produitModifier
				.setDesignation("modifier produit suppriemr prix et document ");
		produitModifier
				.setReference("modifier reference suppriemr prix et document");
		produitModifier.setSiteId(1L);
		// suppriemr document
		produitModifier.setDetailsPrix(null);
		// modifier produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitValue);
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(5L);
		// verification si produit est modifier
		assertEquals(produitVerifier.getDetailsPrix().size(), 0);
		assertNotNull(vIdProduitResultant);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	// test modifier caracteristique produit
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierCaracteristiqueProduit() {
		/************* Creation d'un objet valeur produit *************/
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		produitModifier.setDesignation("modifier produit totale ");
		produitModifier.setReference("modifier reference totale");
		produitModifier.setSiteId(1L);
		produitModifier
				.setDateIntroduction(new GregorianCalendar(2013, 10, 28));
		produitModifier.setPartieIntersseId(1L);
		produitModifier.setSousFamilleId(1L);
		produitModifier.setSousFamilleDesignation("sous famille 12/08/2015");
		produitModifier.setFamilleDesignation("famille 12/08/2015");
		// modifier produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitValue);
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(5L);
		// verification si produit est modifier
		assertEquals(produitVerifier.getDesignation(),
				"modifier produit totale ");
		assertEquals(produitVerifier.getFamilleDesignation(),
				"famille 12/08/2015");
		assertEquals(produitVerifier.getPartieIntersseId().longValue(), 1L);
		assertEquals(produitVerifier.getReference(),
				"modifier reference totale");
		assertEquals(produitVerifier.getSiteId().longValue(), 1L);
		assertEquals(produitVerifier.getSousFamilleDesignation(),
				"sous famille 12/08/2015");
		assertNotNull(vIdProduitResultant);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	/***************************************** Fin test unitaire modification produit ***************************************************/

	/*************************************** test recherche multi critere produit *************************/
	// test Recherche Multi Critere Produit Quatre Critere
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereProduitQuatreCritere() {
		// Creation d'un objet valeur RechercheMulticritere
		vRechercheMultiCritere.setDesignation("produit 1");
		vRechercheMultiCritere.setReference("prod1");
		vRechercheMultiCritere.setSite("1");
		vRechercheMultiCritere.setFamille("1");
		// lancer recherche
		ResultatRechecheProduitValue vResultatRecherche = produitDomaine
				.rechercherProduitMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		fail("erreur taille  liste  "
				+ vResultatRecherche.getNombreResultaRechercher());

	}

	// test Recherche Multi Critere Produit tous Critere
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereProduitTousCritere() {
		// Creation d'un objet valeur RechercheMulticritere
		vRechercheMultiCritere.setDesignation("produit 1");
		vRechercheMultiCritere.setReference("prod1");
		vRechercheMultiCritere.setSite("1");
		vRechercheMultiCritere.setFamille("1");
		vRechercheMultiCritere.setSousfamille("1");
		vRechercheMultiCritere.setPartieInteressee("1");
		vRechercheMultiCritere.setPrix_sup(2.0);
		vRechercheMultiCritere.setPrix_sup(10.0);
		vRechercheMultiCritere.setEtat("etat");
		// lancer recherche
		ResultatRechecheProduitValue vResultatRecherche = produitDomaine
				.rechercherProduitMultiCritere(vRechercheMultiCritere);

		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		fail("erreur taille  liste  "
				+ vResultatRecherche.getNombreResultaRechercher());

	}

	// test Recherche Multi Critere Produit entre deux prix
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereProduitEntreDeuxPrix() {
		// Creation d'un objet valeur RechercheMulticritere
		vRechercheMultiCritere.setPrix_sup(2.0);
		vRechercheMultiCritere.setPrix_sup(10.0);
		// lancer recherche
		ResultatRechecheProduitValue vResultatRecherche = produitDomaine
				.rechercherProduitMultiCritere(vRechercheMultiCritere);

		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		fail("erreur taille  liste  "
				+ vResultatRecherche.getNombreResultaRechercher());

	}

	// test Recherche Multi Critere Produit prix superieur < prix inferieur
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereProduitEntreDeuxPrixDeferent() {
		// Creation d'un objet valeur RechercheMulticritere
		vRechercheMultiCritere.setPrix_sup(10.0);
		vRechercheMultiCritere.setPrix_sup(2.0);
		// lancer recherche
		ResultatRechecheProduitValue vResultatRecherche = produitDomaine
				.rechercherProduitMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		// assertFalse(vResultatRecherche.getNombreResultaRechercher().equals(1L));
		fail("erreur taille  liste  "
				+ vResultatRecherche.getNombreResultaRechercher());

	}

	/*************************************** Fin test recherche multi critere produit *************************/

	// test produit est supprimer
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testSupprimerProduit() {
			/************** lancer la suppression ****************/
			produitDomaine.supprimerProduit(3L);
			fail("erreur supprission   ");
		}

	// test recherche d'une liste
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testListerProduit() {
		list = produitDomaine.listeProduit();
		assertNotNull(list);
	}

	
	public IProduitDomaine getProduitDomaine() {
		return produitDomaine;
	}

	public void setProduitDomaine(IProduitDomaine produitDomaine) {
		this.produitDomaine = produitDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
