package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class AlunoDAO extends GenericDAO<Aluno>{

    @Override
    protected Aluno construirObjeto(ResultSet rs) {
        Aluno aluno = null;
        
        try {
            
            aluno = new Aluno();
            aluno.setRaAluno(rs.getInt("RA_ALUNO"));
            aluno.setNomeAluno(rs.getString("NOME_ALUNO"));
            aluno.setDtNascAluno(rs.getString("DT_NASC_ALUNO"));
            
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            aluno.setEndereco(enderecoDAO.buscarPorRaAluno(aluno.getRaAluno()));
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aluno;
       
    }
    
    public ArrayList<Aluno> findByNome(String nome) {
    ArrayList<Aluno> alunosEncontrados = new ArrayList<>();
    
    String sql = "SELECT * FROM public.\"Aluno\" WHERE LOWER(\"NOME_ALUNO\") LIKE LOWER(?) ORDER BY \"NOME_ALUNO\"";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + nome + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                
                alunosEncontrados.add(construirObjeto(rs));
            }
        }
    } catch (SQLException e) {
        Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, e);
    }
    return alunosEncontrados;
}

    @Override
    public boolean salvar(Aluno aluno) {
        
        String sql = "INSERT INTO public.\"Aluno\"(" +
                    "\"RA_ALUNO\", \"NOME_ALUNO\", \"DT_NASC_ALUNO\")" +
                    "VALUES (?, ?, ?);";
            
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, aluno.getRaAluno());
            ps.setString(2, aluno.getNomeAluno());
            ps.setString(3, aluno.getDtNascAluno());
            ps.executeUpdate();
            ps.close();
            
            if (aluno.getEndereco() != null) {
                aluno.getEndereco().setRaAlunoFk(aluno.getRaAluno());
                new EnderecoDAO().salvar(aluno.getEndereco());
            }

            
            return true;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
                
    }

    @Override
    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE public.\"Aluno\" SET "
                    + "\"NOME_ALUNO\"=?, \"DT_NASC_ALUNO\"=? "
                    + "WHERE \"RA_ALUNO\" = ?";
            
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, aluno.getNomeAluno());
            ps.setString(2, aluno.getDtNascAluno());
            ps.setInt(3, aluno.getRaAluno());
            ps.executeUpdate();
            ps.close();
            
            if (aluno.getEndereco() != null) {
                new EnderecoDAO().atualizar(aluno.getEndereco());
            }
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean remover(int raAluno) {
        
        new EnderecoDAO().remover(raAluno);

        String sql = "DELETE FROM public.\"Aluno\" WHERE \"RA_ALUNO\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raAluno);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void gerarRelatorio(int raAluno) {
        JasperPrint printer = null;
        
        new EnderecoDAO().remover(raAluno);
        
        String sql = "DELETE FROM public.\"Aluno\" WHERE \"RA_ALUNO\" = ?";
        
        try {
            printer = JasperFillManager.fillReport("relatório/AlunoUnipar.jasper", null, conn);
        } catch (JRException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JasperViewer view = new JasperViewer(printer,false);
        view.setVisible(true);
    }
    
}
