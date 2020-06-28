package com.amazonws.lambda.function.example.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonws.lambda.function.example.bo.DeviceBO;
import com.amazonws.lambda.function.example.exception.DeviceSectorException;
import com.amazonws.lambda.function.example.util.Constants;
import com.amazonws.lambda.function.example.util.Util;

/**
 * 
 * @author Andres Solorzano
 */
public class DeviceRequestHandler implements RequestStreamHandler {

	private static final Logger LOGGER = LogManager.getLogger(DeviceRequestHandler.class);

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
		try {
			JSONObject request = new JSONObject(Util.getRequestEventAsString(inputStream));
			LOGGER.debug("REQUEST EVENT: \n" + request);

			String sectorId = Util.getQueryParameterValue(request, Constants.SECTOR_ID_PROPERTY_NAME);
			JSONArray devicesList = DeviceBO.getInstance().findDevicesBySectorId(sectorId);

			// SET A RESPONSE
			Util.setJsonResponseForApiGateway(outputStream, devicesList.toString());
			
		} catch (JSONException | IOException | DeviceSectorException e) {
			LOGGER.error("ERROR: " + e.getMessage());
			try {
				Util.setNotAcceptableInResponse(outputStream, e.getMessage());
			} catch (IOException e1) {
				LOGGER.error("ERROR: " + e1.getMessage());
			}
		}
	}

}
