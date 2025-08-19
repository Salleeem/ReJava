package com.example.View.Student;

import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.example.Controller.StudentController;
import com.example.DAO.StudentDAO;
import com.example.Model.Student;

public class ManageStudentTabsView {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public ManageStudentTabsView() {
        frame = new JFrame("Gerenciamento de Alunos");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        tabbedPane.addTab("Criar", null, new CreateStudentPanel(), "Cadastrar novo aluno");
        tabbedPane.addTab("Editar", null, new UpdateStudentPanel(), "Atualizar dados de aluno");
        tabbedPane.addTab("Excluir", null, new DeleteStudentPanel(), "Excluir aluno");
        tabbedPane.addTab("Listar", null, new ListStudentPanel(), "Listar alunoes");

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    class CreateStudentPanel extends JPanel {
        private JTextField nameField;
        private JTextField cpfField;
        private JPasswordField passwordField;
        private JButton saveButton;

        private StudentController controller;

        public CreateStudentPanel() {
            setLayout(null);

            controller = new StudentController();

            JLabel nameLabel = new JLabel("Nome:");
            nameLabel.setBounds(30, 30, 100, 25);
            add(nameLabel);

            nameField = new JTextField();
            nameField.setBounds(130, 30, 200, 25);
            add(nameField);

            JLabel cpfLabel = new JLabel("CPF:");
            cpfLabel.setBounds(30, 70, 100, 25);
            add(cpfLabel);

            try {
                MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
                cpfMask.setPlaceholderCharacter('_');
                cpfField = new JFormattedTextField(cpfMask);
            } catch (Exception e) {
                cpfField = new JFormattedTextField();
                e.printStackTrace();
            }
            cpfField.setBounds(130, 70, 200, 25);
            add(cpfField);

            JLabel passwordLabel = new JLabel("Senha:");
            passwordLabel.setBounds(30, 110, 100, 25);
            add(passwordLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(130, 110, 200, 25);
            add(passwordField);

            saveButton = new JButton("Salvar");
            saveButton.setBounds(130, 200, 100, 30);
            add(saveButton);

            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveStudent();
                }
            });
        }

        private void saveStudent() {
            String name = nameField.getText();
            String cpf = cpfField.getText().replaceAll("[^\\d]", ""); // remove pontos e traço
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || cpf.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.createStudent(cpf, name, password);
            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
            nameField.setText("");
            cpfField.setText("");
            passwordField.setText("");

        }
    }

    class UpdateStudentPanel extends JPanel {
        private JComboBox<Student> studentCombo;
        private JTextField nameField;
        private JTextField cpfField;
        private JPasswordField passwordField;
        private JButton updateButton;

        private StudentDAO studentDAO = new StudentDAO();
        private StudentController studentController = new StudentController();

        public UpdateStudentPanel() {
            setLayout(null);

            JLabel selectLabel = new JLabel("Selecione o aluno:");
            selectLabel.setBounds(20, 20, 200, 25);
            add(selectLabel);

            studentCombo = new JComboBox<>();
            studentCombo.setBounds(20, 50, 340, 25);
            studentCombo.addActionListener(e -> loadFields());
            add(studentCombo);

            JLabel nameLabel = new JLabel("Nome:");
            nameLabel.setBounds(20, 90, 100, 25);
            add(nameLabel);

            nameField = new JTextField();
            nameField.setBounds(120, 90, 240, 25);
            add(nameField);

            JLabel cpfLabel = new JLabel("CPF:");
            cpfLabel.setBounds(20, 130, 100, 25);
            add(cpfLabel);

            MaskFormatter cpfMask = null;
            try {
                cpfMask = new MaskFormatter("###.###.###-##");
                cpfMask.setPlaceholderCharacter('_');
            } catch (Exception e) {
                e.printStackTrace();
            }

            cpfField = new JFormattedTextField(cpfMask);
            cpfField.setBounds(120, 130, 240, 25);
            add(cpfField);

            JLabel passLabel = new JLabel("Senha:");
            passLabel.setBounds(20, 170, 100, 25);
            add(passLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(120, 170, 240, 25);
            add(passwordField);


            updateButton = new JButton("Atualizar");
            updateButton.setBounds(130, 260, 120, 30);
            updateButton.addActionListener(e -> updateStudent());
            add(updateButton);

            loadStudents();
        }

        private void loadStudents() {
            List<Student> students = studentDAO.list();
            for (Student t : students) {
                studentCombo.addItem(t);
            }
        }

        private void loadFields() {
            Student selected = (Student) studentCombo.getSelectedItem();
            if (selected != null && selected.getPerson() != null) {
                nameField.setText(selected.getName());
                cpfField.setText(selected.getPerson().getCpf());
                passwordField.setText(selected.getPassword());
            }
        }

        private void updateStudent() {
            Student selected = (Student) studentCombo.getSelectedItem();
            if (selected == null || selected.getPerson() == null) {
                JOptionPane.showMessageDialog(this, "Selecione um aluno.");
                return;
            }

            selected.setName(nameField.getText());
            selected.getPerson().setCpf(cpfField.getText());
            selected.setPassword(new String(passwordField.getPassword()));

            studentController.updateStudent(selected);
            JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
        }
    }

    class DeleteStudentPanel extends JPanel {
        private JComboBox<Student> studentCombo;
        private JButton deleteButton;
        private StudentDAO studentDAO = new StudentDAO();
        private StudentController studentController = new StudentController();

        public DeleteStudentPanel() {
            setLayout(null);
            setSize(400, 200);

            JLabel label = new JLabel("Selecione o aluno:");
            label.setBounds(30, 30, 200, 25);
            add(label);

            studentCombo = new JComboBox<>();
            studentCombo.setBounds(30, 60, 320, 25);
            add(studentCombo);

            deleteButton = new JButton("Excluir");
            deleteButton.setBounds(130, 100, 100, 30);
            add(deleteButton);

            loadStudents();

            deleteButton.addActionListener(e -> deleteStudent());
        }

        private void loadStudents() {
            studentCombo.removeAllItems();
            List<Student> students = studentDAO.list();
            for (Student t : students) {
                studentCombo.addItem(t);
            }
        }

        private void deleteStudent() {
            Student selected = (Student) studentCombo.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Nenhum aluno selecionado.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Tem certeza que deseja excluir o aluno \"" + selected.getName() + "\"?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                studentController.deleteStudent(selected.getId());
                JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!");
                loadStudents();
            }
        }
    }

    class ListStudentPanel extends JPanel {
        private JTable table;
        private JScrollPane scrollPane;

        public ListStudentPanel() {
            this.setLayout(null);
            String[] columnNames = { "ID", "Nome", "CPF"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            table = new JTable(model);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(20, 20, 740, 400);
            this.add(scrollPane);

            loadStudents(model);
        }

        private void loadStudents(DefaultTableModel model) {
            StudentDAO studentDAO = new StudentDAO();
            List<Student> teachers = studentDAO.list();

            for (Student t : teachers) {
                Object[] rowData = {
                        t.getId(),
                        t.getName(),
                        t.getPerson().getCpf(),
                };
                model.addRow(rowData);
            }
        }
    }

}
