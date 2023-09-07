package com.perficient.controllers;

public class CursoNotFoundException extends RuntimeException {

  public CursoNotFoundException(Long id) {
    super("Curso con ID " + id + " no encontrado");
  }
}

