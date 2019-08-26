package com.erp.socle.j2ee.mt.socle.exception;

/**
 * Classe permmettant de définir une erreur par un code et un message
 * 
 * @author rkhaskhoussy
 * 
 */
public class ErreurTechnique {
	/** Code */
	private String code;
	/** Message */
	private String message;

	/**
	 * Constructeur
	 * 
	 * @param code
	 * @param message
	 */
	public ErreurTechnique(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ErreurFonctionnelle [code=" + this.code + ", message="
				+ this.message + "]";
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.code == null ? 0 : this.code.hashCode());
		result = 31 * result
				+ (this.message == null ? 0 : this.message.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErreurTechnique other = (ErreurTechnique) obj;
		if (this.code == null) {
			if (other.code != null)
				return false;
		} else if (!this.code.equals(other.code))
			return false;
		if (this.message == null) {
			if (other.message != null)
				return false;
		} else if (!this.message.equals(other.message))
			return false;
		return true;
	}
}