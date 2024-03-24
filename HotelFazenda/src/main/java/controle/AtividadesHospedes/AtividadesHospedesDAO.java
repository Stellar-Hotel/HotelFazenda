package controle.AtividadesHospedes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controle.Conexao;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.Hospedes;

public class AtividadesHospedesDAO implements IAtividadesHospedesDAO {

	private static AtividadesHospedesDAO instancia;

	/*
	 * Método para instanciar
	 * 
	 */
	public static AtividadesHospedesDAO getInstancia() {
		if (instancia == null) {
			instancia = new AtividadesHospedesDAO();
		}

		return instancia;
	}

	/*
	 * construtor privado (padrão singleton
	 */
	private AtividadesHospedesDAO() {
	}

	@Override
	public int InserirAtividadesHospedes(AtividadesHospedes A) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO AtividadesHospedes(IdHospede,IdAtividade) VALUES (?,?)";
		// Método pra fazer a conexão com o banco
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		int ChavePrimariaGerada = Integer.MIN_VALUE;

		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			Ps.setInt(1, A.getHospede().getIdHospede());
			Ps.setInt(2, A.getAtividade().getIdAtividade());

			int result = Ps.executeUpdate();
			if (result ==0) {
				throw new SQLException("Não foi possível inserir na tabela Ativides Hospedes!");
			}
			else {
				ResultSet rs=Ps.getGeneratedKeys();
				if(rs.next())
				{
					ChavePrimariaGerada=rs.getInt(1);
				}
			}				
			/*
			 * return Ps.executeUpdate();Atualiza o banco sem retorno do banco quando o
			 * insert for sem coisa auto gerada
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		return ChavePrimariaGerada;
	}

	@Override
	public ArrayList<AtividadesHospedes> ListarAtividadesHospedes() {

		// ArrayList Que vai ser retornado
		ArrayList<AtividadesHospedes> AtividadesHospedes = new ArrayList<AtividadesHospedes>();

		// Comando pro MySQL
		String SQL = "SELECT * FROM AtividadesHospedes";

		// Método pra fazer a conexão
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		// Método pra avisar caso dê algum erro, ele tenta o bloco de código no try e se
		// der erro mostra um erro no método do catch
		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);

			ResultSet Rs = Ps.executeQuery();

			while (Rs.next()) {
				AtividadesHospedes At = new AtividadesHospedes();

				Hospedes Hospede = new Hospedes();
				// prenche os atributos desse objeto

				Hospede.setNome(Rs.getString("Nome"));
				Hospede.setCPF(Rs.getString("CPF"));
				Hospede.setSobrenome(Rs.getString("Sobrenome"));
				Hospede.setDataNasc(Rs.getDate("data_nasc"));
				Hospede.setNacionalidade(Rs.getString("Nacionalidade"));
				Hospede.setPronome(Rs.getString("Pronome"));
				Hospede.setEmail(Rs.getString("Email"));
				Hospede.setDataNasc(Rs.getDate("DataNasc"));
				Hospede.setIdHospede(Rs.getInt("IdHospede"));

				Atividades Ativ = new Atividades();

				Ativ.setIdadeMinima(Rs.getInt("IdadeMinima"));
				Ativ.setHorario(Rs.getString("Horario"));
				Ativ.setHorarioFim(Rs.getString("HorarioFim"));
				Ativ.setNomeAtividade(Rs.getString("NomeAtividade"));
				Ativ.setData(Rs.getDate("Data"));
				Ativ.setIdAtividade(Rs.getInt("IdAtividade"));
				
				
				Ativ.setFuncionario(null);

				// Tem que preencher os atributos dos objetos hd e ativ
				At.setIdHospedeAtividade(Rs.getInt("IdHospedeAtividade"));
				At.setHospede(Hospede);
				At.setAtividade(Ativ);

				AtividadesHospedes.add(At);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}

		// Return da arraylist
		return AtividadesHospedes;
	}

	@Override
	public boolean AtualizarAtividadesHospedes(AtividadesHospedes AtivHosp) {
		// TODO Auto-generated method stub
		String SQL = "UPDATE AtividadesHospedes SET IdHospede = ?, IdAtividade = ? WHERE IdHospedeAtividade = ?";
		
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		int Retorno=0;
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			
			ps.setInt(1,AtivHosp.getHospede().getIdHospede() );
			ps.setInt(2, AtivHosp.getAtividade().getIdAtividade());
			
			ps.setInt(3, AtivHosp.getIdHospedeAtividade());
			
			Retorno=ps.executeUpdate();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		return (Retorno==0?false:true);
	}

	@Override
	public AtividadesHospedes BuscarAtividadesHospedesPorNome(String Nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean RemoverAtividadeHospede(String IdAtividade) {
		String SQL = "DELETE FROM AtividadesHospedes WHERE NomeAtividade = ?";

		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setString(1, IdAtividade);

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		return (retorno == 0 ? false : true);
	}

}
