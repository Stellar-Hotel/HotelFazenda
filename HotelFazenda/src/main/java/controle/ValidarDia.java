package controle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ValidarDia {

	public static boolean lerDia(String diaAtual) {
		String diaArquivo;
		FileReader arquivo = null;
		BufferedReader reader = null;

		try {
			arquivo = new FileReader("ValidarDia.txt");
			reader = new BufferedReader(arquivo);
			diaArquivo = reader.readLine();

			if (diaArquivo != null && diaArquivo.equals(diaAtual)) {

				return false;
			} else {

				try (BufferedWriter writer = new BufferedWriter(new FileWriter("ValidarDia.txt"))) {
					writer.write(diaAtual);
				}
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (arquivo != null)
					arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
