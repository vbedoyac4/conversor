import com.google.gson.Gson;

import java.net.SocketOption;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Dictionary;
import java.util.Map;

public class ConsultarTasa {
    public static double buscarTasadeCambio(String de, String a){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/dd79ef9af111868de4417fd3/pair/"+ de +"/" + a);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(url).build();

        try{
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            RespuestaConsultaTasa respuesta = new Gson().fromJson(response.body(), RespuestaConsultaTasa.class);
            return respuesta.conversion_rate;
        } catch (Exception e){
            throw new RuntimeException("Error obteniendo la tasa de conversion: " + e.getMessage(), e);
        }
    }
}
