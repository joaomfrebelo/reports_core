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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.jfree.util.Log;
import org.xml.sax.SAXException;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.RRPdfProperties;
import rebelo.reports.core.RRPrintProperties;
import rebelo.reports.core.RRProperties;
import rebelo.reports.core.RRPropertiesException;
import rebelo.reports.core.Report;
import rebelo.reports.core.common.Message;
import rebelo.reports.core.common.StringUtils;
import rebelo.reports.core.common.Util;
import rebelo.reports.core.datasource.ARRDsHttp;
import rebelo.reports.core.datasource.DataSourceException;
import rebelo.reports.core.datasource.RRDsDatabase;
import rebelo.reports.core.datasource.RRDsHttpJson;
import rebelo.reports.core.datasource.RRDsHttpXml;
import rebelo.reports.core.datasource.RRDsHttpsJson;
import rebelo.reports.core.datasource.RRDsHttpsXml;
import static rebelo.reports.core.parse.ParseXML.LOG;
import rebelo.reports.core.parse.pojo.Database;
import rebelo.reports.core.parse.pojo.Jsonserver;
import rebelo.reports.core.parse.pojo.Parameter;
import rebelo.reports.core.parse.pojo.Rreport;
import rebelo.reports.core.parse.pojo.Sign;
import rebelo.reports.core.parse.pojo.Xmlserver;
import rebelo.reports.core.sign.RRSignPdfProperties;

/**
 *
 * @author João Rebelo
 */
public abstract class AParse {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * Output file base dir. The base dir for output file report if not null it
     * will be preppend
     */
    protected String outputBaseDir = null;

    /**
     * Jaster report file base dir. The base dir for the jasper report file if
     * not null it will be preppend
     */
    protected String jasperFileBaseDir = null;

    /**
     * KeyStore base dir. The base dir for the Keystore used to sign the pdf if
     * not null it will be preppend
     */
    protected String keyStoreBaseDir = null;

    /**
     * The XSD version that the parses parse
     */
    public static final String XSD_VERSION = "1.1";

    public AParse() {
        if (null != Report.logLevel) {
            Configurator.setLevel(getClass().getName(), Report.logLevel);
            Configurator.setLevel(AParse.class.getName(), Report.logLevel);
        }
    }

    /**
     *
     * Get the base dir for the Output file report The base dir for output file
     * report if not null it will be preppend
     *
     * @return
     */
    @Null
    public String getOutputBaseDir() {
        return outputBaseDir;
    }

    /**
     *
     * Set the base dir for the Output file report The base dir for output file
     * report if not null it will be preppend
     *
     * @param outputBaseDir
     */
    public void setOutputBaseDir(@Null String outputBaseDir) {
        this.outputBaseDir = outputBaseDir;
        LOG.debug(() -> String.format(
                "OutputBaseDir seted to '%s'",
                outputBaseDir == null ? "null" : outputBaseDir)
        );
    }

    /**
     *
     * Get Jasper File base dir Jaster report file base dir. The base dir for
     * the jasper report file if not null it will be preppend
     *
     * @return
     */
    @Null
    public String getJasperFileBaseDir() {
        return jasperFileBaseDir;
    }

    /**
     *
     * Set Jasper File base dir Jaster report file base dir. The base dir for
     * the jasper report file if not null it will be preppend
     *
     * @param jasperFileBaseDir
     */
    public void setJasperFileBaseDir(@Null String jasperFileBaseDir) {
        this.jasperFileBaseDir = jasperFileBaseDir;
        LOG.debug(() -> String.format(
                "JasperFileBaseDir seted to '%s'",
                jasperFileBaseDir == null ? "null" : jasperFileBaseDir)
        );
    }

    /**
     *
     * Get KeyStore base dir. The base dir for the Keystore used to sign the pdf
     * if not null it will be preppend
     *
     * @return
     */
    @Null
    public String getKeyStoreBaseDir() {
        return keyStoreBaseDir;
    }

