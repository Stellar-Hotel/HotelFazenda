package controle.HospedagemQuartos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controle.Conexao;
import controle.HospedagemQuartos.HospedagemQuartoDAO;
import modelo.Funcionarios;
import modelo.HospedagemQuartos;

public class HospedagemQuartoDAO implements IHospedagemQuartosDAO
{

	
private static HospedagemQuartoDAO instancia;
	
	private HospedagemQuartoDAO() {
	} // construtor privado

	/**
	 * Metodo singleton
	 * 
	 * @return conexao
	 */

	public static HospedagemQuartoDAO getConexao() {
		if (instancia == null) {
			instancia = new HospedagemQuartoDAO();
		}

		return instancia;
	}
	
	@Override
	public int InserirHospedagemQuartos(HospedagemQuartos Hosp) {
		// TODO Auto-generated method stub
		String SQL = "INSERT INTO HospedagemQuartos (id_Hospedagem_quartos, id_Quartos, Hospedagens_id,Hospede_id) VALUES (?, ?)";
		return 0;
	}

	@Override
	public ArrayList<HospedagemQuartos> ListarHospedagemQuartos() {
		// TODO Auto-generated method stub
		
		ArrayList<HospedagemQuartos> HospedagemQuarto = new ArrayList<HospedagemQuartos>();
		
		String SQL = "SELECT * FROM HospedagemQuarto";
		
		Conexao con = Conexao.getConexao();
		Connection conBD = con.conectar();
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				HospedagemQuartos HospedagemQuartos = new HospedagemQuartos();
				
				Integer IdHospedagemQuarto = rs.getInt("id_Hospedagem_quartos");
				Integer IdQuarto = rs.getInt("id_Quartos");
				Integer HospedagemId = rs.getInt("Hospedagens_id");
				Integer HospedeId = rs.getInt("Hospede_id");
				
				HospedagemQuartos.setIdHospedagensQuartos(IdHospedagemQuarto);
				HospedagemQuartos.setIdQuartos(IdQuarto);
				HospedagemQuartos.setHospedagensId(HospedagemId);
				HospedagemQuartos.setHospedeId(HospedeId);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean AtualizarHospedagemQuartos(HospedagemQuartos Hosp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean RemoverHospedagemQuartos(HospedagemQuartos Hosp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HospedagemQuartos BuscarHospedagemQuartosPorNumero(int Num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
