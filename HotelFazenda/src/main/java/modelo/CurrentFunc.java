package modelo;

public class CurrentFunc {
	private static CurrentFunc instance;
	private Funcionarios loggedInFuncionario;

	// Construtor privado para evitar instanciamento externo
	private CurrentFunc() {
	}

	// Método para obter a instância única
	public static synchronized CurrentFunc getInstance() {
		if (instance == null) {
			instance = new CurrentFunc();
		}
		return instance;
	}

	public Funcionarios getLoggedInFuncionario() {
		return loggedInFuncionario;
	}

	public void setLoggedInFuncionario(Funcionarios funcionario) {
		this.loggedInFuncionario = funcionario;
	}
}
