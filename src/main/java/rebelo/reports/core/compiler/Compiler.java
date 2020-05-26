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
package rebelo.reports.core.compiler;

import java.io.File;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import javax.validation.constraints.NotNull;
import rebelo.reports.core.Report;

/**
 *
 * @author João Rebelo
 */
public class Compiler {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    public Compiler() {
        if (null != Report.logLevel) {
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
        LOG.debug("Instance initiated");
    }

    /**
     * Compile JRXML file to jasper file
     * @param jrxmlFile
     * @param jasperFile
     * @throws JRException 
     */
    public void CompileJrxmlToJasper(@NotNull File jrxmlFile, @NotNull File jasperFile) throws JRException {
        LOG.debug("JRXML File: " + jrxmlFile.getAbsolutePath());
        LOG.debug("JASPER File: " + jasperFile.getAbsolutePath());
        JasperCompileManager.compileReportToFile(
                jrxmlFile.getAbsolutePath(), 
                jasperFile.getAbsolutePath()
        );
    }

    /**
     * Revert Jasper file to Jrxml
     * @see https://stackoverflow.com/a/7752252/6397645
     * @param jasperFile
     * @param jrxmlFile
     * @throws JRException 
     */
    public void RevertJasperToJrxml(@NotNull  File jasperFile, @NotNull File jrxmlFile) throws JRException {
        LOG.debug("JASPER File: " + jasperFile.getAbsolutePath());
        LOG.debug("JRXML File: " + jrxmlFile.getAbsolutePath());
        JasperReport report = (JasperReport) JRLoader.loadObject(jasperFile);
        JRXmlWriter.writeReport(report, jrxmlFile.getAbsolutePath(), "UTF-8");
    }

}
