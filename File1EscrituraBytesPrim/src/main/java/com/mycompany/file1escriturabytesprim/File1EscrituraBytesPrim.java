/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file1escriturabytesprim;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class File1EscrituraBytesPrim {

    public static void main(String[] args) throws FileNotFoundException {
       File prueba = new File(".\\prueba.txt");
      
           try {
                FileOutputStream fos = new FileOutputStream(prueba); 
                  DataOutputStream dos= new DataOutputStream(fos);
                        boolean sb=true;
                        int bt= 32;
                        String bytes="rarete no?";
                        int st=2;
                        String Chars ="Caracteres";
                        int caracter=25;
                        int enter=30;
                        long longo=23;
                        float fl = 3;
                        double db=6.32;
                        String utf ="u te efe";
                        dos.writeBoolean(sb);
                        dos.writeByte(bt);
                        dos.writeBytes(bytes);
                        dos.writeShort(st);
                        dos.writeChars(Chars);
                        dos.writeInt(enter);
                        dos.writeLong(longo);
                        dos.writeFloat(fl);
                        dos.writeDouble(db);
                        dos.writeUTF(utf);
                        fos.close();
                        dos.close();
                      
                       
                       
             
               
              
                   
               
           }catch (IOException ioe){
                   System.out.print(ioe);}
   
           }}
    

