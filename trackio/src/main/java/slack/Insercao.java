package slack;

import coletardados.PegaDados;

/**
 *
 * @author giova
 */
public class Insercao {

    private Double usoProcessador;
    private Integer dadosJsensorRpm;
    private Double memoriaTotal;
    private Long discoTotal;
    private Double memoriaEmUso;
    private Long discoEmUso;
    private Double temperatura;
    private Integer dadosJsensorTemp;

    PegaDados dados = new PegaDados();

    public Insercao() {
        this.usoProcessador = dados.getUsoProcessador();
        this.dadosJsensorRpm = dados.pegaDadosJSensorRpm();
        this.memoriaTotal = dados.getMemoriaTotal() / 1000000000;
        this.discoTotal = dados.getDiscoTotal() / 1000000000;
        this.memoriaEmUso = dados.getMemoriaEmUso() / 1000000000;
        this.discoEmUso = dados.getDiscoEmUso() / 1000000000;
        this.temperatura = dados.getTemperatura();
        this.dadosJsensorTemp = dados.pegaDadosJSensorTemp();
    }

    public Double getUsoProcessador() {
        return usoProcessador;
    }

    public void setUsoProcessador(Double usoProcessador) {
        this.usoProcessador = usoProcessador;
    }

    public Integer getDadosJsensorRpm() {
        return dadosJsensorRpm;
    }

    public void setDadosJsensorRpm(Integer dadosJsensorRpm) {
        this.dadosJsensorRpm = dadosJsensorRpm;
    }

    public Double getMemoriaTotal() {
        return memoriaTotal;
    }

    public void setMemoriaTotal(Double memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    public Long getDiscoTotal() {
        return discoTotal;
    }

    public void setDiscoTotal(Long discoTotal) {
        this.discoTotal = discoTotal;
    }

    public Double getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public void setMemoriaEmUso(Double memoriaEmUso) {
        this.memoriaEmUso = memoriaEmUso;
    }

    public Long getDiscoEmUso() {
        return discoEmUso;
    }

    public void setDiscoEmUso(Long discoEmUso) {
        this.discoEmUso = discoEmUso;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getDadosJsensorTemp() {
        return dadosJsensorTemp;
    }

    public void setDadosJsensorTemp(Integer dadosJsensorTemp) {
        this.dadosJsensorTemp = dadosJsensorTemp;
    }

}
