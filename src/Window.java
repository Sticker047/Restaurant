import javax.swing.*;


public class Window extends JFrame {

    private final JLabel[] labelsWaiter;
    private final JLabel[] labelsConsumer;

    public Window(Waiter[] waiterList, Consumer[] consumerList) {
        super("Ресторан");

        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel content = new JPanel(new VerticalLayout());

        labelsWaiter = new JLabel[waiterList.length];
        for (int i = 0; i < waiterList.length; i++) {
            labelsWaiter[i] = new JLabel(waiterList[i].toString());
            content.add(labelsWaiter[i]);
        }

        labelsConsumer = new JLabel[consumerList.length];
        for (int i = 0; i < consumerList.length; i++) {
            labelsConsumer[i] = new JLabel(consumerList[i].toString());
            content.add(labelsConsumer[i]);
        }

        this.setContentPane(content);

    }

    public void update(Waiter[] waiterList, Consumer[] consumerList){
        while (isVisible()){
            for (int i = 0; i < waiterList.length; i++) {
                labelsWaiter[i].setText(waiterList[i].toString());
            }
            for (int i = 0; i < consumerList.length; i++) {
                labelsConsumer[i].setText(consumerList[i].toString());
            }
        }
    }
}
