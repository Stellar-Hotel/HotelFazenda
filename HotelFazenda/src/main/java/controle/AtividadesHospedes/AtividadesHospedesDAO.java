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
import modelo.Funcionarios;
import modelo.Hospedes;
import modelo.Usuarios;

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
		String SQL = "INSERT INTO `AtividadesHospedes` (IdHospedeAtividades, IdAtividadeAtividades) VALUES (?,?)";
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
		String SQL = "SELECT * FROM atividadeshospedes inner join Atividades on Atividades.IdAtividade=AtividadesHospedes.IdAtividadeAtividades"
				+ " inner join Hospedes on Hospedes.IdHospede=AtividadesHospedes.IdHospedeAtividades"
				+ " inner join Funcionarios on Atividades.IdFuncionarioAtividade=Funcionarios.IdFuncionario";

		//tem que rever esse inner join aqui e preencher os atributos dos objetos de cada tabela

		// Método pra fazer a conexão
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();

		// Método pra avisar caso dê algum erro, ele tenta o bloco de código no try e se
		// der erro mostra um erro no método do catch
		try {
			PreparedStatement Ps = conBD.prepareStatement(SQL);
			System.out.println(Ps);

			ResultSet Rs = Ps.executeQuery();

			while (Rs.next()) {
				AtividadesHospedes At = new AtividadesHospedes();
				
				Funcionarios Func=new Funcionarios();
				
				Func.setIdFuncionario(Rs.getInt("IdFuncionario"));

				Hospedes Hospede = new Hospedes();
				// prenche os atributos desse objeto

				Hospede.setNome(Rs.getString("Nome"));
				Hospede.setDocumento(Rs.getString("Documento"));
				Hospede.setSobrenome(Rs.getString("Sobrenome"));
				Hospede.setDataNasc(Rs.getDate("DataNasc"));
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
				
				
				Ativ.setFuncionario(Func);

				// Tem que preencher os atributos dos objetos hd e ativ
				At.setIdHospedeAtividade(Rs.getInt("IdAtividadesHospedes"));
				At.setHospede(Hospede);
				At.setAtividade(Ativ);

				AtividadesHospedes.add(At);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
System.out.println(AtividadesHospedes.size());
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
	public ArrayList<AtividadesHospedes> BuscarAtividadesHospedesPorIdHospede(int Id) {
		// TODO Auto-generated method stub
		AtividadesHospedes AtivHosp=null;
		ArrayList<AtividadesHospedes> Lista=new ArrayList<AtividadesHospedes>();
		String SQL="Select * from AtividadesHospedes where IdHospede = ? inner join Atividades.IdAtividade=AtividadesHospedes.IdAtividade"+
					" inner join Hospedes.IdHospede=AtividadesHospedes.IdHospede inner join Usuarios.IdUsuario=Hospede.IdUsuario "+
					" inner join Funcionarios.IdFuncionario=Atividades.IdFuncionario inner join Usuarios.IdUsuario=Funcionario.IdUsuario";
		Conexao con=Conexao.getConexao();
		Connection conBD=con.Conectar();
		
		try {
			PreparedStatement ps=conBD.prepareStatement(SQL);
			ps.setInt(1, Id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) 
			{
				Usuarios Usu=new Usuarios();
				Hospedes Hosp=new Hospedes();
				
				Atividades Ativ=new Atividades();
				
				Ativ.setData(rs.getDate("Data"));
				AtivHosp=new AtividadesHospedes();
				AtivHosp.setIdHospedeAtividade(rs.getInt("IdAtividadeHospede"));
				AtivHosp.setHospede(null);
				AtivHosp.setAtividade(null);
				
				Lista.add(AtivHosp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.FecharConexao();
		}
		return Lista;
	}

	@Override
	public boolean RemoverAtividadeHospede(int IdAtividade) {
		String SQL = "DELETE FROM AtividadesHospedes WHERE IdAtividadesHospedes = ?";

		Conexao con = Conexao.getConexao(); // instanciando
		Connection conBD = con.Conectar(); // cria "ponte"

		int retorno = 0;
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, IdAtividade);

			retorno = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		return (retorno == 0 ? false : true);
	}
	
public int contarHospedesNaAtividade(int idAtividade) {
	        int contagem = 0;
	        String SQL = "SELECT COUNT(*) FROM AtividadesHospedes WHERE IdAtividadeAtividades = ?";
	        
	        // Obtém a conexão com o banco de dados
	        Conexao con = Conexao.getConexao();
	        Connection conBD = con.Conectar();

	        try {
	            // Prepara o comando SQL
	            PreparedStatement ps = conBD.prepareStatement(SQL);
	            ps.setInt(1, idAtividade);

	            // Executa a consulta
	            ResultSet rs = ps.executeQuery();

	            // Processa o resultado
	            if (rs.next()) {
	                contagem = rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Fecha a conexão com o banco de dados
	            con.FecharConexao();
	        }
	        
	        return contagem;
	    }
public boolean isHospedeRegisteredForActivity(String documento, int idAtividade) {
    boolean isRegistered = false;
    String SQL = "SELECT COUNT(*) FROM AtividadesHospedes " +
                 "INNER JOIN Hospedes ON Hospedes.IdHospede = AtividadesHospedes.IdHospedeAtividades " +
                 "WHERE Hospedes.Documento = ? AND AtividadesHospedes.IdAtividadeAtividades = ?";
    
    Conexao con = Conexao.getConexao();
    Connection conBD = con.Conectar();

    try {
        PreparedStatement ps = conBD.prepareStatement(SQL);
        ps.setString(1, documento);
        ps.setInt(2, idAtividade);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            isRegistered = (count > 0);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        con.FecharConexao();
    }
    return isRegistered;
}


}
