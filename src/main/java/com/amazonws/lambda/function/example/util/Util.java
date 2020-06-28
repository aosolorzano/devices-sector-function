package com.amazonws.lambda.function.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.amazonws.lambda.function.example.exception.DeviceSectorException;

/**
 * 
 * @author Andres Solorzano
 */
public final class Util {

	private Util() {
		// Nothing to implement.
	}
	
	public static String getRequestEventAsString(InputStream inputStream) throws IOException {
		int letter;
		String eventObject = "";
		while ((letter = inputStream.read()) > -1) {
			char inputChar = (char) letter;
			eventObject += inputChar;
		}
		return eventObject;
	}
	
	public static String getQueryParameterValue(JSONObject request, String parameter) throws DeviceSectorException {
		JSONObject queryParameters = null;
		if (!request.has(Constants.QUERY_STRING_PARAMETERS)) {
			throw new DeviceSectorException(Constants.NO_PARAMETERS_FOUND_ERROR);
		}
		queryParameters = Util.getNestedJsonObjectFromRequestContext(request, Constants.QUERY_STRING_PARAMETERS);
		if (!queryParameters.has(parameter)) {
			throw new DeviceSectorException(Constants.PARAMETER_NOT_FOUND_ERROR + parameter);
		}
		return queryParameters.getString(parameter);
	}
	
	public static JSONObject getNestedJsonObjectFromRequestContext(JSONObject requestContextJSON, String property)
			throws DeviceSectorException {
		JSONObject nestedJson = requestContextJSON.getJSONObject(property);
		if (null == nestedJson) {
			throw new DeviceSectorException(Constants.NO_PROPERTY_VALUE_FOUND_IN_JSON_ERROR + property);
		}
		return nestedJson;
	}
	
	public static void setNotAcceptableInResponse(OutputStream outputStream, String messageDetail) throws IOException {
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(Constants.STATUS_CODE_PROPERTY, 406);
		jsonResponse.put(Constants.BODY_PROPERTY, messageDetail);
		outputStream.write(jsonResponse.toString().getBytes(StandardCharsets.UTF_8));
	}

	public static void setJsonResponseForApiGateway(OutputStream outputStream, String json) throws IOException {
		JSONObject headerJson = new JSONObject();
		headerJson.put("Access-Control-Allow-Origin", "*"); // MUST BE ADDED
		headerJson.put("Content-Type", "application/json");

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put(Constants.STATUS_CODE_PROPERTY, 200);
		jsonResponse.put(Constants.HEADERS_PROPERTY, headerJson);
		jsonResponse.put(Constants.BODY_PROPERTY, json); // MUST BE A STRING VALUE

		OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
		writer.write(jsonResponse.toString());
		writer.close();
	}
}
