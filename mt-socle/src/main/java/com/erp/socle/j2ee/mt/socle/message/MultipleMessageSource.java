package com.erp.socle.j2ee.mt.socle.message;

import java.io.IOException;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.erp.socle.j2ee.mt.socle.exception.TechniqueException;


/**
 * 
 * Classe Utilitaire permettant la gestion des message multiple
 * 
 * @author rkhaskhoussy
 * 
 */
public class MultipleMessageSource extends
		ReloadableResourceBundleMessageSource {
	/** Suffixe des fichiers properties */
	private static final String PROPERTIES_SUFFIX = ".properties";
	
	/** Message d'erreur */
	private static final String ERREUR_AGREAGATION_RESSOURCES = "Erreur lors du chargement/agregation des fichiers de messages d'erreurs";
	
	/** Resolver Path */
	private final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	/**
	 * Méthode permettant de rafraichire les fichiers properties en cas de
	 * modification (non-Javadoc)
	 * 
	 * @see org.springframework.context.support.ReloadableResourceBundleMessageSource#refreshProperties(java.lang.String,
	 *      org.springframework.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder)
	 */
	protected ReloadableResourceBundleMessageSource.PropertiesHolder refreshProperties(
			String filename,
			ReloadableResourceBundleMessageSource.PropertiesHolder propHolder) {
		Properties properties = new Properties();
		long lastModified = -1L;
		try {
			Resource[] resources = this.resolver.getResources(filename
					+ PROPERTIES_SUFFIX);
			for (Resource resource : resources) {
				String sourcePath = resource.getURI().toString()
						.replace(PROPERTIES_SUFFIX, "");
				ReloadableResourceBundleMessageSource.PropertiesHolder holder = super
						.refreshProperties(sourcePath, propHolder);
				properties.putAll(holder.getProperties());
				if (lastModified < resource.lastModified())
					lastModified = resource.lastModified();
			}
		} catch (IOException e) {
			throw new TechniqueException(
					ERREUR_AGREAGATION_RESSOURCES,
					e);
		}
		return new ReloadableResourceBundleMessageSource.PropertiesHolder(
				properties, lastModified);
	}
	
	 
  /**
   * Calculate all filenames for the given bundle basename and Locale.
   * Will calculate filenames for the given Locale, the system Locale
   * (if applicable), and the default file.
   * @param basename the basename of the bundle
   * @param locale the locale
   * @return the List of filenames to check
   * @see #setFallbackToSystemLocale
   * @see #calculateFilenamesForLocale
   */
  @Override
  protected List<String> calculateAllFilenames(String basename, Locale locale) {
    
    
    List<String> filenames  = calculateFilenamesForLocale( basename,  locale);

    return filenames;
  }
  
  /**
   * Calculate the filenames for the given bundle basename and Locale,
   * appending language code, country code, and variant code.
   * E.g.: basename "messages", Locale "de_AT_oo" -> "messages_de_AT_OO",
   * "messages_de_AT", "messages_de".
   * <p>Follows the rules defined by {@link java.util.Locale#toString()}.
   * @param basename the basename of the bundle
   * @param locale the locale
   * @return the List of filenames to check
   */
  @Override
  protected List<String> calculateFilenamesForLocale(String basename, Locale locale) {
    List<String> result = new ArrayList<String>(1);

    result.add(0, basename);
    return result;
  }

}