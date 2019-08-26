package com.gpro.consulting.tiers.gpao.coordination.operation.value;

import java.util.Set;
import java.util.TreeSet;

import com.gpro.consulting.tiers.commun.coordination.response.value.ResponseValue;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class ListOperationResponseValue extends ResponseValue{

	private Set<OperationValue> operations;

	public Set<OperationValue> getOperations() {
		
		if (operations == null) {
			operations = new TreeSet<OperationValue>();
		}
		return this.operations;
	}

	public void setOperations(Set<OperationValue> operations) {
		this.operations = operations;
	}

	
}
