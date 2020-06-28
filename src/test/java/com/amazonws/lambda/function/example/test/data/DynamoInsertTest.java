package com.amazonws.lambda.function.example.test.data;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonws.lambda.function.example.test.entity.DeviceEntity;
import com.amazonws.lambda.function.example.test.entity.enums.EnumDeviceStatus;
import com.amazonws.lambda.function.example.test.entity.enums.EnumDeviceType;

/**
 * 
 * @author Andres Solorzano
 * 
 */
public class DynamoInsertTest {

	private static final Logger LOGGER = LogManager.getLogger(DynamoInsertTest.class);

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client);

		// ************************************** //
		// ***************** SALA *************** //
		// ************************************** //
		DeviceEntity device1 = new DeviceEntity(1, "SALA", "Luz principal", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device1);

		DeviceEntity device2 = new DeviceEntity(2, "SALA", "Luz de lectura", EnumDeviceStatus.ON.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device2);

		
		// ************************************** //
		// *************** COMEDOR ************** //
		// ************************************** //
		DeviceEntity device3 = new DeviceEntity(3, "COMEDOR", "Luz principal", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device3);

		
		// ************************************** //
		// *************** COCINA *************** //
		// ************************************** //
		DeviceEntity device4 = new DeviceEntity(4, "COCINA", "Luz principal", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device4);

		DeviceEntity device5 = new DeviceEntity(5, "COCINA", "Luz de mesones", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device5);

		DeviceEntity device6 = new DeviceEntity(6, "COCINA", "Sensor de gas", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.SENSOR.name());
		dynamoDBMapper.save(device6);

		
		// ************************************** //
		// *************** JARDIN *************** //
		// ************************************** //
		DeviceEntity device7 = new DeviceEntity(7, "JARDIN", "Riego de agua", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device7);

		DeviceEntity device8 = new DeviceEntity(8, "JARDIN", "Sensor de movimiento", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.SENSOR.name());
		dynamoDBMapper.save(device8);

		
		// ************************************** //
		// *************** GARAJE *************** //
		// ************************************** //
		DeviceEntity device9 = new DeviceEntity(9, "GARAJE", "Luz principal", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.ACTUADOR.name());
		dynamoDBMapper.save(device9);

		DeviceEntity device10 = new DeviceEntity(10, "GARAJE", "Sensor de movimiento", EnumDeviceStatus.OFF.name(),
				EnumDeviceType.SENSOR.name());
		dynamoDBMapper.save(device10);

		
		LOGGER.info("DONE...");
	}
}
