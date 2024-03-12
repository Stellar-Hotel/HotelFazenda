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
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;

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
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();
		
		int ChavePrimariaGerada = Integer.MIN_VALUE;
		
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			ps.setInt(1, Hosp.getIdHospedagensQuartos());
			ps.setInt(2, Hosp.getQuarto().getIdQuartos());
			ps.setInt(3, Hosp.getHospedagem().getHospedagensId());
			ps.setInt(3, Hosp.getHospede().getHospedeId());
		
			ResultSet Rs = ps.executeQuery();
			if(Rs!=null) {
				ChavePrimariaGerada=Rs.getInt(1);
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			con.FecharConexao();
		}
		
		
		return 0;
	}

	@Override
	public ArrayList<HospedagemQuartos> ListarHospedagemQuartos() {
		// TODO Auto-generated method stub
		
		ArrayList<HospedagemQuartos> HospedagemQuarto = new ArrayList<HospedagemQuartos>();
		
		String SQL = "SELECT * FROM HospedagemQuarto";
		
		Conexao con = Conexao.getConexao();
		Connection conBD = con.Conectar();
		try {
			PreparedStatement ps = conBD.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				HospedagemQuartos HospedagemQuartos = new HospedagemQuartos();
				
				Integer IdHospedagemQuarto = rs.getInt("id_Hospedagem_quartos");

				
				//tem que preencher os atributos desses 3 objetos
				Quartos Quarto= new Quartos();
				
				Quarto.setMaxPessoas(rs.getInt("MaxPessoas"));
				Quarto.setTipoCama(rs.getString("TipoCama"));
				Quarto.setManutencao(rs.getString("Manutenção"));
				Quarto.setFrigobar(rs.getBoolean("Frigobar"));
				Quarto.setArCondicionado(rs.getBoolean("ArCondicionado"));
				Quarto.setBanheira(rs.getBoolean("Banheira"));
				Quarto.setTV(rs.getBoolean("TV"));
				Quarto.setPrecoDiaria(rs.getFloat("PrecoDiaria"));  
				
				Hospedagens Hosp=new Hospedagens();
				
				Hosp.setCheckin(rs.getDate("Checkin"));
				Hosp.setCheckout(rs.getDate("Checkout"));
				Hosp.setPrecoTotal(rs.getFloat("PrecoTotal"));
				
				Hospedes Hospede=new Hospedes();
				
				Hospede.setNome(rs.getString("Nome"));
				Hospede.setCPF(rs.getString("CPF"));
				Hospede.setSobrenome(rs.getString("Sobrenome"));
				Hospede.setDataNasc(rs.getDate("data_nasc"));
				Hospede.setNacionalidade(rs.getString("Nacionalidade"));
				Hospede.setPronome(rs.getString("Pronome"));
				Hospede.setEmail(rs.getString("Email"));
				Hospede.setDataNasc(rs.getDate("DataNasc"));
				
				HospedagemQuartos.setIdHospedagensQuartos(IdHospedagemQuarto);
				HospedagemQuartos.setQuarto(Quarto);
				HospedagemQuartos.setHospedagem(Hosp);
				HospedagemQuartos.setHospede(Hospede);
				
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
