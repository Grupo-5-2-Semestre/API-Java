package com.mycompany.trackio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexao {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/trackio", "usuario", "senha");
            ResultSet rsCliente = conexao.createStatement().executeQuery("select * from usuario");
            
            while (rsCliente.next()) {
                System.out.println("Nome: " + rsCliente.getString("nomeUsuario"));
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver do banco de dados não localizado");
        } catch (SQLException ex) {
            System.out.println("ERRO ao acessar o banco");
        }
    }
}
