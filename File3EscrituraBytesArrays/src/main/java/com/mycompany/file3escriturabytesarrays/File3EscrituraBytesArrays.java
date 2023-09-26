/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.file3escriturabytesarrays;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Antonio
 */
public class File3EscrituraBytesArrays {

    public static void main(String[] args)  {
        String[] arraypersonas = new String[3];
        arraypersonas[0]="pedro";
        arraypersonas[1]="menganito";
        arraypersonas[2]="fulanito";
        String[] arraytf = new String[3];
          arraytf[0]="926666666";
        arraytf[1]="620714814";
        arraytf[2]="666666666";
        File prueba = new File(".\\prueba.dat");
    
        byte[] bt;
        try{
                if(!prueba.exists()){
            prueba.createNewFile();
            
        }
            FileOutputStream fos = new FileOutputStream(prueba);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i=0;i<arraypersonas.length;i++){
                
                bt = arraypersonas[i].getBytes();
                dos.write(bt);
                fos.write(System.lineSeparator().getBytes());
                
                bt = arraytf[i].getBytes();
                fos.write(bt);
                 fos.write(System.lineSeparator().getBytes());
            }
            fos.close();
            dos.close();
        }
        catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}
