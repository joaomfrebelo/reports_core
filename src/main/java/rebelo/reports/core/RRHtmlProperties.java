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

import javax.validation.constraints.NotNull;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static rebelo.reports.core.ARRPropSWEO.LOG;
import rebelo.reports.core.common.Message;

/**
 * The HTML exporter properties
 * @author João Rebelo
 */
public class RRHtmlProperties {
    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();
    
    /**
     * Report properties
     */
    private final RRProperties prop;
    
    /**
     * The SimpleHtmlExporterOutput
     */
    private final SimpleHtmlExporterOutput simHtmlExpOut;
    /**
     * The SimpleHtmlExporterConfiguration
     */
    private final SimpleHtmlExporterConfiguration simHtmlConfig;
    
    /**
     * The HTML exporter properties
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RRHtmlProperties(@NotNull RRProperties prop) throws NullNotAllowedException{
        LOG.debug("Starting instance 'RRHtmlProperties'");
        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "RRHtmlProperties");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.prop = prop;
        this.simHtmlExpOut = new SimpleHtmlExporterOutput(prop.getOutputFile());
        this.simHtmlConfig = new SimpleHtmlExporterConfiguration();
    }
    
    /**
     * Get the SimpleHtmlExporterOutput
     * 
     * @return
     */
    @NotNull
    public SimpleHtmlExporterOutput getSimpleHtmlExporterOutput(){
        LOG.trace("Gettting SimpleHtmlExporterOutput");
        return simHtmlExpOut;
    }
    
    /**
     * Get the SimpleHtmlExporterConfiguration
     * 
     * @return
     */
    @NotNull
    public SimpleHtmlExporterConfiguration getSimpleHtmlExporterConfiguration(){
        LOG.trace("Gettting SimpleHtmlExporterConfiguration");
        return simHtmlConfig;
    }
    
}
