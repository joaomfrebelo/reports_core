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
package rebelo.reports.core.common;

/**
 * Class the contain common messages
 * @author João Rebelo
 */
public class Message {
    
    /**
     * To be used whene the geted value is null and not allowed
     */
    public static final String GET_NULL_ERROR = "Getting '%1$s' while null";
    
    /**
     * To be used whene the seted value is null and not allowed
     */
    public static final String SET_NULL_ERROR = "Setting '%1$s' while null";
    
    /**
     * To be used for getted value
     */
    public static final String GETTED_VALUE = "'%1$s' getted is '%2$s'";
    
    /**
     * To be used for getted value
     */
    public static final String SETTED_VALUE = "'%1$s' setted to '%2$s'";
}
