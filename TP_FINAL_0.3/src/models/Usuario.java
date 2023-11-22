package models;
public class Usuario {
	private String nombreUsuario;
	private String contraseniaUsuario;
	private char tipoUsuario;

//	agregue saldo
	private double saldo;
	
	public Usuario(String nombreUsuario, String contraseniaUsuario, char tipoUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseniaUsuario = contraseniaUsuario;
		this.tipoUsuario = tipoUsuario;
		this.saldo = 0;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setContraseniaUsuario(String contraseniaUsuario) {
		this.contraseniaUsuario = contraseniaUsuario;
	}

	public char getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(char tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getContraseniaUsuario() {
		//no se deberia poder devolver la contrasenia
		return contraseniaUsuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public double descontarSaldo(double descontar) {
		return saldo -= descontar;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void agregarSaldo(double monto) {
		this.saldo += monto;
	}
	public void retirarSaldo(double monto) {
		if(monto <= this.saldo) {
			this.saldo -= monto;
		}else {
			System.out.println("Saldo Insuficiente");
		}
	}
	public void transferirSaldo(double monto,Usuario destinatario) {
		if (monto <= this.saldo) {
			this.saldo -= monto;
			destinatario.agregarSaldo(monto);
		}else {
			System.out.println("Saldo insuficiente para transferir");
		}
	}
	
	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", contraseniaUsuario=" + contraseniaUsuario
				+ ", tipoUsuario=" + tipoUsuario + ", saldo=" + this.saldo + "]";
	}
}
  
