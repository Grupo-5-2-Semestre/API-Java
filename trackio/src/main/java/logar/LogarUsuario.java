package logar;

public class LogarUsuario {
    private String nomeUsuario, senhaUsuario;

    public LogarUsuario(String nomeUsuario, String senhaUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Override
    public String toString() {
        return "LogarUsuario{" + "nomeUsuario=" + nomeUsuario + ", senhaUsuario=" + senhaUsuario + '}';
    }
    
    
}
