/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file4lecturabytesarrays;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class File4LecturaBytesArrays {

    public static void main(String[] args) {
       File prueba = new File("..\\File3EscrituraBytesArrays\\prueba.txt");
       System.out.println(prueba.getAbsolutePath());
        String st;
       try{
           FileInputStream fis = new FileInputStream(prueba);
           BufferedReader br =  new BufferedReader(new InputStreamReader(fis));
           String linea;
          
            boolean impar=true;
           while ((linea=br.readLine())!=null){
               
                String[] partes = linea.split(" - ");
               
                    st = partes[0];
                   if(impar){
                    System.out.print("Nombre: " + st + " " );
                    impar=false;
                   }
                   else{
                       System.out.println("Numero: " + st );
                       impar=true;
                   }
           
           }
           fis.close();
           br.close();
       } catch (FileNotFoundException ex) {
                System.out.println(ex);
               } catch (IOException ioe){
           System.out.println(ioe);
           
           
       }
    
    }}
