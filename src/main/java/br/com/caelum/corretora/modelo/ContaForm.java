package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ContaForm {
	
	@NotBlank
	private String titular;
	
	private Long numero;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal saldo;
	
	public ContaForm() {};
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public Conta build(Usuario usuarioLogado) {
		System.out.println("build conta");
		Conta conta = new Conta();
		conta.setUsuario(usuarioLogado);
		conta.setTitular(titular);
		conta.setNumero(numero);
		conta.setSaldo(saldo);
		return conta;
	}
}
