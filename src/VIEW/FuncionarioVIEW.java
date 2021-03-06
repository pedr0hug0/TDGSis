/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import CTR.tdgCTR;
import DTO.FuncionarioDTO;
import DTO.FuncionarioDTO;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PeDr0_HuG0
 */
public class FuncionarioVIEW extends javax.swing.JFrame {

    /**
     * Creates new form ClienteVIEW
     */
    public FuncionarioVIEW() {
        initComponents();
        //botao x fechar apenas janela
        setDefaultCloseOperation(ProdutoVIEW.HIDE_ON_CLOSE);
        
        tdgCTR.considerarEnterComoTab(txt_area);
        tdgCTR.considerarEnterComoTab(txt_nome);
        tdgCTR.considerarEnterComoTab(txt_cargo);
        tdgCTR.considerarEnterComoTab(txt_nome_pesquisar);
        
   
        txt_nome.requestFocus();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        txt_cargo = new javax.swing.JTextField();
        btn_Cadastrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_area = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_Excluir = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JButton();
        btn_Limpar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_nome_pesquisar = new javax.swing.JTextField();
        btn_Pesquisar_razao = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Funcionario"));

        jLabel2.setText("Nome:");

        jLabel3.setText("Cargo:");

        btn_Cadastrar.setText("Cadastrar");
        btn_Cadastrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_CadastrarFocusGained(evt);
            }
        });
        btn_Cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CadastrarActionPerformed(evt);
            }
        });

        jLabel6.setText("Área:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Cadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_area)
                    .addComponent(txt_nome)
                    .addComponent(txt_cargo))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Cadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar / Excluir"));

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
        btn_Pesquisar_razao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btn_Pesquisar_razaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btn_Pesquisar_razaoFocusLost(evt);
            }
        });
        btn_Pesquisar_razao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Pesquisar_razaoActionPerformed(evt);
            }
        });

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nome_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Pesquisar_razao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Excluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListarTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Limpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_nome_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Pesquisar_razao))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Excluir)
                        .addComponent(btnListarTodos)
                        .addComponent(btn_Limpar)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "CARGO", "AREA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        }
        //ocultar id
        jTable1.getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getColumnModel().getColumn( 0 ).setMinWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMaxWidth( 0 );
        jTable1.getTableHeader().getColumnModel().getColumn( 0 ).setMinWidth( 0 );

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
   
    
    private void btn_CadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CadastrarActionPerformed
        // TODO add your handling code here:
        
        FuncionarioDTO funcionarios = new FuncionarioDTO();
        funcionarios.setNome(txt_nome.getText());
        funcionarios.setCargo(txt_cargo.getText());
        funcionarios.setArea(txt_area.getText());
        
         // fazendo a validação dos dados
        if ((txt_nome.getText().isEmpty()) || (txt_cargo.getText().isEmpty()) || (txt_area.getText().isEmpty()) ) {
            JOptionPane.showMessageDialog(null, "Os campos não podem retornar vazios");
        }
        else {

            // instanciando a classe ProdutoDAO do pacote dao e criando seu objeto dao

            FuncionarioDAO dao = new FuncionarioDAO();
            if (dao.adiciona_funcionario(funcionarios)){
                JOptionPane.showMessageDialog(null, "Funcionario:\n "+txt_nome.getText()+"\nAdicionado com sucesso! ");
                    
                
                
                
                DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
                model.setNumRows(0);
                model.addRow(new Object[]{funcionarios.getId(), funcionarios.getNome(), funcionarios.getCargo(), funcionarios.getArea()});
                   
                     
                // apaga os dados preenchidos nos campos de texto
                
                txt_nome.setText("");
                txt_cargo.setText("");
                txt_area.setText("");
                

            }
            else {
                JOptionPane.showMessageDialog(null, "Erro, o funcionario não foi cadastrado!");
            }
           
        }
    }//GEN-LAST:event_btn_CadastrarActionPerformed

    private void btnListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodosActionPerformed
        // TODO add your handling code here:
        
        jTable1.setVisible(true);
       FuncionarioDAO dao = new FuncionarioDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       
       String funcionario = "%";
       
       for (FuncionarioDTO f : dao.getFuncionarioPorNome(funcionario)) {
           
            model.addRow(new Object[]{f.getId(), f.getNome(), f.getCargo(), f.getArea()});
        }
        
        
    }//GEN-LAST:event_btnListarTodosActionPerformed

    private void btn_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExcluirActionPerformed
        // TODO add your handling code here:
         FuncionarioDTO funcionario = new FuncionarioDTO();
                
                Integer linhaSelecionada = -1;
		linhaSelecionada = jTable1.getSelectedRow();
                
            if (linhaSelecionada >= 0) {//se linha selecionada
                
                    Integer id = (Integer) jTable1.getValueAt(linhaSelecionada, 0);
                    String nome = (String) jTable1.getValueAt(linhaSelecionada, 1);
                    
                    //Confirmação
                    String message = "Deseja realmente excluir o Funcionario: "+nome+" ?";
                    String title = "Confirmação";
                    int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION)
                    {//sim
                            //se confirmou
                            DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                            funcionario.setId(id);//setar o id pra excluir
                            funcionario.setNome(nome);
                            FuncionarioDAO dao = new FuncionarioDAO();
                            if (dao.excluir_funcionario(funcionario)){
                                
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Erro, o funcionario:\n "+nome+" não foi excluido!");
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
       FuncionarioDAO dao = new FuncionarioDAO();
       DefaultTableModel model =  (DefaultTableModel) jTable1.getModel(); 
            
       model.setNumRows(0);
       
       String funcionario = txt_nome_pesquisar.getText();
       if ( (txt_nome_pesquisar.getText().isEmpty()) || (txt_nome_pesquisar.getText().equals(""))){
          funcionario = "%";
       }
       
       
        for (FuncionarioDTO f : dao.getFuncionarioPorNome(funcionario)) {
           
            model.addRow(new Object[]{f.getId(), f.getNome(), f.getCargo(), f.getArea()});
        }
    }//GEN-LAST:event_btn_Pesquisar_razaoActionPerformed

    private void btn_CadastrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_CadastrarFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Cadastrar);
    }//GEN-LAST:event_btn_CadastrarFocusGained

    private void btn_Pesquisar_razaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Pesquisar_razaoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Pesquisar_razaoFocusLost

    private void btn_Pesquisar_razaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btn_Pesquisar_razaoFocusGained
        // TODO add your handling code here:
        tdgCTR.reagirEnter(btn_Pesquisar_razao);
    }//GEN-LAST:event_btn_Pesquisar_razaoFocusGained

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
        FuncionarioDTO funcionarios = new FuncionarioDTO();
        if ("tableCellEditor".equals(evt.getPropertyName()))
		{
			if (jTable1.isEditing()){
                        }	
                        else{
                            
                            Integer linhaSelecionada = -1;
                            linhaSelecionada = jTable1.getSelectedRow();

                            if (linhaSelecionada >= 0) {
                                Integer id = (Integer) jTable1.getValueAt(linhaSelecionada, 0);
                                String nome = (String) jTable1.getValueAt(linhaSelecionada, 1);
                                String cargo = (String) jTable1.getValueAt(linhaSelecionada, 2);
                                String area = (String) jTable1.getValueAt(linhaSelecionada, 3);
                                
                                
                                
                                
                                DefaultTableModel model =  (DefaultTableModel) jTable1.getModel();
                                funcionarios.setId(id);//setar o id pra excluir
                                funcionarios.setNome(nome);
                                funcionarios.setCargo(cargo);
                                funcionarios.setArea(area);
                                
                                FuncionarioDAO dao = new FuncionarioDAO();
                                if (dao.atualizar_func(funcionarios)){
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
		}
        
        
    }//GEN-LAST:event_jTable1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Edite direto da tabela.");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionarioVIEW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionarioVIEW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListarTodos;
    private javax.swing.JButton btn_Cadastrar;
    private javax.swing.JButton btn_Excluir;
    private javax.swing.JButton btn_Limpar;
    private javax.swing.JButton btn_Pesquisar_razao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_area;
    private javax.swing.JTextField txt_cargo;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_nome_pesquisar;
    // End of variables declaration//GEN-END:variables
}
