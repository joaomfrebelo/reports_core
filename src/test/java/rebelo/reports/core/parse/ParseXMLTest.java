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
package rebelo.reports.core.parse;

import java.io.File;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.RRProperties;
import rebelo.reports.core.Report;

/**
 *
 * @author João Rebelo
 */
public class ParseXMLTest {
    
    public ParseXMLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.parse.ParseXML.class.getName(), Level.ALL);
        Configurator.setLevel(rebelo.reports.core.Report.class.getName(), Level.ALL);
        Configurator.setLevel(rebelo.reports.core.parse.AParse.class.getName(), Level.ALL);
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

    @Test
    public void testInstance() {        
        assertTrue(new ParseXML() instanceof ParseXML);
    }
    
    @Test
    public void testCreateReport() throws SAXException, NullNotAllowedException, Exception{
        
        System.out.println("testCreateReport");
        ClassLoader classLoader = getClass().getClassLoader();
        File xml = new File(classLoader.getResource("./parsexml/mysql_pdf_signed.xml").getFile());

        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        ParseXML parse = new ParseXML();

        parse.setOutputBaseDir(resDirPath + "/parsexml/generated_reports");
        parse.setJasperFileBaseDir(resDirPath + "/reports/sakila");
        parse.setKeyStoreBaseDir(resDirPath);
        
        RRProperties prop = parse.parse(xml);
        
        Report report = new Report();
        report.setProperties(prop);
        report.exportReport();
        
    }
    
}
