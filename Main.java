import entidades.Cita;
import entidades.Paciente;
import entidades.Doctor;

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

        ArrayList<Doctor> listaDoctores = new ArrayList<Doctor>();
        Doctor d = new Doctor();


        d.setIdDoc("00015");
        d.setNombreDoc("mercedes");
        d.setApPaternoDoc("iglesias");
        d.setApMaternoDoc("Castillo");
        d.setCPDoc("123456");

        listaDoctores.add(d);


        ArrayList<Cita> listaCitas= new ArrayList<Cita>();
        Cita c = new Cita();

        c.setObservaciones("m");
        c.setHora("15");
        c.setMinutos("30");
        c.setIdDoctor("00015");

        listaCitas.add(c);

        try{
            // escribir en archivo
            FileOutputStream escribir = new FileOutputStream("athainarada\\listaCitas.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(listaCitas);
            miStream.close();

            //LEER lista desde archivo

            FileInputStream leer = new FileInputStream("athainarada\\listaCitas.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o3 = miStream2.readObject();

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