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

import com.sun.istack.internal.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rebelo.reports.core.common.Message;

/**
 * The data source (JDBC connection) properties
 *
 * @author João Rebelo
 */
public class RRDsDatabase implements IRRDsProperties {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * The Driver name Ex: com.mysql.jdbc.Driver
     */
    private String driver;

    /**
     * Connection string Ex: jdbc:mysql:://127.0.0.1:3306/database
     */
    private String connString;

    /**
     * User to connect to data source
     */
    @Nullable
    private String user;

    /**
     * Password to connect data source
     */
    @Nullable
    private String password;

    public RRDsDatabase() {
        LOG.debug(() -> "instance created");
    }

    /**
     * Get the driver name to connect to the data source<br>
     *
     * Ex: com.mysql.jdbc.Driver
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public String getDriver() throws rebelo.reports.core.NullNotAllowedException {
        if (driver == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "driver");
            LOG.warn(msg);
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "driver", driver));
        return driver;
    }

    /**
     * Set the driver name<br>
     * Ex: com.mysql.jdbc.Driver
     *
     * @param driver
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setDriver(String driver) throws rebelo.reports.core.NullNotAllowedException {
        if (driver == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "driver");
            LOG.error(msg);
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        this.driver = driver;
    }

    /**
     * Get connection string
     *
     * @return
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public String getConnString() throws rebelo.reports.core.NullNotAllowedException {
        if (connString == null) {
            String msg = String.format(Message.GET_NULL_ERROR, "connection string");
            LOG.warn(msg);
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "connection string", connString));
        return connString;
    }

    /**
     * Set connection string Ex: jdbc:mysql:://127.0.0.1:3306/database
     *
     *
     * @param connString
     * @throws rebelo.reports.core.NullNotAllowedException
     */
    public void setConnString(String connString) throws rebelo.reports.core.NullNotAllowedException {
        if (connString == null) {
            String msg = String.format(Message.SET_NULL_ERROR, "connection string");
            LOG.warn(msg);
            throw new rebelo.reports.core.NullNotAllowedException(msg);
        }
        LOG.trace(() -> String.format(Message.GETTED_VALUE, "connection string", connString));
        this.connString = connString;
    }

    /**
     * Get user
     *
     * @return
     */
    @Nullable
    public String getUser() {
        LOG.trace(() -> String.format(
                Message.GETTED_VALUE, "user", user == null ? "null" : user)
        );
        return user;
    }

    /**
     * Set user of data source connection
     *
     * @param user
     */
    public void setUser(@Nullable String user) {
        LOG.trace(() -> String.format(
                Message.SETTED_VALUE, "user", user == null ? "null" : user)
        );
        this.user = user;
    }

    /**
     * Get password of data source connection
     *
     * @return
     */
    @Nullable
    public String getPassword() {
        LOG.trace(() -> String.format(
                Message.GETTED_VALUE, "password", user == null ? "null" : "!!! hidden !!!")
        );
        return password;
    }

    /**
     * Set password of data source connection
     *
     * @param password
     */
    public void setPassword(@Nullable String password) {
        LOG.trace(() -> String.format(
                Message.SETTED_VALUE, "password", user == null ? "null" : "!!! hidden !!!")
        );
        this.password = password;
    }

    /**
     * Get the data source in JasperFillManager
     * 
     * @return
     * @throws rebelo.reports.core.datasource.DataSourceException
     */
    @Override
    public Connection getDataSource() throws DataSourceException{
        try{
        LOG.debug("Load driver class");
        Class.forName(this.getDriver()).newInstance();

        LOG.debug("Create db connection");
        Connection conn = DriverManager.getConnection(
                this.getConnString(),
                this.getUser(),
                this.getPassword()
        );
        return conn;
        }catch(@SuppressWarnings("UseSpecificCatch") Exception e){
            throw new DataSourceException(e);
        }
    }

}
