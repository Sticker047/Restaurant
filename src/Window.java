import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;


public class Window extends JFrame {

    public Window(Waiter @NotNull [] waiterList, Consumer[] consumerList, List<Order> orderList) {
        super("Ресторан");

        this.setBounds(100, 100, 250, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel content = new JPanel(new VerticalLayout());

        JLabel[] labelsWaiter = new JLabel[waiterList.length];
        for (int i = 0; i < waiterList.length; i++) {
            labelsWaiter[i] = new JLabel(waiterList[i].toString());
            content.add(labelsWaiter[i]);
        }

        JLabel[] labelsConsumer = new JLabel[consumerList.length];
        for (int i = 0; i < consumerList.length; i++) {
            labelsConsumer[i] = new JLabel(consumerList[i].toString());
            content.add(labelsConsumer[i]);
        }

        JLabel[] labelsOrder = new JLabel[orderList.size()];
        for (int i = 0; i < orderList.size(); i++) {
            labelsConsumer[i] = new JLabel(orderList.get(i).toString());
            content.add(labelsOrder[i]);
        }

        this.setContentPane(content);
        this.setVisible(true);
    }
}
