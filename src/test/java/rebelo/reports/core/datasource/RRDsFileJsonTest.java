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
public class RRDsFileJsonTest {
    
    public RRDsFileJsonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.datasource.RRDsFileJson.class.getName() , Level.ALL);
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
     * Test of setFile asnd getFile method, of class RRDsFileJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetFile() throws Exception {
        System.out.println("setGetFile");
        String path = "c:\\path\\for\\file";
        File file = new File(path);
        RRDsFileJson rRDsFileJson = new RRDsFileJson();
        rRDsFileJson.setFile(file);
        assertEquals(path, rRDsFileJson.getFile().getAbsolutePath());        
    }

    /**
     * Test of setFile method to null, of class RRDsFileJson.
     * 
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSeFileNull() throws Exception {
        System.out.println("setFileNull");
        try {
            RRDsFileJson rRDsFileJson = new RRDsFileJson();
            rRDsFileJson.setFile(null);
            fail("set file to null must throw NullNortAllowed exception");            
        } catch (NullNotAllowedException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of getFile method while null, of class RRDsFileJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetFileNull() throws Exception {
        System.out.println("getFileNull");
        try {
            RRDsFileJson rRDsFileJson = new RRDsFileJson();
            rRDsFileJson.getFile();
            fail("get file to null must throw NullNortAllowed exception");            
        } catch (NullNotAllowedException e) {
            assertTrue(true);
        }       
    }

    /**
     * Test of setDatePattern and getDatePattern method, of class RRDsFileJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetDatePattern() throws Exception {
        System.out.println("setGetDatePattern");
        String patt = "YYYY-mm-dd";
        RRDsFileJson rRDsFileJson = new RRDsFileJson();
        rRDsFileJson.setDatePattern(patt);
        assertEquals(patt, rRDsFileJson.getDatePattern());        
    }

    /**
     * Test of setNumberPattern and getNumberPattern method, of class RRDsFileJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetNumberPattern() throws Exception {
        System.out.println("setGetNumberPattern");
        String patt = "##.00";
        RRDsFileJson rRDsFileJson = new RRDsFileJson();
        rRDsFileJson.setNumberPattern(patt);
        assertEquals(patt, rRDsFileJson.getNumberPattern());        
    }

}
