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
import net.sf.jasperreports.export.SimplePptxExporterConfiguration;

/**
 *
 * @author João Rebelo
 */
public class RRPptxProperties extends ARRPropSOSEO{

    private final SimplePptxExporterConfiguration simPptxExpconfig;
    
    /**
     * 
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RRPptxProperties(@NotNull RRProperties prop) throws NullNotAllowedException {
        super(prop);
        this.simPptxExpconfig = new SimplePptxExporterConfiguration();
        LOG.debug("Start instance 'RRPptxProperties'");
    }
    
    /**
     * 
     * Get the SimplePptxExporterConfiguration
     * <br>
     * The SimplePptxExporterConfiguration
     * you can configure this Exporter
     *
     * @return
     */
    @NotNull
    public SimplePptxExporterConfiguration getSimplePptxExporterConfiguration(){
        LOG.trace("Gettting SimplePptxExporterConfiguration");
        return simPptxExpconfig;
    }
    
}
