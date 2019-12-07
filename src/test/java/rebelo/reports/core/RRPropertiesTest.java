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

import java.io.File;
import java.util.HashMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import static org.hamcrest.CoreMatchers.instanceOf;
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
public class RRPropertiesTest {

    public static RRProperties propreties;

    public RRPropertiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Configurator.setLevel(RRProperties.class.getName(), Level.ALL);
        propreties = new rebelo.reports.core.RRProperties();
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
     * Test of getEncoding and setEncoding method, of class RRProperties.
     */
    @Test
    public void testSetGetEncoding() {
        System.out.println("setGetEncoding");
        try {
            assertEquals("UTF-8", propreties.getEncoding());

            String value = "UTF-16";
            propreties.setEncoding(value);
            assertEquals(value, propreties.getEncoding());

        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSetEncodingNull() {
        System.out.println("setEncodingNull");
        try {
            propreties.setEncoding(null);

        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failing set encoding to null, must throw NullNotAllowed");
    }

    /**
     * Test of getJasperFile and setJasperFilePath method, of class
     * RRProperties.
     */
    @Test
    public void testSetGetJasperFilePath() {
        System.out.println("getJasperFilePath");
        try {
            String value = "/path/to/jasperFile";
            propreties.setJasperFile(value);
            assertEquals(value, propreties.getJasperFile());

        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of setJasperFilePath to null method, of class RRProperties.
     */
    @Test
    public void testSetJasperFilePathNull() {
        System.out.println("setJasperFilePathNull");
        try {
            propreties.setJasperFile(null);
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing set jasper file path to null");
    }

    /**
     * Test of getJasperFilePath while null , of class RRProperties.
     */
    @Test
    public void testGetJasperFilePathNull() {
        System.out.println("setJasperFilePath");
        try {
            RRProperties prop = new RRProperties();
            prop.getJasperFile();
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing get jasper file path while null");
    }

    /**
     * Test of getOutputFilePath and getOutputFilePath method, of class
     * RRProperties.
     * @throws rebelo.reports.core.RRPropertiesException
     */
    @Test
    public void testSetGetOutputFilePath() throws RRPropertiesException {
        System.out.println("setGetOutputFilePath");
        try {
            String value = "/path/output/file";
            propreties.setOutputFile(value);
            assertEquals(value, propreties.getOutputFile());

        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test set outpufilePath to null
     * @throws rebelo.reports.core.RRPropertiesException
     */
    @SuppressWarnings("null")
    @Test
    public void testSetOutputFilePathNull() throws RRPropertiesException {
        System.out.println("setOutputFilePathNull");
        try {
            propreties.setOutputFile(null);
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failing set output file path to null, should throw NUllNor Allowed");
    }

    /**
     * Test set outpufilePath to null
     */
    @Test
    public void testGetOutputFilePathNull() {
        System.out.println("setOutputFilePathNull");
        try {
            rebelo.reports.core.RRProperties prop = new rebelo.reports.core.RRProperties();
            prop.getOutputFile();
        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("Failing get output file path to null, should throw NUllNor Allowed");
    }

    /**
     * Test of addReportFormat and getRportFormats method, of class
     * RRProperties.
     */
    @Test
    public void testAddGetReportFormat() {
        System.out.println("addGetReportFormat");
        try {

            propreties.setType(RRProperties.Types.pdf);

            assertEquals(RRProperties.Types.pdf, propreties.getType());

        } catch (NullNotAllowedException e) {
            fail("failinf add report format");
        }

    }

    /**
     * Test of getReportFormatsNull method, of class RRProperties.
     */
    @Test
    public void testGetReportFormatsNull() {
        System.out.println("getReportFormatsNull");
        try {
            RRProperties prop = new RRProperties();
            prop.getType();

        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing get null report format, shoul throw NullNotAllowed");
    }

    /**
     * Test of getReportFormatsNull method, of class RRProperties.
     */
    @SuppressWarnings("null")
    @Test
    public void testSetReportFormatsNull() {
        System.out.println("setReportFormatsNull");
        try {
            RRProperties prop = new RRProperties();
            propreties.setType(null);

        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing set null report format, shoul throw NullNotAllowed");
    }

    /**
     * Test of addParameter method, of class RRProperties.
     */
    @Test
    public void testAddParameter() {
        System.out.println("addParameter");
        try {

            assertTrue(propreties.getParameters().isEmpty());

            propreties.addParameter("test_bool_true", true);
            propreties.addParameter("test_bool_false", false);
            propreties.addParameter("test_string", "test");
            propreties.addParameter("test_int", 999);

            HashMap<String, Object> param = propreties.getParameters();

            assertEquals(4, param.size());
            assertTrue((boolean) param.get("test_bool_true"));
            assertFalse((boolean) param.get("test_bool_false"));
            assertTrue(String.valueOf(param.get("test_string")).equals("test"));
            assertEquals(999, param.get("test_int"));

        } catch (NullNotAllowedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test of SetKeyParametersNull, of class RRProperties.
     */
    @SuppressWarnings("null")
    @Test
    public void testSetKeyParametersNull() {
        System.out.println("setKeyParametersNull");
        try {
            propreties.addParameter(null, "");

        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing seting null key of parameters, should throw NullNotAllowed");
    }

    /**
     * Test of SetValueParametersNull, of class RRProperties.
     */
    @SuppressWarnings("null")
    @Test
    public void testSetValueParametersNull() {
        System.out.println("setValueParametersNull");
        try {
            propreties.addParameter("", null);

        } catch (NullNotAllowedException e) {
            assertTrue(true);
            return;
        }
        fail("failing seting null null of parameters, should throw NullNotAllowed");
    }

    @Test
    public void testGetTypeProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath();
        File out = new File(resDirPath + "/generated_reports/test");
        
        for (RRProperties.Types type : RRProperties.Types.values()) {
            try {
                RRProperties prop = new RRProperties();
                prop.setOutputFile(out.getAbsolutePath());
                prop.setType(type);
                switch (type) {
                    case pdf:
                        RRPdfProperties pdfprop = (RRPdfProperties) prop.getTypeProperties();
                        assertTrue(pdfprop instanceof RRPdfProperties);
                        break;
                    case csv:
                        RRCsvProperties csvprop = (RRCsvProperties) prop.getTypeProperties();
                        assertTrue(csvprop instanceof RRCsvProperties);
                        break;
                    case docx:
                        RRDocxProperties docxprop = (RRDocxProperties) prop.getTypeProperties();
                        assertTrue(docxprop instanceof RRDocxProperties);
                        break;
                    case html:
                        RRHtmlProperties htmlprop = (RRHtmlProperties) prop.getTypeProperties();
                        assertTrue(htmlprop instanceof RRHtmlProperties);
                        break;
                    case json:
                        RRJsonProperties jsonprop = (RRJsonProperties) prop.getTypeProperties();
                        assertTrue(jsonprop instanceof RRJsonProperties);
                        break;
                    case ods:
                        RROdsProperties odsprop = (RROdsProperties) prop.getTypeProperties();
                        assertTrue(odsprop instanceof RROdsProperties);
                        break;
                    case odt:
                        RROdtProperties odtprop = (RROdtProperties) prop.getTypeProperties();
                        assertTrue(odtprop instanceof RROdtProperties);
                        break;
                    case pptx:
                        RRPptxProperties pptxprop = (RRPptxProperties) prop.getTypeProperties();
                        assertTrue(pptxprop instanceof RRPptxProperties);
                        break;
                    case print:
                        RRPrintProperties printprop = (RRPrintProperties) prop.getTypeProperties();
                        assertTrue(printprop instanceof RRPrintProperties);
                        break;
                    case rtf:
                        RRRtfProperties rtfprop = (RRRtfProperties) prop.getTypeProperties();
                        assertTrue(rtfprop instanceof RRRtfProperties);
                        break;
                    case text:
                        RRTextProperties textprop = (RRTextProperties) prop.getTypeProperties();
                        assertTrue(textprop instanceof RRTextProperties);
                        break;
                    case xls:
                        RRXlsProperties xlsprop = (RRXlsProperties) prop.getTypeProperties();
                        assertTrue(xlsprop instanceof RRXlsProperties);
                        break;
                    case xlsx:
                        RRXlsxProperties xlsxprop = (RRXlsxProperties) prop.getTypeProperties();
                        assertTrue(xlsxprop instanceof RRXlsxProperties);
                        break;
                    case xml:
                        RRXmlProperties xmlprop = (RRXmlProperties) prop.getTypeProperties();
                        assertTrue(xmlprop instanceof RRXmlProperties);
                        break;
                    default:
                        fail(String.format("Unknow type '%s' to test in testGetTypeProperties", type.toString()));
                }
            } catch (Exception e) {
                fail(String.format("Failin test type '%s' in in testGetTypeProperties with message '%s'",
                        type.toString(),
                        e.getMessage()));
            }
        }

    }

}
