import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorService {

    public static double converterMoeda(String from, String to, double amount) {
        try {
            String urlStr = "https://open.er-api.com/v6/latest/" + from;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String json = response.toString();
            String target = "\"" + to + "\":";
            int index = json.indexOf(target);
            if (index == -1) {
                System.out.println("Moeda destino não encontrada");
                return -1;
            }
            int start = index + target.length();
            int end = json.indexOf(",", start);
            if (end == -1) end = json.indexOf("}", start);
            String rateStr = json.substring(start, end).trim();

            double rate = Double.parseDouble(rateStr);

            return amount * rate;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
