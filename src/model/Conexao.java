package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://da_java.mysql.dbaas.com.br/da_java";
    private static final String USUARIO = "da_java";
    private static final String SENHA = "Tecnicas*2023@";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao obter conexão com o banco de dados: " + e.getMessage());
        }
    }

    public static void fecharConexao(Connection conexao) throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }
}