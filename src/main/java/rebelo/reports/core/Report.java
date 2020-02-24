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
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;
import javax.validation.constraints.Null;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePptxReportConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rebelo.reports.core.common.Message;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import rebelo.reports.core.datasource.ARRDsJRDataSource;
import rebelo.reports.core.datasource.DataSourceException;
import rebelo.reports.core.datasource.IRRDsProperties;
import rebelo.reports.core.datasource.RRDsDatabase;
import rebelo.reports.core.sign.RRSignPdf;
import rebelo.reports.core.sign.RRSignPdfProperties;
import rebelo.reports.core.sign.SignPdfException;

/**
 * The class that generates the jasper report
 *
 * @author João Rebelo
 */
public class Report {

    /**
     * The log level
     */
    public static Level logLevel = null;

    /**
     * The report index parameter name to passed to the jasperreport.
     * <br>
     * the index is the number of the report
     */
    public static final String RR_INDEX_PARAMETER = "RR_INDEX_PARAMETER";

    /**
     * Date of starting Report generation
     */
    protected LocalDateTime start = LocalDateTime.now();

    /**
     * Report properties
     */
    private rebelo.reports.core.RRProperties prop;

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * Report engine Do not access directly to this propertie, use the
     * getJaspertPrint method
     */
    private ArrayList<JasperPrint> jasperPrint;

    /**
     * The properties to sign the report
     */
    private RRSignPdfProperties signProp = null;

    /**
     * The class that generates the jasper report
     */
    public Report() {
        if (null != logLevel) {
            Configurator.setLevel(getClass().getName(), logLevel);
        }
        LOG.debug("Instance initiated");
    }

