package leitor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Leitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
            // Parâmetros de leitura
			Scanner entrada = new Scanner(new BufferedReader(new FileReader("vitoria2016.csv")));
            entrada.useDelimiter("[;%*#]");
            String lixo;
            
            TreeSet<Candidato> listaCandidatos = new TreeSet<Candidato>();
            
            // Leitura do arquivo
            lixo = entrada.nextLine();
            //System.out.println(lixo); // Cabeçalho
            
            while(entrada.hasNext()) {
            	Candidato c = new Candidato();
            	c.lerCandidato(entrada);
                lixo = entrada.nextLine(); // Desconsidera a quebra de linha
                listaCandidatos.add(c);
                //System.out.println(c);
            }
            
            // Impressão da lista de Candidatos
            for (Candidato aux : listaCandidatos) {
            	System.out.println(aux);
            }

        } catch (FileNotFoundException e){
              System.out.println("Arquivo não encontrado!");
              e.printStackTrace();
        }
		
    }
 
    /*public static void imprimirCatalogo(Lista lista, int tamanho) {
        Node aux = new Node();
        aux = lista.primeiro;
 
        //for (int i = 0; i < tamanho ; i++)
        while (aux != null)
            {
                if (aux.item instanceof Aluno) {
                    ((Aluno)aux.item).imprimir();
                    //aux = aux.next;
                }
                else if (aux.item instanceof Professor) {
                    ((Professor)aux.item).imprimir();
                    //aux = aux.next;
                }
                else System.out.println("Tipo do objeto não identificado.");
                aux = aux.next;
            }
    }

	}*/

}