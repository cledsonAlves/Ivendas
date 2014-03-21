package BaseDados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Objetos.Cliente;
import Objetos.Pedido;

import com.google.gson.Gson;

public class ManipulaBanco extends Banco {
	private PreparedStatement stmt;
	Statement st;
	// private ResultSet rs;
	Connection conexao;

	// Construtor da classe
	public ManipulaBanco() {
		// Gera a conexão ..
		conexao = super.criaConexao();

	}
	


	// método insere pedido no banco
	public void inserePedido(Pedido pedido) {
		try {
			String query = "INSERT INTO pedido(NUMPED,DATA,VLTOTAL,CODCLI,CODUSUR)values(?,?,?,?,?)";
			// prepara o sql para gravar o pedido
			/**
			 * stmt = conexao.prepareStatement(query);
			 * stmt.setInt(1,pedido.getNumeroPedido());
			 * stmt.setDate(2,pedido.getData()); stmt.setDouble(3,
			 * pedido.getValorTotal());
			 * stmt.setInt(4,pedido.getCodigoCliente()); stmt.setInt(4,
			 * pedido.getCodigoVendedor());
			 **/

			// insere o pedido no banco
			stmt.execute();
			// fecha as conexões abertas ...
			stmt.close();
			conexao.close();
			System.out.println("Pedido inserido no banco");

		} catch (SQLException erro) {
			erro.printStackTrace();

		}
	}
	
	//  Código não é deste programa, somente para pegar a carteira do RCA .. 
	 public void buscaDados(){
		 /* Força de vendas */
			String sql = "SELECT CODCLI,CLIENTE,CGCENT AS CNPJ,CPFCONJUGE AS CPF, pcclient.IEENT, pcclient.ENDERCOB, pcclient.BAIRROCOB,  pcclient.MUNICCOB,"+
					   "pcclient.ESTCOB, pcclient.CEPCOB, pcclient.EMAIL, pcclient.EMAILNFE, pcclient.TELCOM,CPFCONJUGE     from pcclient where codusur1 = 90  order by codcli";
		 
			ArrayList<Cliente> lista = new ArrayList<Cliente>();
		// String sql = "SELECT pcclient.codcli,pcclient.cliente,pcclient.cgcent,pcclient.endercob,pcclient.bairrocob,pcclient.municcob,pcclient.estcob,telcob,telent,Email  FROM PCCLIENT WHERE CODUSUR1 = 90 ";
			try {
				Statement st  = conexao.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()){
					
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getInt(1));
					cliente.setNome(rs.getString(2));
					cliente.setCnpj(rs.getString(3));
					cliente.setCpf(rs.getString(4));
					cliente.setInscricaoEstadual(rs.getString(5));
					cliente.setEndereco(rs.getString(6));
					cliente.setBairro(rs.getString(7));
					cliente.setCidade(rs.getString(8));
					cliente.setEstado(rs.getString(9));
					cliente.setCep(rs.getString(10));
					cliente.setEmail(rs.getString(11));
					cliente.setEmailNfe(rs.getString(12));
					cliente.setTelComercial(rs.getString(13));
					cliente.setCelular(rs.getString(14));
					lista.add(cliente);
			    
					/**System.out.println("INSERT INTO FV_CLIENTE  VALUES (" + rs.getString(1)+",'" + rs.getString(2)+"','" +rs.getString(3)+"','" + rs.getString(4)+"','"+rs.getString(5)
							+"','"+rs.getString(6)+"','"+rs.getString(7)+"','"+rs.getString(8)+"','"+rs.getString(9)+"','"+rs.getString(10)+"','"+rs.getString(11)
							+"','"+rs.getString(12)+"','"+rs.getString(13)+"','"+rs.getString(14)+"');");**/
				}
				
				Gson gson = new Gson();
				String listaJSONString = gson.toJson(lista);
				
				File f = new File("C:/Users/c.santos/Desktop/Clientes.txt");
				FileWriter read = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(read);
				
				bw.write(listaJSONString);
				bw.close();
				read.close();
				
				
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(lista.get(i).getEmailNfe());
				}				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	// metodo busca dados  do cliente no Whinthor....
	public String[] buscaDados(String codigo) {
		String sql = " SELECT PCCLIENT.enderent," +
		" PCCLIENT.bairroent,  (SELECT NOMECIDADE FROM PCCIDADE WHERE CODCIDADE = PCCLIENT.codcidade) AS CIDADE," +
		" PCCLIENT.municent,PCCLIENT.estent,NVL(PCCLIENT.email,'helpdesk@kelma.com.br'),"+
		" decode(NVL(PCCLIENT.emailnfe,'helpdesk@kelma.com.br'),'ti.kelma@gmail.com',NVL(PCCLIENT.email,'helpdesk@kelma.com.br'),NVL(PCCLIENT.emailnfe,'helpdesk@kelma.com.br')) FROM PCCLIENT WHERE CODCLI= " + codigo;
		
		String[] dados = new String[7];
			
		try {
			
			Statement st  = conexao.createStatement();
			ResultSet rs  = st.executeQuery(sql);
			while (rs.next()){
			dados[0] = rs.getString(1);	
			dados[1] = rs.getString(2);	
			dados[2] = rs.getString(3);	
			dados[3] = rs.getString(4);	
			dados[4] = rs.getString(5);	
			dados[5] = rs.getString(6);	
			dados[6] = rs.getString(7);	
		
			}
			rs.close();
			st.close();
			conexao.close();
			return dados;
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void main(String[] args) {
		ManipulaBanco mb = new ManipulaBanco();
		mb.buscaDados();
	}

	

}













