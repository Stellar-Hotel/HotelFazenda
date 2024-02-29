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
		Connection conBD = con.conectar(); 
		String SQL = "INSERT INTO ServicosConsumidos (id_hospede, id_servico, id_hospedagens) VALUES(?, ?, ?)";
		int chavePrimariaGerada = Integer.MIN_VALUE;
		
		
	
		try {
			
			PreparedStatement ps = conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = ps.executeQuery();
			
			ps.setInt(1, end.getIdHospede());
			ps.setInt(2, end.getIdServico());
			ps.setInt(3, end.getIdHospedagens());
			
			if (rs!= null) {
				chavePrimariaGerada = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		
		
		return chavePrimariaGerada;
		
		
	}
	@Override
	public ArrayList<ServicosConsumidos> ListarServicos() {
		
		ArrayList<ServicosConsumidos> Servicoconsumido = new ArrayList<ServicosConsumidos>();
		String SQL = "SELECT * FROM Usuario";
		Conexao con = Conexao.getInstancia();
		Connection conBD = con.conectar(); 
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ServicosConsumidos Serv = new ServicosConsumidos();
				
				Integer id_hospede = rs.getInt("id_hospede");
				Integer id_servico = rs.getInt("id_servico");
				Integer id_hospedagens = rs.getInt("id_hospedagens");
				
				Serv.setIdHospede(id_hospede);
				Serv.setIdServico(id_servico);
				Serv.setIdHospedagens(id_hospedagens);
				
				
				
	
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean atualizarServicoConsumido(ServicosConsumidos end) {
		// TODO Auto-generated method stub
		return false;
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
