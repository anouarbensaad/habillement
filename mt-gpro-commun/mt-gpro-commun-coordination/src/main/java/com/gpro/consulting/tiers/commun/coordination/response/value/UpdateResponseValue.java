package com.gpro.consulting.tiers.commun.coordination.response.value;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public class UpdateResponseValue extends ResponseValue{

	/**
	 * The id of the object value update 
	 * when the UPDATE action is succeed
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
