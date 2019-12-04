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

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.lang3.NotImplementedException;
import org.xml.sax.SAXException;

/**
 *
 * @author João Rebelo
 */
public class ParseJson extends AParse {

    public ParseJson() {

    }

    @Override
    public Unmarshaller unmarshallInstance()
            throws SAXException, JAXBException {
        throw new NotImplementedException("Not implemented");
//        Unmarshaller unmarshaller = super.unmarshallInstance();
//        unmarshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, "application/json");
//        unmarshaller.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, true);

//        return unmarshaller;
    }

}
