package model.post;

import model.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostJavalar {
    public static void inserirRelatorioJavalar(String nome, String matricula, String nomeArquivo, int bugPython,
                                              int bugJavascript, int bugRuby, int bugPHP, int bugCSharp, int bugCPlusPlus,
                                              int bugC, int devPython, int devJavascript, int devRuby, int devPHP, int devCSharp,
                                              int devCPlusPlus, int devC, int vPython, int vJavascript, int vRuby, int vPHP,
                                              int vCSharp, int vCPlusPlus, int vC, int dPython, int dJavascript, int dRuby,
                                              int dPHP, int dCSharp, int dCPlusPlus, int dC, int aPython, int aJavascript,
                                              int aRuby, int aPHP, int aCSharp, int aCPlusPlus, int aC, int bugQ1, int bugQ2,
                                              int bugQ3, int bugQ4, int devQ1, int devQ2, int devQ3, int devQ4) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = new Conexao().obterConexao();

            String sql = "INSERT INTO javalar " +
                    "(nome, matricula, nome_arquivo, bug_python, bug_javascript, bug_ruby, bug_php, bug_csharp, " +
                    "bug_cmais, bug_c, dev_python, dev_javascript, dev_ruby, dev_php, dev_csharp, dev_cmais, dev_c, " +
                    "v_python, v_javascript, v_ruby, v_php, v_csharp, v_cmais, v_c, d_python, d_javascript, d_ruby, " +
                    "d_php, d_csharp, d_cmais, d_c, a_python, a_javascript, a_ruby, a_php, a_csharp, a_cmais, a_c, " +
                    "bug_q1, bug_q2, bug_q3, bug_q4, dev_q1, dev_q2, dev_q3, dev_q4) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, matricula);
            preparedStatement.setString(3, nomeArquivo);
            preparedStatement.setInt(4, bugPython);
            preparedStatement.setInt(5, bugJavascript);
            preparedStatement.setInt(6, bugRuby);
            preparedStatement.setInt(7, bugPHP);
            preparedStatement.setInt(8, bugCSharp);
            preparedStatement.setInt(9, bugCPlusPlus);
            preparedStatement.setInt(10, bugC);
            preparedStatement.setInt(11, devPython);
            preparedStatement.setInt(12, devJavascript);
            preparedStatement.setInt(13, devRuby);
            preparedStatement.setInt(14, devPHP);
            preparedStatement.setInt(15, devCSharp);
            preparedStatement.setInt(16, devCPlusPlus);
            preparedStatement.setInt(17, devC);
            preparedStatement.setInt(18, vPython);
            preparedStatement.setInt(19, vJavascript);
            preparedStatement.setInt(20, vRuby);
            preparedStatement.setInt(21, vPHP);
            preparedStatement.setInt(22, vCSharp);
            preparedStatement.setInt(23, vCPlusPlus);
            preparedStatement.setInt(24, vC);
            preparedStatement.setInt(25, dPython);
            preparedStatement.setInt(26, dJavascript);
            preparedStatement.setInt(27, dRuby);
            preparedStatement.setInt(28, dPHP);
            preparedStatement.setInt(29, dCSharp);
            preparedStatement.setInt(30, dCPlusPlus);
            preparedStatement.setInt(31, dC);
            preparedStatement.setInt(32, aPython);
            preparedStatement.setInt(33, aJavascript);
            preparedStatement.setInt(34, aRuby);
            preparedStatement.setInt(35, aPHP);
            preparedStatement.setInt(36, aCSharp);
            preparedStatement.setInt(37, aCPlusPlus);
            preparedStatement.setInt(38, aC);
            preparedStatement.setInt(39, bugQ1);
            preparedStatement.setInt(40, bugQ2);
            preparedStatement.setInt(41, bugQ3);
            preparedStatement.setInt(42, bugQ4);
            preparedStatement.setInt(43, devQ1);
            preparedStatement.setInt(44, devQ2);
            preparedStatement.setInt(45, devQ3);
            preparedStatement.setInt(46, devQ4);

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
