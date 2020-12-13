package cliente;

import java.text.Normalizer;
import java.util.Arrays;

public class Teste {

    public static void main(String[] args) {
        System.out.println(removerAcentos("Cristavão Colombo"));
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
