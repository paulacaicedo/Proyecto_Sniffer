package com.validacion;

public enum Errores {
	
    ERROR_LOGIN(1),
    ERROR_EXCEPCION_NUMERO(2),
	ERROR_CARACTER_VACIO(3);

    private int id;

    private Errores(int id)
    {
        this.id = id;
    }

    public int getId() { return id; }

}
