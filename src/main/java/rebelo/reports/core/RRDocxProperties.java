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
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import static rebelo.reports.core.RRHtmlProperties.LOG;
import rebelo.reports.core.common.Message;

/**
 *
 * @author João Rebelo
 */
public class RRDocxProperties extends ARRPropSOSEO{

    /**
     * The SimpleDocxExporterConfiguration
     * you can configure this Exporter 
     */
    private final SimpleDocxExporterConfiguration simDocxExpconfig;
    
    /**
     * 
     * @param prop
     * @throws NullNotAllowedException 
     */
    public RRDocxProperties(@NotNull RRProperties prop) throws NullNotAllowedException {
        super(prop);
        if (prop == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "RRDocxProperties");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.simDocxExpconfig = new SimpleDocxExporterConfiguration();
        LOG.debug("Start instance 'RRDocxProperties'");
    }
    
    /**
     * Get the SimpleDocxExporterConfiguration
     * 
     * @return
     */
    @NotNull
    public SimpleDocxExporterConfiguration getSimpleDocxExporterConfiguration(){
        LOG.trace("Gettting SimpleDocxExporterConfiguration");
        return simDocxExpconfig;
    }
    
}
