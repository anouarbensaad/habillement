package com.erp.socle.j2ee.mt.socle.trace;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.message.MessageFactory;




/**
 * Classe permettant de construire les traces techniques et fonctionnelles
 * 
 * @author rkhaskhoussy
 * 
 */
public class GestionnaireTraces implements IGestionnaireTraces {
	/** Logger Technique */
	private final Logger logTech = Logger.getLogger(ILoggers.TECHNIQUE);
	/** Logger Fonctionnel */
	private final Logger logFonc = Logger.getLogger(ILoggers.FONCTIONNEL);

	/**
	 * Instanciation du gestionnaire de trace
	 */
	private static GestionnaireTraces instance = new GestionnaireTraces();

	/**
	 * Méthode  permettant l'accés au Gestionnaire de trace 
	 * 
	 * @return
	 */
	public static GestionnaireTraces getInstance() {
		return instance;
	}
	/**
	 *  Service Permettant l'accées au logger technique 
	 *  (non-Javadoc)
	 * 
	 */
	public Logger getLogTech() {
		return getInstance().logTech;
	}

	/**
	 * Methode permettant la récupération du message correspondant au loggeur
	 *  (non-Javadoc)
	 */
	public String getMessage(String code, String[] params) {
		return MessageFactory.getInstance().getMessage(code, params);
	}

	/**
	 *  Service Permettant l'accées au logger focntionnel
	 *  (non-Javadoc)
	 */
	public Logger getLogFonc() {
		return getInstance().logFonc;
	}

	
}