package org.gitz.interfaces;

/**
 * Usage interface for manifest model objects
 */
public interface ManifestType {

	/**
	 * Answer the base folder loaded form the XML source
	 * @return	String
	 */
	String getBaseFolder();

	/**
	 * Answer the server path loaded form the XML source
	 * @return	String
	 */
	String getDefaultServerPath();
}
