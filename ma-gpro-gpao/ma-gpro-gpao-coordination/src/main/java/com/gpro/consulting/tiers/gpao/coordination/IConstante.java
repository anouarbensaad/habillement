package com.gpro.consulting.tiers.gpao.coordination;

// TODO: Auto-generated Javadoc
/**
 * The Interface IConstante.
 */
public interface IConstante {

	/************************* constante *******************************/
	


	/********************* table name ***********************/
	public static String TABLE_GP_ORDREFABRICATION = "gp_ordrefabrication";
	public static String TABLE_GP_DETAILSOF = "gp_detailsof ";
	public static String TABLE_GP_COMPOSITIONOF = "gp_compositionof";
	public static String TABLE_GP_PHASEOF = "gp_phaseof";
	public static String TABLE_GP_CHAINE = "gp_chaine";
	public static String TABLE_GP_STATUTOF = "gp_statutof";
	public static String TABLE_GP_CAUSEVARIATION = "gp_causevariation ";
	
	public static String TABLE_GP_ELEMENTGAMME= "GP_ELEMENTGAMME";
	public static String TABLE_GP_GAMMEPRODUIT= "GP_GAMMEPRODUIT";
	public static String TABLE_GP_MACHINE= "GP_MACHINE";
	public static String TABLE_GP_OPERATION= "GP_OPERATION";
	public static String TABLE_GP_SECTION= "GP_SECTION";
	public static String TABLE_GP_PRODUCTIONJOUR ="GP_PRODUCTIONJOUR" ;
	
	public static String TABLE_GP_ELEMENTGAMMEOF= "GP_ELEMENTGAMMEOF";
	public static String TABLE_GP_GAMMEOF= "GP_GAMMEOF";
	
	public static String TABLE_GP_ABCARTICLEDETAILETAPEJOUR = "GP_ABCARTICLEDETAILETAPEJOUR";
	
	public static String TABLE_GP_PRODUCTION = "GP_PRODUCTION";
	public static String TABLE_GP_PRODUCTION_ELEMENT = "GP_PRODUCTIONELEMENT";
	
	public static String TABLE_GP_FICHEECLATEMENT= "GP_FICHEECLATEMENT";
	public static String TABLE_GP_PAQUET= "GP_PAQUET";
	
	public static String TABLE_GP_FEUILLE_SAISIE = "GP_FEUILLESAISIE";
	public static String TABLE_GP_ELEMENT_SAISIE = "GP_ELEMENTSAISIE";
	
	public static String TABLE_GP_ELEMENT_ALEAS = "GP_ELEMENTALEAS";
	public static String TABLE_GP_ALEAS = "GP_ALEAS";
	public static String TABLE_GP_FAMILLE_ALEAS = "GP_FAMILLEALEAS";

	
	public static String TABLE_GP_PERSONNEL = "GP_PERSONNEL";
	
	
	public static String TABLE_GP_FICHECOLISAGE= "GP_FICHECOLISAGE";
	public static String TABLE_GP_COLIS= "GP_COLIS";
	public static String TABLE_GP_DETAILS_COLIS= "GP_DETAILS_COLIS";
	
	public static String TABLE_GP_MOUVEMENT_FINI= "GP_MOUVEMENT_FINI";
	
	/******************** sequence name *********************/
	public static String SEQUENCE_GP_ORDREFABRICATION = "pof_seq";
	public static String SEQUENCE_GP_DETAILSOF = "pdo_seq";
	public static String SEQUENCE_GP_COMPOSITIONOF = "pco_seq";
	public static String SEQUENCE_GP_PHASEOF = "ppof_seq";
	public static String SEQUENCE_GP_CHAINE = "pch_seq";
	public static String SEQUENCE_GP_STATUTOF = "pso_seq";
	public static String SEQUENCE_GP_CAUSEVARIATION = "pcv_seq";
	public static String SEQUENCE_GP_PRODUCTIONJOUR="PPJ_SEQ";
	
	public static String SEQUENCE_GP_ELEMENTGAMME= "PEG_SEQ";
	public static String SEQUENCE_GP_GAMMEPRODUIT= "PGP_SEQ";
	public static String SEQUENCE_GP_MACHINE= "PMA_SEQ";
	public static String SEQUENCE_GP_OPERATION= "POP_SEQ";
	public static String SEQUENCE_GP_SECTION= "PSE_SEQ";
	
	public static String SEQUENCE_GP_ELEMENTGAMMEOF= "PEGO_SEQ";
	public static String SEQUENCE_GP_GAMMEOF= "PGOF_SEQ";
	
	public static String SEQUENCE_GP_ABCARTICLEDETAILETAPEJOUR = "ABCADEJ_SEQ";
	
	public static String SEQUENCE_GP_PRODUCTION = "PROD_SEQ";
	public static String SEQUENCE_GP_PRODUCTION_ELEMENT = "PRODELM_SEQ";
	
	public static String SEQUENCE_GP_FICHEECLATEMENT= "PFE_SEQ";
	public static String SEQUENCE_GP_PAQUET= "PPA_SEQ";
	
	public static String SEQUENCE_GP_FEUILLE_SAISIE = "PFS_SEQ";
	public static String SEQUENCE_GP_ELEMENT_SAISIE = "PEFS_SEQ";
	
	public static String SEQUENCE_GP_ELEMENT_ALEAS = "PEA_SEQ";
	public static String SEQUENCE_GP_ALEAS = "PAL_SEQ";
	public static String SEQUENCE_GP_FAMILLE_ALEAS = "PFA_SEQ";

	public static String SEQUENCE_GP_PERSONNEL = "PPS_SEQ";
	
	public static String SEQUENCE_GP_FICHECOLISAGE= "PFC_SEQ";
	public static String SEQUENCE_GP_COLIS= "PCO_SEQ";
	public static String SEQUENCE_GP_DETAILS_COLIS= "PDC_SEQ";
	
	public static String SEQUENCE_GP_MOUVEMENT_FINI= "PMVF_SEQ";
	
	
	public static String SCP = "SCP";		//Sortie Coupe
	public static String ENG = "ENG";		//Engagement
	public static String SCH = "SCH";		//Sortie Chaine
	public static String EXP = "EXP";		//Export
	public static String ECL = "ECL";		//Eclatemenet
	public static String MCOL= "MCOL";		//Mise En Colis
	public static String SCO = "SCO";		//Sortie Conditionnement

	// OperationId for Reporting 
	public static Long SCPID =49L;		//Sortie Coupe
	public static Long ENGID = 39L;		//Engagement
	public static Long SCHID = 41L;		//Sortie Chaine
	//public static Long EXPID = "EXP";		//Export
	public static Long ECLID = 28L;		//Eclatemenet
	public static Long MCOLID= 30L;		//Mise En Colis
	public static Long SCOID = 45L;		//Sortie Conditionnement 


}