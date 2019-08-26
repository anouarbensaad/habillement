package com.gpro.consulting.tiers.gpao.coordination;

/**
 * Constant Report parameters
 * 
 * @author Wahid Gazzah
 * @since 10/08/2016
 *
 */
public interface IConstanteReport {

	public static final String REPORT_NAME_ORDRE_FABRICATION_LIST = "Liste des ordres de fabrication";
	public static final String REPORT_NAME_GAMME_PRODUIT_LIST = "Liste des gammes produits";
	public static final String REPORT_NAME_RESERVATIONPRODUIT = "List des reservations produit";
	public static final String REPORT_NAME_GAMME_OF_LIST = "List des gammes d'ordres de fabrication";
	public static final String REPORT_NAME_GAMMEPRODUIT = "gamme Produit";
	public static final String REPORT_NAME_GAMME_OF = "gamme OF";
	public static final String REPORT_NAME_FICHESUIVEUSE = "Fiche Suiveuse";
	
	
	public static final String REPORT_NAME_COLIS = "Colis";

	public static final String REPORT_NAME_FICHEDEPARTAGE = "Fiche Departage";
	public static final String REPORT_NAME_FICHEPAQUETS = "Fiche Paquets";
	public static final String REPORT_NAME_FICHE_SAISIE_LIST = "Fiche Fiche saisie";
	public static final String REPORT_NAME_F_SAISIE_PRODUCTIVITE_GLOBALE_LIST = "Productivité Globale";

	public static final String REPORT_NAME_ECLATEMENT_LIST = "Fiche Eclatement";
	public static final String REPORT_NAME_OPERATION_LIST = "Catalogue de Temps";
	public static final String REPORT_NAME_ABCARTICLEDETAILETAPEJOUR_LIST = "Rapport de Production";
	public static final String REPORT_NAME_CHART_PRODUCTION_LIST = "Rapport de Variation de Production";

	public static final String REPORT_NAME_RECAP_PRODUCTION = "Recap Production";
	public static final String REPORT_NAME_PRODUCTION = "Suivi de Production";

	public static final String PATH_LOGO = "PATH_LOGO";
	public static final String LOGO_EMILE = "/report/logo_emile.png";
	public static final String LOGO_ENFAVET = "/report/logo_enfavet.png";

	public static final String PATH_JRXML_FICHESUIVEUSE = "C://ERP/Lib/EMILE_FicheSuiveuse/fiche_suiveuse_report.jrxml";
	public static final String PATH_JRXML_FICHEDEPARTAGE = "C://ERP/Lib/EMILE_FicheDepartage/fiche_departage_report.jrxml";
	public static final String PATH_JRXML_FICHEPAQUETS = "C://ERP/Lib/EMILE_FichePaquets/fiche_paquets_report.jrxml";
	public static final String PATH_JRXML_PRODUCTION_GLOBALE = "C://ERP/Lib/EMILE_Production/globale/production_globale_report.jrxml";
	public static final String PATH_JRXML_PRODUCTION_CHAINE = "C://ERP/Lib/EMILE_Production/chaine/production_chaine_report.jrxml";
	public static final String PATH_JRXML_OPERATEUR_COMPETENCE = "C://ERP/Lib/Reporting/RapportOperateur/competence/operateur_competence_report.jrxml";
	public static final String REPORT_NAME_F_OPERATEUR_HISTORIQUE = "C://ERP/Lib/Reporting/RapportOperateur/historique/f_operateur_historique_report.jrxml";
	public static final String REPORT_QTE_PAR_PRODUIT = "C://ERP/Lib/gpao/chartReport/parProduit/repartition_Qte_par_client_report.jrxml";
	public static final String REPORT_QTE_PAR_CLIENT = "C://ERP/Lib/gpao/chartReport/parClient/repartition_Qte_par_produit_report.jrxml";
	public static final String REPORT_QTE_PAR_SFAMILLE = "C://ERP/Lib/gpao/chartReport/parSFamille/repartition_Qte_par_Sfamille_report.jrxml";
	public static final String REPORT_RECAP_PRODUCTION_CHAINE = "C://ERP/Lib/Reporting/RapportChaine/recapProdChaine/recap_production_report.jrxml";
	public static final String REPORT_ALEA = "Rapport Aléa";

	public static final String REPORT_NAME_AVANCEMENT_OF = "Avancement OF";
	public static final String REPORT_NAME_RAPPORT_CHAINE_COMPTAGE = "Comptage";
	public static final String REPORT_NAME_PRODUCTION_GLOBALE = "Production Globale";
	public static final String REPORT_NAME_PRODUCTION_CHAINE = "Production Chaine";
	public static final String REPORT_NAME_CHAINE_GLOBAL_OF = "Rapport Production / OF";
	public static final String REPORT_NAME_CHAINE_JOUR_OF = "Rapport Production Journalier/ OF";

	public static final Long ZERO = 0L;
	public static final Double ZEROD = 0D;
	public static final int FIRST_INDEX = 0;
	
		
	/************************************* Rapports *******************/
		
	public static String RAPPORTS_GPAO_BASE_URL = "C:/ERP/Lib/Confection/gpao/";
		
}
