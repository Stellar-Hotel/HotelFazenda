package controle.Quartos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import controle.Funcionarios.FuncionariosDAO;
import modelo.Funcionarios;
import modelo.Quartos;

public class QuartosDAO implements IQuartosDAO {

	private static QuartosDAO instancia;

	private QuartosDAO() {
	} // construtor privado

	/**
	 * Metodo singleton
	 * 
	 * @return conexao
	 */

	public static QuartosDAO getConexao() {
		if (instancia == null) {
			instancia = new QuartosDAO();
		}

		return instancia;
	}

	@Override
	public int inserirQuarto(Quartos end) {
		// TODO Auto-generated method stub

		String SQL = "INSERT INTO Quartos (idQuarto ,MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, end.getIdQuarto());
			ps.setInt(2, end.getMaxPessoas());
			ps.setString(3, end.getManutencao());
			ps.setString(4, end.getTipoCama());
			ps.setBoolean(5, end.getFrigobar());
			ps.setBoolean(6, end.getArCondicionado());
			ps.setBoolean(7, end.getBanheira());
			ps.setBoolean(8, end.getTV());
			ps.setFloat(9, end.getPrecoDiaria());

			int result=ps.executeUpdate();
			if(result==0)
			{
				throw new SQLException("Não foi possível inserir o quarto!");
			}
			else {
				ResultSet Rs=ps.getGeneratedKeys();
				if(Rs.next())
				{
					ChavePrimariaGerada=Rs.getInt(1);
				}
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
	public ArrayList<Quartos> ListarQuartos() {
		// TODO Auto-generated method stub

		ArrayList<Quartos> Quartos = new ArrayList<Quartos>();

		String SQL = "SELECT * FROM Quartos";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Quartos Quarto = new Quartos();
				
				Quarto.setIdQuarto(rs.getInt("IdQuarto"));
				Quarto.setMaxPessoas(rs.getInt("MaxPessoas"));
				Quarto.setManutencao(rs.getString("Manutencao"));
				Quarto.setTipoCama(rs.getString("TipoCama"));
				Quarto.setFrigobar(rs.getBoolean("Frigobar"));
				Quarto.setArCondicionado(rs.getBoolean("ArCondicionado"));
				Quarto.setBanheira(rs.getBoolean("Banheira"));
				Quarto.setTV(rs.getBoolean("TV"));
				Quarto.setPrecoDiaria(rs.getFloat("PrecoDiaria"));

				Quartos.add(Quarto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Quartos;

	}

	@Override
	public boolean atualizarQuarto(Quartos end) {

		// Conexâo SQl a ser executada
		String SQL = "UPDATE Quartos SET MaxPessoas = ?, TipoCama = ?, Manutencao = ?, Frigobar = ?, ArCondicionado = ?, Banheira = ?, TV = ?, PrecoDiaria = ? WHERE IdQuartos = ?";

		// abre a conexão e cria a "parte de conexão" com MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int retorno = 0;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ps.setInt(1, end.getMaxPessoas());
			ps.setString(2, end.getTipoCama());
			ps.setString(3, end.getManutencao());
			ps.setBoolean(4, end.getFrigobar());
			ps.setBoolean(5, end.getArCondicionado());
			ps.setBoolean(6, end.getBanheira());
			ps.setBoolean(7, end.getTV());
			ps.setFloat(8, end.getPrecoDiaria());
			ps.setInt(9, end.getIdQuarto());
			
			retorno= ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return (retorno == 0 ? false : true);
	}

	@Override
	public boolean removerQuarto(Quartos end) {
		// TODO Auto-generated method stub
		
		String SQL = "DELETE FROM Quartos WHERE IdQuarto = ?";

		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, end.getIdQuarto());

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return (retorno == 0 ? false : true);
		
	}

	@Override
	public Quartos buscarQuartoPorNumero(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
