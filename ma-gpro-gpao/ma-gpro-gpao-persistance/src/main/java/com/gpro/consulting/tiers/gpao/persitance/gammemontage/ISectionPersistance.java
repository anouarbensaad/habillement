package com.gpro.consulting.tiers.gpao.persitance.gammemontage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;

/**
 * Section Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface ISectionPersistance {

	public String create(SectionValue sectionValue);

	public SectionValue getById(Long id);

	public String update(SectionValue sectionValue);

	public void delete(Long id);

	public List<SectionValue> getAll();
}
