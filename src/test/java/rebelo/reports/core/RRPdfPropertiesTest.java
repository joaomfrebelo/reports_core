/**
 * Copyright (C) 2019  João M F Rebelo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rebelo.reports.core;

import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João Rebelo
 */
public class RRPdfPropertiesTest {
    
    public static RRPdfProperties properties;
    
    public RRPdfPropertiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NullNotAllowedException {
        Configurator.setLevel(rebelo.reports.core.RRPdfProperties.class.getName(), Level.ALL);
        RRProperties rrProp = new RRProperties();
        rrProp.setOutputFile("teste");
        properties = new RRPdfProperties(rrProp);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isSignPDF method, of class RRProperties.
     */
    @Test
    public void testIsSignPDF() {
        System.out.println("setIsSignPdf");
        try {
            assertFalse(properties.isSignPDF());
            
            properties.isSignPDF(true);
            assertTrue(properties.isSignPDF());
            
            properties.isSignPDF(false);
            assertFalse(properties.isSignPDF());
            
        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of isSignPDF null method, of class RRProperties.
     */
    @Test
    public void testIsSignPDFNull() {
        System.out.println("isSignPDF");
        try {
            properties.isSignPDF(null);
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failing set isVisible to null, should throw NullNot Allowed");
    }

    /**
     * Test of getSignProp method, of class RRProperties.
     */
    @Test
    public void testSetGetSignProp() {
        System.out.println("setGetSignProp");
        
        assertNull(properties.getSignProp());
        properties.setSignProp(new rebelo.reports.core.sign.RRSignPdfProperties());
        assertThat(properties.getSignProp(),
                instanceOf(rebelo.reports.core.sign.RRSignPdfProperties.class));
    }
   
    /**
     * Test of getSimplePdfExporterConfiguration method, of class RRPdfProperties.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSimplePdfExporterConfiguration() throws Exception {
        assertTrue(properties.getSimplePdfExporterConfiguration() instanceof SimplePdfExporterConfiguration);
    }
    
   
    /**
     * Test of getSimplePdfExporterConfiguration method, of class RRPdfProperties.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetSimpleOutputStreamExporterOutput() throws Exception {
        assertTrue(properties.getSimpleOutputStreamExporterOutput() instanceof SimpleOutputStreamExporterOutput);
    }

    
}
