import java.awt.*;

public class VerticalLayout implements LayoutManager {

    private final Dimension size = new Dimension();

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    @Override
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }

    @Override
    public void layoutContainer(Container container) {
        Component[] components = container.getComponents();
        int currentY = 10;
        for (Component component : components) {
            Dimension pref = component.getPreferredSize();
            component.setBounds(5, currentY, pref.width, pref.height);
            currentY += 5;
            currentY += pref.height;
        }
    }

    private Dimension calculateBestSize(Container c) {
        {
            Component[] list = c.getComponents();
            int maxWidth = 0;
            for (Component component : list) {
                int width = component.getWidth();
                if (width > maxWidth)
                    maxWidth = width;
            }
            size.width = maxWidth + 5;
            int height = 0;
            for (Component component : list) {
                height += 5;
                height += component.getHeight();
            }
            size.height = height;
            return size;
        }
    }
}
