import javax.swing ;*.  
import java.awt ;*. 
import java.awt.event ;*. 
import java.io ;*. 
import java.util ;*. 
 
public class AttendanceSystem  { 
 
    private Map<String, String> attendance = new HashMap ;)(>< 
    private java.util.List<String> students = new ArrayList<>(Arrays.asList("Ali", 
"Sara", "Maha", "Omar")) ; 
 
    private JFrame frame ; 
    private JTextArea reportArea ; 
    private JPanel studentPanel ; 
 
    public static void main(String[] args)  { 
        new AttendanceSystem().showLogin ;)( 
     } 
  ألوان فاتحة         //     
 
     Color bgColor = new Color(245, 250, 255) خلفية //      ; 
     Color panelColor = new Color(255, 255, 255) أبيض //   ; 
     Color blueSoft = new Color(173, 216, 230) أزرق فاتح //     ; 
     Color greenSoft = new Color(200, 230, 201) أخضر فاتح //    ; 
     Color pinkSoft = new Color(255, 220, 230) وردي فاتح //     ; 
 
      ================= // LOGIN ================= 
    public void showLogin { )( 
        JFrame login = new JFrame("Login") ; 
        login.setSize(300, 200) ; 
        login.setLayout(new GridLayout(3, 2)) ; 
 
        login.getContentPane().setBackground(bgColor) ; 
 
        JTextField user = new JTextField ;)( 
        JPasswordField pass = new JPasswordField ;)( 
 
        JButton btn = new JButton("Login") ; 
        styleButton(btn, blueSoft) ; 
 
        login.add(new JLabel("Username:")) ; 
        login.add(user) ; 
        login.add(new JLabel("Password:")) ; 
        login.add(pass) ; 
        login.add(new JLabel()) ; 
        login.add(btn) ; 
 
        btn.addActionListener(e - { > 
            if (user.getText().equals("teacher") && new 
String(pass.getPassword()).equals("1234")) { 
                 login.dispose ;)( 
                 createSystem ;)( 
              } else  { 
                JOptionPane.showMessageDialog(login, "Wrong login!") ; 
             } 
         ;)} 
 
        login.setVisible(true) ; 
     } 
 
      ================= // MAIN ================= 
    public void createSystem { )( 
        frame = new JFrame("Attendance System") ; 
        frame.setSize(750, 550) ; 
        frame.setLayout(new BorderLayout()) ; 
 
        frame.getContentPane().setBackground(bgColor) ; 
 
        JLabel title = new JLabel("Student Attendance System", JLabel.CENTER) ; 
        title.setFont(new Font("Arial", Font.BOLD, 22)) ; 
        title.setForeground(new Color(70, 130, 180)) أزرق هادئ // ; 
 
        frame.add(title, BorderLayout.NORTH) ; 
 
        studentPanel = new JPanel ;)( 
        studentPanel.setLayout(new GridLayout(0, 3, 10, 10)) ; 
        studentPanel.setBackground(panelColor) ; 
 
        refreshStudentPanel ;)( 
 
        frame.add(studentPanel, BorderLayout.CENTER) ; 
 
        JPanel bottom = new JPanel ;)( 
        bottom.setBackground(bgColor) ; 
 
        JButton reportBtn = new JButton("Show Report") ; 
        JButton percentBtn = new JButton("Attendance %") ; 
        JButton saveBtn = new JButton("Save") ; 
        JButton addBtn = new JButton("Add Student") ; 
        JButton deleteBtn = new JButton("Delete Student") ; 
  أزرار بألوان فاتحة        //        
 
        styleButton(reportBtn, greenSoft) ; 
        styleButton(percentBtn, blueSoft) ; 
        styleButton(saveBtn, blueSoft) ; 
        styleButton(addBtn, pinkSoft) ; 
        styleButton(deleteBtn, pinkSoft) ; 
 
        reportArea = new JTextArea(8, 50) ; 
        reportArea.setBackground(panelColor) ; 
        reportArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 
200))) ; 
 
        reportBtn.addActionListener(e -> showReport()) ; 
        percentBtn.addActionListener(e -> showPercentage()) ; 
        saveBtn.addActionListener(e -> saveToFile()) ; 
        addBtn.addActionListener(e -> addStudent()) ; 
        deleteBtn.addActionListener(e -> deleteStudent()) ; 
 
        bottom.add(reportBtn) ; 
        bottom.add(percentBtn) ; 
        bottom.add(saveBtn) ; 
        bottom.add(addBtn) ; 
        bottom.add(deleteBtn) ; 
        bottom.add(new JScrollPane(reportArea)) ; 
 
        frame.add(bottom, BorderLayout.SOUTH) ; 
 
        frame.setVisible(true) ; 
     } 
  تنسيق األزرار        //     
 
    private void styleButton(JButton button, Color color)  { 
        button.setBackground(color) ; 
        button.setForeground(Color.BLACK) ; 
        button.setFocusPainted(false) ; 
     } 
 
      ================= // STUDENTS ================= 
    public void refreshStudentPanel { )( 
        studentPanel.removeAll ;)( 
 
        for (String student : students) { 
 
            JLabel name = new JLabel(student) ; 
            name.setFont(new Font("Arial", Font.BOLD, 14)) ; 
 
            JButton present = new JButton("Present") ; 
            JButton absent = new JButton("Absent") ; 
 
            styleButton(present, greenSoft) ; 
            styleButton(absent, pinkSoft) ; 
 
            present.addActionListener(e -> attendance.put(student, "Present")) ; 
            absent.addActionListener(e -> attendance.put(student, "Absent")) ; 
 
            studentPanel.add(name) ; 
            studentPanel.add(present) ; 
            studentPanel.add(absent) ; 
         } 
 
        studentPanel.revalidate ;)( 
        studentPanel.repaint ;)( 
     } 
 
      ================= // REPORT ================= 
    public void showReport { )( 
        reportArea.setText("Attendance Report:\n") ; 
 
        for (String s : students)  { 
            String status = attendance.getOrDefault(s, "Not Marked") ; 
            reportArea.append(s + " : " + status + "\n") ; 
         } 
     } 
 
      ================= // PERCENT ================= 
    public void showPercentage { )( 
        int present = 0 ; 
 
        for (String s : students)  { 
            if ("Present".equals(attendance.get(s)))  { 
                present ;++ 
             } 
         } 
 
        double percent = (students.size() == 0) ? 0 : (present * 100.0 / 
students.size()) ; 
 
        JOptionPane.showMessageDialog(frame, "Attendance: " + percent + "%") ; 
     } 
 
      ================= // SAVE ================= 
    public void saveToFile { )( 
        try { 
            FileWriter writer = new FileWriter("attendance.txt") ; 
 
            for (String s : students)   { 
                 String status = attendance.getOrDefault(s, "Not Marked") ; 
                writer.write(s + " : " + status + "\n") ; 
             } 
 
            writer.close ;)( 
            JOptionPane.showMessageDialog(frame, "Saved!") ; 
 
         } catch (IOException e)  { 
            JOptionPane.showMessageDialog(frame, "Error!") ; 
         } 
     } 
 
       ================= // ADD ================= 
    public void addStudent { )( 
        String name = JOptionPane.showInputDialog("Enter name:") ; 
        if (name != null && !name.isEmpty()) { 
            students.add(name) ; 
            refreshStudentPanel ;)( 
         } 
     } 
 
      ================= // DELETE ================= 
    public void deleteStudent { )( 
        String name = JOptionPane.showInputDialog("Enter name to delete:") ; 
        if (students.remove(name))  { 
            attendance.remove(name) ; 
            refreshStudentPanel ;)( 
         } else  { 
            JOptionPane.showMessageDialog(frame, "Not found!") ; 
         } 
     } 
}
