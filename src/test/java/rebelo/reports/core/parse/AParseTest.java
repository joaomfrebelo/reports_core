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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.RRPdfProperties;
import rebelo.reports.core.RRProperties;
import rebelo.reports.core.RRPropertiesException;
import rebelo.reports.core.datasource.DataSourceException;
import rebelo.reports.core.datasource.RRDsDatabase;
import rebelo.reports.core.parse.pojo.Rreport;
import rebelo.reports.core.parse.pojo.Sign;
import rebelo.reports.core.sign.RRSignPdfProperties;

/**
 *
 * @author João Rebelo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AParseTest {

    /**
     * The Parse instance that is intancoated in every test with the procted
     * function as public
     */
    protected class Parse extends AParse {

        public Parse() {
            super();
        }

        @Override
        protected void parseDatasource(RRProperties prop, Rreport.Datasource datasource) throws NullNotAllowedException, MalformedURLException, DataSourceException {
            super.parseDatasource(prop, datasource);
        }

        @Override
        protected void parseParameters(RRProperties prop, Rreport.Parameters parameters) throws NullNotAllowedException, rebelo.reports.core.parse.ParseException, rebelo.reports.core.parse.ParseException {
            super.parseParameters(prop, parameters); 
        }
        
        @Override
        protected void parseSign(RRPdfProperties pdfProp, Sign sign) throws NullNotAllowedException, RRPropertiesException {
            super.parseSign(pdfProp, sign);
        }

        @Override
        protected void parseReportType(RRProperties prop, Rreport.Reporttype repType) throws NullNotAllowedException, RRPropertiesException, rebelo.reports.core.parse.ParseException {
            super.parseReportType(prop, repType);
        }

    }

    public AParseTest() {

    }

    @BeforeClass
    public static void setUpClass() {
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

    protected void aparseInstance() {

    }

    /**
     * Teste the set and get
     */
    @Test
    public void test0010SetGet() {

        String outPre = "outPre";
        String jasperPre = "jasperPre";
        String keyPre = "keyPre";

        Parse parse = new Parse();
        Assert.assertNull(parse.getOutputBaseDir());
        Assert.assertNull(parse.getJasperFileBaseDir());
        Assert.assertNull(parse.getKeyStoreBaseDir());

        parse.setOutputBaseDir(outPre);
        Assert.assertEquals(outPre, parse.getOutputBaseDir());

        parse.setJasperFileBaseDir(jasperPre);
        Assert.assertEquals(jasperPre, parse.getJasperFileBaseDir());

        parse.setKeyStoreBaseDir(keyPre);
        Assert.assertEquals(keyPre, parse.getKeyStoreBaseDir());

    }

    /**
     * Test the parse of the mysql_pdf_sign
     *
     * @throws Exception
     */
    @Test
    public void test9000ParseFile() throws Exception {
        System.out.println("test9000Parse");
        ClassLoader classLoader = getClass().getClassLoader();
        File xml = new File(classLoader.getResource("./parsexml/mysql_pdf_signed.xml").getFile());

        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        Parse parse = new Parse();

        String outputBaseDir = resDirPath + "/parsexml/generated_reports";
        parse.setOutputBaseDir(outputBaseDir);
        
        String jasperFileBaseDir = resDirPath;
        parse.setJasperFileBaseDir(jasperFileBaseDir);
        
        String keyStoreBaseDir = resDirPath;
        parse.setKeyStoreBaseDir(keyStoreBaseDir);

        this.verifyAsserts(parse.parse(xml));
    }

    /**
     * Test the parse of the mysql_pdf_sign
     *
     * @throws Exception
     */
    @Test
    public void test9010ParseString() throws Exception {
        System.out.println("test9010Parse");
        ClassLoader classLoader = getClass().getClassLoader();
        File xml = new File(classLoader.getResource("./parsexml/mysql_pdf_signed.xml").getFile());

        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        Parse parse = new Parse();

        parse.setOutputBaseDir(resDirPath + "/parsexml/generated_reports");
        parse.setJasperFileBaseDir(resDirPath);
        parse.setKeyStoreBaseDir(resDirPath);

        this.verifyAsserts(
                parse.parse(
                        new String(
                                Files.readAllBytes(
                                        Paths.get(
                                                xml.getAbsolutePath()
                                        )
                                )
                        )
                )
        );
    }
    
    /**
     * Test the parse of the mysql_pdf_sign
     *
     * @throws Exception
     */
    @Test
    @org.junit.Ignore
    public void test9010ParseUrl() throws Exception {
        System.out.println("test9010Parse");
        ClassLoader classLoader = getClass().getClassLoader();
        
        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();

        Parse parse = new Parse();

        parse.setOutputBaseDir(resDirPath + "/parsexml/generated_reports");
        parse.setJasperFileBaseDir(resDirPath);
        parse.setKeyStoreBaseDir(resDirPath);
        Assert.fail("Not implemented is missing the URL path");
        this.verifyAsserts(
                parse.parse(new URL("/////"))
        );
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
