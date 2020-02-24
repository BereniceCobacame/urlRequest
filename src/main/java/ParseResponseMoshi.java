import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class ParseResponseMoshi {
    private final OkHttpClient client = new OkHttpClient();
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

    public static void main(String... args) throws Exception {
        new ParseResponseMoshi().run();
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://pokeapi.co/api/v2/pokemon/pikachu")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Gist gist = gistJsonAdapter.fromJson(response.body().source());

            System.out.println(gist);

            for (Map.Entry<String, GistFile> entry : gist.abilities.entrySet()) {
              System.out.println(entry.getKey());
               System.out.println(entry.getValue().ability);
            }
        }
    }

    static class Gist {
        Map<String, GistFile> abilities;
    }

    static class GistFile {
        String ability;
        String filename;
        String type;
    }


}
