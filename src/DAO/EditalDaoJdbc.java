package src.DAO;

import src.DTO.EditalDeMonitoriaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EditalDaoJdbc implements SearchEditalDeMonitoria  {
	
	
	private Connection conn;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;


	@Override
	public EditalDeMonitoriaDTO adicionarEdital(EditalDeMonitoriaDTO edital) {
		
		String sql = "INSERT INTO Edital (numero, qtd_de_inscricao_por_aluno, situacao_do_edital, data_inicio, data_fim, peso_cre, peso_nota) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
            stmt.setInt(1, edital.getNumeroDoEdital());
            stmt.setInt(2, edital.getQtdDeInscricaoPorAluno());
            stmt.setString(3, edital.getSituacaoDoEdital());
            stmt.setDate(4, edital.getDataInicio());
            stmt.setDate(5, edital.getDataFim());
            stmt.setFloat(6, edital.getPesoCRE());
            stmt.setFloat(7, edital.getPesoNota());
            
            stmt.executeUpdate();
            System.out.println("Edital adicionado com sucesso!");
			
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
	public EditalDeMonitoriaDTO editarEdital(EditalDeMonitoriaDTO edital) {
		String sql = "UPDATE Edital SET codigo = ?, numero = ?, qtd_de_inscricao_por_aluno = ?, situacao_do_edital = ?, data_inicio = ?, data_fim = ?, peso_cre = ?, peso_nota = ? WHERE id = ?";
		
		try {
			conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, edital.getCodigo());
			stmt.setInt(2, edital.getNumeroDoEdital());
	        stmt.setInt(3, edital.getQtdDeInscricaoPorAluno());
	        stmt.setString(4, edital.getSituacaoDoEdital());
	        stmt.setDate(5, edital.getDataInicio());
	        stmt.setDate(6, edital.getDataFim());
	        stmt.setFloat(7, edital.getPesoCRE());
	        stmt.setFloat(8, edital.getPesoNota());
	        stmt.setInt(9, (int) edital.getId());
			
	        stmt.executeQuery();
	        System.out.println("Edital atualizado com sucesso!");
			
		}catch(SQLException e ) {
			e.printStackTrace();
		}finally {
			
			try {
				stmt.close();
				conn.close();
				
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
		
		return null;
	}
	
	// Recupera o edital po id
	@Override
	public EditalDeMonitoriaDTO verEdital(EditalDeMonitoriaDTO edital) {
		String sql = "SELECT * FROM Edital WHERE id = ?";
		EditalDeMonitoriaDTO editalRec = null;
		
		try {
			conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, (int) edital.getId());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
                edital = new EditalDeMonitoriaDTO();
                edital.setId(rs.getInt("id"));
                //edital.setCodigo(rs.getString("codigo"));
                edital.setNumeroDoEdital(rs.getInt("numero"));
                edital.setQtdDeInscricaoPorAluno(rs.getInt("qtd_de_inscricao_por_aluno"));
                edital.setSituacaoDoEdital(rs.getString("situacao_do_edital"));
                edital.setDataInicio(rs.getDate("data_inicio"));
                edital.setDataFim(rs.getDate("data_fim"));
                edital.setPesoCRE(rs.getFloat("peso_cre"));
                edital.setPesoNota(rs.getFloat("peso_nota"));
            }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
				
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
		return null;
	}

	@Override
	public EditalDeMonitoriaDTO excluirEdital(EditalDeMonitoriaDTO edital) {
		
		String sql = "DELETE FROM Edital WHERE id = ?";
		
		try {
			conn = JDBC.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,(int) edital.getId());
            stmt.executeUpdate();
            System.out.println("Edital removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	
        	try {
				stmt.close();
				conn.close();
				
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
		
		return null;
	}

	@Override
	// Listar todos os editais
	public List<EditalDeMonitoriaDTO> recuperarEditais(EditalDeMonitoriaDTO edital) {
		
		List<EditalDeMonitoriaDTO> editais = new ArrayList<>();
		
		String sql = "SELECT * FROM Edital";
		
		try {
			conn = JDBC.getConnection();
			stmt = (PreparedStatement) conn.createStatement();
			rs = stmt.executeQuery();
			
			 while (rs.next()) {
	                EditalDeMonitoriaDTO edital1 = new EditalDeMonitoriaDTO();
	                edital1.setId(rs.getInt("id"));
	                //edital1.setCodigo(rs.getString("codigo"));
	                edital1.setNumeroDoEdital(rs.getInt("numero"));
	                edital1.setQtdDeInscricaoPorAluno(rs.getInt("qtd_de_inscricao_por_aluno"));
	                edital1.setSituacaoDoEdital(rs.getString("situacao_do_edital"));
	                edital1.setDataInicio(rs.getDate("data_inicio"));
	                edital1.setDataFim(rs.getDate("data_fim"));
	                edital1.setPesoCRE(rs.getFloat("peso_cre"));
	                edital1.setPesoNota(rs.getFloat("peso_nota"));

	                editais.add(edital1);
	            }
			
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}


		return editais;
	}

}
