package com.amazonws.lambda.function.example.test.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.amazonws.lambda.function.example.handler.DeviceRequestHandler;

/**
 * 
 * @author Andres Solorzano
 * 
 */
public class HandlerFunctionTest {

	/** The LOGGER property for logger messages. */
	private static final Logger LOGGER = LogManager.getLogger(HandlerFunctionTest.class);

	private DeviceRequestHandler handler;

	@Before
	public void init() {
		this.handler = new DeviceRequestHandler();
	}

	@Test
	public void mustFindDevicesBySectorId() {
		InputStream inputStream = HandlerFunctionTest.class.getClassLoader()
				.getResourceAsStream("http-get-devices-request-event.json");
		OutputStream outputStream = new OutputStream() {
			private StringBuilder string = new StringBuilder();
			
			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b );
			}
			
			public String toString(){
	            return this.string.toString();
	        }
		};
		
		this.handler.handleRequest(inputStream, outputStream, null);
		LOGGER.info(outputStream.toString());
	}

}
