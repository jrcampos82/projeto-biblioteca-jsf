package br.edu.ifms.biblioteca.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifms.biblioteca.bd.ConexaoMySQL;
import br.edu.ifms.biblioteca.model.Acervo;
import br.edu.ifms.biblioteca.model.Livro;


// CRUD
public class AcervoDAO implements Serializable {
	
	private Connection conn = null;
	
	public AcervoDAO() {
		ConexaoMySQL conexao = new ConexaoMySQL();
		conn = conexao.getConnection();
	}
	
	public void salvar(Acervo acervo) {

        
		String sql = "INSERT INTO tb_acervo (id_livro, quantidade) VALUES (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acervo.getLivro().getId()); // pegar o obj livro primeiro, depois o id do livro
			ps.setInt(2, acervo.getQuantidade());
			
			
			int resultado = ps.executeUpdate();
			
			if(resultado > 0) {
				System.out.println("Acervo inserido com sucesso!");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	public List<Acervo> selecionarTodos() {
		List<Acervo> acervos = new ArrayList<Acervo>();
		
		String sql = "SELECT * FROM tb_usuarios";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			// ex: 12 reg
			while(rs.next()) {
				Acervo acervo = new Acervo();
                int idLivro = rs.getInt(1);
                Livro l = localizarLivro(idLivro);
				acervo.setLivro(l);
				acervo.setQuantidade(rs.getInt("quantidade"));
				acervo.setId(rs.getInt("id"));

				acervos.add(acervo);
				
			}
			return acervos;
			
		} catch (SQLException e) {
			
			System.out.println("Erro ao consultar os acervos no BD");
			e.printStackTrace();
		}
		return null;
		
	}
	
	private Livro localizarLivro(int idLivro) {
        String sql = "SELECT * FROM tb_usuarios WHERE id_livro = ?";
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idLivro); 
			ResultSet rs = ps.executeQuery(sql);
			
			// ex: 12 reg
			while(rs.next()) {
				Livro l = new Livro();
                l.setNome(rs.getString("nome"));
                l.setAutor(rs.getString("autor"));
                l.setEditora(rs.getString("editora"));
                l.setCodigoLivro(rs.getString("codLivro"));
                l.setAno(rs.getInt("ano"));

                return l;

            }
        } catch (SQLException e) {
			
            System.out.println("Erro ao consultar os acervos no BD");
            e.printStackTrace();
        }

        return null;
    }

    public void deletar(int id) {
        

		String sql = "DELETE FROM tb_usuarios WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rowDelete = ps.executeUpdate();
			if(rowDelete > 0) {
				System.out.println("Usuário deletado!!!");
			} 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void atualizar(Acervo acervo) {
		String sql = "UPDATE tb_acervo SET id_livro = ?, quantidade = ? WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, acervo.getLivro().getId()); // pegar o obj livro primeiro, depois o id do livro
			ps.setInt(2, acervo.getQuantidade());
            ps.setInt(3, acervo.getId());
			
			int rowUpdate = ps.executeUpdate();
			
			if(rowUpdate > 0) {
				System.out.println("Usuário atualizado!");
			} 
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
