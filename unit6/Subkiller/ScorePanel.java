
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ScorePanel extends JPanel{
    private int score=0;
    JSlider difficultySlider=new JSlider(1, 5, 1);
    JPanel difficultyPanel=new JPanel();
    JLabel scoreLabel=new JLabel("Score: "+score);
    JLabel difficultyLabel=new JLabel("Difficulty: ");
    public ScorePanel(){
        super();
        this.setLayout(new GridLayout(1,2));
        difficultyPanel.add(difficultyLabel);
        difficultySlider.setMajorTickSpacing(2);
        difficultySlider.setMinorTickSpacing(1);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setSnapToTicks(true);
        difficultyPanel.add(difficultySlider);
        add(difficultyPanel);
        add(scoreLabel);
    }
    public void setScore(int score){
        this.score=score;
        scoreLabel.setText("Score: "+score);
        repaint();
    }
    public int getScore(){
        return score;
    }
    public void setDifficulty(int difficulty){
        difficultySlider.setValue(difficulty);
    }
    public int getDifficulty(){
        return difficultySlider.getValue();
    }
    public JSlider getDifficultySlider(){
        return difficultySlider;
    }
}
