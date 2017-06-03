package api;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SearchRestaurants
 */
@WebServlet("/restaurants")
public class SearchRestaurants extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static DBConnection connection = new MySQLDBConnection();
    //private DBConnection connection = new MongoDBConnection();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRestaurants() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        JSONArray array = new JSONArray();

        if (request.getParameterMap().containsKey("lat") &&
                request.getParameterMap().containsKey("lon")) {
            String term = request.getParameter("term");
            String user_id = "1111";
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lon = Double.parseDouble(request.getParameter("lon"));
            array = connection.searchRestaurants(user_id, lat, lon, term);

        }

        RpcParser.writeOutput(response, array);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
