
// Package and import statements
package com.hospitalmanagement;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Model Layer
class Doctor {
    private int id;
    private String name;
    private String specialization;
    private String contactNumber;
    private int yearsOfExperience;
    private boolean isAvailable;

    public Doctor(int id, String name, String specialization, String contactNumber, int yearsOfExperience, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.contactNumber = contactNumber;
        this.yearsOfExperience = yearsOfExperience;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization + ", Contact Number: " + contactNumber + ", Years of Experience: " + yearsOfExperience + ", Available: " + isAvailable;
    }
}

class Patient {
    private int id;
    private String name;
    private int age;
    private String medicalHistory;
    private String emergencyContact;

    public Patient(int id, String name, int age, String medicalHistory, String emergencyContact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
        this.emergencyContact = emergencyContact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Medical History: " + medicalHistory + ", Emergency Contact: " + emergencyContact;
    }
}

class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private String date;
    private String status;
    private String consultationNotes;

    public Appointment(int id, int doctorId, int patientId, String date, String status, String consultationNotes) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.status = status;
        this.consultationNotes = consultationNotes;
    }

    public int getId() {
        return id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + id + ", Doctor ID: " + doctorId + ", Patient ID: " + patientId + ", Date: " + date + ", Status: " + status + ", Notes: " + consultationNotes;
    }
}

class Department {
    private int id;
    private String name;
    private String departmentHead;
    private String contactNumber;
    private int numberOfStaff;

    public Department(int id, String name, String departmentHead, String contactNumber, int numberOfStaff) {
        this.id = id;
        this.name = name;
        this.departmentHead = departmentHead;
        this.contactNumber = contactNumber;
        this.numberOfStaff = numberOfStaff;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getNumberOfStaff() {
        return numberOfStaff;
    }

    @Override
    public String toString() {
        return "Department ID: " + id + ", Name: " + name + ", Head: " + departmentHead + ", Contact: " + contactNumber + ", Staff Count: " + numberOfStaff;
    }
}

// Service Layer
class HospitalService {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void updateDoctorAvailability(int doctorId, boolean isAvailable) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == doctorId) {
                doctor.setAvailable(isAvailable);
                return;
            }
        }
        System.out.println("Doctor not found!");
    }

    public void updatePatientMedicalHistory(int patientId, String medicalHistory) {
        for (Patient patient : patients) {
            if (patient.getId() == patientId) {
                patient.setMedicalHistory(medicalHistory);
                return;
            }
        }
        System.out.println("Patient not found!");
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}

// Utility Layer
class InputValidator {
    public static boolean isValidName(String name) {
        return name != null && name.matches("[A-Za-z ]+");
    }

    public static boolean isValidAge(int age) {
        return age > 0;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\d{10}");
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);  // Parse the date to validate it
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidAppointmentStatus(String status) {
        return status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Completed") || status.equalsIgnoreCase("Cancelled");
    }
}

class FileManager {
    public static void saveDoctors(String fileName, List<Doctor> doctors) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.toString() + "\n");
            }
        }
    }

    public static List<Doctor> loadDoctors(String fileName) throws IOException {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parsing logic to create Doctor object
            }
        }
        return doctors;
    }
}

// Exception Layer
class HospitalException extends Exception {
    public HospitalException(String message) {
        super(message);
    }
}

class DoctorNotFoundException extends HospitalException {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}

class PatientNotFoundException extends HospitalException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}

// Helper Classes
class MenuHelper {
    public static void displayMainMenu() {
        System.out.println("--- Hospital Management System ---");
        System.out.println("1. Add Doctor");
        System.out.println("2. Add Patient");
        System.out.println("3. Add Appointment");
        System.out.println("4. Add Department");
        System.out.println("5. Update Doctor Availability");
        System.out.println("6. Update Patient Medical History");
        System.out.println("7. View All Data");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }
}

// Main Application
public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalService service = new HospitalService();

        while (true) {
            MenuHelper.displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        // Add Doctor
                        break;
                    case 2:
                        // Add Patient
                        break;
                    case 3:
                        // Add Appointment
                        break;
                    case 4:
                        // Add Department
                        break;
                    case 5:
                        // Update Doctor Availability
                        break;
                    case 6:
                        // Update Patient Medical History
                        break;
                    case 7:
                        // View Data
                        break;
                    case 8:
                        System.out.println("Exiting... Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (DoctorNotFoundException | PatientNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }
    }
}