    /**
     *
     * Set KeyStore base dir. The base dir for the Keystore used to sign the pdf
     * if not null it will be preppend
     *
     * @param keyStoreBaseDir
     */
    public void setKeyStoreBaseDir(@Null String keyStoreBaseDir) {
        this.keyStoreBaseDir = keyStoreBaseDir;
        LOG.debug(() -> String.format(
                "KeyStoreBaseDir seted to '%s'",
                keyStoreBaseDir == null ? "null" : keyStoreBaseDir)
        );
    }

    /**
     *
     * get the Output file path preppend teh base dir if not null
     *
     * @param path
     * @return
     * @throws NullNotAllowedException
     */
    @NotNull
    protected String constructOtputFilePath(@NotNull String path) throws NullNotAllowedException {
        if (path == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parse file");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        return this.getOutputBaseDir() == null
                ? path
                : this.getOutputBaseDir() + path;

    }

    /**
     *
     * @param file
     * @return
     * @throws JAXBException
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws rebelo.reports.core.parse.ParseException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws java.net.MalformedURLException
     * @throws rebelo.reports.core.datasource.DataSourceException
     * @throws java.text.ParseException
     */
    @NotNull
    public RRProperties parse(@NotNull File file) throws
            JAXBException,
            SAXException,
            NullNotAllowedException,
            ParseException,
            RRPropertiesException,
            MalformedURLException,
            DataSourceException,
            java.text.ParseException {

        Rreport rreport = this.unmarshaller(file);
        return parse(rreport);
    }

    /**
     *
     * Parse a xml string
     *
     * @param strXml
     * @return
     * @throws JAXBException
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws Exception
     */
    @NotNull
    public RRProperties parse(@NotNull String strXml) throws
            JAXBException,
            SAXException,
            NullNotAllowedException,
            Exception {

        Rreport rreport = this.unmarshaller(strXml);
        return parse(rreport);
    }

    /**
     *
     * Parse a xml get over a URL
     *
     * @param url
     * @return
     * @throws JAXBException
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws Exception
     */
    @NotNull
    public RRProperties parse(@NotNull URL url) throws
            JAXBException,
            SAXException,
            NullNotAllowedException,
            Exception {

        Rreport rreport = this.unmarshaller(url);
        return parse(rreport);
    }

    /**
     *
     * Parse the Rreport (rebelo.reports.core.prase.pojo)
     *
     * @param rreport
     * @return
     * @throws JAXBException
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws rebelo.reports.core.parse.ParseException
     * @throws java.net.MalformedURLException
     * @throws rebelo.reports.core.datasource.DataSourceException
     */
    @NotNull
    protected RRProperties parse(@NotNull Rreport rreport)
            throws JAXBException,
            SAXException,
            NullNotAllowedException,
            RRPropertiesException,
            ParseException,
            MalformedURLException,
            DataSourceException {
        LOG.trace(() -> "startig parseReport");

        RRProperties prop = new RRProperties();

        this.parseReportType(prop, rreport.getReporttype());

        LOG.trace(() -> "startig populate RRProperties");

        prop.setJasperFile(
                this.getJasperFileBaseDir() == null
                ? rreport.getJasperfile().getValue()
                : this.getJasperFileBaseDir() + rreport.getJasperfile().getValue()
        );
        LOG.debug(() -> {
            try {
                return "JasperReport file seted to '" + prop.getJasperFile() + "'";
            } catch (NullNotAllowedException ex) {
                return ex.getMessage();
            }
        });

        if (rreport.getJasperfile().getCopies() == null) {
            LOG.debug(() -> "Copies in report xml file was 'null', seted to 1 copie");
            prop.setCopies(1);
        } else {
            LOG.debug(() -> "Copies in report xml file seted to "
                    + rreport.getJasperfile().getCopies().toString()
                    + " copies");
            prop.setCopies(rreport.getJasperfile().getCopies().intValue());
        }

        this.parseReportType(prop, rreport.getReporttype());
        LOG.debug(() -> "ReportType parsed");

        this.parseDatasource(prop, rreport.getDatasource());
        LOG.debug(() -> "datasource parsed");

        this.parseParameters(prop, rreport.getParameters());
        LOG.debug(() -> "parameters parsed");

        return prop;
    }

    /**
     *
     * @param file
     * @return
     * @throws JAXBException
     * @throws SAXException
     * @throws rebelo.reports.core.parse.ParseException
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public Rreport unmarshaller(@NotNull File file)
            throws
            JAXBException,
            SAXException,
            ParseException,
            NullNotAllowedException {

        LOG.trace(() -> "checking file to be unmarshaller");

        if (file == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "file");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        if (file.isFile() == false) {
            Log.error("'" + file.getAbsolutePath() + "'" + " is not a file");
            throw new ParseException("'" + file.getAbsolutePath() + "'" + " is not a file");
        }

        if (file.canRead() == false) {
            Log.error("'" + file.getAbsolutePath() + "'" + " is not readable");
            throw new ParseException("'" + file.getAbsolutePath() + "'" + " is not readable");
        }

        LOG.debug(() -> "Init parse");
        Unmarshaller unmarshaller = this.unmarshallInstance();
        LOG.debug(() -> "unmarshalling file '" + file.getAbsolutePath() + "'");
        Rreport rreport = (Rreport) unmarshaller.unmarshal(file);
        LOG.info(() -> rreport.toString());
        return rreport;
    }

    /**
     *
     * Unmarshall a string
     *
     * @param str
     * @return
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws JAXBException
     */
    @NotNull
    public Rreport unmarshaller(@NotNull String str)
            throws SAXException, NullNotAllowedException, JAXBException {
        LOG.trace(() -> "Init unmarshaller string ");
        LOG.info(() -> "XML string => " + str);
        if (str == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "str");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        LOG.debug(() -> "Init parse");
        Unmarshaller unmarshaller = this.unmarshallInstance();
        LOG.debug(() -> "unmarshalling string");
        Rreport rreport = (Rreport) unmarshaller.unmarshal(new StringReader(str));
        LOG.info(() -> rreport.toString());
        return rreport;
    }

    /**
     *
     * Unmarshall a Url
     *
     * @param url
     * @return
     * @throws SAXException
     * @throws NullNotAllowedException
     * @throws JAXBException
     */
    @NotNull
    public Rreport unmarshaller(@NotNull URL url) throws SAXException, NullNotAllowedException, JAXBException {
        LOG.trace(() -> "Init unmarshaller string ");
        LOG.info(() -> "Url to unmarshaller => " + url.toString());
        if (url == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "Url");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        LOG.debug(() -> "Init parse");
        Unmarshaller unmarshaller = this.unmarshallInstance();
        LOG.debug(() -> "unmarshalling url");
        Rreport rreport = (Rreport) unmarshaller.unmarshal(url);
        LOG.info(() -> rreport.toString());
        return rreport;
    }

    /**
     *
     * Create the Unmarshall Insntance to parse
     *
     * @return
     * @throws SAXException
     * @throws JAXBException
     */
    @NotNull
    public Unmarshaller unmarshallInstance() throws SAXException, JAXBException {
        LOG.trace(() -> "Create unmarshallInstance");
        LOG.trace(() -> "Read xsd file");

        //ClassLoader classLoader = getClass().getClassLoader();
        URL xsdUrl = getClass().getResource(
                "/schema_" + XSD_VERSION.replace(".", "_") + ".xsd"
        );

        LOG.trace(() -> "Creating JAXBContext.newInstance ");
        JAXBContext jaxbContext = JAXBContext.newInstance(Rreport.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        if (xsdUrl == null) {
            LOG.warn(() -> "Schema not seted please put the schema file ('schema_"
                    + XSD_VERSION.replace(".", "_")
                    + ".xsd' in the same folder as your java file");
        } else {
            File xsd = new File(xsdUrl.getFile());
            if (xsd.isFile() && xsd.canRead()) {
                LOG.debug(() -> "Seting xsd shema file '" + xsd.getAbsolutePath() + "'");
                SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = sf.newSchema(xsd);
                unmarshaller.setSchema(schema);
                LOG.trace(() -> "Schema seted");
            } else {
                LOG.debug(() -> "XSD shema path file '"
                        + xsd.getAbsolutePath()
                        + "' is not a file or is not readable");
            }
        }

        return unmarshaller;
    }

    /**
     *
     * Parse the Report type
     *
     * @param prop
     * @param repType
     * @throws NullNotAllowedException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws rebelo.reports.core.parse.ParseException
     */
    protected void parseReportType(@NotNull RRProperties prop, @NotNull Rreport.Reporttype repType)
            throws NullNotAllowedException, RRPropertiesException, ParseException {

        LOG.trace("Init parseReportType method");

        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parsereportType prop");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        if (repType == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseSign repType");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        LOG.trace("Parsing ReportType");

        if (repType.getPdf() != null) {
            prop.setType(RRProperties.Types.pdf);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getPdf().getOutputfile())
            );
            RRPdfProperties pdfProp = (RRPdfProperties) prop.getTypeProperties();
            if (repType.getPdf().getSign() != null) {
                pdfProp.isSignPDF(true);
                this.parseSign(pdfProp, repType.getPdf().getSign());
            } else {
                pdfProp.isSignPDF(false);
            }
        } else if (repType.getPrint() != null) {
            prop.setType(RRProperties.Types.print);
            ((RRPrintProperties) prop.getTypeProperties())
                    .setSelectedPrinter(repType.getPrint().getPrinter());
        } else if (repType.getCsv() != null) {
            prop.setType(RRProperties.Types.csv);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getCsv().getOutputfile())
            );
        } else if (repType.getDocx() != null) {
            prop.setType(RRProperties.Types.docx);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getDocx().getOutputfile())
            );
        } else if (repType.getHtml() != null) {
            prop.setType(RRProperties.Types.html);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getHtml().getOutputfile())
            );
        } else if (repType.getJson() != null) {
            prop.setType(RRProperties.Types.json);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getJson().getOutputfile())
            );
        } else if (repType.getOds() != null) {
            prop.setType(RRProperties.Types.ods);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getOds().getOutputfile())
            );
        } else if (repType.getOdt() != null) {
            prop.setType(RRProperties.Types.odt);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getOdt().getOutputfile())
            );
        } else if (repType.getPptx() != null) {
            prop.setType(RRProperties.Types.pptx);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getPptx().getOutputfile())
            );
        } else if (repType.getRtf() != null) {
            prop.setType(RRProperties.Types.rtf);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getRtf().getOutputfile())
            );
        } else if (repType.getText() != null) {
            prop.setType(RRProperties.Types.text);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getText().getOutputfile())
            );
        } else if (repType.getXls() != null) {
            prop.setType(RRProperties.Types.xls);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getXls().getOutputfile())
            );
        } else if (repType.getXlsx() != null) {
            prop.setType(RRProperties.Types.xlsx);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getXlsx().getOutputfile())
            );
        } else if (repType.getXml() != null) {
            prop.setType(RRProperties.Types.xml);
            prop.setOutputFile(
                    this.constructOtputFilePath(repType.getXml().getOutputfile())
            );
        } else {
            throw new ParseException("Unknowed Report type to be parsed in parsereportType");
        }
        LOG.debug(() -> {
            try {
                return "reportType seted to '" + prop.getType().toString() + "'";
            } catch (NullNotAllowedException ex) {
                return ex.getMessage();
            }
        });
    }

    /**
     * Parse Pdf sign properties
     *
     * @param pdfProp
     * @param sign
     * @throws NullNotAllowedException
     * @throws RRPropertiesException
     */
    @SuppressWarnings("null")
    protected void parseSign(RRPdfProperties pdfProp, Sign sign)
            throws NullNotAllowedException, RRPropertiesException {

        LOG.trace("Init parseSign method");

        if (pdfProp == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseSign pdfProp");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        if (sign == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseSign sign");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        LOG.trace("Parsing sign");
        RRSignPdfProperties signProp = new RRSignPdfProperties();
        signProp.setJavaKeyStorePath(
                this.getKeyStoreBaseDir() == null
                ? sign.getKeystore().getPath()
                : this.getKeyStoreBaseDir() + sign.getKeystore().getPath()
        );
        signProp.setJavaKeyStorePassword(sign.getKeystore().getPassword());
        signProp.setCertificateName(sign.getKeystore().getCertificate().getName());
        signProp.setCertificatePassword(sign.getKeystore().getCertificate().getPassword());
        signProp.setLevel(RRSignPdfProperties.Level.valueOf(sign.getLevel().toUpperCase()));
        signProp.setType(RRSignPdfProperties.Type.valueOf(sign.getType().toUpperCase()));

        try {
            signProp.isVisible(Util.parseBool(sign.getRectangle().getVisible()));
        } catch (Exception e) {
            throw new RRPropertiesException(e.getMessage());
        }

        if (signProp.isVisible()) {
            Float x = Float.valueOf(sign.getRectangle().getPosition().getX().toString());
            Rectangle rec = new Rectangle(
                    Float.valueOf(sign.getRectangle().getPosition().getX().toString()),
                    Float.valueOf(sign.getRectangle().getPosition().getY().toString()),
                    Float.valueOf(sign.getRectangle().getPosition().getWidth().toString()),
                    Float.valueOf(sign.getRectangle().getPosition().getHeight().toString()),
                    Integer.valueOf(sign.getRectangle().getPosition().getRotation().toString())
            );
            signProp.setRectangle(rec);
        }
        if (sign.getLocation() != null) {
            signProp.setLocation(sign.getLocation());
        }

        if (sign.getReazon() != null) {
            signProp.setReazon(sign.getReazon());
        }
        LOG.debug("sign parsed");
        pdfProp.setSignProp(signProp);
        LOG.info(() -> "Sign prop seted to: " + signProp.toString());
    }

    /**
     *
     * Parse parameters
     *
     * @param prop
     * @param parameters
     * @throws NullNotAllowedException
     * @throws ParseException
     */
    protected void parseParameters(@NotNull RRProperties prop,
            @NotNull Rreport.Parameters parameters)
            throws NullNotAllowedException, ParseException, ParseException {

        LOG.trace("Init parseParameters method");

        if (prop == null) {
            String msg = String.format(
                    Message.SET_NULL_ERROR,
                    "parseParameters prop");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        if (parameters == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseParameters parameters");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        if (parameters.getParameter() == null) {
            LOG.debug(() -> "No parameters parameters.getParameter() was null");
        }

        if (parameters.getParameter().isEmpty()) {
            LOG.debug(() -> "No parameters parameters.getParameter() doesn't have elements");
        }

        for (Parameter param : parameters.getParameter()) {
            String name = param.getName();
            String value = param.getValue().getValue();
            switch (param.getType().toLowerCase()) {
                case "string":
                    prop.addParameter(name, value);
                    break;
                case "bool":
                case "boolean":
                    try {
                        prop.addParameter(name, Util.parseBool(value));
                    } catch (Exception e) {
                        throw new ParseException(e.getMessage());
                    }
                    break;
                case "double":
                    prop.addParameter(name, Double.valueOf(value));
                    break;
                case "float":
                    prop.addParameter(name, Float.valueOf(value));
                    break;
                case "integer":
                    prop.addParameter(name, Integer.valueOf(value));
                    break;
                case "long":
                    prop.addParameter(name, Long.valueOf(value));
                    break;
                case "short":
                    prop.addParameter(name, Short.valueOf(value));
                    break;
                case "bigdecimal":
                    prop.addParameter(name, new BigDecimal(Double.valueOf(value)));
                    break;
                case "date":
                    SimpleDateFormat sdf = new SimpleDateFormat(param.getValue().getFormat());
                    try {
                        prop.addParameter(name, sdf.parse(value));
                    } catch (java.text.ParseException e) {
                        throw new ParseException(e);
                    }
                    break;
                case "time":
                    throw new ParseException("Not implemented");
//                    break;
                case "sqltime":
                    prop.addParameter(name, new java.sql.Time(Integer.valueOf(value)));
                    break;
                case "sqldate":
                    prop.addParameter(name, new java.sql.Date(Long.valueOf(value)));
                    break;
                case "timestamp":
                    prop.addParameter(name, new java.sql.Timestamp(Integer.valueOf(value)));
                    break;
                default:
                    throw new ParseException("Unknowed parameters type '" + param.getType() + "'");
            }
        }

    }

    /**
     *
     * Parse datasource
     *
     * @param prop
     * @param datasource
     * @throws NullNotAllowedException
     * @throws MalformedURLException
     * @throws DataSourceException
     */
    protected void parseDatasource(@NotNull RRProperties prop,
            @NotNull Rreport.Datasource datasource) throws NullNotAllowedException, MalformedURLException, DataSourceException {

        LOG.trace("Init parseDatasource method");

        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseDatasource pdfProp");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        if (datasource == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "parseDatasource datasource");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }

        if (datasource.getDatabase() != null) {
            Database db = datasource.getDatabase();
            RRDsDatabase dsProp = new RRDsDatabase();
            dsProp.setConnString(db.getConnectionString());
            dsProp.setDriver(db.getDriver());
            dsProp.setPassword(db.getPassword());
            dsProp.setUser(db.getUser());
            prop.setDataSourceProperties(dsProp);
            LOG.debug(() -> "Datasource setted to 'Database'");
            return;
        }

        if (datasource.getJsonhttp() != null) {
            RRDsHttpJson propJsds = new RRDsHttpJson();
            Jsonserver jserver = datasource.getJsonhttp();
            if (StringUtils.isNotEmptyOrNull(jserver.getDatePattern())) {
                propJsds.setDatePattern(jserver.getDatePattern());
            }
            if (StringUtils.isNotEmptyOrNull(jserver.getNumberPattern())) {
                propJsds.setNumberPattern(jserver.getNumberPattern());
            }
            propJsds.setType(ARRDsHttp.Type.valueOf(jserver.getType()));
            propJsds.setUrl(new URL(jserver.getUrl()));
            prop.setDataSourceProperties(propJsds);
            LOG.debug(() -> "Datasource setted to 'JsonHttp'");
            return;
        }

        if (datasource.getJsonhttps() != null) {
            RRDsHttpsJson propJsds = new RRDsHttpsJson();
            Jsonserver jserver = datasource.getJsonhttps();
            if (StringUtils.isNotEmptyOrNull(jserver.getDatePattern())) {
                propJsds.setDatePattern(jserver.getDatePattern());
            }
            if (StringUtils.isNotEmptyOrNull(jserver.getNumberPattern())) {
                propJsds.setNumberPattern(jserver.getNumberPattern());
            }
            propJsds.setType(ARRDsHttp.Type.valueOf(jserver.getType()));
            propJsds.setUrl(new URL(jserver.getUrl()));
            prop.setDataSourceProperties(propJsds);
            LOG.debug(() -> "Datasource setted to 'JsonHttps'");
            return;
        }

        if (datasource.getXmlhttp() != null) {
            RRDsHttpXml propXml = new RRDsHttpXml();
            Xmlserver jserver = datasource.getXmlhttp();
            if (StringUtils.isNotEmptyOrNull(jserver.getDatePattern())) {
                propXml.setDatePattern(jserver.getDatePattern());
            }
            if (StringUtils.isNotEmptyOrNull(jserver.getNumberPattern())) {
                propXml.setNumberPattern(jserver.getNumberPattern());
            }
            propXml.setType(ARRDsHttp.Type.valueOf(jserver.getType()));
            propXml.setUrl(new URL(jserver.getUrl()));
            prop.setDataSourceProperties(propXml);
            LOG.debug(() -> "Datasource setted to 'XmlHttp'");
            return;
        }

        if (datasource.getXmlhttps() != null) {
            RRDsHttpsXml propXml = new RRDsHttpsXml();
            Xmlserver jserver = datasource.getXmlhttps();
            if (StringUtils.isNotEmptyOrNull(jserver.getDatePattern())) {
                propXml.setDatePattern(jserver.getDatePattern());
            }
            if (StringUtils.isNotEmptyOrNull(jserver.getNumberPattern())) {
                propXml.setNumberPattern(jserver.getNumberPattern());
            }
            propXml.setType(ARRDsHttp.Type.valueOf(jserver.getType()));
            propXml.setUrl(new URL(jserver.getUrl()));
            prop.setDataSourceProperties(propXml);
            LOG.debug(() -> "Datasource setted to 'XmlHttps'");
            //return;
        }
    }

}
