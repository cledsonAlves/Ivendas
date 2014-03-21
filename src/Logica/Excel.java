package Logica;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import Objetos.Item;

public class Excel {

	// carrega a planilha excell
	public Vector carregaCabecalho(String arquivo) throws BiffException,
			IOException {
		/**
		 * Carrega a planilha
		 */
		WorkbookSettings conf = new WorkbookSettings();
		conf.setEncoding("ISO-8859-1");

		Workbook workbook = Workbook.getWorkbook(new File(arquivo), conf);

		/**
		 * Aqui é feito o controle de qual aba do xls será realiza a leitura dos
		 * dados
		 */
		Sheet sheet = workbook.getSheet(0);

		/**
		 * Numero de linhas com dados do xls
		 */
		int linhas = sheet.getRows();

		// cabeçalho ....
		String rca = sheet.getCell(2, 1).getContents();
		String prazo = sheet.getCell(1, 7).getContents();
		String cliente = sheet.getCell(2, 3).getContents();
		String data = sheet.getCell(12, 2).getContents();
		String vlTotal = sheet.getCell(12, 7).getContents();
		String cidade = sheet.getCell(1, 5).getContents();
		String endereco = sheet.getCell(1, 4).getContents();

		Vector<String> vetor = new Vector<String>();

		vetor.add(rca);
		vetor.add(cliente);
		vetor.add(prazo);
		vetor.add(data);
		vetor.add(vlTotal);
		vetor.add(cidade);
		vetor.add(endereco);

		// feecha o arquivo
		workbook.close();

		return vetor;

	}

	public Vector<Item> carregaItens(String arquivo) {
		Vector<Item> vt = new Vector<Item>();
		WorkbookSettings conf = new WorkbookSettings();
		conf.setEncoding("ISO-8859-1");

		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(arquivo), conf);
			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();
             
			// coluna 1
			for (int i = 10; i < linhas; i++) {

				Item it = new Item();
				it.setCodigo(sheet.getCell(0, i).getContents());
				it.setDescricao(sheet.getCell(1, i).getContents());
				it.setQtd(sheet.getCell(2, i).getContents());
				it.setVlUnitario(sheet.getCell(3, i).getContents());
				it.setVlTotal(sheet.getCell(4, i).getContents());

				if (!it.getVlTotal().equals("0,00")) {
					if (!it.getCodigo().equals("Cód.")) {
						if (!it.getCodigo().equals("")) {
							if (!it.getCodigo().equals("Cod")) {
								if (!it.getDescricao().equals("")) {
									if (!it.getCodigo().startsWith("*")) {
										if (!it.getQtd().equals("")) {
								vt.add(it);
							}
						}
					}
				}
					}}
				}
			}
			
			//  coluna  2
			for (int i = 10; i < linhas; i++) {

				Item it = new Item();
				it.setCodigo(sheet.getCell(5, i).getContents());
				it.setDescricao(sheet.getCell(6, i).getContents());
				it.setQtd(sheet.getCell(7, i).getContents());
				it.setVlUnitario(sheet.getCell(8, i).getContents());
				it.setVlTotal(sheet.getCell(9, i).getContents());

				if (!it.getVlTotal().equals("0,00")) {
					if (!it.getCodigo().equals("Cód.")) {
						if (!it.getCodigo().equals("")) {
							if (!it.getCodigo().equals("Cod")) {
								if (!it.getDescricao().equals("")) {
									if (!it.getCodigo().startsWith("*")) {
										if (!it.getQtd().equals("")) {
								vt.add(it);
							}
						}
					}
				}
					}}
			  }
			}
			// coluna 3 
			for (int i = 10; i < linhas; i++) {

				Item it = new Item();
				it.setCodigo(sheet.getCell(10, i).getContents());
				it.setDescricao(sheet.getCell(11, i).getContents());
				it.setQtd(sheet.getCell(12, i).getContents());
				it.setVlUnitario(sheet.getCell(13, i).getContents());
				it.setVlTotal(sheet.getCell(14, i).getContents());

				if (!it.getVlTotal().equals("0,00")) {
					if (!it.getCodigo().equals("Cód.")) {
						if (!it.getCodigo().equals("")) {
							if (!it.getCodigo().equals("Cod")) {
								if (!it.getDescricao().equals("")) {
									if (!it.getCodigo().startsWith("*")) {
										if (!it.getQtd().equals("")) {
								vt.add(it);
							}
						}
					}
				}}}
				}
			}
			
			// coluna 4 
			for (int i = 10; i < linhas; i++) {

				Item it = new Item();
				it.setCodigo(sheet.getCell(15, i).getContents());
				it.setDescricao(sheet.getCell(16, i).getContents());
				it.setQtd(sheet.getCell(17, i).getContents());
				it.setVlUnitario(sheet.getCell(18, i).getContents());
				it.setVlTotal(sheet.getCell(19, i).getContents());

				if (!it.getVlTotal().equals("0,00")) {
					if (!it.getCodigo().equals("Cód.")) {
						if (!it.getCodigo().equals("")) {
							if (!it.getCodigo().equals("Cod")) {
								if (!it.getDescricao().equals("")) {
									if (!it.getCodigo().startsWith("*")) {
										if (!it.getQtd().equals("")) {
								vt.add(it);
							}
						}
					}
				}}}
				}
			}
			
			return vt;

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}