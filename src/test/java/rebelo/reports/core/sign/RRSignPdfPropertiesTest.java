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
package rebelo.reports.core.sign;

import com.itextpdf.text.Rectangle;
import org.apache.logging.log4j.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rebelo.reports.core.NullNotAllowedException;
import org.apache.logging.log4j.core.config.Configurator;

/**
 *
 * @author João Rebelo
 */
public class RRSignPdfPropertiesTest {
    
    public RRSignPdfPropertiesTest() {
    }
    
    protected static rebelo.reports.core.sign.RRSignPdfProperties properties;
    
    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(RRSignPdfProperties.class.getName(), Level.ALL);
        properties = new rebelo.reports.core.sign.RRSignPdfProperties();
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
     * Test of setType method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetType() {
        System.out.println("testSetGetType");
        try {
            System.out.println("setGetType");
            
//            properties.setType(RRSignPdfProperties.Type.CA);
//            assertEquals(RRSignPdfProperties.Type.CA, properties.getType());
            
            properties.setType(RRSignPdfProperties.Type.SELF);
            assertEquals(RRSignPdfProperties.Type.SELF, properties.getType());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting type to null
     */
    @Test 
    public void testSetTypeNull(){
        System.out.println("testSetTypeNull");
        try{
            properties.setType(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting type to null, should throw NullNotAllowed");
    }

    /**
     * test getting type while null
     */
    @Test 
    public void testGetTypeNull(){
        System.out.println("testGetTypeNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getType();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting type while null, should throw NullNotAllowed");
    }
    
    /**
     * Test of getLevel method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetLevel() {
        System.out.println("testSetGetLevel");
        try {
                        
            properties.setLevel(RRSignPdfProperties.Level.CERTIFIED_FORM_FILLING);
            assertEquals(RRSignPdfProperties.Level.CERTIFIED_FORM_FILLING, properties.getLevel());
            
            properties.setLevel(RRSignPdfProperties.Level.CERTIFIED_FORM_FILLING_AND_ANNOTATIONS);
            assertEquals(RRSignPdfProperties.Level.CERTIFIED_FORM_FILLING_AND_ANNOTATIONS, properties.getLevel());
            
            properties.setLevel(RRSignPdfProperties.Level.CERTIFIED_NO_CHANGES_ALLOWED);
            assertEquals(RRSignPdfProperties.Level.CERTIFIED_NO_CHANGES_ALLOWED, properties.getLevel());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting level to null
     */
    @Test 
    public void testSetLevelNull(){
        System.out.println("testSetLevelNull");
        try{
            properties.setLevel(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting level to null, should throw NullNotAllowed");
    }

    /**
     * test getting type while null
     */
    @Test 
    public void testGetLevelNull(){
        System.out.println("testGetLevelNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getLevel();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting level while null, should throw NullNotAllowed");
    }
    
    /**
     * Test of getJavaKeyStorePath method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetJavaKeyStorePath() {
        try {
            System.out.println("setGetCertificate");
            
            String cert = "/path/to/jks";
            properties.setJavaKeyStorePath(cert);
            assertEquals(cert, properties.getJavaKeyStorePath());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }


    /**
     * test setting certificate to null
     */
    @Test 
    public void testSetJavaKeyStorePathNull(){
        System.out.println("testSetCertificateNull");
        try{
            properties.setJavaKeyStorePath(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting certificate to null, should throw NullNotAllowed");
    }

    /**
     * test getting certificate while null
     */
    @Test 
    public void testGetJavaKeyStorePathNull(){
        System.out.println("testGetCertificateNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getJavaKeyStorePath();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting certificate while null, should throw NullNotAllowed");
    }
    
    /**
     * Test of get an set JavaKeyStorePassword method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetJavaKeyStorePassword() {
        try {
            System.out.println("setPassword");
            
            String value = "password";
            properties.setJavaKeyStorePassword(value);
            assertEquals(value, properties.getJavaKeyStorePassword());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting password to null
     */
    @Test 
    public void testSetPJavaKeyStorePasswordNull(){
        System.out.println("testSetPasswordNull");
        try{
            properties.setJavaKeyStorePassword(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting password to null, should throw NullNotAllowed");
    }

    /**
     * test getting password while null
     */
    @Test 
    public void testGetJavaKeyStorePasswordNull(){
        System.out.println("testGetPasswordNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getJavaKeyStorePassword();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting password while null, should throw NullNotAllowed");
    }
    
    /**
     * Test of getReazon method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetReazon() {
        System.out.println("testSetGetReazon");
        try {
            System.out.println("setGetReazon");
            
            assertEquals("", properties.getReazon());
            String value = "reazon";
            properties.setReazon(value);
            assertEquals(value, properties.getReazon());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting reazon to null
     */
    @Test 
    public void testSetReazonNull(){
        System.out.println("testSetReazonNull");
        try{
            properties.setReazon(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting reazon to null, should throw NullNotAllowed");
    }
    
    /**
     * Test of getLocation method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetLocation() {
        try {
            System.out.println("setGetLocation");
            
            assertEquals("", properties.getLocation());
            String value = "location";
            properties.setLocation(value);
            assertEquals(value, properties.getLocation());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting location to null
     */
    @Test 
    public void testSetLocationNull(){
        System.out.println("testSetLocationNull");
        try{
            properties.setLocation(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting location to null, should throw NullNotAllowed");
    }

    /**
     * Test of getContact method, of class RRSignPdfProperties.
     */
    @Test
    public void testGetContact() {
        try {
            System.out.println("setGetContact");
            
            assertEquals("", properties.getContact());
            String value = "contact";
            properties.setContact(value);
            assertEquals(value, properties.getContact());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting contact to null
     */
    @Test 
    public void testSetContactNull(){
        System.out.println("testSetContactNull");
        try{
            properties.setContact(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting contact to null, should throw NullNotAllowed");
    }

    /**
     * Test of isVisible method, of class RRSignPdfProperties.
     */
    @Test
    public void testIsVisible() {
        try {
            System.out.println("isVisible");
            
            assertFalse(properties.isVisible());
            
            properties.isVisible(true);
            assertTrue(properties.isVisible());
            
            properties.isVisible(false);
            assertFalse(properties.isVisible());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
        
    }

    /**
     * test setting visible to null
     */
    @Test 
    public void testSetVisibleNull(){
        System.out.println("testSetVisibleNull");
        try{
            properties.isVisible(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting visible to null, should throw NullNotAllowed");
    }

    /**
     * Test of getRectangle method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetRectangle() {
        System.out.println("setGetRectangle");
        
        assertNull(properties.getRectangle());
        Rectangle rect = new Rectangle(9, 19, 29, 39);
        properties.setRectangle(rect);
        assertEquals(rect, properties.getRectangle());
        
        properties.setRectangle(null);
        assertNull(properties.getRectangle());
    }
    
    /**
     * Test of getCertificateName( method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetCertificateName() {
        try {
            System.out.println("setGetCertificate");
            
            String cert = "certificate name";
            properties.setCertificateName(cert);
            assertEquals(cert, properties.getCertificateName());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting certificate to null
     */
    @Test 
    public void testSetCertificateNameNull(){
        System.out.println("testSetCertificateNameNull");
        try{
            properties.setCertificateName(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting certificate name to null, should throw NullNotAllowed");
    }

    /**
     * test getting certificate name while null
     */
    @Test 
    public void testGetCertificateNameNull(){
        System.out.println("testGetCertificateNameNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getCertificateName();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting certificate name while null, should throw NullNotAllowed");
    }
    
    /**
     * Test of getCertificatePassword method, of class RRSignPdfProperties.
     */
    @Test
    public void testSetGetCertificatePassword() {
        try {
            System.out.println("setCertificatePassword");
            
            String cert = "certificate password";
            properties.setCertificatePassword(cert);
            assertEquals(cert, properties.getCertificatePassword());
        } catch (NullNotAllowedException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * test setting certificate password to null
     */
    @Test 
    public void testSetCertificatePasswordNull(){
        System.out.println("testSetCertificatePasswordNull");
        try{
            properties.setCertificatePassword(null);
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng setting certificate password to null, should throw NullNotAllowed");
    }

    /**
     * test getting certificate password while null
     */
    @Test 
    public void testGetCertificatePasswordNull(){
        System.out.println("testGetCertificatePasswordNull");
        try{
            rebelo.reports.core.sign.RRSignPdfProperties prop = 
                    new rebelo.reports.core.sign.RRSignPdfProperties();
            prop.getCertificatePassword();
        }catch(rebelo.reports.core.NullNotAllowedException e){
            assertTrue(true);
            return;
        }
        fail("Failng getting certificate password while null, should throw NullNotAllowed");
    }
    
}
