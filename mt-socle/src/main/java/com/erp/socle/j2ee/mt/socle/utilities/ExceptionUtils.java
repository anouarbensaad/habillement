package com.erp.socle.j2ee.mt.socle.utilities;

import com.erp.socle.j2ee.mt.socle.exception.FonctionnelleException;
import com.erp.socle.j2ee.mt.socle.message.MessageFactory;



public class ExceptionUtils {
	private static final String MESSAGES_PROVIDER_MSG_ERR_FONCT = "Une Erreur technique est survenue lors de la contruction du message d'erreur.";
	private static final String MESSAGES_PROVIDER_MSG_ERR_TECH = "Le Founisseur de messages est null";

	public static String constuireMessageErreur(String code, String[] params,
			Class typeException) {
		MessageFactory messagesFactory = MessageFactory.getInstance();
		String message;
		if (messagesFactory != null) {

			if (null == params) {
				message = messagesFactory.getMessage(code);
			} else
				message = messagesFactory.getMessage(code, params);
		} else {

			if (typeException.equals(FonctionnelleException.class)) {
				message = MESSAGES_PROVIDER_MSG_ERR_FONCT;
			} else {
				message = MESSAGES_PROVIDER_MSG_ERR_TECH;
			}
		}

		return message;
	}
}