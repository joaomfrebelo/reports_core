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

import java.io.File;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;
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
public class RRXlsxPropertiesTest {
    
    public static RRXlsxProperties properties;
    
    public RRXlsxPropertiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws NullNotAllowedException, RRPropertiesException {
        Configurator.setLevel(rebelo.reports.core.RRXlsxProperties.class.getName(), Level.ALL);
        RRProperties rrProp = new RRProperties();
        ClassLoader classLoader = RRTextPropertiesTest.class.getClassLoader();
        File resDir = new File(classLoader.getResource("./").getFile());
        String out = new File(resDir.getAbsolutePath() +  "/generated_reports/test")
                .getAbsolutePath();
        rrProp.setOutputFile(out);
        properties = new RRXlsxProperties(rrProp);
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
     * Test of getSimpleXlsxExporterConfiguration method, of class RRXlsxProperties.
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @Test
    public void testGetSimpleXlsxExporterConfiguration() throws NullNotAllowedException {
        assertTrue(properties.getSimpleXlsxExporterConfiguration() instanceof SimpleXlsxExporterConfiguration);
    }
    
    /**
     * Test of getSimpleWriterExporterOutput method, of class RRCsvProperties.
     * @throws NullNotAllowedException 
     */
    @Test
    public void testGetSimpleOutputStreamExporterOutput()throws NullNotAllowedException {
        assertTrue(properties.getSimpleOutputStreamExporterOutput() instanceof SimpleOutputStreamExporterOutput);
    }
    
}
