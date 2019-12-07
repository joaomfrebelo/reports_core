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

import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import rebelo.reports.core.common.Message;
import rebelo.reports.core.sign.RRSignPdfProperties;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * The class for definition of the PDF report properties
 * and PDF exporter properties
 * @author João Rebelo
 */
public class RRPdfProperties extends ARRPropSOSEO{
    
    /**
     * The SimplePdfExporterConfiguration
     * you can configure this Exporter 
     */
    private final SimplePdfExporterConfiguration pdfExportConfig;
    
    /**
     * Define if the pdf is signed
     */
    private boolean signPDF = false;

    /**
     * The signature properties
     */
    @Null
    private RRSignPdfProperties signProp = null;
    
    /**
     * 
     * The PDF exporter properties
     * 
     * @param prop 
     * @throws rebelo.reports.core.NullNotAllowedException 
     */
    public RRPdfProperties(@NotNull RRProperties prop) throws NullNotAllowedException {        
        super(prop);
        this.pdfExportConfig = new SimplePdfExporterConfiguration();
        LOG.debug("Start instance 'RRPdfProperties'");
    }
    
    /**
     * Get the SimplePdfExporterConfiguration
     * 
     * @return
     */
    @NotNull
    public SimplePdfExporterConfiguration getSimplePdfExporterConfiguration(){
        LOG.trace("Gettting SimplePdfExporterConfiguration");
        return pdfExportConfig;
    }
    
    /**
     * Get if the pdf report is to be digital signed
     * @return 
     */
    @NotNull
    public Boolean isSignPDF() {
        LOG.trace(()->String.format(Message.GETTED_VALUE, "signPDF", signPDF));
        return signPDF;
    }

    /**
     * Define if the pdf report is to be digital signed
     * @param signPDF 
     * @throws NullNotAllowedException
     */
    public void isSignPDF(@NotNull Boolean signPDF)  throws NullNotAllowedException{
        if(signPDF == null){
            String msg = String.format(Message.SET_NULL_ERROR, "signPDF");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(()->String.format(Message.SETTED_VALUE, "signPDF", signPDF.toString()));
        this.signPDF = signPDF;
    }
    
    

    /**
     * Get the signPdf properties
     * @return 
     */
    @Null
    public rebelo.reports.core.sign.RRSignPdfProperties getSignProp() {
        LOG.trace(()->String.format(
                Message.GETTED_VALUE, 
                "signProperties", 
                "rebelo.reports.core.sign.Properties instance"));
        return signProp;
    }

    /**
     * Set the signPdf properties
     * @param signProp 
     */
    public void setSignProp(@Null rebelo.reports.core.sign.RRSignPdfProperties signProp) {
        LOG.trace(()->String.format(Message.SETTED_VALUE, 
                "signProperties", 
                signProp == null ? "null" : "rebelo.reports.core.sign.Properties instance"));
        this.signProp = signProp;
    }
    
}
