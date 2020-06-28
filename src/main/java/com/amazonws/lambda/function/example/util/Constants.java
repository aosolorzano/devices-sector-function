package com.amazonws.lambda.function.example.util;

/**
 * 
 * @author Andres Solorzano
 */
public final class Constants {

	private Constants() {
		// Nothing to implement
	}

	public static final String QUERY_STRING_PARAMETERS = "queryStringParameters";

	// JSON REQUEST AND RESPONSE PROPERTIES
	public static final String STATUS_CODE_PROPERTY = "statusCode";
	public static final String BODY_PROPERTY = "body";
	public static final String HEADERS_PROPERTY = "headers";

	// ERROR MESSAGES
	public static final String NO_PARAMETERS_FOUND_ERROR = "NO PARAMETERS FOUND IN THE REQUEST HEADER.";
	public static final String PARAMETER_NOT_FOUND_ERROR = "PARAMETER NOT FOUND IN THE REQUEST HEADER: ";
	public static final String NO_PROPERTY_VALUE_FOUND_IN_JSON_ERROR = "NO PROPERTY VALUE FOUND IN THE JSON REQUEST: ";

	// DEVICE OPERATION JSON PROPERTIES
	public static final String SECTOR_ID_PROPERTY_NAME = "sectorId";

	// DYNAMODB TABLES AND INDEXES
	public static final String DYNAMODB_TABLE_SECTOR_INDEX = "sectorId_index";
	public static final String DYNAMODB_DEVICE_TABLE_NAME = "Dispositivos";
}
