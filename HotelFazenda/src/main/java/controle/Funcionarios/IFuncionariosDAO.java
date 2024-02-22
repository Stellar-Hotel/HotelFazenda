package controle.Funcionarios;

import java.util.ArrayList;

import modelo.Funcionarios;

public interface IFuncionariosDAO {
	public int InserirFuncionario(Funcionarios Func);
	public ArrayList<Funcionarios> ListarFuncionarios();
	public boolean AtualizarFuncionarios(Funcionarios Func);
	public boolean RemoverFuncionario(Funcionarios Func);
	public Funcionarios BuscarFuncionarioPorNome(String Nome);
}
