import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
@author Anish R(1710110053)

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

        Integer[] frameOPTions = {3, 4, 5, 6, 7};
        JComboBox<Integer> combo = new JComboBox<Integer>(frameOPTions);
        c.gridx++;
        c.weightx = .5;
        p.add(combo, c);


        JLabel refStringlabel = new JLabel(" Reference String");
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
c.gridy=7;
c.weighty=1;
c.weightx=1;
        OPT obj2=new OPT();
add(obj2,c);
        c.gridwidth=3;
        c.gridy=11;
        c.weighty=1;
        c.weightx=1;
        LRU obj3=new LRU();
add(obj3,c);


       compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//insertData(obj1.model,obj1.obj1arr((int)combo.getSele));
                int[] reff=refStringVals(t1.getText().toString());
                insertData(obj1.model,
                        obj1.obj1arr((int)combo.getSelectedItem(),reff), (t1.getText()).split(","));

                insertData(obj2.model,
                        obj2.obj2arr((int)combo.getSelectedItem(),reff), (t1.getText()).split(","));

                insertData(obj3.model,
                        obj3.obj3arr((int)combo.getSelectedItem(),reff), (t1.getText()).split(","));
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

    public static void init(int [][] array){
        for (int i = 0 ; i < array.length; i++){
            for(int j = 0 ; j < array[0].length; j++)
                array[i][j] = Integer.MAX_VALUE;
        }
    }

    public static void initRow(int [] array, int num, int i){
        for(int j = i ; j < array.length; j++)
            array[j] = num;
    }




    public static String[][] obj1arr(int noOfFramest, int []refferalString){
        int frame[][] = new int[noOfFramest][refferalString.length];
        init(frame);
        int count = 0;
        boolean flag[] = new boolean[noOfFramest];

        for (int i = 0 ; i < refferalString.length ; i++){
            if (!chckE(frame, i, refferalString[i])){
                frame[count%noOfFramest][i] =refferalString[i];
                flag[count%noOfFramest] = true;
                initRow(frame[count%noOfFramest],refferalString[i],i);
                count++;
            }
        }
        String [][]ff = new String[noOfFramest][refferalString.length];


        for (int k = 0 ; k < noOfFramest; k++){
            int a=10;
            for (int j = 0 ; j < refferalString.length ; j++){
                if (frame[k][j] == Integer.MAX_VALUE){
                    int flag11=0;
                    ff[k][j] = "";
                    System.out.println("inside fifo "+count);
                    continue;
                }
                ff[k][j] = Integer.toString(frame[k][j]);
            }
        }

        pagefaults.setText("Page Faults= "+count);
        return ff;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
}




/*
OPTimal algorithm defined and values are modified by model
and then entered into the Jtable table 2
object here is obj2
The page that will be used furthest will be replaced in this algorithm

 */
