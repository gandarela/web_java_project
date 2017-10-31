package sv;
import java.io.IOException;


public class CadastroAluno extends Cadastro implements Controle {
        
    @Acao( form = FormAluno.class )
    public String salvar( FormAluno form ) throws IOException {
    	
    	
    	
    	System.out.println(form.getID());
    	
    	Aluno aluno = new Aluno();
    	
    	aluno.setID( "" + form.getID() );
    	aluno.setCelular( form.getCelular() );
    	aluno.setEmail( form.getEmail() );
    	aluno.setNome( form.getNome() );
    	
    	AlunoDAO.salva(form.getID(), aluno);
    	
    	System.out.println(aluno);
    	
    	
        System.out.println( "SALVOU" );
        
        return "/ok.jsp";
    }

    @Override
    public Object preencheForm( int id ) {
        FormAluno form = new FormAluno();
        Aluno aluno = AlunoDAO.recupera( id );
        
        form.setID( "" + aluno.getID() );
        form.setCelular( aluno.getCelular() );
        form.setEmail( aluno.getEmail() );
        form.setNome( aluno.getNome() );
        
        return form;
    }
}
