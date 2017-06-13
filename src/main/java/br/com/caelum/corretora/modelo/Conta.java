package br.com.caelum.corretora.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	private Long numero;
	
	private BigDecimal saldo;
	
	private String titular;
	
	@Transient
	public static Set<Long> numeros = new HashSet<Long>();

	public Conta(Usuario usuario, String titular, Long numero, BigDecimal saldo) {
		validaUsuario(usuario);
		validaTitular(titular);
		validaSaldo(saldo);
		this.usuario = usuario;
		this.titular = titular;
		this.numero = (long) (1000 + Math.random() * 8999L);
		while (numeros.contains(numero)) {
			numero = (long) (1000 + Math.random() * 8999L);
		}
		System.out.println("----->NUMERO " + this.numero);
		this.saldo = saldo;
		cadastra();
	}

	public Conta() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
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
		numero = (long) (1000 + Math.random() * 8999L);
		while (numeros.contains(this.numero)) {
			numero = (long) (1000 + Math.random() * 8999L);
		}
		this.numero = numero;
		cadastra();
	}
	
	public BigDecimal getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	private boolean saca(BigDecimal valor) {
		if(valor.compareTo(saldo) == 1) {
			throw new IllegalArgumentException("saque inválido - valor não pode ser maior do que seu saldo em conta");
		} else {
			saldo.subtract(valor);
			return true;
		}
	}

	public boolean deposita(BigDecimal valor) {
		if (valor.compareTo(new BigDecimal(0.0)) == 1){
			saldo.add(valor);
			return true;
		} else {
			throw new IllegalArgumentException("depósito inválido - valor não pode ser negativo");
		}
	}

	public Aplicacao investe(Investimento investimento, BigDecimal valor) {
		saca(valor);
		Aplicacao aplicacao = new Aplicacao(this, investimento, LocalDate.now(), valor);
		return aplicacao;
	}
	
	private void validaSaldo(BigDecimal saldoAValidar) {
		if (saldoAValidar.equals(null))
			throw new IllegalArgumentException("Saldo não pode ser vazio");
		if (saldoAValidar.compareTo(new BigDecimal(0.0)) == -1)
			throw new IllegalArgumentException("Saldo deve ser positivo");
	}

	private void validaTitular(String titularAValidar) {
		if(titularAValidar == null || titularAValidar.isEmpty()) 
			throw new IllegalArgumentException("titular não pode ser nulo ou vazio");
	}
	
	private void validaUsuario(Usuario usuarioAValidar) {
		if (usuarioAValidar == null)
			throw new IllegalArgumentException("usuario não pode ser nulo");		
	}

	private boolean cadastra() {
		return numeros.add(this.numero);
	}
	
	public static Set<Long> getNumeros() {
		return numeros;
	}
	@Override
	public String toString() {
		return numero.toString();
	}
}
