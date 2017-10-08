/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abina
 */
public class PDF {

    private String titulo;

    public PDF(String titulo) {
        this.titulo = titulo;
    }

    public PDF() {
    }
    
    
    /**
     * @param args the command line arguments
     */
    public void report(DefaultTableModel dm,File f,int size, int fontSize,float colSizee[]){
        try {
            if(f==null){
                return;
            }
            String url=f.getAbsolutePath();
            if(!url.endsWith(".pdf")){
                f=new File(url+".pdf");
            
            }
            int colm=dm.getColumnCount();
            int row=dm.getRowCount();
            PdfPTable tabla=new PdfPTable(colm);
            tabla.setWidthPercentage(size);
            tabla.setWidths(colSizee);
            for(int i=0;i<colm;i++){
                
                Paragraph p=new Paragraph(dm.getColumnName(i).toUpperCase(),
                        FontFactory.getFont("arial",   // fuente
                                fontSize,                            // tamaño
                                Font.BOLD,                   // estilo
                                BaseColor.WHITE));             // color
                PdfPCell cell=new PdfPCell(p);
                cell.setBackgroundColor(new BaseColor(0,3,37));

                tabla.addCell(cell);
            }
            for(int filas=0;filas<row;filas++){
                
                for(int columnas=0;columnas<colm;columnas++){
                    Paragraph p=new Paragraph(dm.getValueAt(filas, columnas).toString(),
                            FontFactory.getFont("arial",   // fuente
                                    fontSize,                            // tamaño
                                    Font.NORMAL,                   // estilo
                                    BaseColor.BLACK));             // color
                    p.setAlignment(Paragraph.ALIGN_RIGHT);
                    PdfPCell cell=new PdfPCell(p);
                    if(filas%2!=0){
                        cell.setBackgroundColor(new BaseColor(240, 240, 240));
                    }
                    tabla.addCell(cell);
                }
            }
            // Se crea el documento
            Document documento = new Document();
            documento.setMargins(0,0,0,0);
            // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            FileOutputStream ficheroPdf = new FileOutputStream(f);
            
            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
            
            // Se abre el documento.
            documento.open();
            
            Paragraph p=new Paragraph(this.titulo,
                    FontFactory.getFont("arial",   // fuente
                            22,                            // tamaño
                            Font.BOLD,                   // estilo
                            BaseColor.BLACK));             // color
            p.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(p);
            documento.add(new Paragraph("\n\r"));
            
            documento.add(tabla);

            documento.close();
            
            String file = f.getAbsolutePath(); 
   
            try{ 
                Runtime.getRuntime().exec("cmd /c start "+file);

            }catch(IOException e){
                e.printStackTrace();
            } 
            
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
}
