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

import rebelo.reports.core.compiler.Compiler;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author João Rebelo
 */
public class CompilerTest {
    
    public CompilerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CompileJrxmlToJasper method, of class Compiler.
     */
    @Test
    public void testCompileJrxmlToJasperAndJasperToJrxml() throws Exception {
        System.out.println("CompileJrxmlToJasperAndJasperToJrxml");
        
        ClassLoader classLoader = getClass().getClassLoader();
        File resDir = new File(classLoader.getResource("./").getFile());
        String resDirPath = resDir.getAbsolutePath()+ "/compiler";
        File jrxmlFile = new File(resDirPath + "/sakila.jrxml");
        File compiledFile = new File(resDirPath + "/compiled.jasper");
        File revertedFile = new File(resDirPath + "/reverted.jrxml");
        
        if(compiledFile.exists()){
            compiledFile.delete();
        }
        
        if(revertedFile.exists()){
            revertedFile.delete();
        }
        
        Compiler compiler = new Compiler();
        
        compiler.CompileJrxmlToJasper(jrxmlFile, compiledFile);
        
        if(compiledFile.exists() == false){
            fail("Method CompileJrxmlToJasper failed");
        }
        
        compiler.RevertJasperToJrxml(compiledFile, revertedFile);
        
        if(revertedFile.exists() == false){
            fail("Method RevertJasperToJrxml failed");
        }
    }
    
}
