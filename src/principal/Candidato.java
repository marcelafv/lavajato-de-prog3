package principal;

import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

/* @ CANDIDATO
 * Classe com as informações dos candidatos
 */

public class Candidato implements Comparable<Candidato>{

	/**
	 * @param args
	 */
	
	private int index, numCandidato;
	private String nome;
	private Partido partido;
	private Coligacao coligacao;
	private int numVotos;
	private String porcentagemVotos;
	private boolean eleito;
	
	/* Setters e Getters */
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}

	public int getNumCandidato() {
		return numCandidato;
	}

	public String getNome() {
		return nome;
	}
	
	public void setPartido(String s){
		Partido p = Eleicao.getInstance().containsPartido(s);	// Verificação da existência do partido e inclusão (caso não exista)
		p.addCandidato(this);
		this.partido = p;
	}

	public Partido getPartido() {
		return partido;
	}
	
	public void setColigacao(String s){
		Coligacao c = Eleicao.getInstance().containsColigacao(s);	// Verificação da existência da coligação e inclusão (caso não exista)
		c.addPartido(this.partido);
		this.coligacao = c;
	}
	
	public Coligacao getColigacao() {
		return coligacao;
	}
	
	public int getNumVotos() {
		return numVotos;
	}

	public String getPorcentagemVotos() {
		return porcentagemVotos;
	}
	
	/* Verificação se o candidato foi eleito
	 * (a partir da classificação feita em 'evalIndex()' */
	public boolean isEleito(){
		return eleito;
	}
	
	/* Classificação de  um candidato em 'eleito' (true ou false)
	 * a partir da presença ou não dos caracteres '*' e '#' que acompanham o número de sequência
	 * do candidato no arquivo de entrada.
	 */
	private void evalIndex(String s){
		if(s.charAt(0) == '*'){
			this.eleito = true;
			this.index = Integer.parseInt(s.substring(1,5));	// Garante a cópia do index apenas
		}else if(s.charAt(0) == '#'){
			this.eleito = false;
			this.index = Integer.parseInt(s.substring(1,5));	// Garante a cópia do index apenas
		}else{
			this.eleito = false;
			this.index = Integer.parseInt(s);
		}
	}
	
	/* Leitura das informações do candidato e criação do objeto relacionado
	 * a partir do arquivo de entrada
	 */
	public void lerCandidato(Scanner entrada) {
		
		/* Variável temporária para partidoString; partidoString[0] é Partido
		   e partidoString[1] é Coligação  ( divisão feita pelo split() ) */
		String[] partidoString;
		
		NumberFormat f = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
	
		this.evalIndex(entrada.next());				// Classificação (eleito ou não) do candidato      
		this.numCandidato = Integer.parseInt(entrada.next());
		this.nome = entrada.next();			
		partidoString = entrada.next().split("-");	// Divisão de partido e coligação
		this.setPartido(partidoString[0]);
		
		try{
			this.setColigacao(partidoString[1]);	// Caso o candidato pertença a uma coligação
		} catch(ArrayIndexOutOfBoundsException e){	
			this.setColigacao(partidoString[0]);	// Caso o candidato pertença a um partido apenas
		}
		
		try{
			this.numVotos = f.parse(entrada.next()).intValue();
		} catch (ParseException e){}
		
		this.porcentagemVotos = entrada.next();
	}
	
	@Override
	public String toString() {
		if (getColigacao() == null) return getNome()+" ("+getPartido().getNome()+", "+getNumVotos()+" votos)";
		else return getNome()+" ("+getPartido().getNome()+", "+getNumVotos()+" votos)	- Coligação: "+getColigacao().getNome();
	}
	
	/* Comparador por votos */
	@Override
	public int compareTo(Candidato can) {
	        return  can.numVotos - this.numVotos;
	}
	
	/* Verificação de candidatos repetidos por nome */
	public boolean equals(Candidato c){
		if(this.getNome().equals(c.getNome())) return true;
		else return false;		
	}
}