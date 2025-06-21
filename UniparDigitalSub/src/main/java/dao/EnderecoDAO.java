package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Endereco;


public class EnderecoDAO extends GenericDAO<Endereco> {
    
    @Override
    protected Endereco construirObjeto(ResultSet rs) {
        Endereco endereco = null;
        try {
            endereco = new Endereco();
            endereco.setIdEndereco(rs.getInt("ID_ENDERECO"));
            endereco.setCep(rs.getString("CEP"));
            endereco.setLogradouro(rs.getString("LOGRADOURO"));
            endereco.setNumero(rs.getString("NUMERO"));
            endereco.setBairro(rs.getString("BAIRRO"));
            endereco.setCidade(rs.getString("CIDADE"));
            endereco.setUf(rs.getString("UF"));
            endereco.setRaAlunoFk(rs.getInt("RA_ALUNO_FK"));
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return endereco;
    }
    
    public Endereco buscarPorRaAluno(int raAluno) {
        String sql = "SELECT * FROM public.\"Endereco\" WHERE \"RA_ALUNO_FK\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raAluno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirObjeto(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public boolean salvar(Endereco endereco) {
        String sql = "INSERT INTO public.\"Endereco\"(" +
                     "\"CEP\", \"LOGRADOURO\", \"NUMERO\", \"BAIRRO\", \"CIDADE\", \"UF\", \"RA_ALUNO_FK\")" +
                     "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setInt(7, endereco.getRaAlunoFk());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean atualizar(Endereco endereco) {
        String sql = "UPDATE public.\"Endereco\" SET " +
                     "\"CEP\"=?, \"LOGRADOURO\"=?, \"NUMERO\"=?, \"BAIRRO\"=?, \"CIDADE\"=?, \"UF\"=? " +
                     "WHERE \"RA_ALUNO_FK\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getUf());
            ps.setInt(7, endereco.getRaAlunoFk());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(int raAlunoFk) {
        String sql = "DELETE FROM public.\"Endereco\" WHERE \"RA_ALUNO_FK\" = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, raAlunoFk);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
