/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package VIEW;

import CTR.tdgCTR;
import DTO.ClienteDTO;
import dao.ClienteDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PeDr0_HuG0
 */
public class ClienteVIEW extends javax.swing.JFrame {

    /**
     * Creates new form ClienteVIEW
     */
    public ClienteVIEW() {
        initComponents();
        //botao x fechar apenas janela
        setDefaultCloseOperation(ProdutoVIEW.HIDE_ON_CLOSE);
        
        tdgCTR.considerarEnterComoTab(txt_codigo);
        tdgCTR.considerarEnterComoTab(txt_razao);
        tdgCTR.considerarEnterComoTab(txt_fantasia);
        tdgCTR.considerarEnterComoTab(txt_razao_pesquisar);
        tdgCTR.considerarEnterComoTab(txt_codigo_pesquisar);
        
        ocultar_coluna_tabela();
        
        txt_codigo.requestFocus();
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
        txt_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_razao = new javax.swing.JTextField();
        txt_fantasia = new javax.swing.JTextField();
        btn_Cadastrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_codigo_pesquisar = new javax.swing.JTextField();
        btn_Pesquisar = new javax.swing.JButton();
        btn_Excluir = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JButton();
        btn_Limpar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_razao_pesquisar = new javax.swing.JTextField();
        btn_Pesquisar_razao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro Cliente"));

        jLabel1.setText("Codigo Cliente:");

        txt_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_codigoFocusLost(evt);
            }
        });

        jLabel2.setText("Razão Social:");

        jLabel3.setText("Nome Fantasia:");

        btn_Cadastrar.setText("Cadastrar");
        btn_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CadastrarActionPerformed(evt);
            }
        });
        btn_Cadastrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_CadastrarFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_razao)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_fantasia))
                .addContainerGap())
            .addComponent(btn_Cadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_razao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_fantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Cadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar / Excluir"));

        jLabel4.setText("Codigo:");

        btn_Pesquisar.setText("Pesquisar");
        btn_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PesquisarActionPerformed(evt);
            }
        });
        btn_Pesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_PesquisarFocusGained(evt);
            }
        });

        btn_Excluir.setText("Excluir");
        btn_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExcluirActionPerformed(evt);
            }
        });

        btnListarTodos.setText("Listar Todos");
        btnListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTodosActionPerformed(evt);
            }
        });

        btn_Limpar.setText("Limpar");
        btn_Limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimparActionPerformed(evt);
            }
        });

        jLabel5.setText("Razão:");

        btn_Pesquisar_razao.setText("Pesquisar");
        btn_Pesquisar_razao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Pesquisar_razaoActionPerformed(evt);
            }
        });
        btn_Pesquisar_razao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Pesquisar_razaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btn_Pesquisar_razaoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_codigo_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Pesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Excluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Limpar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_razao_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Pesquisar_razao)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_codigo_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Pesquisar)
                    .addComponent(btn_Excluir)
                    .addComponent(btnListarTodos)
                    .addComponent(btn_Limpar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_razao_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Pesquisar_razao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "RAZAO SOCIAL", "FANTASIA"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void ocultar_coluna_tabela(){
        //ocultar coluna ID da tabela
        jTable1.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        jTable1.getColumnModel().getColumn( 0 ).setMinWidth( 0 );  
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );  
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 ); 
        //fim ocultar
    }
    
    private void btn_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CadastrarActionPerformed
        // TODO add your handling code here:
        
        ClienteDTO clientes = new ClienteDTO();
        clientes.setCodigo(Integer.parseInt(txt_codigo.getText()));
        clientes.setRazao(txt_razao.getText());
        clientes.setFantasia(txt_fantasia.getText());
        
        
         // fazendo a validação dos dados
        if ((txt_codigo.getText().isEmpty()) || (txt_razao.getText().isEmpty()) ) {
            JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
        }
        else {

            // instanciando a classe ProdutoDAO do pacote dao e criando seu objeto dao

            ClienteDAO dao = new ClienteDAO();
            if (dao.adiciona_cliente(clientes)){
                JOptionPane.showMessageDialog(null, "Cliente "+txt_razao.getText()+" adicionado com sucesso! ");
                    
                
                
                
                DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
                model.setNumRows(0);
                model.addRow(new Object[]{clientes.getId(), clientes.getCodigo(), clientes.getRazao(), clientes.getFantasia()});
                    //listando todos clientes
                   //listar_clientes();
                     
                    // apaga os dados preenchidos nos campos de texto
                txt_codigo.setText("");
                txt_razao.setText("");
                txt_fantasia.setText("");
                

            }
            else {
                JOptionPane.showMessageDialog(null, "Erro, o cliente "+txt_razao.getText()+" não foi cadastrado! ");
            }
           
        }
    }//GEN-LAST:event_btn_CadastrarActionPerformed

    private void btn_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PesquisarActionPerformed
        // TODO add your handling code here:
       jTable1.setVisible(true);
       ClienteDAO dao = new ClienteDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       
        for (ClienteDTO c : dao.getClientePorCodigo(txt_codigo_pesquisar.getText())) {
           
            model.addRow(new Object[]{c.getId(), c.getCodigo(), c.getRazao(), c.getFantasia()});
        }
    }//GEN-LAST:event_btn_PesquisarActionPerformed

    private void btnListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodosActionPerformed
        // TODO add your handling code here:
        
        ClienteDAO dao = new ClienteDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
        model.setNumRows(0);
       
        for (ClienteDTO clientes : dao.listarClientes()) {
           
            model.addRow(new Object[]{clientes.getId(), clientes.getCodigo(), clientes.getRazao(), clientes.getFantasia()});
        }
        //fim listar
        
        
    }//GEN-LAST:event_btnListarTodosActionPerformed

    private void btn_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcluirActionPerformed
        // TODO add your handling code here:
         ClienteDTO cliente = new ClienteDTO();
                
                Integer linhaSelecionada = -1;
		linhaSelecionada = jTable1.getSelectedRow();
                
            if (linhaSelecionada >= 0) {//se linha selecionada
                
                    Integer id = (Integer) jTable1.getValueAt(linhaSelecionada, 0);
                    Integer codigo = (Integer) jTable1.getValueAt(linhaSelecionada, 1);
                    
                    //Confirmação
                    String message = "Deseja realmente excluir o Cliente: "+codigo+" ?";
                    String title = "Confirmação";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {//sim
                            //se confirmou
                            DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                            cliente.setId(id);//setar o id pra excluir
                            cliente.setCodigo(codigo);
                            ClienteDAO dao = new ClienteDAO();
                            if (dao.excluir(cliente)){
                                
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Erro, o cliente: "+codigo+" não foi excluido!");
                            }
                            model.removeRow(linhaSelecionada);
                    }else{
                        //não confirmou
                    }
                 
            }
            else{ //se linha não estiver selecionada
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");
            }   // fim linha selecionada
    }//GEN-LAST:event_btn_ExcluirActionPerformed

    private void btn_LimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimparActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
        model.setNumRows(0);
    }//GEN-LAST:event_btn_LimparActionPerformed

    private void btn_Pesquisar_razaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Pesquisar_razaoActionPerformed
        // TODO add your handling code here:
       jTable1.setVisible(true);
       ClienteDAO dao = new ClienteDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       
        for (ClienteDTO c : dao.getClientePorRazao(txt_razao_pesquisar.getText())) {
           
            model.addRow(new Object[]{c.getId(), c.getCodigo(), c.getRazao(), c.getFantasia()});
        }
    }//GEN-LAST:event_btn_Pesquisar_razaoActionPerformed

    private void txt_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_codigoFocusLost
        // TODO add your handling code here:
        //consultando cliente para preencher formulario
        
        //esvaziando campos
        txt_razao.setText("");
        txt_fantasia.setText("");
        
        
        String codigo_cliente = txt_codigo.getText();
        
          jTable1.setVisible(true);
       ClienteDAO dao = new ClienteDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       
        for (ClienteDTO c : dao.getClientePorCodigo(codigo_cliente)) {
             txt_razao.setText(c.getRazao());
             txt_fantasia.setText(c.getFantasia());
        }
        
        if ((txt_razao.getText().isEmpty()) ){
                    //cliente não existe, pode cadastrar
        }
        else{
            JOptionPane.showMessageDialog(null, "Codigo do cliente já existe.");
        }
    }//GEN-LAST:event_txt_codigoFocusLost

    private void btn_CadastrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_CadastrarFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Cadastrar);
    }//GEN-LAST:event_btn_CadastrarFocusGained

    private void btn_PesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_PesquisarFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Pesquisar);
    }//GEN-LAST:event_btn_PesquisarFocusGained

    private void btn_Pesquisar_razaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Pesquisar_razaoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Pesquisar_razaoFocusLost

    private void btn_Pesquisar_razaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Pesquisar_razaoFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Pesquisar_razao);
    }//GEN-LAST:event_btn_Pesquisar_razaoFocusGained

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
            java.util.logging.Logger.getLogger(ClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListarTodos;
    private javax.swing.JButton btn_Cadastrar;
    private javax.swing.JButton btn_Excluir;
    private javax.swing.JButton btn_Limpar;
    private javax.swing.JButton btn_Pesquisar;
    private javax.swing.JButton btn_Pesquisar_razao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_codigo_pesquisar;
    private javax.swing.JTextField txt_fantasia;
    private javax.swing.JTextField txt_razao;
    private javax.swing.JTextField txt_razao_pesquisar;
    // End of variables declaration//GEN-END:variables
}