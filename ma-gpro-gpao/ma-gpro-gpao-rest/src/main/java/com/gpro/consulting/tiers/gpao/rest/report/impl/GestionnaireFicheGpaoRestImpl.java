package com.gpro.consulting.tiers.gpao.rest.report.impl;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.awt.Color;


import java.text.DateFormat;
import java.text.DecimalFormat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.service.ICouleurService;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IPhaseService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.ITailleService;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;

import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.TableHeader;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationReportValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.service.IChaineService;
import com.gpro.consulting.tiers.gpao.service.IFicheEclatementService;
import com.gpro.consulting.tiers.gpao.service.IGammeOfService;
import com.gpro.consulting.tiers.gpao.service.IGestionnaireCacheService;
import com.gpro.consulting.tiers.gpao.service.IMachineService;
import com.gpro.consulting.tiers.gpao.service.IOperationService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;
import com.gpro.consulting.tiers.gpao.service.ISectionService;
import com.gpro.consulting.tiers.gpao.service.planning.chaine.IProductionJourService;
import com.gpro.consulting.tiers.gpao.service.stockfini.IDetailOfService;
import com.gpro.consulting.tiers.gpao.service.stockfini.IMouvementFiniService;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;

import jxl.write.WriteException;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Gestionnaire de reporting
 * 
 * @author Ghazi Atroussi
 * @since 19/12/2017
 *
 */

@Controller
@RequestMapping("/fiches")
@SuppressWarnings("static-access")
public class GestionnaireFicheGpaoRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireFicheGpaoRestImpl.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final String DATE_CALENDAR_FORMAT = "EEE MMM dd yyyy";

	
	@Autowired
	private IGestionnaireCacheService gestionnaireCacheServiceGpao;

	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IPartieInteresseeService partieInteresseeService;

	@Autowired
	private IMachineService machineService;

	@Autowired
	private ISectionService sectionService;

//	@Autowired
//	private IGammeOfService gammeOfService;

	@Autowired
	private IOrdreFabricationService ordreFabricationService;

	@Autowired
	private IOperationService operationService;

	@Autowired
	private ICouleurService couleurService;
	
	
	@Autowired
	private ITailleService tailleService;
	
	@Autowired
	private IFicheEclatementService eclatementService;
	
	
	@Autowired
	private IGammeOfService gammeOFService;
	
	
	@Autowired
	IBaseInfoService baseInfoService;
	

	@Autowired
	IProductionJourService productionJourService;

	@Autowired
	IChaineService chaineService;

	@Autowired
	IPhaseService phaseService;
	
	@Autowired
	private IDetailOfService detailOfService;

	@Autowired
	private IMouvementFiniService mouvementFiniService;
	
	
	private static Cell createCell(String sCell, int colspan) {
		Cell cell;
		cell = new Cell(sCell);
		cell.setColspan(colspan);
		cell.setBorderColor(new Color(201, 201, 201));
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);

		return cell;
	}

	private static PdfPCell createPdfCell(String sCell, int colspan, Font f) {
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(sCell, f));
		cell.setColspan(colspan);
		cell.setBorderColor(new Color(0, 0, 0));
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);

		return cell;
	}

	
	
	@RequestMapping(value = "/ExportReport", method = RequestMethod.GET)
	public void getExportReportValue(
			@RequestParam("id") Long id,
			 HttpServletResponse response) throws JRException, IOException, ParseException, DocumentException {

	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<PaquetValue> listPaquet = new ArrayList<PaquetValue>();
		FicheEclatementValue fiche=eclatementService.getById(id);
		OrdreFabricationValue ordre=ordreFabricationService.rechercheOrdreFabricationParId(fiche.getOrdreFabricationId());
		PartieInteresseValue piRecherche=new PartieInteresseValue();
		piRecherche.setId(ordre.getPartieInterresId());
		
		PartieInteresseValue  client=partieInteresseeService.recherchePartieInteresseeParId(piRecherche);
		
		ProduitValue produit=produitService.rechercheProduitById(ordre.getProduitId(), true);
		
		SousFamilleProduitValue sousFamille=sousFamilleProduitService.rechercheSousFamilleProduitById(produit.getSousFamilleId());
		
		for (PaquetValue paq:fiche.getListPaquet()){
			PaquetValue vPaquet=new PaquetValue();
			TailleValue tailleRecherche=new TailleValue();
			CouleurValue couleurRecherche=new CouleurValue();
			tailleRecherche.setId(paq.getTailleId());
			couleurRecherche.setId(paq.getCouleurId());
			
			TailleValue taillePaq=tailleService.rechercheTailleParId(tailleRecherche);
			CouleurValue couleurPaq=couleurService.rechercheCouleurParId(couleurRecherche);
			
			vPaquet.setId(paq.getId());
			vPaquet.setCouleurId(paq.getCouleurId());
			vPaquet.setTailleId(paq.getTailleId());
			vPaquet.setQuantite(paq.getQuantite());
			vPaquet.setTailleDesignation(taillePaq.getDesignation());
			vPaquet.setCouleurDesignation(couleurPaq.getDesignation());
			vPaquet.setNum(paq.getOrdre());
			vPaquet.setOrdre(taillePaq.getOrdre().longValue());
			listPaquet.add(vPaquet);
		}
		
		 Collections.sort(listPaquet);
	//	listPaquet=fiche.getListPaquet();
		
		
		// int i=0;
		//FacesContext context = FacesContext.getCurrentInstance();

		// etape 1: creation du document
		Document document = new Document(PageSize.A4);

		// etape 2:
		// creation du writer -> PDF ou HTML
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		// etape 3: ouverture du document
		TableHeader event = new TableHeader();
		writer.setPageEvent(event);
		document.open();
		
	    
	   // image.set
		//	document.add(image);
		Table tableEntete2 = new Table(4);
		int[] itemsCols2 = { 10, 10, 10, 10 };
		tableEntete2.setWidths(itemsCols2);
		tableEntete2.setPadding(5);
		tableEntete2.setSpacing(3);
		tableEntete2.setBorder(0);
		Cell cell;

		cell = createCell("Suivi Export", 1);
		cell.setBorderWidthRight(2);
		cell.setBorderWidthBottom(2);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setRowspan(2);
		tableEntete2.addCell(cell);

		// PdfPTable tableEntete2 = new PdfPTable(3);

		cell = createCell("Client", 1);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);

		cell = createCell("Date Export", 1);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);

		cell = createCell("Atelier", 1);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);

		cell = createCell(""
				+ client.getAbreviation(), 1);
		cell.setBorderWidthRight(2);
		cell.setBorderWidthBottom(2);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);
//TODO Dates 
		if (ordre.getNumero() != null)
			cell = createCell("" + dateFormat.format(new Date()), 1);
		else
			cell = createCell("", 1);
		cell.setBorderWidthRight(2);
		cell.setBorderWidthBottom(2);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);
