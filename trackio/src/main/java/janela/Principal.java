/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package janela;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import conexao.ConexaoUsuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import slack.Insercao;
import slack.SlackBd;

/**
 *
 * @author T-Gamer
 */
public class Principal extends javax.swing.JFrame {

    Looca looca;
    Insercao insercao = new Insercao();
    ConexaoUsuario conexaoUsuario = new ConexaoUsuario();

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.looca = new Looca();
        this.setUpOs();

    }

    private void setUpOs() {

        //data/hora atual
        LocalDateTime agora = LocalDateTime.now();


        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        String dataFormatada = formatterData.format(agora);


        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = formatterHora.format(agora);

        Sistema sistema = looca.getSistema();
        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(0, 101);


        DecimalFormat formatador = new DecimalFormat();

        dataHora1.setText(String.format("%s", sistema.getInicializado()));
        dataHora1.setForeground(Color.white);

        formatador.setMaximumFractionDigits(0);

        int delay = 5000; //milliseconds

        //dados sidebar
        id.setText(String.format("%s", sistema.getSistemaOperacional()));
        id.setForeground(Color.white);

        id.setText(String.format("%s", processador.getId()));

        process.setText(String.format("Temperatura: %.2f °C", insercao.getTemperatura()));
        process2.setText(String.format("Em uso: %.2f%%", insercao.getUsoProcessador()));
        mem.setText(String.format("Em uso: %.2f%% ", insercao.getMemoriaEmUso()));
        mem2.setText(String.format("Tamanho total: %.2f GB ", insercao.getMemoriaTotal()));
        disc.setText(String.format("Tamanho total: %d GB ", insercao.getDiscoTotal()));
        disc2.setText(String.format("Em uso: %d%%", insercao.getDiscoEmUso()));
        gpu.setText(String.format("Temperatura: %d °C", insercao.getDadosJsensorTemp()));
        gpu2.setText(String.format("Em uso: %d RPM", insercao.getDadosJsensorRpm()));

        ActionListener taskPerformer = new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                process.setText(String.format("Temperatura: %.2f °C", insercao.getTemperatura()));
                process2.setText(String.format("Em uso: %.2f%%", insercao.getUsoProcessador()));
                mem.setText(String.format("Em uso: %.2f%% ", insercao.getMemoriaEmUso()));
                mem2.setText(String.format("Tamanho total: %.2f GB ", insercao.getMemoriaTotal()));
                disc.setText(String.format("Tamanho total: %d GB ", insercao.getDiscoTotal()));
                disc2.setText(String.format("Em uso: %d%%", insercao.getDiscoEmUso()));
                gpu.setText(String.format("Temperatura: %d °C", insercao.getDadosJsensorTemp()));
                gpu2.setText(String.format("Em uso: %d RPM", insercao.getDadosJsensorRpm()));

                Insercao insersao = new Insercao();

                if (memoria.getEmUso() > 0) {
                    logGenerator.LogInfo.generateLogInfo("Info: A memória pode estar comprometida - API Trackio |" + " Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");
          
                } else {
                    logGenerator.LogInfo.generateLogInfo("Info: Memória livre para uso - API Trackio | "
                            + " Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");

                }
                if (processador.getUso() >= 80) {

                    logGenerator.LogInfo.generateLogInfo("Info: Excesso de processos em execução, seu sistema pode não funcionar corretamente -  API Trackio |  CPU: "
                            + processador.getNome() + " | Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");

                } else {
                    logGenerator.LogInfo.generateLogInfo("Info: Sistema executando de forma otimizada - API Trackio | "
                            + " Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");
                }
                enviarMensagens();
                conexaoUsuario.getSlackBd();
            }
        };
        new Timer(delay, taskPerformer).start();


        id.setText(String.format("%s logou", processador.getId()));

        if (memoria.getEmUso() < 20) {
           logGenerator.LogInfo.generateLogInfo("Info: A memória pode estar comprometida - API Trackio |" + " Data:" + dataFormatada + " Hora:" + horaFormatada + "\n");
     
            
        }  if(processador.getUso() >= 80){
         
               logGenerator.LogInfo.generateLogInfo("Info: Excesso de processos em execução, seu sistema pode não funcionar corretamente -  API Trackio |  CPU: " 
                       + processador.getNome()   + " | Data:" + dataFormatada + " Hora:" + horaFormatada + "\n" );
        }
      
    }

    public void enviarMensagens() {

        Insercao insersao = new Insercao();

        if (insersao.getMemoriaEmUso() > 0) { // Métrica > 80
            ramPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            JOptionPane.showMessageDialog(null, "A Memória RAM atingiu o nível critico,\n Contate o suporte!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }

        if (insersao.getTemperatura() >= 0) { // Métrica > 65 ou < 40
            cpuPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            JOptionPane.showMessageDialog(null, "O Processador atingiu o nível critico,\n Contate o suporte!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }

        if (insersao.getDadosJsensorRpm() > 0 || insersao.getDadosJsensorRpm() < 1000) { // Métrica > 2600
            gpuPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            JOptionPane.showMessageDialog(null, "A GPU atingiu o nível critico,\n Contate o suporte!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }

        if (insersao.getDiscoEmUso() > 0) { // Métrica > 90
            discoPanel.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            JOptionPane.showMessageDialog(null, "O Disco atingiu o nível critico,\n Contate o suporte!", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        jLabel18 = new javax.swing.JLabel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        id = new javax.swing.JLabel();
        logoTrackio = new javax.swing.JLabel();
        voltar = new javax.swing.JLabel();
        question = new javax.swing.JLabel();
        ramPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mem = new javax.swing.JLabel();
        mem2 = new javax.swing.JLabel();
        discoPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        disc = new javax.swing.JLabel();
        disc2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dataHora = new javax.swing.JLabel();
        dataHora1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 100), new java.awt.Dimension(0, 100), new java.awt.Dimension(32767, 100));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 200), new java.awt.Dimension(0, 200), new java.awt.Dimension(32767, 200));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        gpuPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        gpu = new javax.swing.JLabel();
        gpu2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cpuPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        process = new javax.swing.JLabel();
        process2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        jLabel18.setText("jLabel18");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(890, 560));

        kGradientPanel1.setForeground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setAlignmentX(50.0F);
        kGradientPanel1.setAlignmentY(50.0F);
        kGradientPanel1.setkEndColor(new java.awt.Color(0, 51, 102));
        kGradientPanel1.setkGradientFocus(900);
        kGradientPanel1.setkStartColor(new java.awt.Color(102, 0, 51));
        kGradientPanel1.setMaximumSize(new java.awt.Dimension(900, 700));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(100, 100));
        kGradientPanel1.setPreferredSize(new java.awt.Dimension(850, 500));

        id.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        id.setForeground(new java.awt.Color(255, 255, 255));
        id.setText("usuário ");

        logoTrackio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoMenorTrackio.png"))); // NOI18N
        logoTrackio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoTrackioMouseEntered(evt);
            }
        });

        voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btnVoltar.png"))); // NOI18N
        voltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                voltarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                voltarMouseEntered(evt);
            }
        });

        question.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/btnQuestion.png"))); // NOI18N
        question.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                questionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                questionMouseEntered(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ram.png"))); // NOI18N

        jLabel10.setText("RAM");
        jLabel10.setAlignmentX(100.0F);
        jLabel10.setAlignmentY(90.0F);
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        mem.setText("-");

        mem2.setText("jLabel16");

        javax.swing.GroupLayout ramPanelLayout = new javax.swing.GroupLayout(ramPanel);
        ramPanel.setLayout(ramPanelLayout);
        ramPanelLayout.setHorizontalGroup(
            ramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ramPanelLayout.createSequentialGroup()
                .addGroup(ramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ramPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mem2)
                            .addComponent(mem)))
                    .addGroup(ramPanelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ramPanelLayout.createSequentialGroup()
                .addGap(0, 69, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        ramPanelLayout.setVerticalGroup(
            ramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ramPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mem2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setText("Disco");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/disco.png"))); // NOI18N

        disc.setText("-");

        disc2.setText("-");

        javax.swing.GroupLayout discoPanelLayout = new javax.swing.GroupLayout(discoPanel);
        discoPanel.setLayout(discoPanelLayout);
        discoPanelLayout.setHorizontalGroup(
            discoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discoPanelLayout.createSequentialGroup()
                .addGroup(discoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(discoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(disc2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(discoPanelLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel13))
                    .addGroup(discoPanelLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel15))
                    .addGroup(discoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(disc, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        discoPanelLayout.setVerticalGroup(
            discoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discoPanelLayout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(disc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(disc2)
                .addGap(21, 21, 21))
        );

        jLabel15.getAccessibleContext().setAccessibleName("temperatura");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Desempenho");

        dataHora1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dataHora1.setText("jLabel19");

        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(3, 700));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bem vindo");

        gpuPanel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("GPU");

        gpu.setText("-");

        gpu2.setText("-");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/process.png"))); // NOI18N

        javax.swing.GroupLayout gpuPanelLayout = new javax.swing.GroupLayout(gpuPanel);
        gpuPanel.setLayout(gpuPanelLayout);
        gpuPanelLayout.setHorizontalGroup(
            gpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gpuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gpu2)
                    .addComponent(gpu))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(gpuPanelLayout.createSequentialGroup()
                .addGroup(gpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gpuPanelLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel11))
                    .addGroup(gpuPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gpuPanelLayout.setVerticalGroup(
            gpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gpuPanelLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(15, 15, 15)
                .addComponent(gpu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gpu2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Processador");

        process.setText("-");
        process.setMaximumSize(new java.awt.Dimension(20, 11));
        process.setMinimumSize(new java.awt.Dimension(10, 11));
        process.setPreferredSize(new java.awt.Dimension(20, 11));

        process2.setText("-");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/gpu.png"))); // NOI18N

        javax.swing.GroupLayout cpuPanelLayout = new javax.swing.GroupLayout(cpuPanel);
        cpuPanel.setLayout(cpuPanelLayout);
        cpuPanelLayout.setHorizontalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuPanelLayout.createSequentialGroup()
                .addGroup(cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cpuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(process2))
                    .addGroup(cpuPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel12))
                    .addGroup(cpuPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel7))
                    .addGroup(cpuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(process, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        cpuPanelLayout.setVerticalGroup(
            cpuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuPanelLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(process, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(process2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(voltar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(question)
                        .addGap(18, 18, 18))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(dataHora))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(dataHora1))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(id))
                            .addComponent(logoTrackio, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel5)
                        .addGap(804, 804, 804)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(70, 70, 70)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(discoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(filler3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(512, 512, 512))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(logoTrackio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dataHora1)
                .addGap(34, 34, 34)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(voltar)
                    .addComponent(question))
                .addGap(199, 199, 199))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(filler3, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                        .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(61, 61, 61)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(discoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cpuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voltarMouseClicked
        Principal principal = new Principal();

        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_voltarMouseClicked

    private void voltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_voltarMouseEntered
        voltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_voltarMouseEntered

    private void questionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionMouseEntered
        question.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_questionMouseEntered

    private void logoTrackioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoTrackioMouseEntered
        logoTrackio.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoTrackioMouseEntered

    private void questionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_questionMouseClicked
        new Informacoes().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_questionMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cpuPanel;
    private javax.swing.JLabel dataHora;
    private javax.swing.JLabel dataHora1;
    private javax.swing.JLabel disc;
    private javax.swing.JLabel disc2;
    private javax.swing.JPanel discoPanel;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JLabel gpu;
    private javax.swing.JLabel gpu2;
    private javax.swing.JPanel gpuPanel;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel logoTrackio;
    private javax.swing.JLabel mem;
    private javax.swing.JLabel mem2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private javax.swing.JLabel process;
    private javax.swing.JLabel process2;
    private javax.swing.JLabel question;
    private javax.swing.JPanel ramPanel;
    private javax.swing.JLabel voltar;
    // End of variables declaration//GEN-END:variables

}
