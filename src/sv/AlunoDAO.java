package sv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AlunoDAO {

    static private Map<Integer, Aluno> alunos = leAlunos( "C:\\Users\\ejcmpc4\\Desktop\\alunos.txt" );

    public static void salva(int a, Aluno aluno ) {
        alunos.put(a, aluno);
    }

    public static Aluno recupera( int ID ) {
        return alunos.get( ID );
    }
    
    public static Map<Integer, Aluno> getAlunos() {
        return alunos;
    }

    static Map<Integer, Aluno> leAlunos( String arquivo ) {
        Map<Integer, Aluno> map = new TreeMap<>();
        List<String> linhas;
        try {
            linhas = Files.readAllLines( Paths.get( arquivo ), Charset.forName( "UTF8" ) );

            for( String linha : linhas ) {
                String w[] = linha.split( "[|]" );
                Aluno aluno = new Aluno();

                aluno.setID( w[0] );
                aluno.setNome( w[1] );
                aluno.setCelular( w[2] );
                aluno.setEmail( w[3] );

                map.put( aluno.getID(), aluno );
            }
        } catch( IOException e ) {
            e.printStackTrace();
        }

        return map;
    }

}
