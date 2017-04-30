package jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import jdbc.dao.AlunoDAO;
import com.jdriven.ng2boot.Aluno;

// imports aqui (CTRL+SHIFT+O)

public class TestaBancoDados {
	private static Scanner teclado;

	public static void main(String[] args) throws SQLException {
		int opt=-1;
		int search;
	    teclado = new Scanner(System.in);
	    AlunoDAO dao = new AlunoDAO();
	    
	    
	    while(opt!=0){
		    Aluno aluno = new Aluno(-1,"","");
	    	
		    System.out.println("Selecione:");
		    System.out.println("1 - Gravar");
		    System.out.println("2 - Listar Todos Alunos");
		    System.out.println("3 - Pesquisar Aluno por Id");
		    System.out.println("4 - Excluir Aluno por Id");
		    System.out.println("5 - Alterar Aluno");
		    System.out.println("0 - Sair");
	    	
		    opt=teclado.nextInt();
		    
		    if(opt==1){
		    	System.out.println("Digite o id:");
			    aluno.setId(teclado.nextInt());
		    	System.out.println("Digite o nome:");
			    aluno.setName(teclado.next());
			    System.out.println("Digite o Gender:");
			    aluno.setGender(teclado.next());
			    //grave nessa conexão!!!
			    dao.adiciona(aluno);
			    System.out.println("Gravado!");
			    
			    //contato.setNome("Caelum");
			    //contato.setEmail("contato@caelum.com.br");
			    //contato.setEndereco("R. Vergueiro 3185 cj57");
			    
		    }
		    
		    
		    else if(opt==2){
			    List<Aluno> alunos = dao.getLista();
			
			    for (Aluno loopAluno : alunos) {
			    	System.out.println("Id: " + Integer.toString(loopAluno.getId()));
				    System.out.println("Nome: " + loopAluno.getName());
				    System.out.println("Gender: " + loopAluno.getGender() + "\n");
			    }
		    }
		    
		    else if(opt==3){
		    	System.out.println("Digite o id a ser pesquisado:");
			    search=teclado.nextInt();
			    aluno=dao.getAluno(search);
			    System.out.println("Id: " + aluno.getId());
			    System.out.println("Name: " + aluno.getName());
			    System.out.println("Gender: " + aluno.getGender() + "\n");
			    
		    }
		    
		    
		    else if(opt==4){
	
		    	System.out.println("Digite o id a ser excluido:");
			    search=teclado.nextInt();
			    dao.excluir(search);
			    System.out.println("Registro excluido");
			    
		    }
		    
		    else if(opt==5){
	
		    	System.out.println("Digite o id para alterar:");
			    aluno.setId(teclado.nextInt());
		    	System.out.println("Digite o nome:");
			    aluno.setName(teclado.next());
			    System.out.println("Digite o gender:");
			    aluno.setGender(teclado.next());
			    dao.altera(aluno);
			    System.out.println("Registro alterado");
		    }
		    
		    else if(opt==0){
		    	System.out.println("Tchau cara, até mais, vou ficar esperando você me executar de novo :'(");
		    }
		    
		    else{
		    	System.out.println("TROLLOU! HAHAHAHAHAHAHA");
		    }
		}
	}
}