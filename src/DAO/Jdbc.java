package  DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import DTO.AlunoDTO;
import DTO.CoordenadorDTO;
import DTO.EditalDeMonitoriaDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBC implements SearchAluno, SearchCoordenador,SearchEditalDeMonitoria{

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    LocalDate dataAtual = LocalDate.now();

    public Connection getConnection() {
        if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url,props);
			}
			catch (SQLException e ) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
    }
    // Método para carregar as propriedades do db.properties
    private Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs); //ler e guarda os dados do arv properties no obj props
			return props;
		}
		catch(IOException e ) {
			throw new DbException(e.getMessage());
		}
    }

    @Override
    public EditalDeMonitoriaDTO adicionarEdital(EditalDeMonitoriaDTO edital) {
        
        try {
            String sqlCheck = "SELECT * FROM editais WHERE id = ? OR numero_do_edital = ?";
            stmt = conn.prepareStatement(sqlCheck);
            stmt.setInt(1, edital.getId());
            stmt.setString(2, edital.getNumeroDoEdital());
            rs = stmt.executeQuery();

            // Se encontrar um edital existente, retorna o objeto sem inserção
            if (rs.next()) {
                System.out.println("Edital já existe com o mesmo ID ou número de edital.");
                edital.setEditalExiste(true); // Definindo que o edital já existe
                return edital;
            }
            // Inserir novo edital se não existir
            String sqlInsert = "INSERT INTO editais (numero_do_edital, qtd_de_inscricao_por_aluno, data_inicio, data_fim, peso_cre, peso_nota, disciplinas) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, edital.getNumeroDoEdital());
            stmt.setInt(2, edital.getQtdDeInscricaoPorAluno());
            stmt.setDate(3, java.sql.Date.valueOf(edital.getDataInicio()));
            stmt.setDate(4, java.sql.Date.valueOf(edital.getDataFim()));
            stmt.setFloat(5, edital.getPesoCRE());
            stmt.setFloat(6, edital.getPesoNota());
            stmt.setString(7, edital.getDisciplinas()); // Supondo que disciplinas sejam armazenadas como string

            int rowsInserted = stmt.executeUpdate();

            // Verificar se a inserção foi bem-sucedida
            if (rowsInserted > 0) {
                System.out.println("Edital adicionado com sucesso!");
                edital.setEditalExiste(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
             // Fechando recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return edital;
    }

    @Override
    public EditalDeMonitoriaDTO editarEdital(EditalDeMonitoriaDTO edital) {
        try{
            // Verificar se o edital existe
            String sqlCheck = "SELECT * FROM editais WHERE id = ?";
            stmt = conn.prepareStatement(sqlCheck);
            stmt.setInt(1, edital.getId());
            rs = stmt.executeQuery();

            // Se o edital não for encontrado, retornar o DTO sem alterações
            if (!rs.next()) {
                System.out.println("Edital não encontrado.");
                return edital;
            }

            // Verificar as condições de edição
            LocalDate dataInicio = rs.getDate("data_inicio").toLocalDate();
            LocalDate dataFim = rs.getDate("data_fim").toLocalDate();
            int qtdInscricoesExistente = rs.getInt("qtd_de_inscricao_por_aluno");
            if (edital.getDataInicio().isAfter(dataAtual) || edital.getDataInicio().isEqual(dataAtual)) {
                if (edital.getDataFim().isAfter(dataAtual) || edital.getDataFim().isEqual(dataAtual)) {
                    if (Integer.parseInt(edital.getQtdDeInscricaoPorAluno()) >= qtdInscricoesExistente) {
    
                        // Query para atualizar o edital
                        String sqlUpdate = "UPDATE editais SET numero_do_edital = ?, qtd_de_inscricao_por_aluno = ?, situacao_do_edital = ?, "
                                + "data_inicio = ?, data_fim = ?, peso_cre = ?, peso_nota = ?, disciplinas = ? WHERE id = ?";
    
                        stmt = conn.prepareStatement(sqlUpdate);
                        stmt.setString(1, edital.getNumeroDoEdital());
                        stmt.setInt(2, Integer.parseInt(edital.getQtdDeInscricaoPorAluno()));
                        stmt.setString(3, edital.getSituacaoDoEdital());
                        stmt.setDate(4, java.sql.Date.valueOf(edital.getDataInicio()));
                        stmt.setDate(5, java.sql.Date.valueOf(edital.getDataFim()));
                        stmt.setFloat(6, edital.getPesoCRE());
                        stmt.setFloat(7, edital.getPesoNota());
                        stmt.setString(8, edital.getDisciplinas());
                        stmt.setInt(9, edital.getId());
    
                        int rowsUpdated = stmt.executeUpdate();
    
                        // Verificar se a atualização foi bem-sucedida
                        if (rowsUpdated > 0) {
                            System.out.println("Edital atualizado com sucesso!");
                            edital.setEditalExiste(true);
                        } else {
                            System.out.println("Erro ao atualizar o edital.");
                        }
    
                        return edital;
                    }
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return edital;
    }

    @Override
    public EditalDeMonitoriaDTO verEdital(int editalId) {
        
        EditalDeMonitoriaDTO edital = new EditalDeMonitoriaDTO();

        try {
            // Query para buscar o edital pelo ID
            String sql = "SELECT * FROM editais WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, editalId);
    
            // Executar a consulta
            rs = stmt.executeQuery();
    
            // Se o edital for encontrado, preencher o objeto EditalDeMonitoriaDTO
            if (rs.next()) {
                edital.setId(rs.getInt("id"));
                edital.setNumeroDoEdital(rs.getString("numero_do_edital"));
                edital.setQtdDeInscricaoPorAluno(rs.getInt("qtd_de_inscricao_por_aluno"));
                edital.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                edital.setDataFim(rs.getDate("data_fim").toLocalDate());
                edital.setPesoCRE(rs.getFloat("peso_cre"));
                edital.setPesoNota(rs.getFloat("peso_nota"));
                edital.setDisciplinas(rs.getString("disciplinas")); // Supondo que disciplinas sejam armazenadas como string
                edital.setSituacaoDoEdital(rs.getString("situacao_do_edital"));
                edital.setEditalExiste(true); // Definindo que o edital foi encontrado
    
                System.out.println("Edital encontrado com sucesso!");
    
            } else {
                System.out.println("Edital não encontrado.");
                edital.setEditalExiste(false);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn!= null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return edital;

    }
    // Excluir Edital de Monitoria no Banco de Dados
    @Override
    public EditalDeMonitoriaDTO excluirEdital(int editalId) {
        
        EditalDeMonitoriaDTO edital = new EditalDeMonitoriaDTO();

        try {
            // Verificar se o edital existe
            String sqlCheck = "SELECT * FROM editais WHERE id = ?";
            stmt = conn.prepareStatement(sqlCheck);
            stmt.setInt(1, editalId);
            rs = stmt.executeQuery();
    
            // Se o edital não for encontrado, retornar DTO com editalNãoExistente
            if (!rs.next()) {
                System.out.println("Edital não encontrado.");
                edital.setEditalExiste(false);
                return edital;
            }
    
            // Excluir o edital
            String sqlDelete = "DELETE FROM editais WHERE id = ?";
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setInt(1, editalId);
    
            int rowsDeleted = stmt.executeUpdate();
    
            // Verificar se a exclusão foi bem-sucedida
            if (rowsDeleted > 0) {
                System.out.println("Edital excluído com sucesso!");
                edital.setEditalExiste(true);
            } else {
                System.out.println("Erro ao excluir o edital.");
                edital.setEditalExiste(false);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return edital;
    }

    @Override
    public AlunoDTO adicionarAluno(AlunoDTO aluno) {
        // Verificando se o Aluno já existe no Banco de dados
        if (alunoExiste(aluno).getAlunoExiste()) {
            return aluno; // Preciso ajeitar este método
        }

        try {
            // Query de inserção
            String sql = "INSERT INTO alunos (nome, matricula, email, senha, sexo) VALUES (?, ?, ?, ?, ?)";

            // Preparando a query
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getSenha());
            stmt.setString(5, aluno.getSexo());

            // Executando a inserção
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Aluno inserido com sucesso!");
                aluno.setAlunoCriado(true);
        }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // Fechando recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  aluno;   
    }

    @Override
    public AlunoDTO excluirAluno(AlunoDTO aluno) {
        try {
            // Query para excluir o aluno pela matrícula
            String sql = "DELETE FROM alunos WHERE matricula = ?";

            // Preparando a query
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getMatricula());

            // Executando a exclusão
            int rowsDeleted = stmt.executeUpdate();

            // Verificando se algum aluno foi excluído
            if (rowsDeleted > 0) {
                aluno.setAlunoExiste(true);
                System.out.println("Aluno excluído com sucesso!");
            } else {
                aluno.setAlunoExiste(false);
                System.out.println("Aluno não encontrado para exclusão.");
        }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // Fechando recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
             e.printStackTrace();
            }
        }
        return aluno;
    }

    @Override
    public AlunoDTO alunoExiste(AlunoDTO aluno) {
        // Verificando se o Aluno já existe no Banco de dados
        if (alunoExiste(aluno).getAlunoExiste()) {
            return aluno; 
        }

        try {
            // Query de inserção
            String sql = "INSERT INTO alunos (nome, matricula, email, senha, sexo) VALUES (?, ?, ?, ?, ?)";

            // Preparando a query
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getSenha());
            stmt.setString(5, aluno.getSexo());

            // Executando a inserção
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Aluno inserido com sucesso!");
                aluno.setAlunoCriado(true);
        }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // Fechando recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  aluno;  
    }

    @Override
    public AlunoDTO editarAluno(AlunoDTO aluno) {
        try {
            // Query para atualizar os dados do aluno com base na matrícula
           String sql = "UPDATE alunos SET nome = ?, email = ?, senha = ?, sexo = ? WHERE matricula = ?";
           // Preparando a query
           stmt = conn.prepareStatement(sql);
           stmt.setString(1, aluno.getNome());
           stmt.setString(2, aluno.getEmail());
           stmt.setString(3, aluno.getSenha());
           stmt.setString(4, aluno.getSexo());
           stmt.setString(5, aluno.getMatricula());

           // Executando a atualização
           int rowsUpdated = stmt.executeUpdate();

           // Verificando se algum aluno foi atualizado
           if (rowsUpdated > 0) {
               aluno.setAlunoExiste(true);
               System.out.println("Dados do aluno atualizados com sucesso!");
           } else {
               aluno.setAlunoExiste(false);
               System.out.println("Aluno não encontrado para atualização.");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally{
           // Fechando recursos
           try {
               if (stmt != null) stmt.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       return aluno;
    }

    @Override
    public AlunoDTO listarAlunos(AlunoDTO aluno) {
        List<AlunoModel> listaDeAlunos = new ArrayList<>();

        try {
            // Query para selecionar todos os alunos
            String sql = "SELECT * FROM alunos";

            // Preparando e executando a query
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Iterando sobre os resultados e criando a lista de AlunoModel
            while (rs.next()) {
                AlunoModel a = new AlunoModel();
                a.setNome(rs.getString("nome"));
                a.setMatricula(rs.getString("matricula"));
                a.setEmail(rs.getString("email"));
                a.setSenha(rs.getString("senha"));
                a.setSexo(rs.getString("sexo"));

                // Adicionando o aluno à lista
                listaDeAlunos.add(a);
            }

            // Configurar a lista de alunos no DTO
            aluno.setAlunos(listaDeAlunos);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            // Fechando recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aluno;
    }

    @Override
    public AlunoDTO loginAluno(AlunoDTO aluno) {
        
        try {

            // Prepara a query SQL para verificar o login do aluno
            String sql = "SELECT email, senha FROM Alunos WHERE email = ? AND senha = ?";
            stmt = connection.prepareStatement(sql);

            // Define os parâmetros da query
            stmt.setString(1, aluno.getEmail());
            stmt.setString(2, aluno.getSenha());

            // Executa a query
            rs = stmt.executeQuery();

            // Verifica se o aluno foi encontrado
            if (rs.next()) {
                aluno.setAlunoExiste(true);
            } else {
                aluno.setAlunoExiste(false);
            }

            return aluno;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //Fechando os recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    
}
