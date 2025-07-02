package com.example.View.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.List;

import com.example.Controller.TeacherController;
import com.example.DAO.SubjectDAO;
import com.example.DAO.TeacherDAO;
import com.example.Model.Subject;
import com.example.Model.Teacher;

public class ManageTeacherViewTabs {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    public ManageTeacherViewTabs() {
        frame = new JFrame("Painel de Professores");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        tabbedPane.addTab("Criar", null, new CreateTeacherPanel(), "Cadastrar novo professor");
        tabbedPane.addTab("Editar", null, new UpdateTeacherPanel(), "Atualizar dados de professor");
        tabbedPane.addTab("Excluir", null, new DeleteTeacherPanel(), "Excluir professor");
        tabbedPane.addTab("Listar", null, new ListTeacherPanel(), "Listar professores");

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    class CreateTeacherPanel extends JPanel {
        private JTextField nameField;
        private JTextField cpfField;
        private JPasswordField passwordField;
        private JComboBox<Subject> subjectCombo;
        private JButton saveButton;

        private TeacherController controller;
        private SubjectDAO subjectDAO;

        public CreateTeacherPanel() {
            setLayout(null);

            controller = new TeacherController();
            subjectDAO = new SubjectDAO();

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

            JLabel subjectLabel = new JLabel("Matéria:");
            subjectLabel.setBounds(30, 150, 100, 25);
            add(subjectLabel);

            subjectCombo = new JComboBox<>();
            subjectCombo.setBounds(130, 150, 200, 25);
            add(subjectCombo);

            saveButton = new JButton("Salvar");
            saveButton.setBounds(130, 200, 100, 30);
            add(saveButton);

            loadSubjects();

            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveTeacher();
                }
            });
        }

        private void loadSubjects() {
            List<Subject> subjects = subjectDAO.list();
            for (Subject subject : subjects) {
                subjectCombo.addItem(subject);
            }
        }

        private void saveTeacher() {
            String name = nameField.getText();
            String cpf = cpfField.getText();
            String password = new String(passwordField.getPassword());
            Subject selectedSubject = (Subject) subjectCombo.getSelectedItem();

            if (name.isEmpty() || cpf.isEmpty() || password.isEmpty() || selectedSubject == null) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.createTeacher(name, cpf, password, selectedSubject);
            JOptionPane.showMessageDialog(this, "Professor cadastrado com sucesso!");
        }
    }

    class UpdateTeacherPanel extends JPanel {

        private JComboBox<Teacher> teacherCombo;
        private JTextField nameField, cpfField;
        private JPasswordField passwordField;
        private JComboBox<Subject> subjectCombo;
        private JButton updateButton;

        private TeacherController teacherController;
        private TeacherDAO teacherDAO;
        private SubjectDAO subjectDAO;

        public UpdateTeacherPanel() {
            teacherController = new TeacherController();
            teacherDAO = new TeacherDAO();
            subjectDAO = new SubjectDAO();

            setLayout(null);

            JLabel selectLabel = new JLabel("Selecione o professor:");
            selectLabel.setBounds(20, 20, 200, 25);
            this.add(selectLabel);

            teacherCombo = new JComboBox<>();
            teacherCombo.setBounds(20, 50, 340, 25);
            teacherCombo.addActionListener(e -> loadFields());
            this.add(teacherCombo);

            JLabel nameLabel = new JLabel("Nome:");
            nameLabel.setBounds(20, 90, 100, 25);
            this.add(nameLabel);

            nameField = new JTextField();
            nameField.setBounds(120, 90, 240, 25);
            this.add(nameField);

            JLabel cpfLabel = new JLabel("CPF:");
            cpfLabel.setBounds(20, 130, 100, 25);
            this.add(cpfLabel);

            cpfField = new JTextField();
            cpfField.setBounds(120, 130, 240, 25);
            this.add(cpfField);

            JLabel passLabel = new JLabel("Senha:");
            passLabel.setBounds(20, 170, 100, 25);
            this.add(passLabel);

            passwordField = new JPasswordField();
            passwordField.setBounds(120, 170, 240, 25);
            this.add(passwordField);

            JLabel subjectLabel = new JLabel("Matéria:");
            subjectLabel.setBounds(20, 210, 100, 25);
            this.add(subjectLabel);

            subjectCombo = new JComboBox<>();
            subjectCombo.setBounds(120, 210, 240, 25);
            this.add(subjectCombo);

            updateButton = new JButton("Atualizar");
            updateButton.setBounds(130, 260, 120, 30);
            updateButton.addActionListener(e -> UpdateTeachers());
            this.add(updateButton);

            loadTeachers();
            loadSubjects();

            this.setVisible(true);
        }

        private void loadTeachers() {
            List<Teacher> teachers = teacherDAO.list();
            for (Teacher t : teachers) {
                teacherCombo.addItem(t);
            }
        }

        private void loadSubjects() {
            List<Subject> subjects = subjectDAO.list();
            for (Subject s : subjects) {
                subjectCombo.addItem(s);
            }
        }

        private void loadFields() {
            Teacher selected = (Teacher) teacherCombo.getSelectedItem();
            if (selected != null) {
                nameField.setText(selected.getName());
                cpfField.setText(selected.getCpf());
                passwordField.setText(selected.getPassword());
                subjectCombo.setSelectedItem(selected.getSubject());
            }
        }

        private void UpdateTeachers() {
            Teacher selected = (Teacher) teacherCombo.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Selecione um professor.");
                return;
            }

            String name = nameField.getText();
            String cpf = cpfField.getText();
            String password = new String(passwordField.getPassword());
            Subject subject = (Subject) subjectCombo.getSelectedItem();

            selected.setName(name);
            selected.setCpf(cpf);
            selected.setPassword(password);
            selected.setSubject(subject);

            teacherController.updateTeacher(selected);
            JOptionPane.showMessageDialog(this, "Professor atualizado com sucesso!");
        }
    }
}

