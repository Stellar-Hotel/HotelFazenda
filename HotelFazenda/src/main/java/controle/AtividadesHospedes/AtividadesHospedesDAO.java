package controle.AtividadesHospedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		//Método pra avisar caso dê algum erro, ele tenta o bloco de código no try e se der erro mostra um erro no método do catch
		try {
			PreparedStatement Ps=conBD.prepareStatement(SQL);
			
			ResultSet Rs= Ps.executeQuery();
			
			while (Rs.next()) {
				AtividadesHospedes At=new AtividadesHospedes();
				
				int IdAtividadesHospedes= Rs.getInt("id_hospede_atividade");
				int IdHospede= Rs.getInt("Hospede_id");
				int IdAtividade= Rs.getInt("id_atividade");
				
				
				
				At.setIdAtividadesHospedes(IdAtividadesHospedes);
				At.setIdHospede(IdHospede);
				At.setIdAtividade(IdAtividade);
				
				AtividadesHospedes.add(At);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
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
