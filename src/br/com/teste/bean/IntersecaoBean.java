package br.com.teste.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IntersecaoBean {

	private String menssagem = "";
	private int f1a;
	private int f1b;
	private int f2a;
	private int f2b;

	private List<String> faixa1 = new ArrayList<String>();
	private List<String> faixa2 = new ArrayList<String>();

	public List<String> getFaixa1() {
		return faixa1;
	}

	public void setFaixa1(List<String> faixa1) {
		this.faixa1 = faixa1;
	}

	public List<String> getFaixa2() {
		return faixa2;
	}

	public void setFaixa2(List<String> faixa2) {
		this.faixa2 = faixa2;
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public int getF1a() {
		return f1a;
	}

	public void setF1a(int f1a) {
		this.f1a = f1a;
	}

	public int getF1b() {
		return f1b;
	}

	public void setF1b(int f1b) {
		this.f1b = f1b;
	}

	public int getF2a() {
		return f2a;
	}

	public void setF2a(int f2a) {
		this.f2a = f2a;
	}

	public int getF2b() {
		return f2b;
	}

	public void setF2b(int f2b) {
		this.f2b = f2b;
	}

	public void existeIntersecao() {
		System.out.println("Iniciando Verificação");

		preencheFaixa(this.faixa1, getF1a(), getF1b());
		preencheFaixa(this.faixa2, getF2a(), getF2b());
		verificaIntersecao(this.faixa1, this.faixa2);
		System.out.println("Verificação concluida - " + this.getMenssagem());
	}

	public List<String> preencheFaixa(List<String> faixa, int inicio, int fim) {

		for (int i = inicio; i <= fim; i++) {
			faixa.add("" + i);
		}
		return faixa;
	}

	public void verificaIntersecao(List<String> faixa1, List<String> faixa2) {
		for (String a : faixa1) {
			for (String b : faixa2) {
				System.out.println("String a - " + a);

				if (faixa2.contains(a)) {
					System.out.println("String b - " + a);
					this.setMenssagem("Existe interseção entre as faixas 1 e 2.");
				} else if (faixa1.contains(b)) {
					System.out.println("String b - " + b);
					this.setMenssagem("Existe interseção entre as faixas 1 e 2.");
				} else {
					this.setMenssagem("Não há interseção entre as faixas 1 e 2.");
				}
			}
		}
	}

}
