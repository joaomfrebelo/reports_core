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
import java.io.File;
import java.io.IOException;
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
public class RRSignPdfTest {

    public RRSignPdfTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(RRSignPdfProperties.class.getName(), Level.ALL);
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
     * Test of signPdf method, of class RRSignPdf.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testSignPdf() throws IOException {
        try {
            System.out.println("testSignPdf");
          
            ClassLoader classLoader = getClass().getClassLoader();
            File jks = new File(classLoader.getResource("./certificates/keystore.ks").getFile());
            File nosignpdf = new File(classLoader.getResource("./signpdf/no_signed.pdf").getFile());
            String dir = classLoader.getResource("./signpdf").getPath();
            File signpdf = new File(dir + "/signed.pdf");
            if (signpdf.exists()) {
                signpdf.delete();
            }

            RRSignPdfProperties signProp = new RRSignPdfProperties();

            signProp.setCertificateName("rreports");
            signProp.setCertificatePassword("password");
            signProp.setContact("rrports contact");
            signProp.setJavaKeyStorePassword("password");
            signProp.setJavaKeyStorePath(jks.getAbsolutePath());
            signProp.setLevel(RRSignPdfProperties.Level.CERTIFIED_NO_CHANGES_ALLOWED);
            signProp.setLocation("Lisbon");
            signProp.setReazon("Unit test");
            signProp.setRectangle(new Rectangle(100, 100, 100, 100, 0));
            signProp.setType(RRSignPdfProperties.Type.SELF);
            signProp.isVisible(true);

            RRSignPdf rRSignPdf = new RRSignPdf(
                    signProp,
                    nosignpdf.getAbsolutePath(),
                    signpdf.getAbsolutePath()
            );
            rRSignPdf.signPdf();

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
