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
import javax.validation.constraints.Null;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import static rebelo.reports.core.Report.logLevel;

/**
 *
 * @author João Rebelo
 */
public class RRPrintProperties {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * The SimplePrintServiceExporterConfiguration you can configure this
     * Exporter
     */
    private final SimplePrintServiceExporterConfiguration simPrintExpConf = new SimplePrintServiceExporterConfiguration();

    /**
     * The printer name Ex:"Microsoft XPS Document Writer" <br>
     * EX2: "\\\\S-BPPRINT\\HP Color LaserJet 4700"; // examlpe to network
     * shared printer
     */
    @Null
    private String selectedPrinter;

    /**
     * Printer requet atribute
     */
    private final PrintRequestAttributeSet printRequestAttributeSet;

    /**
     * Printer Servie atribute
     */
    private final PrintServiceAttributeSet printServiceAttributeSet;

    /**
     * 
     */
    public RRPrintProperties() {
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
        this.printRequestAttributeSet = new HashPrintRequestAttributeSet();
        this.printServiceAttributeSet = new HashPrintServiceAttributeSet();
        this.simPrintExpConf.setPrintRequestAttributeSet(printRequestAttributeSet);
        this.simPrintExpConf.setPrintServiceAttributeSet(printServiceAttributeSet);
    }

    /**
     * 
     * getSimplePrintServiceExporterConfiguration
     * 
     * Config the printer service exporter
     * 
     * @return 
     */
    @NotNull
    public SimplePrintServiceExporterConfiguration getSimplePrintServiceExporterConfiguration() {
        LOG.trace("Getting SimplePrintServiceExporterConfiguration");
        return simPrintExpConf;
    }

    /**
     * Get selected printer
     *
     * @return
     */
    @Null
    public String getSelectedPrinter() {
        return selectedPrinter;
    }

    /**
     * Set Selected printer
     *
     * @param selectedPrinter
     */
    public void setSelectedPrinter(@Null String selectedPrinter) {
        this.selectedPrinter = selectedPrinter;
    }

    /**
     * Get the PrintRequestAttributeSet to configure
     *
     * @return
     */
    @NotNull
    public PrintRequestAttributeSet getPrintRequestAttributeSet() {
        return printRequestAttributeSet;
    }

    /**
     * Get the PrintServiceAttributeSet to configure
     *
     * @return
     */
    @NotNull
    public PrintServiceAttributeSet getPrintServiceAttributeSet() {
        return printServiceAttributeSet;
    }

}
