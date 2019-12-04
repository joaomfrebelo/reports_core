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
public class RRDsDatabaseTest {

    public static rebelo.reports.core.datasource.RRDsDatabase properties;

    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.datasource.RRDsDatabase.class.getName() , Level.ALL);
        properties = new rebelo.reports.core.datasource.RRDsDatabase();
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
     * Test of getDriver method, of class RRDsDatabase.
     */
    @Test
    public void testSetGetDriver() {
        System.out.println("getDriver");
        try {
            String value = "com.mysql.jdbc.Driver";
            properties.setDriver(value);
            assertEquals(value, properties.getDriver());
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * test set driver to null
     */
    @Test
    public void setDriverNull() {
        System.out.println("setDriverNull");
        try {
            properties.setDriver(null);
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Seting driver to null didn't throw exception");
    }

    /**
     * Test get driver while null
     */
    @Test
    public void getDriverNull() {
        System.out.println("getDriverNull");
        try {
            rebelo.reports.core.datasource.RRDsDatabase prop = 
                    new rebelo.reports.core.datasource.RRDsDatabase();
            prop.getDriver();
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Geting driver to null didn't throw exception");
    }

    /**
     * Test of getConnString method, of class RRDsDatabase.
     */
    @Test
    public void testSetGetConnString() {
        System.out.println("getConnString");

        try {
            String value = "jdbc:mysql:://127.0.0.1:3306/database";
            properties.setConnString(value);
            assertEquals(value, properties.getConnString());
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * test set connection string to null
     */
    @Test
    public void setConnStringNull() {
        System.out.println("setConnStringNull");
        try {
            properties.setConnString(null);
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Seting connection string to null didn't throw exception");
    }

    /**
     * test get connection string while null
     */
    @Test
    public void getConnStringNull() {
        System.out.println("getConnStringNull");
        try {
            rebelo.reports.core.datasource.RRDsDatabase prop = 
                    new rebelo.reports.core.datasource.RRDsDatabase();
            prop.getConnString();
        } catch (rebelo.reports.core.NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Geting connection string to null didn't throw exception");
    }

    /**
     * Test of getUser method, of class RRDsDatabase.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");

        assertNull(properties.getUser());
        String value = "user";
        properties.setUser(value);
        assertEquals(value, properties.getUser());
    }

    /**
     * Test of getPassword method, of class RRDsDatabase.
     */
    @Test
    public void testSetGetPassword() {
        System.out.println("getPassword");

        assertNull(properties.getPassword());
        String value = "password";
        properties.setPassword(value);
        assertEquals(value, properties.getPassword());
    }

}
