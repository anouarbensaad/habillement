package com.erp.socle.j2ee.mt.socle.trace;

import org.apache.log4j.Logger;

/**
 * Interface du gestionnaire du log
 * 
 * @author rkhaskhoussy
 * 
 */
public interface IGestionnaireTraces {

	/**
	 * Construit un message répertorié dans un fichier de propriété avec les paramètres appropriés
	 * 
	 * @param paramString
	 * @param paramArrayOfString
	 * @return
	 */
	public abstract String getMessage(String code,
			String[] params);

	/**
	 * Logger Technique
	 * @return
	 */
	public abstract Logger getLogTech();

	/**
	 * Logger Fonctionnel
	 * @return
	 */
	public abstract Logger getLogFonc();

	public static abstract interface ILoggers {
		public static final String TECHNIQUE = "logmodulesGPRO.technique";
		public static final String FONCTIONNEL = "logmodulesGPRO.fonctionnel";
	}

}
