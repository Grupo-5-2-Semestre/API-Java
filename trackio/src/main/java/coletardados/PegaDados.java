package coletardados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.time.Instant;
import java.util.List;


public class PegaDados {
    Looca looca = new Looca();
    
    private Integer totalProcessos = looca.getGrupoDeProcessos().getTotalProcessos();
    private Integer totalThreads = looca.getGrupoDeProcessos().getTotalThreads();
        
    private Double usoProcessador = looca.getProcessador().getUso();
    private Integer numeroCpusFisicas = looca.getProcessador().getNumeroCpusFisicas();
    private Integer numeroCpusLogicas = looca.getProcessador().getNumeroCpusLogicas();
    private Integer numeroPacotesFisicos = looca.getProcessador().getNumeroPacotesFisicos();

    private Long tempoDeAtividade = looca.getSistema().getTempoDeAtividade();
    private Instant inicializado = looca.getSistema().getInicializado();

    private Long memoriaDisponivel = looca.getMemoria().getDisponivel();
    private Long memoriaEmUso = looca.getMemoria().getEmUso();
    private Long memoriaTotal = looca.getMemoria().getTotal();

    private Temperatura temperatura = looca.getTemperatura();  

    public Double getTotalProcessos() {
        return totalProcessos.doubleValue();
    }

    public Double getTotalThreads() {
        return totalThreads.doubleValue();
    }

    public Double getUsoProcessador() {
        return usoProcessador;
    }

    public Double getNumeroCpusFisicas() {
        return numeroCpusFisicas.doubleValue();
    }

    public Double getNumeroCpusLogicas() {
        return numeroCpusLogicas.doubleValue();
    }

    public Double getNumeroPacotesFisicos() {
        return numeroPacotesFisicos.doubleValue();
    }

    public Double getTempoDeAtividade() {
        return tempoDeAtividade.doubleValue();
    }

    public Instant getInicializado() {
        return inicializado;
    }

    public Double getMemoriaDisponivel() {
        return memoriaDisponivel.doubleValue();
    }

    public Double getMemoriaEmUso() {
        return memoriaEmUso.doubleValue();
    }

    public Double getMemoriaTotal() {
        return memoriaTotal.doubleValue();
    }

    public Double getTemperatura() {
        return temperatura.getTemperatura();
    }
    
    public Integer pegaDadosJSensor(){
        Double tempGpu = 0.0;
        Components components = JSensors.get.components();

        List<Gpu> gpus = components.gpus;
        
        if (gpus != null) {
            for (final Gpu gpu : gpus) {
                
                System.out.println("Found CPU component: " + gpu.name);
                if (gpu.sensors != null) {
                  System.out.println("Sensors: ");

                  //Print temperatures
                  List<Temperature> temps = gpu.sensors.temperatures;
                  for (final Temperature temp : temps) {
                      
                      System.out.println(temp.name + ": " + temp.value + " C");
                      tempGpu = temp.value;
                  }

                  //Print fan speed
                  List<Fan> fans = gpu.sensors.fans;
                  for (final Fan fan : fans) {
                      System.out.println(fan.name + ": " + fan.value + " RPM");
                  }

                  List<Load> loads = gpu.sensors.loads;
                  for (final Load load : loads) {
                      System.out.println(load.name + ": " + load.value + " RPM");
                  }
                }
            }
        }
        return tempGpu.intValue();
    }
};
