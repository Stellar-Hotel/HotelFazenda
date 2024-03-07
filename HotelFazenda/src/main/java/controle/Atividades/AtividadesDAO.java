package controle.Atividades;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import controle.Conexao;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.Funcionarios;

public class AtividadesDAO implements IAtividadesDAO

{
	private static AtividadesDAO instancia;

	public static AtividadesDAO getInstancia() {
		if (instancia == null) {
			instancia = new AtividadesDAO();
		}

		return instancia;
	}
	private AtividadesDAO() {}
	@Override
	public ArrayList<Atividades> ListarAtividades() {
		
		//ArrayList Que vai ser retornado
		ArrayList<Atividades> atividades= new ArrayList<Atividades>();
		
		//Comando pro MySQL
		String SQL = "SELECT * FROM Atividades";
		
		//Método pra fazer a conexão
		Conexao con= Conexao.getConexao();
		Connection conBD= con.conectar();
		
		try {
			PreparedStatement Ps=conBD.prepareStatement(SQL);
			
			ResultSet Rs= Ps.executeQuery();
			while (Rs.next()) {
				Atividades At=new Atividades();
				
				int IdAtividade= Rs.getInt("id_atividade");
				int RestricaoIdade= Rs.getInt("restricao_idade");
				String Horario=Rs.getString("horario");
				String HorarioFim=Rs.getString("horario_fim");
				Funcionarios Funcionario=new Funcionarios();
				String NomeAtividade=Rs.getString("nome_atividade");
				Date Data=Rs.getDate("data");
				
				//Tem que preencher os atributos do objeto funcionário
				At.setIdAtividade(IdAtividade);
				At.setRestricaoIdade(RestricaoIdade);
				At.setHorario(Horario);
				At.setHorarioFim(HorarioFim);
				At.setFuncionario(Funcionario);
				At.setNomeAtividade(NomeAtividade);
				At.setData(Data);
				atividades.add(At);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.fecharConexao();
		}
	
		//Return da arraylist
		return atividades;
	}

	@Override
	public boolean AtualizarAtividades(Atividades Ativ) {


        //Conexâo SQl a ser executada
        String SQL = "UPDATE atividades SET NomeAtividade  = ? WHERE IdAtividade = ?";
        
        //abre a conexão e cria a "parte de conexão" com MYSQL
        Conexao con= Conexao.getInstancia();
        Connection conBD= con.conectar();
        
        int retorno = 0;
        try {
            PreparedStatement ps = conBD.prepareStatement(SQL);
            ps.setString(1, Ativ.getNomeAtividade());
            ps.setInt(2, Ativ.getIdAtividade());
            ps.setString(3, Ativ.getNomeAtividade());
            ps.setInt(4, Ativ.getRestricaoIdade());
            ps.setDate(5, Ativ.getData());
            ps.setInt(6, Ativ.getFuncionario().getFuncionarioId());
            
            
            retorno = ps.executeUpdate();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            
        }
    
    return (retorno == 0 ? false :true);
}

	

	@Override
	public boolean RemoverAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Atividades BuscarAtividadesPorLocal(String Local) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int InserirAtividades(Atividades Ativ) {
		// TODO Auto-generated method stub
		String SQL= "INSERT INTO Atividades(horario,horario_fim,funcionario_id,restricao_idade,nome_atividade,data) VALUES (?,?,?,?,?,?)";
		//Método pra fazer a conexão com o banco
			Conexao con= Conexao.getConexao();
			Connection conBD= con.conectar();
			
			
		int ChavePrimariaGerada= Integer.MIN_VALUE;//variável que vai guardar a chave que vai ser gerada automaticamente, se não for autogerado não precisa disso
		
		
			try {
				PreparedStatement Ps= conBD.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);//se for um insert já conhcendo a chave primária não adcionar o Statement.RETURN_GENERATED_KEYS
				Ps.setString(1, Ativ.getHorario());
				Ps.setString(2, Ativ.getHorarioFim());
				Ps.setInt(3, Ativ.getFuncionario().getFuncionarioId());
				Ps.setInt(4, Ativ.getRestricaoIdade());
				Ps.setString(5, Ativ.getNomeAtividade());
				Ps.setDate(6, Ativ.getData());
				
				/*
				 * se for um insert sem gerar chave primária automaticamente não usar a parte de baixo 
				 */
				ResultSet Rs= Ps.executeQuery();
				if(Rs!=null) {
					ChavePrimariaGerada= Rs.getInt(1);
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				con.fecharConexao();
			}
		
		return ChavePrimariaGerada;
	}
	
}
