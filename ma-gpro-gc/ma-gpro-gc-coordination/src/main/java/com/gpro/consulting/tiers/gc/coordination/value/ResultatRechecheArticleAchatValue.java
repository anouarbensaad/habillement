/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.List;

/**
 * @author $Ameni
 *
 */
public class ResultatRechecheArticleAchatValue {

	  private Long nombreResultaRechercher;

	  private List < ArticleAchatValue> articleAchatValues;

	/**
	 * @return the nombreResultaRechercher
	 */
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * @param nombreResultaRechercher the nombreResultaRechercher to set
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/**
	 * @return the articleAchatValues
	 */
	public List<ArticleAchatValue> getArticleAchatValues() {
		return articleAchatValues;
	}

	/**
	 * @param articleAchatValues the articleAchatValues to set
	 */
	public void setArticleAchatValues(List<ArticleAchatValue> articleAchatValues) {
		this.articleAchatValues = articleAchatValues;
	}

}
