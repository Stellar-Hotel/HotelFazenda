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
	
	/*
	 * Tem que possuir a chave primária(ID,CPF,CEP,etc)
	 * 
	 * Atualiza um registro já existente no banco de dados
	 * 
	 * O objeto passado já deve possuiur os novos valores
	 * porém deve possuir a mesma chave primária do registro que vai ser alteradio
	 * 
	 */
	@Override
	public boolean atualizarServico(Servicos end) {
		// Comando que vai ser executado no sql
		String SQL= "UPDATE Servicos SET Preco=? where ServicoId=?";
		
		//abre a conexão e cria a "ponte de conexão" com MYsql
		Conexao con=Conexao.getInstancia();
		Connection conBD=con.conectar();
		
		boolean retorno=false;
		
		try {
			PreparedStatement Ps= conBD.prepareStatement(SQL);
			
			Ps.setFloat(1,end.getPrecoServico());
			Ps.setString(2, end.getNomeServico());
			
			retorno=(Ps.executeUpdate()==0 ?false : true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
		
		return retorno;
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
