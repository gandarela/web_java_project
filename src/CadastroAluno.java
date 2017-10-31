import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sv.*;

public class CadastroAluno extends Controle {

    static public Map<Integer, FormAluno> alunos = CadastroAluno.leAlunos( "C:\\Users\\ejcmpc4\\Desktop\\alunos.txt" );

    static Map<Integer, FormAluno> leAlunos( String arquivo ) {
        int id = 1;
        Map<Integer, FormAluno> map = new TreeMap<>();
        List<String> linhas;
        try {
            linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );
           
            for( String linha : linhas ) {
                String w[] = linha.split( "[|]" );
                FormAluno form = new FormAluno();

                form.setNome( w[0] );
                form.setCelular( w[1] );
                form.setEmail( w[2] );

                map.put( id++, form );
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }

        return map;
    }

    @Acao
    public String listar() {
        return "/listar.jsp";
    }

    @Acao( form = FormAluno.class )
    public String incluir( FormAluno form ) {
        return "/ok.jsp";
    }

    public static void main( String[] args ) throws IOException {
        System.out.println( leAlunos( "C:\\Users\\ejcmpc4\\Desktop\\alunos.txt" ) );
    }
}