class DeleteTeacherPanel extends JPanel {

    private JComboBox<Teacher> teacherCombo;
    private JButton deleteButton;

    private TeacherDAO teacherDAO;
    private TeacherController teacherController;

    public DeleteTeacherPanel() {

        teacherDAO = new TeacherDAO();
        teacherController = new TeacherController();

        this.setSize(400, 200);
        this.setLayout(null);

        JLabel label = new JLabel("Selecione o professor:");
        label.setBounds(30, 30, 200, 25);
        this.add(label);

        teacherCombo = new JComboBox<>();
        teacherCombo.setBounds(30, 60, 320, 25);
        this.add(teacherCombo);

        deleteButton = new JButton("Excluir");
        deleteButton.setBounds(130, 100, 100, 30);
        this.add(deleteButton);

        loadTeachers();

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTeacher();
            }
        });

    }

    private void loadTeachers() {
        List<Teacher> teachers = teacherDAO.list();
        for (Teacher t : teachers) {
            teacherCombo.addItem(t);
        }
    }

    private void deleteTeacher() {
        Teacher selected = (Teacher) teacherCombo.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Nenhum professor selecionado.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir o professor \"" + selected.getName() + "\"?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            teacherController.deleteTeacher(selected.getId()); // método no Controller
            JOptionPane.showMessageDialog(this, "Professor excluído com sucesso!");

        }

    }
}

class ListTeacherPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;

    public ListTeacherPanel() {

        this.setSize(600, 400);
        this.setLayout(null);
        setLayout(null);

        String[] columnNames = { "ID", "Nome", "CPF", "Matéria" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 540, 300);
        this.add(scrollPane);

        loadTeachers(model);

        this.setVisible(true);
    }

    private void loadTeachers(DefaultTableModel model) {
        TeacherDAO teacherDAO = new TeacherDAO();
        List<Teacher> teachers = teacherDAO.list();

        for (Teacher t : teachers) {
            Object[] rowData = {
                    t.getId(),
                    t.getName(),
                    t.getCpf(),
                    (t.getSubject() != null) ? t.getSubject().getName() : "Sem matéria"
            };
            model.addRow(rowData);
        }
    }
}
