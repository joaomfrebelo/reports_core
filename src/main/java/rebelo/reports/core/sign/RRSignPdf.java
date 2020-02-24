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
package rebelo.reports.core.sign;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.Report;

/**
 *
 * @author joaorebelo
 */
public class RRSignPdf {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * The file to be signed
     */
    protected final String inputFile;

    /**
     * The signed file
     */
    protected final String outputFile;

    /**
     * The Sign pdf properties
     */
    private final RRSignPdfProperties rRSignPdfProperties;

    /**
     * 
     * @param rRPdfProperties
     * @param inputFile the file to be signed
     * @param outputFile the signed file
     */
    public RRSignPdf(@NotNull RRSignPdfProperties rRPdfProperties,
            String inputFile,
            String outputFile) {
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
        this.rRSignPdfProperties = rRPdfProperties;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        LOG.debug("Start instance");
    }

    /**
     * Sign the PDF<br>
     *
     * 
     * @see http://cysorz-software-hardware.blogspot.com/2008/11/how-to-sign-pdf-using-itext-and.html
     * 
     * 
     * @throws KeyStoreException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws UnrecoverableKeyException
     * @throws CertificateException
     * @throws DocumentException
     * @throws NullNotAllowedException
     * @throws Exception
     */
    public void signPdf()
            throws KeyStoreException,
            IOException,
            NoSuchAlgorithmException,
            UnrecoverableKeyException,
            CertificateException,
            DocumentException,
            NullNotAllowedException,
            Exception {

        
        @SuppressWarnings("null")
        String keyFile = rRSignPdfProperties.getJavaKeyStorePath();

        PdfName filter;
        switch(rRSignPdfProperties.getType()){
            case SELF:
                filter = PdfSignatureAppearance.SELF_SIGNED;
                break;
            default:
                throw new Exception("Sign type Unknown");
        }
        
        
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(keyFile), rRSignPdfProperties.getJavaKeyStorePassword().toCharArray());
        PrivateKey key = (PrivateKey) ks.getKey(
                rRSignPdfProperties.getCertificateName(),
                rRSignPdfProperties.getCertificatePassword().toCharArray());

        java.security.cert.Certificate[] chain = ks.getCertificateChain(rRSignPdfProperties.getCertificateName());
        PdfReader reader = new PdfReader(inputFile);
        FileOutputStream fout = new FileOutputStream(outputFile);
        PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0');
        PdfSignatureAppearance sap = stp.getSignatureAppearance();
        sap.setCrypto(key, (java.security.cert.Certificate[]) chain, null, filter);
        sap.setReason(rRSignPdfProperties.getReazon());
        sap.setLocation(rRSignPdfProperties.getLocation());
        sap.setContact(rRSignPdfProperties.getContact());

        if (rRSignPdfProperties.isVisible()) {
            sap.setVisibleSignature(rRSignPdfProperties.getRectangle(), 1, null);
        }

        int level;
        switch (rRSignPdfProperties.getLevel()) {
            case CERTIFIED_FORM_FILLING:
                level = PdfSignatureAppearance.CERTIFIED_FORM_FILLING;
                break;
            case CERTIFIED_FORM_FILLING_AND_ANNOTATIONS:
                level = PdfSignatureAppearance.CERTIFIED_FORM_FILLING_AND_ANNOTATIONS;
                break;
            case CERTIFIED_NO_CHANGES_ALLOWED:
                level = PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED;
                break;
            default:
                throw new Exception("Level of certification not Knowed");
        }
        sap.setCertificationLevel(level);
        stp.close();
        reader.close();
        fout.close();
    }
}
