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
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;

/**
 * The Exporter CSV properties
 * @author João Rebelo
 */
public class RRCsvProperties extends ARRPropSWEO{
    
    /**
     * The SimpleCsvReportConfiguration
     * you can configure this Exporter 
     */
    private final SimpleCsvReportConfiguration simCsvConfig;
    
    /**
     * The Exporter CSV properties
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RRCsvProperties(@NotNull RRProperties prop) throws NullNotAllowedException {
        super(prop);
        this.simCsvConfig = new SimpleCsvReportConfiguration();
        LOG.debug("Start instance 'RRCsvProperties'");
    }
    
    /**
     * Get the SimpleCsvReportConfiguration
     * 
     * @return
     */
    @NotNull
    public SimpleCsvReportConfiguration getSimpleCsvReportConfiguration(){
        LOG.trace("Gettting SimpleCsvReportConfiguration");
        return simCsvConfig;
    }
    
}
