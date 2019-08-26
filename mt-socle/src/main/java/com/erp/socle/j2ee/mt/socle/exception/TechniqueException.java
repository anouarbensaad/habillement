package com.erp.socle.j2ee.mt.socle.exception;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;

import org.apache.commons.lang3.ArrayUtils;

import com.erp.socle.j2ee.mt.socle.utilities.ExceptionUtils;
import com.erp.socle.j2ee.mt.socle.utilities.UUIDUtilities;



/**
 * Exception de type technique.
 * 
 * @see java.lang.RuntimeException
 * 
 * @author $Author: Ridha KHASKHOUSSY
 * 
 */
@XmlType(namespace = "urn://exception.com.erp")
@WebFault(name = "TechniqueException", targetNamespace = "urn://exception.com.erp")
public class TechniqueException extends RuntimeException implements IException, Serializable {

  /** SerialVersionUID. */
  private static final long serialVersionUID = 6003024623516714625L;

  /** Code de l'exception. */
  private String code;
  
	/** Liste des exceptions Techniques */
	private List<ErreurTechnique> listErreurTechnique;

  /** Identifiant de l'exception. */
  private byte[] uuid;

	/**
	 * Constructeur de la classe.
	 * 
	 * @param message
	 *            Le message de l'exception
	 */
	public TechniqueException(final String message) {
		super(message);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param cause
	 *            La cause de l'exception
	 */
	public TechniqueException(final Throwable cause) {
		super(cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * @param listErreurTechnique
	 */
	public TechniqueException(
			List<ErreurTechnique> listErreurTechnique) {
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
		this.listErreurTechnique = listErreurTechnique;
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param message
	 *            Le message de l'exception
	 * @param cause
	 *            La cause de l'exception
	 */
	public TechniqueException(final String message, final Throwable cause) {
		super(message, cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * @param code
	 * @param cause
	 * @param params
	 */
	public TechniqueException(String code, Throwable cause, String[] params) {
		super(cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
		String message = ExceptionUtils.constuireMessageErreur(code, params,
				TechniqueException.class);
		this.listErreurTechnique = new ArrayList<ErreurTechnique>();
		this.listErreurTechnique
				.add(new ErreurTechnique(code, message));
	}

	/**
	 * @param code
	 * @param params
	 */
	public TechniqueException(String code, String[] params) {
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();

		String message = ExceptionUtils.constuireMessageErreur(code, params,
				TechniqueException.class);
		this.listErreurTechnique = new ArrayList<ErreurTechnique>();
		this.listErreurTechnique
				.add(new ErreurTechnique(code, message));
	}

	/**
	 * Méthode permettant la vérification de l'existance d'un code dans une
	 * liste
	 * 
	 * @param codeErreur
	 * @return
	 */
	public boolean containsCodeErreur(String codeErreur) {
		List<ErreurTechnique> errs = getListErreurTechnique();
		for (ErreurTechnique err : errs) {
			if (err.getCode().equals(codeErreur)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Méthode d'ajout d'une erreur dans la liste
	 * 
	 * @param code
	 * @param message
	 */
	public void addErreur(String code, String message) {
		if (this.listErreurTechnique == null) {
			this.listErreurTechnique = new ArrayList<ErreurTechnique>();
		}
		this.listErreurTechnique
				.add(new ErreurTechnique(code, message));
	}

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getCode() {
    return code;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setCode(final String code) {
    this.code = code;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final byte[] getUuid() {
    return ArrayUtils.clone(uuid);
  }

  /**
   * Accesseur en écriture de l'attribut <code>uuid</code>.
   * 
   * @param uuid
   *          L'attribut uuid à modifier.
   */
  public final void setUuid(final byte[] uuid) {
    this.uuid = ArrayUtils.clone(uuid);
  }

  public final String getNamedUuid() {
    return UUID.nameUUIDFromBytes(getUuid()).toString();
  }
  
	/**
	 * @return the listErreurTechnique
	 */
	public List<ErreurTechnique> getListErreurTechnique() {
		return listErreurTechnique;
	}

	/**
	 * @param listErreurTechnique
	 *            the listErreurTechnique to set
	 */
	public void setListErreurTechnique(
			List<ErreurTechnique> listErreurTechnique) {
		this.listErreurTechnique = listErreurTechnique;
	}

}
