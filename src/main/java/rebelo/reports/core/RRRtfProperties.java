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

import net.sf.jasperreports.export.SimpleRtfExporterConfiguration;

/**
 * The RTF exporter properties
 * @author João Rebelo
 */
public class RRRtfProperties extends ARRPropSWEO{

    /**
     * The SimpleRtfExporterConfiguration
     * you can configure this Exporter 
     */
    private final SimpleRtfExporterConfiguration simRtfExpConf; 
    
    /**
     * 
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RRRtfProperties(RRProperties prop) throws NullNotAllowedException {
        super(prop);
        this.simRtfExpConf = new SimpleRtfExporterConfiguration();
        LOG.debug("Start instance");
    }
    
    /**
     * Get the SimpleRtfExporterConfiguration
     * 
     * @return
     */
    public SimpleRtfExporterConfiguration getSimpleRtfExporterConfiguration(){
        LOG.trace("Gettting SimpleRtfExporterConfiguration");
        return simRtfExpConf;
    }
    
}