    /**
     * The class that generates the jasper report
     *
     * @param prop
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public Report(@NotNull RRProperties prop) throws NullNotAllowedException {
        this();
        setProperties(prop);
    }

    /**
     * Set the report properties
     *
     * @param prop
     * @throws NullNotAllowedException
     */
    public final void setProperties(@NotNull RRProperties prop) throws NullNotAllowedException {
        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "properties");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "properties", "properties instance"));
        this.prop = prop;
    }

    /**
     *
     * Get the report properties
     *
     * @return
     * @throws NullNotAllowedException
     */
    @NotNull
    public RRProperties getProperties() throws NullNotAllowedException {
        if (prop == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "properties");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "properties", "properties instance"));
        return prop;
    }

    /**
     *
     * Set the JasperPrint engine.Whene o getJasperPrint or generate the report
     * if the not seted it will be setted automataclly. However the RRProperties
     * parameters, jasper file and driver properies must be seted or will throw
     * a exception, because JasperPrint can not be instanciated without this
     * parameters.
     *
     * This method is only if you wont to define your one instance for any
     * reazon This will be set automatically
     *
     * @param jasperPrint
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setJasperPrint(@NotNull ArrayList<JasperPrint> jasperPrint) throws NullNotAllowedException {
        if (jasperPrint == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "jasperPrint");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        this.jasperPrint = jasperPrint;
    }

    /**
     * Get the pdf sign properties
     *
     * @return null if no properties assigned
     */
    @Null
    public RRSignPdfProperties getSignProp() {
        LOG.traceEntry();
        return signProp;
    }

    /**
     * Set the pdf sign properties
     *
     * @param signProp
     */
    public void setSignProp(@Null RRSignPdfProperties signProp) {
        LOG.traceEntry(() -> String.format(
                "Seting pdf sign properties to '%s'",
                signProp == null ? "null" : "object")
        );
        this.signProp = signProp;
    }

    /**
     * Get the JasperReport engine
     *
     * When o getJasperPrint or you generate the report if the not seted it will
     * be setted automataclly.However the RRProperties parameters, jasper file
     * and driver properies must be seted or will throw a exception, because
     * JasperPrint can not be instanciated without this parameters.This method
     * exist to be possible to manipulate the JasperPrint before the generation
     * of the report
     *
     * @return
     * @throws rebelo.reports.core.datasource.DataSourceException
     * @throws net.sf.jasperreports.engine.JRException
     * @throws rebelo.reports.core.NullNotAllowedException
     * @throws rebelo.reports.core.RRException
     */
    @NotNull
    public ArrayList<JasperPrint> getJasperPrint()
            throws DataSourceException, JRException, NullNotAllowedException, RRException {

        if (jasperPrint == null) {
            jasperPrint = new ArrayList<>();
            IRRDsProperties dsProp = prop.getDataSourceProperties();

            for (int index = 1; index <= prop.getCopies(); index++) {

                LOG.debug("Create JasperPrint index '"
                        + new StringBuilder().append(index).toString()
                        + "'");

                prop.getParameters().put(RR_INDEX_PARAMETER, index);

                if (dsProp instanceof RRDsDatabase) {
                    jasperPrint.add(
                            JasperFillManager.fillReport(
                                    prop.getJasperFile(),
                                    prop.getParameters(),
                                    ((RRDsDatabase) dsProp).getDataSource()
                            )
                    );
                } else if (dsProp instanceof ARRDsJRDataSource) {
                    jasperPrint.add(
                            JasperFillManager.fillReport(
                                    prop.getJasperFile(),
                                    prop.getParameters(),
                                    ((ARRDsJRDataSource) dsProp).getDataSource()
                            )
                    );
                } else {
                    throw new RRException("Unknown datasource type to pass as argument to JasperFillManager.fillReport");
                }
            }
        }
        return jasperPrint;
    }

    /**
     * Gerenate the report
     *
     * @return
     * @throws NullNotAllowedException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws JRException
     * @throws RRException
     * @throws rebelo.reports.core.datasource.DataSourceException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws rebelo.reports.core.PrinterNotFoundException
     */
    @NotNull
    public net.sf.jasperreports.export.Exporter getExporter() throws
            NullNotAllowedException,
            ClassNotFoundException,
            InstantiationException,
            SQLException,
            IllegalAccessException,
            JRException,
            RRException,
            DataSourceException,
            RRPropertiesException,
            PrinterNotFoundException {

        LOG.debug("Start generate report");

        net.sf.jasperreports.export.Exporter exporter;

        SimpleExporterInput simpleExporterInput
                = SimpleExporterInput.getInstance(this.getJasperPrint());

        switch (prop.getType()) {
            case pdf:
                LOG.trace("Start export PDF report");
                RRPdfProperties pdfProp = (RRPdfProperties) prop.getTypeProperties();
                exporter = new JRPdfExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(pdfProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(pdfProp.getSimplePdfExporterConfiguration());
                LOG.trace("Seting the pdf sign properties");
                if (pdfProp.isSignPDF()) {
                    this.signProp = pdfProp.getSignProp();
                }
                break;
            case html:
                LOG.trace("Start export HTML report");
                RRHtmlProperties htmlProp = (RRHtmlProperties) prop.getTypeProperties();
                exporter = new HtmlExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(htmlProp.getSimpleHtmlExporterOutput());
                exporter.setConfiguration(htmlProp.getSimpleHtmlExporterConfiguration());
                break;
            case csv:
                LOG.trace("Start export CSV report");
                RRCsvProperties csvProp = (RRCsvProperties) prop.getTypeProperties();
                exporter = new JRCsvExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(csvProp.getSimpleWriterExporterOutput());
                exporter.setConfiguration(csvProp.getSimpleCsvReportConfiguration());
                break;
            case xls:
                LOG.trace("Start export XLS report");
                exporter = new JRXlsExporter();
                RRXlsProperties xlsProp = (RRXlsProperties) prop.getTypeProperties();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(xlsProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(xlsProp.getSimpleXlsReportConfiguration());
                break;
            case xml:
                LOG.trace("Start export XML report");
                RRXmlProperties xmlProp = (RRXmlProperties) prop.getTypeProperties();
                exporter = new JRXmlExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(xmlProp.getSimpleXmlExporterOutput());
                exporter.setConfiguration(xmlProp.getSimpleReportExportConfiguration());
                break;
            case rtf:
                LOG.trace("Start export RTF report");
                RRRtfProperties rtfProp = (RRRtfProperties) prop.getTypeProperties();
                exporter = new JRRtfExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(rtfProp.getSimpleWriterExporterOutput());
                exporter.setConfiguration(rtfProp.getSimpleRtfExporterConfiguration());
                break;
            case text:
                LOG.trace("Start export TEXT report");
                RRTextProperties txtProp = (RRTextProperties) prop.getTypeProperties();
                exporter = new JRTextExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(txtProp.getSimpleWriterExporterOutput());
                exporter.setConfiguration(txtProp.getSimpleTextExporterConfiguration());
                break;
            case pptx:
                LOG.trace("Start export PPTX report");
                RRPptxProperties pptxProp = (RRPptxProperties) prop.getTypeProperties();
                exporter = new JRPptxExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(pptxProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(new SimplePptxReportConfiguration());
                break;
            case xlsx:
                LOG.trace("Start export xlsx report");
                RRXlsxProperties xlsxProp = (RRXlsxProperties) prop.getTypeProperties();
                exporter = new JRXlsxExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(xlsxProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(xlsxProp.getSimpleXlsxExporterConfiguration());
                break;
            case docx:
                LOG.trace("Start export docx report");
                RRDocxProperties docxProp = (RRDocxProperties) prop.getTypeProperties();
                exporter = new JRDocxExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(docxProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(docxProp.getSimpleDocxExporterConfiguration());
                break;
            case ods:
                LOG.trace("Start export ods report");
                RROdsProperties odsProp = (RROdsProperties) prop.getTypeProperties();
                exporter = new JROdsExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(odsProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(odsProp.getSimpleOdsExporterConfiguration());
                break;
            case odt:
                LOG.trace("Start export odt report");
                RROdtProperties odtProp = (RROdtProperties) prop.getTypeProperties();
                exporter = new JROdtExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(odtProp.getSimpleOutputStreamExporterOutput());
                exporter.setConfiguration(odtProp.getSimpleOdtExporterConfiguration());
                break;
            case json:
                LOG.trace("Start export json report");
                RRJsonProperties jsonProp = (RRJsonProperties) prop.getTypeProperties();
                exporter = new JsonExporter();
                exporter.setExporterInput(simpleExporterInput);
                exporter.setExporterOutput(jsonProp.getSimpleJsonExporterOutput());
                exporter.setConfiguration(jsonProp.getSimpleJsonExporterConfiguration());
                break;
            case print:
                RRPrintProperties prtProp = (RRPrintProperties) prop.getTypeProperties();
                exporter = new JRPrintServiceExporter();
                exporter.setExporterInput(simpleExporterInput);

                if (jasperPrint.get(0).getOrientationValue() == net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE) {
                    prtProp.getPrintRequestAttributeSet().add(OrientationRequested.LANDSCAPE);
                } else {
                    prtProp.getPrintRequestAttributeSet().add(OrientationRequested.PORTRAIT);
                }
                exporter.setConfiguration(prtProp.getSimplePrintServiceExporterConfiguration());

                if (prtProp.getSelectedPrinter() == null || prtProp.getSelectedPrinter().trim().equals("")) {
                    PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                    prtProp.getPrintServiceAttributeSet().add(new PrinterName(service.getName(), null));
                    return exporter;
                }

                prtProp.getPrintServiceAttributeSet().add(new PrinterName(prtProp.getSelectedPrinter(), null));

                PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
                //Iterate through available printer, and once matched with our <selectedPrinter>
                if (services != null && services.length != 0) {
                    for (PrintService service : services) {
                        String existingPrinter = service.getName();
                        if (existingPrinter.equalsIgnoreCase(prtProp.getSelectedPrinter())) {
                            return exporter;
                        }
                    }
                }
                throw new PrinterNotFoundException("Printer " + prtProp.getSelectedPrinter() + " not found");
            //break;
            default:
                throw new RRException("Unuported report type "
                        .concat(prop.getType().toString()));
        }

        return exporter;
    }

    /**
     *
     * Export the Report
     *
     * @throws NullNotAllowedException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws JRException
     * @throws rebelo.reports.core.datasource.DataSourceException
     * @throws rebelo.reports.core.RRException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws rebelo.reports.core.PrinterNotFoundException
     * @throws rebelo.reports.core.sign.SignPdfException
     */
    public void exportReport() throws
            NullNotAllowedException,
            ClassNotFoundException,
            InstantiationException,
            SQLException,
            IllegalAccessException,
            JRException,
            DataSourceException,
            RRException,
            RRPropertiesException,
            PrinterNotFoundException,
            SignPdfException {
        LOG.traceEntry();
        this.getExporter().exportReport();

        LOG.info("Report exported from Jasper");
        LOG.debug("Check if the report is to be signed");

        if (this.prop.getType().equals(RRProperties.Types.pdf)) {
            LOG.info("The report is a PDF");
            RRPdfProperties pdfProp = (RRPdfProperties) this.prop.getTypeProperties();
            if (pdfProp.isSignPDF()) {
                this.signPdf();
            } else {
                LOG.info("The report is not seted to be signed");
            }
        } else {
            LOG.info("The report is not a PDF (is not to be signed)");
        }

        LOG.traceExit("End of export report, executed in {} miliseconds",
                ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
    }

    /**
     *
     * Sign the PDF
     *
     * @throws SignPdfException
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void signPdf() throws SignPdfException, NullNotAllowedException {
        LOG.traceEntry();
        try {
            if (this.signProp == null) {
                throw new SignPdfException("RRSignPdfProperties is null, pdf can not be signed");
            }

            File tmp = new File(prop.getOutputFile() + ".tmp");

            RRSignPdf signPdf = new RRSignPdf(
                    this.signProp,
                    this.prop.getOutputFile(),
                    tmp.getAbsolutePath()
            );
            LOG.debug("Is going to sign the PDF");
            signPdf.signPdf();

            LOG.info(() -> "PDF is sigend in the temp file " + tmp.getAbsolutePath());

            LOG.trace("IS going to delete not signed pdf and rename the temp sign file");

            File pdf = new File(prop.getOutputFile());
            if (pdf.exists()) {
                LOG.trace(() -> "Deleting file " + pdf.getAbsolutePath());
                pdf.delete();
                if(pdf.exists()){
                    LOG.debug(() -> "File " + pdf.getAbsolutePath() + " was not deleted");
                }
            } else {
                LOG.warn(() -> String.format(
                        "File '%s' not exist to be deleted",
                        pdf.getAbsolutePath())
                );
            }

            if (tmp.exists()) {
                LOG.trace(() -> String.format(
                        "Renaming file '%s' to '%s'",
                        tmp.getAbsolutePath(),
                        pdf.getAbsolutePath()
                ));
                tmp.renameTo(pdf);
            } else {
                String msg = String.format(
                        "The signed pdf file '%s' not exist to be renamed",
                        tmp.getAbsoluteFile()
                );
                LOG.error(msg);
                throw new SignPdfException(msg);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            File file = new File(prop.getOutputFile());
            if (file.exists()) {
                file.delete();
            }
            throw new SignPdfException(e);
        }
    }

}