class OPT extends JPanel {
    DefaultTableModel model;
    JTable obj2table;
    static JLabel obj2label = new JLabel("Optimal ");
    static JLabel pagefaults = new JLabel("Page Faults=  ");
    OPT(){
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        labelPanel.add(obj2label,BorderLayout.WEST);
        labelPanel.add(pagefaults,BorderLayout.EAST);
        model = new DefaultTableModel();
        obj2table = new JTable(model);
        obj2table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(obj2table);
        add(labelPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
    }

    public int findOPTimal(int []refString, int [][] array, int i) {
        for (int j = 0 ; j < array.length ; j++){
            if (array[j][i] == Integer.MAX_VALUE) {
                return j;
            }
        }

        int [] OPT = new int[array.length];
        for (int k = 0 ; k < OPT.length ; k++){
            OPT[k] = refString.length;
        }
        for (int k = 0 ; k < array.length ; k++) {
            for (int j = i + 1; j < refString.length; j++) {
                if (array[k][i] == refString[j]){
                    OPT[k] = j;
                    break;
                }
            }
        }

        int minimumIndexing = 0;

        for (int j = 0 ; j < OPT.length; j++){
            if (OPT[minimumIndexing]<OPT[j]){
                minimumIndexing = j;
            }
        }

        return minimumIndexing;
    }



//                if (frame[i][j] == Integer.MAX_VALUE){
//
//                    continue;
//                }
//
//            }
//
//        }
//    }

    public String[][] obj2arr(int noOfFramest, int []refString){
        int frame[][] = new int[noOfFramest][refString.length];
        FIFO.init(frame);
        boolean flag[] = new boolean[noOfFramest];
        int count = 0;
        for (int i = 0 ; i < refString.length ; i ++){
            if (!FIFO.chckE(frame, i, refString[i])){
                int replace = findOPTimal(refString, frame, i);
                FIFO.initRow(frame[replace], refString[i], i);
                count++;
            }
        }
        //display(frame);
        String [][]f = new String[noOfFramest][refString.length];

        for (int i = 0 ; i < noOfFramest; i++){

            for (int j = 0 ; j < refString.length ; j++){
                int flag22=0;
                if (frame[i][j] == Integer.MAX_VALUE){
                    f[i][j] = "";
                    System.out.println("inside optimal "+count);
                    int flag23=1;
                    continue;
                }
                f[i][j] = Integer.toString(frame[i][j]);
            }
        }

        pagefaults.setText("Page Fault: "+count);
        return f;
    }
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

}
/*
Least recently used page will be replaced
 */
class LRU extends JPanel {
    DefaultTableModel model;
    JTable obj3table;
    JLabel obj3label = new JLabel("Least Recently Used ");
    static JLabel pagefaults = new JLabel("Page Faults=  ");
    LRU(){
        JPanel labelPanel = new JPanel();
        setLayout(new BorderLayout());
        labelPanel.setLayout(new BorderLayout());
        labelPanel.add(obj3label,BorderLayout.WEST);
        labelPanel.add(pagefaults,BorderLayout.EAST);

        model = new DefaultTableModel();
        obj3table = new JTable(model);
        obj3table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(obj3table);
        add(labelPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
    }

    int findLeastRecent(int [][]array, int col, int [] refString){
        int []leastused = new int[array.length];

        for (int i = 0 ; i < leastused.length; i++){
            leastused[i] = Integer.MIN_VALUE;
        }

        for (int j = 0 ; j < array.length ; j++) {
            if (array[j][col] == Integer.MAX_VALUE) {
                return j;
            }
        }

        for (int k = 0 ; k < array.length ; k++) {
            for (int i = 0; i < col; i++) {
                if (refString[i] == array[k][col]){
                    leastused[k] = i ;
                }
            }
        }

        int minimumIndexing = 0;

        for (int i = 0 ; i < leastused.length ; i++){
            if (leastused[minimumIndexing] > leastused[i]){
                minimumIndexing = i;
            }
        }
        for (int i = 0 ; i < leastused.length; i++){

        }


        return minimumIndexing;
    }




    public String[][] obj3arr(int noOfFramest, int []refString){
        int frames[][] = new int[noOfFramest][refString.length];
        FIFO.init(frames);
        int count = 0;
        for( int i = 0 ; i < refString.length ; i++){
            if (!FIFO.chckE(frames, i, refString[i])){
                int replace = findLeastRecent(frames, i , refString);
                System.out.println("in lru");
                FIFO.initRow(frames[replace], refString[i], i);
                count++;
            }
        }


        String [][]f = new String[noOfFramest][refString.length];
        for (int i = 0 ; i < noOfFramest; i++){
            for (int j = 0 ; j < refString.length ; j++){
                if (frames[i][j] == Integer.MAX_VALUE){
                    int flg24=0;
                    System.out.println(" indside lru"+count);
                    f[i][j] = "";
                    continue;
                }
                f[i][j] = Integer.toString(frames[i][j]);
            }
        }

        pagefaults.setText("Page Fault: "+count);
        return f;
    }
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }

}


//      for (int i = 0 ; i < frame.length; i++) {
//            for (int j = 0; j < frame[0].length; j++) {
//                if (frame[i][j] == Integer.MAX_VALUE){
//
//                    continue;
//                }
//
//            }
//
//
//        }
