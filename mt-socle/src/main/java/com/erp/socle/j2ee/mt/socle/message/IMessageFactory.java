package com.erp.socle.j2ee.mt.socle.message;

/**
 * Interface pour les messages.
 * 
 */
public interface IMessageFactory {

  /**
   * Retourne le message suivant le code.
   * 
   * @param code
   *          le code
   * @return Le message
   */
  String getMessage(String code);

  /**
   * Retourne le message suivant le code.
   * 
   * @param code
   *          le code
   * @param arg
   *          le argument
   * 
   * @return Le message
   */
  String getMessage(String code, String arg);

  /**
   * Retourne le message suivant le code.
   * 
   * @param code
   *          le code
   * @param args
   *          les arguments
   * @return Le message
   */
  String getMessage(String code, String[] args);
}
