# Rebelo Reports

Rebelo Reports is a middleware to use the Jasper Reports Framework in the cases that you can not use the Jasper Reports embedded in you software,in cases that you software is not java or there are incompatibility of licences.

The Rebelo reports Core is the the core of Rebelo Reports is to be use with the other modules of Rebelo Reports that allow the integration of your technology with java and Rebelo Reports.

# Rebelo Reports Core

Rebelo Reports Core is the core of the Rebelo Reports is not intended to be used alonebut with other modules (That at the time are in develop)

## Tested
    The software was teste with Windows® 10 Pro 1809
    openjdk version "1.8.0_212"
    OpenJDK Runtime Environment (build 1.8.0_212-b03)
    Eclipse® OpenJ9® VM (build openj9-0.14.0, JRE 1.8.0 Windows® 10 amd64-64-Bit Compressed References 20190417_339 (JIT enabled, AOT enabled)
    OpenJ9   - bad1d4d06
    OMR      - 4a4278e6
    JCL      - 5590c4f818 based on jdk8u212-b03)

## TODO
    Parse from json
    Linux tests
    

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
