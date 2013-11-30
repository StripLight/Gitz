package org.gitz.process;

import org.gitz.model.Manifest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class to load the manifest model from the given source file
 */
public class ModelLoader {


	/**
	 * Entry point
	 *
	 * Load the Gitz manifest
	 *
	 * @param sourceFile String
	 * @return Manifest
	 */
	public Manifest load(String sourceFile) {

		Manifest manifest = null;

		try {
			File file = new File(sourceFile);
			manifest = loadFileSet(file);

			manifest.postLoad();

		} catch (JAXBException je) {
			throw new GitzException("Failed to parse source manifest file.", je);
		}
		return manifest;

	}


	private Manifest loadFileSet(File file) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Manifest.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		return (Manifest) jaxbUnmarshaller.unmarshal(file);

	}
}
