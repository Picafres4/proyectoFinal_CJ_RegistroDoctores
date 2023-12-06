package DAO;

import entidades.Doctor;

import java.io.*;
import java.util.ArrayList;

public class DoctoresCRUD {

    //método para leer la lista de Doctores que no recibe ningún parámetro pero regresa el array list
    public static ArrayList<Doctor> leerListaDoctores() {
        Object o2;
        try {
            FileInputStream leer =
                    new FileInputStream("athainarada\\listaDoctores.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            o2 = miStream2.readObject();
            return (ArrayList<Doctor>) o2;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("El archivo no existe: " + e.getMessage(), e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
        }
    }

    // método que recibe el array list que fue resultado del metodo leerListaDoctores()
    public static void escribirDoctores(ArrayList<Doctor> listaDoctores) {
        try {
            FileOutputStream escribir =
                    new FileOutputStream("athainarada\\listaDoctores.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);

            miStream.writeObject(listaDoctores);

        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo: " + e.getMessage(), e);
        }
    }

    // método para insertar
    public void insertarDoctor(Doctor d) {
        ArrayList<Doctor> listaDoctores = leerListaDoctores();
        listaDoctores.add(d);
        escribirDoctores(listaDoctores);
    }

    // regresará un objeto de tipo Doctor si lo buscamos por su ID
    public Doctor buscarDoctorPorId(String idMetodo) {
        ArrayList<Doctor> listaDoctores = leerListaDoctores();
        listaDoctores.remove(0);


        for (Doctor d: listaDoctores) {
            if (d.getIdDoc().equals(idMetodo)) {
                System.out.println(d);
                return d;
            }
        }
        return null;

    }
}



