package controle.ServicosConsumidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import controle.Conexao;
import controle.Servicos.ServicosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Servicos;
import modelo.ServicosConsumidos;

public class ServicosConsumidosDAO implements IServicosConsumidosDAO {

	private static ServicosConsumidosDAO instancia;

	private ServicosConsumidosDAO() {

	}

	public static ServicosConsumidosDAO getinstancia() {
		if (instancia == null) {
			instancia = new ServicosConsumidosDAO();
		}
		return instancia;
	}

	@Override
	public int inserirServicoConsumido(ServicosConsumidos end) {

		Conexao con = Conexao.getInstancia();
		Connection conBD = con.Conectar();
		String SQL = "INSERT INTO ServicosConsumidos (id_hospede, id_servico, id_hospedagens) VALUES(?, ?, ?)";
		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {

			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();

			ps.setInt(1, end.getHospede().getHospedeId());
			ps.setInt(2, end.getServico().getIdServicos());
			ps.setInt(3, end.getHospedagem().getHospedagensId());

			if (rs != null) {
				chavePrimariaGerada = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		return chavePrimariaGerada;

	}

	@Override
	public ArrayList<ServicosConsumidos> ListarServicos() {

		ArrayList<ServicosConsumidos> Servicoconsumido = new ArrayList<ServicosConsumidos>();
		String SQL = "SELECT hospede.cpf, servicos.id_servico FROM servicos_consumidos "
				+ "INNER JOIN hospede.id_hospede = servico_consumido.id_hospede";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.Conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ServicosConsumidos Serv = new ServicosConsumidos();

				// Pegar os dados da tabela hospede

				// Pegar os dados da tabela servicos

				// Pegar os dados da tabela hospedagens

				// Criar obj para cada tabela
				// tem que preencher os atributos desses objetos
				Hospedes Hospede = new Hospedes();
				Servicos Servico = new Servicos();
				Hospedagens Hospedagem = new Hospedagens();

				// Setar os valores nos objetos
				Hospede.setNome(rs.getString("Nome"));
				Hospede.setCPF(rs.getString("CPF"));
				Hospede.setSobrenome(rs.getString("Sobrenome"));
				Hospede.setDataNasc(rs.getDate("data_nasc"));
				Hospede.setNacionalidade(rs.getString("Nacionalidade"));
				Hospede.setPronome(rs.getString("Pronome"));
				Hospede.setEmail(rs.getString("Email"));

				Serv.setHospede(Hospede);
				Serv.setServico(Servico);
				Serv.setHospedagens(Hospedagem);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
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
	public boolean atualizarServicoConsumido(ServicosConsumidos end) {
		// Comando que vai ser executado no sql
		String SQL = "UPDATE ServicosConsumidos SET idServico=? where IdServicosConsumidos=?";

		// abre a conexão e cria a "ponte de conexão" com MYsql
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.Conectar();

		boolean retorno = false;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);

			Ps.setInt(1, end.getHospede().getHospedeId());
			Ps.setInt(2, end.getServico().getIdServicos());
			Ps.setInt(3, end.getHospedagem().getHospedagensId());

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
	public boolean removerServicoConsumido(ServicosConsumidos end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ServicosConsumidos buscarServicoConsumidoPorNome(int nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
