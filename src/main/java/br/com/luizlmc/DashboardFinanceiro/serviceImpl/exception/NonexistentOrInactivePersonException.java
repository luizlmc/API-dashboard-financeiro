package br.com.luizlmc.DashboardFinanceiro.serviceImpl.exception;

public class NonexistentOrInactivePersonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NonexistentOrInactivePersonException(String message){
        super(message);
    }

}
