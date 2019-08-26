package com.gpro.consulting.tiers.gpao.service.test.impl;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IPhaseOfDomaine;


/**
 * @author raan
 *
 */
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gpao-service-test.xml",
		"/spring/config-ma-gpro-gpao-persistance.xml",
		"/spring/config-ma-gpro-gpao-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class PhaseOfServiceTest {
	@Autowired
	IPhaseOfDomaine PhaseOfDomaine ;
	
	/** La datasource. */

	@Autowired
	private DataSource dataSource;
	
	@DirtiesContext
	@Rollback(false)
	@Test
	public void test() {
		PhaseOfValue vPhaseOfValue= new PhaseOfValue();
		assertNotNull("N'est pas bien enregistrer", PhaseOfDomaine.modifierPhaseOf(vPhaseOfValue));
	}
	public IPhaseOfDomaine getPhaseOfDomaine() {
		return PhaseOfDomaine;
	}
	public void setPhaseOfDomaine(IPhaseOfDomaine phaseOfDomaine) {
		PhaseOfDomaine = phaseOfDomaine;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
}
