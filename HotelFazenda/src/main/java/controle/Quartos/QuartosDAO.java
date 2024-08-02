package controle.Quartos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
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

		String SQL = "INSERT INTO Quartos (MaxPessoas, Manutencao, TipoCama, Frigobar, ArCondicionado, Banheira, TV, PrecoDiaria, Situacao,TipoQuarto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, end.getMaxPessoas());
			ps.setString(2, end.getManutencao());
			ps.setString(3, end.getTipoCama());
			ps.setBoolean(4, end.getFrigobar());
			ps.setBoolean(5, end.getArCondicionado());
			ps.setBoolean(6, end.getBanheira());
			ps.setBoolean(7, end.getTV());
			ps.setFloat(8, end.getPrecoDiaria());
			ps.setInt(9, end.getSituacao());
			ps.setInt(10, Integer.valueOf(end.getTipoQuarto()));

			int result = ps.executeUpdate();
			if (result == 0) {
				throw new SQLException("Não foi possível inserir o quarto!");
			} else {
				ResultSet Rs = ps.getGeneratedKeys();
				if (Rs.next()) {
					ChavePrimariaGerada = Rs.getInt(1);
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
				Quarto.setSituacao(rs.getInt("Situacao"));
				Quarto.setTipoQuarto(rs.getInt("TipoQuarto"));
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
		String SQL = "UPDATE Quartos SET MaxPessoas = ?, TipoCama = ?, Manutencao = ?, Frigobar = ?, ArCondicionado = ?, Banheira = ?, TV = ?, PrecoDiaria = ?, Situacao = ?, TipoQuarto=? WHERE IdQuarto = ?";

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
			ps.setInt(9, end.getSituacao());
			ps.setInt(10, Integer.valueOf(end.getTipoQuarto()));

			ps.setInt(11, end.getIdQuarto());
			retorno = ps.executeUpdate();

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
	public ArrayList<Quartos> buscarQuartoPorNumero(int x) {
		// TODO Auto-generated method stub
		ArrayList<Quartos> Quartos = new ArrayList<Quartos>();
		String SQL = "Select * from Quartos where TipoQuarto = ?";
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, x);
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
				Quarto.setSituacao(rs.getInt("Situacao"));
				Quarto.setTipoQuarto(rs.getInt("TipoQuarto"));
				Quartos.add(Quarto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Quartos;
	}

	@Override
	public Quartos buscarQuartoPorId(int id) {
		// TODO Auto-generated method stub
		Quartos Quarto = null;
		String SQL = "Select * from Quartos where IdQuarto = ?";
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Quarto = new Quartos();

				Quarto.setIdQuarto(rs.getInt("IdQuarto"));
				Quarto.setMaxPessoas(rs.getInt("MaxPessoas"));
				Quarto.setManutencao(rs.getString("Manutencao"));
				Quarto.setTipoCama(rs.getString("TipoCama"));
				Quarto.setFrigobar(rs.getBoolean("Frigobar"));
				Quarto.setArCondicionado(rs.getBoolean("ArCondicionado"));
				Quarto.setBanheira(rs.getBoolean("Banheira"));
				Quarto.setTV(rs.getBoolean("TV"));
				Quarto.setPrecoDiaria(rs.getFloat("PrecoDiaria"));
				Quarto.setSituacao(rs.getInt("Situacao"));
				Quarto.setTipoQuarto(rs.getInt("TipoQuarto"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return Quarto;
	}
}
