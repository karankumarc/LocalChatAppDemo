package com.projects.karan.localchatappdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.karan.localchatappdemo.utils.Constants;

public class ChildActivity extends AppCompatActivity {

    EditText editTextChild;
    TextView textViewChild;
    Button buttonChild;

    boolean isChatDeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        setTitle("Child");

        editTextChild = (EditText) findViewById(R.id.editTextChild);
        textViewChild = (TextView) findViewById(R.id.textViewChild);
        buttonChild = (Button) findViewById(R.id.buttonSendChild);

        Intent intent = getIntent();
        final String dataFromParent = intent.getExtras().getString(Constants.PARENT_DATA_KEY);

        textViewChild.setText(dataFromParent);

        buttonChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextData = editTextChild.getText().toString().trim();

                if(editTextData != null && !editTextData.equals("")){
                    String finalData = "";

                    if(isChatDeleted)
                        finalData = "Child: "+ editTextData;
                    else
                        finalData = dataFromParent+"\n"+"Child: "+ editTextData;

                    Intent intent = new Intent();
                    intent.putExtra(Constants.CHILD_DATA_KEY, finalData);
                    setResult(RESULT_OK, intent);
                    finish();

                } else {
                    Toast.makeText(ChildActivity.this, "Cannot send empty chat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_child, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete_child){
            textViewChild.setText("");
            editTextChild.setText("");
            isChatDeleted = true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewChild.setText(savedInstanceState.getString(Constants.TEXT_VIEW_KEY));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.TEXT_VIEW_KEY, textViewChild.getText().toString());
    }
}
