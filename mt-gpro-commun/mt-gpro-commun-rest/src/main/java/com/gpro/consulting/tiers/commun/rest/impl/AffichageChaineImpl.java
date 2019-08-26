package com.gpro.consulting.tiers.commun.rest.impl;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * affichage chaine Controller
 * 
 * @author hetteieb
 *
 */

@Controller
@RequestMapping("/affichagechaine")
public class AffichageChaineImpl {

	private static final Logger logger = LoggerFactory.getLogger(AffichageChaineImpl.class);

	@RequestMapping(value = "/chaine1rendement", method = RequestMethod.POST, headers = "application/json")
	public void rendementChaine1(@RequestParam String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine1Rendement.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/Chaine2Rendement", method = RequestMethod.POST, headers = "application/json")
	public void rendementChaine2(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine2Rendement.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/Chaine3Rendement", method = RequestMethod.POST, headers = "application/json")
	public void rendementChaine3(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine3Rendement.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/Chaine4Rendement", method = RequestMethod.POST, headers = "application/json")
	public void rendementChaine4(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine4Rendement.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	// green chaine
	@RequestMapping(value = "/greenChaine1", method = RequestMethod.POST, headers = "application/json")
	public void greenChaine1(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chainePositif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/greenChaine2", method = RequestMethod.POST, headers = "application/json")
	public void greenChaine2(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine2Positif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/greenChaine3", method = RequestMethod.POST, headers = "application/json")
	public void greenChaine3(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine3Positif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/greenChaine4", method = RequestMethod.POST, headers = "application/json")
	public void greenChaine4(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine4Positif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/greenChainePrep", method = RequestMethod.POST, headers = "application/json")
	public void greenChainePrep(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/prep.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	// red chaine
	@RequestMapping(value = "/redChaine1", method = RequestMethod.POST, headers = "application/json")
	public void redChaine1(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaineNegatif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/redChaine2", method = RequestMethod.POST, headers = "application/json")
	public void redChaine2(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine2Negatif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/redChaine3", method = RequestMethod.POST, headers = "application/json")
	public void redChaine3(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine3Negatif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/redChaine4", method = RequestMethod.POST, headers = "application/json")
	public void redChaine4(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine4Negatif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/redChainePrep", method = RequestMethod.POST, headers = "application/json")
	public void redChainePrep(@RequestBody String chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/prepNegatif.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/ChaineDeuxRendement", method = RequestMethod.POST, headers = "application/json")
	public void rendementChaineDeux(@RequestBody Object chaine) {
		Log.info("ici");
		PrintWriter writer;
		try {
			writer = new PrintWriter("C:/ERP/chaine2Rendement.json", "UTF-8");
			writer.println(chaine);

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/test", method = RequestMethod.GET, headers = "application/json")
	public void test() {
		//System.out.println("###### TEST #######");

	}
}
