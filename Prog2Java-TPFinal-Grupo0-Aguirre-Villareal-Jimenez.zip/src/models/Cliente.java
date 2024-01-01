package models;

public class Cliente extends Usuario {
	private double saldo;

	public Cliente(int id, String nombreUsuario, String contraseniaUsuario) {
		super(id, nombreUsuario, contraseniaUsuario);
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public double descontarSaldo(double descontar) {
		return saldo -= descontar;
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
	public void transferirSaldo(double monto, Cliente destinatario) {
		if (monto <= this.saldo) {
			this.saldo -= monto;
			destinatario.agregarSaldo(monto);
		}else {
			System.out.println("Saldo insuficiente para transferir");
		}
	}
}
