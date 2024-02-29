package controle.Atividades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import controle.Conexao;
import modelo.Atividades;
import modelo.AtividadesHospedes;

public class AtividadesDAO implements IAtividadesDAO

{
	private static AtividadesDAO instancia;

	public static AtividadesDAO getInstancia() {
		if (instancia == null) {
			instancia = new AtividadesDAO();
		}

		return instancia;
	}
	private AtividadesDAO() {}
	@Override
	public ArrayList<Atividades> ListarAtividades() {
		
		//ArrayList Que vai ser retornado
		ArrayList<Atividades> atividades= new ArrayList<Atividades>();
		
		//Comando pro MySQL
		String SQL = "SELECT * FROM Atividades";
		
		//Método pra fazer a conexão
		Conexao con= Conexao.getConexao();
		Connection conBD= con.conectar();
		
		try {
			PreparedStatement Ps=conBD.prepareStatement(SQL);
			
			ResultSet Rs= Ps.executeQuery();
			while (Rs.next()) {
				Atividades At=new Atividades();
				
				int IdAtividade= Rs.getInt("id_atividade");
				int RestricaoIdade= Rs.getInt("restricao_idade");
				String Horario=Rs.getString("horario");
				String HorarioFim=Rs.getString("horario_fim");
				int FuncionarioId=Rs.getInt("funcionario_id");
				String NomeAtividade=Rs.getString("nome_atividade");
				Date Data=Rs.getDate("data");
				
				At.setIdAtividade(IdAtividade);
				At.setRestricaoIdade(RestricaoIdade);
				At.setHorario(Horario);
				At.setHorarioFim(HorarioFim);
				At.setFuncionarioId(FuncionarioId);
				At.setNomeAtividade(NomeAtividade);
				At.setData(Data);
				atividades.add(At);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		//Return da arraylist
		return atividades;
	}

	@Override
	public boolean AtualizarAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean RemoverAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Atividades BuscarAtividadesPorLocal(String Local) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int InserirAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		String SQL= "INSERT INTO Atividades(horario, horario_fim) VALUES (?,?)";
		return 0;
	}
	
}
