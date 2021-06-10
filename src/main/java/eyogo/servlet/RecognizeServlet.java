package eyogo.servlet;

import eyogo.business.Dish;
import eyogo.business.DishCounter;
import eyogo.jdbc.DishDAO;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class RecognizeServlet extends HttpServlet {

    private static Map<String, ArrayList<DishCounter>> terminals = new HashMap<>();
    private static DishDAO dishDAO = new DishDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            request.getRequestDispatcher("/recognize.jsp").forward(request, response);
            String sessionID = request.getSession().getId();
            if (!terminals.containsKey(sessionID)) {
                terminals.put(sessionID, new ArrayList<>());
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String sessionID = request.getSession().getId();
        String base64image = request.getParameter("test");

        String imageDataBytes = base64image.substring(base64image.indexOf(",")+1);
        byte[] decoded = Base64.getDecoder().decode(imageDataBytes);
        String imageName = sessionID + ".jpg";
        OutputStream os = new FileOutputStream(imageName);
        os.write(decoded);

        HttpPost httpPost = new HttpPost("http://localhost:5000/");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("imageName", imageName));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse pythonResponse = httpClient.execute(httpPost)){

            result = EntityUtils.toString(pythonResponse.getEntity());
        }
        Integer recognizedDishCode = Integer.parseInt(result)+1;


        ArrayList<DishCounter> currentDishes = terminals.get(sessionID);
        if (recognizedDishCode != 6) {
            Dish dish = dishDAO.read(recognizedDishCode);
            boolean found = false;
            for (int i = 0; i < currentDishes.size(); i++) {
                DishCounter dishCounterExample = currentDishes.get(i);
                if (dishCounterExample.getDish().equals(dish)) {
                    dishCounterExample.setCount(dishCounterExample.getCount() + 1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                currentDishes.add(new DishCounter(dish, 1));
            }
        }
        request.setAttribute("dishes", terminals.get(sessionID));

        request.getRequestDispatcher("/recognize.jsp").forward(request, response);
    }

    public static Map<String, ArrayList<DishCounter>> getTerminals() {
        return terminals;
    }

    private Integer getRecognizedDishCode() throws IOException {
        String value = "E:\\7semestr\\recognize.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python", value);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        String result = readPythonInputStream(process.getInputStream());
        return Integer.valueOf(result);
    }

    private String readPythonInputStream(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String res = br.readLine();
        br.close();
        return res;
    }
}
