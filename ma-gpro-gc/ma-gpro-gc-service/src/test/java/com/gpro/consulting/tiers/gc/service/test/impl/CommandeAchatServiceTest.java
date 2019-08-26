/**
 * 
 */
package com.gpro.consulting.tiers.gc.service.test.impl;

import static org.junit.Assert.assertNotNull;




import java.util.HashSet;
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

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ElementCommandeAchatValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeAchatDomaine;

/**
 * @author $Ameni
 *
 */
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gc-service-test.xml",
		"/spring/config-ma-gpro-gc-persistance.xml",
		"/spring/config-ma-gpro-gc-domaine.xml",
		"/spring/ehcacheGc.xml",
		"*/spring/config-mt-gpro-commun-service.xml",
		"*/spring/config-mt-gpro-commun-rest.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CommandeAchatServiceTest {

	/** Injection du Bean. */
	@Autowired
	ICommandeAchatDomaine commandeAchatDomaine;
	
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	//test si le BondeCommande d'Achat est crée
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreatBonCommandeAchat() {
	// creation EtatCommande

		ElementCommandeAchatValue element1 = new ElementCommandeAchatValue();
		ElementCommandeAchatValue element2 = new ElementCommandeAchatValue();

		element1.setLivre(false);
		element1.setPrixTotal(100L);
		element1.setPrixUnitiaire(10L);
		element1.setQuantite(150L);
		element1.setArticleId(182L);

		element2.setLivre(true);
		element2.setPrixTotal(200L);
		element2.setPrixUnitiaire(20L);
		element2.setQuantite(250L);
		element1.setArticleId(183L);
		
		Set<ElementCommandeAchatValue> elementValues = new HashSet<ElementCommandeAchatValue>();
		elementValues.add(element1);
		elementValues.add(element2);
		
		// creation bondeCommandeAchat
		CommandeAchatValue vCommandeAchat = new CommandeAchatValue();
		vCommandeAchat.setReference("RefBCAchat1");
		vCommandeAchat.setEtat("Etat1");
		vCommandeAchat.setElementCommandes(elementValues);
		
		String vIdbonCommandeResultant = commandeAchatDomaine.creerCommandeAchat(vCommandeAchat);
		// Test et vérifiacation
		assertNotNull(vIdbonCommandeResultant);
	}

	
	/**
	 * @return the commandeAchatDomaine
	 */
	public ICommandeAchatDomaine getCommandeAchatDomaine() {
		return commandeAchatDomaine;
	}


	/**
	 * @param commandeAchatDomaine the commandeAchatDomaine to set
	 */
	public void setCommandeAchatDomaine(ICommandeAchatDomaine commandeAchatDomaine) {
		this.commandeAchatDomaine = commandeAchatDomaine;
	}


	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
