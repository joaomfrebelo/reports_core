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

import com.itextpdf.text.Rectangle;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.Unmarshaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.RRPdfProperties;
import rebelo.reports.core.RRProperties;
import rebelo.reports.core.datasource.RRDsDatabase;
import rebelo.reports.core.sign.RRSignPdfProperties;

/**
 *
 * @author João Rebelo
 */
public class ParseJsonTest {
    
    public ParseJsonTest() {
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
     * Test the parse of the mysql_pdf_sign
     *
     * @throws Exception
     */
    @Test
    public void test9000ParseFile() throws Exception {
        System.out.println("test0010Parse");
        ClassLoader classLoader = getClass().getClassLoader();
        File xml = new File(classLoader.getResource("./parsejson/mysql_pdf_signed.json").getFile());

        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        ParseJson parse = new ParseJson();

        parse.setOutputBaseDir(resDirPath + "/parsexml/generated_reports");
        parse.setJasperFileBaseDir(resDirPath);
        parse.setKeyStoreBaseDir(resDirPath);

        this.verifyAsserts(parse.parse(xml));
    }

    /**
     *
     * Verify asserts
     *
     * @param rrProperties
     * @throws NullNotAllowedException
     * @throws Exception
     */
    public void verifyAsserts(RRProperties rrProperties)
            throws NullNotAllowedException, Exception {

        System.out.println("verify asserts");
        ClassLoader classLoader = getClass().getClassLoader();

        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        assertEquals(
                resDirPath + "/sakila.jasper",
                rrProperties.getJasperFile()
        );
        assertEquals(RRProperties.Types.pdf, rrProperties.getType());
        assertEquals(
                resDirPath + "/parsexml/generated_reports" + "/sakila_xmlparse_signed.pdf",
                rrProperties.getOutputFile()
        );
        // PDF
        RRPdfProperties pdf = (RRPdfProperties) rrProperties.getTypeProperties();
        assertTrue(pdf.isSignPDF());
        assertEquals(resDirPath + "/certificates/keystore.ks", pdf.getSignProp().getJavaKeyStorePath());
        assertEquals("password", pdf.getSignProp().getJavaKeyStorePassword());
        assertEquals("rreports", pdf.getSignProp().getCertificateName());
        assertEquals("password", pdf.getSignProp().getCertificatePassword());
        assertEquals(
                RRSignPdfProperties.Level.CERTIFIED_NO_CHANGES_ALLOWED,
                pdf.getSignProp().getLevel()
        );
        assertEquals(RRSignPdfProperties.Type.SELF, pdf.getSignProp().getType());
        assertTrue(pdf.getSignProp().isVisible());
        Rectangle rec = pdf.getSignProp().getRectangle();
        assertEquals(0, rec.getRotation());
        assertEquals(100f, rec.getRight(), 0d);// X        
        assertEquals(100f, rec.getTop(), 0d);// Y       
        assertEquals(100f, rec.getWidth(), 0d);// Width        
        assertEquals(100f, rec.getHeight(), 0d);// height
        assertEquals("Lisbon - Sintra", pdf.getSignProp().getLocation());
        assertEquals("Test", pdf.getSignProp().getReazon());

        //Datasource
        RRDsDatabase ds = (RRDsDatabase) rrProperties.getDataSourceProperties();
        assertEquals("jdbc:mysql://localhost/sakila", ds.getConnString());
        assertEquals("com.mysql.jdbc.Driver", ds.getDriver());
        assertEquals("rebelo", ds.getUser());
        assertEquals("password", ds.getPassword());

        //Parameters
        rrProperties.getParameters().entrySet().forEach((param) -> {
            String name = param.getKey();
            switch (name) {
                case "P_STRING":
                    assertEquals("Parameter String", (String) param.getValue());
                    break;
                case "P_BOOLEAN":
                    assertTrue((boolean) param.getValue());
                    break;
                case "P_DOUBLE":
                    assertEquals(999d, (double) param.getValue(), 0d);
                    break;
                case "P_FLOAT":
                    assertEquals(9.09f, (float) param.getValue(), 0f);
                    break;
                case "P_INTEGER":
                    assertEquals(9, (int) param.getValue());
                    break;
                case "P_LONG":
                    assertEquals(49, (long) param.getValue());
                    break;
                case "P_SHORT":
                    assertEquals(129, (short) param.getValue());
                    break;
                case "P_BIG_DECIMAL":
                    assertEquals(new BigDecimal(19), (BigDecimal) param.getValue());
                    break;
                case "P_SQL_DATE":
                    assertEquals(
                            (new java.sql.Date(1254729890)).getTime(),
                            ((java.sql.Date) param.getValue()).getTime()
                    );
                    break;
                case "P_SQL_TIME":
                    assertEquals(
                            (new java.sql.Time(199)).getTime(),
                            ((java.sql.Time) param.getValue()).getTime()
                    );
                    break;
                case "P_TIMESTAMPT":
                    assertEquals(
                            (new Date(199999)).getTime(),
                            ((Date) param.getValue()).getTime()
                    );
                    break;
                case "P_DATE":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    assertEquals("1969-10-05", sdf.format((Date) param.getValue()));
                    break;
                default:
                    Assert.fail("Parameter name '" + name + "' unknowed");
            }
        });
    }
    
}
