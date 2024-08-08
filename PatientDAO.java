import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class PatientDAO {
	public void addPatient(Patient patient) {
		String query = "INSERT INTO patients (name, age, address, phone) VALUES(?,?,?,?)";
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setString(1, patient.getName());
			stmt.setInt(2, patient.getAge());
			stmt.setString(3, patient.getAddress());
			stmt.setString(4, patient.getPhone());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Patient> getAllPatients(){
		List<Patient> patients = new ArrayList<>();
		String query = "SELECT * FROM patients";
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery()){
			while(rs.next()) {
				Patient p = new Patient(rs.getString("name"),rs.getInt("age"),rs.getString("address"),rs.getString("phone"));
				p.setId(rs.getInt("id"));
				patients.add(p);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}
	public void deletePatient(int id) {
		String query = "DELETE FROM patients WHERE id=?";
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)){
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
