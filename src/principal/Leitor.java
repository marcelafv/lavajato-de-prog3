package principal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Leitor {

	/**
	 * @param args
	 */
	
	public static void ImprimeCandidatos(List<Candidato> l){
		int i = 1;
	    for (Candidato aux : l) {
	    	System.out.println(i+" - "+aux);
	    	i++;
	    }
	}
	
	public static void ImprimeComRanking(List<Candidato> l){
		int vagas = Eleicao.getInstance().getNumVagas();
	    for (Candidato aux : l) {
	    	if(vagas <=0) break;
	    	System.out.println(aux.getIndex()+" - "+aux);
	    	vagas--;
	    }
	}
	
	public static void ImprimeNVagas(){
	    System.out.println("Número de vagas: " + Eleicao.getInstance().getNumVagas());
	}
		
	public static void ImprimeColigacoes(List<Coligacao> l){
		int i = 1;
	    for (Coligacao aux : l) {
	    	System.out.println(i+" - "+aux);
	    	i++;
	    }
	}
	
	public static void ImprimePartidos(List<Partido> l){
		int i = 1;
	    for (Partido aux : l) {
	    	System.out.println(i+" - "+aux);
	    	i++;
	    }
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Eleicao eleicao = Eleicao.getInstance();
		
		try {
            // Parâmetros de leitura
			Scanner entrada = new Scanner(new BufferedReader(new FileReader("vitoria2016.csv")));
            entrada.useDelimiter("[;%]");
            String lixo;
            
            // Leitura do arquivo
            lixo = entrada.nextLine();
            //System.out.println(lixo); // Cabeçalho
            
            while(entrada.hasNext()) {
            	Candidato c = new Candidato();
            	c.lerCandidato(entrada);
                entrada.nextLine(); // Desconsidera a quebra de linha
                eleicao.addCandidato(c);
                //System.out.println(c);
            }
            
           
            // Impressão da lista de Candidatos
             //  ImprimeCandidatos(eleicao.getListaCandidatos());
            // Impressão do número de vagas
               ImprimeNVagas();
            // Impressão dos Vereadores Eleitos
               System.out.println("\nVereadores eleitos:");
               ImprimeCandidatos(eleicao.getEleitos());
               
               System.out.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");
               ImprimeComRanking(eleicao.getMaisVotados());
               
               System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
               ImprimeComRanking(eleicao.getPrejudicados());
               
               System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
               ImprimeComRanking(eleicao.getBeneficiados());
               
               System.out.println("\nVotação (nominal) das coligações e número de candidatos eleitos:");
               ImprimeColigacoes(eleicao.getColigacoes());
               
               System.out.println("\nVotação (nominal) dos partidos e número de candidatos eleitos:");
               ImprimePartidos(eleicao.getPartidos());
         
       
               
        } catch (FileNotFoundException e){
              System.out.println("Arquivo não encontrado!");
              e.printStackTrace();
        }
		
    }
 

}
