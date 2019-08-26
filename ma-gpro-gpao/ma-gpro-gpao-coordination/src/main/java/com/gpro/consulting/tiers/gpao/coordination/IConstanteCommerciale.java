package com.gpro.consulting.tiers.gpao.coordination;

/**
 * Interface présentant les noms des tables, des séquences, des constantes ...
 * utilisées dans le module Commerciale
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IConstanteCommerciale {
	
	/** Liste des Tables */
	public static String TABLE_GC_METRAGE_FACTURE = "GC_METRAGEFACTURE";
	public static String TABLE_GC_DETLIVRAISONVENTE = "GC_DETLIVRAISONVENTE";
	public static String TABLE_GC_LIVRAISONVENTE = "GC_BONLIVRAISONVENTE";
	public static String TABLE_GC_TAXE = "GC_TAXE";
	public static String TABLE_GC_TAXELIVRAISON = "GC_TAXELIVRAISON";
	
	public static String TABLE_GC_MODEPAIEMENT= "GC_MODEPAIEMENT";
	public static String TABLE_GC_MARCHE = "GC_MARCHE";
	
	public static String TABLE_GC_DETFACTUREVENTE = "GC_DETFACTUREVENTE";
	public static String TABLE_GC_FACTUREVENTE = "GC_FACTUREVENTE";
	public static String TABLE_GC_TAXEFACTURE = "GC_TAXEFACTURE";
	
	public static String TABLE_GC_DETAILSREGLEMENT = "GC_DETAILSREGLEMENT";
	public static String TABLE_GC_ELEMENTREGLEMENT = "GC_ELEMENTREGLEMENT";
	public static String TABLE_GC_REGLEMENT = "GC_REGLEMENT";
	public static String TABLE_GC_TYPEREGLEMENT = "GC_TYPEREGLEMENT";
	public static String TABLE_GC_SOLDECLIENT = "GC_SOLDECLIENT";
	
	/************* Package bonCommande ***************/
	
	public static String TABLE_GC_PRODUITCOMMANDE = "GC_PRODUITCOMMANDE";
	public static String TABLE_GC_COMMANDEVENTE = "GC_COMMANDEVENTE";
	public static String TABLE_GC_ETATCOMMANDE = "GC_ETATCOMMANDE";
	
	/************* Package planning ***************/
	
	public static String TABLE_GC_EVENT = "GC_EVENT";
	public static String TABLE_GC_MOTIF = "GC_MOTIF";
	public static String TABLE_GC_STATUS = "GC_STATUS";
	
	
	/** Liste des séquences */
	public static String SEQUENCE_GC_MET_FAC_SEQ = "MET_FAC_SEQ";
	
	public static String SEQUENCE_GC_CDLV_SEQ = "CDLV_SEQ";
	public static String SEQUENCE_GC_CLV_SEQ = "CBLV_SEQ";
	public static String SEQUENCE_GC_CTA_SEQ = "CTA_SEQ";
	public static String SEQUENCE_GC_CTLV_SEQ = "CTLV_SEQ";
	
	public static String SEQUENCE_GC_CDFV_SEQ = "CDFV_SEQ";
	public static String SEQUENCE_GC_CFV_SEQ = "CFV_SEQ";
	public static String SEQUENCE_GC_CTF_SEQ = "CTF_SEQ";
	
	public static String SEQUENCE_GC_CMA_SEQ = "CMA_SEQ";
	public static String SEQUENCE_GC_CMP_SEQ = "CMP_SEQ";
	
	public static String SEQUENCE_GC_DETAILSREGLEMENT = "CDR_SEQ";
	public static String SEQUENCE_GC_ELEMENTREGLEMENT = "CER_SEQ";
	public static String SEQUENCE_GC_REGLEMENT = "CRG_SEQ";
	public static String SEQUENCE_GC_TYPEREGLEMENT = "CTR_SEQ";
	
	public static final String ORDER_BY_PRODUIT_CODE = "produitCode";
	public static final String ORDER_BY_CHOIX = "choix";

	public static String SEQUENCE_GC_SOLDECLIENT = "CSC_SEQ";
	
	/************* Package bonCommande ***************/
	
	public static final String SEQUENCE_GC_PRODUITCOMMANDE = "CPC_SEQ";
	public static final String SEQUENCE_GC_COMMANDEVENTE = "CCV_SEQ";
	public static final String SEQUENCE_GC_ETATCOMMANDE = "GC_ETAT_COMMANDE_SEQ";
	
/************* Package planning ***************/
	
	public static final String SEQUENCE_GC_EVENT = "GC_EVENT_SEQ";
	public static final String SEQUENCE_GC_MOTIF = "GC_MOTIF_SEQ";
	public static final String SEQUENCE_GC_STATUS = "GC_STATUS_SEQ";
	
	
}
