/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

class Pet implements Serializable {

    int id;
    String name;
    String type;
    int age;

    public Pet(int id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + type + "," + age;
    }

}

class Adopter implements Serializable {

    int id;
    String name;
    String gender;
    int age;
    double salary;
    Pet pet;

    public Adopter(int id, String name, String gender, int age, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Pet getPet() {
        return pet;
    }

    @Override
    public String toString() {
        String Petname = "null";
        if (pet != null) {
            Petname = pet.toString();
        }
        return id + "," + name + "," + gender + "," + age + "," + salary + "," + Petname;
    }
}

class PNode implements Serializable {

    Pet data;
    PNode next;
    PNode prev;

    public PNode(Pet data) {
        this.data = data;
        this.next = null;
        this.prev = null;

    }

}

class ANode implements Serializable {

    Adopter data;
    ANode next, prev;

    public ANode(Adopter data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}

class LinkedList implements Serializable {

    ANode head;
    ANode tail;
    int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(Adopter adopter) {

        ANode node = new ANode(adopter);

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
    }

    int size() {
        return size;
    }

    ANode getHead() {
        return head;
    }

    void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    Adopter[] toArray() {

        Adopter[] arr = new Adopter[size];

        ANode p = head;
        int i = 0;

        while (p != null) {
            arr[i] = p.data;
            p = p.next;
            i++;
        }

        return arr;
    }

