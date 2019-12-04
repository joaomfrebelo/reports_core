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

import net.sf.jasperreports.export.SimpleReportExportConfiguration;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The XML exporrter properties
 *
 * @author João Rebelo
 */
public class RRXmlProperties {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * The SimpleXmlExporterOutput
     */
    private final SimpleXmlExporterOutput simpXmlOut;
    /**
     * SimpleReportExportConfiguration
     */
    private final SimpleReportExportConfiguration simRepCong;

    /**
     * Report properties
     */
    private final RRProperties prop;

    /**
     * 
     * @param prop
     * @throws NullNotAllowedException 
     */
    public RRXmlProperties(RRProperties prop) throws NullNotAllowedException {
        LOG.debug("Starting instance");
        this.prop = prop;
        this.simpXmlOut = new SimpleXmlExporterOutput(prop.getOutputFile(), prop.getEncoding());
        this.simRepCong = new SimpleReportExportConfiguration();
    }
    
    /**
     * Get the SimpleXmlExporterOutput
     * 
     * @return
     */
    public SimpleXmlExporterOutput getSimpleXmlExporterOutput() {
        LOG.trace("Gettting SimpleXmlExporterOutput");
        return simpXmlOut;
    }
    
    /**
     * Get the SimpleReportExportConfiguration
     * 
     * @return
     */
    public SimpleReportExportConfiguration getSimpleReportExportConfiguration(){
        LOG.trace("Gettting SimpleReportExportConfiguration");
        return simRepCong;
    }
}
