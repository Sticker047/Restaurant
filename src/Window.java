import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Window extends JFrame {

    private final ArrayList<JLabel> labelsWaiter;
    private final ArrayList<JLabel> labelsConsumer;
    private final JPanel content;


    public Window(ArrayList<Waiter> waiterList, ArrayList<Consumer> consumerList) {
        super("Ресторан");

        this.setBounds(100, 100, 640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        content = new JPanel(new VerticalLayout());
        content.setBackground(Color.BLACK);


        labelsWaiter = new ArrayList<>();
        for (int i = 0; i < waiterList.size(); i++) {
            JLabel temp = new JLabel(waiterList.get(i).toString());
            temp.setForeground(Color.CYAN);
            labelsWaiter.add(temp);
            content.add(labelsWaiter.get(i));
        }

        labelsConsumer = new ArrayList<>();
        for (int i = 0; i < consumerList.size(); i++) {
            JLabel temp = new JLabel(consumerList.get(i).toString());
            temp.setForeground(Color.GREEN);
            labelsConsumer.add(temp);
            content.add(labelsConsumer.get(i));
        }

        this.setContentPane(content);

    }

    public void update(ArrayList<Waiter> waiterList, ArrayList<Consumer> consumerList) {
        while (isVisible()) {
            for (int i = 0; i < waiterList.size(); i++) {
                labelsWaiter.get(i).setText(waiterList.get(i).toString());
            }
            for (int i = 0; i < consumerList.size(); i++) {
                labelsConsumer.get(i).setText(consumerList.get(i).toString());
            }

            if (consumerList.size() > labelsConsumer.size()) {
                for (int i = labelsConsumer.size(); i < consumerList.size(); i++){
                    labelsConsumer.add(new JLabel(consumerList.get(i).toString()));
                    content.add(labelsConsumer.get(i));
                }
            }
        }
    }
}
