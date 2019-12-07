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

import java.util.Arrays;

/**
 *
 * @author João Rebelo
 */
public class Util {

    /**
     *
     * Parse the strin to boolea
     *
     * @param bool
     * @return
     * @throws Exception
     */
    public static final boolean parseBool(String bool) throws Exception {
        if(bool == null){
            throw new Exception("A null value is not parseble to boolean");
        }
        switch (bool.toLowerCase()) {
            case "0":
            case "off":
            case "false":
            case "no":
                return false;
            case "1":
            case "on":
            case "true":
            case "yes":
                return true;
            default:
                throw new Exception("Value '" + bool + "' is not parseble to boolean");
        }
    }
    
    /**
     * 
     * Convert enum to String[]
     * @see https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string/13783744#13783744
     * @param e
     * @return 
     */
    public static String[] enumToArray(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
    
}
