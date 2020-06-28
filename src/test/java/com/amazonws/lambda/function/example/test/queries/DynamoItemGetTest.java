/**
 * Product  : Hiperium Project
 * Architect: Andres Solorzano.
 * Created  : 08-05-2009 - 23:30:00
 * 
 * The contents of this file are copyrighted by Andres Solorzano 
 * and it is protected by the license: "GPL V3." You can find a copy of this 
 * license at: http://www.hiperium.com/about/licence.html
 * 
 * Copyright 2014 Andres Solorzano. All rights reserved.
 * 
 */
package com.amazonws.lambda.function.example.test.queries;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonws.lambda.function.example.util.Constants;

/**
 * This test query the Device table in AWS DinamoDB.
 * 
 * @author Andres Solorzano
 * 
 */
public class DynamoItemGetTest {

	/** The LOGGER property for logger messages. */
	private static final Logger LOGGER = LogManager.getLogger(DynamoItemGetTest.class);

	private static final String SECTOR_ID = "SALA";
	private Table table;
	
	@Before
	public void init() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);
		this.table = dynamoDB.getTable(Constants.DYNAMODB_DEVICE_TABLE_NAME);
	}
	
	@Test
	public void mustFindDeviceItemTest() {
		Item item = this.table.getItem("id", 1, "sectorId", SECTOR_ID);
		assertNotNull(item);
		LOGGER.info("FOUND DEVICE WITH THE FOLLOWING VALUES:\n" + item.toJSONPretty());
	}
	
	@Test
	public void mustNotFindDeviceItemTest() {
		Item item = this.table.getItem("id", 3, "sectorId", SECTOR_ID);
		assertNull(item);
		LOGGER.info("No item found with the key: " + 3);
	}

}
