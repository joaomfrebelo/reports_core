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
 *
 * @author João Rebelo
 */
public class StringUtils extends  org.apache.commons.lang3.StringUtils{
    
    /**
     * 
     * If the string is empty or null will return true
     * 
     * @param string
     * @return 
     */
    public static boolean isEmptyOrNull(String string){
        if(string == null){
            return true;
        }
        return string.isEmpty();
    }
    
    /**
     * If the string is empty or null will return false
     * 
     * @param string
     * @return 
     */
    public static boolean isNotEmptyOrNull(String string){
        return !isEmptyOrNull(string);
    }
    
    
}
