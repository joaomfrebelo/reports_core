# Rebelo Reports

Rebelo Reports is a middleware to use the Jasper Reports Framework in the cases that you can not use the Jasper Reports embedded in you software,in cases that you software is not java or there are incompatibility of licences.

The Rebelo reports Core is the the core of Rebelo Reports is to be use with the other modules of Rebelo Reports that allow the integration of your technology with java and Rebelo Reports.

# Rebelo Reports Core

Rebelo Reports Core is the core of the Rebelo Reports is not intended to be used alonebut with other modules (That at the time are in develop)

## TODO
    Parse from json
    Full Linux unit tests
    

## To include in your project

Use the Jitpack

https://jitpack.io/#joaomfrebelo/reports_core

For maven prjects

Include in the repositories section of your pom file:

	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>

In the dependecies of your pom file

    <dependency>
	    <groupId>com.github.joaomfrebelo</groupId>
	    <artifactId>reports_core</artifactId>
	    <version>Tag of version</version>
	</dependency>

# Existent Modules

## Rebelo Report Cli  
To run Rebelo Reports and generate Jasper reports from any non java language  
https://github.com/joaomfrebelo/reports_cli

## Rebelo Reports for PHP  
To run Rebelo Reports and generate Jasper reports from PHP  
https://github.com/joaomfrebelo/reports_4_php
  
## Features  
### Export to:  
- PDF  
- Digital Sign PDF
- Csv
- Docx
- Html
- Json
- Ods  
- Odt  
- Pptx  
- Rtf  
- Text  
- Xls  
- Xlsx  
- Xml  
- To printer

### Others
- Export multiple reports as one with the same exporter.
- Export copies of the repoprt at onces with a parametrs of the copy index  
- Pass parameters to the report well typed, parameters types:  
    - const P_STRING     = "string";
    - const P_BOOL       = "bool";
    - const P_BOOLEAN    = "boolean";
    - const P_DOUBLE     = "double";
    - const P_FLOAT      = "float";
    - const P_INTEGER    = "integer";
    - const P_LONG       = "long";
    - const P_SHORT      = "short";
    - const P_BIGDECIMAL = "bigdecimal";
    - const P_DATE       = "date";
    - const P_TIME       = "time";
    - const P_SQL_TIME   = "sqltime";
    - const P_SQL_DATE   = "sqldate";
    - const P_TIMESTAMP  = "timestamp";


## License

Copyright (C) 2019  João M F Rebelo

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.
 
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

## License issues

The use of Rebelo Reports with non AGPL software and with Commercial software:

Because  Rebelo Reports uses AGPL license it’s important to keep the AGPL license file around. In my opinion your software that is going to invoke Rebelo Reports doesn’t have to be licensed under AGPL as the Rebelo Reports if is being  used via its modules as an external executable Midleware and not as part of your project and your source code, compile, install and use as two separated software.

How ever this only reflects my opinion about the compatibilities of licences and the use of the software under AGPL and other licences included commercial ones as described above, is your responsibility to verify if the legality of the compatibility and uses.
