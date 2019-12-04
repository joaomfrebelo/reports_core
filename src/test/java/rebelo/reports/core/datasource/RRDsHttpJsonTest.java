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

import java.net.URL;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.datasource.ARRDsHttp.Type;

/**
 *
 * @author João Rebelo
 */
public class RRDsHttpJsonTest {
    
    public RRDsHttpJsonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.datasource.RRDsHttpJson.class.getName(), Level.ALL);
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
     * Test of setGetEncode method, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetEncode() throws Exception {
        System.out.println("setGetEncode");
        String encode = "UTF-8";
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setEncode(encode);
        assertEquals(encode, rRDsHttpJson.getEncode());
    }

    /**
     * Test of setEncode method null, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetEncodeNull() throws Exception {
        try{
        System.out.println("setEncodeNull");
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setEncode(null);
        fail("set encode to null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of setDatePattern and getDatePattern method, of class RRDsHttpJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetDatePattern() throws Exception {
        System.out.println("setGetDatePattern");
        String patt = "YYYY-mm-dd";
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setDatePattern(patt);
        assertEquals(patt, rRDsHttpJson.getDatePattern());        
    }

    /**
     * Test of setNumberPattern and getNumberPattern method, of class RRDsHttpJson.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetNumberPattern() throws Exception {
        System.out.println("setGetNumberPattern");
        String patt = "##.00";
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setNumberPattern(patt);
        assertEquals(patt, rRDsHttpJson.getNumberPattern());        
    }
    
    /**
     * Test of setGetType method, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetType() throws Exception {
        System.out.println("setGetType");
        Type type = Type.GET;
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setType(type);
        assertEquals(type, rRDsHttpJson.getType());
    }

    /**
     * Test of setType method null, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetTypeNull() throws Exception {
        try{
        System.out.println("setTypeNull");
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setType(null);
        fail("set type to null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of setGetUrl method, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetUrl() throws Exception {
        System.out.println("setGetUrl");
        String uri = "http://www";
        URL url = new URL(uri);
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setUrl(url);
        assertEquals(url.toURI().getRawPath(), rRDsHttpJson.getUrl().toURI().getRawPath());
    }

    /**
     * Test of setUrl method null, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetUrlNull() throws Exception {
        try{
        System.out.println("setUrlNull");
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.setUrl(null);
        fail("set Url to null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }

    /**
     * Test of getType method null, of class RRDsHttpJson.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testGetUrlNull() throws Exception {
        try{
        System.out.println("setUrlNull");
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        rRDsHttpJson.getUrl();
        fail("get Url null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of setUrl method with wrong protocol, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetUrlWrongProtocol() throws Exception {
        try{
        System.out.println("setUrlWongProtocol");
        RRDsHttpJson rRDsHttpJson = new RRDsHttpJson();
        String uri = "https://www";
        URL url = new URL(uri);
        rRDsHttpJson.setUrl(url);
        fail("set Url to wrong protocol must throw DataSourceException exception");
        }catch(DataSourceException e){
            assertTrue(true);
        }
    }
}
