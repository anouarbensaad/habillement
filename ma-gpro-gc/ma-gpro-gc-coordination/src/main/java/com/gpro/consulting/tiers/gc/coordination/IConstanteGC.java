package com.gpro.consulting.tiers.gc.coordination;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConstante.
 */
public interface IConstanteGC {

	/************************* constante *******************************/
	

	/********************* table name ***********************/
	// Tables CommandeVente
	public static String TABLE_GC_COMMANDEVENTE = "gc_commandevente";
	public static String TABLE_GC_DOCUMENTCOMMANDE = "gc_documentcommande";
	public static String TABLE_GC_PRODUITCOMMANDE = "gc_produitcommande";
	public static String TABLE_GC_TYPECOMMANDE = "gc_typecommande";
	public static String TABLE_GC_ETATCOMMANDE = "gc_etatcommande";
	public static String TABLE_GC_DETAILPRODUITCOMMANDE = "gc_detailproduitcommande";
	
	//Tables CommandeAchat
	public static String TABLE_GC_COMMANDEACHAT = "gc_commandeachat";
	public static String TABLE_GC_DOCUMENTCOMMANDEACHAT = "gc_documentcommandeachat";
	public static String TABLE_GC_ELEMENTCOMMANDE = "gc_elementcommandeachat";
	
	//Table LivraisonCommande
	public static String TABLE_GC_LIVRAISONVENTE = "gc_livraisonvente ";
	public static String TABLE_GC_PRODUITLIVRAISON = "gc_produitlivraison ";
	public static String TABLE_GC_DETAILPRODUITLIVRAISON = "gc_detailproduitlivraison";
	
	//Table FactureVente
	public static String TABLE_GC_FACTUREVENTE = "gc_facturevente ";
	public static String TABLE_GC_PRODUIT_FACTURE_VENTE = "gc_produitfacture ";
	public static String TABLE_GC_DIVERS_FACTURE_VENTE = "gc_diversfacture ";
	
	//Table Agent
	public static String TABLE_GC_AGENT = "gc_agent ";
	/******************** sequence name *********************/
	//Sequances CommandeVente
	public static String SEQUENCE_GC_COMMANDEVENTE = "ccv_seq";
	public static String SEQUENCE_GC_DOCUMENTCOMMANDE = "cdc_seq";
	public static String SEQUENCE_GC_PRODUITCOMMANDE = "cpc_seq";
	public static String SEQUENCE_GC_TYPECOMMANDE = "ctc_seq";
	public static String SEQUENCE_GC_ETATCOMMANDE = "cec_seq";
	public static String SEQUENCE_GL_DETAILPRODUITCOMMANDE = "cdpc_seq";
	
	//Sequences CommandeAchat
	public static String SEQUENCE_GC_COMMANDEACHAT = "cca_seq";
	public static String SEQUENCE_GC_DOCUMENTCOMMANDEACHAT = "cdca_seq";
	public static String SEQUENCE_GC_ELEMENTCOMMANDE = "ceca_seq";
	
	//Sequences LivraisonCommande
	public static String SEQUENCE_GC_LIVRAISONCOMMANDE = "clv_seq";
	public static String SEQUENCE_GC_PRODUITLIVRAISON = "cpl_pk";
	public static String SEQUENCE_GC_DETAILPRODUITLIVRAISON = "cdpl_seq";
	
	//Sequence FactureVente
	public static String SEQUENCE_GC_FACTUREVENTE = "cfv_seq";
	public static String SEQUENCE_GC_PRODUIT_FACTURE_VENTE = "cpf_seq";
	public static String SEQUENCE_GC_DIVERS_FACTURE_VENTE = "cdfv_seq";

	//Sequence Agent
	public static String SEQUENCE_GC_AGENT = "cag_seq";
	
	/************************************* Rapports *******************/
	
	public static String RAPPORTS_GC_BASE_URL = "C:/ERP/Lib/Confection/gc/";

	 /*************** LOGO PATH ********************/
	  
	  public static String LOGO_BASE_URL="C:/ERP/logos_clients/";
	  
	  /************************************* Rapports *******************/
		
	  public static String RAPPORTS_COMMUN_BASE_URL = "C:/ERP/Lib/Confection/commun/";
}