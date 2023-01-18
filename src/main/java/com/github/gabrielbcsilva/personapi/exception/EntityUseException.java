package com.github.gabrielbcsilva.personapi.exception;

public class EntityUseException   extends RuntimeException{
    private static final long serialVersionUID = 1L;

	public EntityUseException(String mensagem) {
		super(mensagem);
	}
}
