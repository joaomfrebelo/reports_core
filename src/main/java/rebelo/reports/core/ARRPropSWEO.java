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
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import static rebelo.reports.core.Report.logLevel;
import rebelo.reports.core.common.Message;

/**
 *
 * @author João Rebelo
 */
public abstract class ARRPropSWEO {
    
    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();
    
    /**
     * The report propertied
     */
    protected final RRProperties prop;
    
    /**
     * The SimpleWriterExporterOutput
     * you can configure this Exporter Stream
     * This is the Output stream that it will be used
     */
    protected final SimpleWriterExporterOutput sweoutput;
       
    /**
     * 
     * @param prop
     * @throws NullNotAllowedException 
     */
    public ARRPropSWEO(@NotNull RRProperties prop) throws NullNotAllowedException{        
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
            Configurator.setLevel(ARRPropSWEO.class.getName(), Report.logLevel);
        }
        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "ARRPropSWEO");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.prop = prop;
        this.sweoutput = new SimpleWriterExporterOutput(prop.getOutputFile(), prop.getEncoding());
    }
    
    /**
     * Get the SimpleWriterExporterOutput
     * 
     * @return 
     */
    @NotNull
    public SimpleWriterExporterOutput getSimpleWriterExporterOutput(){
        LOG.trace("Getting SimpleWriterExporterOutput");
        return sweoutput;
    }
    
}
