package com.gpro.consulting.tiers.gc.service.test.impl;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;










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

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeVenteDomaine;
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gc-service-test.xml",
		"/spring/config-ma-gpro-gc-persistance.xml",
		"/spring/config-ma-gpro-gc-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CommandeVenteServiceTest {
 /** commandeVenteDomaine */
	@Autowired
	ICommandeVenteDomaine commandeVenteDomaine;
	
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	// test si magasin    est ajouter
			@Test
			@DirtiesContext
			@Rollback(false)
			public void testCreatBonCommande() {
			  //creation CommandeVente
				CommandeVenteValue commande1=new CommandeVenteValue();
				commande1.setObservations("observations 1");;
			    commande1.setPrixTotal(1D);
			    
			 // traitement :  ajouter ligne de seuil
				String vIdArticleResultant = commandeVenteDomaine
						.creerCommandeVente(commande1);
				// Test et vérifiacation
				assertNotNull(vIdArticleResultant);
			}
			
			

	

	/**
			 * @return the commandeVenteDomaine
			 */
			public ICommandeVenteDomaine getCommandeVenteDomaine() {
				return commandeVenteDomaine;
			}


			/**
			 * @param commandeVenteDomaine the commandeVenteDomaine to set
			 */
			public void setCommandeVenteDomaine(ICommandeVenteDomaine commandeVenteDomaine) {
				this.commandeVenteDomaine = commandeVenteDomaine;
			}





	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
