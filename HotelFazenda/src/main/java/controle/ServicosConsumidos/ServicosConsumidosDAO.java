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

		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();
		String SQL = "INSERT INTO ServicosConsumidos (IdHospede, IdServico, IdHospedagem) VALUES(?, ?, ?)";
		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {

			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			

			ps.setInt(1, end.getHospede().getIdHospede());
			ps.setInt(2, end.getServico().getIdServico());
			ps.setInt(3, end.getHospedagem().getIdHospedagem());

			int result=ps.executeUpdate();
			if(result==0)
			{
				throw new SQLException("Não foi possível inserir no banco!");
			}
			else
			{
				ResultSet Rs=ps.getGeneratedKeys();
				if(Rs.next())
				{
					chavePrimariaGerada=Rs.getInt(1);
				}
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

		ArrayList<ServicosConsumidos> Lista = new ArrayList<ServicosConsumidos>();
		String SQL = "SELECT * FROM servicos_consumidos INNER JOIN hospede.id_hospede = servico_consumido.id_hospede";
		Conexao con = Conexao.getConexao();
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
				Servico.setIdServico(rs.getInt("IdServico"));
				Servico.setNomeServico(rs.getString("NomeServico"));
				Servico.setPrecoServico(rs.getFloat("PrecoServico"));

				Hospedagem.setCheckin(rs.getDate("Checkin"));
				Hospedagem.setCheckout(rs.getDate("Checkout"));
				Hospedagem.setPrecoTotal(rs.getFloat("PrecoTotal"));
				Hospedagem.setIdHospedagem(rs.getInt("IdHospedagem"));

				Hospede.setCPF(rs.getString("CPF"));
				Hospede.setDataNasc(rs.getDate("DataNasc"));
				Hospede.setEmail(rs.getString("Email"));
				Hospede.setIdHospede(rs.getInt("IdHospede"));
				Hospede.setNacionalidade(rs.getString("Nacionalidade"));
				Hospede.setNome(rs.getString("Nome"));
				Hospede.setPronome(rs.getString("Pronome"));
				Hospede.setSobrenome(rs.getString("Sobreneome"));

				Hospedagem.setHospde(null);
				Hospede.setUsuario(null);

				Serv.setHospede(Hospede);
				Serv.setServico(Servico);
				Serv.setHospedagens(Hospedagem);

				Lista.add(Serv);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return Lista;
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
		String SQL = "UPDATE ServicosConsumidos SET IdServico=?, IdHospede=?, IdHospedagem=? where IdServicosConsumidos=?";

		// abre a conexão e cria a "ponte de conexão" com MYsql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		boolean retorno = false;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);

			Ps.setInt(1, end.getHospede().getIdHospede());
			Ps.setInt(2, end.getServico().getIdServico());
			Ps.setInt(3, end.getHospedagem().getIdHospedagem());
			
			Ps.setInt(4, end.getIdServicoConsumido());

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
		String SQL = "Delete from ServicosConsumidos Where IdServicoConsumido = ?";
		// Método pra fazer a conexão com o banco
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int retorno = 0;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			Ps.setInt(1, end.getIdServicoConsumido());
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
	public ServicosConsumidos buscarServicoConsumidoPorNome(int nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
