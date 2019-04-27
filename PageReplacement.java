import javax.swing.*;
import java.awt.*;

public class PageReplacement {

    public static void main(String[] args) {
inputWindow obj=new inputWindow();
obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
obj.setVisible(true);
obj.setSize(obj.getPreferredSize());
obj.setTitle(" PageReplacement Simulator");

    }
}

class inputWindow extends JFrame {
JPanel p;

inputWindow(){

    GridBagLayout gridbag=new GridBagLayout();
    GridBagConstraints c=new GridBagConstraints();
    p=new JPanel(gridbag);
    c.fill=GridBagConstraints.BOTH;
    c.gridx=0;
    c.gridy=0;
    c.fill=1;
    c.weightx=1;
JLabel frames=new JLabel("NO OF FRAMES");
p.add(frames,c);

Integer[] frameoptions={3,4,5,6,7};
    JComboBox<Integer> combo=new JComboBox<Integer>(frameoptions);
//    c.gridx=1;
//    c.gridy=0;
//    c.fill=1;
    c.gridx=1;
    c.weightx=1;
    p.add(combo,c);

    c.weightx=0;
    JLabel refString=new JLabel(" Enter The Reference String");
    c.gridy=1;
    c.gridx=0;
    c.weightx=1;//c.weighty=.1;
    p.add(refString,c);

    JTextField t1=new JTextField("");
    c.weightx=1;
c.gridx=1;
p.add(t1,c);
add(p);
setSize(500,500);
pack();;
}
}