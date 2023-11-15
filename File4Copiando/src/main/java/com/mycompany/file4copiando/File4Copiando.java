/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file4copiando;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class File4Copiando {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
              System.out.println("escriba la ruta del fichero");
              String ruta1=input.next();
              File archivo1 = new File(ruta1);
              System.out.println("ahora escriba una ruta de destino");
              String ruta2=input.next();
              try{
                  InputStream in = new FileInputStream(ruta1);
                  OutputStream out = new FileOutputStream(ruta2);
                  byte[] bit = new byte[1024];
                  int linea;
                  while ((linea=in.read(bit))>0){
                      out.write(bit,0,linea);
                  }
                  in.close();
                  out.close();
                  
              } catch(IOException ioe){
                  System.out.println(ioe);
              }
              
              
              
    }
}
