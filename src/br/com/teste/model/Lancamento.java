package br.com.teste.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lancamento implements Comparable<Lancamento> {

	@Id
	@GeneratedValue
	private int oid;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dt_inicial = Calendar.getInstance();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dt_final = Calendar.getInstance();
	private double vl_total;
	private String observacao;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Item> Item = new ArrayList<Item>();

	public List<Item> getItem() {
		return  Item;
	}
	
	public void setItem(List<Item> lancamentoItem) {
		this.Item = lancamentoItem;
	}
	
	

	public Lancamento() {
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}


	public Calendar getDt_inicial() {
		return dt_inicial;
	}

	public void setDt_inicial(Calendar dt_inicial) {
		this.dt_inicial = dt_inicial;
	}

	public Calendar getDt_final() {
		return dt_final;
	}

	public void setDt_final(Calendar dt_final) {
		this.dt_final = dt_final;
	}

	public double getVl_total() {
		setVl_total(0.0);
		for(Item item : Item) {
			vl_total += item.getValor();
		}
		return vl_total;
	}

	public void setVl_total(double vl_total) {
		this.vl_total = vl_total;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public int compareTo(Lancamento outroLancamento) {
	     if (this.vl_total > outroLancamento.getVl_total()) {
	          return -1;
	     }
	     if (this.vl_total < outroLancamento.getVl_total()) {
	          return 1;
	     }
	     return 0;
	}

}