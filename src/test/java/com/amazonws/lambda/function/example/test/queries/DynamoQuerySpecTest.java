package com.amazonws.lambda.function.example.test.queries;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonws.lambda.function.example.test.entity.enums.EnumDeviceStatus;
import com.amazonws.lambda.function.example.test.entity.enums.EnumDeviceType;
import com.amazonws.lambda.function.example.util.Constants;

/**
 * 
 * @author Andres Solorzano
 * 
 */
public class DynamoQuerySpecTest {

	/** The LOGGER property for logger messages. */
	private static final Logger LOGGER = LogManager.getLogger(DynamoQuerySpecTest.class);
	
	private static final String SECTOR_ID = "SALA";
	
	private Index index = null;
	
	@Before
	public void init() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable(Constants.DYNAMODB_DEVICE_TABLE_NAME);
		this.index = table.getIndex(Constants.DYNAMODB_TABLE_SECTOR_INDEX);
	}
	
	@Test
	public void mustFindDevicesBySectorId() {
		QuerySpec spec = new QuerySpec()
				.withProjectionExpression("nombre, tipo, estado")
			    .withKeyConditionExpression("sectorId = :v_sector_id")
			    .withValueMap(new ValueMap()
			    		.withString(":v_sector_id", SECTOR_ID));
		ItemCollection<QueryOutcome> items = this.index.query(spec);
		
		Iterator<Item> iter = items.iterator(); 
		while (iter.hasNext()) {
			LOGGER.info(iter.next().toJSONPretty());
		}
	}
	
	@Test
	public void mustFindActuatorDevicesBySectorId() {
		QuerySpec spec = new QuerySpec()
		    .withKeyConditionExpression("sectorId = :v_sector_id")
		    .withFilterExpression("tipo = :v_device_type")
		    .withValueMap(new ValueMap()
		        .withString(":v_sector_id", SECTOR_ID)
		        .withString(":v_device_type", EnumDeviceType.ACTUADOR.name()));

		ItemCollection<QueryOutcome> items = this.index.query(spec);
		Iterator<Item> iter = items.iterator(); 
		while (iter.hasNext()) {
			LOGGER.info(iter.next().toJSONPretty());
		}
	}
	
	@Test
	public void mustFindActivatedDevicesBySectorId() {
		QuerySpec spec = new QuerySpec()
		    .withKeyConditionExpression("sectorId = :v_sector_id")
		    .withFilterExpression("estado = :v_device_status")
		    .withValueMap(new ValueMap()
		        .withString(":v_sector_id", SECTOR_ID)
		        .withString(":v_device_status", EnumDeviceStatus.ON.name()));

		ItemCollection<QueryOutcome> items = this.index.query(spec);
		Iterator<Item> iter = items.iterator(); 
		while (iter.hasNext()) {
			LOGGER.info(iter.next().toJSONPretty());
		}
	}
	
	

}
