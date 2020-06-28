package com.amazonws.lambda.function.example.dao;

import java.util.Iterator;
import java.util.Objects;

import org.json.JSONArray;

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
import com.amazonws.lambda.function.example.util.Constants;

/**
 *  
 * @author Andres Solorzano
 * 
 */
public class DeviceDAO {

	private static volatile DeviceDAO INSTANCE;

	private Index index = null;
	
	private DeviceDAO() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable(Constants.DYNAMODB_DEVICE_TABLE_NAME);
		this.index = table.getIndex(Constants.DYNAMODB_TABLE_SECTOR_INDEX);
	}

	public static DeviceDAO getInstance() {
		if (Objects.isNull(INSTANCE)) {
			synchronized (DeviceDAO.class) {
				if (Objects.isNull(INSTANCE)) { // Double Checking Locking
					INSTANCE = new DeviceDAO();
				}
			}
		}
		return INSTANCE;
	}

	public JSONArray findDevicesBySectorId(String sectorId) {
		QuerySpec spec = new QuerySpec()
			    .withKeyConditionExpression("sectorId = :v_sector_id")
			    .withValueMap(new ValueMap()
			    		.withString(":v_sector_id", sectorId));
		ItemCollection<QueryOutcome> items = this.index.query(spec);
		
		JSONArray jsonArray = new JSONArray();
		Iterator<Item> iter = items.iterator(); 
		while (iter.hasNext()) {
			jsonArray.put(iter.next().toJSON());
		}
		return jsonArray;
	}
}
