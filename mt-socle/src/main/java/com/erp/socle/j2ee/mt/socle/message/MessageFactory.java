package com.erp.socle.j2ee.mt.socle.message;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import com.erp.socle.j2ee.mt.socle.trace.GestionnaireTraces;


/**
 * Factory des messages.
 * 
 * 
 */
public final class MessageFactory implements IMessageFactory {

	private static final String MSG_INTROUVAVBLE = "Le code du message  n'est pas référencié dans le fichier des messages : ";

	/** La source des messages. */
	private MessageSource messageSource;

	/** Instance de Message Factory */
	private static MessageFactory instance = new MessageFactory();

	/**
	 * 
	 * Singleton pour acceder à une instance de MessageFactory
	 * 
	 * @return
	 */
	public static MessageFactory getInstance() {
		return instance;
	}

	/**
	 * Initialise l'attribut <code>messageSource</code>.
	 * 
	 * @param messageSource
	 *            la source des messages
	 * @org.apache.xbean.Property 
	 *                            description="Message ressources fourni par Spring"
	 */
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * {@inheritDoc}
	 */

	public String getMessage(final String code) {
		return getMessage(code, (String[]) null);
	}

	/**
	 * {@inheritDoc}
	 */

	public String getMessage(final String code, final String arg) {
		return getMessage(code, new String[] { arg });
	}

	/**
	 * {@inheritDoc}
	 */

	public String getMessage(final String code, final String[] args) {
		String message= null;
		try{
						
			message= this.messageSource.getMessage(code, args, Locale.getDefault());
		} catch (NoSuchMessageException e)
	    {
		      GestionnaireTraces.getInstance().getLogTech().warn(MSG_INTROUVAVBLE + code, e.getCause());
		      return MSG_INTROUVAVBLE + code;
		    }
		return message;
	}
	
}
