import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.Acao;
import sv.CampoNaoPreenchidoException;
import sv.Cadastro;

/**
 * Servlet implementation class SistaCad
 */
@WebServlet( "/SistaCad" )
public class SistaCad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGetPost( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGetPost( request, response );
    }

    protected void doGetPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String control = request.getParameter( "control" );
        String action = request.getParameter( "action" );

        if( isValid( control ) && isValid( action ) ) {
            String proximaPagina;
            try {
                proximaPagina = doAction( request, control, action );
                RequestDispatcher rd = getServletContext().getRequestDispatcher( proximaPagina );

                rd.forward( request, response );
            } catch( Exception e ) {
                e.printStackTrace();
            }

        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher( "/index.html" );
            rd.forward( request, response );
        }
    }

    private boolean isValid( String name ) {
        return name != null && !name.trim().equals( "" );
    }

    private String doAction( HttpServletRequest request, String control, String action ) throws Exception {
        Class<?> controlClass;
        try {
            controlClass = Class.forName( control );
            Map<String, String> params = filtraParams( request );

            Cadastro controlObject = (Cadastro) controlClass.newInstance();

            System.out.println( "Objeto criado: " + controlObject.getClass().getName() );

            Method metodos[] = controlClass.getDeclaredMethods();

            for( Method m : metodos )
                if( m.getName().equals( action ) )
                    if( m.isAnnotationPresent( Acao.class ) ) {
                        Class<?> formClass = m.getAnnotation( Acao.class ).form();
                        String proximaPagina = "";

                        if( formClass != Object.class ) {
                            Object form = controlObject.preencheForm( formClass, params );

                            proximaPagina = m.invoke( controlObject, form ).toString();
                            request.setAttribute( "form", form );
                        } else {
                            proximaPagina = m.invoke( controlObject ).toString();
                        }

                        return proximaPagina;
                    } else {
                        System.out.println( "Passou Aqui" );
                        return "/AcessoNegado.jsp";
                    }

        } catch( CampoNaoPreenchidoException e ) {
            e.printStackTrace();
            request.setAttribute( "erro", e );
            return "/erro.jsp";
        } catch( Exception e ) {
            e.printStackTrace();
            request.setAttribute( "erro", e );
            return "/erro.jsp";
        }

        return "";
    }

    private Map<String, String> filtraParams( HttpServletRequest request ) {
        TreeMap<String, String> map = new TreeMap<>();

        for( String param : request.getParameterMap().keySet() )
            if( !param.equals( "control" ) && !param.equals( "action" ) )
                map.put( param.toLowerCase(), request.getParameter( param ) );

        return map;
    }

}
