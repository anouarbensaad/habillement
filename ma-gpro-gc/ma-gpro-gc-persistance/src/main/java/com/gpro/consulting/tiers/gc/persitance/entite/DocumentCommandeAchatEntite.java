/**
 * 
 */
package com.gpro.consulting.tiers.gc.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author $Ameni
 *
 */
@Entity
@Table(name = IConstanteGC.TABLE_GC_DOCUMENTCOMMANDEACHAT)
public class DocumentCommandeAchatEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2112283363061929183L;

	@Id
	@SequenceGenerator(name = "DOCUMENTCOMMANDEACHAT_ID_GENERATOR", sequenceName = IConstanteGC.SEQUENCE_GC_DOCUMENTCOMMANDEACHAT, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENTCOMMANDEACHAT_ID_GENERATOR")
	private Long id;

	/** The path. */
	@Column(name = "path")
	private String path;

	/** The uid document. */
	@Column(name = "uid_document")
	private String uidDocument;

	/** The type document id. */
	@Column(name = "com_typedoc_id")
	private Long typeDocumentId;

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** * many-to-one association to CommandeAchat*. */
	@ManyToOne
	@JoinColumn(name = "gc_commandeachat_id")
	private CommandeAchatEntite commandeAchat;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the uidDocument
	 */
	public String getUidDocument() {
		return uidDocument;
	}

	/**
	 * @param uidDocument
	 *            the uidDocument to set
	 */
	public void setUidDocument(String uidDocument) {
		this.uidDocument = uidDocument;
	}

	/**
	 * @return the typeDocumentId
	 */
	public Long getTypeDocumentId() {
		return typeDocumentId;
	}

	/**
	 * @param typeDocumentId
	 *            the typeDocumentId to set
	 */
	public void setTypeDocumentId(Long typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression
	 *            the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation
	 *            the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification
	 *            the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression
	 *            the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the commandeAchat
	 */
	public CommandeAchatEntite getCommandeAchat() {
		return commandeAchat;
	}

	/**
	 * @param commandeAchat
	 *            the commandeAchat to set
	 */
	public void setCommandeAchat(CommandeAchatEntite commandeAchat) {
		this.commandeAchat = commandeAchat;
	}

}
