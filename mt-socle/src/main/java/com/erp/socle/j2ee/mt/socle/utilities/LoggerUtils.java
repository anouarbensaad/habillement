package com.erp.socle.j2ee.mt.socle.utilities;

/**
 * Utilitaire pour la gestion des logs
 * 
 * @author rkhaskhoussy
 *
 */
public final class LoggerUtils {

	/**
	 * Méthode permettant de créer une ligne du Log pour les régles de gestion
	 * 
	 * @param pNumeroRegle
	 * @param pDatatIn
	 * @param pDataOut
	 * @param pTypeLog
	 * @param pMessageLog
	 */
	public static  String printLog(String pNumeroRegle, String pDatatIn, String pDataOut,String pTypeLog, String pMessageLog){
		
		StringBuffer vLog=new StringBuffer("");
		// Numero de la Régle
		if (!StringUtils.isStringEmptyOrNull(pNumeroRegle)) {

			vLog.append("RG#<");
			vLog.append(pNumeroRegle);
			vLog.append(">/");
		}
		//Données en entrée
		if(!StringUtils.isStringEmptyOrNull(pDatatIn)){
			vLog.append("DATAIN :<");
			vLog.append(pDatatIn);
			vLog.append("> /");
		}
		//Donnéees en sortie
		if(!StringUtils.isStringEmptyOrNull(pDataOut)){
			vLog.append("DATAOUT :<");
			vLog.append(pDataOut);
			vLog.append("> /");
		}
		//Type Erreur
		if(!StringUtils.isStringEmptyOrNull(pTypeLog)){
			vLog.append(pTypeLog);
			vLog.append(":<");
		}
		//Message Log
		if(!StringUtils.isStringEmptyOrNull(pMessageLog)){
			vLog.append(pMessageLog);
			vLog.append(">");
		}else{
			vLog.append(">");
		}
		
		return vLog.toString();
	}
}
