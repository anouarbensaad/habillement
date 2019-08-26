package com.gpro.consulting.tiers.commun.coordination.response.value;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class DeleteResponseValue extends ResponseValue{

	/**
	 * 	Set deleted attribute on true boolean value
	 * 	when the DELETE action is succeed
	 */
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
	
}
