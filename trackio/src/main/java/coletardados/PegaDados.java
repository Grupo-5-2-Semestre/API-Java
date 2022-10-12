package coletardados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import java.time.Instant;


public class PegaDados {
    
    public void getDadosLooca() {
    Looca looca = new Looca();
        
        Integer totalProcessos = looca.getGrupoDeProcessos().getTotalProcessos();
        Integer totalThreads = looca.getGrupoDeProcessos().getTotalThreads();
        
        Double usoProcessador = looca.getProcessador().getUso();
        Integer numeroCpusFisicas = looca.getProcessador().getNumeroCpusFisicas();
        Integer numeroCpusLogicas = looca.getProcessador().getNumeroCpusLogicas();
        Integer numeroPacotesFisicos = looca.getProcessador().getNumeroPacotesFisicos();
        
        Long tempoDeAtividade = looca.getSistema().getTempoDeAtividade();
        Instant inicializado = looca.getSistema().getInicializado();
        
        Long memoriaDisponivel = looca.getMemoria().getDisponivel();
        Long memoriaEmUso = looca.getMemoria().getEmUso();
        Long memoriaTotal = looca.getMemoria().getTotal();
        
        Temperatura temperatura = looca.getTemperatura();       
    }
    public Double pegaNumeroCpusFisica () {
        Looca looca = new Looca();

        return looca.getProcessador().getUso();
    }
}
