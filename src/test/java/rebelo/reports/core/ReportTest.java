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
package rebelo.reports.core;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rebelo.reports.core.datasource.ARRDsHttp;
import rebelo.reports.core.datasource.IRRDsProperties;
import rebelo.reports.core.datasource.RRDsDatabase;
import rebelo.reports.core.datasource.RRDsFileJson;
import rebelo.reports.core.datasource.RRDsFileXml;
import rebelo.reports.core.datasource.RRDsHttpJson;
import rebelo.reports.core.datasource.RRDsHttpXml;
import rebelo.reports.core.datasource.RRDsHttpsJson;
import rebelo.reports.core.datasource.RRDsHttpsXml;
import rebelo.reports.core.sign.RRSignPdf;
import rebelo.reports.core.sign.RRSignPdfProperties;

/**
 *
 * @author João Rebelo
 */
public class ReportTest {

    public ReportTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(rebelo.reports.core.Report.class.getName(), Level.ALL);
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
     * Test instance
     */
    @Test
    public void testInstanceNoProp() {
        try {
            Report report = new Report();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test instance
     */
    @Test
    public void testInstance() {
        try {
            Report report = new Report(new RRProperties());
        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of set and GetProperties method, of class Report.
     */
    @Test
    public void testSetGetProperties() {
        try {
            System.out.println("setGetProperties");
            Report report = new Report();
            report.setProperties(new RRProperties());
            report.getProperties();
        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of getProperties method, of class Report.
     */
    @Test
    public void testGetPropertiesNull() {
        System.out.println("getPropertiesNull");
        Report report = new Report();
        try {
            report.getProperties();
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failed get null test, should throw NulNotAllowed exceptrion");
    }

    /**
     * Test of getProperties method, of class Report.
     */
    @SuppressWarnings("null") 
    @Test    
    public void testSetPropertiesNull() {
        System.out.println("setPropertiesNull");
        Report report = new Report();
        try {
            report.setProperties(null);
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failed set null test, should throw NulNotAllowed exceptrion");
    }

    /**
     * Test of getExporter method, of class Report.
     */
    @Test
    public void testExporter() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File resDir = new File(classLoader.getResource("./").getFile());
            String resDirPath = resDir.getAbsolutePath();
            File outdir = new File(resDirPath + "/generated_reports");
            outdir.mkdir();
            File[] dbs = resDir.listFiles((File dir, String name) -> name.matches("\\w+\\.properties$"));

            for (File propFile : dbs) {
                try {
                    java.util.Properties dbprop = new java.util.Properties();
                    dbprop.load(new FileInputStream(propFile));

                    if (dbprop.getProperty("enable", "0").equals("1") == false) {
                        continue;
                    }

                    String propReport = dbprop.getProperty("report", null);
                    String propConnection = dbprop.getProperty("connection", null);
                    String propDriver = dbprop.getProperty("driver", null);
                    String propUser = dbprop.getProperty("user", null);
                    String propPassword = dbprop.getProperty("password", null);
                    String propOutname = dbprop.getProperty("outname", null);
                    String propDatasource = dbprop.getProperty("datasource", null);
                    String propFilePath = dbprop.getProperty("filepath", null);
                    String propUrl = dbprop.getProperty("url", null);
                    String propType = dbprop.getProperty("type", null);

                    ArrayList<RRProperties.Types> testType = new ArrayList<>();
                    String[] spTypes = dbprop.getProperty("types").split(",");
                    if (spTypes.length == 0) {
                        System.out.println("No type tp be test in file " + propFile);
                    }
                    for (String type : spTypes) {
                        testType.add(RRProperties.Types.valueOf(type));
                    }

                    if (propReport == null) {
                        throw new Exception("Report file path is not defined in " + propFile.getName());
                    }

                    File report = new File(resDirPath.concat(propReport));
                    if (report.isFile() == false || report.canRead() == false) {
                        throw new Exception(resDirPath
                                .concat(propReport)
                                .concat(" is not a file or can not read"));
                    }

                    for (RRProperties.Types type : RRProperties.Types.values()) {
                        try {

                            if (testType.contains(type) == false) {
                                continue;
                            }

                            IRRDsProperties dsProp;

                            switch (propDatasource) {
                                case "database":
                                    if (propDriver == null) {
                                        throw new Exception("Driver name is not defined in " + propFile.getName());
                                    }

                                    if (propConnection == null) {
                                        throw new Exception("Connection string is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsDatabase();
                                    ((RRDsDatabase) dsProp).setConnString(propConnection);
                                    ((RRDsDatabase) dsProp).setDriver(propDriver);
                                    ((RRDsDatabase) dsProp).setPassword(propPassword);
                                    ((RRDsDatabase) dsProp).setUser(propUser);
                                    break;
                                case "json_file":
                                    if (propFilePath == null) {
                                        throw new Exception("File path is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsFileJson();
                                    ((RRDsFileJson) dsProp).setFile(new File(propFilePath));
                                    break;
                                case "json_http":
                                    if (propUrl == null) {
                                        throw new Exception("Url is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsHttpJson();
                                    ((RRDsHttpJson) dsProp).setUrl(new URL(propUrl));
                                    ((RRDsHttpJson) dsProp).setType(ARRDsHttp.Type.valueOf(propType));
                                    break;
                                case "json_https":
                                    if (propUrl == null) {
                                        throw new Exception("Url is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsHttpsJson();
                                    ((RRDsHttpsJson) dsProp).setUrl(new URL(propUrl));
                                    ((RRDsHttpsJson) dsProp).setType(ARRDsHttp.Type.valueOf(propType));
                                    break;
                                case "xml_file":
                                    if (propFilePath == null) {
                                        throw new Exception("File path is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsFileXml();
                                    ((RRDsFileXml) dsProp).setFile(new File(propFilePath));
                                    break;
                                case "xml_http":
                                    if (propUrl == null) {
                                        throw new Exception("Url is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsHttpXml();
                                    ((RRDsHttpXml) dsProp).setUrl(new URL(propUrl));
                                    ((RRDsHttpJson) dsProp).setType(ARRDsHttp.Type.valueOf(propType));
                                    break;
                                case "xml_https":
                                    if (propUrl == null) {
                                        throw new Exception("Url is not defined in " + propFile.getName());
                                    }
                                    dsProp = new RRDsHttpsXml();
                                    ((RRDsHttpsXml) dsProp).setUrl(new URL(propUrl));
                                    ((RRDsHttpJson) dsProp).setType(ARRDsHttp.Type.valueOf(propType));
                                    break;
                                default:
                                    throw new Exception("unknowed test type");
                            }

                            RRProperties prop = new RRProperties();
                            prop.setType(type);
                            prop.setJasperFile(report.getAbsolutePath());
                            prop.setDataSourceProperties(dsProp); //(IRRDsProperties)dsProp

                            File outfile = new File(outdir + "/" + propOutname + "." + type.toString());

                            if (outfile.exists()) {
                                outfile.delete();
                            }

                            prop.setOutputFile(outfile.getAbsolutePath());

                            //Set parameters
                            prop.addParameter("P_STRING", "Parameter String");
                            prop.addParameter("P_BOOLEAN", true);
                            prop.addParameter("P_DOUBLE", new Double(999));
                            prop.addParameter("P_FLOAT", 9.09F);
                            prop.addParameter("P_INTEGER", 9);
                            prop.addParameter("P_LONG", new Long(49));
                            prop.addParameter("P_SHORT", new Short("109"));
                            prop.addParameter("P_BIG_DECIMAL", new BigDecimal(BigInteger.TEN));
                            prop.addParameter("P_SQL_DATE", new java.sql.Date(new Long(1254729890)));
                            prop.addParameter("P_SQL_TIME", new java.sql.Time(199));
                            prop.addParameter("P_TIMESTAMPT", new java.sql.Timestamp(199999));
                            prop.addParameter("P_DATE", new java.util.Date());

                            if (type.equals(RRProperties.Types.print)) {
                                RRPrintProperties prt = (RRPrintProperties) prop.getTypeProperties();
                                //prt.setSelectedPrinter("Microsoft Print to PDF");
                                //prt.setSelectedPrinter("HP Photosmart C4500 series");
                                // No print selection will print to default printer
                                prt.getPrintRequestAttributeSet().add(MediaSizeName.ISO_A4);
                                prt.getPrintRequestAttributeSet().add(new Copies(2));
                            }

                            Report rep = new Report(prop);
                            rep.exportReport();

                            if (type.equals(RRProperties.Types.print) == false) {

                                assertTrue(String.format("Test for type '%s' of file '%s'",
                                        type.toString(),
                                        propFile.getAbsolutePath()),
                                        outfile.exists());
                            }

                            if (type.equals(RRProperties.Types.pdf)) {
                                signPdf(prop);
                            }

                        } catch (Exception e) {
                            fail(String.format("Failing test for type '%s' of file '%s', with message '%s'",
                                    type.toString(),
                                    propFile.getAbsolutePath(),
                                    e.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    fail(String.format("Failing test for file '%s'; with message: '%s'",
                            propFile.getAbsolutePath(),
                            e.getMessage()));
                }
            }

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    /**
     * Sign the pdf
     *
     * @param prop
     * @throws NullNotAllowedException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws UnrecoverableKeyException
     * @throws CertificateException
     * @throws DocumentException
     * @throws Exception
     */
    public void signPdf(RRProperties prop)
            throws NullNotAllowedException,
            IOException,
            NoSuchAlgorithmException,
            UnrecoverableKeyException,
            CertificateException,
            DocumentException,
            Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File jks = new File(classLoader.getResource("./certificates/keystore.ks").getFile());
        File signpdf = new File(prop.getOutputFile().replace(".pdf", "_signed.pdf"));
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
                prop.getOutputFile(),
                signpdf.getAbsolutePath()
        );
        rRSignPdf.signPdf();
    }

}
