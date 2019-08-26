package com.gpro.consulting.tiers.commun.service.test.produit;
import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.DetailsPrixProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterDetailsPrixProduitServiceTest {
	// logger
	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(AjouterDetailsPrixProduitServiceTest.class);
	// injection produit domaine
	@Autowired
	IProduitDomaine produitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test ajouter ligne document produit
	// =====>entrer : produit + une seule ligne details prix
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testAjouterDetailsPrixProduit() {
		// recherche produit par id
		ProduitValue produitModifier = produitRechercher(5L);
		// Creation d'un objet valeur produit
		// creer list details prix
		Set<DetailsPrixProduitValue> detailsPrixValues = new HashSet<DetailsPrixProduitValue>();

		// remplir list details prix
		DetailsPrixProduitValue nouveauDetailsPrix = creerDetailsPrixProduit(
				"c:/doc", 2L, new GregorianCalendar(2013, 10, 28),
				new GregorianCalendar(2015, 10, 28), "metode", 2.0, 2L, 6L);
		detailsPrixValues.add(nouveauDetailsPrix);
		LOGGER.info("path : " + nouveauDetailsPrix.getPrixVente());
		// affecter list details prix au produit
		produitModifier.setDetailsPrix(detailsPrixValues);

		// traitement : modifier produit et ajouter ligne de document
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitModifier);
		assertNotNull(vIdProduitResultant);

		// resultat volue : produit + deux ligne de details prix
		// Vérification du resultat modifier
		ProduitValue produitRechercher = produitRechercher(Long
				.parseLong(vIdProduitResultant));
		// Test et vérifiacation

		// Verifiication des caracteritiuques de details prix produit
		for (DetailsPrixProduitValue detailsPrix : produitRechercher
				.getDetailsPrix()) {
			if (detailsPrix.getId() == 2L) {
				assertTrue(detailsPrix.getPrixVente() == 2.0);
				break;
			}
		}
		// verification nombre des details prix
		assertEquals(produitRechercher.getDocumentProduits().size(), 2);

	}
	
	 /*********** rechercher produit par id ********/
		public ProduitValue produitRechercher(Long id) {
			// id produit à rechercher
			ProduitValue produitModifier = produitDomaine.rechercheProduitById(id);
			return produitModifier;
		}

		/***** creation details prix  produit Value 
		 * @param dateDeb 
		 * @param dateFin 
		 * @param methode 
		 * @param prixVente 
		 * @param quantiteInferieur 
		 * @param quantiteSuperieur ***/
		public DetailsPrixProduitValue creerDetailsPrixProduit(String path,Long typeDocumentId, Calendar dateDeb, 
				Calendar dateFin, String methode, double prixVente, Long quantiteInferieur, Long quantiteSuperieur) {
			DetailsPrixProduitValue vDp= new DetailsPrixProduitValue();
			vDp.setDateDeb(dateDeb);
			vDp.setDateFin(dateFin);
			vDp.setMethode(methode);
			vDp.setPrixVente(prixVente);
			vDp.setQuantiteInferieur(quantiteInferieur);
			vDp.setQuantiteSuperieur(quantiteSuperieur);
			return vDp;
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
