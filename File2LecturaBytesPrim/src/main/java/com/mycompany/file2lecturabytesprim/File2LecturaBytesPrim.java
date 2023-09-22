/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file2lecturabytesprim;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Antonio
 */
public class File2LecturaBytesPrim {

    public static void main(String[] args) {
        File entrada = new File("..\\File1EscrituraBytesPrim\\prueba.txt");      
    
        
        try{
            FileInputStream fis = new FileInputStream(entrada); 
            int i;
            i=fis.read();
            while(i!=-1){
                System.out.print((char)i);
                
                
            }
            fis.close();
            
            
            
        }
        catch (IOException ioe){
            
           System.out.println(ioe);
        }
        
    
    
    
    
    }
}
