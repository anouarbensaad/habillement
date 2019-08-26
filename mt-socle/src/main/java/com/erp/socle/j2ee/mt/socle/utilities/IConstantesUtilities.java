package com.erp.socle.j2ee.mt.socle.utilities;

/**
 * Classe de constantes.
 * 
 * @author $Author: Ridha KHASKHOUSSY
 * 
 */

public interface IConstantesUtilities {
	/** Emplacement du resource bundle dans les composants techniques. */
	  String PATH_RESOURCE_BUNDLE = "META-INF/{0}/messages";

	  /** Préfixe des packages des composants techniques. */
	  String PREFIX_PACKAGE_MT = "com.erp.socle.j2ee.mt";

	  /** Emplacement du fichier manifest.mf dans les modules applicatifs. */
	  String META_INF_MANIFEST = "/META-INF/MANIFEST.MF";
	  /** Clé du nom du module applicatif dans le manifest. */
	  String ARTIFACTID = "ArtifactId";
	  /** Clé du version du module applicatif dans le manifest. */
	  String VERSION = "version";

	  // Les constantes d'erreur
	  /** Erreur dans le chargement du resource bundle. */
	  String ERR_CHARGEMENT_RESOURCE_BUNDLE = "mt.socle.err.001";
	  /** Erreur lors du marshalling jaxb. */
	  String ERR_MARSHALLING_XML = "mt.socle.err.002";
	  /** Erreur lors du marshalling jaxb. */
	  String ERR_UNMARSHALLING_XML = "mt.socle.err.003";
	  /** Erreur dans l''acces au service : {0}. */
	  String ERR_APPEL_SERVICE = "mt.socle.err.200";
	  /** Erreur Générale, Veuillez prendre contact avec votre administrateur. */
	  String ERR_GENERALE_SERVEUR = "mt.socle.err.500";
	  /** Erreur de connexion au web service. */
	  String ERR_APPEL_CLIENT_SERVICE = "mt.socle.err.400";
	  /** Serveur inconnu. */
	  String ERR_APPEL_HOST_INCONNU = "mt.socle.err.401";
	  /** Erreur IO/ Endpoint inconnu / endpoint non deployé. */
	  String ERR_APPEL_IO_SERVICE = "mt.socle.err.402";
	  /** Timeoutlors de l'appel du service. */
	  String ERR_APPEL_TIMEOUT_SERVICE = "mt.socle.err.403";
	  /** chaine vide. */
	  public static String CHAINE_VIDE = "";
	  
	  /** Prefixe de tous les package de l'appli. */
	  String PACKAGE_NAME = "com.erp";
	  /** Suffixe des classes implémentations. */
	  String IMPL_SUFFIX = "Impl";

	  /** Erreur dans l''acces a la base de donnees : {0}. */
	  String ERR_APPEL_BD = "mt.commun.err.100";
	  /** Erreur de conccurrence d'accès. */
	  String ERR_CONCURRENCE_ACCES = "mt.commun.err.102";
	  /** Erreur de conccurrence d'accès. */
	  String ERR_NOT_ENTITY = "mt.commun.err.103";

	  /**
	   * La variable de session représentant l'identifiant present dans le header HTTP qui transmet les
	   * infos au MAS.
	   */
	  String HTTP_HEADER = "generic_id";
}
