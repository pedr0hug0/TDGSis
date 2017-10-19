/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CTR.printData;
import CTR.tdgCTR;
import DTO.CorteDTO;
import DTO.PedidoDTO;
import DTO.ProducaoDTO;
import dao.CorteDAO;
import dao.PedidoDAO;
import dao.ProducaoDAO;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author PeDr0_HuG0
 */
public class MalhaConsumoPedidosVIEW extends javax.swing.JFrame {

    /**
     * Creates new form ProducaoVIEW
     */
    public MalhaConsumoPedidosVIEW() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(ProdutoVIEW.HIDE_ON_CLOSE);
        
       tdgCTR.todasDescricao(jComboBox_descricao);
       
       consumo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToggleButton_Selecionar = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        txt_malha_kg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox_descricao = new javax.swing.JComboBox();
        btn_add = new javax.swing.JButton();
        jComboBox_categoria = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btn_remove = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Consumo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CONSUMO MALHA PEDIDOS");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("PROCESSOS CONTABILIZADOS NO CONSUMO"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CALCULAR", "CORTE", "REF", "COR", "QTD", "CONSUMO", "PROGRAMADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        //COMBOBOX STATUS
        TableColumn programadoCol = jTable1.getColumnModel().getColumn(7);

        JComboBox comboBox_programado = new JComboBox();

        comboBox_programado.addItem("SIM");
        comboBox_programado.addItem("NAO");
        programadoCol.setCellEditor(new DefaultCellEditor(comboBox_programado));
        //FIM COMBOBOX PROGRAMADO

