/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ModeloTabla;

import java.awt.Font;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ModelT extends DefaultTableModel{

    public ModelT( Object[] columnNames,Class [] types) {
        super(new Object[][]{}, columnNames);
        this.types=types;
    }


    private Class[] types ;
    
    private boolean[] canEdit =null;

    @Override
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(canEdit==null)return false;
        return canEdit [columnIndex];
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }
}
