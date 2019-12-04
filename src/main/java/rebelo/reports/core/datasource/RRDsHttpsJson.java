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
package rebelo.reports.core.datasource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;

/**
 * Get the JsonDataSource
 * 
 * @author João Rebelo
 */
public class RRDsHttpsJson extends ARRDsHttps {

    @Override
    public JRDataSource getDataSource() throws DataSourceException {
        LOG.debug("Get data source");
        try {
            getUrlConnection().setRequestProperty("Accept", "application/json");
            JsonDataSource jds = new JsonDataSource(getInputStream());

            if (getDatePattern() != null) {
                jds.setDatePattern(getDatePattern());
            }

            if (getNumberPattern() != null) {
                jds.setNumberPattern(getNumberPattern());
            }
            
            JRDataSource ds = (JRDataSource) jds;
            return ds;
        } catch (@SuppressWarnings("UseSpecificCatch") Exception e) {
            throw new DataSourceException(e);
        }
    }
}
