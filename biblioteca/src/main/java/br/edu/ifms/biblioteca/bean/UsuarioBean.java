package br.edu.ifms.biblioteca.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifms.biblioteca.dao.UserDAO;
import br.edu.ifms.biblioteca.model.Usuario;

import static javax.faces.context.FacesContext.getCurrentInstance;

public class UsuarioBean implements Serializable{

    @Inject
    Usuario usuario;

    @Inject
    UserDAO udao;


    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UserDAO getUdao() {
        return this.udao;
    }

    public void setUdao(UserDAO udao) {
        this.udao = udao;
    }

    public void cadastrar(){}

    public void atualizar(){}

    public void deletar(){}


    public String login(String user, String pwd){
        if(user.equals("beto") && pwd.equals("123"))
            return "dashboard";
        else {
            // apresentar msg de erro no login glow
            showError("Erro no Login", "Usuário ou senha incorretos");
            return "";
        }
    }







    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_INFO, titulo, msg);
    }

    public void showWarn(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_WARN, titulo, msg);
    }

    public void showError(String titulo, String msg) {
        addMessage(FacesMessage.SEVERITY_ERROR, titulo, msg);
    }

    
}
