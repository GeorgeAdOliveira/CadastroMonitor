package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.DTO.AlunoDTO;

public class AlunoDaoJdbc implements SearchAluno{
	
	private Connection conn;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@Override
	public AlunoDTO adicionarAluno(AlunoDTO aluno) {
		
        String sql = "INSERT INTO Aluno (nome, matricula, email, senha, sexo) VALUES (?, ?, ?, ?, ?)";
        
        try {
        	conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getSenha());
            stmt.setString(5, aluno.getSexo());
            
            stmt.executeUpdate();
            System.out.println("Aluno adicionado com sucesso!");
        	
        } catch (SQLException e) {
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

	@Override
	public AlunoDTO excluirAluno(AlunoDTO aluno) {
		
		String sql = "DELETE FROM Aluno WHERE id = ?";
		
		try {
			conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, aluno.getId());
			stmt.executeQuery();
			System.out.println("Alunos removido com sucesso!");
			
		} catch (SQLException e) {
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

	@Override
	public AlunoDTO editarAluno(AlunoDTO aluno) {
		
        String sql = "UPDATE Aluno SET nome = ?, matricula = ?, email = ?, senha = ?, sexo = ? WHERE id = ?";
        
        try {
        	conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getSenha());
            stmt.setString(5, aluno.getSexo());
            stmt.setInt(6, aluno.getId());

            stmt.executeUpdate();
            System.out.println("Aluno atualizado com sucesso!");
        	
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

	@Override
	public List<AlunoDTO> listarAlunos(AlunoDTO aluno) {
		List<AlunoDTO> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        
        try {
        	conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			while (rs.next()) {
                AlunoDTO aluno1 = new AlunoDTO();
                aluno1.setId(rs.getInt("id"));
                aluno1.setNome(rs.getString("nome"));
                aluno1.setMatricula(rs.getString("matricula"));
                aluno1.setEmail(rs.getString("email"));
                aluno1.setSenha(rs.getString("senha"));
                aluno1.setSexo(rs.getString("sexo"));

                alunos.add(aluno1);
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
        
		return alunos;
	}

	@Override
	public AlunoDTO recuperarPorMatricula(AlunoDTO aluno) {
		// TODO Auto-generated method stub
		return null;
	}

}
