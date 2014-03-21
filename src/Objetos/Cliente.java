package Objetos;

public class Cliente  extends Pessoa {
     private String cpf;
     private String cnpj;
     private String inscricaoEstadual;
 	private String email;
 	private String emailNfe;
 	
 	
 	
 	
	public String getEmailNfe() {
		return emailNfe;
	}
	public void setEmailNfe(String emailNfe) {
		this.emailNfe = emailNfe;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
     

     
}
