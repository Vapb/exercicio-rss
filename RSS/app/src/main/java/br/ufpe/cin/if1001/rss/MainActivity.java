package br.ufpe.cin.if1001.rss;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainActivity extends Activity {

    private String RSS_FEED;

    //OUTROS LINKS PARA TESTAR...
    //http://rss.cnn.com/rss/edition.rss
    //http://pox.globo.com/rss/g1/brasil/
    //http://pox.globo.com/rss/g1/ciencia-e-saude/
    //http://pox.globo.com/rss/g1/tecnologia/

    //use ListView ao invés de TextView - deixe o atributo com o mesmo nome
    private ListView conteudoRSS;
    private ArrayAdapter<ItemRSS> adapterRSS;
    private MyAdapter beloAdapter;
    SharedPreferences sharedPreferences;
    private static String Rss = "rssfeed";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //use ListView ao invés de TextView - deixe o ID no layout XML com o mesmo nome conteudoRSS
        //isso vai exigir o processamento do XML baixado da internet usando o ParserRSS
        conteudoRSS = (ListView) findViewById(R.id.conteudoRSS);

        beloAdapter = new MyAdapter(this);

        // Botar pegar preferencias

        PreferenceManager.setDefaultValues(this, R.xml.preferencias, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    }

    // Menu Magic
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.resetar:
                SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                prefsEditor.putString(Rss,getResources().getString(R.string.rss_feed_default));
                Toast.makeText(this, "Resetei", Toast.LENGTH_SHORT).show();
                prefsEditor.commit();
                return true;

            case R.id.muda:
                startActivity(new Intent(getApplicationContext(), PreferenciasActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();

        RSS_FEED = sharedPreferences.getString(Rss,"DEFAULT");
        new CarregaRSStask().execute(RSS_FEED);
    }

    private class CarregaRSStask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "iniciando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String conteudo = "provavelmente deu erro...";
            try {
                conteudo = getRssFeed(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(), "terminando...", Toast.LENGTH_SHORT).show();


            // Passo 2 3 4 Criei uma listview que utiliza ParserRss
            List<ItemRSS> parsed;
            ParserRSS victor = new ParserRSS();
            try {
                parsed = victor.parse(s);
                ///Preencher lista com itens parseados
                beloAdapter.listaRss.addAll(parsed);
                conteudoRSS.setAdapter(beloAdapter);

                /// Get link do item clicado
                conteudoRSS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        ListView listView = (ListView) adapterView;

                        MyAdapter arrayAdapter = (MyAdapter) listView.getAdapter();

                        String link = ((ItemRSS) arrayAdapter.getItem(i)).getLink();

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(link));
                        startActivity(intent);

                    }
                });



            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Opcional - pesquise outros meios de obter arquivos da internet
    private String getRssFeed(String feed) throws IOException {
        InputStream in = null;
        String rssFeed = "";
        try {
            URL url = new URL(feed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            rssFeed = new String(response, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return rssFeed;
    }
}
