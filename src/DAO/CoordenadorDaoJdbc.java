package src.DAO;

import DTO.CoordenadorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordenadorDaoJdbc implements SearchCoordenador{
	
	private Connection conn;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	public CoordenadorDTO adicionarCoordenador (CoordenadorDTO coordenador) {

        String sql = "INSERT INTO Coordenador (nome, email, senha) VALUES (?, ?, ?)";
		 
		try {
			 
		    conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			 
			stmt.setString(1, coordenador.getNome());
	        stmt.setString(2, coordenador.getEmail());
	        stmt.setString(3, coordenador.getSenha());

	        stmt.executeUpdate();
	        System.out.println("Coordenador adicionado com sucesso!");
			 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
				
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				
		}
		
		return null;
	}

	public CoordenadorDTO editarCoordenador (CoordenadorDTO coordenador) {
        
        String sql = "UPDATE Coordenador SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try {
			 
		    conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			 
			stmt.setString(1, coordenador.getNome());
	        stmt.setString(2, coordenador.getEmail());
	        stmt.setString(3, coordenador.getSenha());
            stmt.setInt(4, coordenador.getId());

	        stmt.executeUpdate();
	        System.out.println("Coordenador adicionado com sucesso!");
			 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
				
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
				
		}

		return null;
	}
	
	public CoordenadorDTO verCoordenador (CoordenadorDTO coordenador) {

        String sql = "SELECT * FROM Coordenador WHERE id = ?";

        try {
			 
		    conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			 
			stmt.setInt(1, coordenador.getId());
            rs = stmt.executeQuery();

	        if (rs.next()) {
                coordenador.setId(rs.getInt("id"));
                coordenador.setNome(rs.getString("nome"));
                coordenador.setEmail(rs.getString("email"));
                coordenador.setSenha(rs.getString("senha"));
            }
			 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
				
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
		return coordenador;
	}
	
	public CoordenadorDTO excluirCoordenador (CoordenadorDTO coordenador){

        String sql = "DELETE FROM Coordenador WHERE id = ?";

        try {
			 
		    conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			 
			stmt.setInt(1, coordenador.getId());
            stmt.executeUpdate();
            System.out.println("Coordenador removido com sucesso!");
			 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
				
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}		
		}
        return null;
    }

}
