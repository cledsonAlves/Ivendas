package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import Objetos.Item;

public class XML {
	private FileInputStream   input;
	private static String arquivo;
	public static String[] cabeca;
	Vector<Item> listaItens;
	

	
	//  construtor da classe 
	public XML(){
		
		//  verifica se o arquivo esta em branco ...
		
	//  Lista Itens ...  
		listaItens = new Vector<Item>();
	}
	
	public Vector<Item> leitura(String arquivo){
		try {
		
			
			input = new FileInputStream(new File(arquivo));
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(input);
			Element root =(Element) doc.getRootElement();
			List<Element> itens = root.getChildren();
			Iterator i = itens.iterator();
			
			//  leitura do cabeçalho 
			Element cabecalho = (Element) i.next();  
			
			cabeca = new String[]{cabecalho.getChildText("representante"),cabecalho.getChildText("codigoCliente"),cabecalho.getChildText("nomeCliente"),cabecalho.getChildText("numero"),cabecalho.getChildText("dataVenda"),cabecalho.getChildText("condPagamento")
					,cabecalho.getChildText("tabela"),cabecalho.getChildText("qtdItens"),cabecalho.getChildText("valorTotal"),cabecalho.getChildText("tipoPagamento"),cabecalho.getChildText("tipoVenda"),cabecalho.getChildText("obs")};
			
			
			
			//  leitura dos itens 
			while (i.hasNext()){
				//  elemento XML
				Element element = (Element)i.next();
                 
				//  Item para lista 
				Item item = new Item();
				
				item.setCodigo(element.getChildText("codigo"));
				item.setDescricao(element.getChildText("descricao"));
				item.setQtd(element.getChildText("quantidade"));
				item.setVlUnitario(element.getChildText("valorUnitario"));
				item.setVlTotal(element.getChildText("valorTotal"));
				//  adcionando elemnto na lista 
				listaItens.add(item);
			}
			
		
		return listaItens;	
			

			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não localizado!!  contate equipe de T.I informando o erro : " + e);
			e.printStackTrace();
		} catch (JDOMException e) {
			JOptionPane.showMessageDialog(null, "Erro na leitura do xml!!  contate equipe de T.I informando o erro : " + e);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na leitura do xml!!  contate equipe de T.I informando o erro : " + e);
			e.printStackTrace();
		}
		return listaItens;
	}


}
