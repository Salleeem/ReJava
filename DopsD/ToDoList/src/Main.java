package DopsD.ToDoList.src;
public class Main {
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(() -> {
            TodoList app = new TodoList();
            app.run();
        });
    }
}
