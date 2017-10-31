package sv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import sv.Campo;

public class Cadastro {

    @Acao( form = FormListar.class )
    public String listar( FormListar form ) {
        System.out.println( "Entidade " + form.getEntidade() );

        // Essa é a solução RUIM!!!
        // Use o nome da entidade para criar uma instância da classe CadastroXXXX, onde XXXX é o nome da
        // entidade. Essa classe Cadastro deve ter um método listar.
        if( form.getEntidade().equals( "Aluno" ) ) {
            ArrayList<String> nome = new ArrayList<>();

            for( Aluno a : AlunoDAO.getAlunos().values() )
                nome.add( "<a href=\"SistaCad?control=sv.Cadastro&action=exibir&entidade=Aluno&ID=" + a.getID() + "\">"
                        + a.getNome() + "</a>" );

            form.setNome( nome );
            form.setSize( nome.size() );
        }
        return "/listar.jsp";
    }

    @Acao( form = FormExibir.class )
    public String exibir( FormExibir form ) {
        try {
            Class<?> classeDoCadastro = Class.forName( "sv.Cadastro" + form.getEntidade() );
            Controle controle = (Controle) classeDoCadastro.newInstance();
            Object formEntidade = controle.preencheForm( Integer.valueOf( form.getId() ) );

            Map<String, String> campos = new TreeMap<>();

            for( Method m : formEntidade.getClass().getMethods() )
                if( m.isAnnotationPresent( Campo.class ) ) {
                    String label = m.getName().substring( 3 );

                    campos.put( label, chamaGetter( formEntidade, "get" + label ) );
                }

            form.setCampos( campos );

        } catch( Exception e ) {
            e.printStackTrace();
        }

        if( form.getEditar().equals( "true" ) )
            return "/editar.jsp";
        else
            return "/exibir.jsp";

    }

    private String chamaGetter( Object formEntidade, String nomeMetodo ) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method m = formEntidade.getClass().getMethod( nomeMetodo );

        return m.invoke( formEntidade ).toString();
    }

    public Object preencheForm( Class<?> formClass, Map<String, String> param ) throws Exception {
        Object form = formClass.newInstance();

        for( Method m : formClass.getMethods() )
            if( m.getAnnotation( Campo.class ) != null )
                if( m.getName().substring( 0, 3 ).equals( "set" ) ) {
                    String nomeParam = m.getName().substring( 3 ).toLowerCase();
                    String valorParam = param.get( nomeParam );

                    if( m.getAnnotation( Campo.class ).obrigatorio() && (valorParam == null || valorParam.trim().equals( "" )) )
                        throw new CampoNaoPreenchidoException( nomeParam );
                    else
                        m.invoke( form, valorParam );
                } else
                    throw new Exception( "O nome do m�todo anotado por @Campo deve começar por 'set'" );

        return form;
    }
}
