package com.gpro.consulting.tiers.gpao.coordination;

/**
 * Interface présentant les noms des tables, des séquences, des constantes ...
 * utilisées dans le module Logistique
 * 
 * @author $Author: rkhaskhoussy $
 * @version $Revision: 0 $
 */
public interface IConstanteLogistique {

	/** Liste des Tables */
	// Table Bon de reception
	public static String TABLE_BON_RECEPTION = "gl_bonreception";
	// Tale Machine
	public static String TABLE_MACHINE = "gl_machine";
	// Tale RouleauEcru
	public static String TABLE_ROULEAUECRU = "gl_rouleauecru";
	// Tale Traitement
	public static String TABLE_TRAITEMENT = "gl_traitement";
	// Tale ReceptionTraitement
	public static String TABLE_RECEPTIONTRRAITEMENT = "gl_receptiontraitement";

	// Table GL_ENTREPOT
	public static String TABLE_GL_ENTREPOT = "GL_ENTREPOT";
	// Tale GL_ROULEAUFINI
	public static String TABLE_GL_ROULEAUEFINI = "GL_ROULEAUFINI";

	// Table de guichet bon reception
	public static String TABLE_GENERATEUR_BON_RECEPTION = "gl_generateurbr";

	// Table de guichet bon reception
	public static String TABLE_GENERATEUR_ROULEAU = "gl_generateurrouleau";
	// Table Mise
	public static String TABLE_MISE = "gl_mise";
	// Table TraitementMise
	public static String TABLE_TRAITEMENTMISE = "gl_traitementmise";

	// Table de guichet mise
	public static String TABLE_GENERATEUR_MISE = "gl_generateurmise";

	// Table choix rouleau
	public static String TABLE_GL_CHOIX_ROULEAU = "gl_choixrouleau";
	
	// Table Bon Sortie Fini
	public static String TABLE_GL_BONSORTIEFINI = "GL_BONSORTIEFINI";
	
	public static String TABLE_GL_CONTROLE = "GL_CONTROLE";
	public static String TABLE_GL_ELEMENTCONTROLE = "GL_ELEMENTCONTROLE";
	public static String TABLE_GL_ELEMENTRECETTEMISE = "GL_ELEMENTRECETTEMISE";
	public static String TABLE_GL_FICHESUIVEUSEMISE = "GL_FICHESUIVEUSEMISE";

	// Table Generatteur 

	public static String TABLE_GENERATEUR_MENSUEL = "gc_generateurmensuel";

	public static String TABLE_GENERATEUR_ANNUEL= "gc_generateurannuel";

	// Table fiche façon
	public static String TABLE_FICHE_FACON = "gl_fiche_facon";
	
	// Table traitement fiche
	public static String TABLE_TRAITEMENT_FICHE = "gl_traitement_fiche";
	
	// Table traitement fiche
	public static String TABLE_TRAITEMENT_FACON = "gl_traitement_facon";
	
	// Table methode teinture
	public static String TABLE_METHODE_TEINTURE = "gl_methode_teinture";

	
	
	/************************** Liste des séquences *************************/
	
	// Séquence Bon Réception
	public static String SEQUENCE_BON_RECEPTION = "lbr_seq";
	// Séquence Machine
	public static String SEQUENCE_MACHINE = "lma_seq";
	// Séquence RouleauEcru
	public static String SEQUENCE_ROULEAUECRU = "lre_seq";
	// Séquence Traitement
	public static String SEQUENCE_TRAITEMENT = "ltm_seq";
	// Séquence ReceptionTraitement
	public static String SEQUENCE_RECEPTIONTRAITEMENT = "lrt_seq";
	// Séquence ENTREPOT
	public static String SEQUENCE_GL_ENTREPOT = "LEN_SEQ";
	// Séquence ROULEAUEFINI
	public static String SEQUENCE_GL_ROULEAUFINI = "LRF_SEQ";

	// Séquence Mise
	public static String SEQUENCE_MISE = "lmi_seq";
	// *séquence TraitementMise
	public static String SEQUENCE_TRAITEMENTMISE = "lmt_seq";
	
	// Séquence Choix Rouleau
	public static String SEQUENCE_GL_CHOIX_ROULEAU = "LCR_SEQ";
	
	// Séquence BON SORTIE FINI
	public static String SEQUENCE_GL_BONSORTIEFINI = "LBSF_SEQ";
	
	public static String SEQUENCE_GL_CONTROLE = "LCO_SEQ";
	public static String SEQUENCE_GL_ELEMENTCONTROLE = "LEC_SEQ";
	public static String SEQUENCE_GL_ELEMENTRECETTEMISE = "LER_SEQ";
	public static String SEQUENCE_GL_FICHESUIVEUSEMISE = "LFS_SEQ";
	
	// Facon
	public static String SEQUENCE_GL_FICHE_FACON = "LFF_SEQ";
	public static String SEQUENCE_GL_TRAITEMENT_FICHE = "LTFC_SEQ";
	public static String SEQUENCE_GL_TRAITEMENT_FACON = "LTF_SEQ";
	
	//Methode teinture
	public static String SEQUENCE_GL_METHODE_TEINTURE = "LMET_SEQ";
	
	public static String YES = "oui";
	public static String NO = "non";
	public static String ALL = "tous";
	
	public static String ORDER_BY_REFERENCE = "reference";
	public static String ORDER_BY_METRAGE = "metrage";
	public static String ORDER_BY_PRIX_UNITAIRE = "prixUnitaire";
	
	public static Long GPRO_CLIENT_ID = 1L;
   	
	// Table Bon Inventaire Fini
		public static String TABLE_GL_BONINVENTAIREFINI = "GL_BONINVENTAIREFINI";
		// Séquence BON Inventaire FINI
		public static String SEQUENCE_GL_BONINVENTAIREFINI = "LBIF_SEQ";
		
	
		public static String TYPE_INJECTION = "Injection";
		public static String TYPE_PAINTING = "Tempographie";
		public static String TYPE_ASSEMBLAGE = "Assemblage";
}
