package br.com.teste.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class PrimosBean {

	private String lista = "";
	public String getLista() {
		return lista;
	}

	public void setLista(String lista) {
		this.lista += lista;
	}

	public void imprimiPrimos() {
		for (int i = 41; i <= 5002; i++) {
			if (ehPrimo(i))				
				this.setLista(i + ", ");
		}
		System.out.println(this.lista);
	}

	private static boolean ehPrimo(int numero) {
		for (int j = 2; j < numero; j++) {
			if (numero % j == 0)
				return false;
		}
		return true;
	}
}
