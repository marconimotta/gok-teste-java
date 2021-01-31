
package br.com.gok.templateapi.util;

import br.com.gok.templateapi.exception.APIException;

/**
 * Class for using generic methods in any system.
 *
 * @author Marconi Motta
 * @since 29 de jan de 2021
 */

public class GeneralUtil {

	/**
	 *
	 * Returns a valid Long value from a String.
	 *
	 * @param longString
	 * @param allowReturnNull
	 * @return Long
	 */
	public static Long getValidLong(final String longString, final boolean allowReturnNull) {
		try {
			return Long.valueOf(longString);
		} catch(final Exception e) {
			if(allowReturnNull) {
				return null;
			}
			throw new APIException(Constants.CONVERSION_ERROR) {};
		}
	}

	/**
	 *
	 * Returns a valid String value from a Long.
	 *
	 * @param longValue
	 * @param allowReturnNull
	 * @return String
	 */
	public static String getValidString(final Long longValue, final boolean allowReturnNull) {
		try {
			return String.valueOf(longValue);
		} catch(final Exception e) {
			if(allowReturnNull) {
				return null;
			}
			throw new APIException(Constants.CONVERSION_ERROR) {};
		}
	}

	/**
	 *
	 * Formats the message in the order sent from the parameters
	 *
	 * @param message
	 * @param args
	 * @return String
	 */
	public static String formatMessage(final String message, final Object... args) {
		return String.format(message, args);
	}
}