        //ocultar coluna ID da tabela
        jTable1.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        //fim ocultar
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setMinWidth(85);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(85);
        }

        jToggleButton_Selecionar.setText("Selecionar Todos");
        jToggleButton_Selecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton_SelecionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jToggleButton_Selecionar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToggleButton_Selecionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToggleButton_Selecionar.setSelected(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Funções"));

        txt_malha_kg.setText("19");

        jLabel1.setText("Malha Kg:");

        jButton1.setText("Imprimir Tabela");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Imprimir Consumo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox_descricao.setMaximumRowCount(15);
        jComboBox_descricao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        jComboBox_descricao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_descricaoItemStateChanged(evt);
            }
        });
        jComboBox_descricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox_descricaoKeyPressed(evt);
            }
        });

        btn_add.setText("+");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        jComboBox_categoria.setMaximumRowCount(15);

        jLabel2.setText("Titulos para imprimir:");

        btn_remove.setText("-");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jComboBox_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_malha_kg)
                .addGap(1, 1, 1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_remove))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add)
                    .addComponent(jLabel1)
                    .addComponent(txt_malha_kg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_remove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        jButton1.setVisible(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("CONSUMO MALHA"));

        jTable_Consumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COR", "QTD", "MALHA_KG", "PEÇA_MALHA"
            }
        ));
        jScrollPane2.setViewportView(jTable_Consumo);
        if (jTable_Consumo.getColumnModel().getColumnCount() > 0) {
            jTable_Consumo.getColumnModel().getColumn(1).setMinWidth(50);
            jTable_Consumo.getColumnModel().getColumn(1).setMaxWidth(50);
        }
        //ordenando cliacando nas tabelas
        jTable_Consumo.setAutoCreateRowSorter(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
     
     
    
    public void setar_programado(){
        CorteDTO cortes = new CorteDTO();
        //Confirmação
                                String message = "Deseja programar todas ref selelcionadas?";
                                String title = "Setar como programado";
                                int resposta = JOptionPane.showConfirmDialog(null, message);
                                tdgCTR geral = new tdgCTR();
                                System.err.println("Respota:"+resposta);
                                if ((resposta == 1) || (resposta == 2)) 
                                {
                                    JOptionPane.showMessageDialog(null, "REF não foi programada.");
                                }else{//sim(
                                           
                                    Boolean calcular; 
                                    Integer id;
                                    String programado;
                                    
                                    for(int i=0;i<jTable1.getRowCount();i++){ 
                                        calcular = (Boolean) jTable1.getValueAt(i, 1);
                                        
                                        //SE ESTIVER SELECIONADO
                                        if (calcular == true){
                                            //ref = (String) jTable1.getValueAt(i,2);
                                            id = (Integer) jTable1.getValueAt(i, 0);
                                            //programado = (String) jTable1.getValueAt(i, 7);
                                            programado = "SIM";
                                            
                                            DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                                            cortes.setId(id);
                                            cortes.setProgramado(programado);

                                            
                                            CorteDAO dao = new CorteDAO();
                                            if (dao.atualizarProgramado(cortes)){
                                                System.out.println("atualizado");
                                                
                                                
                                            }
                                            else {
                                                System.out.println("Não atualizou");
                                            }
                                
                                        }//fim selecionado
        
                                    }//fim row count
                                    
                                    listar_programacao();
                                }//fim confirmar Programacao
    }//fim metodo
    
    
    public void calcular_consumo(){
        //limpando tabela cosumo
        DefaultTableModel model =  (DefaultTableModel) jTable_Consumo.getModel(); 
        model.setNumRows(0);
        Integer malha_kg = Integer.parseInt(txt_malha_kg.getText());
        Double psoma = 0.0;
        int qtdsoma = 0;
        double qtd_peca = 0.0;
        Boolean cor_existe = false;
        
        
        for (int i=0; i< jTable1.getRowCount();i++){
            
            psoma = 0.0;
            qtdsoma = 0;
            cor_existe = false;
            if(jTable1.getValueAt(i, 1).toString().equals("true")){//se está selecionado
                for (int z=0;z<jTable_Consumo.getRowCount();z++){//percorrer cores ja adicionadas na jconsumo
                    if(jTable1.getValueAt(i, 4).equals(jTable_Consumo.getValueAt(z, 0))){//se a cor existir, seta cor_existe = verdadeiro
                        cor_existe=true;
                    }
                }
                if (!cor_existe){
                    for (int x=0; x<jTable1.getRowCount();x++){
                    if((jTable1.getValueAt(x, 1).toString().equals("true")) && (jTable1.getValueAt(i, 4).equals(jTable1.getValueAt(x, 4)))){                        
                        psoma = psoma + Double.parseDouble(jTable1.getValueAt(x, 6).toString());
                        qtdsoma = qtdsoma + Integer.parseInt(jTable1.getValueAt(x, 5).toString());
                        qtd_peca = Math.ceil(psoma/malha_kg);
                    }
                    
                }
                    DecimalFormat formato = new DecimalFormat("#.##");      
                    
                    model.addRow(new Object[]{jTable1.getValueAt(i, 4), qtdsoma, (formato.format(psoma)),qtd_peca});
                }
            }//fim está selecionado
        }
    }
    
    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
        //if (jTable1.editCellAt(jTable1.getSelectedRow(), 1)){
        
        
        CorteDTO cortes = new CorteDTO();
        
        calcular_consumo();
        if ("tableCellEditor".equals(evt.getPropertyName()))
		{
			if (jTable1.isEditing()){
                        }else{
                            Integer linhaSelecionada = -1;
                            linhaSelecionada = jTable1.getSelectedRow();
                            
                            if (linhaSelecionada >= 0) {
                                
                                
                                            Integer id = (Integer) jTable1.getValueAt(linhaSelecionada, 0);
                                            String programado = (String) jTable1.getValueAt(linhaSelecionada, 7);



                                            DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                                            cortes.setId(id);//setar o id pra excluir
                                            cortes.setProgramado(programado);
                                            
                                            
                                        CorteDAO dao = new CorteDAO();
                                        if (dao.atualizarProgramado(cortes)){
                                            System.out.println("atualizado");
                                            //listar?
                                        }
                                        else {
                                            System.out.println("Não atualizou");
                                        }
                                            
                                    
                                        
                            }else{
                                JOptionPane.showMessageDialog(null, "Selecione uma linha!");
                            }
                        }    
		//}
        }
    }//GEN-LAST:event_jTable1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         printData imprimir = new printData();
         imprimir.ImprimirJTable(jTable_Consumo);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jToggleButton_SelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton_SelecionarActionPerformed
        // TODO add your handling code here:
        if (jToggleButton_Selecionar.isSelected()){
            //Selecionar
            for(int i=0;i<jTable1.getRowCount();i++){ 
            jTable1.setValueAt(true, i, 1);
            }
        }else{//Deselecionar
            for(int i=0;i<jTable1.getRowCount();i++){ 
            jTable1.setValueAt(false, i, 1);
                                            
            }
        }
        
        calcular_consumo();
        
    }//GEN-LAST:event_jToggleButton_SelecionarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            imprimir();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        } catch (JRException ex) {
            Logger.getLogger(MalhaConsumoPedidosVIEW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public void consumo(){
        DefaultTableModel model =  (DefaultTableModel) jTable_Consumo.getModel();
        model.setNumRows(0);
        
        listar_programacao();

        calcular_consumo();
        
        //auto ordenando campo cor 
        RowSorter sorter = jTable_Consumo.getRowSorter();
        sorter.setSortKeys(Arrays.asList(new SortKey(0, SortOrder.ASCENDING)));
    }
    
    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        // TODO add your handling code here:

        if(jComboBox_categoria.getItemCount()==0){
            JOptionPane.showMessageDialog(null, "Categoria invalida.");
        }else{
            String categoria;
            categoria = jComboBox_categoria.getSelectedItem().toString();
            //adiciona item
            jComboBox_descricao.addItem(categoria);
            //remove item original
            jComboBox_categoria.removeItemAt(jComboBox_categoria.getSelectedIndex());
        }
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:

        String descricao;
        descricao = jComboBox_descricao.getSelectedItem().toString();

        if((jComboBox_descricao.getSelectedItem().equals("-"))){
            JOptionPane.showMessageDialog(null, "Categoria invalida.");
        }else{

            //adiciona item
            jComboBox_categoria.addItem(descricao);
            //remove item original
            jComboBox_descricao.removeItemAt(jComboBox_descricao.getSelectedIndex());

            jComboBox_categoria.setSelectedIndex(jComboBox_categoria.getItemCount()-1);
        }

    }//GEN-LAST:event_btn_addActionPerformed

    private void jComboBox_descricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_descricaoKeyPressed
        // TODO add your handling code here:
        /*
        if(evt.getKeyCode() == evt.VK_ENTER){

            jCheckBox_Bordado.requestFocus();

        }
        */
    }//GEN-LAST:event_jComboBox_descricaoKeyPressed

    private void jComboBox_descricaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_descricaoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_descricaoItemStateChanged
   
    //listar programacao
     public void listar_programacao(){
        jTable1.setRowSorter(null);
        
        
        
         PedidoDAO dao = new PedidoDAO();
        DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
        model.setNumRows(0);
        
        
            
            
            //SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
            for (PedidoDTO c : dao.getConsumoPedidos("%",PedidosVIEW.todos_pedidos_selecionados)) {

                model.addRow(new Object[]{null, new Boolean(true), c.getDescricao(), c.getCodigo(), c.getCor(), c.getQtd(), c.getConsumo(), null});
            }
        
        
        
        //adicionar rowsorter 
        jTable1.setAutoCreateRowSorter(true);
        
    }//fim listar programacao2
    
    
    
    
        //passando valores para  um relatorio ireport sem conexao no banco e pegando valores da jtable
        //metodo chamado em um jbutton
        public void imprimir() throws JRException{
                String arquivo = "relatorios_ireport/consumo_malha.jasper"; //arquivo de relatorio
                java.io.InputStream file = getClass().getClassLoader().getResourceAsStream(arquivo); //carrego o arquivo
                
                RowSorter sorter = jTable_Consumo.getRowSorter();
                sorter.setSortKeys(Arrays.asList(new SortKey(0, SortOrder.ASCENDING)));
                
                JRTableModelDataSource tabela = new JRTableModelDataSource(jTable_Consumo.getModel()); //aqui eu crio um datasource usando a propria jtable
                
                Map parametros = new HashMap(); //parametros sao as colunas da jtable ("fields" com o nome de COLUMN_0, COLUMN_1 )
                
                String tipo = "";
                Integer qtd_filtros = jComboBox_categoria.getItemCount();
                for (int i = 0; i < qtd_filtros; i++) {
                    //se não for ultimo i (se for remover o -)
                    if (i != qtd_filtros-1){
                    tipo = tipo+jComboBox_categoria.getItemAt(i).toString().substring(2)+" -";
                    }else{
                        tipo = tipo+jComboBox_categoria.getItemAt(i).toString().substring(2);
                    }
                }
                
                /*
                if (jRadioButton_careca_v.isSelected()){
                    tipo = "CARECA / V RIBANNA";
                }else if (jRadioButton_polo.isSelected()){
                    tipo = "POLO";
                }else if (jRadioButton_piquet.isSelected()){
                    tipo = "PIQUET";
                }else if (jRadioButton_especial.isSelected()){
                    tipo = "PRODUTOS ESPECIAIS";
                }else if (jRadioButton_todos.isSelected()){
                    tipo = "TODOS";
                }
                */
                parametros.put("tipo", tipo);
                JasperPrint printer = JasperFillManager.fillReport(file, parametros, tabela); //aqui eu passo a jtable(tabela) para o relatorio
                JRViewer view = new JRViewer(printer); //preview
                JDialog dialog = new JDialog(); //jdialog que contem o preview
                dialog.getContentPane().add(view); //adiciono o preview
                dialog.setSize(850,600);
                dialog.setLocationRelativeTo(this);
                dialog.setModalityType(ModalityType.APPLICATION_MODAL);
                dialog.setModal(true);
                dialog.setVisible(true);
                 /*
                Agora é só ir até o model do relatório no meu caso é "EmitirComanda.jrxml"
                e criar os "fields" com o nome de COLUMN_0, COLUMN_1 etc..etc..
                Cada field ira receber os dados da jtable recebida conforme sua coluna. 
                No modelo EmitirComanda.jrxml va ate suas propriedades e mude a opcao:
                "When No Data" para "No Data Section", 
                isso dira para seu .jrxml q ele naum ira receber uma conexao SQL....
                depois desses passos, basta compilar o relatorio e a aplicacao e rodar 
                para ver a magia acontecer.
                */
        }//fim imprimir

       


   
    /*
    //listar
     public void listar_programacao(){
        jTable1.setRowSorter(null);
        String tipo_produto;
        if (jRadioButton_careca_v.isSelected()){
                tipo_produto = "CARECA_E_V";
            }else if (jRadioButton_polo.isSelected()){
                tipo_produto = "POLO";
            }else if (jRadioButton_piquet.isSelected()){
                tipo_produto = "PIQUET";
            }else if (jRadioButton_especial.isSelected()){
                tipo_produto = "ESPECIAL";
            }else{//todos
                tipo_produto = "TODOS";
        }    
        
        String programado = jComboBox_Programado.getSelectedItem().toString();
        if (programado == "TODOS"){
            programado = "%";
        }
        String codigo = txt_ref_pesquisar.getText();
        if (codigo.equals("") || codigo.equals(null)){
            codigo = "%";
        }
        
        
         CorteDAO dao = new CorteDAO();
         DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       //SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        for (CorteDTO c : dao.getProgramacao(programado, tipo_produto, codigo)) {

            model.addRow(new Object[]{c.getId(), new Boolean(true), c.getCorte(), c.getCodigo(), c.getCor(), c.getQtd(), c.getConsumo(), c.getProgramado()});
        }
       //adicionar rowsorter 
       jTable1.setAutoCreateRowSorter(true);
    }//fim listar producao
    */
    
  

     
     
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
            java.util.logging.Logger.getLogger(MalhaConsumoPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MalhaConsumoPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MalhaConsumoPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MalhaConsumoPedidosVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MalhaConsumoPedidosVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_remove;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox_categoria;
    private javax.swing.JComboBox jComboBox_descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_Consumo;
    private javax.swing.JToggleButton jToggleButton_Selecionar;
    private javax.swing.JTextField txt_malha_kg;
    // End of variables declaration//GEN-END:variables
}
