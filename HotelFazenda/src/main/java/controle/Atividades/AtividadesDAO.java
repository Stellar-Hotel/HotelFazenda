package controle.Atividades;

import java.sql.Connection;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Atividades;

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
		
		// TODO Auto-generated method stub
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
