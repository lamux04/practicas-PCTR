public class cuentaCorriente {
    // Atributos de la cuenta corriente
    private String numeroCuenta;
    private double saldo;

    // Constructor que inicializa la cuenta con un número y un saldo inicial
    public cuentaCorriente(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Método para realizar un depósito en la cuenta
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Depósito realizado: " + cantidad);
            System.out.println("Nuevo saldo: " + saldo);
        } else {
            System.out.println("La cantidad a depositar debe ser positiva.");
        }
    }

    // Método para realizar un reintegro (retiro) de la cuenta
    public void retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            System.out.println("Reintegro realizado: " + cantidad);
            System.out.println("Nuevo saldo: " + saldo);
        } else if (cantidad > saldo) {
            System.out.println("Fondos insuficientes para realizar el reintegro.");
        } else {
            System.out.println("La cantidad a retirar debe ser positiva.");
        }
    }

    // Método para consultar el saldo actual
    public double getSaldo() {
        return saldo;
    }

    // Método para obtener el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Método para mostrar información de la cuenta
    public void mostrarInformacion() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo actual: " + saldo);
    }
}
