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

import com.itextpdf.text.Rectangle;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.Report;
import rebelo.reports.core.common.Message;

/**
 * The properties to sign the pdf
 *
 * @author João Rebelo
 */
public class RRSignPdfProperties {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * Type of signature SELF for self sign certificate CA for a CA certificate
     */
    public enum Type {
        //CA,
        SELF
    }

    /**
     * The possible level signature
     */
    public enum Level {
        CERTIFIED_NO_CHANGES_ALLOWED,
        CERTIFIED_FORM_FILLING,
        CERTIFIED_FORM_FILLING_AND_ANNOTATIONS
    }
    /**
     * The type of signature
     */
    private Type type;

    /**
     * The signaturemlevel
     */
    private Level level;

    /**
     *
     * The path to the java key store
     *
     */
    private String javaKeyStorePath;

    /**
     * Java key store javaKeyStorePassword
     */
    private String javaKeyStorePassword;

    /**
     * The certificate name/alias in the Java Key Store
     */
    private String certifricateName;

    /**
     * The certificate password in the Java Key Store
     */
    private String certificatePassword;

    /**
     * Reazon of the signature It will be written to the signature and is
     * optional
     */
    private String reazon = "";

    /**
     * Location (Ex: city) It will be written to the signature and is optional
     *
     */
    private String location = "";

    /**
     * Contact that it will be written to the signature and is optional
     */
    private String contact = "";

    /**
     * Set if the signature is visible or not
     */
    private Boolean visible = false;

    /**
     * Signature rectangle
     */
    @Null
    private Rectangle rectangle = null;

    /**
     * The properties to sign the pdf
     */
    public RRSignPdfProperties() {
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
        LOG.debug(() -> "instance created");
    }

    /**
     * Get the type of signature
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public Type getType() throws rebelo.reports.core.NullNotAllowedException {
        if (type == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "type");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "type", type));
        return type;
    }

    /**
     * Set the type of signature
     *
     * @param type
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setType(@NotNull Type type) throws NullNotAllowedException {
        if (type == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "type");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.type = type;
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "type", type));
    }

    /**
     * Get the signature level
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public Level getLevel() throws NullNotAllowedException {
        if (level == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "level");
            LOG.warn(msg);
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "level", level));
        return level;
    }

    /**
     * Set the signature level
     *
     * @param level
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setLevel(@NotNull Level level) throws NullNotAllowedException {
        if (level == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "level");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.level = level;
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "level", level));
    }

    /**
     * Get Java Key Strore path
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public String getJavaKeyStorePath() throws NullNotAllowedException {
        if (javaKeyStorePath == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "JavaKeyStorePath");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "JavaKeyStorePath", javaKeyStorePath));
        return javaKeyStorePath;
    }

    /**
     * Set the Java Key Store path
     *
     * @param path
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setJavaKeyStorePath(@NotNull String path) throws NullNotAllowedException {
        if (path == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "JavaKeyStorePath");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        this.javaKeyStorePath = path;
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "JavaKeyStorePath", path));
    }

    /**
     * Get java Key Store Password
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public String getJavaKeyStorePassword() throws NullNotAllowedException {
        if (javaKeyStorePassword == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "password");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "password", "!!! hidden !!!"));
        return javaKeyStorePassword;
    }

    /**
     * Set java Key Store Password
     *
     * @param javaKeyStorePassword
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setJavaKeyStorePassword(@NotNull String javaKeyStorePassword) throws rebelo.reports.core.NullNotAllowedException {
        if (javaKeyStorePassword == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "password");
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "password", "!!! hidden !!!"));
        this.javaKeyStorePassword = javaKeyStorePassword;
    }

    /**
     * Get certificate password
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public String getCertificatePassword() throws NullNotAllowedException {
        if (certificatePassword == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "certificate password");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "certificate password", "!!! hidden !!!"));
        return certificatePassword;
    }

    /**
     * Set certificate password
     *
     * @param certificatePassword
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setCertificatePassword(@NotNull String certificatePassword) throws rebelo.reports.core.NullNotAllowedException {
        if (certificatePassword == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "certificate password");
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "certificate password", "!!! hidden !!!"));
        this.certificatePassword = certificatePassword;
    }

    /**
     * Get certificate name / alias
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public String getCertificateName() throws NullNotAllowedException {
        if (certifricateName == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "certificate name");
            LOG.warn(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "certificate name", certifricateName));
        return certifricateName;
    }

    /**
     * Set certificate name / alias
     *
     * @param certifricateName
     * @throws NullNotAllowedException
     */
    public void setCertificateName(@NotNull String certifricateName) throws NullNotAllowedException {
        if (certifricateName == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "certificate name");
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "certificate name", certifricateName));
        this.certifricateName = certifricateName;
    }

    /**
     * Get signature reazon
     *
     * @return
     */
    @NotNull
    public String getReazon() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "reazon", reazon));
        return reazon;
    }

    /**
     * Set signature reazon
     *
     * @param reazon
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setReazon(@NotNull String reazon) throws NullNotAllowedException {
        if (reazon == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "reazon");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "reazon", reazon));
        this.reazon = reazon;
    }

    /**
     * Get signature's label location
     *
     * @return
     */
    @NotNull
    public String getLocation() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "location", location));
        return location;
    }

    /**
     * Set signature's location label (Ex: city)
     *
     * @param location
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setLocation(@NotNull String location) throws rebelo.reports.core.NullNotAllowedException {
        if (location == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "location");
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "location", location));
        this.location = location;
    }

    /**
     * Get contact siganture's label contact
     *
     * @return
     */
    @NotNull
    public String getContact() {
        LOG.trace(String.format(Message.GETTED_VALUE, "contact", contact));
        return contact;
    }

    /**
     * Set contact siganture's label contact
     *
     * @param contact
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setContact(@NotNull String contact) throws NullNotAllowedException {
        if (contact == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "contact");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "contact", contact));
        this.contact = contact;
    }

    /**
     * Get if signature is visible or not
     *
     * @return
     */
    @NotNull
    public Boolean isVisible() {
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "visible", visible.toString()));
        return visible;
    }

    /**
     * Set if signature is visible or not
     *
     * @param visible
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public void isVisible(@NotNull Boolean visible) throws NullNotAllowedException {
        if (visible == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "visible");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "visible", visible.toString()));
        this.visible = visible;
    }

    /**
     * Get signature's rectangle position
     *
     * @return
     */
    @Null
    public Rectangle getRectangle() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "rectangle",
                rectangle == null ? "null" : "Rectangle instance"));
        return rectangle;
    }

    /**
     * Set signature's rectangle position
     *
     * @param rectangle
     */
    @Null
    public void setRectangle(Rectangle rectangle) {
        LOG.trace(String.format(
                Message.SETTED_VALUE, "rectangle",
                rectangle == null ? "null" : "Rectangle instance"));
        this.rectangle = rectangle;
    }

    @Override
    @NotNull
    public String toString() {
        return "RRSignPdfProperties{" + "type=" + type + ", level=" + level + ", "
                + "javaKeyStorePath=" + javaKeyStorePath + ", javaKeyStorePassword=" 
                + javaKeyStorePassword + ", certifricateName=" + certifricateName 
                + ", reazon=" + reazon + ", location=" + location 
                + ", contact=" + contact + ", visible=" + visible 
                + ", rectangle=" + rectangle.toString() + '}';
    }

}
