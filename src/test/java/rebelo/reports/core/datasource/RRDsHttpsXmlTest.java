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
public class RRDsHttpsXmlTest {
    
    public RRDsHttpsXmlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of setGetEncode method, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetEncode() throws Exception {
        System.out.println("setGetEncode");
        String encode = "UTF-8";
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setEncode(encode);
        assertEquals(encode, rRDsHttpsXml.getEncode());
    }

    /**
     * Test of setEncode method null, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetEncodeNull() throws Exception {
        try{
        System.out.println("setEncodeNull");
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setEncode(null);
        fail("set encode to null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of setDatePattern and getDatePattern method, of class RRDsHttpsXml.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetDatePattern() throws Exception {
        System.out.println("setGetDatePattern");
        String patt = "YYYY-mm-dd";
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setDatePattern(patt);
        assertEquals(patt, rRDsHttpsXml.getDatePattern());        
    }

    /**
     * Test of setNumberPattern and getNumberPattern method, of class RRDsHttpsXml.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetNumberPattern() throws Exception {
        System.out.println("setGetNumberPattern");
        String patt = "##.00";
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setNumberPattern(patt);
        assertEquals(patt, rRDsHttpsXml.getNumberPattern());        
    }
    
    /**
     * Test of setGetType method, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetType() throws Exception {
        System.out.println("setGetType");
        ARRDsHttps.Type type = ARRDsHttps.Type.GET;
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setType(type);
        assertEquals(type, rRDsHttpsXml.getType());
    }

    /**
     * Test of setType method null, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetTypeNull() throws Exception {
        try{
        System.out.println("setTypeNull");
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setType(null);
        fail("set type to null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
    
    /**
     * Test of setGetUrl method, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetGetUrl() throws Exception {
        System.out.println("setGetUrl");
        String uri = "https://www";
        URL url = new URL(uri);
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setUrl(url);
        assertEquals(url.toURI().getRawPath(), rRDsHttpsXml.getUrl().toURI().getRawPath());
    }

    /**
     * Test of setUrl method null, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testSetUrlNull() throws Exception {
        try{
        System.out.println("setUrlNull");
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.setUrl(null);
        fail("set Url to null must throw NullNortAllowed exception");
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
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        String uri = "http://www";
        URL url = new URL(uri);
        rRDsHttpsXml.setUrl(url);
        fail("set Url to wrong protocol must throw DataSourceException exception");
        }catch(DataSourceException e){
            assertTrue(true);
        }
    }

    /**
     * Test of getType method null, of class RRDsHttpsXml.
     * @throws java.lang.Exception
     */
    @SuppressWarnings("null")
    @Test
    public void testGetUrlNull() throws Exception {
        try{
        System.out.println("setUrlNull");
        RRDsHttpsXml rRDsHttpsXml = new RRDsHttpsXml();
        rRDsHttpsXml.getUrl();
        fail("get Url null must throw NullNortAllowed exception");
        }catch(NullNotAllowedException e){
            assertTrue(true);
        }
    }
}
