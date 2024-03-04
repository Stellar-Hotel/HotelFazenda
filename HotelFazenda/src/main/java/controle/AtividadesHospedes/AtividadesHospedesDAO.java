package controle.AtividadesHospedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.Hospedes;
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
		String SQL= "INSERT INTO Atividades_Hospedes(Hospede_id,id_atividade) VALUES (?,?)";
		//Método pra fazer a conexão com o banco
		Conexao con= Conexao.getConexao();
		Connection conBD= con.conectar();
		
		int ChavePrimariaGerada= Integer.MIN_VALUE;
		
		try {
			PreparedStatement Ps= conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			Ps.setInt(1, A.getHospede().getHospedeId());
			Ps.setInt(2, A.getAtividade().getIdAtividade());
			
			ResultSet Rs= Ps.executeQuery();
			if(Rs!=null)
			{
				ChavePrimariaGerada=Rs.getInt(1);
			}
			/*
			 * return Ps.executeUpdate();Atualiza o banco sem retorno do banco quando o insert for sem coisa auto gerada
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		return ChavePrimariaGerada;
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
				
				Hospedes Hd=new Hospedes();
				Atividades Ativ=new Atividades();
				
				At.setAtividadesHospedes(IdAtividadesHospedes);
				At.setHospede(Hd);
				At.setAtividade(Ativ);
				
				AtividadesHospedes.add(At);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
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
