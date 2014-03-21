package Gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.print.PrintException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import Logica.Logica;
import Logica.MailTester;
import Logica.XML;
import Objetos.Item;
/**
 * 
 * @author c.santos  alteração 18/10/2013 17:45hs
 *
 */
public class MenuPrincipal extends JFrame implements ActionListener {
	private JPanel pItens, pCabecalho, pPagamento;
	private JTable tabela;
	private JScrollPane scroll;
	private Font fonte = new Font("Arial", Font.PLAIN, 14);
	private JTextField txtRca, txtCliente, txtPrazo, txtRazao, txtEndereco,
			txtCidade, txtCnpj, txtdataVenda, txtBairro, txtTotal, txtQtdItens,
			txtNumPed;
	private JButton carregaPedido, btnLimpar, btnTrasmite, btnImprime,
			btnGrava, btnExclui, btnInclui;
	private JProgressBar barra;
	String[] cabeca;
	Vector<Item> lista;
	Logica logica;

	Tabela modelo;

	// Construtor
	public MenuPrincipal() {

		run();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(840, 760);
		this.setTitle("::iVENDAS");
		this.setLayout(null);
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/Imagens/inf32.png")).getImage());
		this.setVisible(true);

		/*
		 * Rotina para centralizar a janela independente da resolução da tela
		 */

		Dimension tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) (tamanhoTela.getWidth() - this.getWidth()) / 2,
				(int) (tamanhoTela.getHeight() - this.getHeight()) / 2);

	}

	public void paint(Graphics g) {
		super.paint(g);
		// g.drawLine(660, 80, 660, 627);
		g.drawLine(805, 80, 805, 627);

	}

	public void run() {

		// cabeçalho do pedido
		pCabecalho = new JPanel();
		pCabecalho.setLayout(null);

		// Numero do pedido
		JLabel rca = new JLabel("Representante :");
		rca.setBounds(5, 0, 150, 25);
		rca.setFont(fonte);
		pCabecalho.add(rca);

		txtRca = new JTextField();
		txtRca.setFont(fonte);
		txtRca.setEditable(false);
		txtRca.setBounds(130, 5, 300, 25);
		pCabecalho.add(txtRca);

		JLabel razao = new JLabel("Razão Social :");
		razao.setBounds(5, 40, 150, 25);
		razao.setFont(fonte);
		pCabecalho.add(razao);

		txtRazao = new JTextField();
		txtRazao.setFont(fonte);
		txtRazao.setEditable(false);
		txtRazao.setBounds(130, 40, 500, 25);
		pCabecalho.add(txtRazao);

		JLabel endereco = new JLabel("Código Cliente:");
		endereco.setBounds(5, 75, 150, 25);
		endereco.setFont(fonte);
		pCabecalho.add(endereco);

		txtEndereco = new JTextField();
		txtEndereco.setFont(fonte);
		txtEndereco.setEditable(false);
		txtEndereco.setBounds(130, 75, 250, 25);
		pCabecalho.add(txtEndereco);

		JLabel cidade = new JLabel("Observação:");
		cidade.setBounds(5, 110, 150, 25);
		cidade.setFont(fonte);
		pCabecalho.add(cidade);

		txtCidade = new JTextField();
		txtCidade.setFont(fonte);
		txtCidade.setEditable(false);
		txtCidade.setBounds(130, 110, 510, 35);
		pCabecalho.add(txtCidade);

		JLabel prazo = new JLabel("Prazo:");
		prazo.setBounds(5, 150, 150, 25);
		prazo.setFont(fonte);
		pCabecalho.add(prazo);

		txtPrazo = new JTextField();
		txtPrazo.setFont(fonte);
		txtPrazo.setEditable(false);
		txtPrazo.setBounds(130, 150, 160, 25);
		pCabecalho.add(txtPrazo);

		JLabel total = new JLabel("Total do Pedido:");
		total.setBounds(320, 660, 150, 25);
		total.setFont(fonte);
		this.add(total);

		txtTotal = new JTextField();
		txtTotal.setFont(fonte);
		txtTotal.setEditable(false);
		txtTotal.setBounds(470, 660, 160, 25);
		this.add(txtTotal);

		JLabel qtdItens = new JLabel("Qtd Itens:");
		qtdItens.setBounds(20, 660, 150, 25);
		qtdItens.setFont(fonte);
		this.add(qtdItens);

		// Botão exclui intens do pedido
		btnExclui = new JButton("Excluir");
		btnExclui.setBounds(200, 600, 124, 44);
		btnExclui.setFont(fonte);
		btnExclui.addActionListener(this);
		this.add(btnExclui);

		// Botão Inclui itens no pedido
		btnInclui = new JButton("Inserir");
		btnInclui.setBounds(400, 600, 124, 44);
		btnInclui.setFont(fonte);
		btnInclui.addActionListener(this);
		this.add(btnInclui);

		txtQtdItens = new JTextField();
		txtQtdItens.setFont(fonte);
		txtQtdItens.setEditable(false);
		txtQtdItens.setBounds(100, 660, 100, 25);
		this.add(txtQtdItens);

		JLabel numPedido = new JLabel("Nº Pedido:");
		numPedido.setBounds(450, 5, 150, 25);
		numPedido.setFont(fonte);
		pCabecalho.add(numPedido);

		txtNumPed = new JTextField();
		txtNumPed.setFont(fonte);
		txtNumPed.setEditable(false);
		txtNumPed.setBounds(520, 5, 120, 25);
		pCabecalho.add(txtNumPed);

		JLabel dataVenda = new JLabel("Data Venda :");
		dataVenda.setBounds(320, 150, 150, 25);
		dataVenda.setFont(fonte);
		pCabecalho.add(dataVenda);

		txtdataVenda = new JTextField();
		txtdataVenda.setFont(fonte);
		txtdataVenda.setEditable(false);
		txtdataVenda.setBounds(470, 150, 160, 25);
		pCabecalho.add(txtdataVenda);

		carregaPedido = new JButton("Pedido");
		carregaPedido.addActionListener(this);
		carregaPedido.setBounds(660, 0, 124, 44);
		carregaPedido.setFont(fonte);
		pCabecalho.add(carregaPedido);

		// Botão Limpar
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(660, 80, 124, 44);
		btnLimpar.addActionListener(this);
		btnLimpar.setFont(fonte);
		btnLimpar.setEnabled(false);
		pCabecalho.add(btnLimpar);

		// Botão imprimir pedido
		btnImprime = new JButton("Imprimir");
		btnImprime.setBounds(660, 160, 124, 44);
		btnImprime.addActionListener(this);
		btnImprime.setEnabled(false);
		btnImprime.setFont(fonte);
		pCabecalho.add(btnImprime);

		// Botão Trasmitir
		btnTrasmite = new JButton("Gerar espelho");
		btnTrasmite.setBounds(660, 250, 124, 44);
		btnTrasmite.setEnabled(false);
		btnTrasmite.setFont(fonte);
		btnTrasmite.addActionListener(this);
		pCabecalho.add(btnTrasmite);

		// Botão Grava no banco de dados o pedido
		btnGrava = new JButton("Gravar");
		btnGrava.setBounds(660, 340, 124, 44);
		btnGrava.setEnabled(false);
		btnGrava.setFont(fonte);
		btnGrava.addActionListener(this);
		pCabecalho.add(btnGrava);
	

		JTabbedPane jtpTabs = new JTabbedPane();
		jtpTabs.setBounds(0, 100, 600, 600);

		// adiciona os panels como tabs
		// jtpTabs.addTab("Dados Cliente", painelCliente);
		// jtpTabs.addTab("Itens Pedido      ", painelPedido);
		// jtpTabs.addTab("Condição de Pagamento", painelPagamento);

		// molde para cabeçalho do corpo do pedido
		modelo = new Tabela();
		modelo.addColumn("<html><H4>Código</H4></html>");
		modelo.addColumn("<html><H4>Descrição</H4></html>");
		modelo.addColumn("<html><H4>Qtd</H4><html>");
		modelo.addColumn("<html><H4>Valor Unitário");
		modelo.addColumn("<html><H4>Valor Total");

		// tabela corpo do pedido
		tabela = new JTable(modelo);
		tabela.setLayout(null);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(260);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(90);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(145);

		// cria scroll do corpo do pedido
		scroll = new JScrollPane(tabela);
		scroll.setHorizontalScrollBar(null);
		scroll.setBounds(0, 222, 640, 320);

		// painel dos itens
		pItens = new JPanel();
		pItens.setLayout(null);
		pCabecalho.add(scroll);

		// painel condição pagamento
		pPagamento = new JPanel();
		pPagamento.setLayout(null);

		jtpTabs.addTab("<html><H2>Pedido Kelma</H2></html>", pCabecalho);
		// jtpTabs.addTab("Itens", pItens);
		// jtpTabs.addTab("Pagamento", pPagamento);
		jtpTabs.setBounds(0, 0, 800, 600);
		this.add(jtpTabs);

		// para alternar cores
		tabela.setFont(fonte);

		barra = new JProgressBar();
		// barra.setFloatable(false);
		barra.setBounds(0, 700, 823, 20);
		barra.setMaximum(100);
		this.add(barra);

	}

	private String buscaPedido() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Carregar Pedido");
		fc.setCurrentDirectory(new File("K:\\VENDAS\\PEDIDOS"));
		// fc.setSelectedFile(new File(".xml"));
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(new javax.swing.filechooser.FileFilter() {
			// Filtro, converte as letras em minúsculas antes de comparar
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".xml")
						|| f.isDirectory();
			}

			// Texto que será exibido para o usuário
			public String getDescription() {
				return "Arquivos de pedidos (.xml)";
			}
		});

		// restringe a amostra a diretorios apenas
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int res = fc.showOpenDialog(null);
		// Se encontrou diretorio carrega o pedido
		if (res == JFileChooser.APPROVE_OPTION) {
			// XML leitura = new XML(fc.getSelectedFile().getAbsolutePath());
			return fc.getSelectedFile().getAbsolutePath();

		} else
			JOptionPane.showMessageDialog(null,
					"Você não selecionou nenhum diretório.");
		return null;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == carregaPedido) {
			String path = buscaPedido();
			lista = new Vector<Item>();
			XML x = new XML();

			lista = x.leitura(path);

			for (int i = 0; i < lista.size(); i++) {
				logica = new Logica();

				// limita casa decimal ...
				Double vl = Double.parseDouble(lista.get(i).getVlTotal());

				String[] v = new String[] { lista.get(i).getCodigo(),
						lista.get(i).getDescricao(), lista.get(i).getQtd(),
						lista.get(i).getVlUnitario(), logica.limitaCasa(vl) };
				modelo.addRow(v);
			}

			cabeca = XML.cabeca;
			txtRca.setText(cabeca[0]);
			txtRazao.setText(cabeca[2]);
			txtNumPed.setText(cabeca[3]);
			txtdataVenda.setText(cabeca[4]);
			txtEndereco.setText(cabeca[1]);
			txtCidade.setText(cabeca[11]);
			txtPrazo.setText(cabeca[6]);
			txtQtdItens.setText(cabeca[7]);
			Double vlTotal = Double.parseDouble(cabeca[8]);

			txtTotal.setText(logica.limitaCasa(vlTotal));
			btnLimpar.setEnabled(true);
			btnImprime.setEnabled(true);
			btnTrasmite.setEnabled(true);
			carregaPedido.setEnabled(false);

		}
		if (e.getSource() == btnImprime) {
			Logica l = new Logica();
			try {
				
				String email = "" ;
				
				//  Lembrar de modoficar este codigo ( Pode ser com case )
				if (txtRca.getText().contains("EDUARDO")){
				  email = JOptionPane.showInputDialog("E-mail para envio do espelho","esasimoes03@terra.com.br");	 
				}else if (txtRca.getText().contains("ALAN")){
					 email = JOptionPane.showInputDialog("E-mail para envio do espelho","alansadao@hotmail.com");
				}else if (txtRca.getText().contains("MIQUEIAS")){
					 email = JOptionPane.showInputDialog("E-mail para envio do espelho","miqueiaskelma@gmail.com");
				}else if (txtRca.getText().contains("MARCOS")){
					 email = JOptionPane.showInputDialog("E-mail para envio do espelho","marcosac22@gmail.com");
				}else if (txtRca.getText().contains("CARLOS")){
					 email = JOptionPane.showInputDialog("E-mail para envio do espelho","carloskelma@gmail.com");
				}else if (txtRca.getText().contains("LAELIA")){
					 email = JOptionPane.showInputDialog("E-mail para envio do espelho","laeliafrazao@gmail.com");
				}else if (txtRca.getText().contains("")){
					 email = JOptionPane.showInputDialog("Informe o e-mail do RCA para gerar o espelho");
				}
				if (!email.equals("")){
				 new MailTester(email, lista);
				 JOptionPane.showMessageDialog(null,
				 "Espelho enviado com sucesso !!!"); barra.setValue(0);
				  l.imprimir(lista);
				}
				
			
			} catch (MalformedURLException e1) {

				e1.printStackTrace();
			} catch (PrintException e1) {

				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnLimpar) {
			txtRca.setText("");
			txtRazao.setText("");
			txtNumPed.setText("");
			txtdataVenda.setText("");
			txtPrazo.setText("");
			txtQtdItens.setText("");
			txtTotal.setText("");
			txtCidade.setText("");
			txtEndereco.setText("");
			barra.setValue(0);

			while (tabela.getModel().getRowCount() > 0){
				modelo.removeRow(0);
			}
		 
			btnLimpar.setEnabled(false);
			btnImprime.setEnabled(false);
			btnTrasmite.setEnabled(false);
			carregaPedido.setEnabled(true);
		} 
		if (e.getSource() == btnTrasmite) {
			// busca dados do cliente ......
			//ManipulaBanco mb = new ManipulaBanco();
			//String[] dados = mb.buscaDados(cabeca[1]);
			String email = "" ;
			
			//  Lembrar de modoficar este codigo ( Pode ser com case )
			if (txtRca.getText().contains("EDUARDO")){
			  email = JOptionPane.showInputDialog("E-mail para envio do espelho","esasimoes03@terra.com.br");	 
			}else if (txtRca.getText().contains("ALAN")){
				 email = JOptionPane.showInputDialog("E-mail para envio do espelho","alansadao@hotmail.com");
			}else if (txtRca.getText().contains("MIQUEIAS")){
				 email = JOptionPane.showInputDialog("E-mail para envio do espelho","miqueiaskelma@gmail.com");
			}else if (txtRca.getText().contains("MARCOS")){
				 email = JOptionPane.showInputDialog("E-mail para envio do espelho","marcosac22@gmail.com");
			}else if (txtRca.getText().contains("CARLOS")){
				 email = JOptionPane.showInputDialog("E-mail para envio do espelho","carloskelma@gmail.com");
			}else if (txtRca.getText().contains("LAELIA")){
				 email = JOptionPane.showInputDialog("E-mail para envio do espelho","laeliafrazao@gmail.com");
			}else if (txtRca.getText().contains("")){
				 email = JOptionPane.showInputDialog("Informe o e-mail do RCA para gerar o espelho");
			}
			if (!email.equals("")){
			 new MailTester(email, lista);

			 JOptionPane.showMessageDialog(null,
			 "Espelho enviado com sucesso !!!"); barra.setValue(0);
			}
			
			
			
			
			

			
		}
		if (e.getSource() == btnExclui){
			if (tabela.getSelectedRow() >= 0){  
				JOptionPane.showMessageDialog(null, lista.get(tabela.getSelectedRow()).getDescricao());
				lista.remove(tabela.getSelectedRow());
				modelo.removeRow(tabela.getSelectedRow()); 
				
				
				double total = 0;
				for (int i = 0; i < modelo.getRowCount(); i++) {
					String s = (String)modelo.getValueAt(i, 4);
					s = s.replace(",", ".");
					double t1 = Double.parseDouble(s);
				  total = total + t1; 	
				}
				txtTotal.setText(String.valueOf(total));
			
				}
			
		}
		if (e.getSource() == btnInclui){
			modelo.addRow(new Vector(){});
		}
		

	}

}
