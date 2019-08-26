package com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value.InformationOfValue;

@Component
public class BonLivraisonFactureUtilities {

	
	public BonLivraisonReportProductValue copyBonLivraisonReportProductValue(BonLivraisonReportProductValue bl) {
		BonLivraisonReportProductValue blnew = new BonLivraisonReportProductValue();

		blnew.setChoix(bl.getChoix());
		blnew.setComposition(bl.getComposition());
		blnew.setDetLivraisonVenteId(bl.getDetLivraisonVenteId());

		InformationOfValue infOf = new InformationOfValue();

		infOf.setCodeDouane(bl.getInformationOfValue().getCodeDouane());
		infOf.setComposition(bl.getInformationOfValue().getComposition());

		infOf.setReferenceTissu1(bl.getInformationOfValue().getReferenceTissu1());
		infOf.setReferenceTissu2(bl.getInformationOfValue().getReferenceTissu2());
		infOf.setReferenceTissu3(bl.getInformationOfValue().getReferenceTissu3());
		infOf.setReferenceTissu4(bl.getInformationOfValue().getReferenceTissu4());

		infOf.setDesignationTissu1(bl.getInformationOfValue().getDesignationTissu1());
		infOf.setDesignationTissu2(bl.getInformationOfValue().getDesignationTissu2());
		infOf.setDesignationTissu3(bl.getInformationOfValue().getDesignationTissu3());
		infOf.setDesignationTissu4(bl.getInformationOfValue().getDesignationTissu4());

		infOf.setEmploiTissu1(bl.getInformationOfValue().getEmploiTissu1());
		infOf.setEmploiTissu2(bl.getInformationOfValue().getEmploiTissu2());
		infOf.setEmploiTissu3(bl.getInformationOfValue().getEmploiTissu3());
		infOf.setEmploiTissu4(bl.getInformationOfValue().getEmploiTissu4());

		infOf.setPrixTissu1(bl.getInformationOfValue().getPrixTissu1());
		infOf.setPrixTissu2(bl.getInformationOfValue().getPrixTissu2());
		infOf.setPrixTissu3(bl.getInformationOfValue().getPrixTissu3());
		infOf.setPrixTissu4(bl.getInformationOfValue().getPrixTissu4());

		infOf.setTypeTissu1(bl.getInformationOfValue().getTypeTissu1());
		infOf.setTypeTissu2(bl.getInformationOfValue().getTypeTissu2());
		infOf.setTypeTissu3(bl.getInformationOfValue().getTypeTissu3());
		infOf.setTypeTissu4(bl.getInformationOfValue().getTypeTissu4());

		blnew.setInformationOfValue(infOf);

		blnew.setMise(bl.getMise());
		blnew.setMontantHT(bl.getMontantHT());
		blnew.setNombreColis(bl.getNombreColis());
		blnew.setNumeroOF(bl.getNumeroOF());
		blnew.setPalette(bl.getPalette());
		blnew.setPrincipal(bl.getPrincipal());
		blnew.setPrixUHT(bl.getPrixUHT());
		blnew.setProduitCode(bl.getProduitCode());
		blnew.setProduitDesignation(bl.getProduitDesignation());
		blnew.setProduitId(bl.getProduitId());
		blnew.setQuantite(bl.getQuantite());
		blnew.setQuantiteColis(bl.getQuantiteColis());
		blnew.setRemise(bl.getRemise());
		blnew.setUnite(bl.getUnite());

		return blnew;

	}
	
