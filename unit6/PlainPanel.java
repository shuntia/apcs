
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlainPanel extends JPanel{
    public PlainPanel(){
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
    }
}
