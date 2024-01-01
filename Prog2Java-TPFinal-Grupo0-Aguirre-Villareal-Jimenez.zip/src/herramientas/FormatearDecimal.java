package herramientas;

import java.text.DecimalFormat;

public class FormatearDecimal {
    public static double formatearConTresDecimales(double numero) {
        DecimalFormat formato = new DecimalFormat("#.###");
        String numeroFormateado = formato.format(numero);
        return Double.parseDouble(numeroFormateado);
    }
}
