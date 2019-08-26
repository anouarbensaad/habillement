package com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue;

import java.util.HashSet;
import java.util.Set;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;

/**
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */
public class FicheSuiveuseVue {
	
	Set<OperationValue> operationsList = new HashSet<OperationValue>();
	Set<PaquetValue> paquetsList = new HashSet<PaquetValue>();
	
	public Set<OperationValue> getOperationsList() {
		return operationsList;
	}
	public void setOperationsList(Set<OperationValue> operationsList) {
		this.operationsList = operationsList;
	}
	public Set<PaquetValue> getPaquetsList() {
		return paquetsList;
	}
	public void setPaquetsList(Set<PaquetValue> paquetsList) {
		this.paquetsList = paquetsList;
	}

}
