package controle.Servicos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String SQL = "INSERT INTO Servicos (preco_servico, nome_servico) VALUES(?, ?)";
		// TODO Auto-generated method stub
		return 0;
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
				
				Servicos end = new Servicos();
				
				Float preco_servico = rs.getFloat("preco_servico");
				String nome_servico = rs.getString("Senha");
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
