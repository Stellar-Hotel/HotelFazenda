package controle.Servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
import controle.Usuarios.UsuariosDAO;
import modelo.Servicos;

public class ServicosDAO implements IServicosDAO {

	private static ServicosDAO instancia;

	private ServicosDAO() {

	}

	public static ServicosDAO getInstancia() {
		if (instancia == null) {
			instancia = new ServicosDAO();
		}
		return instancia;
	}

	@Override
	public int inserirServico(Servicos end) {
		String SQL = "INSERT INTO Servicos (PrecoServico, NomeServico) VALUES(?, ?)";
		// TODO Auto-generated method stub
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);// se for um insert já conhcendo a chave primária não
																// adcionar o Statement.RETURN_GENERATED_KEYS
			Ps.setFloat(1, end.getPrecoServico());
			Ps.setString(2, end.getNomeServico());

			ResultSet Rs = Ps.executeQuery();
			if (Rs != null) {
				ChavePrimariaGerada = Rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return ChavePrimariaGerada;
	}

	@Override
	public ArrayList<Servicos> ListarServicos() {
		ArrayList<Servicos> Servico = new ArrayList<Servicos>();
		String SQL = "SELECT * FROM Servicos";
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Servicos Serv = new Servicos();

				int IdServico = rs.getInt("IdServico");
				Float PrecoServico = rs.getFloat("PrecoServico");
				String NomeServico = rs.getString("NomeServico");

				Serv.setIdServico(IdServico);
				Serv.setPrecoServico(PrecoServico);
				Serv.setNomeServico(NomeServico);

				Servico.add(Serv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Servico;
	}

	/*
	 * Tem que possuir a chave primária(ID,CPF,CEP,etc)
	 * 
	 * Atualiza um registro já existente no banco de dados
	 * 
	 * O objeto passado já deve possuiur os novos valores porém deve possuir a mesma
	 * chave primária do registro que vai ser alteradio
	 * 
	 */
	@Override
	public boolean atualizarServico(Servicos end) {
		// Comando que vai ser executado no sql
		String SQL = "UPDATE Servicos SET Preco=?,NomeServico=? where IdServico=?";

		// abre a conexão e cria a "ponte de conexão" com MYsql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		boolean retorno = false;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);

			Ps.setFloat(1, end.getPrecoServico());
			Ps.setString(2, end.getNomeServico());

			retorno = (Ps.executeUpdate() == 0 ? false : true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return retorno;
	}

	@Override
	public boolean removerServico(Servicos end) {

		String SQL = "Delete from Servicos Where IdServico = ?";
		// Método pra fazer a conexão com o banco
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int retorno = 0;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			Ps.setInt(1, end.getIdServico());
			retorno = Ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return (retorno == 0 ? false : true);

	}

	@Override
	public Servicos buscarServicoPorNome(int nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
