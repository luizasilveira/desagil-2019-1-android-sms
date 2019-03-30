package br.edu.insper.al.algumaluno.sms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        EditText textMessage = findViewById(R.id.text_message);
        EditText textPhone = findViewById(R.id.text_phone);
        Button buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener((view) -> {
            String message = textMessage.getText().toString();
            String phone = textPhone.getText().toString();

            // Esta verificação do número de telefone é bem
            // rígida, pois exige até mesmo o código do país.
            if (PhoneNumberUtils.isGlobalPhoneNumber(phone)) {

                // Enviar uma mensagem de SMS. Por simplicidade,
                // não estou verificando se foi mesmo enviada.
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(phone, null, message, null, null);

                // Limpar o campo para nenhum engraçadinho
                // ficar apertando o botão várias vezes.
                textPhone.setText("");
            } else {

                // Mostrar uma bolha de texto com duração curta.
                Toast toast = Toast.makeText(this, "Número inválido!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
