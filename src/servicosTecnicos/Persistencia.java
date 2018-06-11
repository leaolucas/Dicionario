package servicosTecnicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

	public Persistencia(){		
	}
	
	/**
	 * L� e retorna o todo o conte�do do arquivo
	 * 
	 * @param caminhoArquivo
	 *            O caminho do arquivo a ser lido
	 * @return Uma lista contendo todo o conte�do do arquivo lido, sendo cada
	 *         linha um objeto String da lista
	 */
	public ArrayList<String> lerArquivo(String caminhoArquivo) {
		ArrayList<String> objetos = new ArrayList<String>();
		try {
			File f = new File(caminhoArquivo);
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = new String();

			while ((readLine = b.readLine()) != null) {
				objetos.add(readLine);
			}

			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return objetos;
	}

	/**
	 * Escreve um texto no final do arquivo
	 * 
	 * @param caminhoArquivo
	 *            O caminho do arquivo a ser escrito
	 * @param texto
	 *            O texto a ser escrito no final do arquivo
	 */
	public void escreverArquivo(String caminhoArquivo, String texto) {
		try {
			FileWriter fw = new FileWriter(caminhoArquivo, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			out.println(texto);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Substitui o texto contido em uma linha do arquivo por outro texto
	 * 
	 * @param caminhoArquivo
	 *            O caminho do arquivo a ter o texto substitu�do
	 * @param textoNovo
	 *            O texto a ser escrito no arquivo
	 * @param textoAntigo
	 *            O texto a ser removido do arquivo
	 */
	public void substituirLinhaArquivo(String caminhoArquivo, String textoNovo, String textoAntigo) {
		try {
			Path path = Paths.get(caminhoArquivo);
			List<String> objetos = new ArrayList<>(Files.readAllLines(path, StandardCharsets.ISO_8859_1));

			for (int i = 0; i < objetos.size(); i++) {
				if (objetos.get(i).equals(textoAntigo)) {
					objetos.set(i, textoNovo);
					break;
				}
			}

			Files.write(path, objetos, StandardCharsets.ISO_8859_1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
