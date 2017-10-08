/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulos;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.time.LocalDate;
import java.time.Month;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Calendario extends javax.swing.JDialog {

    private LocalDate fecha;
    /**
     * Creates new form Calendario
     */
    public Calendario(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        LocalDate date=LocalDate.now();
        fecha=null;
        anio.setValue(date.getYear());
        mes.setSelectedIndex(date.getMonthValue()-1);
        semana();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    public Calendario(java.awt.Frame parent, int anio,int mes) {
        super(parent, true);
        initComponents();
        LocalDate date=LocalDate.of(anio, mes, 1);
        fecha=null;
        this.anio.setValue(date.getYear());
        this.mes.setSelectedIndex(date.getMonthValue()-1);
        semana();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void semana(){
        botonEnableFalse();
        int m=1;
        m+=getMesNum(mes.getSelectedItem().toString());
        m+=Integer.parseInt(anio.getValue().toString())%100;
        m+=(int)(Integer.parseInt(anio.getValue().toString())%100)/4;
        m+=getSiglo(Integer.parseInt(anio.getValue().toString()));
        m=m%7;
        if(m==7)m=0;
        m++;
        int d=1,maxdias=0;
        switch(mes.getSelectedItem().toString()){
            //Febrero, salvo en año bisiesto que tiene 29 días. 
            case "FEB":
                GregorianCalendar calendar=new GregorianCalendar();
                if (calendar.isLeapYear(Integer.parseInt(anio.getValue().toString())))
                        maxdias=29;
                else
                        maxdias=28;
                break;
            //Abril, junio, septiembre y noviembre. 
            case "ABR":case "JUN":case "SEP":case "NOV":maxdias=30;break;
            default: maxdias=31;break;
        }
        
        for(int i=m;d<=maxdias;i++){
            setTextBoton(i, d+"");
            d++;
        }
    }
    private void botonEnableFalse(){
        for(int i=1;i<43;i++){
            setTextBoton(i, "");
        }
        
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);
        b13.setEnabled(false);
        b14.setEnabled(false);
        b15.setEnabled(false);
        b16.setEnabled(false);
        b17.setEnabled(false);
        b18.setEnabled(false);
        b19.setEnabled(false);
        b20.setEnabled(false);
        b21.setEnabled(false);
        b22.setEnabled(false);
        b23.setEnabled(false);
        b24.setEnabled(false);
        b25.setEnabled(false);
        b26.setEnabled(false);
        b27.setEnabled(false);
        b28.setEnabled(false);
        b29.setEnabled(false);
        b30.setEnabled(false);
        b31.setEnabled(false);
        b32.setEnabled(false);
        b33.setEnabled(false);
        b34.setEnabled(false);
        b35.setEnabled(false);
        b36.setEnabled(false);
        b37.setEnabled(false);
        b38.setEnabled(false);
        b39.setEnabled(false);
        b40.setEnabled(false);
        b41.setEnabled(false);
        b42.setEnabled(false);
    }
    
    private void setTextBoton(int index,String numDia){
        switch(index){
            case 1:
                b1.setEnabled(true);
                b1.setText(numDia+"");
                break;
            case 2:
                b2.setEnabled(true);
                b2.setText(numDia+"");
                break;
            case 3:
                b3.setEnabled(true);
                b3.setText(numDia+"");
                break;
            case 4:
                b4.setEnabled(true);
                b4.setText(numDia+"");
                break;
            case 5:
                b5.setEnabled(true);
                b5.setText(numDia+"");
                break;
            case 6:
                b6.setEnabled(true);
                b6.setText(numDia+"");
                break;
            case 7:
                b7.setEnabled(true);
                b7.setText(numDia+"");
                break;
            case 8:
                b8.setEnabled(true);
                b8.setText(numDia+"");
                break;
            case 9:
                b9.setEnabled(true);
                b9.setText(numDia+"");
                break;
            case 10:
                b10.setEnabled(true);
                b10.setText(numDia+"");
                break;
            case 11:
                b11.setEnabled(true);
                b11.setText(numDia+"");
                break;
            case 12:
                b12.setEnabled(true);
                b12.setText(numDia+"");
                break;
            case 13:
                b13.setEnabled(true);
                b13.setText(numDia+"");
                break;
            case 14:
                b14.setEnabled(true);
                b14.setText(numDia+"");
                break;
            case 15:
                b15.setEnabled(true);
                b15.setText(numDia+"");
                break;
            case 16:
                b16.setEnabled(true);
                b16.setText(numDia+"");
                break;
            case 17:
                b17.setEnabled(true);
                b17.setText(numDia+"");
                break;
            case 18:
                b18.setEnabled(true);
                b18.setText(numDia+"");
                break;
            case 19:
                b19.setEnabled(true);
                b19.setText(numDia+"");
                break;
            case 20:
                b20.setEnabled(true);
                b20.setText(numDia+"");
                break;
            case 21:
                b21.setEnabled(true);
                b21.setText(numDia+"");
                break;
            case 22:
                b22.setEnabled(true);
                b22.setText(numDia+"");
                break;
            case 23:
                b23.setEnabled(true);
                b23.setText(numDia+"");
                break;
            case 24:
                b24.setEnabled(true);
                b24.setText(numDia+"");
                break;
            case 25:
                b25.setEnabled(true);
                b25.setText(numDia+"");
                break;
            case 26:
                b26.setEnabled(true);
                b26.setText(numDia+"");
                break;
            case 27:
                b27.setEnabled(true);
                b27.setText(numDia+"");
                break;
            case 28:
                b28.setEnabled(true);
                b28.setText(numDia+"");
                break;
            case 29:
                b29.setEnabled(true);
                b29.setText(numDia+"");
                break;
            case 30:
                b30.setEnabled(true);
                b30.setText(numDia+"");
                break;
            case 31:
                b31.setEnabled(true);
                b31.setText(numDia+"");
                break;
            case 32:
                b32.setEnabled(true);
                b32.setText(numDia+"");
                break;
            case 33:
                b33.setEnabled(true);
                b33.setText(numDia+"");
                break;
            case 34:
                b34.setEnabled(true);
                b34.setText(numDia+"");
                break;
            case 35:
                b35.setEnabled(true);
                b35.setText(numDia+"");
                break;
            case 36:
                b36.setEnabled(true);
                b36.setText(numDia+"");
                break;
            case 37:
                b37.setEnabled(true);
                b37.setText(numDia+"");
                break;
            case 38:
                b38.setEnabled(true);
                b38.setText(numDia+"");
                break;
            case 39:
                b39.setEnabled(true);
                b39.setText(numDia+"");
                break;
            case 40:
                b40.setEnabled(true);
                b40.setText(numDia+"");
                break;
            case 41:
                b41.setEnabled(true);
                b41.setText(numDia+"");
                break;
            case 42:
                b42.setEnabled(true);
                b42.setText(numDia+"");
                break;
                
        }
    }
    private int  getMesNum(String mes){
        switch(mes){
            case "ENE":return 0;
            case "FEB":return 3;
            case "MAR":return 3;
            case "ABR":return 6;
            case "MAY":return 1;
            case "JUN":return 4;
            case "JUL":return 6;
            case "AGO":return 2;
            case "SEP":return 5;
            case "OCT":return 0;
            case "NOV":return 3;
            case "DIC":return 5;
        }
        return -1;
    }
    private int getSiglo(int anio){
        if(anio >= 2000 && anio <=2099){
            return 6;
        }else if(anio>=1900 && anio<=1999) {
            return 0;
        }else if(anio>=1800 && anio <=1899 ){
            return 2;
        }
        else if(anio >=2100 && anio <= 2199){
            return 4;
        }else if(anio >=2200 && anio <= 2299){
            return 2;
        }
        return -1;
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b18 = new javax.swing.JButton();
        b19 = new javax.swing.JButton();
        b20 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b25 = new javax.swing.JButton();
        b26 = new javax.swing.JButton();
        b27 = new javax.swing.JButton();
        b28 = new javax.swing.JButton();
        b29 = new javax.swing.JButton();
        b30 = new javax.swing.JButton();
        b31 = new javax.swing.JButton();
        b32 = new javax.swing.JButton();
        b33 = new javax.swing.JButton();
        b34 = new javax.swing.JButton();
        b35 = new javax.swing.JButton();
        b36 = new javax.swing.JButton();
        b37 = new javax.swing.JButton();
        b38 = new javax.swing.JButton();
        b39 = new javax.swing.JButton();
        b40 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        mes = new javax.swing.JComboBox<>();
        anio = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Calendario");
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(0, 7));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DO");
        jPanel1.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LU");
        jPanel1.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MA");
        jPanel1.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MI");
        jPanel1.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("JU");
        jPanel1.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("VI");
        jPanel1.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SA");
        jPanel1.add(jLabel7);

        b1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b1.setText("1");
        b1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1);

        b2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b2.setText("2");
        b2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel1.add(b2);

        b3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b3.setText("3");
        b3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel1.add(b3);

        b4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b4.setText("4");
        b4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        jPanel1.add(b4);

        b5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b5.setText("5");
        b5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel1.add(b5);

        b6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b6.setText("6");
        b6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        jPanel1.add(b6);

        b7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b7.setText("7");
        b7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        jPanel1.add(b7);

        b8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b8.setText("8");
        b8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        jPanel1.add(b8);

        b9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b9.setText("9");
        b9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        jPanel1.add(b9);

        b10.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b10.setText("10");
        b10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b10.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });
        jPanel1.add(b10);

        b11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b11.setText("11");
        b11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        jPanel1.add(b11);

        b12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b12.setText("12");
        b12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });
        jPanel1.add(b12);

        b13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b13.setText("13");
        b13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        jPanel1.add(b13);

        b14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b14.setText("14");
        b14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b14ActionPerformed(evt);
            }
        });
        jPanel1.add(b14);

        b15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b15.setText("15");
        b15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b15ActionPerformed(evt);
            }
        });
        jPanel1.add(b15);

        b16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b16.setText("16");
        b16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b16ActionPerformed(evt);
            }
        });
        jPanel1.add(b16);

        b17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b17.setText("17");
        b17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b17ActionPerformed(evt);
            }
        });
        jPanel1.add(b17);

        b18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b18.setText("18");
        b18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b18ActionPerformed(evt);
            }
        });
        jPanel1.add(b18);

        b19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b19.setText("19");
        b19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b19.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b19ActionPerformed(evt);
            }
        });
        jPanel1.add(b19);

        b20.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b20.setText("20");
        b20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b20ActionPerformed(evt);
            }
        });
        jPanel1.add(b20);

        b21.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b21.setText("21");
        b21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b21ActionPerformed(evt);
            }
        });
        jPanel1.add(b21);

        b22.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b22.setText("22");
        b22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b22ActionPerformed(evt);
            }
        });
        jPanel1.add(b22);

        b23.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b23.setText("23");
        b23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b23ActionPerformed(evt);
            }
        });
        jPanel1.add(b23);

        b24.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b24.setText("24");
        b24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b24.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b24ActionPerformed(evt);
            }
        });
        jPanel1.add(b24);

        b25.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b25.setText("25");
        b25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b25.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b25ActionPerformed(evt);
            }
        });
        jPanel1.add(b25);

        b26.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b26.setText("26");
        b26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b26.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b26ActionPerformed(evt);
            }
        });
        jPanel1.add(b26);

        b27.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b27.setText("27");
        b27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b27.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b27ActionPerformed(evt);
            }
        });
        jPanel1.add(b27);

        b28.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b28.setText("28");
        b28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b28.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b28ActionPerformed(evt);
            }
        });
        jPanel1.add(b28);

        b29.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b29.setText("29");
        b29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b29.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b29ActionPerformed(evt);
            }
        });
        jPanel1.add(b29);

        b30.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b30.setText("30");
        b30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b30.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b30ActionPerformed(evt);
            }
        });
        jPanel1.add(b30);

        b31.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b31.setText("31");
        b31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b31.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b31ActionPerformed(evt);
            }
        });
        jPanel1.add(b31);

        b32.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b32.setText("32");
        b32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b32.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b32ActionPerformed(evt);
            }
        });
        jPanel1.add(b32);

        b33.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b33.setText("33");
        b33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b33.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b33ActionPerformed(evt);
            }
        });
        jPanel1.add(b33);

        b34.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b34.setText("34");
        b34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b34.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b34ActionPerformed(evt);
            }
        });
        jPanel1.add(b34);

        b35.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b35.setText("35");
        b35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b35.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b35ActionPerformed(evt);
            }
        });
        jPanel1.add(b35);

        b36.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b36.setText("36");
        b36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b36.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b36ActionPerformed(evt);
            }
        });
        jPanel1.add(b36);

        b37.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b37.setText("37");
        b37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b37.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b37ActionPerformed(evt);
            }
        });
        jPanel1.add(b37);

        b38.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b38.setText("38");
        b38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b38.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b38ActionPerformed(evt);
            }
        });
        jPanel1.add(b38);

        b39.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b39.setText("39");
        b39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b39.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b39ActionPerformed(evt);
            }
        });
        jPanel1.add(b39);

        b40.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b40.setText("40");
        b40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b40.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b40ActionPerformed(evt);
            }
        });
        jPanel1.add(b40);

        b41.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b41.setText("41");
        b41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b41.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b41ActionPerformed(evt);
            }
        });
        jPanel1.add(b41);

        b42.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        b42.setText("42");
        b42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        b42.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b42ActionPerformed(evt);
            }
        });
        jPanel1.add(b42);

        mes.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" }));
        mes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mesActionPerformed(evt);
            }
        });
        jPanel2.add(mes);

        anio.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        anio.setModel(new javax.swing.SpinnerNumberModel(2003, 2000, 2099, 1));
        anio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                anioStateChanged(evt);
            }
        });
        jPanel2.add(anio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton(JButton boton){
        try{
            int dia=Integer.parseInt(boton.getText());
            fecha= LocalDate.of(Integer.parseInt(anio.getValue().toString()),mes.getSelectedIndex()+1 , dia);
            this.dispose();
        }catch(Exception e){
            
        }
    }
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed

        boton(b1);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed

        boton(b2);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed

        boton(b3);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed

        boton(b4);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed

        boton(b5);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed

        boton(b6);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        boton(b7);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        boton(b8);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        boton(b9);
    }//GEN-LAST:event_b9ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        boton(b10);
    }//GEN-LAST:event_b10ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        boton(b11);
    }//GEN-LAST:event_b11ActionPerformed

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        boton(b12);
    }//GEN-LAST:event_b12ActionPerformed

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        boton(b13);
    }//GEN-LAST:event_b13ActionPerformed

    private void b14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b14ActionPerformed
        boton(b14);
    }//GEN-LAST:event_b14ActionPerformed

    private void b15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b15ActionPerformed
        boton(b15);
    }//GEN-LAST:event_b15ActionPerformed

    private void b16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b16ActionPerformed
        boton(b16);
    }//GEN-LAST:event_b16ActionPerformed

    private void b17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b17ActionPerformed
        boton(b17);
    }//GEN-LAST:event_b17ActionPerformed

    private void b18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b18ActionPerformed
        boton(b18);
    }//GEN-LAST:event_b18ActionPerformed

    private void b19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b19ActionPerformed
        boton(b19);
    }//GEN-LAST:event_b19ActionPerformed

    private void b20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b20ActionPerformed
        boton(b20);
    }//GEN-LAST:event_b20ActionPerformed

    private void b21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b21ActionPerformed
        boton(b21);
    }//GEN-LAST:event_b21ActionPerformed

    private void b22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b22ActionPerformed
        boton(b22);
    }//GEN-LAST:event_b22ActionPerformed

    private void b23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b23ActionPerformed
        boton(b23);
    }//GEN-LAST:event_b23ActionPerformed

    private void b24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b24ActionPerformed
        boton(b24);
    }//GEN-LAST:event_b24ActionPerformed

    private void b25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b25ActionPerformed
        boton(b25);
    }//GEN-LAST:event_b25ActionPerformed

    private void b26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b26ActionPerformed
        boton(b26);
    }//GEN-LAST:event_b26ActionPerformed

    private void b27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b27ActionPerformed
        boton(b27);
    }//GEN-LAST:event_b27ActionPerformed

    private void b28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b28ActionPerformed
        boton(b28);
    }//GEN-LAST:event_b28ActionPerformed

    private void b29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b29ActionPerformed
        boton(b29);
    }//GEN-LAST:event_b29ActionPerformed

    private void b30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b30ActionPerformed
        boton(b30);
    }//GEN-LAST:event_b30ActionPerformed

    private void b31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b31ActionPerformed
        boton(b31);
    }//GEN-LAST:event_b31ActionPerformed

    private void b32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b32ActionPerformed
        boton(b32);
    }//GEN-LAST:event_b32ActionPerformed

    private void b33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b33ActionPerformed
        boton(b33);
    }//GEN-LAST:event_b33ActionPerformed

    private void b34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b34ActionPerformed
        boton(b34);
    }//GEN-LAST:event_b34ActionPerformed

    private void b35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b35ActionPerformed
        boton(b35);
    }//GEN-LAST:event_b35ActionPerformed

    private void b36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b36ActionPerformed
        boton(b36);
    }//GEN-LAST:event_b36ActionPerformed

    private void b37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b37ActionPerformed
        boton(b37);
    }//GEN-LAST:event_b37ActionPerformed

    private void b38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b38ActionPerformed
        boton(b38);
    }//GEN-LAST:event_b38ActionPerformed

    private void b39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b39ActionPerformed
        boton(b39);
    }//GEN-LAST:event_b39ActionPerformed

    private void b40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b40ActionPerformed
        boton(b40);
    }//GEN-LAST:event_b40ActionPerformed

    private void b41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b41ActionPerformed
        boton(b41);
    }//GEN-LAST:event_b41ActionPerformed

    private void b42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b42ActionPerformed
        boton(b42);
    }//GEN-LAST:event_b42ActionPerformed

    private void mesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mesActionPerformed
        semana();
    }//GEN-LAST:event_mesActionPerformed

    private void anioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_anioStateChanged
        semana();
    }//GEN-LAST:event_anioStateChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner anio;
    private javax.swing.JButton b1;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b18;
    private javax.swing.JButton b19;
    private javax.swing.JButton b2;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b28;
    private javax.swing.JButton b29;
    private javax.swing.JButton b3;
    private javax.swing.JButton b30;
    private javax.swing.JButton b31;
    private javax.swing.JButton b32;
    private javax.swing.JButton b33;
    private javax.swing.JButton b34;
    private javax.swing.JButton b35;
    private javax.swing.JButton b36;
    private javax.swing.JButton b37;
    private javax.swing.JButton b38;
    private javax.swing.JButton b39;
    private javax.swing.JButton b4;
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private javax.swing.JButton b42;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> mes;
    // End of variables declaration//GEN-END:variables

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
