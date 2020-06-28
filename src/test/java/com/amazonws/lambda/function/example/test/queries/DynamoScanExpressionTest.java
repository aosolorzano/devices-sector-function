package com.amazonws.lambda.function.example.test.queries;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonws.lambda.function.example.test.entity.DeviceEntity;
import com.amazonws.lambda.function.example.test.entity.enums.EnumDeviceStatus;

/**
 * 
 * @author Andres Solorzano
 * 
 */
public class DynamoScanExpressionTest {

	/** The LOGGER property for logger messages. */
	private static final Logger LOGGER = LogManager.getLogger(DynamoScanExpressionTest.class);

	private DynamoDBMapper dynamoDBMapper;
	
	@Before
	public void init() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		this.dynamoDBMapper = new DynamoDBMapper(client);
	}
	
	@Test
	public void mustFindAllActivatedDevices() {
		Map<String, AttributeValue> expressionAttributes = new HashMap<String, AttributeValue>();
		expressionAttributes.put(":estado", new AttributeValue().withS(EnumDeviceStatus.OFF.name()));
		
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withFilterExpression("estado = :estado")
				.withExpressionAttributeValues(expressionAttributes);
		
		List<DeviceEntity> scanResult = this.dynamoDBMapper.scan(DeviceEntity.class, scanExpression);
		assertTrue(!scanResult.isEmpty());
		
		for (DeviceEntity entity : scanResult) {
			LOGGER.info("FOUND => " + entity);
		}
	}

}
