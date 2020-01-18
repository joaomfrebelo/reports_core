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

import javax.validation.constraints.Null;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import rebelo.reports.core.Report;
import rebelo.reports.core.common.Message;

/**
 *
 * @author João Rebelo
 */
public abstract class ARRDsJRDataSource implements IRRDsProperties{
    
    /**
     * Date pattern
     */
    private String datePattern;
    
    /**
     * Number Pattern
     */
    private String numberPattern;
       
    
    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();
    
    /**
     * 
     * Get the JRDataSource to use in the JasperFillManager
     * 
     * @return
     * @throws DataSourceException 
     */
    @Override 
    public abstract JRDataSource getDataSource() throws DataSourceException;

    public ARRDsJRDataSource() {        
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
            Configurator.setLevel(ARRDsJRDataSource.class.getName(), Report.logLevel);
        }
    }

    /**
     * Get Date Pattern
     * @return 
     */
    @Null
    public String getDatePattern() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "getDatePattern", datePattern == null ? "null" : datePattern));
        return datePattern;
    }

    /**
     * 
     * Set Date Pattern
     * 
     * @param datePattern 
     */
    @Null
    public void setDatePattern(String datePattern) {
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "getDatePattern", datePattern == null ? "null" : datePattern));
        this.datePattern = datePattern;
    }

    /**
     * Get Number Pattern
     * @return 
     */
    @Null
    public String getNumberPattern() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "getNumberPattern", numberPattern == null ? "null" : numberPattern));
        return numberPattern;
    }

    /**
     * Set Number Pattern
     * @param numberPattern 
     */
    @Null
    public void setNumberPattern(String numberPattern) {
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "getNumberPattern", numberPattern == null ? "null" : numberPattern));
        this.numberPattern = numberPattern;
    }
    
}
