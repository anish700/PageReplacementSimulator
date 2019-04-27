import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
obj1arr=getFifoArray

 */
public class PageReplacement {

    public static void main(String[] args) {
inputWindow obj=new inputWindow();
obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
obj.setVisible(true);
obj.setSize(700,700);
//obj.pack();
obj.setTitle(" PageReplacement Simulator");

    }
}

class inputWindow extends JFrame {
    JPanel p;

// UIManager(UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()));
    inputWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        p = new JPanel(gridbag);

        this.setLayout(gridbag);
        JLabel frames = new JLabel("NO OF FRAMES");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = 1;
        c.weightx = .5;
        p.add(frames, c);

        Integer[] frameoptions = {3, 4, 5, 6, 7};
        JComboBox<Integer> combo = new JComboBox<Integer>(frameoptions);
        c.gridx++;
        c.weightx = .5;
        p.add(combo, c);


        JLabel refStringlabel = new JLabel(" Enter The Reference String");
        c.weightx = .5;
        c.gridy++;
        c.gridx--;
        p.add(refStringlabel, c);

        JTextField t1 = new JTextField("");
        c.weightx = .5;
        t1.setPreferredSize(new Dimension(200, refStringlabel.getHeight() + 35));
        c.gridx++;
        p.add(t1, c);

        JButton compute = new JButton(" Compute ");
        c.weightx = .1;
        c.weightx = .1;
        c.fill = 0;
        c.gridx--;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.gridy++;
        p.add(compute, c);
int zero=0;
        c.gridy =zero;
        c.gridx = zero;
        c.fill = 1;

        add(p, c);

        c.fill=1;
c.gridwidth=3;
c.gridy=4;
c.weightx=1;
c.weighty=1;
        FIFO obj1=new FIFO();
add(obj1,c);

c.gridwidth=3;
c.gridy=11;
c.weighty=1;
c.weightx=1;
        OPT obj2=new OPT();
add(obj2,c);

        LRU obj3=new LRU();



       compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//insertData(obj1.model,obj1.obj1arr((int)combo.getSele));
                int[] reff=refStringVals(t1.getText().toString());
                insertData(obj1.model,
                        obj1.obj1arr((int)combo.getSelectedItem(),reff), (t1.getText()).split(","));

                insertData(obj2.model,
                        obj2.obj2arr((int)combo.getSelectedItem(),reff), (t1.getText()).split(","));
            }
        });
    }

     int[] refStringVals(String refstring) {
        int c;
        int b;
        int a;
        try {
            int[] referenceStringArray;
            String[] newString = refstring.split(",");
            referenceStringArray = new int[newString.length];
            for (int i = 0; i < newString.length; i++) {
                referenceStringArray[i] = Integer.parseInt(newString[i]);
            }
            return referenceStringArray;
        }
        catch ( Exception e){
e.printStackTrace();
        }
return null;}
public void insertData(DefaultTableModel model, String[][] rows, String[] columns) {
    model.setRowCount(0);
    model.setColumnCount(0);

    if (columns != null)
        for (int i = 0; i < columns.length; i++)
            model.addColumn(columns[i]);



    if (rows != null)
        for (int i = 0; i < rows.length; i++)
            model.addRow(rows[i]);
    }

}




