package controle.Hospede;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import modelo.Hospedes;

public class HospedeDAO implements IHospedeDAO {

	/*
	 * variavel padrao singleton
	 */
	private static HospedeDAO instancia;

	/*
	 * Construtor privado(padrao singleton)
	 */

	private HospedeDAO() {
	}

	public static HospedeDAO getInstancia() {
		if (instancia == null) {
			instancia = new HospedeDAO();
		}
		return instancia;
	}

	public int inserirHospede(Hospedes Hd) {
		// TODO Auto-generated method stub

		// Comando SQL a ser executado
		String SQL = "INSERT INTO Hospedagens (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		// cria a "ponte de conexao" com o MYSQL
		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();

		int chavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, Hd.getNome());
			ps.setString(2, Hd.getSobrenome());
			ps.setDate(3, Hd.getDataNasc());
			ps.setString(4, Hd.getNacionalidade());
			ps.setString(5, Hd.getEmail());
			ps.setString(6, Hd.getPronome());
			ps.setString(7, Hd.getCPF());

			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				chavePrimariaGerada = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Fecha conexao
			con.fecharConexao();
		}

		return chavePrimariaGerada;
	}

	@Override
	public ArrayList<Hospedes> ListarHospedes() {
		// TODO Auto-generated method stub

		// Arraylist para armazenar resultado do select
		ArrayList<Hospedes> hospede = new ArrayList<Hospedes>();

		// comando sql executado
		String SQL = "SELECT * FROM Hospedes";

		// cria a "ponte de conexao" com o mysql
		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();

		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);

			ResultSet rs = ps.executeQuery(SQL);

			while (rs.next()) {

				// Cria objeto
				Hospedes Hd = new Hospedes();

				// Pega os valores de cada coluna d registro
				String nome = rs.getString("Nome");
				String sobrenome = rs.getString("Sobrenome");
				Date data_nasc = rs.getDate("data_nasc");
				String cpf = rs.getString("CPF");
				String nacionalidade = rs.getString("Nacionalidade");
				String pronome = rs.getString("Pronome");
				String email = rs.getString("Email");

				Hd.setNome(sobrenome);
				Hd.setNome(nome);
				Hd.setDataNasc(data_nasc);
				Hd.setCPF(cpf);
				Hd.setEmail(email);
				Hd.setNacionalidade(nacionalidade);
				Hd.setPronome(pronome);

				// Adiciona objeto na lista
				hospede.add(Hd);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.fecharConexao();
		}

		return hospede;
	}

	@Override
	public boolean atualizarHospede(Hospedes Hd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removerHospede(Hospedes Hd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hospedes buscarHospedePorCep(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
