package com.gpro.consulting.tiers.commun.coordination;

public interface IConstante {
  
   /********************Constante **************************/
	public static String OUI="oui";
	public static String NON="non";
	
  /********************* table name ***********************/
  public static String TABLE_EB_COULEUR = "eb_couleur";
  public static String TABLE_EB_PRODUIT_COULEUR = "eb_produitcouleur";
  public static String TABLE_EB_STANDARD_TAILLE = "eb_standardtaille";
  public static String TABLE_EB_TAILLE = "eb_taille";
  public static String TABLE_EB_PRODUIT_TAILLE = "eb_produittaille";
  public static String TABLE_EB_PHASE = "eb_phase";
  public static String TABLE_EB_PHASE_PRODUIT = "eb_phaseproduit";
  public static String TABLE_DETAILS_PRODUIT = "eb_produitdetails";
  
  public static String TABLE_DOCUMENT_PRODUIT="eb_documentprod";
  public static String TABLE_FAMILLE_PRODUIT="eb_familleprod";
  public static String TABLE_PRODUIT="eb_produit";
  public static String TABLE_SOUS_FAMILLE_PRODUIT="eb_sousfamilleprod";
  public static String TABLE_DETAILS_PRIX_PRODUIT="eb_detailsprix";
  
  public static String TABLE_EB_DETPROD_ARTICLE = "eb_detprodarticle";
  public static String TABLE_EB_GROSSEUR = "eb_grosseur";
  public static String TABLE_EB_MATIERE = "eb_matiere";
  public static String TABLE_EB_METRAGE = "eb_metrage";
  public static String TABLE_FAMILLE_ARTICLE = "eb_familleart";
  public static String TABLE_SEUIL = "eb_seuil";
  public static String TABLE_SOUS_FAMILLE = "eb_sousfamilleart";
  public static String TABLE_TYPE_ARTICLE = "eb_typearticle";
  public static String TABLE_UNITE = "eb_unite";

  public static String TABLE_PARTIE_INTERESSEE = "pi_partieint";
  public static String TABLE_CATEHGORIE_PARTIE_INTERESSEE = "pi_categorie";
  public static String TABLE_TYPE_PARTIE_INTERESSEE = "pi_typepi";
  public static String TABLE_SITE_PARTIE_INTERESSEE = "pi_com_site";
  public static String TABLE_FAMILLE_PARTIE_INTERESSEE = "pi_famillepi";
  public static String TABLE_DOCUMENT = "pi_document";
  public static String TABLE_REPRESENTANT = "pi_representant";
  public static String TABLE_TYPE_DOCUMENT = "pi_com_typedoc";
  
  public static String TABLE_ARTICLE="eb_article";
  public static String TABLE_DOCUMENT_ARTICLE="eb_documentart";
  public static String TABLE_DEVISE="pi_devise";
  
  public static String TABLE_Fiche_BESOIN="eb_fichebesoin";
  public static String TABLE_ELEMENT_Fiche_BESOIN="eb_elementfichebesoin";
  
  public static String TABLE_EB_BASEINFO="EB_BASEINFO";
  
  public static String TABLE_EB_USER = "EB_USER";

  /*********************** sequence name ***********************/
  public static String SEQUENCE_DOCUMENT_PRODUIT="efp_seq";
  public static String SEQUENCE_FAMILLE_PRODUIT="efp_seq";
  public static String SEQUENCE_PRODUIT="epr_seq";
  public static String SEQUENCE_SOUS_FAMILLE_PRODUIT="esf_seq";
  public static String SEQUENCE_DETAILS_PRIX_PRODUIT="epx_seq";
  public static String SEQUENCE_PHASE_PRODUIT="epp_seq";
  
  public static String SEQUENCE_EB_GROSSEUR = "egr_seq";
  public static String SEQUENCE_EB_MATIERE = "emt_seq";
  public static String SEQUENCE_EB_METRAGE = "emg_seq";
  public static String SEQUENCE_EB_COULEUR = "ecl_seq";
  public static String SEQUENCE_EB_PRODUIT_COULEUR = "epc_seq";
  public static String SEQUENCE_EB_STANDARD_TAILLE = "est_seq";
  public static String SEQUENCE_EB_TAILLE = "etl_seq";
  public static String SEQUENCE_EB_PRODUIT_TAILLE = "ept_seq";
  public static String SEQUENCE_EB_PHASE = "eph_seq";
  public static String SEQUENCE_EB_DETPROD_ARTICLE = "epa_seq";
  
  public static String SEQUENCE_ARTICLE="eb_article_id_seq";
  public static String SEQUENCE_DOCUMENT_ARTICLE="ear_seq";
  
  public static String SEQUENCE_PARTIE_INTERESSEE = "seq_ppi";
  public static String SEQUENCE_FAMILLE_PARTIE_INTERESSEE = "seq_pfp";
  public static String SEQUENCE_TYPE_PARTIE_INTERESSEE = "seq_ptp";
  public static String SEQUENCE_CATEHGORIE_PARTIE_INTERESSEE = "seq_pcg";
  public static String SEQUENCE_SITE_PARTIE_INTERESSEE = "seq_pcs";
  public static String SEQUENCE_DOCUMENT = "seq_pdc";
  public static String SEQUENCE_REPRESENTANT = "seq_prp";
  public static String SEQUENCE_TYPE_DOCUMENT = "seq_pct";

  public static String SEQUENCE_FAMILLE_ARTICLE = "efa_seq";
  public static String SEQUENCE_SEUIL = "esu_seq";
  public static String SEQUENCE_SOUS_FAMILLE = "sfa_seq";
  public static String SEQUENCE_TYPE_ARTICLE = "eta_seq";
  public static String SEQUENCE_UNITE = "eun_seq";
  public static String SEQUENCE_DEVISE = "pdv_seq";
  
  public static String SEQUENCE_FICHE_BESOIN="fb_seq";
  public static String SEQUENCE_ELEMENT_FICHE_BESOIN="efb_seq";
  
  public static String SEQUENCE_EB_BASEINFO="EBBI_SEQ";
  
  public static String SEQUENCE_EB_USER = "EBUSR_SEQ";
  
  
  /*************** LOGO PATH ********************/
  
  public static String LOGO_BASE_URL="C:/ERP/logos_clients/";
  
  /************************************* Rapports *******************/
	
  public static String RAPPORTS_COMMUN_BASE_URL = "C:/ERP/Lib/Confection/commun/";
  
}
