package com.gpro.consulting.tiers.commun.coordination.response.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class ResponseValue {

	private boolean hasErrors;
	private List<ErrorValue> errors;
	private Integer totalResults;
	
	public boolean getHasErrors() {
		
		if(getErrors().size() > 0)
			return true;
		
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public List<ErrorValue> getErrors() {
        if (errors == null) {
        	errors = new ArrayList<ErrorValue>();
        }
        return this.errors;
	}

	public void setErrors(List<ErrorValue> errors) {
		this.errors = errors;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	
	
}
