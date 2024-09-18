package DTO;

public class CoordenadorDTO {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean coordenadorExiste;
    private boolean coordenadorCriado;

    public CoordenadorDTO() {

    }

    public CoordenadorDTO(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getCoordenadorExiste() {
        return coordenadorExiste;
    }

    public void setCoordenadorExiste(boolean coordenadorExiste) {
        this.coordenadorExiste = coordenadorExiste;
    }

    public boolean getCoordenadorCriado() {
        return coordenadorCriado;
    }

    public void setCoordenadorCriado(boolean coordenadorCriado) {
        this.coordenadorCriado = coordenadorCriado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