	public List<BonLivraisonReportProductValue> ofbyTissu(
			List<BonLivraisonReportProductValue> bonLivraisonReportProductList) {
		List<BonLivraisonReportProductValue> listofbyTissu = new ArrayList<BonLivraisonReportProductValue>();

		Boolean testType ;
		for (BonLivraisonReportProductValue bl : bonLivraisonReportProductList) {
			
			testType = false ;
			if (bl.getInformationOfValue().getReferenceTissu1() != null
					&& !bl.getInformationOfValue().getReferenceTissu1().equals("")) {
				// System.out.println("######ReferenceTissu1####### " +
				// bl.getInformationOfValue().getReferenceTissu1());
				BonLivraisonReportProductValue bonliv1 = new BonLivraisonReportProductValue();

				bonliv1 = copyBonLivraisonReportProductValue(bl);
				bonliv1.setPrincipal(true);

				listofbyTissu.add(bonliv1);
				
				testType =true;

			}

			if (bl.getInformationOfValue().getReferenceTissu2() != null
					&& !bl.getInformationOfValue().getReferenceTissu2().equals("")) {
				// System.out.println("######ReferenceTissu2####### " +
				// bl.getInformationOfValue().getReferenceTissu2());
				BonLivraisonReportProductValue bonliv2 = new BonLivraisonReportProductValue();

				bonliv2 = copyBonLivraisonReportProductValue(bl);
				bonliv2.setPrincipal(false);

				bonliv2.getInformationOfValue().setReferenceTissu1(bl.getInformationOfValue().getReferenceTissu2());
				bonliv2.getInformationOfValue().setDesignationTissu1(bl.getInformationOfValue().getDesignationTissu2());
				bonliv2.getInformationOfValue().setEmploiTissu1(bl.getInformationOfValue().getEmploiTissu2());
				bonliv2.getInformationOfValue().setPrixTissu1(bl.getInformationOfValue().getPrixTissu2());
				bonliv2.getInformationOfValue().setTypeTissu1(bl.getInformationOfValue().getTypeTissu2());

				listofbyTissu.add(bonliv2);
				testType =true;

			}

			if (bl.getInformationOfValue().getReferenceTissu3() != null
					&& !bl.getInformationOfValue().getReferenceTissu3().equals("")) {
				// System.out.println("######ReferenceTissu3####### " +
				// bl.getInformationOfValue().getReferenceTissu3());
				BonLivraisonReportProductValue bonliv3 = new BonLivraisonReportProductValue();

				bonliv3 = copyBonLivraisonReportProductValue(bl);
				bonliv3.setPrincipal(false);

				bonliv3.getInformationOfValue().setReferenceTissu1(bl.getInformationOfValue().getReferenceTissu3());
				bonliv3.getInformationOfValue().setDesignationTissu1(bl.getInformationOfValue().getDesignationTissu3());
				bonliv3.getInformationOfValue().setEmploiTissu1(bl.getInformationOfValue().getEmploiTissu3());
				bonliv3.getInformationOfValue().setPrixTissu1(bl.getInformationOfValue().getPrixTissu3());
				bonliv3.getInformationOfValue().setTypeTissu1(bl.getInformationOfValue().getTypeTissu3());
				listofbyTissu.add(bonliv3);
				testType =true;

			}

			if (bl.getInformationOfValue().getReferenceTissu4() != null
					&& !bl.getInformationOfValue().getReferenceTissu4().equals("")) {
				// System.out.println("######ReferenceTissu4####### " +
				// bl.getInformationOfValue().getReferenceTissu4());
				BonLivraisonReportProductValue bonliv4 = new BonLivraisonReportProductValue();

				bonliv4 = copyBonLivraisonReportProductValue(bl);
				bonliv4.setPrincipal(false);

				bonliv4.getInformationOfValue().setReferenceTissu1(bl.getInformationOfValue().getReferenceTissu4());
				bonliv4.getInformationOfValue().setDesignationTissu1(bl.getInformationOfValue().getDesignationTissu4());
				bonliv4.getInformationOfValue().setEmploiTissu1(bl.getInformationOfValue().getEmploiTissu4());
				bonliv4.getInformationOfValue().setPrixTissu1(bl.getInformationOfValue().getPrixTissu4());
				bonliv4.getInformationOfValue().setTypeTissu1(bl.getInformationOfValue().getTypeTissu4());
				listofbyTissu.add(bonliv4);
				
				testType =true;

			}
			
			if (!testType)
			{
				BonLivraisonReportProductValue bonliv2 = new BonLivraisonReportProductValue();

				bonliv2 = copyBonLivraisonReportProductValue(bl);
				bonliv2.setPrincipal(false);

				bonliv2.getInformationOfValue().setReferenceTissu1("N.D");
				bonliv2.getInformationOfValue().setDesignationTissu1(bl.getInformationOfValue().getDesignationTissu1());
				bonliv2.getInformationOfValue().setEmploiTissu1(bl.getInformationOfValue().getEmploiTissu1());
				bonliv2.getInformationOfValue().setPrixTissu1(bl.getInformationOfValue().getPrixTissu1());
				bonliv2.getInformationOfValue().setTypeTissu1("N.D");

				listofbyTissu.add(bonliv2);
			}
		}

		return listofbyTissu;
	}

	public List<BonLivraisonReportProductValue> ofbyTypeTissu(
			List<BonLivraisonReportProductValue> bonLivraisonReportProductList, String typeTissu) {
		List<BonLivraisonReportProductValue> listofbyTissu = new ArrayList<BonLivraisonReportProductValue>();

		for (BonLivraisonReportProductValue bl : bonLivraisonReportProductList) {

			if (bl.getInformationOfValue().getTypeTissu1().equals(typeTissu)) {
				listofbyTissu.add(bl);
			}

		}
		return listofbyTissu;

	}
	
	
}
