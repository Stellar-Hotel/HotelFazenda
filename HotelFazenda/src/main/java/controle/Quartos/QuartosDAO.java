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
public class QuartosDAO implements IQuartosDAO{
	
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
		
		String SQL = "INSERT INTO Quartos (id_Quartos ,max_pessoas, manutencao, tipo_cama, frigobar, ar_condicionado, banheira, tv, preco_quarto_dia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();
		
		int ChavePrimariaGerada = Integer.MIN_VALUE;
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, end.getIdQuartos());
			ps.setInt(2, end.getMaxPessoas());
			ps.setString(3, end.getManutencao());
			ps.setString(4, end.getTipoCama());
			ps.setBoolean(5, end.getFrigobar());
			ps.setBoolean(6, end.getArCondicionado());
			ps.setBoolean(7, end.getBanheira());
			ps.setBoolean(8, end.getTV());
			ps.setFloat(9, end.getPrecoDiaria());
		
			ResultSet Rs = ps.executeQuery();
			if(Rs!=null) {
				ChavePrimariaGerada=Rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.fecharConexao();
		}
		
		return 0;
	}
	@Override
	public ArrayList<Quartos> ListarQuartos() {
		// TODO Auto-generated method stub
		
ArrayList<Quartos> Funcionarios = new ArrayList<Quartos>();
		
		String SQL = "SELECT * FROM Quartos";
		
		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Quartos quartos = new Quartos();
				
				Integer id_Quartos = rs.getInt("id_Quartos");
				Integer max_pessoas = rs.getInt("max_pessoas");
				String manutencao = rs.getString("manutencao");
				String tipo_cama = rs.getString("tipo_cama");
				Boolean frigobar = rs.getBoolean("frigobar");
				Boolean ar_condicionado = rs.getBoolean("ar_condiconado");
				Boolean banheira = rs.getBoolean("banheira");
				Boolean tv = rs.getBoolean("tv");
				Float preco_quarto_dia= rs.getFloat("preco_quarto_dia");
				
				quartos.setIdQuartos(id_Quartos);
				quartos.setMaxPessoas(max_pessoas);
				quartos.setManutencao(manutencao);
				quartos.setTipoCama(tipo_cama);
				quartos.setFrigobar(frigobar);
				quartos.setArCondicionado(ar_condicionado);
				quartos.setBanheira(banheira);
				quartos.setTV(tv);
				quartos.setPrecoDiaria(preco_quarto_dia);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	@Override
	public boolean atualizarQuarto(Quartos end) {



        //Conexâo SQl a ser executada
        String SQL = "UPDATE Quartos SET MaxPessoas = ? WHERE IdQuartos = ?";
        
        //abre a conexão e cria a "parte de conexão" com MYSQL
        Conexao con= Conexao.getInstancia();
        Connection conBD= con.conectar();
        
        int retorno = 0;
        
        try {
            PreparedStatement ps = conBD.prepareStatement(SQL);
            
            ps.setInt(1, end.getIdQuartos());
            ps.setInt(2, end.getMaxPessoas());
            ps.setString(3, end.getTipoCama());
            ps.setString(4, end.getManutencao());
            ps.setBoolean(5, end.getFrigobar());
            ps.setBoolean(6, end.getArCondicionado());
            ps.setBoolean(7, end.getBanheira());
            ps.setBoolean(8, end.getTV());
            ps.setFloat(9, end.getPrecoDiaria());
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            
        }


        return (retorno == 0 ? false :true);
	}
	@Override
	public boolean removerQuarto(Quartos end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Quartos buscarQuartoPorNumero(int cep) {
		// TODO Auto-generated method stub
		return null;
	}
}
