package ru.mirea.lednevadd.favoritebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    static  final String KEY = "book_name";
    static  final String USER_MESSAGE = "Message";
    private TextView textViewUserBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewUserBook = findViewById(R.id.textView);
        Button buttonSend = findViewById(R.id.button);

        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode() == Activity.RESULT_OK){
                    Intent data = o.getData();
                    String userBook = data.getStringExtra(USER_MESSAGE);
                    textViewUserBook.setText("Ваша любимая книга: " + userBook);
                }
            }
        };

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoAboutBook(v);
            }
        });
    }
    public void  getInfoAboutBook(View view){
        Intent intent = new Intent(this, ShareActivity.class);
        intent.putExtra(KEY, "Хамелеон");
        activityResultLauncher.launch(intent);
    }
}