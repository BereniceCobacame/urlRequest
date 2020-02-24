import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App
{
    OkHttpClient client = new OkHttpClient();

    String url() throws IOException {
        Request request = new Request.Builder().url("https://pokeapi.com/api/v2/pokemon/pikachu/").build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static void main( String[] args ) throws IOException{

        App app = new App();
        String jsons = app.url();
        //System.out.println(jsons);
        //JSONObject obj = new JSONObject(jsons);

        JsonObject jsonObject = new JsonParser().parse(jsons).getAsJsonObject();

        String abilities = jsonObject.getAsJsonObject("abilities").get("abilities").getAsString();
        System.out.println(abilities);

//        JsonArray arr = jsonObject.getAsJsonArray("posts");
//        for (int i = 0; i < arr.size(); i++) {
//            String post_id = arr.get(i).getAsJsonObject().get("post_id").getAsString();
//            System.out.println(post_id);
//        }

//        List<String> list = new ArrayList<String>();
//        JSONArray array = obj.getJSONArray("abilities");
//        for(int i = 0 ; i < array.length() ; i++){
//            list.add(array.getJSONObject(i).getString("ability"));
//        }

/*        JSONObject obj = new JSONObject(jsons);
       // JSONObject obj2 = new JSONObject(jsona);

        JSONArray abilities = obj.getJSONArray("abilities");
        for (int i = 0; i < abilities.length(); i++) {
            String object = abilities.getJSONObject(i).toString();
            System.out.println(object);
        }*/
//        JSONArray flavor = obj2.getJSONArray("flavor_text_entries");
//        for (int i = 0; i < flavor.length(); i++) {
//            String object = flavor.getJSONObject(i).getString("flavor_text");
//            System.out.println(object);
//        }

    }
}
