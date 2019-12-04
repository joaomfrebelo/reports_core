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

import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import rebelo.reports.core.NullNotAllowedException;

/**
 *
 * @author João Rebelo
 */
public abstract class ARRDsHttps extends ARRDsHttp{
    
    /**
     * The Http
     */
    private HttpsURLConnection conn;

    /**
     * Get the Https connection
     * @return 
     * @throws rebelo.reports.core.datasource.DataSourceException 
     * @throws java.io.IOException 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    @Override
    public HttpsURLConnection getUrlConnection() throws DataSourceException, IOException, NullNotAllowedException{
        if(url == null){
            throw new DataSourceException("Url for connection is not initialized or seted");
        }
        if(conn == null){
            conn = (HttpsURLConnection)url.openConnection();
            setGeneric(conn);
        }
        LOG.trace("Geting Url Connection (getUrlConnection)");
        return conn;
    }

    
    
    
    
    

}
