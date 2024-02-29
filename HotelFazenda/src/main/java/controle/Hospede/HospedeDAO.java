package controle.Hospede;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.math.BigInteger;
import controle.Conexao;
import modelo.Hospede;

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

	public int inserirHospede(Hospede end) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO Hospedagens (nome, sobrenome, data_nasc, CPF, Nacionalidade, Pronome, email, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return 0;
	}

	@Override
	public ArrayList<Hospede> ListarHospedes() {
		// TODO Auto-generated method stub

		// Arraylist para armazenar resultado do select
		ArrayList<Hospede> hospede = new ArrayList<Hospede>();

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
				Hospede Hd = new Hospede();

				// Pega os valores de cada coluna d registro
				String nome = rs.getString("Nome");
				String sobrenome = rs.getString("Sobrenome");
				Date data_nasc = rs.getDate("Data de Nascimento");
				String cpf = rs.getString("CPF");
				String nacionalidade = rs.getString("Nacionalidade");
				String pronome = rs.getString("Pronome");
				String email = rs.getString("Email");

				Hd.setNome(sobrenome);
				Hd.setNome(nome);
				Hd.setDataNasc(data_nasc);
				Hd.setCpf(cpf);
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
	public boolean atualizarHospede(Hospede end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removerHospede(Hospede end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Hospede buscarHospedePorCep(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