//TODO Site
		cell = createCell("SOPROLIN"
				/*+ listPaquet.get(0).getAbcAticleDetails()
						.getArticleboncommande().getOrdrefabrication()
						.getListordre_chaine().get(0).getChaine().getSite()
						.getLibelle_site()*/, 1);
		cell.setBorderWidthRight(2);
		cell.setBorderWidthBottom(2);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		tableEntete2.addCell(cell);

		document.add(Chunk.NEWLINE);

		Table tableEmb = new Table(1);
		tableEmb.setPadding(5);
		tableEmb.setSpacing(3);
		tableEmb.setBorder(0);

		cell = createCell("Departement Emballage", 1);
		tableEmb.addCell(cell);

		document.add(tableEntete2);
		document.add(tableEmb);

		/*
		 * int[] itemsCols2 = {10, 10, 10, 10,10}; PdfPTable table2 = new
		 * PdfPTable(5); table2.setWidths(itemsCols2);
		 */

		Font ff = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
		Font titre = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
		Font titreFiche = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
		Font titre1 = new Font(Font.TIMES_ROMAN, 17, Font.BOLD);

		/*
		 * Paragraph p2 = new
		 * Paragraph("Date du rapport: "+dateFormat.format(new
		 * Date()),titreFiche); p2.setAlignment(Element.ALIGN_CENTER);
		 * p2.setSpacingBefore(5); p2.setSpacingAfter(20);
		 * 
		 * 
		 * 
		 * document.add(p); document.add(p2); document.add(p3);
		 */

		PdfPCell row1 = createPdfCell("Couleur", 1, titre);
		PdfPCell row2 = createPdfCell("Qté/of", 1, titre);
		PdfPCell row3 = createPdfCell("Rang", 1, titre);
		PdfPCell row4 = createPdfCell("Taille", 1, titre);
		PdfPCell row5 = createPdfCell("Qté", 1, titre);
		/*
		 * Cell row6 =createCell("",1); row6.setWidth(2); row6.setBorder(0);
		 */
		PdfPCell row7 = createPdfCell("Qté Emb", 1, titre);
		PdfPCell row8 = createPdfCell("N°Colis", 1, titre);

		row1.setBackgroundColor(Color.LIGHT_GRAY);
		row2.setBackgroundColor(Color.LIGHT_GRAY);
		row3.setBackgroundColor(Color.LIGHT_GRAY);
		row4.setBackgroundColor(Color.LIGHT_GRAY);
		row5.setBackgroundColor(Color.LIGHT_GRAY);
		// row6.setBackgroundColor(Color.white);
		row7.setBackgroundColor(Color.LIGHT_GRAY);
		row8.setBackgroundColor(Color.LIGHT_GRAY);

		int[] itemsCols = { 18, 10, 10, 10, 10, 10, 10 };
		PdfPTable table = new PdfPTable(7);
		table.setWidths(itemsCols);
		table.setSpacingAfter(2);

		// table.setPadding(5);
		// table.setSpacing(3);
		// table.setBorder(0);

		Paragraph p;
		p = new Paragraph(" OF: "
				+ ordre.getNumero()
				+ "      Désignation :  "
				+ produit.getDesignation()
				+"   Modèle :  "
				+sousFamille.getDesignation()
				
				, titreFiche);
		p.setAlignment(Element.ALIGN_LEFT);
		p.setSpacingBefore(5);
		p.setSpacingAfter(20);
		document.add(p);

		/*
		 * p = new
		 * Paragraph("Article: "+listPaquet.get(0).getAbcAticleDetails().
		 * getArticleboncommande
		 * ().getArticle().getLibelle_article(),titreFiche);
		 * p.setAlignment(Element.ALIGN_CENTER); p.setSpacingBefore(5);
		 * p.setSpacingAfter(20); document.add(p);
		 */

		/*
		 * table.addCell(row1); table.addCell(row2); table.addCell(row3);
		 * table.addCell(row4); table.addCell(row5); table.addCell(row6);
		 * table.addCell(row7); table.addCell(row8);
		 */

		if (listPaquet != null && listPaquet.size() != 0) {
            Set<DetailOfValue> listdetail=new HashSet();
			List<String> listCouleur = new ArrayList<String>();
			listdetail = ordre.getDetailsOF();
//			listCouleur = entityManager
//					.createQuery(
//							"select detail.code.couleur.libelle_couleur from ArticleDetails detail")
//					.getResultList();
			for (DetailOfValue abc:listdetail)
			  {
				CouleurValue couleurRecherche=new CouleurValue();
				couleurRecherche.setId(abc.getCouleurId());
				
				CouleurValue couleur =couleurService.rechercheCouleurParId(couleurRecherche);
				listCouleur.add(couleur.getDesignation());
				
			  }
			Map<String, List<PaquetValue>> list = new HashMap<String, List<PaquetValue>>();
			Map<String, Long> listqteOf = new HashMap<String, Long>();
			for (String couleur : listCouleur) {
				list.put(couleur, new ArrayList<PaquetValue>());
				listqteOf.put(couleur, 0L);
			}

			Set cles = list.keySet();
			Iterator it = cles.iterator();
			while (it.hasNext()) {
				Object cle = it.next();
				for (PaquetValue paquet : listPaquet) {
					CouleurValue couleurRecherche=new CouleurValue();
					couleurRecherche.setId(paquet.getCouleurId());
					CouleurValue couleur =couleurService.rechercheCouleurParId(couleurRecherche);
					String st=couleur.getDesignation();
				
					if (st.equals(cle.toString())) {
						list.get(cle).add(paquet);
						listqteOf.put((String) cle, listqteOf.get(cle)
								+ paquet.getQuantite());
					}
				}
			}
			Iterator it2 = cles.iterator();
			String taille = "";
			int ind = 0;
			int nbre = 0;
			Long qteparof = 0L;
			Long qteTotale = 0L;
			PdfPCell td;
			PdfPCell qteOf;
			while (it2.hasNext()) {
				Object cle = it2.next();

				if (cle != null && list.get(cle) != null
						&& list.get(cle).size() != 0) {
					table.addCell(row1);
					table.addCell(row2);
					table.addCell(row3);
					table.addCell(row4);
					table.addCell(row5); // table.addCell(row6);
					table.addCell(row7);
					table.addCell(row8);

					td = createPdfCell(cle.toString(), 1, ff);
					table.addCell(td);

					qteOf = createPdfCell("" + listqteOf.get(cle), 1, ff);
					table.addCell(qteOf);

					td = createPdfCell("", 5, ff);
					td.setBorder(0);
					table.addCell(td);
					ind = 0;
					qteparof = 0L;
					for (PaquetValue paquet : list.get(cle)) {
						//System.out.println("#####   paquet.getTailleDesignation() :"+paquet.getTailleDesignation());
						//System.out.println("888**888 taille is  :   "+taille);
						
						if (ind > 0
								&& !paquet.getTailleDesignation()
										.equals(taille)) {

							PdfPCell cell1 = createPdfCell(
									"Synthese pour taille = " + taille + " ("
											+ nbre + " paquets)", 4, titre);
							table.addCell(cell1);

							PdfPCell cell2 = createPdfCell("" + qteTotale, 1,
									titre);
							table.addCell(cell2);
							PdfPCell cell3 = createPdfCell("", 2, titre);
							cell3.setBorder(0);
							table.addCell(cell3);
							qteTotale = 0L;
							nbre = 0;
							taille = paquet.getTailleDesignation();
						}
						if (ind == 0)
							taille = paquet.getTailleDesignation();
						ind++;
						nbre++;

						PdfPCell espace = createPdfCell("", 2, ff);
						espace.setBorder(0);
						table.addCell(espace);

						PdfPCell cell1 = createPdfCell(""
								+ paquet.getNum().toString(), 1, ff);
						table.addCell(cell1);

						if (paquet.getQuantite() != null) {

							PdfPCell cell2 = createPdfCell(""
									+ paquet.getTailleDesignation(), 1, ff);
							table.addCell(cell2);
						} else {
							PdfPCell cell2 = createPdfCell("", 1, ff);
							table.addCell(cell2);
						}

						if (paquet.getQuantite() != null) {

							PdfPCell cell3 = createPdfCell(paquet.getQuantite()
									.toString(), 1, ff);
							table.addCell(cell3);
							qteTotale += paquet.getQuantite();
							qteparof += paquet.getQuantite();
						} else {
							PdfPCell cell3 = createPdfCell("", 1, ff);
							table.addCell(cell3);
						}

						/*
						 * Cell cell4 =createCell("",1); cell4.setBorder(0);
						 * cell4.setWidth(2); table.addCell(cell4);
						 */

						PdfPCell cell5 = createPdfCell("", 1, ff);
						table.addCell(cell5);

						PdfPCell cell6 = createPdfCell("", 1, ff);
						table.addCell(cell6);

					}
					PdfPCell Synthese = createPdfCell("Synthese pour taille = "
							+ taille + " (" + nbre + " paquets)", 4, titre);
					table.addCell(Synthese);

					PdfPCell cell2 = createPdfCell("" + qteTotale, 1, titre);
					table.addCell(cell2);
					PdfPCell cell3 = createPdfCell("", 2, titre);
					cell3.setBorder(0);
					table.addCell(cell3);
					qteTotale = 0L;
					nbre = 0;
				}
           // document.newPage();
			}
			//FacesContext context = FacesContext.getCurrentInstance();
			document.add(table);
			document.close();
			String filen = "EXPORT" + "_" + dateFormat.format(new Date())+".pdf";
			/**************************** Ouvrir le nouveau fichier généré *******************************/
			
			
			
	
		response.addHeader("Content-disposition", "attachment;filename="
			+ filen);
		ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			baos.flush();

		response.setContentType("application/pdf");
			

		}

	  
	
	
	
	} 

