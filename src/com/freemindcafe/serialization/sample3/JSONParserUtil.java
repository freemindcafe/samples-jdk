/*
 * Copyright (c) 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013.
 * 
 * This is an unpublished work, is confidential and proprietary to
 * eMeter Corporation as a trade secret and is not to be used or
 * disclosed except as may be provided in an applicable eMeter Corporation
 * license agreement.
 * 
 * U.S. Government Rights. The software is commercial computer software, and
 * the accompanying documentation is commercial computer software documentation.
 * The terms and conditions of eMeter Corporation's license agreement are fully
 * applicable to the Government's use and disclosure of the software and
 * documentation, and shall supersede any conflicting terms or conditions.
 * No license of any kind is granted in the case of acquisitions which contain
 * or are subject to the clauses FAR 52-227.19 COMMERCIAL COMPUTER
 * SOFTWARE-RESTRICTED RIGHTS (JUNE 1987) or DFARS 252.227-7013 RIGHTS IN
 * TECHNICAL DATA AND COMPUTER SOFTWARE (OCT 1988) or any other clause which
 * purports to grant to the Government rights greater than, or additional to
 * those, set forth in such license agreement, or which purports to impose
 * additional requirements upon eMeter. If the license agreement fails to meet
 * the Government's stated needs or is inconsistent in any respect with federal
 * law, the Government agrees to return the software and documentation, unused,
 * to eMeter. The Licensor/Manufacturer is eMeter Corporation,
 * 4000 E. Third Ave., Fourth Floor Foster City, CA 94404 USA
 */
package com.freemindcafe.serialization.sample3;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

/**
 * The Class JSONParserUtil.
 *
 * @author Abhinav Solan
 */
public class JSONParserUtil {
	
	/**
	 * json to list of object
	 * @param json
	 * @param clazz
	 * @return the List<T>
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> List<T> jsonToListOfObject(final String json, final Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (List<T>) mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class,
				    clazz));
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * json to object
	 * @param json
	 * @param clazz
	 * @return T
	 */
	public static <T extends Object> T jsonToObject(final String json, final Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(json, clazz);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * object to json
	 * @param t
	 * @param clazz
	 * @return json string
	 */
	@SuppressWarnings("unchecked")
	public static <T> String objectToJson(final Object t, final Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString((T) t);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
}
