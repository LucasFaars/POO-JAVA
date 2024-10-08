package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.time.format.DateTimeParseException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Model.Evento;

public class EventoDAO {

    private static final String drive = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/Calendario_Eventos";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";

    public Connection getEventoDAO() throws SQLException {
        try {
            Class.forName(drive);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void CriarTabela() {
        String comando_sql = "CREATE TABLE IF NOT EXISTS eventos (" +
                             "id SERIAL PRIMARY KEY, " +
                             "nome VARCHAR(100) NOT NULL, " +
                             "descricao TEXT, " +
                             "local VARCHAR(100), " +
                             "data DATE, " +
                             "horario TIME)";

        try (Connection com = getEventoDAO(); PreparedStatement ps = com.prepareStatement(comando_sql)) {
            ps.executeUpdate();
            System.out.println("Tabela Eventos criada com sucesso ou já existe.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Evento> ConsultarTodosEventos() {
        List<Evento> eventos = new ArrayList<>();

        String comando_sql = "SELECT * FROM Eventos";

        try (Connection com = getEventoDAO(); PreparedStatement ps = com.prepareStatement(comando_sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String local = rs.getString("local");
                String data = rs.getString("data");
                String horario = rs.getString("horario");

                eventos.add(new Evento(id, nome, descricao, local, data, horario));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    public void PublicarEvento(Evento evento) {
        String sql = "INSERT INTO eventos (nome, descricao, local, data, horario) VALUES (?, ?, ?, ?, ?)";
    
        try (Connection conn = getEventoDAO(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, evento.getNome());
            pstmt.setString(2, evento.getDescricao());
            pstmt.setString(3, evento.getLocal());
    
            // Converte a data de dd/MM/yyyy para yyyy-MM-dd
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = sdf.parse(evento.getData());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pstmt.setDate(4, sqlDate);
    
            // Converte o horário para o formato HH:mm:ss sem segundos
            String horario = evento.getHorario() + ":00"; // Adiciona os segundos
            pstmt.setTime(5, java.sql.Time.valueOf(horario)); // Converte String para Time
    
            pstmt.executeUpdate();
            System.out.println("Evento Cadastrado com Sucesso!");
    
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar evento: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    

    public void AtualizarEvento(Evento evento) {
        String comando_sql = "UPDATE Eventos SET nome = ?, descricao = ?, local = ?, data = ?, horario = ? WHERE id = ?";

        try (Connection com = getEventoDAO(); PreparedStatement ps = com.prepareStatement(comando_sql)) {
            ps.setString(1, evento.getNome());
            ps.setString(2, evento.getDescricao());
            ps.setString(3, evento.getLocal());
            ps.setString(4, evento.getData());
            ps.setString(5, evento.getHorario());
            ps.setInt(6, evento.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ExcluirEvento(int id) {
        String comando_sql = "DELETE FROM Eventos WHERE id = ?";

        try (Connection com = getEventoDAO(); PreparedStatement ps = com.prepareStatement(comando_sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
