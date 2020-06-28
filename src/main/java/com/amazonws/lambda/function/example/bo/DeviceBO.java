package com.amazonws.lambda.function.example.bo;

import java.util.Objects;

import org.json.JSONArray;

import com.amazonws.lambda.function.example.dao.DeviceDAO;

/**
 * 
 * @author Andres Solorzano
 * 
 */
public class DeviceBO {

	private static volatile DeviceBO INSTANCE;

	private DeviceBO() {
		// Nothing to implement
	}

	public static DeviceBO getInstance() {
		if (Objects.isNull(INSTANCE)) {
			synchronized (DeviceBO.class) {
				if (Objects.isNull(INSTANCE)) { // Double Checking Locking
					INSTANCE = new DeviceBO();
				}
			}
		}
		return INSTANCE;
	}

	public JSONArray findDevicesBySectorId(String sectorId) {
		return DeviceDAO.getInstance().findDevicesBySectorId(sectorId);
	}

}
