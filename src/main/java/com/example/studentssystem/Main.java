package com.example.studentssystem;

public class Main {
    public static void main(String[] args) {
        GestorAcademico gestor = new GestorAcademico();

        Estudiante est1 = new Estudiante(1, "Juan", "Perez", "1990-01-01", Estudiante.Estado.MATRICULADO);
        Estudiante est2 = new Estudiante(2, "Maria", "Gomez", "1992-02-02", Estudiante.Estado.MATRICULADO);

        Curso curso1 = new Curso(1, "Matemáticas", "Curso de matemáticas básicas", 3, "1.0");
        Curso curso2 = new Curso(2, "Historia", "Curso de historia universal", 4, "1.0");

        gestor.matricularEstudiante(est1);
        gestor.matricularEstudiante(est2);

        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        try {
            gestor.inscribirEstudianteCurso(est1, 1);
            gestor.inscribirEstudianteCurso(est2, 2);
        } catch (EstudianteYaInscritoException e) {
            System.out.println(e.getMessage());
        }

        try {
            gestor.desinscribirEstudianteCurso(1, 1);
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }
}
