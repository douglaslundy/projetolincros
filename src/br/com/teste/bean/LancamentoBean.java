package br.com.teste.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.teste.dao.DAO;
import br.com.teste.model.Item;
import br.com.teste.model.Lancamento;

@ManagedBean
@ViewScoped
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private List<Item> itens = new ArrayList<Item>();
	private int lancamentoid;
	private int itemId;

	private String mensagemConsultas;

	public String getMensagemConsultas() {
		return mensagemConsultas;
	}

	public void setMensagemConsultas(String mensagemConsultas) {
		this.mensagemConsultas = mensagemConsultas;
	}

	public int getLancamentoid() {
		return lancamentoid;
	}

	public void setLancamentoid(int lancamentoid) {
		this.lancamentoid = lancamentoid;
	}

	public void carregarLancamentoPelaid() {
		System.out.println("id e " + this.lancamentoid);
		this.lancamento = new DAO<Lancamento>(Lancamento.class).buscaPorId(lancamentoid);
	}

	public List<Item> getItensLancamento() {
		return this.lancamento.getItem();
	}

	public List<Item> getItens() {
		return itens;
	}

	public List<Lancamento> getListarLancamentos() {
		return new DAO<Lancamento>(Lancamento.class).listaTodos();
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public String formItem() {
		return "item?faces-redirect=true";
	}

	public void adicionaItem() {
		Item itemAInserir = new DAO<Item>(Item.class).buscaPorId(itemId);
		itens = lancamento.getItem();
		itens.add(itemAInserir);
		this.lancamento.setItem(itens);
		System.out.println("Intem inserido e " + itemAInserir.getDescricao());
	}

	public void removeItem(Item item) {
		lancamento.getItem().remove(item);
		System.out.println("item removido foi " + item.getDescricao());
	}

	public void gravar() {
		System.out.println("Gravando lancamentos");
		if (lancamento.getObservacao().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("observacao",
					new FacesMessage("Observação deve conter pelo menos um caractere"));
		}
		if (lancamento.getItem().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("lancamentoItem",
					new FacesMessage("O Lançamento precisa ter pelo menos 1 item"));
			return;
		}

		if (this.lancamento.getOid() > 0) {
			new DAO<Lancamento>(Lancamento.class).atualiza(this.lancamento);
		} else {
			new DAO<Lancamento>(Lancamento.class).adiciona(this.lancamento);
		}
		this.lancamento = new Lancamento();
	}

	public void remover(Lancamento lancamento) {
		System.out.println("Removendo lancamento " + lancamento.getObservacao());
		new DAO<Lancamento>(Lancamento.class).remove(lancamento);
	}

	public void editar(Lancamento lancamento) {
		System.out.println("Editando Lancamento de id " + lancamento.getOid());
		this.lancamento = lancamento;
	}

	public void buscaLancamentosMediaMaior() {
		List<Lancamento> listaDeLancamentos;
		double totalValor = 0.0;
		int contador = 0;
		listaDeLancamentos = getListarLancamentos();

		for (Lancamento iterator : listaDeLancamentos) {

			if ((iterator.getVl_total() / iterator.getItem().size()) >= 100) {
				totalValor += iterator.getVl_total();
				contador += 1;
			}
		}
		if (contador > 0) {
			this.setMensagemConsultas("Existem " + contador
					+ " lançamentos cuja média dos itens e maior ou igual a 100, e a soma do total destes lançamentos é: "
					+ totalValor);
		} else {
			this.setMensagemConsultas("Não existem lançamentos cuja media e maior que 100");
		}
	}

	public void buscaLancamentosMaiorValorEDescricaoComecaComA() {
		List<Lancamento> listaDeLancamentos;
		List<Lancamento> lancamentosSelecionados = new ArrayList<Lancamento>();
		listaDeLancamentos = getListarLancamentos();

		for (Lancamento iterator : listaDeLancamentos) {

			if (iterator.getVl_total() > 50 && iterator.getObservacao().charAt(0) == 'A') {
				lancamentosSelecionados.add(iterator);
			}
		}

		Collections.sort(lancamentosSelecionados);

		List<String> listaLancamentos = new ArrayList<String>();
		int contador = 0;
		for (Lancamento insereLancamento : lancamentosSelecionados) {
			contador += 1;
			listaLancamentos.add(contador + "º - " + insereLancamento.getObservacao() + " com valor de "
					+ insereLancamento.getVl_total());
		}
		this.setMensagemConsultas("" + listaLancamentos);
	}
	
	public void selecionaLancamentosComMaisDeDezItens() {
		List<Lancamento> listaDeLancamentos;
		listaDeLancamentos = getListarLancamentos();
		int contador = 0;

		for (Lancamento lancamento : listaDeLancamentos) {
			if(lancamento.getItem().size() > 10) {
				String novaObservacao;
				novaObservacao = lancamento.getObservacao();
				novaObservacao += " - Possuem mais que 10 itens";
				System.out.println(novaObservacao);
				lancamento.setObservacao(novaObservacao);
				this.editar(lancamento);
				this.gravar();
				contador += 1;
			}
			
			if(contador > 0) {
				this.setMensagemConsultas("Foi encontrado " + contador + " lançamentos com mais de 10 itens e foram atualizados com a mensagem  - Possuem mais que 10 itens ");
			}else {
				this.setMensagemConsultas("Não foi encontrado nenhum lançamento com mais de 10 itens");
			}
		}
	}

}
