
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTR;

//P001 está em CorteVIEW, ItemPedidoVIEW, EntradaEstoqueVIEW e cadastro do produto adicionar QTD_CORES = 14 e nenhuma cor. 


import DTO.CoresDTO;
import DTO.ProdutoDTO;
import dao.CoresDAO;
import dao.ProdutoCoresDAO;
import dao.ProdutoDAO;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.joda.time.LocalDate;

/**
 *
 * @author PeDr0_HuG0
 */
public class tdgCTR {
    
     //chamar metodo
    //tdgCTR geral = new tdgCTR();
    //geral.considerarEnterComoTab(txt_n_pedido);
   
    
    //considerar enter como tab
    public static void considerarEnterComoTab(Component comp) {  
        HashSet<AWTKeyStroke> newKeystrokes;  
        newKeystrokes = new HashSet<>(  
                comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_DOWN, 0));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_KP_DOWN, 0));  
        comp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,  
                newKeystrokes);  
        newKeystrokes = new HashSet<>(  
                comp.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER,  
                InputEvent.SHIFT_DOWN_MASK));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_UP, 0));  
        newKeystrokes.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_KP_UP, 0));  
        comp.setFocusTraversalKeys(  
                KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, newKeystrokes);  
    }//fim enter como tab
   
    
    
    //Executa o evento action(clique) quando a tecla enter é precionada
    public static void reagirEnter(JButton btn){
    btn.registerKeyboardAction(
      btn.getActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)), 
        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), 
            JComponent.WHEN_FOCUSED
      );

    btn.registerKeyboardAction(
      btn.getActionForKeyStroke(
        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)), 
        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), 
        JComponent.WHEN_FOCUSED
      );
  }
    
    
   //retorna o total de itens da tabela em string 
    public static String calc_Total_Itens(JTable tabela, Integer coluna) {
        Integer total_itens = 0;
        String total_itens_string = null;
         for(int i=0;i<tabela.getRowCount();i++){ 
            Integer total = (Integer) tabela.getValueAt(i,coluna);
             
            total_itens = total_itens + total;
            total_itens_string = total_itens.toString();
         }
         return total_itens_string;
    }
    
       //retorna o total de itens da tabela em INT 
    public static int calc_Total_Itens_int(JTable tabela, Integer coluna) {
        Integer total_itens = 0;
        
         for(int i=0;i<tabela.getRowCount();i++){ 
            Integer total = (Integer) tabela.getValueAt(i,coluna);
             
            total_itens = total_itens + total;
            
         }
         return total_itens;
    }

    
    public static boolean isInt(String v) {  
    /* Verifica se um numero é inteiro ou não */
            try {  
                Integer.parseInt(v);  
                return true;  
            } catch (Exception e) {  
                return false;  
            }  

    }
    public static boolean isString(String v) {  
    /* Verifica se campos está vazio */
            try {  
                v.toString();  
                return true;  
            } catch (Exception e) {  
                return false;  
            }  

    }
    
    
    public static void selecionarTudo(JTable tabela, Integer coluna_boolean){
        for(int i=0;i<tabela.getRowCount();i++){ 
            //se estiver verdadeiro, seta falso
            if (tabela.getValueAt(i, coluna_boolean).equals(true)){
                tabela.setValueAt(false, i, coluna_boolean);
            }
            else{//Deselecionar
                tabela.setValueAt(true, i, coluna_boolean);
            }
        }
    }
    
   
    public static void todasCores(JComboBox jcombobox){
                jcombobox.addItem("-");
                CoresDAO dao = new CoresDAO();
        
                for (CoresDTO cores : dao.ListarCoresCategoria("TODAS")) {
                    jcombobox.addItem(cores.getCor());
                }
    }
  
     
    //CORES DA REF 
    public static void cores_ref(String codigo, JComboBox jcombobox){
        ProdutoCoresDAO dao = new ProdutoCoresDAO();
        for (CoresDTO cores : dao.ListarProdutoCores(codigo)) {
            jcombobox.addItem(cores.getCor());
        }
    }
    
    
    
    public static void todasDescricao(JComboBox jcombobox){
                
                ProdutoDAO dao = new ProdutoDAO();
        
                for (ProdutoDTO p : dao.ListarDescricao()) {
                    jcombobox.addItem(p.getDescricao());
                }
    }
    
    
    /*listar cores da categorias EM CoresDAO
     
        CoresDAO dao = new CoresDAO();
        
        for (CoresDTO cores : dao.ListarCores()) {
            model.addRow(new Object[]{cores.getId(),cores.getCor(), cores.getCategoria()});
        }
    */
   
    
    public static String getDataFormatada(String data_string){

        SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
        return formatarData.format(LocalDate.parse(data_string).toDate());

    }
    
    
    public static void auto_selecionar(JTextField txt_nome){
        txt_nome.addFocusListener(new java.awt.event.FocusAdapter() {  
                    @Override  
                    public void focusGained(java.awt.event.FocusEvent evt) {  
                        txt_nome.selectAll();  
                    }  
                });
    }
   

    public static void hotkey(char KeyEventDotVK_tecla, String tecla_x, JButton btn_acao) {
        
        Action actionTecla = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        //simula o click no botão
                        btn_acao.doClick();
                }
        }; 
        
        //KeyEvent.VK_F2
        //Associa o listener com a tecla f1 para que seja disparado toda vez, mesmo quando o foco não está no botão
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEventDotVK_tecla, 0);
        String actionName = tecla_x;
        InputMap inputMap = btn_acao.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, actionName);
        ActionMap actionMap = btn_acao.getActionMap();
        actionMap.put(actionName, actionTecla);
        
    }

    public static void hotkey(int KeyEventDotVK_tecla, String tecla_x, JButton btn_acao) {
        Action actionTecla = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        //simula o click no botão
                        btn_acao.doClick();
                }
        }; 
        
        //KeyEvent.VK_F2
        //Associa o listener com a tecla f1 para que seja disparado toda vez, mesmo quando o foco não está no botão
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEventDotVK_tecla, 0);
        String actionName = tecla_x;
        InputMap inputMap = btn_acao.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(keyStroke, actionName);
        ActionMap actionMap = btn_acao.getActionMap();
        actionMap.put(actionName, actionTecla);
        
    }
   
    
    
}//fim 
