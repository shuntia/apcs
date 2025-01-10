
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FramedPanel extends JPanel{
    private Color color;
    public FramedPanel(Color color){
        this.color=color;
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        this.setLayout(new BorderLayout());
        JPanel southContainer=new JPanel();
        southContainer.setLayout(new BorderLayout());
        JPanel prevnext=new JPanel();
        prevnext.setLayout(new GridLayout());
        JButton prev=new JButton("Previous");
        JButton next=new JButton("Next");
        JButton submit=new JButton("Submit");
        prevnext.add(prev);
        prevnext.add(next);
        southContainer.add(prevnext, BorderLayout.CENTER);
        southContainer.add(submit, BorderLayout.SOUTH);
        this.add(southContainer, BorderLayout.SOUTH);
        JPanel centerContainer=new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics gr) {
                super.paintComponent(gr);
                gr.setColor(color);
                int offset = Math.min(getWidth(), getHeight()) / 20;
                gr.fillRect(0, 0, getWidth(), getHeight());
                gr.setColor(Color.white);
                gr.fillRect(offset / 2, offset / 2, getWidth() - offset, getHeight() - offset);
                gr.setColor(Color.black);
                gr.drawRect(offset / 2, offset / 2, getWidth() - offset, getHeight() - offset);
            }
        };
        this.add(centerContainer, BorderLayout.CENTER);
    }
}
