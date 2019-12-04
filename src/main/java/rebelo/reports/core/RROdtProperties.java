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

import net.sf.jasperreports.export.SimpleOdtExporterConfiguration;

/**
 *
 * @author João Rebelo
 */
public class RROdtProperties extends ARRPropSOSEO{

    private final SimpleOdtExporterConfiguration simOdtExpconfig;
    
    /**
     * 
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RROdtProperties(RRProperties prop) throws NullNotAllowedException {
        super(prop);
        this.simOdtExpconfig = new SimpleOdtExporterConfiguration();
        LOG.debug("Start instance");
    }
    
/**
     * The SimpleOdtExporterConfiguration
     * you can configure this Exporter
     */
        /**
     * Get the SimpleOdtExporterConfiguration
     * 
     * @return
     */
    public SimpleOdtExporterConfiguration getSimpleOdtExporterConfiguration(){
        LOG.trace("Gettting SimpleOdtExporterConfiguration");
        return simOdtExpconfig;
    }
    
}
