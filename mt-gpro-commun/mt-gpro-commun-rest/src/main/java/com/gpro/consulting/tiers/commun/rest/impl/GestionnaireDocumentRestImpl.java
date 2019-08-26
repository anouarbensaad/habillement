package com.gpro.consulting.tiers.commun.rest.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erp.socle.j2ee.mt.socle.uploadFichier.IGestionnaireDocument;
import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentMetadata;
import com.erp.socle.j2ee.mt.socle.uploadFichier.value.DocumentPhysique;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping(value = "/gestionnaireDocument")
public class GestionnaireDocumentRestImpl {

  @Autowired
  private IGestionnaireDocument vGestionnaireDocument;

  /** Répertoire par défaut */
  public static final String DIRECTORY = "c:/ERP/archive/";
  
  /**
   * Constructeur de la classe.
   */
  public GestionnaireDocumentRestImpl() {
    // Constructeur vide
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody DocumentMetadata handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file,
    @RequestParam(value = "person", required = true) String person, @RequestParam (value= "module", required=true) String module ) {

    try {
      DocumentPhysique document = new DocumentPhysique(file.getBytes(), file.getOriginalFilename(), Calendar.getInstance().getTime(), person, module);
      vGestionnaireDocument.sauvegarderDocument(document);
      return document.getMetadata();
    } catch (RuntimeException e) {

      throw e;
    } catch (Exception e) {

      throw new RuntimeException(e);
    }
  }

//  @RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
//  public HttpEntity < byte[] > getDocument(@PathVariable String id, @PathParam(value = "module") String module) {
//    // L'envoie vers le client
//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//    return new ResponseEntity < byte[] >(vGestionnaireDocument.chargerDocumentBinaire(id, module), httpHeaders, HttpStatus.OK);
//  }
  
  
  	@RequestMapping(value = "/document/{id}", method = RequestMethod.GET)
  	public void getDocument(HttpServletResponse response, @PathVariable String id, @PathParam(value = "module") String module)throws IOException {
	  
	  	File file = null;
		DocumentPhysique documentPhysique = vGestionnaireDocument.chargerDocument(id,module);
		String EXTERNAL_FILE_PATH = DIRECTORY +module+"/"+id+"/"+documentPhysique.getNomFichier();
		file = new File(EXTERNAL_FILE_PATH);

		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			//System.out.println("mimetype is nolt detectable, will take default");
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);

		response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	  
  }
  
  /**
   * Accesseur en lecture de l'attribut <code>vGestionnaireDocument</code>.
   * 
   * @return IGestionnaireDocument L'attribut vGestionnaireDocument à lire.
   */
  public IGestionnaireDocument getvGestionnaireDocument() {
    return vGestionnaireDocument;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vGestionnaireDocument</code>.
   *
   * @param vGestionnaireDocument
   *          L'attribut vGestionnaireDocument à modifier.
   */
  public void setvGestionnaireDocument(IGestionnaireDocument vGestionnaireDocument) {
    this.vGestionnaireDocument = vGestionnaireDocument;
  }

}
