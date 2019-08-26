package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;

public interface ISectionDomaine {
	
	public String create(SectionValue sectionValue);

	public SectionValue getById(Long id);

	public String update(SectionValue sectionValue);

	public void delete(Long id);

	public List<SectionValue> getAll();
}
