package br.edu.ifms.biblioteca.bean;


import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.biblioteca.dao.LivroDAO;
import br.edu.ifms.biblioteca.model.Livro;

@Named
@RequestScoped
public class LivroBean implements Serializable {
    @Inject
    Livro livro;

    @Inject
    LivroDAO ldao;

    String msg = "cadastrado";

    public Livro getLivro(){
        return this.livro;
    }

    public void setLivro(Livro l){
        this.livro = l;
    }

    public String getMsg(){
        return this.msg;
    }

    public String cadastrar(){
        try {
            System.out.println(livro.toString());  
            addMessage("Livro cadastrado!");
            // validar o campos
            String validar = validar(livro);
            if(!validar.equals("ok")){
                System.out.println(validar);
                return "erro";
            }
            // inserir no bd
            ldao.salvar(livro);
            return "Cadastrado com sucesso!";
            
        } catch (Exception e) {
            System.out.println("Um erro ocorreu no cadastro!");
            addMessage(e.getMessage());
        }        
        return "OK";
    }

    private String validar(Livro livro) {
        if(
            (livro.getNome().equals("")) || 
            (livro.getEditora().equals("")) || 
            (livro.getAutor().equals("")) ||
            (livro.getCodigoLivro().equals("")) ||
            (livro.getAno() == 0)
        )
            return "Preencha os campos corretamente!";
        
        
        
        return "ok";
    }

    private void addMessage(String texto){
        getCurrentInstance().addMessage(null, new FacesMessage(texto));
    }
    
}
