package controle.AtividadesHospedes;

import java.util.ArrayList;

import controle.Atividades.AtividadesDAO;
import modelo.AtividadesHospedes;
public class AtividadesHospedesDAO implements IAtividadesHospedesDAO {

	private static AtividadesHospedesDAO instancia;

	/*Método para instanciar
	 * 
	 */
	public static AtividadesHospedesDAO getInstancia() {
		if (instancia == null) {
			instancia = new AtividadesHospedesDAO();
		}

		return instancia;
	}
	/*
	 * construtor privado (padrão singleton
	*/
	private AtividadesHospedesDAO() {}
	@Override
	public int InserirAtividadesHospedes(AtividadesHospedes A) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<AtividadesHospedes> ListarAtividadesHospedes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AtualizarAtividadesHospedes(AtividadesHospedes A) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AtividadesHospedes BuscarAtividadesHospedesPorNome(String Nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
