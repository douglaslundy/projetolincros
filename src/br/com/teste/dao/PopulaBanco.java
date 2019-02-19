package br.com.teste.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.teste.model.Item;
import br.com.teste.model.Lancamento;

public class PopulaBanco {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		
		/**

		Item item1 = geraItem("Livro Machado de Assis", 50.0);
		Item item2 = geraItem("Livro Dom Casrmurro", 30.0);
		Item item3 = geraItem("Paulo Coelho", 55.0);
		Item item4 = geraItem("Livro Augusty Cury", 50.0);
		Item item5 = geraItem("Teste", 500.0);
		em.persist(item1);
		em.persist(item2);
		em.persist(item3);
		em.persist(item4);
		em.persist(item5);

		
		Lancamento lancamento1 = geraLancamento("01/01/2018", "01/01/2018", 60.0, "ok");
		
		em.persist(lancamento1);
		
		**/
		
		
		em.getTransaction().commit();
		em.close();

	}

	private static Item geraItem(String descricao, double valor) {
		Item item = new Item();
		item.setDescricao(descricao);
		item.setValor(valor);
		return item;
	}

	private static Lancamento geraLancamento(String dt_inicial, String dt_final, double vl_total,
			String observacao) {
		Lancamento lancamento = new Lancamento();
		lancamento.setDt_inicial(parseData(dt_inicial));
		lancamento.setDt_final(parseData(dt_final));
		lancamento.setVl_total(vl_total);
		lancamento.setObservacao(observacao);
		return lancamento;
	}

	@SuppressWarnings("unused")
	private static Calendar parseData(String data) {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

}