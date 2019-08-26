package com.gpro.consulting.tiers.commun.coordination.response.value;

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public enum ErrorType {

	TECHNIQUE,
	FONCTIONNEL;

    public String value() {
        return name();
    }

    public static ErrorType fromValue(String v) {
        return valueOf(v);
    }
}
