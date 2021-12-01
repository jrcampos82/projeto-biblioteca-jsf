package br.edu.ifms.biblioteca.bean;


import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.biblioteca.dao.LivroDAO;
import br.edu.ifms.biblioteca.model.Livro;

@Named
@SessionScoped

public class LivroBean implements Serializable {
    @Inject
    private Livro livro = new Livro();

    
    private LivroDAO ldao;

    private List<Livro> livros;

    

    @PostConstruct
    public void init() {
        livros = ldao.selecionarTodos();
    }

    public Livro getLivro(){
        return this.livro;
    }

    public void setLivro(Livro l){
        this.livro = l;
    }


    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void cadastrar(){
        try {
            // System.out.println(livro.toString());  
            
            // validar o campos
            String validar = validar(livro);
            if(!validar.equals("ok")){
                System.out.println(validar);
                showError("Erro na validação", validar);
                // return "erro";
            } else {
            // inserir no bd
                ldao.salvar(livro);
                showInfo("Sucesso!", "Livro cadastrado com sucesso!");
            }
            
        } catch (Exception e) {
            System.out.println("Um erro ocorreu no cadastro!");
            
        }        
        // return "OK";
    }

    private String validar(Livro livro) {
        if(
            (livro.getNome().equals("")) || 
            (livro.getEditora().equals("")) || 
            (livro.getAutor().equals("")) ||
            (livro.getCodigoLivro().equals("")) ||
            (livro.getAno() == 0)
        ){
            
            return "Preencha os campos corretamente!";
        }
        
        
        return "ok";
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
