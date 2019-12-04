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

import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
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
public class RRPrintPropertiesTest {
    
    public static RRPrintProperties prop; 
    
    public RRPrintPropertiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         Configurator.setLevel(rebelo.reports.core.RRPrintProperties.class.getName(), Level.ALL);
         RRPrintPropertiesTest.prop = new RRPrintProperties();
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
     * Test of getSimplePrintServiceExporterConfiguration method, of class RRPrintProperties.
     */
    @Test
    public void testGetSimPrintExpConf() {
        assertTrue(prop.getSimplePrintServiceExporterConfiguration() instanceof SimplePrintServiceExporterConfiguration);
    }

    /**
     * Test of selectedPrinter method, of class RRPrintProperties.
     */
    @Test
    public void testSelectedPrinter() {
        String prt = "RR teste";
        prop.setSelectedPrinter(prt);
        assertEquals(prop.getSelectedPrinter(), prt);
    }

    /**
     * Test of getPrintRequestAttributeSet method, of class RRPrintProperties.
     */
    @Test
    public void testGetPrintRequestAttributeSet() {
        assertTrue(prop.getPrintRequestAttributeSet() instanceof PrintRequestAttributeSet);
    }

    /**
     * Test of getPrintServiceAttributeSet method, of class RRPrintProperties.
     */
    @Test
    public void testGetPrintServiceAttributeSet() {
       assertTrue(prop.getPrintServiceAttributeSet() instanceof PrintServiceAttributeSet);
    }
    
}
