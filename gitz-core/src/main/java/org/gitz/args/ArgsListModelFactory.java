package org.gitz.args;

import java.util.HashSet;
import java.util.Set;

/**
 * Static factory to create args list model instances
 */
public final class ArgsListModelFactory {

	private ArgsListModelFactory() {
	}

	/**
	 * Create a disallowed list model
	 *
	 * @param args String[]
	 * @return DisallowedArgsListModel
	 */
	public static DisallowedArgsListModel createDisallowed(String args[]) {
		return new DisallowedArgsListModel(getData(args));
	}


	/**
	 * Create allowed list model
	 *
	 * @param args String[]
	 * @return AllowedArgsListModel
	 */
	public static AllowedArgsListModel createAllowed(String args[]) {
		return new AllowedArgsListModel(getData(args));
	}

	/**
	 * Create allowed list model using allowed and required data
	 *
	 * @param args  String[]
	 * @param required      String[][]
	 * @return AllowedArgsListModel
	 */
	public static AllowedArgsListModel createAllowed(String args[], String[][] required) {
		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(getData(required));
		return new AllowedArgsListModel(getData(args), requiredArgsListModel);
	}


	/**
	 * Create allowed model using allowed data and a required model
	 *
	 * @param args                  String[]
	 * @param requiredArgsListModel RequiredArgsListModel
	 * @return    AllowedArgsListModel
	 */
	public static AllowedArgsListModel createAllowed(String args[], RequiredArgsListModel requiredArgsListModel) {
		return new AllowedArgsListModel(getData(args), requiredArgsListModel);
	}

	/**
	 * Create required model
	 *
	 * @param args String[][]
	 * @return  RequiredArgsListModel
	 */
	public static RequiredArgsListModel createRequired(String args[][]) {
		return new RequiredArgsListModel(getData(args));
	}


	private static Set<String> getData(String source[]) {

		Set<String> dataSet = new HashSet<String>();
		for (String s : source) {
			dataSet.add(s);
		}

		return dataSet;
	}

	private static Set<String[]> getData(String source[][]) {

		Set<String[]> dataSet = new HashSet<String[]>();
		for (String s[] : source) {
			dataSet.add(s);
		}

		return dataSet;
	}

}
