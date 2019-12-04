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

import net.sf.jasperreports.export.SimpleRtfExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
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
public class RRRtfPropertiesTest {
    
    public static RRRtfProperties properties;
    
    public RRRtfPropertiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NullNotAllowedException {
        Configurator.setLevel(rebelo.reports.core.RRRtfProperties.class.getName(), Level.ALL);
        RRProperties rrProp = new RRProperties();
        rrProp.setOutputFile("teste");
        properties = new RRRtfProperties(rrProp);
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
     * Test of setSimpleRtfExporterConfiguration method, of class RRRtfProperties.
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @Test
    public void testGetSimpleRtfExporterConfiguration() throws NullNotAllowedException {
        assertTrue(properties.getSimpleRtfExporterConfiguration() instanceof SimpleRtfExporterConfiguration);
    }

    /**
     * Test of getSimpleWriterExporterOutput method, of class RRCsvProperties.
     * @throws NullNotAllowedException 
     */
    @Test
    public void testGetSimpleWriterExporterOutput()throws NullNotAllowedException {
        assertTrue(properties.getSimpleWriterExporterOutput() instanceof SimpleWriterExporterOutput);
    }
    
}
