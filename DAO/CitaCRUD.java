package DAO;

import entidades.Cita;
import entidades.Doctor;
import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

import static DAO.PacientesCRUD.leerListaPacientes;

public class CitaCRUD {
// leer citas
    public static ArrayList<Cita> leerListaCitas() {
        Object o3;
        try {
            FileInputStream leer =
                    new FileInputStream("athainarada\\listaCitas.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            o3 = miStream2.readObject();
            ArrayList<Cita> listaCitas = (ArrayList<Cita>) o3;
            return listaCitas;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("El archivo no existe: " + e.getMessage(), e);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
        }


    }

    // escribir en citas
    public static void escribirCitas(ArrayList<Cita> listaCitas) {
        try {
            FileOutputStream escribir =
                    new FileOutputStream("athainarada\\listaCitas.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);

            miStream.writeObject(listaCitas);

        } catch (IOException e) {
            throw new RuntimeException("Error al escribir en el archivo: " + e.getMessage(), e);
        }
    }
//insertar cita
        public void insertarCita(Cita c) {
            ArrayList<Cita> listaCitas = leerListaCitas();
            listaCitas.add(c);
            escribirCitas(listaCitas);
        }
    }

