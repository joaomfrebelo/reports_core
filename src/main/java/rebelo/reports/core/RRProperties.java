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
import java.util.HashMap;
import javax.validation.constraints.Null;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rebelo.reports.core.common.Message;
import rebelo.reports.core.datasource.IRRDsProperties;

/**
 * Class that have the Report's properties/defenitions
 *
 * @author João Rebelo
 */
public class RRProperties {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * A list of report formats type supported report supported
     */
    public enum Types {
        pdf,
        csv,
        html,
        xls,
        xml,
        rtf,
        text,
        pptx,
        xlsx,
        docx,
        odt,
        ods,
        json,
        print
    }

    /**
     * Encode for xls, csv output type
     */
    private String encoding;

    /**
     * The jasper report file (compiled) report.jasper
     */
    private String jasperFile;

    /**
     * The file name to write the report without extension. The extension will
     * be add by the format Ex: if you wont a file output called like report.pdf
     * set this variable to report
     */
    private String outputFile;

    /**
     * The parameters to be passed to the report
     */
    private final HashMap<String, Object> parameters = new HashMap<>();

    /**
     * A list of the report type
     *
     */
    private Types type;

    /**
     * Each type have it's owns properties object Must be catch when getted
     */
    private Object typePropertie;

    /**
     * The data source properties
     */
    private rebelo.reports.core.datasource.IRRDsProperties dsProp;

    public RRProperties() {
        LOG.debug(() -> "instance created");
        encoding = "UTF-8";
    }

    /**
     * Get encoding
     *
     * @return
     */
    @NotNull
    public String getEncoding() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "encoding", encoding));
        return encoding;
    }

    /**
     * Set encoding Default UTF-8
     *
     * @param encoding
     * @throws NullNotAllowedException
     */
    public void setEncoding(@NotNull String encoding) throws NullNotAllowedException {
        if (encoding == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "encoding");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "encoding", encoding));
        this.encoding = encoding;
    }

    /**
     * Get jasper repor file path
     *
     * @return
     * @throws NullNotAllowedException
     */
    @NotNull
    public String getJasperFile() throws NullNotAllowedException {
        if (jasperFile == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "jasperFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "jasperFile", jasperFile));
        return jasperFile;
    }

    /**
     * Set jasper repor file path
     *
     * @param jasperFile
     * @throws NullNotAllowedException
     */
    public void setJasperFile(@NotNull String jasperFile) throws NullNotAllowedException {
        if (jasperFile == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "jasperFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "jasperFile", jasperFile));
        this.jasperFile = jasperFile;
    }

    /**
     * Get output file path
     *
     * @return
     * @throws NullNotAllowedException
     */
    @NotNull
    public String getOutputFile() throws NullNotAllowedException {
        if (outputFile == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "outputFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "outputFile", outputFile));
        return outputFile;
    }

    /**
     * Set output file path
     *
     * @param outputFile
     * @throws NullNotAllowedException
     */
    public void setOutputFile(@NotNull String outputFile) throws NullNotAllowedException, RRPropertiesException {
        if (outputFile == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "outputFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "outputFile", outputFile));

        File out = new File(outputFile);
        if (out.getParent() != null) {
            if (out.getParentFile().isDirectory() == false) {
                out.getParentFile().mkdir();
                LOG.trace(() -> String.format(Message.SETTED_VALUE, "directory outputFile created", outputFile));
            }
        }else{
            LOG.error("Outpufile path parente is not a directory");
            throw new RRPropertiesException("Outpufile path parente is not a directory");
        }
        this.outputFile = outputFile;
    }

    /**
     * Add a type of report to generate
     *
     * @param type
     * @throws NullNotAllowedException
     */
    public void setType(@NotNull Types type) throws NullNotAllowedException {
        if (type == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "type");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.type = type;
    }

    /**
     * Get the report formats to generate
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public Types getType() throws NullNotAllowedException {
        if (type == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "type");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "type", type));
        return type;
    }

    /**
     * Add a report parameterto be passed
     *
     * @param name
     * @param value
     * @throws NullNotAllowedException
     */
    public void addParameter(@NotNull String name, @NotNull Object value) throws NullNotAllowedException {
        String msg;
        if (name == null) {
            msg = String.format(Message.SET_NULL_ERROR, "parameter name");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        if (value == null) {
            msg = String.format(Message.SET_NULL_ERROR, "parameter value");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE,
                "parameter",
                "name=>" + name + " ;  value=>" + value.toString()));
        this.parameters.put(name, value);
    }

    /**
     * Get the report parameter to be passed
     *
     * @return
     */
    @NotNull
    public HashMap<String, Object> getParameters() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE,
                "parameter", "parameters HashMap"));
        return this.parameters;
    }

    /**
     * Get the data source properties
     *
     * @return
     */
    @Null
    public rebelo.reports.core.datasource.IRRDsProperties getDataSourceProperties() {
        return this.dsProp;
    }

    /**
     *
     * Set the datasource properties
     *
     * @param dsProp
     */
    public void setDataSourceProperties(@NotNull IRRDsProperties dsProp) {
        this.dsProp = dsProp;
    }

    /**
     * Get the report type properties To configure The Streamoutput or the
     * exporter output get the typeProperties and configure teh instance You
     * have to cast for the proper object.<br>
     * Exmeple for pdf RRPdfProperties rrpdf =
     * (RRPdfProperties)getTypeProperties();
     *
     * @return
     * @throws java.lang.Exception
     */
    @NotNull
    public Object getTypeProperties() throws Exception {
        if (typePropertie == null) {
            switch (type) {
                case pdf:
                    typePropertie = new RRPdfProperties(this);
                    break;
                case csv:
                    typePropertie = new RRCsvProperties(this);
                    break;
                case docx:
                    typePropertie = new RRDocxProperties(this);
                    break;
                case html:
                    typePropertie = new RRHtmlProperties(this);
                    break;
                case json:
                    typePropertie = new RRJsonProperties(this);
                    break;
                case ods:
                    typePropertie = new RROdsProperties(this);
                    break;
                case odt:
                    typePropertie = new RROdtProperties(this);
                    break;
                case pptx:
                    typePropertie = new RRPptxProperties(this);
                    break;
                case rtf:
                    typePropertie = new RRRtfProperties(this);
                    break;
                case text:
                    typePropertie = new RRTextProperties(this);
                    break;
                case xls:
                    typePropertie = new RRXlsProperties(this);
                    break;
                case xlsx:
                    typePropertie = new RRXlsxProperties(this);
                    break;
                case xml:
                    typePropertie = new RRXmlProperties(this);
                    break;
                case print:
                    typePropertie = new RRPrintProperties();
                    break;
                default:
                    throw new Exception("Unknow type in RRPropertis getTypeProperties");
            }
        }
        return typePropertie;
    }

}
