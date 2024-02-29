package controle.AtividadesHospedes;

import java.sql.Connection;
import java.util.ArrayList;

import controle.Conexao;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
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
		
		//ArrayList Que vai ser retornado
		ArrayList<AtividadesHospedes> AtividadesHospedes= new ArrayList<AtividadesHospedes>();
		
		//Comando pro MySQL
		String SQL = "SELECT * FROM Atividades";
		
		//Método pra fazer a conexão
		Conexao con= Conexao.getConexao();
		Connection conBD= con.conectar();
		
		// TODO Auto-generated method stub
		//Return da arraylist
		return AtividadesHospedes;
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
