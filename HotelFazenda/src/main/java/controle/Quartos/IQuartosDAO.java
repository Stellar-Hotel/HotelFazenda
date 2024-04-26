package controle.Quartos;

import java.util.ArrayList;

import modelo.Quartos;

public interface IQuartosDAO {

	public int inserirQuarto(Quartos end);

	public ArrayList<Quartos> ListarQuartos();

	public boolean atualizarQuarto(Quartos end);

	public boolean removerQuarto(Quartos end);

	public ArrayList<Quartos> buscarQuartoPorNumero(int numero);

}