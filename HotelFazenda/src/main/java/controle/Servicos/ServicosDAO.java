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

public class ServicosDAO implements IServicosDAO{
	
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
		
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar(); 
		String SQL = "INSERT INTO Servicos (preco_servico, nome_servico) VALUES(?, ?)";
		int chavePrimariaGerada = Integer.MIN_VALUE;	
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			ps.setFloat(1, end.getPrecoServico());
			ps.setString(2,end.getNomeServico());
			
			if (rs!= null) {
				chavePrimariaGerada = rs.getInt(1);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}


		// TODO Auto-generated method stub
		return chavePrimariaGerada;
	}
	@Override
	public ArrayList<Servicos> ListarServicos() {
		ArrayList<Servicos> Servico = new ArrayList<Servicos>();
		String SQL = "SELECT * FROM Servicos";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar();
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Servicos Serv = new Servicos();
				
				Float preco_servico = rs.getFloat("preco_servico");
				String nome_servico = rs.getString("nome_servico");
				
				Serv.setPrecoServico(preco_servico);
				Serv.setNomeServico(nome_servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		

		return Servico;
	}
	@Override
	public boolean atualizarServico(Servicos end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removerServico(Servicos end) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Servicos buscarServicoPorNome(int nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
