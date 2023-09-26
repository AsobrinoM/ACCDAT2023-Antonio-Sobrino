/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file4lecturabytesarrays;

import java.io.BufferedReader;
import java.io.DataInputStream;
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

    public static void main(String[] args) throws IOException {
       File prueba = new File("..\\File3EscrituraBytesArrays\\prueba.dat");
       
       System.out.println(prueba.getAbsolutePath());
     
       try{
           FileInputStream fis = new FileInputStream(prueba);
           DataInputStream dis = new DataInputStream(fis);
             String st;
             int tlf;
          
           
           while (dis.available()>0){
               
              st=dis.readUTF();
              tlf=dis.readInt();
                    System.out.print("Nombre: " + st + " " );
                   System.out.println("Numero: " + tlf );
                 
                   
           
           }
           fis.close();
           dis.close();
       } catch (FileNotFoundException ex) {
                System.out.println(ex);
               } catch (IOException ioe){
           System.out.println(ioe);
           
           
       }
    
    }}
