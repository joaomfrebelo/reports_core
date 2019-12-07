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

import javax.validation.constraints.NotNull;
import java.io.File;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.common.Message;
import static rebelo.reports.core.datasource.ARRDsJRDataSource.LOG;

/**
 *
 * @author João Rebelo
 */
public class RRDsFileJson extends ARRDsJRDataSource {

    private File file;

    /**
     * Get the json file
     * 
     * @return
     * @throws NullNotAllowedException 
     */
    @NotNull
    public File getFile() throws NullNotAllowedException {
        if (file == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "getFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "getFile", file.getAbsolutePath()));
        return file;
    }

    /**
     * set the json file
     * 
     * @param file
     * @throws NullNotAllowedException 
     */
    public void setFile(@NotNull File file) throws NullNotAllowedException {
        if (file == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setFile");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "setFile", file.getAbsolutePath()));
        this.file = file;
    }
        
    /**
     * Get the JsonDataSource
     * @return
     * @throws DataSourceException 
     */
    @Override
    @NotNull
    public JRDataSource getDataSource() throws DataSourceException {
        LOG.debug("Get data source");
        try {
            
            JsonDataSource jds = new JsonDataSource(getFile());

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
