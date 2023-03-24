package nascimento.andrade.sofia.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail); //findViewById localizando editText através do id
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);//findViewById localizando editText através do id
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto); //findViewById localizando editText através do id
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO); //ACTION_SENDTO para enviar um e-mail
                i.setData(Uri.parse("mailto:")); //Adiciona o objeto especificado
                String[] emails = new String[] {email};
                i.putExtra(Intent.EXTRA_EMAIL, emails); //O Intent carregando os emails e a atividade a iniciar
                i.putExtra(Intent.EXTRA_SUBJECT, assunto); //O Intent carregando o assunto e a atividade a iniciar
                i.putExtra(Intent.EXTRA_TEXT, texto); //O Intent carregando o texto e a atividade a iniciar

                try{
                    startActivity(Intent.createChooser(i, "Escolha o APP")); // aparece para o usuário um menu onde ele escolhe entre vários apps que podem enviar uma mensagem de e-mail. Se não tiver nenhuma app de e-mail instalada,irá aparecer uma mensagem de erro.
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this,"Não há nenhum app que posso realizar essa operação",Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}