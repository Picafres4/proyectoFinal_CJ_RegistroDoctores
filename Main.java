import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Paciente p = new Paciente();

        p.setIdPaciente("00012");
        p.setNombre("Ana");
        p.setApPaterno("González");
        p.setApMaterno("Castillo");
        p.setNss("123456");
        p.setOperacionesAnteriores("Ninguna");
        p.setAlergias("Ninguna");
        p.setPadecimientos("Ninguno");

        Date fecha =new Date();
        p.setFechaNacimiento(fecha);
        lista.add(p);

        try{
            // escribir en archivo
            FileOutputStream escribir = new FileOutputStream("athainarada\\listaPacientes.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(lista);
            miStream.close();


            //ArchivoCRUD.leerArchivo();
            //LEER lista desde archivo

            FileInputStream leer = new FileInputStream("athainarada\\listaPacientes.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();

            ArrayList<Paciente> otraLista = (ArrayList<Paciente>)o;
            miStream2.close();


        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado.");
        }catch(IOException e){
            System.out.println("Error de E/S");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println("Error al leer lista de clase Paciente");
        }
    }
}