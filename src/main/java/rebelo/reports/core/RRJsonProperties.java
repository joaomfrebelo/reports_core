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
import net.sf.jasperreports.export.SimpleJsonExporterConfiguration;
import net.sf.jasperreports.export.SimpleJsonExporterOutput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import static rebelo.reports.core.Report.logLevel;
import rebelo.reports.core.common.Message;

/**
 *
 * @author João Rebelo
 */
public class RRJsonProperties{

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();
    
    /**
     * The SimpleJsonExporterOutput
     */
    private final SimpleJsonExporterOutput simpJsonExpOut;
    
    /**
     * The SimpleJsonExporterConfiguration
     * you can configure this Exporter 
     */
    private final SimpleJsonExporterConfiguration simJsonExpconfig;

    /**
     * Report properties
     */
    private final RRProperties prop;
    
    /**
     * 
     * @param prop
     * @throws NullNotAllowedException 
     */
    public RRJsonProperties(@NotNull RRProperties prop) throws NullNotAllowedException {        
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
        LOG.debug("Start instance 'RRJsonProperties'");
        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "RRJsonProperties");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.simJsonExpconfig = new SimpleJsonExporterConfiguration();
        this.simpJsonExpOut = new SimpleJsonExporterOutput(prop.getOutputFile(), prop.getEncoding());
        this.prop = prop;
    }
    
    /**
     * Get the SimpleJsonExporterConfiguration
     * 
     * @return
     */
    @NotNull
    public SimpleJsonExporterConfiguration getSimpleJsonExporterConfiguration(){
        LOG.trace("Gettting SimpleJsonExporterConfiguration");
        return simJsonExpconfig;
    }
    
    /**
     * Get the SimpleJsonExporterOutput
     * 
     * @return
     */
    @NotNull
    public SimpleJsonExporterOutput getSimpleJsonExporterOutput(){
        LOG.trace("Gettting SimpleJsonExporterOutput");
        return simpJsonExpOut;
    }
    
    
    
    
}
