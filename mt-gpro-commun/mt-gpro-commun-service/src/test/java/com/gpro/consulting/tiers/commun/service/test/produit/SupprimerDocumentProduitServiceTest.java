package com.gpro.consulting.tiers.commun.service.test.produit;
import static org.junit.Assert.*;

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
import com.gpro.consulting.tiers.commun.coordination.value.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class SupprimerDocumentProduitServiceTest {
	// logger
		  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SupprimerDocumentProduitServiceTest.class);
	//injection produit domaine 
	@Autowired
	IProduitDomaine produitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	
	// test ajouter ligne document produit
	//=====>entrer : produit  + une seule document 
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerDocumentProduit() {
		// recherche produit par id
		ProduitValue produitModifier = produitRechercher(5L);
		// supprimer ligne document
		for (DocumentProduitValue documentmodifier : produitModifier
				.getDocumentProduits()) {
			if (documentmodifier.getId() == 4L) {
				produitModifier.getDocumentProduits().remove(documentmodifier);
				LOGGER.info("path= " + documentmodifier.getPath());
				break;
			}
		}
		// traitement : modifier produit et supprimer ligne de document
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitModifier);
		assertNotNull(vIdProduitResultant);

		// resultat volue : produit + 1 seul ligne de document
		// Vérification du resultat modifier
		ProduitValue produitRechercher = produitDomaine
				.rechercheProduitById(Long.parseLong(vIdProduitResultant));

		// verification de nombre des lignes de document = 1
		assertEquals(produitRechercher.getDocumentProduits().size(), 1);

	}
	
	 /*********** rechercher produit par id ********/
		public ProduitValue produitRechercher(Long id) {
			// id produit à rechercher
			ProduitValue produitModifier = produitDomaine.rechercheProduitById(id);
			return produitModifier;
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
