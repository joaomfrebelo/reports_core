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
package rebelo.reports.core.parse;

import java.net.MalformedURLException;
import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.RRProperties;
import rebelo.reports.core.RRPropertiesException;
import rebelo.reports.core.datasource.DataSourceException;
import rebelo.reports.core.parse.pojo.Rreport;

/**
 *
 * Parser Class
 * 
 * @author João Rebelo
 */
public class Parse extends AParse{
    
    public Parse(){
        super();
    }
    
    /**
     * 
     * Parse the Rreport (rebelo.reports.core.prase.pojo)
     * 
     * @param rreport
     * @return
     * @throws SAXException
     * @throws rebelo.reports.core.NullNotAllowedException
     * @throws javax.xml.bind.JAXBException
     * @throws rebelo.reports.core.RRPropertiesException
     * @throws rebelo.reports.core.parse.ParseException
     * @throws java.net.MalformedURLException
     * @throws rebelo.reports.core.datasource.DataSourceException
     */
    @Override
    public RRProperties parse(@NotNull Rreport rreport) 
            throws SAXException,
                   NullNotAllowedException, 
                   JAXBException, 
                   RRPropertiesException, 
                   ParseException, 
                   MalformedURLException, 
                   DataSourceException{
        return super.parse(rreport);
    }
    
}
