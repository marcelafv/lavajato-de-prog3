package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/* @ LEITOR
 * Classe com os métodos de leitura do arquivo de entrada e impressão dos resultados.
 * Classe principal: contém a main do programa.
 */

public class Leitor {

	/**
	 * @param args
	 */
	
	/* Impressão de uma lista de candidatos */
	public static void ImprimeCandidatos(List<Candidato> l){
		int i = 1;
	    for (Candidato aux : l) {
	    	System.out.println(i+" - "+aux);
	    	i++;
	    }
	}
	
	/* Impressão de N (número de vagas) candidatos de uma lista */
	public static void ImprimeComRanking(List<Candidato> l){
		int vagas = Eleicao.getInstance().getNumVagas();	//  Cálculo do número de vagas
	    for (Candidato aux : l) {
	    	if(vagas <=0) break;
	    	System.out.println(aux.getIndex()+" - "+aux);
	    	vagas--;
	    }
	}
	
	/* Impressão do número de vagas */
	public static void ImprimeNVagas(){
	    System.out.println("Número de vagas: " + Eleicao.getInstance().getNumVagas());
	}
	
	/* Impressão de uma lista de coligações */	
	public static void ImprimeColigacoes(List<Coligacao> l){
		int i = 1;
	    for (Coligacao aux : l) {
	    	System.out.println(i+" - Coligação: "+aux);
	    	i++;
	    }
	}
	
	/* Impressão de uma lista de partidos */
	public static void ImprimePartidos(List<Partido> l){
		int i = 1;
	    for (Partido aux : l) {
	    	System.out.println(i+" - "+aux);
	    	i++;
	    }
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Eleicao eleicao = Eleicao.getInstance();   // Criação de um Singleton para organizar os objetos e métodos do programa 
		
		try {
            
			/* Parâmetros de leitura */

			Scanner entrada = new Scanner(new BufferedReader(new FileReader(args[0])));
            entrada.useDelimiter("[;%]");
            String lixo;
            
            /* Leitura do arquivo */
            
            lixo = entrada.nextLine();  // Cabeçalho
            //System.out.println(lixo);
            
            while(entrada.hasNext()) {
            	Candidato c = new Candidato();
            	c.lerCandidato(entrada);	// Leitura de todas as informações de um candidato
                entrada.nextLine();			// Desconsidera a quebra de linha
                eleicao.addCandidato(c);
                //System.out.println(c);
            }
            
            //  ImprimeCandidatos(eleicao.getListaCandidatos()); // Impressão da lista de Candidatos
            
            
            /* Manipulação das informações e resultados obtidos */
            
               ImprimeNVagas();		// Impressão do número de vagas
            
               System.out.println("\nVereadores eleitos:");		
               ImprimeCandidatos(eleicao.getEleitos());			// Impressão dos Vereadores Eleitos
               
               System.out.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
               ImprimeComRanking(eleicao.getMaisVotados());		// Impressão dos candidatos mais votados
               
               System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
               ImprimeComRanking(eleicao.getPrejudicados());	// Impressão dos candidatos mais votados e não-eleitos
               
               System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
               ImprimeComRanking(eleicao.getBeneficiados());	// Impressão dos candidatos eleitos pelo sistema vigente
               
               System.out.println("\nVotação (nominal) das coligações e número de candidatos eleitos:");
               ImprimeColigacoes(eleicao.getColigacoes());		// Impressão das coligações
               
               System.out.println("\nVotação (nominal) dos partidos e número de candidatos eleitos:");
               ImprimePartidos(eleicao.getPartidos());			// Impressão dos partidos
         
               System.out.println("\nTotal de votos nominais: " + eleicao.totalVotosNominais());	// Impressão do total de votos
               
        } catch (FileNotFoundException e){
              System.out.println("Arquivo não encontrado!");
              e.printStackTrace();
        }
		
    }
 

}
