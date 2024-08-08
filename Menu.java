import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private PatientDAO patientDAO = new PatientDAO();

    public void showMenu() {
        while (true) {
            System.out.println("\nHealthcare Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Delete Patient");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addPatient() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        Patient patient = new Patient(name, age, address, phone);
        patientDAO.addPatient(patient);
        System.out.println("Patient added successfully!");
    }

    private void viewAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        int id = scanner.nextInt();
        patientDAO.deletePatient(id);
        System.out.println("Patient deleted successfully!");
    }
}
