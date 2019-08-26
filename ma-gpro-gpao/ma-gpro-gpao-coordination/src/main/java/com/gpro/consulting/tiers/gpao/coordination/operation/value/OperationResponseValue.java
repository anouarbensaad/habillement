package com.gpro.consulting.tiers.gpao.coordination.operation.value;

import com.gpro.consulting.tiers.commun.coordination.response.value.ResponseValue;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class OperationResponseValue extends ResponseValue{

	private OperationValue operation;

	public OperationValue getOperation() {
		return operation;
	}

	public void setOperation(OperationValue operation) {
		this.operation = operation;
	}
	
	
}
