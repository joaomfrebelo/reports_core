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
package rebelo.reports.core.datasource;

import java.io.File;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rebelo.reports.core.NullNotAllowedException;

/**
 *
 * @author João Rebelo
 */
public class RRDsFileXmlTest {

    public RRDsFileXmlTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.datasource.RRDsFileXml.class.getName(), Level.ALL);
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
     * Test of setFile method to null, of class RRDsFileXml.
     *
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null") 
    @Test
    public void testSetFileNull() throws Exception {
        try {
            System.out.println("setFileNull");
            RRDsFileXml rRDsFileXml = new RRDsFileXml();
            rRDsFileXml.setFile(null);
            fail("set file to null must throw NullNortAllowed exception");
        } catch (NullNotAllowedException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of getFile method to null, of class RRDsFileXml.
     *
     * @throws java.lang.Exception
     */ 
    @Test
    public void testGetFileNull() throws Exception {
        try {
            System.out.println("getFileNull");
            RRDsFileXml rRDsFileXml = new RRDsFileXml();
            rRDsFileXml.getFile();
            fail("get file to null must throw NullNortAllowed exception");
        } catch (NullNotAllowedException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of setDatePattern and getDatePattern method, of class RRDsFileXml.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetDatePattern() throws Exception {
        System.out.println("setGetDatePattern");
        String pattern = "YYYY-mm-dd";
        RRDsFileXml rRDsFileXml = new RRDsFileXml();
        rRDsFileXml.setDatePattern(pattern);
        assertEquals(pattern, rRDsFileXml.getDatePattern());
    }

}