// etiquette code barre SOPROLIN
	
	
	@RequestMapping(value = "/EtiquetteReport", method = RequestMethod.GET)
	public void getEtiquetteReportValue(
			@RequestParam("id") Long id,
			 HttpServletResponse response) throws JRException, IOException, ParseException, DocumentException {

		List<PaquetValue> listPaquet = new ArrayList<PaquetValue>();
		FicheEclatementValue fiche=eclatementService.getById(id);
		OrdreFabricationValue ordre=ordreFabricationService.rechercheOrdreFabricationParId(fiche.getOrdreFabricationId());
		ProduitValue produit=produitService.rechercheProduitById(ordre.getProduitId(), true);
		
		if (fiche!=null)
			if (fiche.getListPaquet()!=null)
		for (PaquetValue paq:fiche.getListPaquet()){
			PaquetValue vPaquet=new PaquetValue();
			TailleValue tailleRecherche=new TailleValue();
			CouleurValue couleurRecherche=new CouleurValue();
			tailleRecherche.setId(paq.getTailleId());
			couleurRecherche.setId(paq.getCouleurId());
			
			TailleValue taillePaq=tailleService.rechercheTailleParId(tailleRecherche);
			CouleurValue couleurPaq=couleurService.rechercheCouleurParId(couleurRecherche);
			
			vPaquet.setId(paq.getId());
			vPaquet.setCouleurId(paq.getCouleurId());
			vPaquet.setTailleId(paq.getTailleId());
			vPaquet.setQuantite(paq.getQuantite());
			vPaquet.setTailleDesignation(taillePaq.getDesignation());
			vPaquet.setCouleurDesignation(couleurPaq.getDesignation());
			vPaquet.setNum(paq.getOrdre());
			vPaquet.setOrdre(paq.getOrdre());
			listPaquet.add(vPaquet);
		}
		
		 Collections.sort(listPaquet);
		
		List<ElementGammeOfValue> listOpertations=new ArrayList<ElementGammeOfValue>();
		
		GammeOfValue gamme=gammeOFService.getByOFId(ordre.getId());
		if (gamme!=null)
		if(gamme.getListElementGammeOf()!=null)
		for (ElementGammeOfValue element:gamme.getListElementGammeOf()){
			listOpertations.add(element);
		}
		 
		 Collections.sort(listOpertations);
		 
	//	FacesContext context = FacesContext.getCurrentInstance();

		// etape 1: creation du document
		// Rectangle pageSize = new Rectangle(0,0,595,862);
		Document document = new Document(PageSize.A4, 0.5f, 0.5f, 3.5f, 1);

		// etape 2:
		// creation du writer -> PDF ou HTML
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		PdfWriter pdf = PdfWriter.getInstance(document, baos);

		// etape 3: ouverture du document
		document.open();
		//contentByte = pdf.getDirectContent();
		// Font titre= new Font(Font.TIMES_ROMAN,17,Font.BOLD);
		Font titreFiche = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
		titreFiche.setStyle(Font.UNDERLINE);

		int[] itemsCols = { 20 };
		int[] itemsCols2 = { 1, 19 };
		int[] itemsCols3 = { 15, 5 };

		Font font1 = new Font(Font.TIMES_ROMAN, 4, Font.BOLD);
		Font font2 = new Font(Font.TIMES_ROMAN, 4, Font.NORMAL);
		int nbre = 0;
		PdfPTable tableall = new PdfPTable(5);
		tableall.setWidthPercentage(100);
		// tableall.setSpacingAfter(2);
		// tableall.setSpacingBefore(2);

		if (listPaquet != null && listPaquet.size() != 0 && listOpertations!=null && listOpertations.size()!=0) {
			MultiColumnText mtc2 = new MultiColumnText();
			mtc2.addRegularColumns(document.left(), document.right(), 10f, 5);

			for (PaquetValue paquet : listPaquet) {

				if (paquet != null) {

					for (ElementGammeOfValue et : listOpertations) {
						nbre++;
						String rang_of = paquet.getOrdre().toString();
						String op = et.getOrdre().toString();
						String of1 = ordre
								.getId().toString();
						if (paquet.getOrdre() < 10)
							rang_of = "00" + rang_of;
						if (paquet.getOrdre() < 100
								&& paquet.getOrdre() >= 10)
							rang_of = "0" + rang_of;
						if (et.getOrdre() < 10)
							op = "0" + op;
						if (ordre.getId() < 10)
							of1 = "0000" + of1;
						if (ordre.getId() < 100
								&& ordre.getId() >= 10)
							of1 = "000" + of1;
						if (ordre.getId() < 1000
								&& ordre.getId() >= 100)
							of1 = "00" + of1;
						if (ordre.getId() < 10000
								&& ordre.getId() >= 1000)
							of1 = "0" + of1;

						String test = rang_of  + op  + of1;

						String libelle_op = "";
						if (et.getOperationDesignation().length() > 25) {
							libelle_op = et.getOperationDesignation().substring(0, 25);
							// TODO
						} else {
							libelle_op = et.getOperationDesignation();
						}

						String libelle_of = "";
						if (ordre.getNumero().length() > 16) {
							libelle_of = ordre.getNumero().substring(
											0, 16);
							// TODO
						} else {
							libelle_of = ordre.getNumero();
						}
						String libelle_art = "";
						if (produit.getDesignation().length() > 18) {
							libelle_art = produit.getDesignation().substring(0, 18);
							// TODO
						} else {
							libelle_art = produit.getDesignation();
						}

						PdfPTable table = new PdfPTable(1);
						table.setWidths(itemsCols);
						table.setHorizontalAlignment(Element.ALIGN_LEFT);

						PdfPTable table2 = new PdfPTable(1);
						table2.setWidths(itemsCols);
						table2.setHorizontalAlignment(Element.ALIGN_LEFT);

						PdfPTable table4 = new PdfPTable(1);
						table4.setWidths(itemsCols);
						table4.setHorizontalAlignment(Element.ALIGN_LEFT);

						PdfPTable table3 = new PdfPTable(2);
						table3.setWidths(itemsCols2);
						table3.setHorizontalAlignment(Element.ALIGN_LEFT);

						PdfPTable table1 = new PdfPTable(2);
						table1.setWidths(itemsCols3);
						table1.setHorizontalAlignment(Element.ALIGN_LEFT);
						DecimalFormat df = new DecimalFormat("0");
						df.setMaximumFractionDigits(2);
						Double temps_paquet = et.getTemps()
								* paquet.getQuantite();
						PdfPCell row1 = new PdfPCell(new Paragraph("T: "
								+ df.format(temps_paquet) + " " + libelle_op
								+ " Q:" + paquet.getQuantite()+" T:"+paquet.getTailleDesignation()+" C:"+paquet.getCouleurDesignation(), font2));
						row1.setBorderColor(Color.WHITE);

						PdfPCell row2 = new PdfPCell(new Paragraph(libelle_of+" /A:"+libelle_art,
								font1));
						row2.setBorderColor(Color.WHITE);
						row2.setFixedHeight(7);

						PdfPCell row0 = new PdfPCell(new Paragraph(""
								+ paquet.getOrdre()+" Op:"+op, font1));
						row0.setBorderColor(Color.WHITE);
						row0.setFixedHeight(7);

						PdfPCell row3 = new PdfPCell(new Paragraph("", font2));
                        PdfContentByte content=pdf.getDirectContent();
                        Image img =createBarcode128(test,content);
						//System.out.println("#####  IMG  :   "+img);
                        PdfPCell row4 = new PdfPCell(new Phrase(new Chunk(
								createBarcode128(test,content), 0, 0)));

						row3.setBorderColor(Color.WHITE);
						row4.setBorderColor(Color.WHITE);
						row4.setVerticalAlignment(Element.ALIGN_TOP);
						row3.setFixedHeight(15.9f);
						row4.setFixedHeight(15.9f);

						
						PdfPCell row6 = new PdfPCell(new Phrase(libelle_art
								+ "  "
								+ paquet.getTailleDesignation(), font2));
						row6.setBorderColor(Color.WHITE);
						row6.setFixedHeight(6.9f);
						row1.setFixedHeight(6.9f);

						table2.addCell(row1);
						table1.addCell(row2);
						table1.addCell(row0);
						table3.addCell(row3);
						table3.addCell(row4);
						table.addCell(row6);
						table.setWidthPercentage(100);
						table.setSpacingAfter(1.4f);
						table2.setWidthPercentage(100);
						table2.setSpacingAfter(0.0f);
						table3.setWidthPercentage(100);
						table3.setSpacingAfter(0.0f);
						table4.setWidthPercentage(100);
						table4.setSpacingAfter(0.0f);
						PdfPCell cell = new PdfPCell();
						cell.addElement(table1);
						cell.addElement(table2);
						cell.addElement(table3);
						//cell.addElement(table);
						cell.addElement(table4);
						cell.setBorder(0);
						cell.setPadding(5);
						cell.setFixedHeight(42);
						tableall.addCell(cell);

						if (nbre == 95) {
							document.add(tableall);
							document.newPage();
							tableall = new PdfPTable(5);
							tableall.setWidthPercentage(100);
							nbre = 0;
						}
					}

				}
			}
			if (nbre > 0 && nbre < 95) {
				if (nbre % 5 != 0)
					for (int t = 0; t < 5 - (nbre % 5); t++) {
						PdfPCell modcell = new PdfPCell();
						modcell.setBorderColor(Color.WHITE);
						tableall.addCell(modcell);
					}
				document.add(tableall);
			}
			document.close();

			String filen = "fiche" + "_" + ordre.getNumero()+".pdf";
			/**************************** Ouvrir le nouveau fichier généré *******************************/
			
			
			
	
		response.addHeader("Content-disposition", "attachment;filename="
			+ filen);
		ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			baos.flush();

		response.setContentType("application/pdf");
			

		}

	  
	
	
	
	} 

	/*
	 * Cette configuration de code à barre sera utilisée dans les fiches
	 * suiveuses
	 */
	/* Creating a barcode image using Barcode 128 for myText */
	public Image createBarcode128(String myText ,PdfContentByte writer) {
		//contentByte = pdf.getDirectContent();
		Barcode128 code128 = new Barcode128();
	//	//System.out.println("####MyText :" + myText);
		code128.setCode(myText);
		code128.setX((float) 0.6);
		code128.setBarHeight(8);
		// code128.setBarHeight(9);
		code128.setBaseline(5);
		code128.setSize(4);
	//	//System.out.println("***" + contentByte.getPdfWriter());
		Image myBarCodeImage128 = code128.createImageWithBarcode(writer,
				null, null);

		return myBarCodeImage128;
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/commandeSuivie", method = RequestMethod.GET)
	public void generateSuivieCommandeReportExel(@RequestParam(value = "clientId", required = false) String clientId,
			@RequestParam(value = "produitId", required = false) String produitId,
			@RequestParam(value = "DateIntroductionDe", required = false) String vDateIntroductionDe,
			@RequestParam(value = "DateIntroductionA", required = false) String vDateIntroductionA,
			@RequestParam(value = "dateLivraisonDu", required = false) String vDateLivraisonDe,
			@RequestParam(value = "dateLivraisonTo", required = false) String vDateLivraisonA,
			@RequestParam(value = "numOf", required = false) String numOf,
			@RequestParam(value = "refProduit", required = false) String refProduit,

			@RequestParam(value = "desProduit", required = false) String desProduit,
			@RequestParam(value = "vEtat", required = false) String vEtat,
			@RequestParam(value = "type", required = false) String type,

			@RequestParam(value = "etatCoupe", required = false) String etatCoupe,
			@RequestParam(value = "etatFabrication", required = false) String etatFabrication,
			@RequestParam(value = "etatConditionnement", required = false) String etatConditionnement,
			@RequestParam(value = "etatCollisage", required = false) String etatCollisage,
			@RequestParam(value = "etatExpedition", required = false) String etatExpedition,
			@RequestParam(value = "etatEngagement", required = false) String etatEngagement,

			HttpServletResponse response) throws WriteException, IOException {

//		System.out.println("#################################ETAT etatCoupe          ===> "+etatCoupe);
//		System.out.println("#################################ETAT etatFabrication    ===> "+etatFabrication);
//		System.out.println("#################################ETAT etatConditionnement ==> "+etatConditionnement);
//		System.out.println("#################################ETAT etatCollisage      ===> "+etatCollisage);
//		System.out.println("#################################ETAT etatExpedition      ==> "+etatExpedition);
//
//		

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("liste_Suivie_Commande" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("Sortie", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 30);
		sheet3.setColumnView(3, 35);
		sheet3.setColumnView(4, 40);
		sheet3.setColumnView(5, 25);
		sheet3.setColumnView(6, 15);
		sheet3.setColumnView(7, 15);
		sheet3.setColumnView(8, 20);
		sheet3.setColumnView(9, 20);
		sheet3.setColumnView(10, 20);
		sheet3.setColumnView(11, 20);
		sheet3.setColumnView(12, 20);
		sheet3.setColumnView(13, 20);
		sheet3.setColumnView(14, 20);
	

		/**************************************************************************/

		// Récuperation de Nom du client active

		List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		String clientDesigntion = "";
		for (BaseInfoValue baseInf : baseInfoValues) {
			if (baseInf.isActif()) {
				clientDesigntion = baseInf.getDesignation();
			}
		}

		sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "Rapport de suivie des commandes", boldTitre));
		sheet3.mergeCells(2, 7, 14, 8);
		sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", boldRed5));

		RechercheMulticritereOrdreFabricationValue request = new RechercheMulticritereOrdreFabricationValue();

		if (isNotEmty(clientId)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.getById(Long.parseLong(clientId)).getAbreviation(), boldRed3));

			request.setClientId(Long.parseLong(clientId));
		}
		if (isNotEmty(numOf)) {


			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Numero :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numOf, boldRed3));

			request.setvNumero(numOf);
		}
		if (isNotEmty(vDateLivraisonDe)) {
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison du :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, vDateLivraisonDe, boldRed3));

			request.setDateLivraisonDu(stringToCalendar(vDateLivraisonDe));
		}
		if (isNotEmty(vDateLivraisonA)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Livraison A :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, "Numero", boldRed3));

			request.setDateLivraisonTo(stringToCalendar(vDateLivraisonA));
		}

		if (isNotEmty(vDateIntroductionDe)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Introduction du :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, vDateIntroductionDe, boldRed3));

			request.setvDateIntroductionDu(stringToCalendar(vDateIntroductionDe));
		}

		if (isNotEmty(vDateIntroductionA)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date Introduction A :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, vDateIntroductionA, boldRed3));

			request.setvDateIntroductionAu(stringToCalendar(vDateIntroductionA));
		}

	/*	if (isNotEmty(refProduit)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Référence article :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, refProduit, boldRed3));

			request.setReferenceProduit(refProduit);

		}

		if (isNotEmty(desProduit)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Désignation", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, desProduit, boldRed3));

			request.setDesignationProduit(desProduit);

		}

*/

	

		if (isNotEmty(etatCoupe)) {

			if (etatCoupe.equals("PLUS"))
				etatCoupe = "+";
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat coupe :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatCoupe, boldRed3));

			request.setEtatCoupe(etatCoupe);

		}

		if (isNotEmty(etatEngagement)) {

			if (etatEngagement.equals("PLUS"))
				etatEngagement = "+";
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat engagement :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatEngagement, boldRed3));

			request.setEtatEngagement(etatEngagement);

		}
		if (isNotEmty(etatFabrication)) {

			if (etatFabrication.equals("PLUS"))
				etatFabrication = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat fabrication :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatFabrication, boldRed3));

			request.setEtatFabrication(etatFabrication);

		}

		if (isNotEmty(etatConditionnement)) {

			if (etatConditionnement.equals("PLUS"))
				etatConditionnement = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat conditionnement :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatConditionnement, boldRed3));

			request.setEtatConditionnement(etatConditionnement);

		}

		if (isNotEmty(etatCollisage)) {

			if (etatCollisage.equals("PLUS"))
				etatCollisage = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat collisage :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatCollisage, boldRed3));

			request.setEtatCollisage(etatCollisage);

		}

		if (isNotEmty(etatExpedition)) {

			if (etatExpedition.equals("PLUS"))
				etatExpedition = "+";

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Etat expédition :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, etatExpedition, boldRed3));

			request.setEtatExpedition(etatExpedition);

		}

		ResultatMulticritereOrdreFabricationValue resultatRechercheOrdreFabrication = ordreFabricationService
				.rechercherOrdreFabricationMultiCritere(request);
		
		if (resultatRechercheOrdreFabrication != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(), sousFamilleProduit.getDesignation());
				}
			}

			for (OrdreFabricationValue ordreFabrication : resultatRechercheOrdreFabrication.getOrdreFabricationValues()) {

				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId(), true);

					if (produit != null) {
						ordreFabrication.setProduitDesignation(produit.getDesignation());
						ordreFabrication.setProduitReference(produit.getReference());
						if (produit.getPartieIntersseId() != null) {
							PartieInteresseValue client = partieInteresseeService.getAbreviationClient(produit.getPartieIntersseId());

							if (client != null) {
								ordreFabrication.setPartieInterresAbreviation(client.getAbreviation());
							}
						}
						if (mapSousFamilleProduit.containsKey(produit.getSousFamilleId())) {
							ordreFabrication.setProduitSousFamilleDesignation(
									mapSousFamilleProduit.get(produit.getSousFamilleId()));
						}
					}
				}
				Map<String, String> mapA = gestionnaireCacheServiceGpao
						.rechercherProduitEtatOFParId(ordreFabrication.getEtat());

				// Etat(StatutOF)
				ordreFabrication.setEtatDesignation(mapA.get("etatOF"));
			}
		}
		
		

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "Client", boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Numero", boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Référence", boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Désignation", boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Date d'introduction", boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Date de livraison", boldRed2));
		
	
		sheet3.addCell(new Label(8, i - 1, "Quantité", boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Coupe", boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Engagement", boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Sortie Chaine", boldRed2));
		sheet3.addCell(new Label(12, i - 1, "Emballage", boldRed2));
		sheet3.addCell(new Label(13, i - 1, "Colisage", boldRed2));
		sheet3.addCell(new Label(14, i - 1, "Expédition", boldRed2));

		Long sommeQte = new Long(0);
		Long sommeQteCoupe = new Long(0);
		Long sommeQteEngagement = new Long(0);
		Long sommeQteFabrication = new Long(0);
		Long sommeQteEmballage = new Long(0);
		Long sommeQteExpedition = new Long(0);
		Long sommeQteCollisage = new Long(0);
		Double sommeTemps = new Double(0);

		List<OrdreFabricationReportValue> listCommande = new ArrayList<>();

		for (OrdreFabricationValue element : resultatRechercheOrdreFabrication.getOrdreFabricationValues()) {

			OrdreFabricationReportValue of = new OrdreFabricationReportValue();

			of.setId(element.getId());

			if (element.getPartieInterresAbreviation() != null) {
				of.setPartieInterresAbreviation(element.getPartieInterresAbreviation());

			}

			if (element.getNumero() != null) {
				of.setNumero(element.getNumero());

			}
			if (element.getProduitReference() != null) {
				of.setProduitReference(element.getProduitReference());

			}

			if (element.getProduitDesignation() != null) {
				of.setProduitDesignation(element.getProduitDesignation());

			}

			if (element.getQuantite() != null) {

				of.setQuantite(element.getQuantite());

			}
			if (element.getDateIntroduction() != null) {
				of.setDateIntroduction(element.getDateIntroduction());

			}

			if (element.getDateLivraison() != null)
				of.setDateLivraison(element.getDateLivraison());

			else
				of.setDateLivraison(element.getDateIntroduction());


			if (element.getQtCoupe() != null)
				of.setQtCoupe(element.getQtCoupe());

			if (element.getQtColisage() != null)
				of.setQtColisage(element.getQtColisage());

			if (element.getQtFinition() != null)
				of.setQtFinition(element.getQtFinition());

			if (element.getQtExpedition() != null)
				of.setQtExpedition(element.getQtExpedition());

			if (element.getQtSortie() != null)
				of.setQtSortie(element.getQtSortie());

			if (element.getQtEngagement() != null)
				of.setQtEngagement(element.getQtEngagement());
			
			
			listCommande.add(of);

		}

		Collections.sort(listCommande);

		for (OrdreFabricationReportValue element : listCommande) {

			// Client
			if (element.getPartieInterresAbreviation() != null) {
				sheet3.addCell(new Label(2, i, element.getPartieInterresAbreviation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			// numero of
			if (element.getNumero() != null) {
				sheet3.addCell(new Label(3, i, element.getNumero() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			// ref prod

			if (element.getProduitReference() != null) {
				sheet3.addCell(new Label(4, i, element.getProduitReference() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

			// produit designation

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getProduitDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			// date introduction

			if (element.getDateIntroduction() != null) {
				sheet3.addCell(
						new Label(6, i, dateFormat.format(element.getDateIntroduction().getTime()) + "", boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			// date de Livraison

			if (element.getDateLivraison() != null) {
				sheet3.addCell(new Label(7, i, dateFormat.format(element.getDateLivraison().getTime()) + "", boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

		

	

			// Quantite

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(8, i, element.getQuantite() + "", boldRed));
				sommeQte += element.getQuantite();

			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}

			// Coupe

			if (element.getQtCoupe() != null) {
				sheet3.addCell(new Label(9, i, element.getQtCoupe() + "", boldRed));
				sommeQteCoupe += element.getQtCoupe();

			} else {
				sheet3.addCell(new Label(9, i, "", boldRed));
			}
			
			// Coupe

			if (element.getQtEngagement() != null) {
				sheet3.addCell(new Label(10, i, element.getQtEngagement() + "", boldRed));
				sommeQteEngagement += element.getQtEngagement();

			} else {
				sheet3.addCell(new Label(10, i, "", boldRed));
			}

			// QUANTITE Fabrication

			if (element.getQtSortie() != null) {
				sheet3.addCell(new Label(11, i, element.getQtSortie() + "", boldRed));
				sommeQteFabrication += element.getQtSortie();

			} else {
				sheet3.addCell(new Label(11, i, "", boldRed));
			}

			// QTE Emballage

			if (element.getQtFinition() != null) {
				sheet3.addCell(new Label(12, i, element.getQtFinition() + "", boldRed));
				sommeQteEmballage += element.getQtFinition();

			} else {
				sheet3.addCell(new Label(12, i, "", boldRed));
			}

			// QTE Colis

			if (element.getQtColisage() != null) {
				sheet3.addCell(new Label(13, i, element.getQtColisage() + "", boldRed));
				sommeQteCollisage += element.getQtColisage();

			} else {
				sheet3.addCell(new Label(13, i, "", boldRed));
			}

			// QTE EXPEDITION

			if (element.getQtExpedition() != null) {
				sheet3.addCell(new Label(14, i, element.getQtExpedition() + "", boldRed));
				sommeQteExpedition += element.getQtExpedition();

			} else {
				sheet3.addCell(new Label(14, i, "", boldRed));
			}

			i++;

		}

		// Bas de page du Rapport suivie de commande

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 4;

		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage,
				"La somme totale des quantités dans les différentes phase de production des commandes : ", boldRed5));
		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 2, numLigneBasDuPage);

		numLigneBasDuPage++;
		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(
				new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités des commandes: ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQte.toString(), boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités coupées : ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteCoupe.toString(), boldRed3));

		numLigneBasDuPage++;
		

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités engagées : ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteEngagement.toString(), boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités fabriquées : ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteFabrication.toString(), boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités emballées: ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteEmballage.toString(), boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(
				new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités mise en colis: ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteCollisage.toString(), boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des quantités expédiées : ", boldRed3));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQteExpedition.toString(), boldRed3));

		numLigneBasDuPage++;

	/*	sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "La somme des minutes: ", boldRed3));
		sheet3.addCell(
				new Label(numColBasDuPage + 2, numLigneBasDuPage, convertisseur(sommeTemps, 3).toString(), boldRed3));
   */
		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;

	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/production", method = RequestMethod.GET)
	public void generateProductionReportExel(

			@RequestParam(value = "partieInterresId", required = false) String partieInterresId,
			@RequestParam(value = "idChaine", required = false) String idChaine,
			@RequestParam(value = "idPhase", required = false) String idPhase,
			@RequestParam(value = "dateDe", required = false) String dateDe,
			@RequestParam(value = "dateA", required = false) String dateA,
			@RequestParam(value = "numOF", required = false) String numOF,
			@RequestParam(value = "semaine", required = false) String semaine,
			@RequestParam(value = "urgent", required = false) String urgent,

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("rapport-production" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		sheet3.setColumnView(2, 17);// numero of

		sheet3.setColumnView(3, 10); // reference

		sheet3.setColumnView(4, 30);// Designation of

		//sheet3.setColumnView(5, 10); // urgence

		sheet3.setColumnView(5, 16); // chaine
		sheet3.setColumnView(6, 16); // phase

		sheet3.setColumnView(7, 10); // quantite

		sheet3.setColumnView(8, 10); // quantite Non Conf
		
		sheet3.setColumnView(9, 16); // date

		/*
		 * sheet3.setColumnView(8, 10); // Métrage 1er Tissu sheet3.setColumnView(9,
		 * 10); // Métrage 2eme Tissu sheet3.setColumnView(10, 10); // Métrage 3eme
		 * Tissu sheet3.setColumnView(11, 10); // Métrage 4eme Tissu
		 */

		/**************************************************************************/

		// Récuperation de Nom du client active

		List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		String clientDesigntion = "";
		for (BaseInfoValue baseInf : baseInfoValues) {
			if (baseInf.isActif()) {
				clientDesigntion = baseInf.getDesignation();
			}
		}

		sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "Rapport de production", boldTitre));
		sheet3.mergeCells(2, 7, 9, 8);
		sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", boldRed5));
		numLigneCritRech++;

		RechercheMulticritereProductionJourValue request = new RechercheMulticritereProductionJourValue();

		/*
		 * request.setDateA(stringToCalendar(dateA));
		 * request.setDateDe(stringToCalendar(dateDe));
		 */

		request.setDateA(calendarStringToCalendarObject(dateA));
		request.setDateDe(calendarStringToCalendarObject(dateDe));

		request.setChaineId(idChaine);
		request.setNumOF(numOF);
		request.setSemaine(semaine);


		if (isNotEmty(idPhase))

		{
			Long idPhaseNumber = Long.parseLong(idPhase);
			request.setIdPhase(idPhaseNumber);
		}

		if (isNotEmty(partieInterresId)) {
			Long idPartieIntere = Long.parseLong(partieInterresId);
			request.setPartieInterresId(idPartieIntere);
		}

		ResultatRechecheProductionJourValue resultatRecherche = productionJourService.rechercherMultiCritere(request);

		if (isNotEmty(partieInterresId)) {

			// System.out.println("#################################Recherche par Clien id
			// ##################");
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.getById(Long.parseLong(partieInterresId)).getAbreviation(), boldRed3));

		}

		if (isNotEmty(dateDe)) {
			// System.out.println("#################### DateLivraison de " + dateDe);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date de :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateDe).getTime()),
					boldRed3));

		}
		if (isNotEmty(dateA)) {
			// System.out.println("#################### DateLivraison A " + dateA);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date  A :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateA).getTime()),
					boldRed3));

		}

		if (isNotEmty(numOF)) {

			// System.out.println("#################################Recherche par Num of
			// ##################");
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Numero OF :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, numOF, boldRed3));

		}

		if (isNotEmty(idChaine)) {

			String designationChaine = chaineService.rechercheChaineParId(Long.parseLong(idChaine)).getDesignation();

			// System.out.println("#################### DateIntroduction De " + idChaine);
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Chaine ", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designationChaine, boldRed3));

		}

		if (isNotEmty(idPhase)) {

			PhaseValue phase = new PhaseValue();
			phase.setId(Long.parseLong(idPhase));

			String designationPhase = phaseService.recherchePhaseParId(phase).getDesignation();

			// System.out.println("#################### DateIntroduction A " +
			// vDateIntroductionA);

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Phase", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech, designationPhase, boldRed3));

		}

	

		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "O.F", boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Désignation", boldRed2));

	//	sheet3.addCell(new Label(5, i - 1, "Urgence", boldRed2));

		sheet3.addCell(new Label(5, i - 1, "Chaine", boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Phase", boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Quantité", boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Quantité Non Conf.", boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Date", boldRed2));

		/*
		 * sheet3.addCell(new Label(8, i - 1, "Tissu 1", boldRed2)); sheet3.addCell(new
		 * Label(9, i - 1, "Tissu 2", boldRed2)); sheet3.addCell(new Label(10, i - 1,
		 * "Tissu 3", boldRed2)); sheet3.addCell(new Label(11, i - 1, "Tissu 4",
		 * boldRed2));
		 * 
		 */

		Long sommeQuantite = 0l;
		
		Long sommeQuantiteNonConf = 0l;

		for (ProductionJourElementValue element : resultatRecherche.getList()) {

			OrdreFabricationValue ordreFabrication = ordreFabricationService.rechercheOrdreFabricationParId(element.getOf());
			ProduitValue produitOf = produitService.rechercheProduitById(ordreFabrication.getProduitId());
			
			element.setProduitReference(produitOf.getReference());
			element.setProduitDesignation(produitOf.getDesignation());
			
			// numero of

			if (element.getoFDesignation() != null) {
				sheet3.addCell(new Label(2, i, element.getoFDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			// reference

			if (element.getProduitReference() != null) {
				sheet3.addCell(new Label(3, i, element.getProduitReference() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			// designation of

			if (element.getProduitDesignation() != null) {
				sheet3.addCell(new Label(4, i, element.getProduitDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

		
			// chaine

			if (element.getChaineDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getChaineDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			// phase

			if (element.getPhaseDesignation() != null) {
				sheet3.addCell(new Label(6, i, element.getPhaseDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			// quantite

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(7, i, element.getQuantite() + "", boldRed));

				sommeQuantite += element.getQuantite();
			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			// quantite non conforme

			if (element.getQteNonConf() != null) {
				sheet3.addCell(new Label(8, i, element.getQteNonConf() + "", boldRed));

				sommeQuantiteNonConf += element.getQteNonConf();
			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}
			
			// date

			if (element.getDate() != null) {
				sheet3.addCell(new Label(9, i, dateFormat.format(element.getDate().getTime()), boldRed));

			} else {
				sheet3.addCell(new Label(9, i, "", boldRed));
			}



			i++;

		}

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "nombre des lignes ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
				resultatRecherche.getNombreResultaRechercher() + "", boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite totale ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantite + "", boldRed3));
		
		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite Non Conf. totale ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantiteNonConf + "", boldRed3));

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;
		// System.out.println("#####################################" + dateDe);
	}

	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/mouvementFini", method = RequestMethod.GET)
	public void generateMouvementFiniReportExel(
			@RequestParam(value = "numeroBon", required = false) String numeroBon,
			@RequestParam(value = "numeroOf", required = false) String numeroOf,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "dateDe", required = false) String dateDe,
			@RequestParam(value = "dateA", required = false) String dateA,
			@RequestParam(value = "detailOfId", required = false) String detailOfId,
			
			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("rapport-mouvement-etat-" +type+ "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		sheet3.setColumnView(2, 15);// num bon
		
		sheet3.setColumnView(3, 15);// date mvt
		
		sheet3.setColumnView(4, 15);// numOF

		sheet3.setColumnView(5, 15);// reference produit
		
		sheet3.setColumnView(6, 20); // designation of



		sheet3.setColumnView(7, 15); // couleur
		
		sheet3.setColumnView(8, 10); // taille
		
		sheet3.setColumnView(9, 13); // quantite
		
		
		sheet3.setColumnView(10, 13); // quantite Stock
		
		sheet3.setColumnView(11, 13); // quantite Mouvement



		/**************************************************************************/

		// Récuperation de Nom du client active

		List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		String clientDesigntion = "";
		for (BaseInfoValue baseInf : baseInfoValues) {
			if (baseInf.isActif()) {
				clientDesigntion = baseInf.getDesignation();
			}
		}

		sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "Mouvement Stock Fini", boldTitre));
		sheet3.mergeCells(2, 7, 11, 8);
		sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", boldRed5));
		numLigneCritRech++;

		

		RechercheMulticritereMouvementFiniValue requestMvtFini = new RechercheMulticritereMouvementFiniValue();


		if (isNotEmty(numeroBon)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "N° BON :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					numeroBon, boldRed3));
			
			
			requestMvtFini.setNumeroBon(numeroBon);

		}
		
		if (isNotEmty(type)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Mouvement :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					type, boldRed3));
			
			
			requestMvtFini.setType(type);

		}
		

		if (isNotEmty(numeroOf)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "N° O.F :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					numeroOf, boldRed3));
			
			
			requestMvtFini.setNumeroOf(numeroOf);

		}

		if (isNotEmty(dateDe)) {
 
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date De :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateDe).getTime()), boldRed3));
			
			
			Calendar cDateDe = calendarStringToCalendarObject(dateDe);
			cDateDe.add(Calendar.DATE, -1);
			
			requestMvtFini.setDateDe(cDateDe);
			
		
		}
		
		
		if (isNotEmty(dateA)) {
			 
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Date A :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					new SimpleDateFormat("dd-MM-yyyy").format(calendarStringToCalendarObject(dateA).getTime()), boldRed3));
			
			
			Calendar cDateA = calendarStringToCalendarObject(dateA);
			cDateA.add(Calendar.DATE, -1);
			
			requestMvtFini.setDateDe(cDateA);						

		}
		


		int i = numLigneCritRech + 4;

		
		
		sheet3.addCell(new Label(2, i - 1, "N° Bon", boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Date MVT", boldRed2));
		sheet3.addCell(new Label(4, i - 1, "O.F", boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Référence", boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Désignation", boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Couleur", boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Taille", boldRed2));
		sheet3.addCell(new Label(9, i - 1, "Quantité", boldRed2));
		sheet3.addCell(new Label(10, i - 1, "Qte En Stock", boldRed2));
		sheet3.addCell(new Label(11, i - 1, "Qte MVT", boldRed2));


		Long sommeQuantite = 0l;
		
		Long sommeQuantiteEnStock = 0l;
		
		Long sommeQuantiteMVT = 0l;
		
		ResultatMulticritereMouvementFiniValue resultatRechercheMouvementFini  = mouvementFiniService.rechercherMouvementFiniMultiCritere(requestMvtFini);
		

		for (MouvementFiniValue element : resultatRechercheMouvementFini.getMovementFiniValues()) {

			RechercheMulticritereDetailOfValue requestDetailOf = new RechercheMulticritereDetailOfValue();
			
			requestDetailOf.setId(element.getDetailOfId());
			
			DetailOfValue detailOf = null;
		
			
			
			ResultatMulticritereDetailOfValue resultatRechercheMCdetailOF  = detailOfService.rechercherDetailOfMultiCritere(requestDetailOf);
			
			if (resultatRechercheMCdetailOF.getNombreResultaRechercher() == 1)
			{
				detailOf = resultatRechercheMCdetailOF.getDetailOfValues().iterator().next();
			}
			
			
			//num bon
			
			if (element.getNumeroBon() != null) {
				sheet3.addCell(new Label(2, i, element.getNumeroBon() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}
			
			
			//date mvt
			
			
			if (element.getDate()!= null) {
				sheet3.addCell(new Label(3, i, dateFormat.format(element.getDate().getTime()) + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}
			
			
			// numero of

			if (element.getNumeroOf() != null) {
				sheet3.addCell(new Label(4, i, element.getNumeroOf() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

			// reference

			if (detailOf != null && detailOf.getReferenceProduit() != null) {
				sheet3.addCell(new Label(5, i, detailOf.getReferenceProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			// designation of

			if (detailOf != null && detailOf.getDesignationOF() != null) {
				sheet3.addCell(new Label(6, i, detailOf.getDesignationOF() + "", boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

		
			// couleur

			if (detailOf != null && detailOf.getCouleurDesignation() != null) {
				sheet3.addCell(new Label(7, i, detailOf.getCouleurDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			// taille

			if (detailOf != null && detailOf.getTailleDesignation() != null) {
				sheet3.addCell(new Label(8, i, detailOf.getTailleDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}

			// quantite

			if (detailOf != null && detailOf.getQuantite() != null) {
				sheet3.addCell(new Label(9, i, detailOf.getQuantite() + "", boldRed));

				sommeQuantite += detailOf.getQuantite();
			} else {
				sheet3.addCell(new Label(9, i, "", boldRed));
			}

			// quantite en stock

			if (detailOf != null && detailOf.getQteStock() != null) {
				sheet3.addCell(new Label(10, i, detailOf.getQteStock() + "", boldRed));

				sommeQuantiteEnStock += detailOf.getQteStock();
			} else {
				sheet3.addCell(new Label(10, i, "", boldRed));
			}
			
		  
			// quantite mouvement

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(11, i, element.getQuantite() + "", boldRed));

				sommeQuantiteMVT += element.getQuantite();
			} else {
				sheet3.addCell(new Label(11, i, "", boldRed));
			}
			


			i++;

		}

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "nombre des lignes ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
				resultatRechercheMouvementFini.getNombreResultaRechercher() + "", boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite totale ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantite + "", boldRed3));
		
		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite En Stock ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantiteEnStock + "", boldRed3));
		
		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite Mouvement ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantiteMVT + "", boldRed3));

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;
		// System.out.println("#####################################" + dateDe);
	}


	@SuppressWarnings("static-access")
	@RequestMapping(value = "/detailOf", method = RequestMethod.GET)
	public void generateDetailOfReportExel(

			@RequestParam(value = "clientId", required = false) String clientId,
			@RequestParam(value = "numOF", required = false) String numOF,
	

			HttpServletResponse response) throws WriteException, IOException {

		Date d = new Date();
		// FacesContextUtils context = FacesContext.getCurrentInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		String dat = "" + dateFormat.format(d);

		// this.rapport=true;
		File f = new File("rapport-mouvement-etat" + "-" + dat + ".xls");

		WritableWorkbook Equilibrageworkbook = Workbook.createWorkbook(f);
		Equilibrageworkbook.createSheet("production", 0);
		WritableSheet sheet3 = Equilibrageworkbook.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
		WritableCellFormat boldRed = new WritableCellFormat(font);
		// boldRed.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);
		boldRed.setWrap(true);
		boldRed.setBorder(jxl.format.Border.BOTTOM, BorderLineStyle.DOTTED);
		boldRed.setBorder(jxl.format.Border.RIGHT, BorderLineStyle.THIN);
		boldRed.setBorder(jxl.format.Border.LEFT, BorderLineStyle.THIN);
		boldRed.setAlignment(Alignment.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed2 = new WritableCellFormat(font2);
		boldRed2.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed2.setWrap(true);
		boldRed2.setAlignment(Alignment.CENTRE);

		WritableFont font3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldRed3 = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont font4 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
		WritableCellFormat boldEntete = new WritableCellFormat(font3);
		boldRed3.setWrap(true);
		boldRed3.setAlignment(Alignment.LEFT);

		WritableFont fontTitre = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat boldTitre = new WritableCellFormat(fontTitre);
		boldTitre.setWrap(true);
		boldTitre.setAlignment(Alignment.CENTRE);

		WritableFont font5 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat boldRed5 = new WritableCellFormat(font5);
		// boldRed5.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
		boldRed5.setWrap(true);
		boldRed5.setAlignment(Alignment.LEFT);

		sheet3.setColumnView(0, 7);
		sheet3.setColumnView(1, 7);
		
		sheet3.setColumnView(2, 15);// numOF

		sheet3.setColumnView(3, 15);// reference produit
		
		sheet3.setColumnView(4, 20); // designation of



		sheet3.setColumnView(5, 10); // couleur
		
		sheet3.setColumnView(6, 10); // taille
		
		sheet3.setColumnView(7, 10); // quantite
		
		
		sheet3.setColumnView(8, 10); // quantite Stock



		/**************************************************************************/

		// Récuperation de Nom du client active

		List<BaseInfoValue> baseInfoValues = baseInfoService.getAll();
		String clientDesigntion = "";
		for (BaseInfoValue baseInf : baseInfoValues) {
			if (baseInf.isActif()) {
				clientDesigntion = baseInf.getDesignation();
			}
		}

		sheet3.addCell(new Label(2, 2, clientDesigntion, boldRed5));

		// Nom du rapport et la date

		sheet3.addCell(new Label(2, 7, "Etat de Stock Fini", boldTitre));
		sheet3.mergeCells(2, 7, 8, 8);
		sheet3.addCell(new Label(2, 6, "Le : " + dateFormat2.format(d), boldRed3));

		// Critaire de recherche
		int numColCritRech = 2;
		int numLigneCritRech = 10;
		sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Critère de recherche", boldRed5));
		numLigneCritRech++;

		RechercheMulticritereDetailOfValue request = new RechercheMulticritereDetailOfValue();





		if (isNotEmty(numOF)) {

			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "N° O.F :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					numOF, boldRed3));
			
			
			request.setNumOF(numOF);

		}

		if (isNotEmty(clientId)) {
 
			numLigneCritRech++;
			sheet3.addCell(new Label(numColCritRech, numLigneCritRech, "Client :", boldRed3));
			sheet3.addCell(new Label(numColCritRech + 1, numLigneCritRech,
					partieInteresseeService.getById(Long.parseLong(clientId)).getAbreviation(), boldRed3));
			
			request.setClientId(Long.parseLong(clientId));
		}
		


		int i = numLigneCritRech + 4;

		sheet3.addCell(new Label(2, i - 1, "O.F", boldRed2));
		sheet3.addCell(new Label(3, i - 1, "Référence", boldRed2));
		sheet3.addCell(new Label(4, i - 1, "Désignation", boldRed2));
		sheet3.addCell(new Label(5, i - 1, "Couleur", boldRed2));
		sheet3.addCell(new Label(6, i - 1, "Taille", boldRed2));
		sheet3.addCell(new Label(7, i - 1, "Quantité", boldRed2));
		sheet3.addCell(new Label(8, i - 1, "Quantité En Stock", boldRed2));



		Long sommeQuantite = 0l;
		
		Long sommeQuantiteEnStock = 0l;
		
		ResultatMulticritereDetailOfValue resultatRecherche  = detailOfService.rechercherDetailOfMultiCritere(request);

		for (DetailOfValue element : resultatRecherche.getDetailOfValues()) {

			
			// numero of

			if (element.getNumOF() != null) {
				sheet3.addCell(new Label(2, i, element.getNumOF() + "", boldRed));

			} else {
				sheet3.addCell(new Label(2, i, "", boldRed));
			}

			// reference

			if (element.getReferenceProduit() != null) {
				sheet3.addCell(new Label(3, i, element.getReferenceProduit() + "", boldRed));

			} else {
				sheet3.addCell(new Label(3, i, "", boldRed));
			}

			// designation of

			if (element.getDesignationOF() != null) {
				sheet3.addCell(new Label(4, i, element.getDesignationOF() + "", boldRed));

			} else {
				sheet3.addCell(new Label(4, i, "", boldRed));
			}

		
			// couleur

			if (element.getCouleurDesignation() != null) {
				sheet3.addCell(new Label(5, i, element.getCouleurDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(5, i, "", boldRed));
			}

			// taille

			if (element.getTailleDesignation() != null) {
				sheet3.addCell(new Label(6, i, element.getTailleDesignation() + "", boldRed));

			} else {
				sheet3.addCell(new Label(6, i, "", boldRed));
			}

			// quantite

			if (element.getQuantite() != null) {
				sheet3.addCell(new Label(7, i, element.getQuantite() + "", boldRed));

				sommeQuantite += element.getQuantite();
			} else {
				sheet3.addCell(new Label(7, i, "", boldRed));
			}

			// quantite en stock

			if (element.getQteStock() != null) {
				sheet3.addCell(new Label(8, i, element.getQteStock() + "", boldRed));

				sommeQuantiteEnStock += element.getQteStock();
			} else {
				sheet3.addCell(new Label(8, i, "", boldRed));
			}
			
		


			i++;

		}

		/*******************************************
		 * BAS DU PAGE
		 ****************************************/

		int numColBasDuPage = 2;
		int numLigneBasDuPage = i + 2;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "nombre des lignes ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage,
				resultatRecherche.getNombreResultaRechercher() + "", boldRed3));

		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite totale ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantite + "", boldRed3));
		
		numLigneBasDuPage++;

		sheet3.mergeCells(numColBasDuPage, numLigneBasDuPage, numColBasDuPage + 1, numLigneBasDuPage);
		sheet3.addCell(new Label(numColBasDuPage, numLigneBasDuPage, "Quantite En Stock ", boldRed5));
		sheet3.addCell(new Label(numColBasDuPage + 2, numLigneBasDuPage, sommeQuantiteEnStock + "", boldRed3));

		Equilibrageworkbook.write();
		Equilibrageworkbook.close();

		/******************************************************************************************/

		/****************************
		 * Ouvrir le nouveau fichier généré
		 *******************************/

		// HttpServletResponse response = (HttpServletResponse)
		// context.getExternalContext().getResponse();
		response.setHeader("Content-disposition", "attachment; filename=" + f.getName());
		System.out.println("nom du fichier" + f.getName());
		response.setContentType("application/vnd.ms-excel");
		int bufferSize = 64 * 1024;
		byte[] buf = new byte[bufferSize];

		try {
			BufferedInputStream fileInBuf = new BufferedInputStream(new FileInputStream(f), bufferSize * 2);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int length;
			while ((length = fileInBuf.read(buf)) > 0) {
				baos.write(buf, 0, length);
			}
			response.getOutputStream().write(baos.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			// context.responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.rapport=false;
		// System.out.println("#####################################" + dateDe);
	}

	
	
	
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value) && !value.equals("undefined") && !value.equals("null");
	}
	
	private Calendar stringToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}
	
	private Calendar calendarStringToCalendarObject(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_CALENDAR_FORMAT, Locale.ENGLISH);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}
	
	public Double convertisseur(Double d, int nbchiffre) {

		Double result = d;
		if (d != null) {
			String number = d.toString();
			int point = number.lastIndexOf('.');
			point += nbchiffre;
			String subnumber = "";
			if (number.length() >= point) {
				subnumber = number.substring(0, point);
			} else
				return result;
			try {
				result = Double.parseDouble(subnumber);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
	}

}
