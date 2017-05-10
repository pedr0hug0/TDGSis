/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;


import CTR.tdgCTR;
import DTO.CoresDTO;
import DTO.ItemPedidoDTO;
import DTO.ProdutoDTO;
import dao.ItemPedidoDAO;
import dao.PedidoDAO;
import dao.ProdutoCoresDAO;
import dao.ProdutoDAO;
import dao.RelatorioDAO;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Cursor;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.KeyboardFocusManager;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PeDr0_HuG0
 */
public class ItemPedidoVIEW extends javax.swing.JFrame {

    /**
     * Creates new form ItemPedido
     */
    public ItemPedidoVIEW() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        
        
        
        
        ultimo_pedido();//setando ultimo n_pedido no txt_n_pedido
        btn_Dividir_Sortido_Especial.setVisible(false);
        btn_Dividir_Sortido_Normal.setVisible(false);
        btn_Dividir_Sortido_Juvenil.setVisible(false);
        
        //apertar enter = tab
        tdgCTR.considerarEnterComoTab(txt_n_pedido);
        tdgCTR.considerarEnterComoTab(txt_loja);
        tdgCTR.considerarEnterComoTab(txt_ref);
        tdgCTR.considerarEnterComoTab(txt_qtd_total);
        tdgCTR.considerarEnterComoTab(jRadioButtonManual);
        tdgCTR.considerarEnterComoTab(jRadioButtonGrade);
        tdgCTR.considerarEnterComoTab(jRadioButtonAutomatico);
        
        //manual
        tdgCTR.considerarEnterComoTab(txt_t1_grade);
        tdgCTR.considerarEnterComoTab(txt_t2_grade);
        tdgCTR.considerarEnterComoTab(txt_t3_grade);
        tdgCTR.considerarEnterComoTab(txt_t4_grade);
        
        //dividir grade normal
        tdgCTR.considerarEnterComoTab(jRadioButton1111);
        tdgCTR.considerarEnterComoTab(jRadioButton1122);
        tdgCTR.considerarEnterComoTab(jRadioButton1221);
        tdgCTR.considerarEnterComoTab(jRadioButton2112);
        tdgCTR.considerarEnterComoTab(jRadioButton2211);
        
        //dividir grade ESPECIAL
        tdgCTR.considerarEnterComoTab(jRadioButton111);
        tdgCTR.considerarEnterComoTab(jRadioButton112);
        tdgCTR.considerarEnterComoTab(jRadioButton122);
        tdgCTR.considerarEnterComoTab(jRadioButton211);
        tdgCTR.considerarEnterComoTab(jRadioButton221);
        tdgCTR.considerarEnterComoTab(jRadioButton121);
        
