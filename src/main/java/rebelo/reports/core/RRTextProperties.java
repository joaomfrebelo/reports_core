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

import net.sf.jasperreports.export.SimpleTextExporterConfiguration;

/**
 * The text exporter properties
 * @author João Rebelo
 */
public class RRTextProperties extends ARRPropSWEO{

    /**
     * The SimpleTextExporterConfiguration
     * you can configure this Exporter 
     */
    private final SimpleTextExporterConfiguration simTextExpConfig;
    
    /**
     * 
     * @param prop
     * @throws NullNotAllowedException 
     */
    public RRTextProperties(RRProperties prop) throws NullNotAllowedException {
        super(prop);
        this.simTextExpConfig = new SimpleTextExporterConfiguration();
        LOG.debug("Start instance");
    }
    
    /**
     * Get the SimpleTextExporterConfiguration
     * 
     * @return
     */
    public SimpleTextExporterConfiguration getSimpleTextExporterConfiguration(){
        LOG.trace("Gettting SimpleTextExporterConfiguration");
        return simTextExpConfig;
    }
    
}
