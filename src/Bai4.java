import java.util.LinkedList;

public class Bai4 {

    public static class Patient {
        String name;
        boolean isEmergency;

        public Patient(String name, boolean isEmergency) {
            this.name = name;
            this.isEmergency = isEmergency;
        }
    }

    public static class EmergencyRoom {
        private LinkedList<Patient> patients;

        public EmergencyRoom() {
            patients = new LinkedList<>();
        }

        public void patientCheckIn(String name) {
            patients.addLast(new Patient(name, false));
        }

        public void emergencyCheckIn(String name) {
            patients.addFirst(new Patient(name, true));
        }

        public void treatPatient() {
            if (patients.isEmpty()) {
                System.out.println("Không có bệnh nhân nào đang chờ.");
                return;
            }

            Patient currentPatient = patients.removeFirst();

            if (currentPatient.isEmergency) {
                System.out.println("Đang cấp cứu: " + currentPatient.name);
            } else {
                System.out.println("Đang khám: " + currentPatient.name);
            }
        }
    }

    public static void main(String[] args) {
        EmergencyRoom er = new EmergencyRoom();

        er.patientCheckIn("A");
        er.patientCheckIn("B");
        er.emergencyCheckIn("C");

        er.treatPatient();
        er.treatPatient();
        er.treatPatient();
    }
}