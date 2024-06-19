package com.example.studentssystem;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (!inscripciones.containsKey(idCurso)) {
            inscripciones.put(idCurso, new ArrayList<>());
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en el curso.");
        } else {
            inscritos.add(estudiante);
        }
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        if (inscripciones.containsKey(idCurso)) {
            ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
            Estudiante estudiante = estudiantes.stream()
                    .filter(e -> e.getId() == idEstudiante)
                    .findFirst()
                    .orElse(null);
            if (estudiante != null && inscritos.contains(estudiante)) {
                inscritos.remove(estudiante);
            } else {
                throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso.");
            }
        } else {
            throw new EstudianteNoInscritoEnCursoException("El curso con el ID especificado no existe.");
        }
    }
}
