/**
 * 
 */
package it.maestrelli.test.utils;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.maestrelli.utils.ApachePoiUtils;

/**
 * @author z83-mini pc
 *
 */
public class ApachePOIUtilsTest {

	ApachePoiUtils apu;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		if(apu==null)apu = new ApachePoiUtils();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link it.maestrelli.utils.ApachePoiUtils#xlsGenerator(java.lang.String)}.
	 */
	@Test
	public final void testXlsGenerator() {
		
		assertEquals("Qualcosa non funziona", "",apu.xlsGenerator("") );
		
	}

	/**
	 * Test method for {@link it.maestrelli.utils.ApachePoiUtils#xmlGenerator(java.lang.String)}.
	 */
	@Test
	public final void testXmlGenerator() {
		assertEquals("Qualcosa non funziona", "",apu.xmlGenerator("") );
	}

}
