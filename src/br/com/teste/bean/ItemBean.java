package br.com.teste.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.teste.dao.DAO;
import br.com.teste.model.Item;

@ManagedBean
@ViewScoped
public class ItemBean {
	private Item item = new Item();
	private int itemid;	

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public Item getItem() {
		return item;
	}
	
	public List<Item> getItens(){
		return new DAO<Item>(Item.class).listaTodos();
	}
	
	public void carregaritemPelaid() {
		System.out.println("id e " + this.itemid);
		this.item = new DAO<Item>(Item.class).buscaPorId(itemid); 
	}
	
	public String formLancamento() {
		return "lancamento?faces-redirect=true";
	}

	public void gravar() {
				
		if(item.getDescricao().isEmpty()){
            FacesContext.getCurrentInstance().addMessage("descricao", new FacesMessage("Item deve ter uma descrição"));
            return;
        }
		
		if(this.item.getOid() > 0) {
			new DAO<Item>(Item.class).atualiza(this.item);
			System.out.println("Editando Item " + this.item.getDescricao() + " de id " + this.item.getOid());
		}else {
			new DAO<Item>(Item.class).adiciona(this.item);
			System.out.println("Gravando Item " + this.item.getDescricao());
		}
        
        this.item = new Item();
        //return "item?faces-redirect=true";
	}
	
	public void remover(Item item) {
		System.out.println("Removendo item" + item.getDescricao());
		new DAO<Item>(Item.class).remove(item);
	}
	
	public void carregar(Item item) {
		System.out.println("Editando item de id " + item.getOid());
		this.item = item;
	}
}
