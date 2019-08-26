package com.gpro.consulting.tiers.commun.domaine.baseinfo;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;

/**
 * BaseInfo Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */
public interface IBaseInfoDomaine {

	public String create(BaseInfoValue baseInfoValue);

	public BaseInfoValue getById(Long id);

	public String update(BaseInfoValue baseInfoValue);

	public void delete(Long id);

	public List<BaseInfoValue> getAll();
	
	public BaseInfoValue getClientActif();
	
	public String getLogo();
}