class FIFO extends JPanel{
JTable table1;
DefaultTableModel model;

static JLabel pagefaults=new JLabel("Page Faults");
FIFO(){
    setLayout(new BorderLayout());
    JPanel llpanel = new JPanel();
    llpanel.setLayout(new BorderLayout());
    JLabel obj1Label = new JLabel("First In First Out");
    llpanel.add(obj1Label,BorderLayout.WEST);
    llpanel.add(pagefaults,BorderLayout.EAST);
    model = new DefaultTableModel();
    table1 = new JTable(model);
    table1.getTableHeader().setReorderingAllowed(false);
    JScrollPane scrollPane = new JScrollPane(table1);
    add(llpanel,BorderLayout.NORTH);
    add(scrollPane,BorderLayout.CENTER);

}
    public static boolean chckE(int [][]array,int column, int num){
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i][column] == num)
                return true;
        }
        return false;
    }

    public static void initialise(int [][] array){
        for (int i = 0 ; i < array.length; i++){
            for(int j = 0 ; j < array[0].length; j++)
                array[i][j] = Integer.MAX_VALUE;
        }
    }

    public static void initialiseRow(int [] array, int num, int i){
        for(int j = i ; j < array.length; j++)
            array[j] = num;
    }




    public static String[][] obj1arr(int frameCount, int []refString){
        int frame[][] = new int[frameCount][refString.length];
        initialise(frame);
        int count = 0;
        boolean flag[] = new boolean[frameCount];

        for (int i = 0 ; i < refString.length ; i++){
            if (!chckE(frame, i, refString[i])){
                frame[count%frameCount][i] = refString[i];
                flag[count%frameCount] = true;
                initialiseRow(frame[count%frameCount],refString[i],i);
                count++;
            }
        }

        String [][]f = new String[frameCount][refString.length];
        for (int i = 0 ; i < frameCount; i++){
            for (int j = 0 ; j < refString.length ; j++){
                if (frame[i][j] == Integer.MAX_VALUE){
                    f[i][j] = "Null";
                    continue;
                }
                f[i][j] = Integer.toString(frame[i][j]);
            }
        }

        pagefaults.setText("Page Faults= "+count);
        return f;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }
}




/*
Optimal algorithm defined and values are modified by model
and then entered into the Jtable table 2
object here is obj2

 */
class OPT extends JPanel {
    DefaultTableModel model;
    JTable optTable;
    static JLabel opt = new JLabel("Optimal ");
    static JLabel PF = new JLabel("Page Faults=  ");
    OPT(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        labelPanel.add(opt,BorderLayout.WEST);
        labelPanel.add(PF,BorderLayout.EAST);
        model = new DefaultTableModel();
        optTable = new JTable(model);
        optTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(optTable);
        add(labelPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
    }

    public int findOptimal(int []refString, int [][] array, int i) {
        for (int j = 0 ; j < array.length ; j++){
            if (array[j][i] == Integer.MAX_VALUE) {
                return j;
            }
        }

        int [] opt = new int[array.length];
        for (int k = 0 ; k < opt.length ; k++){
            opt[k] = refString.length;
        }
        for (int k = 0 ; k < array.length ; k++) {
            for (int j = i + 1; j < refString.length; j++) {
                if (array[k][i] == refString[j]){
                    opt[k] = j;
                    break;
                }
            }
        }

        int minIndex = 0;

        for (int j = 0 ; j < opt.length; j++){
            if (opt[minIndex]<opt[j]){
                minIndex = j;
            }
        }
        System.out.println(minIndex);
        return minIndex;
    }

    void printer(int [][] frame){
        System.out.println("OPT");

        for (int i = 0 ; i < frame.length; i++) {
            for (int j = 0; j < frame[0].length; j++) {
                if (frame[i][j] == Integer.MAX_VALUE){
                    System.out.print(-1+" ");
                    continue;
                }
                System.out.print(frame[i][j]+"  ");
            }
            System.out.println("");
        }
    }

    public String[][] obj2arr(int frameCount, int []refString){
        int frame[][] = new int[frameCount][refString.length];
        FIFO.initialise(frame);
        boolean flag[] = new boolean[frameCount];
        int count = 0;
        for (int i = 0 ; i < refString.length ; i ++){
            if (!FIFO.chckE(frame, i, refString[i])){
                int replace = findOptimal(refString, frame, i);
                FIFO.initialiseRow(frame[replace], refString[i], i);
                count++;
            }
        }
        printer(frame);
        String [][]f = new String[frameCount][refString.length];
        for (int i = 0 ; i < frameCount; i++){
            for (int j = 0 ; j < refString.length ; j++){
                if (frame[i][j] == Integer.MAX_VALUE){
                    f[i][j] = "Null";
                    continue;
                }
                f[i][j] = Integer.toString(frame[i][j]);
            }
        }

        PF.setText("Page Fault: "+count);
        return f;
    }
    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

}
class LRU{

}