       //sortido
        tdgCTR.considerarEnterComoTab(txt_qtd_total_sortido);
        
        
        //manual sortido
        tdgCTR.considerarEnterComoTab(txt_t1_grade_sortido);
        tdgCTR.considerarEnterComoTab(txt_t2_grade_sortido);
        tdgCTR.considerarEnterComoTab(txt_t3_grade_sortido);
        tdgCTR.considerarEnterComoTab(txt_t4_grade_sortido);
        
        
        //pegar lista de categoria
        listar_categoria_produto();
        
    }
    
      
    private void listar_categoria_produto(){
        ProdutoDAO dao = new ProdutoDAO();
        
        for (ProdutoDTO p : dao.listarCategorias() ) {
            jComboBox_Categoria.addItem(p.getCategoria());
        }
    }
    
    
    
    public void ultimo_pedido(){
        ItemPedidoDAO dao = new ItemPedidoDAO();
        ItemPedidoDTO itempedido = dao.getN_Pedido();
        txt_n_pedido.setText(itempedido.getN_pedido().toString());
    }
                
    
    
    public void dividir(double g1, double g2, double g3, double g4){
        
        
        Double qtd_total;
        Integer qtd_cor;
        Integer qtd_tamanho;
        
        Double qtd_por_cor;
        Double qtd_por_tamanho;
        
        Integer qtd_por_cor_inteiro;
        Integer qtd_por_tamanho_inteiro;
        Integer qtd_total_inteiro;
        
        Double sobra_por_cor;
        Integer sobra_por_cor_inteiro;
        Double sobra_por_tamanho;

        Integer tamanho_que_parou;

        
        DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
        
       
 
        
        //SE COR = TODAS
        if (combobox_cor.getSelectedItem().equals("TODAS")){
            
           System.out.println("Cor Selecionada: "+combobox_cor.getSelectedItem());
           //calculando qtd por qtd_cor_existente(todas)
 //*********qtd_total = Double.parseDouble(txt_qtd_total.getText());
           //pegando valor inteiro para fazer calculo da sobra
//**********qtd_total_inteiro = Integer.parseInt(txt_qtd_total.getText());
           qtd_cor = combobox_cor.getItemCount()-1;
          
           
           
              //********* DIVIDIR TAMANHOS DE ACORDO COM A GRADE INFORMADA *********  
           //Pegando quantos tamanhos tem
           qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
           
           //colocar if para tamanho = 3 (especial)
           
            //pegando grades inseridas
            Integer t1_grade_inteiro = (int)g1;
            Integer t2_grade_inteiro = (int)g2;
            Integer t3_grade_inteiro = (int)g3;
            Integer t4_grade_inteiro = (int)g4;
            
            Double t1_grade = g1;
            Double t2_grade = g2;
            Double t3_grade = g3;
            Double t4_grade = g4;
           
            //calculando sobra dos tamanhos
            Double qtd_t1 = t1_grade/qtd_cor;
            Double qtd_t2 = t2_grade/qtd_cor;
            Double qtd_t3 = t3_grade/qtd_cor;
            Double qtd_t4 = t4_grade/qtd_cor;
           //qtd por tamanho int
           Integer qtd_t1_inteiro = t1_grade_inteiro/qtd_cor;
           Integer qtd_t2_inteiro = t2_grade_inteiro/qtd_cor;
           Integer qtd_t3_inteiro = t3_grade_inteiro/qtd_cor;
           Integer qtd_t4_inteiro = t4_grade_inteiro/qtd_cor;
           //sobra
           //t1
           Double sobra_t1 = (qtd_t1-qtd_t1_inteiro)*qtd_cor;
           System.out.println("Sobra t1: "+sobra_t1);
           Integer sobra_t1_inteiro = (int)Math.round(sobra_t1);
           
           //t2
           Double sobra_t2 = (qtd_t2-qtd_t2_inteiro)*qtd_cor;
            System.out.println("Sobra t2: "+sobra_t2);
           Integer sobra_t2_inteiro = (int)Math.round(sobra_t2);
           
           //t3
           Double sobra_t3 = (qtd_t3-qtd_t3_inteiro)*qtd_cor;
           System.out.println("Sobra t3: "+sobra_t3);
           Integer sobra_t3_inteiro = (int)Math.round(sobra_t3);
           
           //t4
           Double sobra_t4 = (qtd_t4-qtd_t4_inteiro)*qtd_cor;
           System.out.println("Sobra t4: "+sobra_t4);
           Integer sobra_t4_inteiro = (int)Math.round(sobra_t4);
           
            
            //adicionando qtds no array de cada tamanho de acordo com a qtd de cores
            int[] t1 = new int[15];
            int[] t2 = new int[15];
            int[] t3 = new int[15];
            int[] t4 = new int[15];
            
            //SE I = 1 ENTAO COR 1..
            for (int i = 1; i <= qtd_cor; i++) {
                t1[i]=t1_grade_inteiro/qtd_cor;
                t2[i]=t2_grade_inteiro/qtd_cor;
                t3[i]=t3_grade_inteiro/qtd_cor;
                t4[i]=t4_grade_inteiro/qtd_cor;
                
            }
            System.out.println("Divisões iguais:");
            System.out.println(" T1: "+t1[1]+" T2: "+t2[1]+" T3: "+t3[1]+" T4: "+t4[1]);
            
            
            //fazer iniciar na cor que mais tem.
            //select temp table, cor e saldo da ref ordenado por saldo_t1(), cor()
            //se uma cor tiver mais no t1, não significa que ela tem menos no t2, ou seja, pau.
            //deixei sorteando mesmo até uma nova ideia.
            //sortear_cor()
            
            //sortear cor para iniciar
            Random gerador = new Random();
            Integer cor_que_parou = gerador.nextInt(qtd_cor) + 1;//se for para sortear na qtd de cores existente trocar o 3 para qtd_cor
            System.out.println("--------------------------Cor que parou gerado: "+cor_que_parou);
            
            //Integer cor_que_parou = 1;
            
            //************Dividindo cores do ---T1---- ************
            
                    System.out.println("Adicionando sobra T1 começando na cor: "+cor_que_parou);
                    System.out.println("sobra t1 inteiro: "+sobra_t1_inteiro);                    
                    for (int i = 1; i <= sobra_t1_inteiro; i++) {
                        System.out.println("Adicionando sobra: "+i+"");
                        
                        t1[cor_que_parou]=t1[cor_que_parou]+1;
                        System.out.println("T1["+cor_que_parou+"]+1");
                        cor_que_parou++;
                            if (cor_que_parou>qtd_cor){
                                cor_que_parou=1;
                            }
                        System.out.println("cor que parou: "+cor_que_parou);
                        
                    }                                  
                    //mostrando
                     for (int i = 1; i <= qtd_cor; i++) {
                        System.out.println(" COR "+i+": T1: "+t1[i]+" T2: "+t2[i]+" T3: "+t3[i]+" T4: "+t4[i]);
                     }
                
              
            //*************FIM DIVIDIR CORES --T1--- ***********
            
           //************Dividindo cores do ---T2---- ************
            
                    System.out.println("Adicionando sobra T2 começando na cor: "+cor_que_parou);
                    System.out.println("sobra t2 inteiro: "+sobra_t2_inteiro);                    
                    for (int i = 1; i <= sobra_t2_inteiro; i++) {
                        System.out.println("Adicionando sobra: "+i+"");
                        
                        t2[cor_que_parou]=t2[cor_que_parou]+1;
                        System.out.println("T2["+cor_que_parou+"]+1");
                        cor_que_parou++;
                            if (cor_que_parou>qtd_cor){
                                cor_que_parou=1;
                            }
                        System.out.println("cor que parou: "+cor_que_parou);
                        
                    }                                  
                    //mostrando
                     for (int i = 1; i <= qtd_cor; i++) {
                        System.out.println(" COR "+i+": T1: "+t1[i]+" T2: "+t2[i]+" T3: "+t3[i]+" T4: "+t4[i]);
                     }
                
              
            //*************FIM DIVIDIR CORES --T2--- ***********
            
            
            //************Dividindo cores do ---T3---- ************
            
                    System.out.println("Adicionando sobra T3 começando na cor: "+cor_que_parou);
                    System.out.println("sobra t3 inteiro: "+sobra_t3_inteiro);                    
                    for (int i = 1; i <= sobra_t3_inteiro; i++) {
                        System.out.println("Adicionando sobra: "+i+"");
                        
                        t3[cor_que_parou]=t3[cor_que_parou]+1;
                        System.out.println("T3["+cor_que_parou+"]+1");
                        cor_que_parou++;
                            if (cor_que_parou>qtd_cor){
                                cor_que_parou=1;
                            }
                        System.out.println("cor que parou: "+cor_que_parou);
                        
                    }                                  
                    //mostrando
                     for (int i = 1; i <= qtd_cor; i++) {
                        System.out.println(" COR "+i+": T1: "+t1[i]+" T2: "+t2[i]+" T3: "+t3[i]+" T4: "+t4[i]);
                     }
                
              
            //*************FIM DIVIDIR CORES --T3--- ***********
            
            //************Dividindo cores do ---T4---- ************
            
                    System.out.println("Adicionando sobra T4 começando na cor: "+cor_que_parou);
                    System.out.println("sobra t4 inteiro: "+sobra_t4_inteiro);                    
                    for (int i = 1; i <= sobra_t4_inteiro; i++) {
                        System.out.println("Adicionando sobra: "+i+"");
                        
                        t4[cor_que_parou]=t4[cor_que_parou]+1;
                        System.out.println("T4["+cor_que_parou+"]+1");
                        cor_que_parou++;
                            if (cor_que_parou>qtd_cor){
                                cor_que_parou=1;
                            }
                        System.out.println("cor que parou: "+cor_que_parou);
                        
                    }                                  
                    //mostrando
                     for (int i = 1; i <= qtd_cor; i++) {
                        System.out.println(" COR "+i+": T1: "+t1[i]+" T2: "+t2[i]+" T3: "+t3[i]+" T4: "+t4[i]);
                     }
                
              
            //*************FIM DIVIDIR CORES --T4--- ***********
            
            
            
       //**********RESULTADO FINAL:     
             
            if (qtd_tamanho==4){ //se normal
                        
                        //Imprimir Qtd de acordo com qtd de cores
                        for (int y = 1; y <= qtd_cor; y++) {
                                
                                //System.out.println("REF: "+txt_ref.getText()+" T1: "+t1[y]+" T2: "+t2[y]+" T3: "+t3[y]+" T4: "+t4[y]+ " Cor: "+combobox_cor.getItemAt(y)+" Tipo: "+combobox_cor.getSelectedItem());
                                //int total = t1[y]+t2[y]+t3[y]+t4[y];
                                //model.addRow(new Object[]{"id",txt_ref.getText(), combobox_cor.getItemAt(y), t1[y], t2[y], t3[y], t4[y], combobox_cor.getSelectedItem(), total});
                                
                                //se cor conter nenhum tamanho, não adiciona.
                                if (t1[y]==0 && t2[y]==0 && t3[y]==0 && t4[y]==0){
                                    System.out.println("Cor vazia, não adiciona");
                                }else{
                                //adicionando ao banco de dados e listando
                                adiciona_item(y, t1[y],t2[y],t3[y],t4[y]);
                                }
                        }
                }
                else { //tamanho != 4 (ESPECIAL) //nem precisava mas deixei :S
                       //Imprimir Qtd de acordo com qtd de cores
                        for (int y = 1; y <= qtd_cor; y++) {
                                //System.out.println("REF: "+txt_ref.getText()+" T1: "+t1[y]+" T2: "+t2[y]+" T3: "+t3[y]+" Cor: "+combobox_cor.getItemAt(y)+" Tipo: "+combobox_cor.getSelectedItem());
                                //int total = t1[y]+t2[y]+t3[y];
                                //model.addRow(new Object[]{"id",txt_ref.getText(), combobox_cor.getItemAt(y), t1[y], t2[y], t3[y], t4[y], combobox_cor.getSelectedItem(), total});
                                
                                 //se cor conter nenhum tamanho, não adiciona.
                                if (t1[y]==0 && t2[y]==0 && t3[y]==0 && t4[y]==0){
                                    System.out.println("Cor vazia, não adiciona");
                                }else{
                                //adicionando ao banco de dados e listando
                                adiciona_item(y, t1[y],t2[y],t3[y],t4[y]);
                                }
                        }
                }
            
        }else{//SE FOR UMA COR
        
            
            //***************************UMA COR********************
           System.out.println("Uma cor");
        
           System.out.println("Cor Selecionada: "+combobox_cor.getSelectedItem());
           
           /***** não usa mais:
           //calculando qtd por uma cor
           //qtd_total = Double.parseDouble(txt_qtd_total.getText());
           //qtd_total_inteiro = Integer.parseInt(txt_qtd_total.getText());
           */ 
           
           //Pegando quantos tamanhos tem
           qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
           
           //pegando grades inseridas
                        Integer t1_grade_inteiro = (int)g1;
                        Integer t2_grade_inteiro = (int)g2;
                        Integer t3_grade_inteiro = (int)g3;
                        Integer t4_grade_inteiro = (int)g4; 
           
           //SE qtd tamanho = 4 (normal)
                if (qtd_tamanho==4){
                        
                        //resultado  
                        System.out.println("REF: "+txt_ref.getText()+" Cor: "+combobox_cor.getSelectedItem()+" T1: "+t1_grade_inteiro+" T2: "+t2_grade_inteiro+" T3: "+t3_grade_inteiro+" T4: "+t4_grade_inteiro);
                        
                        //int total = t1_grade_inteiro+t2_grade_inteiro+t3_grade_inteiro+t4_grade_inteiro;
                        //model.addRow(new Object[]{"id",txt_ref.getText(), combobox_cor.getSelectedItem(), t1_grade_inteiro, t2_grade_inteiro, t3_grade_inteiro, t4_grade_inteiro, combobox_cor.getSelectedItem(), total});
                        
                        
                        //adicionando ao banco de dados
                        adiciona_item(combobox_cor.getSelectedIndex(), t1_grade_inteiro,t2_grade_inteiro,t3_grade_inteiro,t4_grade_inteiro);
                }
                else { //tamanho != 4 (ESPECIAL)
                        System.out.println("REF: "+txt_ref.getText()+" Cor: "+combobox_cor.getSelectedItem()+" T1: "+t1_grade_inteiro+" T2: "+t2_grade_inteiro+" T3: "+t3_grade_inteiro);
                        //int total = t1_grade_inteiro+t2_grade_inteiro+t3_grade_inteiro;
                        //model.addRow(new Object[]{"id",txt_ref.getText(), combobox_cor.getSelectedItem(), t1_grade_inteiro, t2_grade_inteiro, t3_grade_inteiro, t4_grade_inteiro, combobox_cor.getSelectedItem(), total});
                        
                        //adicionando ao banco de dados
                        adiciona_item(combobox_cor.getSelectedIndex(), t1_grade_inteiro,t2_grade_inteiro,t3_grade_inteiro,t4_grade_inteiro);
                }
        }//fim uma cor
        
        
    } //fim Metodo Dividir();
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGRADE = new javax.swing.ButtonGroup();
        buttonGroupTipoDivisao = new javax.swing.ButtonGroup();
        buttonGroup_Sortido = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        btn_Listar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_total_itens = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jCheckBox_loja = new javax.swing.JCheckBox();
        jPanel_Grade = new javax.swing.JPanel();
        jRadioButton1111 = new javax.swing.JRadioButton();
        jRadioButton1221 = new javax.swing.JRadioButton();
        jRadioButton1122 = new javax.swing.JRadioButton();
        jRadioButton2211 = new javax.swing.JRadioButton();
        jRadioButton2112 = new javax.swing.JRadioButton();
        btn_Dividir_Grade = new javax.swing.JButton();
        jPanel_Manual = new javax.swing.JPanel();
        jLabelT1 = new javax.swing.JLabel();
        txt_t1_grade = new javax.swing.JTextField();
        jLabelT2 = new javax.swing.JLabel();
        txt_t2_grade = new javax.swing.JTextField();
        jLabelT3 = new javax.swing.JLabel();
        txt_t3_grade = new javax.swing.JTextField();
        jLabelT4 = new javax.swing.JLabel();
        txt_t4_grade = new javax.swing.JTextField();
        btn_Dividir_Manual = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox_manter = new javax.swing.JCheckBox();
        jPanel_Grade_Especial = new javax.swing.JPanel();
        jRadioButton111 = new javax.swing.JRadioButton();
        jRadioButton122 = new javax.swing.JRadioButton();
        jRadioButton112 = new javax.swing.JRadioButton();
        jRadioButton221 = new javax.swing.JRadioButton();
        jRadioButton211 = new javax.swing.JRadioButton();
        btn_Dividir_Grade_Especial = new javax.swing.JButton();
        jRadioButton121 = new javax.swing.JRadioButton();
        jPanel_Automatico = new javax.swing.JPanel();
        btn_Dividir_Automatico = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_n_pedido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_ref = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_qtd_total = new javax.swing.JTextField();
        combobox_cor = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jRadioButtonManual = new javax.swing.JRadioButton();
        jRadioButtonGrade = new javax.swing.JRadioButton();
        jRadioButtonAutomatico = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txt_qtd_tamanho = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_qtd_cores = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_Tipo_tamanho = new javax.swing.JTextField();
        btn_remove = new javax.swing.JButton();
        jComboBox_Cores = new javax.swing.JComboBox();
        btn_adiciona = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txt_loja = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_n_pedido_original = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_loja_original = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_loja_novo = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        txt_n_pedido_novo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox_todas_cores = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        jComboBox_excluir_cor = new javax.swing.JComboBox();
        btn_add_cor_excluir = new javax.swing.JButton();
        btn_remove_cor_excluir = new javax.swing.JButton();
        jPanel_SORTIDO = new javax.swing.JPanel();
        btn_Dividir_Sortido_Normal = new javax.swing.JButton();
        jComboBox_refs = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_Categoria = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_qtd_total_sortido = new javax.swing.JTextField();
        txt_qtd_ref = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btn_Dividir_Sortido_Juvenil = new javax.swing.JButton();
        btn_Dividir_Sortido_Especial = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_Tipo_tamanho_categoria = new javax.swing.JTextField();
        jCheckBox_disponivel = new javax.swing.JCheckBox();
        txt_dias = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel_Manual1 = new javax.swing.JPanel();
        jLabelT5 = new javax.swing.JLabel();
        txt_t1_grade_sortido = new javax.swing.JTextField();
        jLabelT6 = new javax.swing.JLabel();
        txt_t2_grade_sortido = new javax.swing.JTextField();
        jLabelT7 = new javax.swing.JLabel();
        txt_t3_grade_sortido = new javax.swing.JTextField();
        jLabelT8 = new javax.swing.JLabel();
        txt_t4_grade_sortido = new javax.swing.JTextField();
        btn_Dividir_Sortido_Manual = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jRadioButton_Sortido_Automatico = new javax.swing.JRadioButton();
        jRadioButton_Sortido_Manual = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txt_saldo = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jCheckBox_sortido_maximo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Itens Pedido");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens cadastrados"));
        jPanel5.setFocusable(false);

        btn_Listar.setText("Listar");
        btn_Listar.setFocusable(false);
        btn_Listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListarActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.setFocusable(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "REF", "COR", "T1", "T2", "T3", "T4", "COR_ORIGINAL", "TOTAL", "LOJA"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jScrollPane1.setViewportView(jTable1);
        //ocultar coluna ID da tabela
        jTable1.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        //fim ocultar

        jButton3.setText("Limpar");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Imprimir direto");
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Total Itens");

        txt_total_itens.setEditable(false);
        txt_total_itens.setFocusable(false);

        jButton6.setText("Relatorio Pedido");
        jButton6.setFocusable(false);
        jButton6.setRequestFocusEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jCheckBox_loja.setText("Ordenar por loja");
        jCheckBox_loja.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_Listar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox_loja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_itens, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_total_itens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Listar)
                        .addComponent(btn_excluir)
                        .addComponent(jButton3)
                        .addComponent(jButton1)
                        .addComponent(jButton6)
                        .addComponent(jCheckBox_loja)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel_Grade.setBorder(javax.swing.BorderFactory.createTitledBorder("Dividir por Grade"));

        buttonGroupGRADE.add(jRadioButton1111);
        jRadioButton1111.setText("1 1 1 1");
        jRadioButton1111.setFocusable(false);
        jRadioButton1111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1111ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton1221);
        jRadioButton1221.setText("1 2 2 1");
        jRadioButton1221.setFocusable(false);
        jRadioButton1221.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1221ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton1122);
        jRadioButton1122.setText("1 1 2 2");
        jRadioButton1122.setFocusable(false);
        jRadioButton1122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1122ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton2211);
        jRadioButton2211.setText("2 2 1 1");
        jRadioButton2211.setFocusable(false);
        jRadioButton2211.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2211ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton2112);
        jRadioButton2112.setText("2 1 1 2");
        jRadioButton2112.setFocusable(false);
        jRadioButton2112.setNextFocusableComponent(jRadioButton1111);
        jRadioButton2112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2112ActionPerformed(evt);
            }
        });

        btn_Dividir_Grade.setText("Dividir por Grade");
        btn_Dividir_Grade.setNextFocusableComponent(txt_ref);
        btn_Dividir_Grade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_GradeFocusGained(evt);
            }
        });
        btn_Dividir_Grade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_GradeActionPerformed(evt);
            }
        });

        jPanel_Grade.setVisible(false);

        javax.swing.GroupLayout jPanel_GradeLayout = new javax.swing.GroupLayout(jPanel_Grade);
        jPanel_Grade.setLayout(jPanel_GradeLayout);
        jPanel_GradeLayout.setHorizontalGroup(
            jPanel_GradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GradeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jRadioButton1111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1221)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2211)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2112)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_GradeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Dividir_Grade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_GradeLayout.setVerticalGroup(
            jPanel_GradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GradeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_GradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1111)
                    .addComponent(jRadioButton1221)
                    .addComponent(jRadioButton1122)
                    .addComponent(jRadioButton2211)
                    .addComponent(jRadioButton2112))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Dividir_Grade)
                .addContainerGap())
        );

        jPanel_Manual.setBorder(javax.swing.BorderFactory.createTitledBorder("Manual"));

        jLabelT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelT1.setText("10/P/G1");

        txt_t1_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t1_gradeFocusLost(evt);
            }
        });

        jLabelT2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelT2.setText("12/M/G2");

        txt_t2_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t2_gradeFocusLost(evt);
            }
        });

        jLabelT3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelT3.setText("14/G/G3");

        txt_t3_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t3_gradeFocusLost(evt);
            }
        });
        txt_t3_grade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_t3_gradeKeyPressed(evt);
            }
        });

        jLabelT4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelT4.setText("16/GG");

        txt_t4_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t4_gradeFocusLost(evt);
            }
        });

        btn_Dividir_Manual.setText("Dividir");
        btn_Dividir_Manual.setNextFocusableComponent(txt_ref);
        btn_Dividir_Manual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_ManualFocusGained(evt);
            }
        });
        btn_Dividir_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_ManualActionPerformed(evt);
            }
        });

        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox_manter.setText("Manter quantidades");
        jCheckBox_manter.setFocusable(false);
        jCheckBox_manter.setRequestFocusEnabled(false);

        jPanel_Manual.setVisible(false);

        javax.swing.GroupLayout jPanel_ManualLayout = new javax.swing.GroupLayout(jPanel_Manual);
        jPanel_Manual.setLayout(jPanel_ManualLayout);
        jPanel_ManualLayout.setHorizontalGroup(
            jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ManualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ManualLayout.createSequentialGroup()
                        .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t1_grade, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t2_grade, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t3_grade, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t4_grade, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel_ManualLayout.createSequentialGroup()
                        .addComponent(btn_Dividir_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel_ManualLayout.createSequentialGroup()
                        .addComponent(jCheckBox_manter)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel_ManualLayout.setVerticalGroup(
            jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ManualLayout.createSequentialGroup()
                .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelT1)
                    .addComponent(jLabelT2)
                    .addComponent(jLabelT3)
                    .addComponent(jLabelT4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_t1_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t2_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t3_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t4_grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_manter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_ManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Dividir_Manual)
                    .addComponent(jButton2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txt_t1_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t1_grade.selectAll();
            }
        });
        txt_t2_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t2_grade.selectAll();
            }
        });
        txt_t3_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t3_grade.selectAll();
            }
        });
        txt_t4_grade.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t4_grade.selectAll();
            }
        });

        jPanel_Grade_Especial.setBorder(javax.swing.BorderFactory.createTitledBorder("Dividir por Grade [Especial]"));

        buttonGroupGRADE.add(jRadioButton111);
        jRadioButton111.setText("1 1 1");
        jRadioButton111.setFocusable(false);
        jRadioButton111.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton111ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton122);
        jRadioButton122.setText("1 2 2");
        jRadioButton122.setFocusable(false);
        jRadioButton122.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton122ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton112);
        jRadioButton112.setText("1 1 2");
        jRadioButton112.setFocusable(false);
        jRadioButton112.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton112ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton221);
        jRadioButton221.setText("2 2 1 ");
        jRadioButton221.setFocusable(false);
        jRadioButton221.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton221ActionPerformed(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton211);
        jRadioButton211.setText("2 1 1 ");
        jRadioButton211.setFocusable(false);
        jRadioButton211.setNextFocusableComponent(jRadioButton111);
        jRadioButton211.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton211ActionPerformed(evt);
            }
        });

        btn_Dividir_Grade_Especial.setText("Dividir por Grade");
        btn_Dividir_Grade_Especial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_Grade_EspecialActionPerformed(evt);
            }
        });
        btn_Dividir_Grade_Especial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_Grade_EspecialFocusGained(evt);
            }
        });

        buttonGroupGRADE.add(jRadioButton121);
        jRadioButton121.setText("1 2 1 ");
        jRadioButton121.setFocusable(false);
        jRadioButton121.setNextFocusableComponent(jRadioButton111);
        jRadioButton121.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton121ActionPerformed(evt);
            }
        });

        jPanel_Grade_Especial.setVisible(false);

        javax.swing.GroupLayout jPanel_Grade_EspecialLayout = new javax.swing.GroupLayout(jPanel_Grade_Especial);
        jPanel_Grade_Especial.setLayout(jPanel_Grade_EspecialLayout);
        jPanel_Grade_EspecialLayout.setHorizontalGroup(
            jPanel_Grade_EspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Grade_EspecialLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jRadioButton111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton221)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton211)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton121)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel_Grade_EspecialLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Dividir_Grade_Especial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Grade_EspecialLayout.setVerticalGroup(
            jPanel_Grade_EspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Grade_EspecialLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_Grade_EspecialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton111)
                    .addComponent(jRadioButton122)
                    .addComponent(jRadioButton112)
                    .addComponent(jRadioButton221)
                    .addComponent(jRadioButton211)
                    .addComponent(jRadioButton121))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Dividir_Grade_Especial)
                .addContainerGap())
        );

        btn_Dividir_Grade_Especial.setNextFocusableComponent(txt_ref);

        jPanel_Automatico.setBorder(javax.swing.BorderFactory.createTitledBorder("Automatico"));

        btn_Dividir_Automatico.setText("Automatico");
        btn_Dividir_Automatico.setNextFocusableComponent(txt_ref);
        btn_Dividir_Automatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_AutomaticoActionPerformed(evt);
            }
        });
        btn_Dividir_Automatico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_AutomaticoFocusGained(evt);
            }
        });

        jPanel_Automatico.setVisible(false);

        javax.swing.GroupLayout jPanel_AutomaticoLayout = new javax.swing.GroupLayout(jPanel_Automatico);
        jPanel_Automatico.setLayout(jPanel_AutomaticoLayout);
        jPanel_AutomaticoLayout.setHorizontalGroup(
            jPanel_AutomaticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Dividir_Automatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_AutomaticoLayout.setVerticalGroup(
            jPanel_AutomaticoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AutomaticoLayout.createSequentialGroup()
                .addComponent(btn_Dividir_Automatico)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.setFocusable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setText("Nº Pedido:");

        txt_n_pedido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_n_pedidoFocusLost(evt);
            }
        });

        jLabel7.setText("Ref:");

        txt_ref.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_refFocusLost(evt);
            }
        });
        txt_ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_refActionPerformed(evt);
            }
        });

        jLabel8.setText("Qtd:");

        txt_qtd_total.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_qtd_totalFocusLost(evt);
            }
        });

        combobox_cor.setInheritsPopupMenu(true);
        combobox_cor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combobox_corItemStateChanged(evt);
            }
        });
        combobox_cor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combobox_corFocusGained(evt);
            }
        });
        combobox_cor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                combobox_corComponentResized(evt);
            }
        });
        combobox_cor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                combobox_corKeyPressed(evt);
            }
        });

        jLabel9.setText("Cor:");

        buttonGroupTipoDivisao.add(jRadioButtonManual);
        jRadioButtonManual.setText("Manual");
        jRadioButtonManual.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonManualStateChanged(evt);
            }
        });
        jRadioButtonManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonManualActionPerformed(evt);
            }
        });

        buttonGroupTipoDivisao.add(jRadioButtonGrade);
        jRadioButtonGrade.setText("Selecionar Grade");
        jRadioButtonGrade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonGradeStateChanged(evt);
            }
        });
        jRadioButtonGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGradeActionPerformed(evt);
            }
        });

        buttonGroupTipoDivisao.add(jRadioButtonAutomatico);
        jRadioButtonAutomatico.setText("Automatico");
        jRadioButtonAutomatico.setNextFocusableComponent(jRadioButtonManual);
        jRadioButtonAutomatico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonAutomaticoStateChanged(evt);
            }
        });
        jRadioButtonAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAutomaticoActionPerformed(evt);
            }
        });

        jLabel1.setText("Tamanhos:");

        txt_qtd_tamanho.setText("0");
        txt_qtd_tamanho.setEnabled(false);
        txt_qtd_tamanho.setFocusable(false);

        jLabel2.setText("Cores:");

        txt_qtd_cores.setEnabled(false);
        txt_qtd_cores.setFocusable(false);
        txt_qtd_cores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qtd_coresActionPerformed(evt);
            }
        });

        jLabel14.setText("Tipo Tamanho:");

        txt_Tipo_tamanho.setEditable(false);
        txt_Tipo_tamanho.setEnabled(false);
        txt_Tipo_tamanho.setFocusable(false);

        btn_remove.setText("- (F2)");
        btn_remove.setFocusable(false);
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        jComboBox_Cores.setMaximumRowCount(15);
        jComboBox_Cores.setFocusable(false);

        btn_adiciona.setText("+");
        btn_adiciona.setFocusable(false);
        btn_adiciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionaActionPerformed(evt);
            }
        });

        jLabel13.setText("Loja:");

        txt_loja.setText("LOJA 1");

        jList1.setFocusable(false);
        jList1.setRequestFocusEnabled(false);
        jList1.setVerifyInputWhenFocusTarget(false);
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_n_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(2, 2, 2)
                                .addComponent(txt_loja)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBox_Cores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_adiciona))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Tipo_tamanho))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qtd_total))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ref, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combobox_cor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_remove))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jRadioButtonManual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonGrade)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonAutomatico))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qtd_tamanho, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qtd_cores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_n_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Tipo_tamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_loja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Cores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_adiciona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonManual)
                    .addComponent(jRadioButtonGrade)
                    .addComponent(jRadioButtonAutomatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(combobox_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btn_remove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_qtd_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_qtd_tamanho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txt_qtd_cores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        txt_ref.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ref.selectAll();
            }
        });
        txt_qtd_total.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_qtd_total.selectAll();
            }
        });
        tdgCTR.todasCores(jComboBox_Cores);

        jTabbedPane1.addTab("Itens Pedido", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel17.setText("Copiar o pedido:");

        txt_n_pedido_original.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_n_pedido_originalFocusLost(evt);
            }
        });

        jLabel18.setText("de loja:");

        jLabel19.setText("PARA O PEDIDO:");

        txt_loja_original.setText("LOJA 1");

        jLabel20.setText("de loja:");

        txt_loja_novo.setText("LOJA 1");

        jButton5.setText("Copiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txt_n_pedido_novo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_n_pedido_novoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_n_pedido_original, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_loja_original, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_n_pedido_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_loja_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(txt_loja_original, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_n_pedido_original, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(txt_loja_novo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_n_pedido_novo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Copiar Itens Pedido", jPanel1);

        jLabel21.setText("Todas Cores:");

        jLabel22.setText("Cores para excluir:");

        btn_add_cor_excluir.setText(">>");
        btn_add_cor_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_cor_excluirActionPerformed(evt);
            }
        });

        btn_remove_cor_excluir.setText("<<");
        btn_remove_cor_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remove_cor_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jComboBox_todas_cores, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_remove_cor_excluir)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_add_cor_excluir)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel22)
                                .addContainerGap(91, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_excluir_cor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_todas_cores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_excluir_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_cor_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_remove_cor_excluir)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        tdgCTR.todasCores(jComboBox_todas_cores);

        jTabbedPane1.addTab("Excluir Cor", jPanel2);

        jPanel_SORTIDO.setBorder(javax.swing.BorderFactory.createTitledBorder("Sortido"));

        btn_Dividir_Sortido_Normal.setText("Automatico Normal");
        btn_Dividir_Sortido_Normal.setNextFocusableComponent(txt_ref);
        btn_Dividir_Sortido_Normal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_Sortido_NormalFocusGained(evt);
            }
        });
        btn_Dividir_Sortido_Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_Sortido_NormalActionPerformed(evt);
            }
        });

        jComboBox_refs.setFocusable(false);

        jLabel3.setText("REFs:");

        jComboBox_Categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        jComboBox_Categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_CategoriaItemStateChanged(evt);
            }
        });
        jComboBox_Categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_CategoriaKeyPressed(evt);
            }
        });

        jLabel10.setText("Categoria Sortido:");

        jLabel11.setText("Qtd total:");

        jLabel12.setText("Qtd Refs");

        btn_Dividir_Sortido_Juvenil.setText("Automatico Juvenil");
        btn_Dividir_Sortido_Juvenil.setNextFocusableComponent(txt_ref);
        btn_Dividir_Sortido_Juvenil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_Sortido_JuvenilFocusGained(evt);
            }
        });
        btn_Dividir_Sortido_Juvenil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_Sortido_JuvenilActionPerformed(evt);
            }
        });

        btn_Dividir_Sortido_Especial.setText("Automatico Especial");
        btn_Dividir_Sortido_Especial.setNextFocusableComponent(txt_ref);
        btn_Dividir_Sortido_Especial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_Sortido_EspecialFocusGained(evt);
            }
        });
        btn_Dividir_Sortido_Especial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_Sortido_EspecialActionPerformed(evt);
            }
        });

        jLabel5.setText("Tamanho:");

        txt_Tipo_tamanho_categoria.setEditable(false);
        txt_Tipo_tamanho_categoria.setEnabled(false);
        txt_Tipo_tamanho_categoria.setFocusable(false);

        jCheckBox_disponivel.setText("Disponivel nos proximos:");
        jCheckBox_disponivel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox_disponivelStateChanged(evt);
            }
        });
        jCheckBox_disponivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox_disponivelItemStateChanged(evt);
            }
        });

        txt_dias.setText("60");
        txt_dias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_diasFocusLost(evt);
            }
        });

        jLabel16.setText("dias.");

        jPanel_Manual1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sortido Manual"));

        jLabelT5.setText("Tamanho1");

        txt_t1_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t1_grade_sortidoFocusLost(evt);
            }
        });

        jLabelT6.setText("Tamanho2");

        txt_t2_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t2_grade_sortidoFocusLost(evt);
            }
        });

        jLabelT7.setText("Tamanho3");

        txt_t3_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t3_grade_sortidoFocusLost(evt);
            }
        });

        jLabelT8.setText("Tamanho4");

        txt_t4_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_t4_grade_sortidoFocusLost(evt);
            }
        });

        btn_Dividir_Sortido_Manual.setText("Dividir Sortido");
        btn_Dividir_Sortido_Manual.setNextFocusableComponent(txt_ref);
        btn_Dividir_Sortido_Manual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Dividir_Sortido_ManualFocusGained(evt);
            }
        });
        btn_Dividir_Sortido_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Dividir_Sortido_ManualActionPerformed(evt);
            }
        });

        jButton4.setText("Limpar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel_Manual1.setVisible(false);

        javax.swing.GroupLayout jPanel_Manual1Layout = new javax.swing.GroupLayout(jPanel_Manual1);
        jPanel_Manual1.setLayout(jPanel_Manual1Layout);
        jPanel_Manual1Layout.setHorizontalGroup(
            jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Manual1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Manual1Layout.createSequentialGroup()
                        .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t1_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t2_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t3_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelT8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_t4_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel_Manual1Layout.createSequentialGroup()
                        .addComponent(btn_Dividir_Sortido_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel_Manual1Layout.setVerticalGroup(
            jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Manual1Layout.createSequentialGroup()
                .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelT5)
                    .addComponent(jLabelT6)
                    .addComponent(jLabelT7)
                    .addComponent(jLabelT8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_t1_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t2_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t3_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_t4_grade_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Manual1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Dividir_Sortido_Manual)
                    .addComponent(jButton4))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        txt_t1_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t1_grade_sortido.selectAll();
            }
        });
        txt_t2_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t2_grade_sortido.selectAll();
            }
        });
        txt_t3_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t3_grade_sortido.selectAll();
            }
        });
        txt_t4_grade_sortido.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_t4_grade_sortido.selectAll();
            }
        });

        buttonGroup_Sortido.add(jRadioButton_Sortido_Automatico);
        jRadioButton_Sortido_Automatico.setText("Automatico");
        jRadioButton_Sortido_Automatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Sortido_AutomaticoActionPerformed(evt);
            }
        });

        buttonGroup_Sortido.add(jRadioButton_Sortido_Manual);
        jRadioButton_Sortido_Manual.setText("Manual");
        jRadioButton_Sortido_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Sortido_ManualActionPerformed(evt);
            }
        });

        jLabel15.setText("Saldo >");

        txt_saldo.setText("100");
        txt_saldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_saldoFocusLost(evt);
            }
        });

        jButton7.setText("-");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jCheckBox_sortido_maximo.setText("Sortido Maximo");
        jCheckBox_sortido_maximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_sortido_maximoActionPerformed(evt);
            }
        });

        jPanel_Automatico.setVisible(false);

        javax.swing.GroupLayout jPanel_SORTIDOLayout = new javax.swing.GroupLayout(jPanel_SORTIDO);
        jPanel_SORTIDO.setLayout(jPanel_SORTIDOLayout);
        jPanel_SORTIDOLayout.setHorizontalGroup(
            jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Dividir_Sortido_Normal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Dividir_Sortido_Juvenil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Dividir_Sortido_Especial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                        .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_refs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qtd_ref, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Tipo_tamanho_categoria))
                            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qtd_total_sortido))))
                    .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                        .addComponent(jCheckBox_disponivel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dias, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_saldo))))
            .addComponent(jPanel_Manual1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                .addComponent(jRadioButton_Sortido_Automatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton_Sortido_Manual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_sortido_maximo)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_SORTIDOLayout.setVerticalGroup(
            jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SORTIDOLayout.createSequentialGroup()
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Sortido_Automatico)
                    .addComponent(jRadioButton_Sortido_Manual)
                    .addComponent(jCheckBox_sortido_maximo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, Short.MAX_VALUE)
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_disponivel)
                    .addComponent(txt_dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_qtd_total_sortido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jComboBox_refs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_SORTIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_qtd_ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel5)
                    .addComponent(txt_Tipo_tamanho_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Dividir_Sortido_Normal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Dividir_Sortido_Juvenil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Dividir_Sortido_Especial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Manual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_Grade_Especial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Grade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Manual, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Automatico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_SORTIDO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Grade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Grade_Especial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Automatico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_SORTIDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Dividir_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_ManualActionPerformed
   if ((txt_n_pedido.getText().isEmpty()) || (txt_ref.getText().isEmpty()) || (txt_t1_grade.getText().isEmpty()) || (txt_t2_grade.getText().isEmpty()) || (txt_t3_grade.getText().isEmpty()) || (txt_t4_grade.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Complete todos os campos.");
                   
                }
                else {
                    
               
                   
                        double t1, t2, t3, t4;
        
                        t1 = Double.parseDouble(txt_t1_grade.getText());
                        t2 = Double.parseDouble(txt_t2_grade.getText());
                        t3 = Double.parseDouble(txt_t3_grade.getText());
                        t4 = Double.parseDouble(txt_t4_grade.getText());
                        dividir(t1, t2, t3, t4);
                        
                        
                        if (jCheckBox_manter.isSelected()){
                            
                        }else{
                            //zerar campos
                        
                            txt_t1_grade.setText("");
                            txt_t2_grade.setText("");
                            txt_t3_grade.setText("");
                            txt_t4_grade.setText("");
                        
                        }
                        
         }   
        
        
        
    }//GEN-LAST:event_btn_Dividir_ManualActionPerformed

    private void btn_Dividir_GradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_GradeActionPerformed
        if (!validar_campos()){
           
       }else{
        
            if ( (!jRadioButton1111.isSelected() ) && (!jRadioButton1122.isSelected()) && (!jRadioButton1221.isSelected() )  && (!jRadioButton2112.isSelected()) && (!jRadioButton2211.isSelected())  ){
                JOptionPane.showMessageDialog(null, "Selecione uma grade!");
            }
            else{

        // DIVIDIR POR GRADE
        double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
        Integer divisor; 
        Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
        Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
        double qtd_divisoria;
        Integer qtd_divisoria_integer;
        double sobra_divisoria;
        Integer sobra_divisoria_integer;
        //se for grade 1111
        if (jRadioButton1111.isSelected()) {
            divisor = qtd_tamanho; 
        }else{ //se for qualquer uma outra
            divisor = 6;
        }
        
        qtd_divisoria = qtd_total/divisor;
        qtd_divisoria_integer = (int) (qtd_divisoria);
        sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
        sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
        System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
        System.out.println("Sobra divisoria: "+sobra_divisoria);
        
        
        if (jRadioButton1111.isSelected()) {
            
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer;
                t4 = qtd_divisoria_integer;
            
        }
        
        if (jRadioButton1122.isSelected()){
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer*2;
                t4 = qtd_divisoria_integer*2;
        }
        
        if (jRadioButton1221.isSelected()){
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer*2;
                t3 = qtd_divisoria_integer*2;
                t4 = qtd_divisoria_integer;
         }
        
        if (jRadioButton2112.isSelected()){
                t1 = qtd_divisoria_integer*2;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer;
                t4 = qtd_divisoria_integer*2;
        }
        
        if (jRadioButton2211.isSelected()){
                t1 = qtd_divisoria_integer*2;
                t2 = qtd_divisoria_integer*2;
                t3 = qtd_divisoria_integer;
                t4 = qtd_divisoria_integer;
         }
        
        
        
        //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        //adicionando sobras 
        if (qtd_tamanho==4) {
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = t4;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = t4;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = t4;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = t4+1;//+
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+1;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = t4+1;//+
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = t4;
                        }
                    }
        }//fim tamanho = 4            
        
        else {//tamanho != 4 (especial)
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+2;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }
        }//fim tamanho = 3  
        
            System.out.println("Grade com sobra:");
            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

            dividir(t1, t2, t3, t4);
        }//fim valida grade
      }//fim valida campos
    }//GEN-LAST:event_btn_Dividir_GradeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
        model.setNumRows(0);
        calc_total_itens();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_Dividir_Grade_EspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_Grade_EspecialActionPerformed
        // dividir por grade especial
        
        if (!validar_campos()){
           
       }else{
            
            if ( (!jRadioButton111.isSelected() ) && (!jRadioButton112.isSelected()) && (!jRadioButton122.isSelected() )  && (!jRadioButton211.isSelected()) && (!jRadioButton221.isSelected()) && (!jRadioButton121.isSelected())  ){
                JOptionPane.showMessageDialog(null, "Selecione uma grade!");
            }
            else{
        
        double t1 = 0,t2 = 0,t3 = 0, t4 = 0;
        Integer divisor; 
        Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
        Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
        double qtd_divisoria;
        Integer qtd_divisoria_integer;
        double sobra_divisoria;
        Integer sobra_divisoria_integer;
        
        
        if (jRadioButton111.isSelected()) {
                     divisor = qtd_tamanho;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer;
                t4 = 0;
                        //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
            
        //adicionando sobras 
       
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+2;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }
       
        
        System.out.println("Grade com sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        dividir(t1, t2, t3, t4);
        }
        
        if (jRadioButton112.isSelected()){
                    divisor = 4;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer*2;
                t4 = 0;
                        //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        //adicionando sobras 
        
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2;
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+
                            t2 = t2;
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+2;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+1;//+
                            t3 = t3+2;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }//fim sobra
      
        
        System.out.println("Grade com sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        dividir(t1, t2, t3, t4);
        }
        
        if (jRadioButton122.isSelected()){
                    divisor = 5;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer*2;
                t3 = qtd_divisoria_integer*2;
                t4 = 0;
                        //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
            //adicionando sobras 
        
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+1;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+1;//+
                            t2 = t2+2;//+
                            t3 = t3+2;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }//fim sobra
        System.out.println("Grade com sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        dividir(t1, t2, t3, t4);
        }
        
        if (jRadioButton211.isSelected()){
                    divisor = 4;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer*2;
                t2 = qtd_divisoria_integer;
                t3 = qtd_divisoria_integer;
                t4 = 0;
                        //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        //adicionando sobras 
        
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1+1;//+
                            t2 = t2;
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+2;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }//fim sobra
                    
                    System.out.println("Grade com sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        dividir(t1, t2, t3, t4);
        }
        
        if (jRadioButton221.isSelected()){
                    divisor = 5;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer*2;
                t2 = qtd_divisoria_integer*2;
                t3 = qtd_divisoria_integer;
                t4 = 0;
                
                //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
         
                
                 //adicionando sobras 
        
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1+1;//+
                            t2 = t2;
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+2;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }//fim sobra
        
        System.out.println("Grade com sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
        dividir(t1, t2, t3, t4);
        
        
        }
        if (jRadioButton121.isSelected()){
                    divisor = 4;
                    qtd_divisoria = qtd_total/divisor;
                    qtd_divisoria_integer = (int) (qtd_divisoria);
                    sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                    sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                    System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                    System.out.println("Sobra divisoria: "+sobra_divisoria);
                t1 = qtd_divisoria_integer;
                t2 = qtd_divisoria_integer*2;
                t3 = qtd_divisoria_integer;
                t4 = 0;
                
                //como ficou grade sem adicionar a sobra
        System.out.println("Grade sem adicionar sobra:");
        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
         
                
                 //adicionando sobras 
        
                    switch (sobra_divisoria_integer) {
                        case 1:
                        {   System.out.println("Sobro 1");
                            t1 = t1;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 2:
                        {   System.out.println("Sobro 2");
                            t1 = t1+1;//+;
                            t2 = t2+1;//+
                            t3 = t3;
                            t4 = 0;
                            break;
                        }
                        case 3:
                        {   System.out.println("Sobro 3");
                            t1 = t1+1;//+
                            t2 = t2+1;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 4:
                        {   System.out.println("Sobro 4");
                            t1 = t1+1;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                        case 5:
                        {   System.out.println("Sobro 5");
                            t1 = t1+2;//+
                            t2 = t2+2;//+
                            t3 = t3+1;//+
                            t4 = 0;
                            break;
                        }
                         default:{
                          System.out.println("Sobro 0, qtd iguais");
                          t1 = t1;
                          t2 = t2;
                          t3 = t3;
                          t4 = 0;
                        }
                    }//fim sobra
        
            System.out.println("Grade com sobra:");
            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);
        
            dividir(t1, t2, t3, t4);
            
        }
        
            }//fim valida grade
        }//fim valida campos   
        
        
        
    }//GEN-LAST:event_btn_Dividir_Grade_EspecialActionPerformed

    private void btn_Dividir_AutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_AutomaticoActionPerformed
        // TODO add your handling code here:
       
       if (!validar_campos()){
           
       }else{
            
        
        
        String tipo = txt_Tipo_tamanho.getText();
        switch (tipo) {
             case "ESPECIAL":
             {
                                System.out.println("Entrou no especial");

                                //grade 1 1 1
                                double t1 = 0,t2 = 0,t3 = 0, t4 = 0;
                                Integer divisor; 
                                Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                                Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                                double qtd_divisoria;
                                Integer qtd_divisoria_integer;
                                double sobra_divisoria;
                                Integer sobra_divisoria_integer;

                                             divisor = qtd_tamanho;
                                            qtd_divisoria = qtd_total/divisor;
                                            qtd_divisoria_integer = (int) (qtd_divisoria);
                                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                                        t1 = qtd_divisoria_integer;
                                        t2 = qtd_divisoria_integer;
                                        t3 = qtd_divisoria_integer;
                                        t4 = 0;
                                                //como ficou grade sem adicionar a sobra
                                System.out.println("Grade sem adicionar sobra:");
                                System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                                //adicionando sobras 

                                            switch (sobra_divisoria_integer) {
                                                case 1:
                                                {   System.out.println("Sobro 1");
                                                    t1 = t1;
                                                    t2 = t2+1;//+
                                                    t3 = t3;
                                                    t4 = 0;
                                                    break;
                                                }
                                                case 2:
                                                {   System.out.println("Sobro 2");
                                                    t1 = t1+1;//+
                                                    t2 = t2+1;//+
                                                    t3 = t3;
                                                    t4 = 0;
                                                    break;
                                                }
                                                case 3:
                                                {   System.out.println("Sobro 3");
                                                    t1 = t1+1;//+
                                                    t2 = t2+1;//+
                                                    t3 = t3+1;//+
                                                    t4 = 0;
                                                    break;
                                                }
                                                case 4:
                                                {   System.out.println("Sobro 4");
                                                    t1 = t1+2;//+
                                                    t2 = t2+1;//+
                                                    t3 = t3+1;//+
                                                    t4 = 0;
                                                    break;
                                                }
                                                case 5:
                                                {   System.out.println("Sobro 5");
                                                    t1 = t1+2;//+
                                                    t2 = t2+2;//+
                                                    t3 = t3+1;//+
                                                    t4 = 0;
                                                    break;
                                                }
                                                 default:{
                                                  System.out.println("Sobro 0, qtd iguais");
                                                  t1 = t1;
                                                  t2 = t2;
                                                  t3 = t3;
                                                  t4 = 0;
                                                }
                                            }


                                        System.out.println("Grade com sobra:");
                                        System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                                        dividir(t1, t2, t3, t4);


             break;
             }
             case "JUVENIL":
             {
             System.out.println("Entrou no juvenil");
            
             //grade 1 1 1 1
             
                            double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            Integer divisor; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            double qtd_divisoria;
                            Integer qtd_divisoria_integer;
                            double sobra_divisoria;
                            Integer sobra_divisoria_integer;
                            divisor = qtd_tamanho;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1 = qtd_divisoria_integer;
                            t2 = qtd_divisoria_integer;
                            t3 = qtd_divisoria_integer;
                            t4 = qtd_divisoria_integer;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                            //adicionando sobras 
                            if (qtd_tamanho==4) {
                                        switch (sobra_divisoria_integer) {
                                            case 1:
                                            {   System.out.println("Sobro 1");
                                                t1 = t1;
                                                t2 = t2+1;//+
                                                t3 = t3;
                                                t4 = t4;
                                                break;
                                            }
                                            case 2:
                                            {   System.out.println("Sobro 2");
                                                t1 = t1;
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4;
                                                break;
                                            }
                                            case 3:
                                            {   System.out.println("Sobro 3");
                                                t1 = t1+1;//+
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4;
                                                break;
                                            }
                                            case 4:
                                            {   System.out.println("Sobro 4");
                                                t1 = t1+1;//+
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4+1;//+
                                                break;
                                            }
                                            case 5:
                                            {   System.out.println("Sobro 5");
                                                t1 = t1+1;//+
                                                t2 = t2+2;//+
                                                t3 = t3+1;//+
                                                t4 = t4+1;//+
                                                break;
                                            }
                                             default:{
                                              System.out.println("Sobro 0, qtd iguais");
                                              t1 = t1;
                                              t2 = t2;
                                              t3 = t3;
                                              t4 = t4;
                                            }
                                        }
                            }//fim tamanho = 4
                            
                             System.out.println("Grade com sobra:");
                            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                            dividir(t1, t2, t3, t4);
             
             break;
             }
             default:{
                 System.out.println("Entrou no default(Normal)");
                 //grade 1 2 2 1
                 
                 
             
                            double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            Integer divisor; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            double qtd_divisoria;
                            Integer qtd_divisoria_integer;
                            double sobra_divisoria;
                            Integer sobra_divisoria_integer;
                            divisor = 6;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1 = qtd_divisoria_integer;
                            t2 = qtd_divisoria_integer*2;
                            t3 = qtd_divisoria_integer*2;
                            t4 = qtd_divisoria_integer;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                            //adicionando sobras 
                            if (qtd_tamanho==4) {
                                        switch (sobra_divisoria_integer) {
                                            case 1:
                                            {   System.out.println("Sobro 1");
                                                t1 = t1;
                                                t2 = t2+1;//+
                                                t3 = t3;
                                                t4 = t4;
                                                break;
                                            }
                                            case 2:
                                            {   System.out.println("Sobro 2");
                                                t1 = t1;
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4;
                                                break;
                                            }
                                            case 3:
                                            {   System.out.println("Sobro 3");
                                                t1 = t1+1;//+
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4;
                                                break;
                                            }
                                            case 4:
                                            {   System.out.println("Sobro 4");
                                                t1 = t1+1;//+
                                                t2 = t2+1;//+
                                                t3 = t3+1;//+
                                                t4 = t4+1;//+
                                                break;
                                            }
                                            case 5:
                                            {   System.out.println("Sobro 5");
                                                t1 = t1+1;//+
                                                t2 = t2+2;//+
                                                t3 = t3+1;//+
                                                t4 = t4+1;//+
                                                break;
                                            }
                                             default:{
                                              System.out.println("Sobro 0, qtd iguais");
                                              t1 = t1;
                                              t2 = t2;
                                              t3 = t3;
                                              t4 = t4;
                                            }
                                        }
                            }//fim tamanho = 4
                            
                             System.out.println("Grade com sobra:");
                            System.out.println("T1: "+t1+" T2: "+t2+" T3: "+t3+" T4: "+t4);

                            dividir(t1, t2, t3, t4);
                 
                
                
             }
             
         }
     
       }//fim valida campos
    }//GEN-LAST:event_btn_Dividir_AutomaticoActionPerformed

    private void btn_ListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListarActionPerformed
        // TODO add your handling code here:
        listar_item_pedido();
        calc_total_itens();
                               
    }//GEN-LAST:event_btn_ListarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        // TODO add your handling code here:
        
        excluir_item();
        calc_total_itens();
    }//GEN-LAST:event_btn_excluirActionPerformed
    private DefaultListModel lista = new DefaultListModel();  
    private void btn_Dividir_ManualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_ManualFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Manual);
    }//GEN-LAST:event_btn_Dividir_ManualFocusGained

    private void btn_Dividir_GradeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_GradeFocusGained
        // TODO add your handling code here:
         tdgCTR.reagirEnter(btn_Dividir_Grade);
    }//GEN-LAST:event_btn_Dividir_GradeFocusGained

    private void btn_Dividir_Grade_EspecialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_Grade_EspecialFocusGained
        // TODO add your handling code here:
         tdgCTR.reagirEnter(btn_Dividir_Grade_Especial);
    }//GEN-LAST:event_btn_Dividir_Grade_EspecialFocusGained

    private void btn_Dividir_AutomaticoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_AutomaticoFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Automatico);
    }//GEN-LAST:event_btn_Dividir_AutomaticoFocusGained

    private void txt_t3_gradeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_t3_gradeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER){
            if (txt_t4_grade.isEnabled()){
                txt_t4_grade.requestFocus();
            }else{
                btn_Dividir_Manual.requestFocus();
            }
        } 
    }//GEN-LAST:event_txt_t3_gradeKeyPressed

    private void jRadioButton1111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1111ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade.requestFocus();
    }//GEN-LAST:event_jRadioButton1111ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txt_t1_grade.setText("0");
        txt_t2_grade.setText("0");
        txt_t3_grade.setText("0");
        txt_t4_grade.setText("0");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        HashMap parametros = new HashMap();  
        RelatorioDAO dao = new RelatorioDAO();
        String n_pedido = txt_n_pedido.getText();
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        JRResultSetDataSource jrRS = new JRResultSetDataSource(dao.listarPedido(n_pedido));  
        JasperPrint jasperPrint;  
       
        try {  
            //PEGANDO DO DIRETORIO MAZIN
            //jasperPrint = JasperFillManager.fillReport("\\\\"+TDG_SIS_VIEW.ip_servidor+"\\TDG_sis_prod\\relatorios\\"+TDG_SIS_VIEW.banco_selecionado+"\\n_pedido.jasper" , parametros, jrRS);  
            
            InputStream inputStream;
            
            if (jCheckBox_loja.isSelected()){
                inputStream = getClass().getResourceAsStream( "/relatorios_ireport/n_pedido_por_loja.jasper" );
            }else{
                inputStream = getClass().getResourceAsStream( "/relatorios_ireport/n_pedido.jasper" );
            }
            
            
            jasperPrint = JasperFillManager.fillReport(inputStream, parametros, jrRS);  
            /* EXIBI TELA DE IMPRESSAO
            JasperViewer.viewReport(jasperPrint, false); 
            */
            //IMPRIME DIRETO
            JasperPrintManager.printReport(jasperPrint,true);
           
        } catch (JRException ex) {  
            JOptionPane.showMessageDialog(null, "ERRO AO IMPRIMIR!\n"+ex);  
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
        
        
        
       /* RELATORIO ANTIGO / EXCEL
        try {
            // TODO add your handling code here:
            
            java.awt.Desktop.getDesktop().open(new File ("\\\\"+TDG_SIS_VIEW.ip_servidor+"\\TDG_sis_prod\\relatorios\\"+TDG_SIS_VIEW.banco_selecionado+"\\relatorio_pedidos.xlsx") );
        } catch (IOException ex) {
            //pau
        }
        */
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1221ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1221ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade.requestFocus();
    }//GEN-LAST:event_jRadioButton1221ActionPerformed

    private void jRadioButton1122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1122ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade.requestFocus();
    }//GEN-LAST:event_jRadioButton1122ActionPerformed

    private void jRadioButton2211ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2211ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade.requestFocus();
    }//GEN-LAST:event_jRadioButton2211ActionPerformed

    private void jRadioButton2112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2112ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade.requestFocus();
    }//GEN-LAST:event_jRadioButton2112ActionPerformed

    private void jRadioButton111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton111ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade_Especial.requestFocus();
    }//GEN-LAST:event_jRadioButton111ActionPerformed

    private void jRadioButton122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton122ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade_Especial.requestFocus();
    }//GEN-LAST:event_jRadioButton122ActionPerformed

    private void jRadioButton112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton112ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade_Especial.requestFocus();
    }//GEN-LAST:event_jRadioButton112ActionPerformed

    private void jRadioButton221ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton221ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade_Especial.requestFocus();
    }//GEN-LAST:event_jRadioButton221ActionPerformed

    private void jRadioButton211ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton211ActionPerformed
        // TODO add your handling code here:
        btn_Dividir_Grade_Especial.requestFocus();
    }//GEN-LAST:event_jRadioButton211ActionPerformed

    private void txt_t1_gradeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t1_gradeFocusLost
        // TODO add your handling code here:
        if (txt_t1_grade.getText().equals("")){
            txt_t1_grade.setText("0");
        }
    }//GEN-LAST:event_txt_t1_gradeFocusLost

    private void txt_t2_gradeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t2_gradeFocusLost
        // TODO add your handling code here:
        if (txt_t2_grade.getText().equals("")){
            txt_t2_grade.setText("0");
        }
    }//GEN-LAST:event_txt_t2_gradeFocusLost

    private void txt_t3_gradeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t3_gradeFocusLost
        // TODO add your handling code here:
        if (txt_t3_grade.getText().equals("")){
            txt_t3_grade.setText("0");
        }
    }//GEN-LAST:event_txt_t3_gradeFocusLost

    private void txt_t4_gradeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t4_gradeFocusLost
        // TODO add your handling code here:
        if (txt_t4_grade.getText().equals("")){
            txt_t4_grade.setText("0");
        }
    }//GEN-LAST:event_txt_t4_gradeFocusLost

    
    
    private void btn_Dividir_Sortido_NormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_NormalActionPerformed
        // TODO add your handling code here:
           
            
            
                            
                            Integer divisor;
                            
                            //pega do count(*) ou outro método..
                            Integer qtd_ref = Integer.parseInt(txt_qtd_ref.getText());
                            
                            Double qtd_total_sortido = Double.parseDouble(txt_qtd_total_sortido.getText());
                            double qtd_divisoria;
                            Integer qtd_divisoria_integer;
                            double sobra_divisoria;
                            Integer sobra_divisoria_integer;
                            divisor = qtd_ref;
                            
                            qtd_divisoria = qtd_total_sortido/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total_sortido+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            
                            int[] qtd_sortido = new int[30];
                            //começar no t1;
                            Integer tamanho_que_parou = 1;
                            
                            System.out.println("Qtd de cada REF sem adicionar sobra:");
                            for (int i = 1; i <= qtd_ref; i++) {
                                qtd_sortido[i]=qtd_divisoria_integer;
                                System.out.println("Qtd da ref["+i+"] = "+qtd_sortido[i]);
                            }
                           //adicionar sobras
                           //new 
                           Integer ref_que_parou;
                           if (jCheckBox_sortido_maximo.isSelected()){
                              /* sorterar ref para iniciar */
                            Random gerador = new Random();
                            ref_que_parou = gerador.nextInt(qtd_ref) + 1;
                            System.out.println("REF sorteado: "+ref_que_parou);
                             
                           }else{
                            ref_que_parou = 1;
                           }
                                    System.out.println("Adicionando sobra Qtd começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra qtd inteiro: "+sobra_divisoria_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        qtd_sortido[ref_que_parou]=qtd_sortido[ref_que_parou]+1;
                                        System.out.println("Qtd_sortido["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println(" REF "+i+": Qtd: "+qtd_sortido[i]);
                            }
                            
                            //executando
                            
                            //t1_ref[1] = qtd do t1 da ref[1]
                            int[] t1_ref = new int[30];
                            int[] t2_ref = new int[30];
                            int[] t3_ref = new int[30];
                            int[] t4_ref = new int[30];
                            
                            
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            for (int z = 1; z <= qtd_ref; z++) {
                                
                                
                                
                                combobox_cor.removeAllItems();
                                //pegando dados da referencia inserida
                                txt_ref.setText(jComboBox_refs.getItemAt(z).toString());

                                for (ProdutoDTO p : dao.getProdutosRef(jComboBox_refs.getItemAt(z).toString())) {
                                    
                                     //adicionando cores na combobox
                                    combobox_cor.addItem("TODAS");
                                    //passando codigo e combobox para add.
                                    tdgCTR.cores_ref(txt_ref.getText(), combobox_cor);
                                   
                                    //executa o request focus e executa o 
                                    combobox_cor.requestFocus();
                                    excluir_cor_do_pedido();
                                        
                                    

                                    txt_Tipo_tamanho.setText(p.getTipo_tamanho());
                                    txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());
                                    Integer qtd_cores = combobox_cor.getItemCount()-1;
                                    txt_qtd_cores.setText(qtd_cores.toString());
                                    String tipo = txt_Tipo_tamanho.getText();
                                
                                }
                                
                                
                                //txt_ref.setText(combobox_refs.getItemAt(z).toString());
                                System.err.println("Valor combobox refs: "+jComboBox_refs.getItemAt(z).toString());
                                //combobox_cor.requestFocus();//buscar dados da ref com o focuslost
                                Integer qtd_sortido_integer = qtd_sortido[z];
                                txt_qtd_total.setText((qtd_sortido_integer.toString()));
                                
                                
                                //btn_Dividir_AutomaticoActionPerformed(evt);
                                //separar grade de acordo informando a qtd_total de cada ref, fazer sequencia
                                
                                
                         //*******************
                                
                //dividir por tipo especial, normal, juvenil       
                String tipo = txt_Tipo_tamanho.getText();
                switch (tipo) {
                       case "JUVENIL":
                       { 
                       System.out.println("Entrou no JUVENIL");    
                            
                            //double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            divisor = 0; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            qtd_divisoria = 0;
                            qtd_divisoria_integer = 0;
                            sobra_divisoria = 0;
                            sobra_divisoria_integer = 0;
                            divisor = qtd_tamanho;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1_ref[z] = qtd_divisoria_integer;
                            t2_ref[z] = qtd_divisoria_integer;
                            t3_ref[z] = qtd_divisoria_integer;
                            t4_ref[z] = qtd_divisoria_integer;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);

                            //adicionando sobras 
                            
                            //for (int w = 1; w <= sobra_divisoria_integer; w++) {
                                System.out.println("Adicionando sobra: "+sobra_divisoria_integer+"");        
                                switch (sobra_divisoria_integer) {
                                        case 1:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 1;
                                                }
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 2:
                                        {
                                            
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                            
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 3:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }    
                                            
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 4:
                                        {
                                                t1_ref[z] = t1_ref[z]+1;
                                                t2_ref[z] = t2_ref[z]+1;//+
                                                t3_ref[z] = t3_ref[z]+1;//+
                                                t4_ref[z] = t4_ref[z]+1;
                                                tamanho_que_parou = 1;
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 5:
                                        {
                                                
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+2;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+2;//2?
                                                    t2_ref[z] = t2_ref[z]+1;//1?
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                            
                                                //tamanho_que_parou++;
                                                //if (tamanho_que_parou>qtd_tamanho){
                                                //tamanho_que_parou=1;
                                                //}
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        default:
                                        {
                                                t1_ref[z] = t1_ref[z];
                                                t2_ref[z] = t2_ref[z];
                                                t3_ref[z] = t3_ref[z];
                                                t4_ref[z] = t4_ref[z];
                                                
                                       
                                            System.out.println("Não sobrou, tamanho que parou: "+tamanho_que_parou);
                                        }
                            }
                                //como ficou grade com sobra
                            
                                //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("REF: "+i+" T1: "+t1_ref[i]+" T2: "+t2_ref[i]+" T3: "+t3_ref[i]+" T4: "+t4_ref[i]);    
                            }
                                //System.out.println("Grade com sobra:");
                                //System.out.println("REF: "+z+" T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);    
                            
                                dividir(t1_ref[z], t2_ref[z], t3_ref[z], t4_ref[z]);
                                
                                
                                
                }//fim case normal
                       
                ///*******************ESPECIAL
                case "ESPECIAL":
                       { 
                       System.out.println("Entrou no ESPECIAL");    
                            
                            //double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            divisor = 0; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            qtd_divisoria = 0;
                            qtd_divisoria_integer = 0;
                            sobra_divisoria = 0;
                            sobra_divisoria_integer = 0;
                            divisor = qtd_tamanho;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1_ref[z] = qtd_divisoria_integer;
                            t2_ref[z] = qtd_divisoria_integer;
                            t3_ref[z] = qtd_divisoria_integer;
                            t4_ref[z] = 0;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+0);

                            //adicionando sobras 
                            
                            //for (int w = 1; w <= sobra_divisoria_integer; w++) {
                                System.out.println("Adicionando sobra: "+sobra_divisoria_integer+"");        
                                switch (sobra_divisoria_integer) {
                                        case 1:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                //não tem tamanho 4 no especial
                                                /*else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 1;
                                                }
                                                */
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 2:
                                        {
                                            
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }/*
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                                */
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 3:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                /*
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }    
                                            */
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 4:
                                        {
                                                 if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                        
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 5:
                                        {
                                                
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+2;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                /* else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+2;//2?
                                                    t2_ref[z] = t2_ref[z]+1;//1?
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                */
                                                
                                                //tamanho_que_parou++;
                                                //if (tamanho_que_parou>qtd_tamanho){
                                                //tamanho_que_parou=1;
                                                //}
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        default:
                                        {
                                                t1_ref[z] = t1_ref[z];
                                                t2_ref[z] = t2_ref[z];
                                                t3_ref[z] = t3_ref[z];
                                                t4_ref[z] = 0;
                                                
                                       
                                            System.out.println("Não sobrou, tamanho que parou: "+tamanho_que_parou);
                                        }
                            }
                                //como ficou grade com sobra
                            
                                //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("REF: "+i+" T1: "+t1_ref[i]+" T2: "+t2_ref[i]+" T3: "+t3_ref[i]+" T4: "+0);    
                            }
                                //System.out.println("Grade com sobra:");
                                //System.out.println("REF: "+z+" T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);    
                            
                                dividir(t1_ref[z], t2_ref[z], t3_ref[z], 0);
                                
                                
                                
                }//fim case ESPECIAL       
    //*********************************************NORMAL
                case "NORMAL":
                       { 
                       System.out.println("Entrou no Normal");    
                            
                            //double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            divisor = 0; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            qtd_divisoria = 0;
                            qtd_divisoria_integer = 0;
                            sobra_divisoria = 0;
                            sobra_divisoria_integer = 0;
                            divisor = 6;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1_ref[z] = qtd_divisoria_integer;
                            t2_ref[z] = qtd_divisoria_integer*2;
                            t3_ref[z] = qtd_divisoria_integer*2;
                            t4_ref[z] = qtd_divisoria_integer;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);

                            //adicionando sobras 
                            
                            //for (int w = 1; w <= sobra_divisoria_integer; w++) {
                                System.out.println("Adicionando sobra: "+sobra_divisoria_integer+"");        
                                switch (sobra_divisoria_integer) {
                                        case 1:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 1;
                                                }
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 2:
                                        {
                                            
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                            
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 3:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }    
                                            
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 4:
                                        {
                                                t1_ref[z] = t1_ref[z]+1;
                                                t2_ref[z] = t2_ref[z]+1;//+
                                                t3_ref[z] = t3_ref[z]+1;//+
                                                t4_ref[z] = t4_ref[z]+1;
                                                tamanho_que_parou = 1;
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 5:
                                        {
                                                
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+2;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+2;//2?
                                                    t2_ref[z] = t2_ref[z]+1;//1?
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                            
                                                //tamanho_que_parou++;
                                                //if (tamanho_que_parou>qtd_tamanho){
                                                //tamanho_que_parou=1;
                                                //}
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        default:
                                        {
                                                t1_ref[z] = t1_ref[z];
                                                t2_ref[z] = t2_ref[z];
                                                t3_ref[z] = t3_ref[z];
                                                t4_ref[z] = t4_ref[z];
                                                
                                       
                                            System.out.println("Não sobrou, tamanho que parou: "+tamanho_que_parou);
                                        }
                            }
                                //como ficou grade com sobra
                            
                                //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("REF: "+i+" T1: "+t1_ref[i]+" T2: "+t2_ref[i]+" T3: "+t3_ref[i]+" T4: "+t4_ref[i]);    
                            }
                                //System.out.println("Grade com sobra:");
                                //System.out.println("REF: "+z+" T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);    
                            
                                dividir(t1_ref[z], t2_ref[z], t3_ref[z], t4_ref[z]);
                                
                                
                                
                }//fim case normal
                
            }  //fim case tipo 
                                
                                
                            
        }//fim for qtd de refs
        
        zerar_campos_sortidos();                    
                           
    }//GEN-LAST:event_btn_Dividir_Sortido_NormalActionPerformed

    public void zerar_campos_sortidos(){
        //zerando campos
        jComboBox_refs.removeAllItems();
        txt_qtd_ref.setText(null);
        txt_qtd_total_sortido.setText(null);
        txt_Tipo_tamanho_categoria.setText(null);
    }
    private void btn_Dividir_Sortido_NormalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_NormalFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Sortido_Normal);
    }//GEN-LAST:event_btn_Dividir_Sortido_NormalFocusGained

    private void btn_Dividir_Sortido_JuvenilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_JuvenilActionPerformed
        // TODO add your handling code here:
        Integer divisor;
                            
                            //pega do count(*) ou outro método..
                            Integer qtd_ref = Integer.parseInt(txt_qtd_ref.getText());
                            
                            Double qtd_total_sortido = Double.parseDouble(txt_qtd_total_sortido.getText());
                            double qtd_divisoria;
                            Integer qtd_divisoria_integer;
                            double sobra_divisoria;
                            Integer sobra_divisoria_integer;
                            divisor = qtd_ref;
                            
                            qtd_divisoria = qtd_total_sortido/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total_sortido+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            
                            int[] qtd_sortido = new int[30];
                            //começar no t1;
                            Integer tamanho_que_parou = 1;
                            
                            System.out.println("Qtd de cada REF sem adicionar sobra:");
                            for (int i = 1; i <= qtd_ref; i++) {
                                qtd_sortido[i]=qtd_divisoria_integer;
                                System.out.println("Qtd da ref["+i+"] = "+qtd_sortido[i]);
                            }
                           //adicionar sobras
                           //new 
                           Integer ref_que_parou;
                           if (jCheckBox_sortido_maximo.isSelected()){
                              /* sorterar ref para iniciar */
                            Random gerador = new Random();
                            ref_que_parou = gerador.nextInt(qtd_ref) + 1;
                            System.out.println("REF sorteado: "+ref_que_parou);
                             
                           }else{
                            ref_que_parou = 1;
                           }
                            
                            
                                    System.out.println("Adicionando sobra Qtd começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra qtd inteiro: "+sobra_divisoria_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        qtd_sortido[ref_que_parou]=qtd_sortido[ref_que_parou]+1;
                                        System.out.println("Qtd_sortido["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println(" REF "+i+": Qtd: "+qtd_sortido[i]);
                            }
                            
                            //executando
                            
                            //t1_ref[1] = qtd do t1 da ref[1]
                            int[] t1_ref = new int[30];
                            int[] t2_ref = new int[30];
                            int[] t3_ref = new int[30];
                            int[] t4_ref = new int[30];
                            
                            
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            for (int z = 1; z <= qtd_ref; z++) {
                                
                                
                                
                                combobox_cor.removeAllItems();
                                //pegando dados da referencia inserida
                                txt_ref.setText(jComboBox_refs.getItemAt(z).toString());

                                for (ProdutoDTO p : dao.getProdutosRef(jComboBox_refs.getItemAt(z).toString())) {
                                    
                                    
                                    //adicionando cores na combobox
                                    combobox_cor.addItem("TODAS");
                                    //passando codigo e combobox para add.
                                    tdgCTR.cores_ref(txt_ref.getText(), combobox_cor);
                                    
                                    excluir_cor_do_pedido();
                                    

                                    txt_Tipo_tamanho.setText(p.getTipo_tamanho());
                                    txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());
                                    Integer qtd_cores = combobox_cor.getItemCount()-1;
                                    txt_qtd_cores.setText(qtd_cores.toString());
                                    String tipo = txt_Tipo_tamanho.getText();
                                
                                }
                                
                                
                                //txt_ref.setText(combobox_refs.getItemAt(z).toString());
                                System.err.println("Valor combobox refs: "+jComboBox_refs.getItemAt(z).toString());
                                //combobox_cor.requestFocus();//buscar dados da ref com o focuslost
                                Integer qtd_sortido_integer = qtd_sortido[z];
                                txt_qtd_total.setText((qtd_sortido_integer.toString()));
                                
                                
                                //btn_Dividir_AutomaticoActionPerformed(evt);
                                //separar grade de acordo informando a qtd_total de cada ref, fazer sequencia
                                
                                
                         //*******************
                                
                //dividir por tipo especial, normal, juvenil       
                String tipo = txt_Tipo_tamanho.getText();
                
                       System.out.println("Entrou no JUVENIL");    
                            
                            //double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            divisor = 0; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            qtd_divisoria = 0;
                            qtd_divisoria_integer = 0;
                            sobra_divisoria = 0;
                            sobra_divisoria_integer = 0;
                            divisor = qtd_tamanho;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1_ref[z] = qtd_divisoria_integer;
                            t2_ref[z] = qtd_divisoria_integer;
                            t3_ref[z] = qtd_divisoria_integer;
                            t4_ref[z] = qtd_divisoria_integer;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);

                            //adicionando sobras 
                            
                            //for (int w = 1; w <= sobra_divisoria_integer; w++) {
                                System.out.println("Adicionando sobra: "+sobra_divisoria_integer+"");        
                                switch (sobra_divisoria_integer) {
                                        case 1:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 1;
                                                }
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 2:
                                        {
                                            
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                            
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 3:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }    
                                            
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 4:
                                        {
                                                t1_ref[z] = t1_ref[z]+1;
                                                t2_ref[z] = t2_ref[z]+1;//+
                                                t3_ref[z] = t3_ref[z]+1;//+
                                                t4_ref[z] = t4_ref[z]+1;
                                                tamanho_que_parou = 1;
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 5:
                                        {
                                                
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+2;
                                                    tamanho_que_parou = 4;
                                                }
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+2;//2?
                                                    t2_ref[z] = t2_ref[z]+1;//1?
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                            
                                                //tamanho_que_parou++;
                                                //if (tamanho_que_parou>qtd_tamanho){
                                                //tamanho_que_parou=1;
                                                //}
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        default:
                                        {
                                                t1_ref[z] = t1_ref[z];
                                                t2_ref[z] = t2_ref[z];
                                                t3_ref[z] = t3_ref[z];
                                                t4_ref[z] = t4_ref[z];
                                                
                                       
                                            System.out.println("Não sobrou, tamanho que parou: "+tamanho_que_parou);
                                        }
                            }
                                //como ficou grade com sobra
                            
                                //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("REF: "+i+" T1: "+t1_ref[i]+" T2: "+t2_ref[i]+" T3: "+t3_ref[i]+" T4: "+t4_ref[i]);    
                            }
                                //System.out.println("Grade com sobra:");
                                //System.out.println("REF: "+z+" T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);    
                            
                                dividir(t1_ref[z], t2_ref[z], t3_ref[z], t4_ref[z]);
                                
                                
                                
               
                       
                
                                
                                
                            
        }//fim for qtd de refs
                            
       zerar_campos_sortidos(); 
    }//GEN-LAST:event_btn_Dividir_Sortido_JuvenilActionPerformed

    private void btn_Dividir_Sortido_JuvenilFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_JuvenilFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Sortido_Juvenil);
    }//GEN-LAST:event_btn_Dividir_Sortido_JuvenilFocusGained

    private void btn_Dividir_Sortido_EspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_EspecialActionPerformed
        // TODO add your handling code here:
        
        
                            
                            Integer divisor;
                            
                            //pega do count(*) ou outro método..
                            Integer qtd_ref = Integer.parseInt(txt_qtd_ref.getText());
                            
                            Double qtd_total_sortido = Double.parseDouble(txt_qtd_total_sortido.getText());
                            double qtd_divisoria;
                            Integer qtd_divisoria_integer;
                            double sobra_divisoria;
                            Integer sobra_divisoria_integer;
                            divisor = qtd_ref;
                            
                            qtd_divisoria = qtd_total_sortido/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total_sortido+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            
                            int[] qtd_sortido = new int[30];
                            //começar no t1;
                            Integer tamanho_que_parou = 1;
                            
                            System.out.println("Qtd de cada REF sem adicionar sobra:");
                            for (int i = 1; i <= qtd_ref; i++) {
                                qtd_sortido[i]=qtd_divisoria_integer;
                                System.out.println("Qtd da ref["+i+"] = "+qtd_sortido[i]);
                            }
                           //adicionar sobras
                           //new 
                           Integer ref_que_parou;
                           if (jCheckBox_sortido_maximo.isSelected()){
                              /* sorterar ref para iniciar */
                            Random gerador = new Random();
                            ref_que_parou = gerador.nextInt(qtd_ref) + 1;
                            System.out.println("REF sorteado: "+ref_que_parou);
                             
                           }else{
                            ref_que_parou = 1;
                           }
                            
                                    System.out.println("Adicionando sobra Qtd começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra qtd inteiro: "+sobra_divisoria_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        qtd_sortido[ref_que_parou]=qtd_sortido[ref_que_parou]+1;
                                        System.out.println("Qtd_sortido["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println(" REF "+i+": Qtd: "+qtd_sortido[i]);
                            }
                            
                            //executando
                            
                            //t1_ref[1] = qtd do t1 da ref[1]
                            int[] t1_ref = new int[30];
                            int[] t2_ref = new int[30];
                            int[] t3_ref = new int[30];
                            int[] t4_ref = new int[30];
                            
                            
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            for (int z = 1; z <= qtd_ref; z++) {
                                
                                
                                
                                combobox_cor.removeAllItems();
                                //pegando dados da referencia inserida
                                txt_ref.setText(jComboBox_refs.getItemAt(z).toString());

                                for (ProdutoDTO p : dao.getProdutosRef(jComboBox_refs.getItemAt(z).toString())) {
                                    
                                    //adicionando cores na combobox
                                    combobox_cor.addItem("TODAS");
                                    //passando codigo e combobox para add.
                                    tdgCTR.cores_ref(txt_ref.getText(), combobox_cor);
                                    
                                    excluir_cor_do_pedido();

                                    txt_Tipo_tamanho.setText(p.getTipo_tamanho());
                                    txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());
                                    Integer qtd_cores = combobox_cor.getItemCount()-1;
                                    txt_qtd_cores.setText(qtd_cores.toString());
                                    String tipo = txt_Tipo_tamanho.getText();
                                
                                }
                                
                                
                                //txt_ref.setText(combobox_refs.getItemAt(z).toString());
                                System.err.println("Valor combobox refs: "+jComboBox_refs.getItemAt(z).toString());
                                //combobox_cor.requestFocus();//buscar dados da ref com o focuslost
                                Integer qtd_sortido_integer = qtd_sortido[z];
                                txt_qtd_total.setText((qtd_sortido_integer.toString()));
                                
                                
                                //btn_Dividir_AutomaticoActionPerformed(evt);
                                //separar grade de acordo informando a qtd_total de cada ref, fazer sequencia
                                
                                
                         //*******************
                                
                
                       System.out.println("Entrou no ESPECIAL");    
                            
                            //double t1 = 0,t2 = 0,t3 = 0,t4 = 0;
                            divisor = 0; 
                            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                            Double qtd_total = Double.parseDouble(txt_qtd_total.getText());
                            qtd_divisoria = 0;
                            qtd_divisoria_integer = 0;
                            sobra_divisoria = 0;
                            sobra_divisoria_integer = 0;
                            divisor = qtd_tamanho;
                            
                            qtd_divisoria = qtd_total/divisor;
                            qtd_divisoria_integer = (int) (qtd_divisoria);
                            sobra_divisoria = (qtd_divisoria - qtd_divisoria_integer)*divisor; 
                            sobra_divisoria_integer = (int) (Math.round(sobra_divisoria));
                            System.out.println("Qtd Total: "+qtd_total+" Qtd_divisoria: "+qtd_divisoria+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria);
                            t1_ref[z] = qtd_divisoria_integer;
                            t2_ref[z] = qtd_divisoria_integer;
                            t3_ref[z] = qtd_divisoria_integer;
                            t4_ref[z] = 0;
                            
                            //como ficou grade sem adicionar a sobra
                            System.out.println("Grade sem adicionar sobra:");
                            System.out.println("T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+0);

                            //adicionando sobras 
                            
                            //for (int w = 1; w <= sobra_divisoria_integer; w++) {
                                System.out.println("Adicionando sobra: "+sobra_divisoria_integer+"");        
                                switch (sobra_divisoria_integer) {
                                        case 1:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                //não tem tamanho 4 no especial
                                                /*else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 1;
                                                }
                                                */
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 2:
                                        {
                                            
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z];
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z];
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }/*
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z];
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 2;
                                                }
                                                */
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 3:
                                        {
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                /*
                                                else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z];
                                                    tamanho_que_parou = 3;
                                                }    
                                            */
                                                
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 4:
                                        {
                                                 if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                        
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        case 5:
                                        {
                                                
                                                if (tamanho_que_parou == 1){
                                                    t1_ref[z] = t1_ref[z]+1;
                                                    t2_ref[z] = t2_ref[z]+2;//+
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 3;
                                                }
                                                else if (tamanho_que_parou == 2){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+1;
                                                    t3_ref[z] = t3_ref[z]+2;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 1;
                                                }
                                                else if (tamanho_que_parou == 3){
                                                    t1_ref[z] = t1_ref[z]+2;
                                                    t2_ref[z] = t2_ref[z]+2;
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = 0;
                                                    tamanho_que_parou = 2;
                                                }
                                                /* else if (tamanho_que_parou == 4){
                                                    t1_ref[z] = t1_ref[z]+2;//2?
                                                    t2_ref[z] = t2_ref[z]+1;//1?
                                                    t3_ref[z] = t3_ref[z]+1;
                                                    t4_ref[z] = t4_ref[z]+1;
                                                    tamanho_que_parou = 1;
                                                }
                                                */
                                                
                                                //tamanho_que_parou++;
                                                //if (tamanho_que_parou>qtd_tamanho){
                                                //tamanho_que_parou=1;
                                                //}
                                       
                                            System.out.println("tamanho que parou: "+tamanho_que_parou);
                                            break;
                                        }
                                        default:
                                        {
                                                t1_ref[z] = t1_ref[z];
                                                t2_ref[z] = t2_ref[z];
                                                t3_ref[z] = t3_ref[z];
                                                t4_ref[z] = 0;
                                                
                                       
                                            System.out.println("Não sobrou, tamanho que parou: "+tamanho_que_parou);
                                        }
                            }
                                //como ficou grade com sobra
                            
                                //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("REF: "+i+" T1: "+t1_ref[i]+" T2: "+t2_ref[i]+" T3: "+t3_ref[i]+" T4: "+0);    
                            }
                                //System.out.println("Grade com sobra:");
                                //System.out.println("REF: "+z+" T1: "+t1_ref[z]+" T2: "+t2_ref[z]+" T3: "+t3_ref[z]+" T4: "+t4_ref[z]);    
                            
                                dividir(t1_ref[z], t2_ref[z], t3_ref[z], 0);
                                
                            
                            
        }//fim for qtd de refs
    zerar_campos_sortidos();                        
    }//GEN-LAST:event_btn_Dividir_Sortido_EspecialActionPerformed

    private void btn_Dividir_Sortido_EspecialFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_EspecialFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Sortido_Especial);
    }//GEN-LAST:event_btn_Dividir_Sortido_EspecialFocusGained

    private void jComboBox_CategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_CategoriaItemStateChanged
        /*
        01 MACHAO
02 GOLA CARECA
03 GOLA V
04 GOLA POLO
05 CARECA ESPECIAL
06 V ESPECIAL
07 POLO ESPECIAL
08 CARECA JUVENIL
09 V JUVENIL
10 POLO JUVENIL
11 CARECA ML
12 V ML
13 POLO ML
14 PIQUET
        
        */
        if (jComboBox_Categoria.getSelectedIndex()==0){
            //zerando campos
            jComboBox_refs.removeAllItems();
            txt_qtd_ref.setText(null);
            txt_qtd_total_sortido.setText(null);
            txt_Tipo_tamanho_categoria.setText(null);
        }else{
            //pegar refs da categoria & tipo_tamanho
             Refs_Categoria();
             
             if (jRadioButton_Sortido_Automatico.isSelected()){
                 
             
                //botões visiveis de acordo com tipo_tamanho_categoria
                 switch (txt_Tipo_tamanho_categoria.getText()) {
                    case "ESPECIAL":
                    {
                        btn_Dividir_Sortido_Especial.setVisible(true);
                        btn_Dividir_Sortido_Normal.setVisible(false);
                        btn_Dividir_Sortido_Juvenil.setVisible(false);
                        break;
                    }
                    case "JUVENIL":
                    {
                        btn_Dividir_Sortido_Especial.setVisible(false);
                        btn_Dividir_Sortido_Normal.setVisible(false);
                        btn_Dividir_Sortido_Juvenil.setVisible(true);
                        break;
                    }
                    case "NORMAL":{
                        btn_Dividir_Sortido_Especial.setVisible(false);
                        btn_Dividir_Sortido_Normal.setVisible(true);
                        btn_Dividir_Sortido_Juvenil.setVisible(false);
                        break;
                    }
                    default:{
                        btn_Dividir_Sortido_Especial.setVisible(false);
                        btn_Dividir_Sortido_Normal.setVisible(false);
                        btn_Dividir_Sortido_Juvenil.setVisible(false);
                        break;
                    }

                }
        }else{
                        btn_Dividir_Sortido_Especial.setVisible(false);
                        btn_Dividir_Sortido_Normal.setVisible(false);
                        btn_Dividir_Sortido_Juvenil.setVisible(false);
                        
                        if (txt_Tipo_tamanho_categoria.getText().equals("ESPECIAL")) {
                            txt_t4_grade_sortido.setText("0");
                            txt_t4_grade_sortido.enable(false);
                        }else{
                            txt_t4_grade_sortido.setEnabled(true);
                        }
                        
                        
             }
             
        }     
    }//GEN-LAST:event_jComboBox_CategoriaItemStateChanged

    private void jComboBox_CategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_CategoriaKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode() == evt.VK_ENTER){
            
                txt_qtd_total_sortido.requestFocus();
           
        } 
    }//GEN-LAST:event_jComboBox_CategoriaKeyPressed

    private void jRadioButton121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton121ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton121ActionPerformed

    private void remover_cor(){
        
        if((combobox_cor.getSelectedItem().equals("TODAS"))){
            JOptionPane.showMessageDialog(null, "A opção 'TODAS' não pode ser removida.");
        }else{

            String cor;
            cor = combobox_cor.getSelectedItem().toString();
            combobox_cor.removeItem(cor);
            
            
            //remove cor da jList;
            lista.remove(combobox_cor.getSelectedIndex());
            
            //volta para a opcao todas do combobox
            combobox_cor.setSelectedIndex(0);
            
            
            
            
        }
        //atualizar qtd_cor
        Integer qtd_cor = combobox_cor.getItemCount()-1;//Diminuir 1 por causa do item 0 (TODOS)
        txt_qtd_cores.setText(qtd_cor.toString());
    }
    
    private void jCheckBox_disponivelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox_disponivelStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCheckBox_disponivelStateChanged

    private void txt_diasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diasFocusLost
        // TODO add your handling code here:
        jComboBox_Categoria.setSelectedIndex(0);
    }//GEN-LAST:event_txt_diasFocusLost

    private void txt_t1_grade_sortidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t1_grade_sortidoFocusLost
        // TODO add your handling code here:
        if (txt_t1_grade_sortido.getText().equals("")){
        txt_t1_grade_sortido.setText("0");
        }
        
    }//GEN-LAST:event_txt_t1_grade_sortidoFocusLost

    private void txt_t2_grade_sortidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t2_grade_sortidoFocusLost
        // TODO add your handling code here:
        if (txt_t2_grade_sortido.getText().equals("")){
        txt_t2_grade_sortido.setText("0");
        }
    }//GEN-LAST:event_txt_t2_grade_sortidoFocusLost

    private void txt_t3_grade_sortidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t3_grade_sortidoFocusLost
        // TODO add your handling code here:
        if (txt_t3_grade_sortido.getText().equals("")){
        txt_t3_grade_sortido.setText("0");
        }
    }//GEN-LAST:event_txt_t3_grade_sortidoFocusLost

    private void txt_t4_grade_sortidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_t4_grade_sortidoFocusLost
        // TODO add your handling code here:
        if (txt_t4_grade_sortido.getText().equals("")){
        txt_t4_grade_sortido.setText("0");
        }
    }//GEN-LAST:event_txt_t4_grade_sortidoFocusLost

    private void btn_Dividir_Sortido_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_ManualActionPerformed
        // TODO add your handling code here:
        if ((txt_n_pedido.getText().isEmpty()) || (txt_t1_grade_sortido.getText().isEmpty()) || (txt_t2_grade_sortido.getText().isEmpty()) || (txt_t3_grade_sortido.getText().isEmpty()) || (txt_t4_grade_sortido.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Complete todos os campos.");
                   
                }
                else {
                
                //pega do count(*) ou outro método..
                            Integer qtd_ref = Integer.parseInt(txt_qtd_ref.getText());
                            Integer divisor = qtd_ref;//mesma coisa
                            
                            Double qtd_total_t1_sortido = Double.parseDouble(txt_t1_grade_sortido.getText());
                            Double qtd_total_t2_sortido = Double.parseDouble(txt_t2_grade_sortido.getText());
                            Double qtd_total_t3_sortido = Double.parseDouble(txt_t3_grade_sortido.getText());
                            Double qtd_total_t4_sortido = Double.parseDouble(txt_t4_grade_sortido.getText());
                            double qtd_divisoria_t1;
                            double qtd_divisoria_t2;
                            double qtd_divisoria_t3;
                            double qtd_divisoria_t4;
                            Integer qtd_divisoria_t1_integer;
                            Integer qtd_divisoria_t2_integer;
                            Integer qtd_divisoria_t3_integer;
                            Integer qtd_divisoria_t4_integer;
                            double sobra_divisoria_t1;
                            double sobra_divisoria_t2;
                            double sobra_divisoria_t3;
                            double sobra_divisoria_t4;
                            Integer sobra_divisoria_t1_integer;
                            Integer sobra_divisoria_t2_integer;
                            Integer sobra_divisoria_t3_integer;
                            Integer sobra_divisoria_t4_integer;
                            
                            //T1
                            qtd_divisoria_t1 = qtd_total_t1_sortido/divisor;
                            qtd_divisoria_t1_integer = (int) (qtd_divisoria_t1);
                            sobra_divisoria_t1 = (qtd_divisoria_t1 - qtd_divisoria_t1_integer)*divisor; 
                            sobra_divisoria_t1_integer = (int) (Math.round(sobra_divisoria_t1));
                            System.out.println("Qtd Total T1: "+qtd_total_t1_sortido+" Qtd_divisoria: "+qtd_divisoria_t1+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria_t1);
                            
                            //T2
                            qtd_divisoria_t2 = qtd_total_t2_sortido/divisor;
                            qtd_divisoria_t2_integer = (int) (qtd_divisoria_t2);
                            sobra_divisoria_t2 = (qtd_divisoria_t2 - qtd_divisoria_t2_integer)*divisor; 
                            sobra_divisoria_t2_integer = (int) (Math.round(sobra_divisoria_t2));
                            System.out.println("Qtd Total T2: "+qtd_total_t2_sortido+" Qtd_divisoria: "+qtd_divisoria_t2+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria_t2);
                            
                            //T3
                            qtd_divisoria_t3 = qtd_total_t3_sortido/divisor;
                            qtd_divisoria_t3_integer = (int) (qtd_divisoria_t3);
                            sobra_divisoria_t3 = (qtd_divisoria_t3 - qtd_divisoria_t3_integer)*divisor; 
                            sobra_divisoria_t3_integer = (int) (Math.round(sobra_divisoria_t3));
                            System.out.println("Qtd Total T3: "+qtd_total_t3_sortido+" Qtd_divisoria: "+qtd_divisoria_t3+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria_t3);
                            
                            //T4
                            qtd_divisoria_t4 = qtd_total_t4_sortido/divisor;
                            qtd_divisoria_t4_integer = (int) (qtd_divisoria_t4);
                            sobra_divisoria_t4 = (qtd_divisoria_t4 - qtd_divisoria_t4_integer)*divisor; 
                            sobra_divisoria_t4_integer = (int) (Math.round(sobra_divisoria_t4));
                            System.out.println("Qtd Total T4: "+qtd_total_t4_sortido+" Qtd_divisoria: "+qtd_divisoria_t4+" Divisor: "+divisor);
                            System.out.println("Sobra divisoria: "+sobra_divisoria_t4);
                            
                            int[] t1 = new int[30];
                            int[] t2 = new int[30];
                            int[] t3 = new int[30];
                            int[] t4 = new int[30];
                            //começar no t1;
                            Integer tamanho_que_parou = 1;
                            
                            System.out.println("Qtd de cada REF sem adicionar sobra:");
                            for (int i = 1; i <= qtd_ref; i++) {
                                t1[i]=qtd_divisoria_t1_integer;
                                t2[i]=qtd_divisoria_t2_integer;
                                t3[i]=qtd_divisoria_t3_integer;
                                t4[i]=qtd_divisoria_t4_integer;
                                System.out.println("Qtd da ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                            }
                            
                            
                            //new 
                           Integer ref_que_parou;
                           if (jCheckBox_sortido_maximo.isSelected()){
                              /* sorterar ref para iniciar */
                            Random gerador = new Random();
                            ref_que_parou = gerador.nextInt(qtd_ref) + 1;
                            System.out.println("REF sorteado: "+ref_que_parou);
                             
                           }else{
                            ref_que_parou = 1;
                           }
                            
                            
                            Integer inicio = ref_que_parou;
                           
                            
                            
                            //ADICIONANDO SOBRA T1
                                    System.out.println("Adicionando sobra T1 começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra t1 inteiro: "+sobra_divisoria_t1_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_t1_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        t1[ref_que_parou]=t1[ref_que_parou]+1;
                                        System.out.println("t1["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("Ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                            }
                            
                            //FIM ADICIONA T1
                            
                            //ADICIONANDO SOBRA T2
                            //se sortido NAO* for marcado:
                            if (!jCheckBox_sortido_maximo.isSelected()){
                               
                            //SETANDO SOBRA COMEÇAR NA MESMA REF DA SOBRA DO T1
                            ref_que_parou = inicio;
                            }
                            
                                    System.out.println("Adicionando sobra T2 começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra t2 inteiro: "+sobra_divisoria_t2_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_t2_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        t2[ref_que_parou]=t2[ref_que_parou]+1;
                                        System.out.println("t2["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("Ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                            }
                            
                            //FIM ADICIONA T2
                            
                            //ADICIONANDO SOBRA T3
                            
                            //se sortido NAO* for marcado:
                            if (!jCheckBox_sortido_maximo.isSelected()){
                               
                            //SETANDO SOBRA COMEÇAR NA MESMA REF DA SOBRA DO T1
                            ref_que_parou = inicio;
                            }
                            
                                    System.out.println("Adicionando sobra T3 começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra t3 inteiro: "+sobra_divisoria_t3_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_t3_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        t3[ref_que_parou]=t3[ref_que_parou]+1;
                                        System.out.println("t3["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("Ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                            }
                            
                            //FIM ADICIONA T3
                            
                            //ADICIONANDO SOBRA T4
                            
                            //se sortido NAO* for marcado:
                            if (!jCheckBox_sortido_maximo.isSelected()){
                               
                            //SETANDO SOBRA COMEÇAR NA MESMA REF DA SOBRA DO T1
                            ref_que_parou = inicio;
                            }
                                    System.out.println("Adicionando sobra T4 começando na ref: "+ref_que_parou);
                                    System.out.println("Sobra t4 inteiro: "+sobra_divisoria_t4_integer);                    
                                    for (int i = 1; i <= sobra_divisoria_t4_integer; i++) {
                                        System.out.println("Adicionando sobra: "+i+"");
                                        t4[ref_que_parou]=t4[ref_que_parou]+1;
                                        System.out.println("t4["+ref_que_parou+"]+1");
                                        ref_que_parou++;
                                            if (ref_que_parou>qtd_ref){
                                                ref_que_parou=1;
                                            }
                                        System.out.println("Ref que parou: "+ref_que_parou);

                                    }    
                            //mostrando
                            for (int i = 1; i <= qtd_ref; i++) {
                               System.out.println("Ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                            }
                            
                            //FIM ADICIONA T4
                            
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            //mostrando final
                            for (int i = 1; i <= qtd_ref; i++) {
                               
                                
                                System.out.println("Final com sobras:");
                                System.out.println("Ref["+i+"] - t1: "+t1[i]+" t2: "+t2[i]+" t3: "+t3[i]+" t4: "+t4[i]);
                                
                                
                                combobox_cor.removeAllItems();
                                //pegando ref da combobox
                                txt_ref.setText(jComboBox_refs.getItemAt(i).toString());
                                
                                //pegando dados da ref
                                        for (ProdutoDTO p : dao.getProdutosRef(jComboBox_refs.getItemAt(i).toString())) {
                                    
                                            //adicionando cores na combobox
                                            combobox_cor.addItem("TODAS");
                                            //passando codigo e combobox para add.
                                            tdgCTR.cores_ref(txt_ref.getText(), combobox_cor);
                                            excluir_cor_do_pedido();

                                            txt_Tipo_tamanho.setText(p.getTipo_tamanho());
                                            txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());
                                            Integer qtd_cores = combobox_cor.getItemCount()-1;
                                            txt_qtd_cores.setText(qtd_cores.toString());
                                            String tipo = txt_Tipo_tamanho.getText();

                                        }
                                
                                
                                //inserir
                                
                                dividir(t1[i], t2[i], t3[i], t4[i]);
                            }
                            
                            
                            //so executar o dividir(i guess)
            /*
                        double t1_total, t2_total, t3_total, t4_total;
        
                        t1_total = Double.parseDouble(txt_t1_grade_sortido.getText());
                        t2_total = Double.parseDouble(txt_t2_grade_sortido.getText());
                        t3_total = Double.parseDouble(txt_t3_grade_sortido.getText());
                        t4_total = Double.parseDouble(txt_t4_grade_sortido.getText());
                        
                        //dividir entre as ref~
                        //************se fudeu
                        
                        dividir(t1, t2, t3, t4);
                        
                        //zerar campos
                        txt_t1_grade_sortido.setText("");
                        txt_t2_grade_sortido.setText("");
                        txt_t3_grade_sortido.setText("");
                        txt_t4_grade_sortido.setText("");
      */
         }   
        
    }//GEN-LAST:event_btn_Dividir_Sortido_ManualActionPerformed

    private void btn_Dividir_Sortido_ManualFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Dividir_Sortido_ManualFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Dividir_Sortido_Manual);
    }//GEN-LAST:event_btn_Dividir_Sortido_ManualFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox_disponivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox_disponivelItemStateChanged
        // TODO add your handling code here:
        jComboBox_Categoria.setSelectedIndex(0);
    }//GEN-LAST:event_jCheckBox_disponivelItemStateChanged

    private void jRadioButton_Sortido_AutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Sortido_AutomaticoActionPerformed
        // TODO add your handling code here:
        if (jRadioButton_Sortido_Automatico.isSelected()){
            jPanel_Manual1.setVisible(false);
            txt_qtd_total_sortido.setEnabled(true);
            
        }
        else{
            
            jPanel_Manual1.setVisible(true);
            txt_qtd_total_sortido.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton_Sortido_AutomaticoActionPerformed

    private void jRadioButton_Sortido_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Sortido_ManualActionPerformed
        // TODO add your handling code here:
                        btn_Dividir_Sortido_Especial.setVisible(false);
                        btn_Dividir_Sortido_Normal.setVisible(false);
                        btn_Dividir_Sortido_Juvenil.setVisible(false);
        if (jRadioButton_Sortido_Manual.isSelected()){
            jPanel_Manual1.setVisible(true);
            txt_qtd_total_sortido.setEnabled(false);
            
        }
        else{
            
            jPanel_Manual1.setVisible(false);
            txt_qtd_total_sortido.setEnabled(true);
        }
    }//GEN-LAST:event_jRadioButton_Sortido_ManualActionPerformed

    private void txt_saldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_saldoFocusLost
        // TODO add your handling code here:
        jComboBox_Categoria.setSelectedIndex(0);
    }//GEN-LAST:event_txt_saldoFocusLost

    private void txt_n_pedido_originalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_n_pedido_originalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_pedido_originalFocusLost

    private void txt_n_pedido_novoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_n_pedido_novoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_pedido_novoFocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //insert into item_pedido (n_pedido, codigo, cor, t1, t2, t3, t4, cor_original, total, loja) 
        //select item_pedido.getN_Pedido(), codigo, cor, t1, t2, t3, t4, cor_original, total, item_pediddo.getLoja() from item_pedido where n_pedido = 1 and loja = 'LOJA 01';
        copia_itens_pedido();
        listar_item_pedido();
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        HashMap parametros = new HashMap();  
        RelatorioDAO dao = new RelatorioDAO();
        String n_pedido = txt_n_pedido.getText();
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        JRResultSetDataSource jrRS = new JRResultSetDataSource(dao.listarPedido(n_pedido));  
        JasperPrint jasperPrint;  
       
        try {  
            //PEGANDO DO DIRETORIO MAZIN
            //jasperPrint = JasperFillManager.fillReport("\\\\"+TDG_SIS_VIEW.ip_servidor+"\\TDG_sis_prod\\relatorios\\"+TDG_SIS_VIEW.banco_selecionado+"\\n_pedido.jasper" , parametros, jrRS);  
            
            InputStream inputStream;
            
            if (jCheckBox_loja.isSelected()){
                inputStream = getClass().getResourceAsStream( "/relatorios_ireport/n_pedido_por_loja.jasper" );
            }else{
                inputStream = getClass().getResourceAsStream( "/relatorios_ireport/n_pedido.jasper" );
            }
            
            
            jasperPrint = JasperFillManager.fillReport(inputStream, parametros, jrRS);  
            JasperViewer.viewReport(jasperPrint, false);  
        } catch (JRException ex) {  
            JOptionPane.showMessageDialog(null, ex);  
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));   
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        if((jComboBox_refs.getSelectedItem().equals("TODAS"))){
            JOptionPane.showMessageDialog(null, "A opção 'TODAS' não pode ser removida.");
        }else{

            String ref;
            ref = jComboBox_refs.getSelectedItem().toString();
            jComboBox_refs.removeItem(ref);
            jComboBox_refs.setSelectedIndex(0);
        }
        //atualizar qtd_cor
        Integer qtd_ref = jComboBox_refs.getItemCount()-1;//Diminuir 1 por causa do item 0 (TODOS)
        txt_qtd_ref.setText(qtd_ref.toString());
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jCheckBox_sortido_maximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_sortido_maximoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_sortido_maximoActionPerformed

    private void btn_adicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionaActionPerformed
        // TODO add your handling code here:
        String cor;
        cor = jComboBox_Cores.getSelectedItem().toString();

        if((jComboBox_Cores.getSelectedItem().equals("-"))){
            JOptionPane.showMessageDialog(null, "Cor invalida, selecione uma cor valida.");
        }else{

            combobox_cor.addItem(cor);
            
            //ADD JLIST
             lista.addElement(cor);
            
            
            combobox_cor.setSelectedIndex(0);
            jComboBox_Cores.setSelectedIndex(0);
            Integer qtd_cor = combobox_cor.getItemCount()-1;//Diminuir 1 por causa do item 0 (TODOS)
            txt_qtd_cores.setText(qtd_cor.toString());

        }
    }//GEN-LAST:event_btn_adicionaActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        // TODO add your handling code here:
        remover_cor();
    }//GEN-LAST:event_btn_removeActionPerformed

    private void jRadioButtonAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAutomaticoActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonAutomatico.isSelected()){
            jPanel_Automatico.setVisible(true);
            txt_ref.requestFocus();
            //btn_Dividir_Automatico.requestFocus();
        }
        else{
            jPanel_Automatico.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButtonAutomaticoActionPerformed

    private void jRadioButtonAutomaticoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonAutomaticoStateChanged
        // TODO add your handling code here:
        if (jRadioButtonAutomatico.isSelected()){
            jPanel_Automatico.setVisible(true);
            //txt_ref.requestFocus();
            //btn_Dividir_Automatico.requestFocus();
        }
        else{
            jPanel_Automatico.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButtonAutomaticoStateChanged

    private void jRadioButtonGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonGradeActionPerformed
        // TODO add your handling code here:
        txt_ref.requestFocus();
        Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
        if (jRadioButtonGrade.isSelected() && qtd_tamanho==null){
            txt_ref.requestFocus();
        }

        if (jRadioButtonGrade.isSelected() && qtd_tamanho!=null){

            //se tamanho for especial, selecionar grade 3

            if (qtd_tamanho == 3){
                //adicionar opções de grade especial
                jPanel_Grade_Especial.setVisible(true);
                jPanel_Grade.setVisible(false);
                txt_ref.requestFocus();
                //jRadioButton111.requestFocus();

            }else if (qtd_tamanho == 4){

                //Opções grade normal
                jPanel_Grade.setVisible(true);
                jPanel_Grade_Especial.setVisible(false);
                txt_ref.requestFocus();
                //jRadioButton1111.requestFocus();
            }
            else{
                //mostrar nada / nenhum tamanho
            }
        }
        else{
            txt_ref.requestFocus();
            jPanel_Grade.setVisible(false);
            jPanel_Grade_Especial.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButtonGradeActionPerformed

    private void jRadioButtonGradeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonGradeStateChanged
        // TODO add your handling code here:

        Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
        if (jRadioButtonGrade.isSelected() && qtd_tamanho==null){

        }

        if (jRadioButtonGrade.isSelected() && qtd_tamanho!=null){

            //se tamanho for especial, selecionar grade 3

            if (qtd_tamanho == 3){
                //adicionar opções de grade especial
                jPanel_Grade_Especial.setVisible(true);
                jPanel_Grade.setVisible(false);

                //jRadioButton111.requestFocus();

            }else if (qtd_tamanho == 4){

                //Opções grade normal
                jPanel_Grade.setVisible(true);
                jPanel_Grade_Especial.setVisible(false);

                //jRadioButton1111.requestFocus();
            }
            else{
                //mostrar nada / nenhum tamanho
            }
        }
        else{

            jPanel_Grade.setVisible(false);
            jPanel_Grade_Especial.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButtonGradeStateChanged

    private void jRadioButtonManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonManualActionPerformed
        // TODO add your handling code here:
        if (jRadioButtonManual.isSelected()){
            jPanel_Manual.setVisible(true);
            txt_ref.requestFocus();

            //se for manual, desativa o campos qtd
            txt_qtd_total.setEnabled(false);

            //txt_t1_grade.requestFocus();
            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());

        }
        else{
            txt_qtd_total.setEnabled(true);
            jPanel_Manual.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButtonManualActionPerformed

    private void jRadioButtonManualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonManualStateChanged
        // TODO add your handling code here:
        if (jRadioButtonManual.isSelected()){
            jPanel_Manual.setVisible(true);

            //se for manual, desativa o campos qtd
            txt_qtd_total.setEnabled(false);

            //txt_t1_grade.requestFocus();
            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());

        }
        else{
            txt_qtd_total.setEnabled(true);
            jPanel_Manual.setVisible(false);
        }

    }//GEN-LAST:event_jRadioButtonManualStateChanged

    private void combobox_corKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_combobox_corKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER){
            if (txt_qtd_total.isEnabled()){
                txt_qtd_total.requestFocus();
            }else{
                txt_t1_grade.requestFocus();
            }
        }
        if(evt.getKeyCode() == evt.VK_F2){
            remover_cor();
        }

    }//GEN-LAST:event_combobox_corKeyPressed

    private void combobox_corComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_combobox_corComponentResized
        // TODO add your handling code here:

    }//GEN-LAST:event_combobox_corComponentResized

    private void combobox_corFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combobox_corFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox_corFocusGained

    private void combobox_corItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combobox_corItemStateChanged
        // TODO add your handling code here:
        Integer qtd_cor = combobox_cor.getItemCount()-1;//Diminuir 1 por causa do item 0 (TODOS)
        txt_qtd_cores.setText(qtd_cor.toString());

    }//GEN-LAST:event_combobox_corItemStateChanged

    private void txt_qtd_totalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_qtd_totalFocusLost
        // sair do campo qtd
        /*
        if ( (txt_qtd_total.equals("")) || (txt_qtd_total.getText() == null)|| (txt_qtd_total.getText().trim().equals("")) ){
            //se não for digitado nenhuma qtd
        }
        else{//se foi informado

            //Selecionar Manual automatico caso Tamanho ou Cor for menor que qtd total

            Integer qtd_cor = Integer.parseInt(txt_qtd_cores.getText());
            Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());

            Integer qtd_total = Integer.parseInt(txt_qtd_total.getText());
            if (qtd_total<qtd_tamanho || qtd_total<qtd_cor){
                jRadioButtonManual.setSelected(true);
            }
        }//fim foi informado qtd
        */
    }//GEN-LAST:event_txt_qtd_totalFocusLost

    private void txt_refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_refActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_refActionPerformed

    private void txt_refFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_refFocusLost

        if (!txt_ref.getText().equals("")){
            
            
            
            
            //adicionando itens combobox
            //está usando o like
            ItemPedidoDAO dao = new ItemPedidoDAO();
            combobox_cor.removeAllItems();
            //pegando dados da referencia inserida

            for (ProdutoDTO p : dao.getProdutosRef(txt_ref.getText())) {

                //adicionando cores na combobox
                combobox_cor.addItem("TODAS");
                lista.clear();
                ProdutoCoresDAO daoP = new ProdutoCoresDAO();
                for (CoresDTO cores : daoP.ListarProdutoCores(txt_ref.getText())) {
                    //jList1.(cores.getCor()+"\n");

                    jList1.setModel(lista);
                    jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

                    lista.addElement(cores.getCor());
                    

                }

                //passando codigo e combobox para add.
                tdgCTR.cores_ref(txt_ref.getText(), combobox_cor);

                
                //pegou as cores, agora pegar qtd de cores adicionadas na ref e comparar com as cores para ignorar.
                excluir_cor_do_pedido();
                    
                
                
                txt_Tipo_tamanho.setText(p.getTipo_tamanho());
                Integer qtd_cores = combobox_cor.getItemCount()-1;
                txt_qtd_cores.setText((qtd_cores.toString()));
                String tipo = txt_Tipo_tamanho.getText();

                System.err.println("Tipo tamanho: "+tipo);

                switch (tipo) {
                    case "ESPECIAL":
                    {
                        txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());

                        //se for especial, desativar t4
                        txt_t4_grade.setEnabled(false);
                        txt_t4_grade.setText("0");

                        //selecionar grade 1 1 1
                        break;
                    }
                    case "JUVENIL":
                    {
                        System.out.println("Entrou no juvenil");
                        txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());

                        txt_t4_grade.setEnabled(true);
                        //selecionar grade 1 1 1 1
                        break;
                    }
                    default:{
                        txt_qtd_tamanho.setText(p.getQtd_tamanho().toString());
                        System.out.println("Entrou no default(Normal)");
                        txt_t4_grade.setEnabled(true);
                        //selecionar grade 1 2 2 1
                    }

                }

                //Sumir / Aparecer Dividir por grade normal / especial

                if (jRadioButtonGrade.isSelected()){

                    //se tamanho for especial, selecionar grade 3
                    Integer qtd_tamanho = Integer.parseInt(txt_qtd_tamanho.getText());
                    if (qtd_tamanho == 3){
                        //adicionar opções de grade especial
                        jPanel_Grade_Especial.setVisible(true);
                        jPanel_Grade.setVisible(false);

                    }else{

                        //Opções grade normal
                        jPanel_Grade.setVisible(true);
                        jPanel_Grade_Especial.setVisible(false);

                    }
                }
                else{
                    jPanel_Grade.setVisible(false);
                    jPanel_Grade_Especial.setVisible(false);
                }

                //fim aparecer / desaparecer grade normal / especial

            }//se codigo ref invalida

            if (combobox_cor.getSelectedItem()==null){
                //JOptionPane.showMessageDialog(null, "REF "+txt_ref.getText()+" invalido!");
                txt_ref.setText("");
                txt_ref.requestFocus();
            }

        }else{
            txt_ref.requestFocus();
        }//fim SE ref for ""

    }//GEN-LAST:event_txt_refFocusLost

    private void excluir_cor_do_pedido(){
        
        
        if (jComboBox_excluir_cor.getItemCount()==0){
            
        }else{
        
        for (int i = 0; i <= jComboBox_excluir_cor.getItemCount(); i++) {
                    //pegando qtd de cores para excluir
                    //System.err.println("combbobox_cor count: "+combobox_cor.getItemCount());
                    //System.err.println("Index inicial da cor ref: "+combobox_cor.getSelectedIndex());
                    for (int x = 1; x < combobox_cor.getItemCount(); x++) {
                        //System.err.println("excluir cor count: "+jComboBox_excluir_cor.getItemCount());
                        //System.err.println("Index inicial da cor excluir: "+jComboBox_excluir_cor.getSelectedIndex());
                        //se cor da ref for igual a das adicionadas para excluir    
                        //System.out.println("final: "+combobox_cor.getItemAt(i)+" + "+jComboBox_excluir_cor.getItemAt(x));
                        if (combobox_cor.getItemAt(x).equals(jComboBox_excluir_cor.getItemAt(i))){
                                
                            combobox_cor.removeItemAt(x);//remove a cor na posicao x
                            
                            /* da pau no sortido as vezes.
                            if (lista.isEmpty()){
                                
                            }else{
                            
                            lista.removeElementAt(i);
                            
                            }
                            //x++;
                            */
                        }else{
                            //nada
                        }
                        
                    }
                    
                }
        }
    }
    
    private void txt_n_pedidoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_n_pedidoFocusLost
        // TODO add your handling code here:
        //verificar se o Pedido X já existe
        String n_pedido = txt_n_pedido.getText();

        if (existe_pedido(n_pedido)){ //se existir = listar
            listar_item_pedido();
            calc_total_itens();
        }else{
            //não existe pedido
            calc_total_itens();
        }
    }//GEN-LAST:event_txt_n_pedidoFocusLost

    private void txt_qtd_coresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qtd_coresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qtd_coresActionPerformed

    private void btn_add_cor_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_cor_excluirActionPerformed
        // TODO add your handling code here:
        String cor;
        cor = jComboBox_todas_cores.getSelectedItem().toString();

        if((jComboBox_todas_cores.getSelectedItem().equals("-"))){
            JOptionPane.showMessageDialog(null, "Cor invalida, selecione uma cor valida.");
        }else{

            jComboBox_excluir_cor.addItem(cor);
            
            
            jComboBox_todas_cores.setSelectedIndex(0);
            

        }
    }//GEN-LAST:event_btn_add_cor_excluirActionPerformed

    private void btn_remove_cor_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remove_cor_excluirActionPerformed
        // TODO add your handling code here:
        
        if (jComboBox_excluir_cor.getItemCount()==0){
            
        }else{
            
            if((jComboBox_excluir_cor.getSelectedItem().equals("TODAS"))){
            JOptionPane.showMessageDialog(null, "A opção 'TODAS' não pode ser removida.");
            }else{

                String cor;
                cor = jComboBox_excluir_cor.getSelectedItem().toString();
                jComboBox_excluir_cor.removeItem(cor);

            }
            
            
        }
        
        
        
        
    }//GEN-LAST:event_btn_remove_cor_excluirActionPerformed
    //adiciona itens no banco e na jList passando Y(cor que está) e qtd de cada tamanho
    public void copia_itens_pedido(){
        // instanciando a classe Usuario do pacote modelo e criando seu objeto usuarios
            
        ItemPedidoDTO itempedido = new ItemPedidoDTO();
        itempedido.setN_pedido_original(Integer.parseInt(txt_n_pedido_original.getText()));
        itempedido.setN_pedido_novo(Integer.parseInt(txt_n_pedido_novo.getText()));
        itempedido.setLoja_original(txt_loja_original.getText());
        itempedido.setLoja_novo(txt_loja_novo.getText());
        
        // instanciando a classe ProdutoDAO do pacote dao e criando seu objeto dao

            ItemPedidoDAO dao = new ItemPedidoDAO();
            if (dao.copiaItemPedido(itempedido)){
                JOptionPane.showMessageDialog(null, "Itens do pedido: "+txt_n_pedido_original.getText()+" \nforam copiados para: "+txt_n_pedido_novo.getText()+".");         
            }
            else {
                JOptionPane.showMessageDialog(null, "Erro, pedido não foi copiado!");
            }
            
        //txt_ref.requestFocus();//após executar ação de inserir, focar no campo ref
    }
    
    
    
    public void Refs_Categoria(){
        ItemPedidoDAO dao = new ItemPedidoDAO();
        //zerando campos
        jComboBox_refs.removeAllItems();
        txt_qtd_ref.setText(null);
        txt_qtd_total_sortido.setText(null);
        txt_Tipo_tamanho_categoria.setText(null);
        
        //pegando dados da categoria selecionada
        jComboBox_refs.addItem("TODAS");
        
        //trazer só as ref da categoria que estão disponiveis(positivo)
        if (jCheckBox_disponivel.isSelected()){
            for (ItemPedidoDTO p : dao.getRefs_Categoria_Disponivel(jComboBox_Categoria.getSelectedItem().toString(), txt_dias.getText(), txt_saldo.getText())) {
                //se codigo nao for P001, adiciona.
                if (!"P001".equals(p.getCodigo())){
                    jComboBox_refs.addItem(p.getCodigo());
                    txt_Tipo_tamanho_categoria.setText(p.getTipo_tamanho());
                } 
                    
            }
        }
        else{//todas as ref da categoria
                for (ItemPedidoDTO p : dao.getRefs_Categoria(jComboBox_Categoria.getSelectedItem().toString())) {
                    //se codigo nao for P001, adiciona.
                    if (!"P001".equals(p.getCodigo())){
                        jComboBox_refs.addItem(p.getCodigo());
                        txt_Tipo_tamanho_categoria.setText(p.getTipo_tamanho());
                    } 
                }
        }
        
        
        Integer qtd_ref = jComboBox_refs.getItemCount()-1;//Diminuir 1 por causa do item 0 (TODOS)
        txt_qtd_ref.setText(qtd_ref.toString());
        
        
    }
    

    
    /*
    public void excluir_item(){
                ItemPedidoDTO itempedido = new ItemPedidoDTO();
                
                Integer linhaSelecionada = -1;
		linhaSelecionada = jTable1.getSelectedRow();
                
            if (linhaSelecionada >= 0) {//se linha selecionada
                
                    Integer id = (Integer) jTable1.getValueAt(linhaSelecionada, 0);
                    String codigo = (String) jTable1.getValueAt(linhaSelecionada, 1);
                    
                    //Confirmação
                    String message = "Deseja realmente excluir a REF: "+codigo+" ?";
                    String title = "Confirmação";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {//sim
                            //se confirmou
                            DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                            itempedido.setId(id);//setar o id pra excluir
                            itempedido.setCodigo(codigo);
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            if (dao.excluir(itempedido)){
                                
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Erro, a REF "+codigo+" não foi excluido!");
                            }
                            model.removeRow(linhaSelecionada);
                    }else{
                        //não confirmou
                    }
                 
            }
            else{ //se linha não estiver selecionada
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");
            }   // fim linha selecionada
       
    }//fim excluir
 */
    
    
    public void excluir_item(){
                ItemPedidoDTO itempedido = new ItemPedidoDTO();
                
                Integer linhaSelecionada = -1;
		linhaSelecionada = jTable1.getSelectedRow();
                
            if (linhaSelecionada >= 0) {//se linha selecionada
                
                int[] itens_selecionados = jTable1.getSelectedRows();
                Integer id = null;
                String codigo = null;
                
                
                    
                    //Confirmação
                    String message = "Deseja realmente excluir refs selecionadas ?";
                    String title = "Confirmação";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {//sim
                            //se confirmou
                        
                        for (int i = 0; i < jTable1.getSelectedRowCount(); i++) {
                            
                            id = (Integer) jTable1.getValueAt(itens_selecionados[i], 0);
                            codigo = (String) jTable1.getValueAt(itens_selecionados[i], 1);
                            
                            itempedido.setId(id);//setar o id pra excluir
                            itempedido.setCodigo(codigo);
                            ItemPedidoDAO dao = new ItemPedidoDAO();
                            if (dao.excluir(itempedido)){
                                
                                
                                itens_selecionados = jTable1.getSelectedRows();
                                
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Erro, a REF "+codigo+" não foi excluido!");
                            }
                        }
                        
                        listar_item_pedido();
                        calc_total_itens();
                        
                        
                    }else{
                        //não confirmou
                    }
                 
            }
            else{ //se linha não estiver selecionada
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");
            }   // fim linha selecionada
       
    }//fim excluir 2
    
    
    public void listar_item_pedido(){
        if ( (txt_n_pedido.getText() == null)  || (txt_n_pedido.getText().equals("")) ){
            JOptionPane.showMessageDialog(null, "Insira o Nº Pedido");
            txt_n_pedido.requestFocus();
        }
        
        ItemPedidoDAO dao = new ItemPedidoDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
       //model.addTableModelListener(jTable1);
       //jTable1.tableChanged(null);
       
       
       model.setNumRows(0);
       
        for (ItemPedidoDTO ip : dao.getItemPedido(txt_n_pedido.getText())) {
           
            model.addRow(new Object[]{ip.getId(), ip.getCodigo(), ip.getCor(), ip.getT1(), ip.getT2(), ip.getT3(), ip.getT4(), ip.getCor_original(), ip.getTotal(), ip.getLoja()});
        }
        //fim listar
    }
    
    //adiciona itens no banco e na jList passando Y(cor que está) e qtd de cada tamanho
    public void adiciona_item(int y, int t1, int t2, int t3, int t4){
        // instanciando a classe Usuario do pacote modelo e criando seu objeto usuarios
            
        ItemPedidoDTO itempedido = new ItemPedidoDTO();
        itempedido.setN_pedido(Integer.parseInt(txt_n_pedido.getText()));
        itempedido.setCodigo(txt_ref.getText());
        itempedido.setLoja(txt_loja.getText());
        /*
        if  ((jToggleButton_COR.isSelected())){
            if((combobox_cor.getSelectedItem()=="TODAS")){
                itempedido.setCor(combobox_cor.getItemAt(y).toString());
            }else{
                itempedido.setCor(combobox_cor.getSelectedItem().toString());
            }
            
        }else{
            itempedido.setCor(combobox_cor.getItemAt(y).toString());
        }*/
        itempedido.setCor(combobox_cor.getItemAt(y).toString());
        
        itempedido.setT1(t1);
        itempedido.setT2(t2);
        itempedido.setT3(t3);
        itempedido.setT4(t4);
        itempedido.setCor_original(combobox_cor.getSelectedItem().toString());
        int total = t1+t2+t3+t4;
        itempedido.setTotal(total);
       

        

            // instanciando a classe ProdutoDAO do pacote dao e criando seu objeto dao

            ItemPedidoDAO dao = new ItemPedidoDAO();
            if (dao.adicionaItemPedido(itempedido)){
                
                //listando item pedido adicionado
                
                     DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
                     model.addRow(new Object[]{itempedido.getId(),itempedido.getCodigo(), itempedido.getCor(), itempedido.getT1(), itempedido.getT2(), itempedido.getT3(), itempedido.getT4(), itempedido.getCor_original(), itempedido.getTotal(), itempedido.getLoja()});
                     
                     calc_total_itens();
                        
            }
            else {
                JOptionPane.showMessageDialog(null, "Erro, o item de ref: "+txt_ref.getText()+" não foi cadastrado! ");
            }
            
        txt_ref.requestFocus();//após executar ação de inserir, focar no campo ref
    }
    
        //verificar se o Pedido X já existe
    public boolean existe_pedido(String n_pedido){
         
        PedidoDAO dao = new PedidoDAO();
        try {
            if (dao.temPedido(n_pedido)){
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null, "Nº Pedido não existe");
                txt_n_pedido.setText(n_pedido);
                txt_n_pedido.requestFocus();
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao verificar");
            System.err.println(ex.getMessage());
        }
        
        return false;
    }
    
    public boolean validar_campos(){
        
        
       
                if ((txt_n_pedido.getText().isEmpty()) || (txt_ref.getText().isEmpty()) || (txt_qtd_total.getText().isEmpty() || (combobox_cor.getSelectedItem().toString().isEmpty()) )) {
                    JOptionPane.showMessageDialog(null, "Complete todos os campos.");
                    return false;
                }
                else {
                    return true;
                }
        
      
        
    }
 public void calc_total_itens(){   
    //total itens
                tdgCTR geral = new tdgCTR();
                txt_total_itens.setText(geral.calc_Total_Itens(jTable1, 8)); 
}                
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItemPedidoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemPedidoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemPedidoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemPedidoVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //setar Botão padrao o que esteja com foco.
                //UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
                
                new ItemPedidoVIEW().setVisible(true);
                
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Dividir_Automatico;
    private javax.swing.JButton btn_Dividir_Grade;
    private javax.swing.JButton btn_Dividir_Grade_Especial;
    private javax.swing.JButton btn_Dividir_Manual;
    private javax.swing.JButton btn_Dividir_Sortido_Especial;
    private javax.swing.JButton btn_Dividir_Sortido_Juvenil;
    private javax.swing.JButton btn_Dividir_Sortido_Manual;
    private javax.swing.JButton btn_Dividir_Sortido_Normal;
    private javax.swing.JButton btn_Listar;
    private javax.swing.JButton btn_add_cor_excluir;
    private javax.swing.JButton btn_adiciona;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_remove_cor_excluir;
    private javax.swing.ButtonGroup buttonGroupGRADE;
    private javax.swing.ButtonGroup buttonGroupTipoDivisao;
    private javax.swing.ButtonGroup buttonGroup_Sortido;
    private javax.swing.JComboBox combobox_cor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox_disponivel;
    private javax.swing.JCheckBox jCheckBox_loja;
    private javax.swing.JCheckBox jCheckBox_manter;
    private javax.swing.JCheckBox jCheckBox_sortido_maximo;
    private javax.swing.JComboBox jComboBox_Categoria;
    private javax.swing.JComboBox jComboBox_Cores;
    private javax.swing.JComboBox jComboBox_excluir_cor;
    private javax.swing.JComboBox jComboBox_refs;
    private javax.swing.JComboBox jComboBox_todas_cores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelT1;
    private javax.swing.JLabel jLabelT2;
    private javax.swing.JLabel jLabelT3;
    private javax.swing.JLabel jLabelT4;
    private javax.swing.JLabel jLabelT5;
    private javax.swing.JLabel jLabelT6;
    private javax.swing.JLabel jLabelT7;
    private javax.swing.JLabel jLabelT8;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_Automatico;
    private javax.swing.JPanel jPanel_Grade;
    private javax.swing.JPanel jPanel_Grade_Especial;
    private javax.swing.JPanel jPanel_Manual;
    private javax.swing.JPanel jPanel_Manual1;
    private javax.swing.JPanel jPanel_SORTIDO;
    private javax.swing.JRadioButton jRadioButton111;
    private javax.swing.JRadioButton jRadioButton1111;
    private javax.swing.JRadioButton jRadioButton112;
    private javax.swing.JRadioButton jRadioButton1122;
    private javax.swing.JRadioButton jRadioButton121;
    private javax.swing.JRadioButton jRadioButton122;
    private javax.swing.JRadioButton jRadioButton1221;
    private javax.swing.JRadioButton jRadioButton211;
    private javax.swing.JRadioButton jRadioButton2112;
    private javax.swing.JRadioButton jRadioButton221;
    private javax.swing.JRadioButton jRadioButton2211;
    private javax.swing.JRadioButton jRadioButtonAutomatico;
    private javax.swing.JRadioButton jRadioButtonGrade;
    private javax.swing.JRadioButton jRadioButtonManual;
    private javax.swing.JRadioButton jRadioButton_Sortido_Automatico;
    private javax.swing.JRadioButton jRadioButton_Sortido_Manual;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_Tipo_tamanho;
    private javax.swing.JTextField txt_Tipo_tamanho_categoria;
    private javax.swing.JTextField txt_dias;
    private javax.swing.JTextField txt_loja;
    private javax.swing.JTextField txt_loja_novo;
    private javax.swing.JTextField txt_loja_original;
    private javax.swing.JTextField txt_n_pedido;
    private javax.swing.JTextField txt_n_pedido_novo;
    private javax.swing.JTextField txt_n_pedido_original;
    private javax.swing.JTextField txt_qtd_cores;
    private javax.swing.JTextField txt_qtd_ref;
    private javax.swing.JTextField txt_qtd_tamanho;
    private javax.swing.JTextField txt_qtd_total;
    private javax.swing.JTextField txt_qtd_total_sortido;
    private javax.swing.JTextField txt_ref;
    private javax.swing.JTextField txt_saldo;
    private javax.swing.JTextField txt_t1_grade;
    private javax.swing.JTextField txt_t1_grade_sortido;
    private javax.swing.JTextField txt_t2_grade;
    private javax.swing.JTextField txt_t2_grade_sortido;
    private javax.swing.JTextField txt_t3_grade;
    private javax.swing.JTextField txt_t3_grade_sortido;
    private javax.swing.JTextField txt_t4_grade;
    private javax.swing.JTextField txt_t4_grade_sortido;
    private javax.swing.JTextField txt_total_itens;
    // End of variables declaration//GEN-END:variables
}
