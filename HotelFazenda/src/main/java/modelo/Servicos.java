package modelo;


public class Servicos {
    private int Id_Servicos;
    private Float Preco_Servico;
    private String Nome_Servico;
    public int getId_Servicos() {
        return Id_Servicos;
    }
    public void setId_Servicos(int id_Servicos) {
        Id_Servicos = id_Servicos;
    }
    public Float getPreco_Servico() {
        return Preco_Servico;
    }
    public void setPreco_Servico(Float preco_Servico) {
        Preco_Servico = preco_Servico;
    }
    public String getNome_Servico() {
        return Nome_Servico;
    }
    public void setNome_Servico(String nome_Servico) {
        Nome_Servico = nome_Servico;
    }
}