    void sortById() {

        Adopter[] arr = toArray();

        for (int j = 0; j < arr.length - 1; j++) {

            for (int i = 0; i < arr.length - 1; i++) {

                if (arr[i].getId() > arr[i + 1].getId()) {

                    Adopter temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }

        clear();

        for (int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
    }
}

class Stack {

    PNode top;
    int size;

    public Stack() {
        top = null;
        size = 0;
    }

    void push(Pet pet) {

        PNode node = new PNode(pet);

        node.next = top;
        top = node;

        size++;
    }

    Pet pop() {

        if (top == null) {
            return null;
        }

        Pet temp = top.data;
        top = top.next;
        size--;

        return temp;
    }

    int size() {
        return size;
    }

    PNode getTop() {
        return top;
    }

    Pet[] toArray() {
        Pet[] arr = new Pet[size];
        PNode a = top;
        int i = 0;
        while (a != null) {
            arr[i] = a.data;
            a = a.next;
            i++;
        }
        return arr;
    }

}

class Queue {

    ANode front;
    ANode last;
    int size;

    public Queue() {
        front = null;
        last = null;
        size = 0;
    }

    void offer(Adopter a) {
        ANode node = new ANode(a);
        if (last == null) {
            front = last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }

    Adopter poll() {
        if (front == null) {
            return null;
        }

        Adopter temp = front.data;

        front = front.next;

        if (front != null) {
            front.prev = null;
        } else {
            last = null;
        }

        size--;

        return temp;
    }

    int size() {
        return size;
    }

    ANode getFront() {
        return front;
    }

    Adopter[] toArray() {
        Adopter[] arr = new Adopter[size];
        ANode a = front;
        int i = 0;

        while (a != null) {
            arr[i] = a.data;
            a = a.next;
            i++;
        }
        return arr;
    }

}

/**
 *
 * @author User
 */
public class NewJFrame1 extends javax.swing.JFrame {

    private LinkedList list1 = new LinkedList();
    private Stack stack1 = new Stack();
    private Queue queue1 = new Queue();
    String str = "abcdefghijklmnopqrstuvwxyz";
    Random r = new Random();
    private Pet currentPet;
    private Adopter currenAdopter;

    private Pet generatePet() {
        int id = r.nextInt(50000) + 10000;
        String name = "";
        for (int i = 0; i < r.nextInt(5) + 2; i++) {
            name += str.charAt(r.nextInt(26));

        }
        String type = "";
        if (r.nextInt(2) == 0) {
            type = "cat";
        } else {
            type = "dog";
        }
        int age = r.nextInt(18) + 2;
        Pet pet = new Pet(id, name, type, age);
        return pet;
    }

    private Adopter generateAdopter() {
        int id = r.nextInt(50000) + 10000;
        String name = "";
        for (int i = 0; i < r.nextInt(5) + 3; i++) {
            name += str.charAt(r.nextInt(26));

        }
        String gender = "";
        if (r.nextInt(2) == 0) {
            gender = "male";
        } else {
            gender = "female";
        }
        int age = r.nextInt(50) + 10;
        double salary = r.nextInt(25000) + 8000;
        Adopter adopter = new Adopter(id, name, gender, age, salary);
        return adopter;
    }

    private void updateTable1() {

        Pet[] pets = stack1.toArray();

        Object[][] data = new Object[pets.length][4];

        for (int i = 0; i < pets.length; i++) {
            data[i][0] = pets[i].getId();
            data[i][1] = pets[i].getName();
            data[i][2] = pets[i].getType();
            data[i][3] = pets[i].getAge();
        }

        String[] columns = {
            "ID",
            "Name",
            "Type",
            "Age"
        };

        javax.swing.table.DefaultTableModel model
                = new javax.swing.table.DefaultTableModel(data, columns);

        jTable1.setModel(model);
    }

    private void updateTable2() {

        Adopter[] adopters = queue1.toArray();

        Object[][] data = new Object[adopters.length][6];

        for (int i = 0; i < adopters.length; i++) {
            data[i][0] = adopters[i].getId();
            data[i][1] = adopters[i].getName();
            data[i][2] = adopters[i].getGender();
            data[i][3] = adopters[i].getAge();
            data[i][4] = adopters[i].getSalary();
            data[i][5] = adopters[i].getPet();

        }

        String[] columns = {
            "ID",
            "Name",
            "Gender",
            "Age",
            "Salary",
            "Pet"
        };

        javax.swing.table.DefaultTableModel model
                = new javax.swing.table.DefaultTableModel(data, columns);

        jTable2.setModel(model);
    }

    private void updateTable3() {

        ANode p = list1.getHead();
        Object[][] data = new Object[list1.size()][6];

        for (int i = 0; i < data.length; i++) {
            data[i][0] = p.data.getId();
            data[i][1] = p.data.getName();
            data[i][2] = p.data.getGender();
            data[i][3] = p.data.getAge();
            data[i][4] = p.data.getSalary();
            data[i][5] = p.data.getPet();

            p = p.next;

        }

        String[] columns = {
            "ID",
            "Name",
            "Gender",
            "Age",
            "Salary",
            "Pet"
        };

        javax.swing.table.DefaultTableModel model
                = new javax.swing.table.DefaultTableModel(data, columns);

        jTable3.setModel(model);
    }
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame1.class.getName());

    /**
     * Creates new form NewJFrame1
     */
    public NewJFrame1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mid_exam_test");

        jTextField1.setText("jTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "name", "type", "age"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("GenPet");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("AddPet");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("RemovePet");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jTextField2.setText("jTextField2");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "name", "gender", "age", "salary", "pet"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton4.setText("Genadopter");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jButton5.setText("AddAdopter");
        jButton5.addActionListener(this::jButton5ActionPerformed);

        jButton6.setText("RemoveAdopter");
        jButton6.addActionListener(this::jButton6ActionPerformed);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "name", "gender", "age", "salary", "pet"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButton7.setText("AssignPet");
        jButton7.addActionListener(this::jButton7ActionPerformed);

        jButton8.setText("Sort");
        jButton8.addActionListener(this::jButton8ActionPerformed);

        jButton9.setText("Save");
        jButton9.addActionListener(this::jButton9ActionPerformed);

        jButton10.setText("Load");
        jButton10.addActionListener(this::jButton10ActionPerformed);

        jLabel1.setText("PET SECTION");

        jLabel2.setText("ADOPTER SECTION");

        jLabel3.setText("ASSIGNED / LINKEDLIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton9)
                                            .addComponent(jButton10)
                                            .addComponent(jButton8)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jScrollPane2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jButton1)
                                                    .addComponent(jButton2)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton6)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jButton4)
                                                    .addComponent(jButton5))
                                                .addGap(12, 12, 12))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2)
                        .addGap(34, 34, 34)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)))
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        currentPet = generatePet();
        jTextField1.setText(currentPet.toString());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (currentPet != null) {
            stack1.push(currentPet);
            updateTable1();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        stack1.pop();
        updateTable1();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        currenAdopter = generateAdopter();
        jTextField2.setText(currenAdopter.toString());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (currenAdopter != null) {
            queue1.offer(currenAdopter);
            updateTable2();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        queue1.poll();
        updateTable2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if (queue1.size() > 0 && stack1.size() > 0) {
            Adopter X = queue1.poll();
            Pet Y = stack1.pop();

            X.pet = Y;
            list1.add(X);
            updateTable2();
            updateTable1();
            updateTable3();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        list1.sortById();
        updateTable3();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(list1);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
            list1 = (LinkedList) in.readObject();
            in.close();
            updateTable3();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewJFrame1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
