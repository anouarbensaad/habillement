package com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value;

import java.util.Calendar;
import java.util.List;



/** 
 * @author Wahid Gazzah
 * @since 27/01/2016
 */
public class ListPaletteElementValue {
	
	private int nombreResultaRechercher;
	
	//added on 25/02/2016, by Wahid Gazzah
	private String palette;
	private Long paletteId;
	private Calendar dateSortie;
	
	List<RouleauFiniReportGroupedByProduitValue> listOFPalette;
	
	
	
	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public List<RouleauFiniReportGroupedByProduitValue> getListOFPalette() {
		return listOFPalette;
	}

	public void setListOFPalette(
			List<RouleauFiniReportGroupedByProduitValue> listOFPalette) {
		this.listOFPalette = listOFPalette;
	}

	public Long getPaletteId() {
		return paletteId;
	}

	public void setPaletteId(Long paletteId) {
		this.paletteId = paletteId;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

	
	
}
