package Logica;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.print.PrintException;

import Objetos.Item;

public class Logica {
	private int numItens = 0;
	
//  METODO IMPRIMIR 
	 public void imprimir(Vector<Item> lista) throws PrintException, MalformedURLException { 
		    // cria um frame temporï¿½rio, onde serï¿½  desenhado  o texto a ser impresso     
		    Frame f = new Frame("Frame temporio");     
		    f.pack();      
		      
		    // pega o Toolkit do Frame     
		    Toolkit tk = f.getToolkit();        
		    PrintJob pj = tk.getPrintJob(f,  "pedido" , null); 
		

		  
		    // Aqui se inicia a impressï¿½o   
		    if (pj != null) {    
		        Graphics g = pj.getGraphics();  
		        g.setFont(new Font("Courier New",Font.PLAIN,10));

		        
		        g.setFont(new Font("Courier New",Font.PLAIN,12));
		        
		        //  imprime o cabeçalho   ....
		        String cabecalho[] = XML.cabeca;
		        
		        g.drawString("REPRESENTANTE : "+ cabecalho[0] ,30, 30);// representante
		        g.drawString("CÓDIGO DO CLIENTE : "+ cabecalho[1] ,30, 45);//  codigo cliente
		        g.drawString("CLIENTE : "+cabecalho[2] ,30, 60); // nome cliente
		        g.drawString("NUMERO PEDIDO : "+cabecalho[3] ,30, 75); //  numero pedido
		        g.drawString("DATA VENDA : "+cabecalho[4] ,230, 75); //  data da venda 
		        g.drawString("PAGAMENTO ----------------------------------------------------------------------------------------------------------",30,90); //  operação de Venda
		        g.drawString("COND. PAGAMENTO : "+cabecalho[5] ,30, 105); //  condição de pagamento
		        g.drawString("TABELA  : "+cabecalho[6] ,290, 105); //  condição de pagamento
		        g.drawString("TIPO PAGAMENTO  : "+cabecalho[7] ,30, 120); //  condição de pagamento
		        g.drawString("OPERAÇÃO :"+ cabecalho[10],360, 120); //  operação de Venda
		        g.setFont(new Font("Courier New",Font.PLAIN,8));
		        g.drawString("OBS.: "+cabecalho[11],30,135); // Observação....
		        g.drawString("_________________________________________________________________________________________________________________ ",30,170); //  operação de Venda
		        g.setFont(new Font("Courier New",Font.PLAIN,12));
		        
		        g.drawString("ITENS *************************************************************************************************************" ,30, 180); //  data da venda 
		        g.drawString("Código    Descrição                               Qtd  Vl.Unit.   Vl.Total" ,30, 192); //  data da venda 
		        g.drawString("____________________________________________________________________________________________________________________" ,30, 195); //  data da venda 
		        
		        int linha = 212;
		        //  imprime os Itens do pedido
		        for(int i =0; i < lista.size();i++){
		        	

					Double vl = Double.parseDouble(lista.get(i).getVlTotal());
					String l = limitaCasa(vl);
					
					
					
			
				Double k = Double.parseDouble(lista.get(i).getVlUnitario());
				
					
					//System.out.println(l);
		        	
		        	 g.drawString( lista.get(i).getCodigo() ,30, linha);
		        	 g.drawString( lista.get(i).getDescricao() ,80, linha); 
		        	 g.drawString( lista.get(i).getQtd() ,390, linha);
		        	 g.drawString(limitaCasa(k) ,430, linha);
		        	 g.drawString( l ,505, linha);
		        	 linha = linha+14;
	 	 
		        }
		      
		       //  RODAPE
		        
		        g.drawString("***************************************************************************************" ,30, linha+30); //  condição de pagamento
		        g.drawString("N° ITENS : "+lista.size() ,30, linha+44); //  condição de pagamento
		        g.drawString("QTD.ITENS : "+cabecalho[7] ,190, linha+44); //  condição de pagamento
		        
		        String totalPedido = limitaCasa(Double.parseDouble(cabecalho[8]));
		        g.drawString("TOTAL PEDIDO : "+totalPedido ,360, linha+44 ); //  condição de pagamento
		   
		           
		  
		        // libera os recursos grï¿½ficos    
		        g.dispose();      
		        // encerra a impressï¿½o    
		        pj.end();   
		    }    
		  
		    // desfaz o frame temporï¿½rio   
		    f.dispose();   
		    }  
	 
	// Limita casa decimal
		public String limitaCasa(Double valor) {
			// Limitar a duas casas decimais
			DecimalFormat df = new DecimalFormat("0.00");
			String str = df.format(valor);
			return str;
		}

		

	

}
