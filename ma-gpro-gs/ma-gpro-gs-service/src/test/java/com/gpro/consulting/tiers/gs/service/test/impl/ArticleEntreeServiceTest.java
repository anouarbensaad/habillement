package com.gpro.consulting.tiers.gs.service.test.impl;
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
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.domaine.IBonMouvementDomaine;
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gs-service-test.xml",
		"/spring/config-ma-gpro-gs-persistance.xml",
		"/spring/config-ma-gpro-gs-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ArticleEntreeServiceTest {

}
