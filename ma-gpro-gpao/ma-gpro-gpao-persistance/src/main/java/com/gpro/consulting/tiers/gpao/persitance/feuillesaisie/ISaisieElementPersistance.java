package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import java.util.List;

public interface ISaisieElementPersistance {

	//Retourne liste des qtés 
	
	public List<Long> getByElementGammeId(Long elementGammeId);
	
	//Retourne la somme des quantités par gammeOfId
	public Long getSommeQteByGammeId(Long elementGammeId);

	public boolean existeOF(String numeroOF);

}
