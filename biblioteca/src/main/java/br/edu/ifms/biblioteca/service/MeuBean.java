package br.edu.ifms.biblioteca.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value="livro")
@RequestScoped
public class MeuBean {
	
	private String msg = "Boa noite JSF";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
