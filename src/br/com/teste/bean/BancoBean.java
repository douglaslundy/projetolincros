package br.com.teste.bean;

import javax.faces.bean.ManagedBean;

import br.com.teste.dao.PopulaBanco;

@ManagedBean
public class BancoBean {
	
	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void construir() {
		System.out.println("Criando banco de dados");
		new PopulaBanco();
		setMensagem("Banco criado com sucesso!!!");
	}
}
