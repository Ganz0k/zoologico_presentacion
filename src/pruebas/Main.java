/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas;

import entidades.Animal;
import factory.FabricaNegocios;
import interfaces.INegocio;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author luisg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        INegocio negocio = FabricaNegocios.crearFNegocio();
        List<Animal> listaAnimales = negocio.consultarAnimalesEspecie(new ObjectId("6272bf111e4f6f40b6b91730"));
        
        for(Animal a : listaAnimales){
            System.out.println(a);
        }
    }
    
}
