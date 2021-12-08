package br.edu.ifms.biblioteca.util;

import javax.faces.application.FacesMessage;

public interface Mensagens {

    public void addMessage(FacesMessage.Severity severity, String summary, String detail);

    public void showInfo(String titulo, String msg);

    public void showWarn(String titulo, String msg);

    public void showError(String titulo, String msg);
    
}
