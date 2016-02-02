/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrices;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author galvez
 */
public class Matriz {
    private int[][]datos;
    private Random rnd = new Random();
    
    public Matriz(int filas, int columnas, boolean inicializarAleatorio){
        datos = new int[columnas][];
        for(int i=0; i<columnas; i++){
            datos[i] = new int[filas];
            if (inicializarAleatorio)
                for(int j=0; j<filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }
    public Matriz(Dimension d, boolean inicializarAleatorio){
        this(d.height, d.width, inicializarAleatorio);
    }
    
    public Dimension getDimension(){
        return new Dimension(datos.length, datos[0].length);
    }
    
    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        if(! a.getDimension().equals(b.getDimension())) throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");        
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) { 
            for (i = 0; i < columnasA; i++) { 
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j]; 
            } 
        } 
        return matrizResultante; 
    } 

    public static Matriz multiplicarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles { 
        int i, j, k, filasA, columnasA, filasB, columnasB; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        filasB = b.getDimension().height; 
        columnasB = b.getDimension().width; 
        if((filasA!=columnasB) || (filasB!=columnasA)) 
		throw new DimensionesIncompatibles("El producto de matrices requiere matrices de dimensiones inversas");        
        Matriz matrizResultante = new Matriz(filasA, columnasB, false);
        for (i = 0; i < filasA; i++) { 
            for (j = 0; j < columnasB; j++) { 
		int aux = 0;
		for (k = 0; k < columnasA; k++) {  
                    aux += a.datos[i][k] * b.datos[k][j]; 
		}
		matrizResultante.datos[i][j] = aux;
            } 
        } 
        return matrizResultante; 
    }
     public static Matriz invertirMatriz(Matriz a){ 
        int i, j, filasA, columnasA; 
        filasA = a.getDimension().height; 
        columnasA = a.getDimension().width; 
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (i = 0; i < filasA; i++) { 
            for (j = 0; j < columnasA; j++) { 
		matrizResultante.datos[j][i] = a.datos[i][j];
            } 
        } 
        return matrizResultante; 
    } 

    
    @Override
    public String toString(){
        String ret = "";
        ret += "[\n";
        for (int i = 0; i < getDimension().width; i++) {
            ret += "(";
            for (int j = 0; j < getDimension().height; j++) {  
                ret += String.format("%3d", datos[i][j]); 
                if (j != getDimension().height - 1) ret += ", ";
            } 
            ret += ")";
            if (i != getDimension().width - 1) ret += ",";
            ret += "\n";
        } 
        ret += "]\n";
        return ret;
    }
}
