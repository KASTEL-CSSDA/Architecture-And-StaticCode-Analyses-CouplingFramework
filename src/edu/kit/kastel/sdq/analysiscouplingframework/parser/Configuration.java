package edu.kit.kastel.sdq.analysiscouplingframework.parser;

import java.io.File;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import edu.kit.kastel.sdq.analysiscouplingframework.Logger;
import edu.kit.kastel.sdq.analysiscouplingframework.Session;
import edu.kit.kastel.sdq.analysiscouplingframework.exceptions.MissingPathIdentifierException;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.Entrypoint;
import edu.kit.kastel.sdq.analysiscouplingframework.processing.ProcessingStrategy;

public abstract class Configuration {
	private final String TAG_NAME_ENTRYPOINT = "entrypoint";
	private final String TAG_NAME_TEMP_SHARED_FOLDER = "tempSharedFolder";
	private final String TAG_NAME_RESULT_SHARED_FOLDER = "resultSharedFolder";
	private final String TAG_NAME_UUID = "uuid";
	private final String ITEM_CONTENT_PENDING = "";
	private final String TAG_NAME_PATHOFID = "pathOfID";
	private final String ITEM_NAME_ID = "id";

	protected Registry registry;
	protected Entrypoint entrypoint;
	protected UUID uuid;
	protected final String configFile;

	public Configuration(final String configFile) {
		this.registry = new Registry();
		this.configFile = configFile;
		this.parseInput();
	}

	private void parseInput() {

		Document document = this.getDocumentByFilePath();

		String entrypointToContinue = document.getElementsByTagName(TAG_NAME_ENTRYPOINT).item(0).getTextContent();
		this.entrypoint = Entrypoint.valueOf(entrypointToContinue);

		this.getOrCreateUuid(document);
		
		String pathTempSharedFolder = document.getElementsByTagName(TAG_NAME_TEMP_SHARED_FOLDER).item(0).getTextContent();
		String pathResultSharedFolder = document.getElementsByTagName(TAG_NAME_RESULT_SHARED_FOLDER).item(0).getTextContent();
		this.registry.initSharedFolders(pathTempSharedFolder, pathResultSharedFolder);

		NodeList idNodeList = document.getElementsByTagName(TAG_NAME_PATHOFID);
		for (int i = 0; i < idNodeList.getLength(); i++) {
			String id = idNodeList.item(i).getAttributes().getNamedItem(ITEM_NAME_ID).getTextContent();
			String path = idNodeList.item(i).getTextContent();
			this.registry.put(id, path);
		}
	}

	private Document getDocumentByFilePath() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setValidating(false);

			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(this.configFile);
		} catch (Exception e) {
			System.out.println("Error reading configuration file:");
			System.out.println(e.getMessage());
		}
		return null;
	}

	private void saveDocumentByFilePath(Document document) {
		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult sr = new StreamResult(new File(this.configFile));
			tf.transform(domSource, sr);
		} catch (Exception e) {
			System.out.println("Error writing configuration file:");
			System.out.println(e.getMessage());
		}
	}

	protected abstract ProcessingStrategy createStrategy() throws MissingPathIdentifierException;

	public Session createSession(Logger logger) throws MissingPathIdentifierException {
		ProcessingStrategy strategy = this.createStrategy();
		return new Session(strategy, this.entrypoint, logger, this.uuid);
	}

	private void getOrCreateUuid(Document document) {
		String uuidOfConfig = document.getElementsByTagName(TAG_NAME_UUID).item(0).getTextContent();
		this.uuid = (uuidOfConfig.equals(ITEM_CONTENT_PENDING)) ? UUID.randomUUID() : UUID.fromString(uuidOfConfig);
		document.getElementsByTagName(TAG_NAME_UUID).item(0).setTextContent(this.uuid.toString());

		this.saveDocumentByFilePath(document);
	}

	public void saveEntrypointForNextExecution(Entrypoint entrypointForNextExecution) {
		Document document = this.getDocumentByFilePath();

		document.getElementsByTagName(TAG_NAME_ENTRYPOINT).item(0).setTextContent(entrypointForNextExecution.name());
		
		this.saveDocumentByFilePath(document);
	}
}
