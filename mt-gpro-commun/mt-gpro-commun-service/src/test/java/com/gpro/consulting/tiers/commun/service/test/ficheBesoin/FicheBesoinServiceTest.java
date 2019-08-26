package com.gpro.consulting.tiers.commun.service.test.ficheBesoin;

import static org.junit.Assert.*;

import java.util.Calendar;
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

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.IFicheBesoinDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class FicheBesoinServiceTest {
	
	@Autowired
	IFicheBesoinDomaine gIFicheBesoinDomaine;

	@Autowired
	private DataSource dataSource;
	
	@DirtiesContext
	@Rollback(false)
	@Test
	public void test() {
		FicheBesoinValue vFicheBesoinValue = new FicheBesoinValue();
		
		//vFicheBesoinValue.setIdFicheBesoin(12L);
		
		//vFicheBesoinValue.setBlSuppression(false);
		vFicheBesoinValue.setDateCreation(Calendar.getInstance());
		vFicheBesoinValue.setDateIntroduction(Calendar.getInstance());
		//vFicheBesoinValue.setDateModification(Calendar.getInstance());
		//vFicheBesoinValue.setDateSuppression(Calendar.getInstance());
		vFicheBesoinValue.setObservation("Observation 16-10-2015");
		vFicheBesoinValue.setResponsable("Responsable 16-10-2015");
		
		ProduitValue vProduitValue = new ProduitValue();
		vProduitValue.setId(2L);
		vFicheBesoinValue.setProduitValue(vProduitValue);

		ArticleValue vArticleValue = new ArticleValue();
		vArticleValue.setId(58L);
		CouleurValue vCouleurValue = new CouleurValue();
		vCouleurValue.setId(2L);		
		TailleValue vTailleValue = new TailleValue();
		vTailleValue.setId(4L);
		
		Set<ElementBesoinValue> vSetelementBesoinValue  = new HashSet<ElementBesoinValue>();
		ElementBesoinValue vItemElementBesoinValue  = new ElementBesoinValue();
		ElementBesoinValue vItemElementBesoinValue2 = new ElementBesoinValue();
		ElementBesoinValue vItemElementBesoinValue3 = new ElementBesoinValue();
		ElementBesoinValue vItemElementBesoinValue4 = new ElementBesoinValue();
		ElementBesoinValue vItemElementBesoinValue5 = new ElementBesoinValue();

		//vItemElementBesoinValue.setIdElementBesoinValue(57L);
		vItemElementBesoinValue.setBloQuatite(true);
		vItemElementBesoinValue.setDateCreation(Calendar.getInstance());
//		vItemElementBesoinValue.setDateModification(Calendar.getInstance());
//		vItemElementBesoinValue.setDateSuppression(Calendar.getInstance());
//		vItemElementBesoinValue.setBlSuppression(false);		
		vItemElementBesoinValue.setOrdre(1);
		vItemElementBesoinValue.setPhase("Phase 1");
		vItemElementBesoinValue.setQuantite(10.25);
		vItemElementBesoinValue.setArticle(vArticleValue);
		//vItemElementBesoinValue.setCouleur(vCouleurValue);
		//vItemElementBesoinValue.setTaille(vTailleValue);
		
		//vItemElementBesoinValue2.setIdElementBesoinValue(58L);
		vItemElementBesoinValue2.setBloQuatite(true);
		vItemElementBesoinValue2.setDateCreation(Calendar.getInstance());
//		vItemElementBesoinValue2.setBlSuppression(false);
//		vItemElementBesoinValue2.setDateModification(Calendar.getInstance());
//		vItemElementBesoinValue2.setDateSuppression(Calendar.getInstance());
		vItemElementBesoinValue2.setOrdre(2);
		vItemElementBesoinValue2.setPhase("Phase 2");
		vItemElementBesoinValue2.setQuantite(11.5);
		vItemElementBesoinValue2.setArticle(vArticleValue);
		//vItemElementBesoinValue2.setCouleur(vCouleurValue);
		//vItemElementBesoinValue2.setTaille(vTailleValue);		

		//vItemElementBesoinValue3.setIdElementBesoinValue(59L);
		vItemElementBesoinValue3.setBloQuatite(true);
		vItemElementBesoinValue3.setDateCreation(Calendar.getInstance());
//		vItemElementBesoinValue3.setBlSuppression(false);
//		vItemElementBesoinValue3.setDateModification(Calendar.getInstance());
//		vItemElementBesoinValue3.setDateSuppression(Calendar.getInstance());
		vItemElementBesoinValue3.setOrdre(3);
		vItemElementBesoinValue3.setPhase("Phase 3");
		vItemElementBesoinValue3.setQuantite(33.55);
		vItemElementBesoinValue3.setArticle(vArticleValue);
		//vItemElementBesoinValue3.setCouleur(vCouleurValue);
		//vItemElementBesoinValue3.setTaille(vTailleValue);

		//vItemElementBesoinValue4.setIdElementBesoinValue(60L);
		vItemElementBesoinValue4.setBloQuatite(true);
		vItemElementBesoinValue4.setDateCreation(Calendar.getInstance());
//		vItemElementBesoinValue4.setBlSuppression(false);
//		vItemElementBesoinValue4.setDateModification(Calendar.getInstance());
//		vItemElementBesoinValue4.setDateSuppression(Calendar.getInstance());
		vItemElementBesoinValue4.setOrdre(4);
		vItemElementBesoinValue4.setPhase("phase 4");
		vItemElementBesoinValue4.setQuantite(1.5);
		vItemElementBesoinValue4.setArticle(vArticleValue);
		//vItemElementBesoinValue4.setCouleur(vCouleurValue);
		//vItemElementBesoinValue4.setTaille(vTailleValue);
		
		//vItemElementBesoinValue5.setIdElementBesoinValue(61L);
		vItemElementBesoinValue5.setBloQuatite(true);
		vItemElementBesoinValue5.setDateCreation(Calendar.getInstance());
//		vItemElementBesoinValue5.setBlSuppression(false);
//		vItemElementBesoinValue5.setDateModification(Calendar.getInstance());
//		vItemElementBesoinValue5.setDateSuppression(Calendar.getInstance());
		vItemElementBesoinValue5.setOrdre(5);
		vItemElementBesoinValue5.setPhase("Phase 5");
		vItemElementBesoinValue5.setQuantite(12.00);
		vItemElementBesoinValue5.setArticle(vArticleValue);
		vItemElementBesoinValue5.setEb_couleur_id(22L);
		vItemElementBesoinValue5.setEb_taille_id(23L);
		//vItemElementBesoinValue5.setCouleur(vCouleurValue);
		//vItemElementBesoinValue5.setTaille(vTailleValue);		
		
		vSetelementBesoinValue.add(vItemElementBesoinValue);	
		vSetelementBesoinValue.add(vItemElementBesoinValue2);
		vSetelementBesoinValue.add(vItemElementBesoinValue3);
		vSetelementBesoinValue.add(vItemElementBesoinValue4);
		vSetelementBesoinValue.add(vItemElementBesoinValue5);
		vFicheBesoinValue.setElementBesoinValue(vSetelementBesoinValue);
		
		String idFicheBesoin  = gIFicheBesoinDomaine.creerOmodifierFicheBesoin(vFicheBesoinValue);
		
		assertNotNull("N'est pas bien enregistrer", idFicheBesoin);
	}

	public IFicheBesoinDomaine getgIFicheBesoinDomaine() {
		return gIFicheBesoinDomaine;
	}

	public void setgIFicheBesoinDomaine(IFicheBesoinDomaine gIFicheBesoinDomaine) {
		this.gIFicheBesoinDomaine = gIFicheBesoinDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	

}
