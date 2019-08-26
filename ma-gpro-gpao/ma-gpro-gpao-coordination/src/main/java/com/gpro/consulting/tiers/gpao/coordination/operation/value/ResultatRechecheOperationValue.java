package com.gpro.consulting.tiers.gpao.coordination.operation.value;

import java.util.Set;
import java.util.TreeSet;

import com.gpro.consulting.tiers.commun.coordination.response.value.ResponseValue;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class ResultatRechecheOperationValue extends ResponseValue{

	private Set<OperationValue> list = new TreeSet <OperationValue>();

	public Set<OperationValue> getList() {
		return list;
	}

	public void setList(Set<OperationValue> list) {
		this.list = list;
	}

}
