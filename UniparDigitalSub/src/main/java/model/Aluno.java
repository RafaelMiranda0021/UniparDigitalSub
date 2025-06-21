package model;


public class Aluno {
    
    private int raAluno;
    private String nomeAluno;
    private String dtNascAluno;
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Aluno() {
    }

    public int getRaAluno() {
        return raAluno;
    }

    public void setRaAluno(int raAluno) {
        this.raAluno = raAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getDtNascAluno() {
        return dtNascAluno;
    }

    public void setDtNascAluno(String dtNascAluno) {
        this.dtNascAluno = dtNascAluno;
    }
    
    
    
}
