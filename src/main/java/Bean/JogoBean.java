package Bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import dao.JogoDao;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista; 

	
	public String salvar() { 
		try {
			JogoDao.salvar(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"O " + jogo.getDescricao() + " foi Salvo com sucesso."));
			jogo = new Jogo();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Erro ao salvar o Jogo"));
		}
		return "listagem";
	}

	public String editar() {
		try {
			JogoDao.editar(jogo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", jogo.getDescricao() + " foi editado com sucesso"));

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao editar a Jogo"));
		}
		return "listagem";
	}

	public String excluir() {
		try {
			JogoDao.excluir(jogo);
			lista.remove(jogo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", jogo.getDescricao() + " foi excluido com sucesso"));

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error ao excluir o jogo"));
		}
		return null;
	}

	public void buscarPares() {
		try {
			List<Integer> numerosPares = JogoDao.buscarValoresPares(jogo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso",
					"Os pares são" + jogo.getDescricao() + " sÃ£o: " + numerosPares));

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao mostrar os nï¿½meros pares"));
		}
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getLista() {
		if (lista == null) {
			lista = JogoDao.listar();
		}
		return lista;
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

}
