package com.example.View.Student;

import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.example.Controller.StudentController;
import com.example.DAO.SubjectDAO;
import com.example.DAO.TeacherDAO;
import com.example.Model.Subject;
import com.example.Model.Teacher;

public class ManageStudentTabsView {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public ManageStudentTabsView() {
        frame = new JFrame("Painel de Professores");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        tabbedPane.addTab("Criar", null, new CreateStudentPanel(), "Cadastrar novo professor");
        // tabbedPane.addTab("Editar", null, new UpdateTeacherPanel(), "Atualizar dados
        // de professor");
        // tabbedPane.addTab("Excluir", null, new DeleteTeacherPanel(), "Excluir
        // professor");
        // tabbedPane.addTab("Listar", null, new ListTeacherPanel(), "Listar
        // professores");

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

            cpfField = new JTextField();
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
            String cpf = cpfField.getText();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || cpf.isEmpty() || password.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.createStudent(name, cpf, password);
            JOptionPane.showMessageDialog(this, "Aluno cadastrado com sucesso!");
        }
    }

}
