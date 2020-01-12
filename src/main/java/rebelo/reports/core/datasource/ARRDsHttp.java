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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.config.Configurator;
import rebelo.reports.core.NullNotAllowedException;
import rebelo.reports.core.Report;
import static rebelo.reports.core.Report.logLevel;
import rebelo.reports.core.common.Message;

/**
 *
 * @author João Rebelo
 */
public abstract class ARRDsHttp extends ARRDsJRDataSource {

    /**
     * Connection type
     */
    public enum Type {
        POST,
        GET
    }

    /**
     * Encoded to be used
     */
    protected String encode = "UTF-8";

    /**
     * Url fo json server
     */
    protected URL url;

    /**
     * Parameter to be passed Post in the request
     */
    protected final HashMap<String, String> parameters = new HashMap<>();

    /**
     * Connection type
     */
    protected Type type;

    public ARRDsHttp() {
        if(null != Report.logLevel){
            Configurator.setLevel(getClass().getName(), Report.logLevel);
        }
    }

    /**
     * Set the connection type GET|POST
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public Type getType() throws NullNotAllowedException {
         if (type == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "getType");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "type", type.toString()));
        return type;
    }

    /**
     * Get the connection type GET|POST
     *
     * @param type
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setType(@NotNull Type type) throws NullNotAllowedException {
        if (type == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setType");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "type", type.toString()));
        this.type = type;
    }

    /**
     *
     * Get the json service URL
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public URL getUrl() throws NullNotAllowedException {
        if (url == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "getUrl");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "getUrl", url.toString()));
        return url;
    }

    /**
     * Set connection common
     *
     * @param conn
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    protected void setGeneric(@NotNull HttpURLConnection conn) throws NullNotAllowedException {
        /**
         * Is necessary to pass the Connection con and not access direct to the
         * propertie because this propertie can be override in the extended
         * class *
         */
        if (conn == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setGeneric");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace("Seting setDoOutput and setDoInout");
        conn.setDoOutput(true);
        conn.setDoInput(true);
    }

    /**
     * Set the json service URL
     *
     * @param url
     * @throws rebelo.reports.core.NullNotAllowedException
     * @throws rebelo.reports.core.datasource.DataSourceException
     */
    public void setUrl(@NotNull URL url) throws NullNotAllowedException, DataSourceException {
        if (url == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setUrl");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        String className = this.getClass().getName().toLowerCase();
        String proto = url.getProtocol();

        if (className.contains("https")) {
            if (proto.equalsIgnoreCase("https") == false) {
                String msg = String.format("protocol muts be https but '%s' was passed", proto);
                LOG.debug(msg);
                throw new DataSourceException(msg);
            }
        } else if (className.contains("http") && proto.equalsIgnoreCase("http") == false) {
            String msg = String.format("protocol muts be http but '%s' was passed", proto);
            LOG.debug(msg);
            throw new DataSourceException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "setUrl", url.toString()));
        this.url = url;
    }

    /**
     * Get the Http cpnenction
     *
     * @return
     * @throws rebelo.reports.core.datasource.DataSourceException
     * @throws java.io.IOException
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public HttpURLConnection getUrlConnection() throws DataSourceException, IOException, NullNotAllowedException {
        if (url == null) {
            throw new DataSourceException("Url for connection is not initialized or seted");
        }
        
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
        setGeneric(conn);
        
        LOG.trace("Geting Url Connection (getUrlConnection)");
        return conn;
    }

    /**
     *
     * Add a POST parameter to the stack
     *
     * @param key
     * @param value
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void addParameter(@NotNull String key, @NotNull String value) throws NullNotAllowedException {
        if (key == null || value == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setUrl");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format("Setting parameter to key='%s', value='%s'", key, value));
        parameters.put(key, value);
    }

    /**
     * Set the encoding
     *
     * @return
     */
    @NotNull
    public String getEncode() {
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "getEncode", encode));
        return encode;
    }

    /**
     * Get encoding
     *
     * @param encode
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setEncode(@NotNull String encode) throws NullNotAllowedException {
        if (encode == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "setEncode");
            LOG.error(msg);
            throw new NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.SETTED_VALUE, "setEncode", encode));
        this.encode = encode;
    }

    /**
     *
     * Get the input stream to initialize JRDataSource
     *
     * @return
     * @throws DataSourceException
     * @throws IOException
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    @NotNull
    public InputStream getInputStream() throws DataSourceException, IOException, NullNotAllowedException {
        LOG.debug("Start getting connection input stream");
        LOG.trace("Setting request mode");
        getUrlConnection().setRequestMethod(getType().toString());
        if (getType().equals(Type.POST)) {
            LOG.trace("Setting parameters in the POST message body");
            if (parameters.size() > 0) {
                StringBuilder result = new StringBuilder();
                for (Map.Entry<String, String> param : parameters.entrySet()) {
                    result.append(URLEncoder.encode(param.getKey(), getEncode()));
                    result.append("=");
                    result.append(URLEncoder.encode(param.getValue(), getEncode()));
                    result.append("&");
                }
                OutputStream os;
                BufferedWriter writer;
                os = getUrlConnection().getOutputStream();
                writer = new BufferedWriter(new OutputStreamWriter(os, getEncode()));
                writer.write(result.toString());
                writer.flush();
                writer.close();
                os.close();
            }
        }
        LOG.debug("Start connetion");
        getUrlConnection().connect();
        LOG.trace("Return Input Stream");
        return getUrlConnection().getInputStream();
    }

}
