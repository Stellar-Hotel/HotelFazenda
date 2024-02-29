package controle.Atividades;

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
		String SQL = "SELECT * FROM Atividades";
		// TODO Auto-generated method stub
		return null;
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
