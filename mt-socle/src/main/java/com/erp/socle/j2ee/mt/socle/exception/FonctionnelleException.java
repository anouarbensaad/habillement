package com.erp.socle.j2ee.mt.socle.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.erp.socle.j2ee.mt.socle.utilities.ExceptionUtils;
import com.erp.socle.j2ee.mt.socle.utilities.UUIDUtilities;



/**
 * Exception de type technique.
 * 
 * @see java.lang.Exception
 * 
 */

public class FonctionnelleException extends Exception implements IException,
		Serializable {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 6921361731142746026L;

	/** Code de l'exception. */
	private String code;
	/** Liste des exceptions fonctionnelles */
	private List<ErreurFonctionnelle> listErreurFonctionnelle;
	/** Identifiant de l'exception. */
	private byte[] uuid;

	/**
	 * Constructeur de la classe.
	 * 
	 * @param message
	 *            Le message de l'exception
	 */
	public FonctionnelleException(final String message) {
		super(message);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param cause
	 *            La cause de l'exception
	 */
	public FonctionnelleException(final Throwable cause) {
		super(cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * @param listErreurFonctionnelle
	 */
	public FonctionnelleException(
			List<ErreurFonctionnelle> listErreurFonctionnelle) {
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
		this.listErreurFonctionnelle = listErreurFonctionnelle;
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param message
	 *            Le message de l'exception
	 * @param cause
	 *            La cause de l'exception
	 */
	public FonctionnelleException(final String message, final Throwable cause) {
		super(message, cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
	}

	/**
	 * @param code
	 * @param cause
	 * @param params
	 */
	public FonctionnelleException(String code, Throwable cause, String[] params) {
		super(cause);
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();
		String message = ExceptionUtils.constuireMessageErreur(code, params,
				FonctionnelleException.class);
		this.listErreurFonctionnelle = new ArrayList<ErreurFonctionnelle>();
		this.listErreurFonctionnelle
				.add(new ErreurFonctionnelle(code, message));
	}

	/**
	 * @param code
	 * @param params
	 */
	public FonctionnelleException(String code, String[] params) {
		this.uuid = UUIDUtilities.genererRandom16ByteUuid();

		String message = ExceptionUtils.constuireMessageErreur(code, params,
				FonctionnelleException.class);
		this.listErreurFonctionnelle = new ArrayList<ErreurFonctionnelle>();
		this.listErreurFonctionnelle
				.add(new ErreurFonctionnelle(code, message));
	}

	/**
	 * Méthode permettant la vérification de l'existance d'un code dans une
	 * liste
	 * 
	 * @param codeErreur
	 * @return
	 */
	public boolean containsCodeErreur(String codeErreur) {
		List<ErreurFonctionnelle> errs = getListErreurFonctionnelle();
		for (ErreurFonctionnelle err : errs) {
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
		if (this.listErreurFonctionnelle == null) {
			this.listErreurFonctionnelle = new ArrayList<ErreurFonctionnelle>();
		}
		this.listErreurFonctionnelle
				.add(new ErreurFonctionnelle(code, message));
	}

	/**
	 * {@inheritDoc}
	 */

	public final String getCode() {
		return code;
	}

	/**
	 * {@inheritDoc}
	 */

	public final void setCode(final String code) {
		this.code = code;
	}

	/**
	 * {@inheritDoc}
	 */

	public final byte[] getUuid() {
		return ArrayUtils.clone(uuid);
	}

	/**
	 * Accesseur en écriture de l'attribut <code>uuid</code>.
	 * 
	 * @param uuid
	 *            L'attribut uuid à modifier.
	 */
	public final void setUuid(final byte[] uuid) {
		this.uuid = ArrayUtils.clone(uuid);
	}

	/**
	 * @return the listErreurFonctionnelle
	 */
	public List<ErreurFonctionnelle> getListErreurFonctionnelle() {
		return listErreurFonctionnelle;
	}

	/**
	 * @param listErreurFonctionnelle
	 *            the listErreurFonctionnelle to set
	 */
	public void setListErreurFonctionnelle(
			List<ErreurFonctionnelle> listErreurFonctionnelle) {
		this.listErreurFonctionnelle = listErreurFonctionnelle;
	}

}
