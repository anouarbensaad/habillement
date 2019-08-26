package com.gpro.consulting.tiers.commun.service.test.partieInteresse.modifier;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class SupprimerDocumentPartieInteresseeServiceTest {
	// logger
	private static final org.slf4j.Logger LOGGER = LoggerFactory
			.getLogger(SupprimerDocumentPartieInteresseeServiceTest.class);
	// injection partieInteresseeDomaine
	@Autowired
	private IPartieInteresseeDomaine partieInteresseeDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test ajouter ligne document PI
	// =====>entrer : PI + deux document
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testSupprimerDocumentPI() {
		// recherche PI par id
		PartieInteresseValue piModifier = partieInteresseRechercher(5L);
		// supprimer ligne document
		for (DocumentValue documentmodifier : piModifier.getDocuments()) {
			if (documentmodifier.getPath().equals("c:/documemnt")) {
				piModifier.getDocuments().remove(documentmodifier);
				LOGGER.info("path= " + documentmodifier.getPath());
				break;
			}
		}
		// traitement : modifier PI et supprimer ligne de document
		String vIdPIResultant = partieInteresseeDomaine
				.modifierPartieInteressee(piModifier);
		assertNotNull(vIdPIResultant);

		// resultat volue : PI + 1 seul ligne de document
		PartieInteresseValue piId = new PartieInteresseValue();
		piId.setId(Long.parseLong(vIdPIResultant));
		PartieInteresseValue piRechercher = partieInteresseeDomaine
				.recherchePartieInteresseeParId(piId);
		// verification de document supprimer
		for (DocumentValue documentVerifier : piRechercher.getDocuments()) {
			assertFalse(documentVerifier.getPath().equals("c:/documemnt"));
			LOGGER.info("path= " + documentVerifier.getPath());
		}
	}

	/*********** rechercher PI par id ********/
	public PartieInteresseValue partieInteresseRechercher(Long id) {
		// id PI à rechercher
		PartieInteresseValue vPiId = new PartieInteresseValue();
		vPiId.setId(id);
		PartieInteresseValue vPi = partieInteresseeDomaine
				.recherchePartieInteresseeParId(vPiId);
		return vPi;
	}

	public IPartieInteresseeDomaine getPartieInteresseeDomaine() {
		return partieInteresseeDomaine;
	}

	public void setPartieInteresseeDomaine(
			IPartieInteresseeDomaine partieInteresseeDomaine) {
		this.partieInteresseeDomaine = partieInteresseeDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
