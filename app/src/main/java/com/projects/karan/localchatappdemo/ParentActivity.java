package com.projects.karan.localchatappdemo;

import android.content.Intent;
import android.provider.MediaStore;
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

public class ParentActivity extends AppCompatActivity {

    private static final int REQ_CODE_1 = 1;
    private static final int REQ_CODE_CAMERA = 2;

    EditText editTextParent;
    TextView textViewParent;
    Button buttonParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Parent");

        editTextParent = (EditText) findViewById(R.id.editTextParent);
        textViewParent = (TextView) findViewById(R.id.textViewParent);
        buttonParent = (Button) findViewById(R.id.buttonSendParent);

        textViewParent.setText("");

        buttonParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTextData = editTextParent.getText().toString().trim();

                if(editTextData != null && !editTextData.equals("")){

                    String finalData;
                    if(textViewParent.getText().toString().equals(""))
                        finalData = "Parent: "+ editTextData;
                    else
                        finalData = textViewParent.getText().toString()+"\n"+"Parent: "+ editTextData;

                    Intent intent = new Intent(ParentActivity.this, ChildActivity.class);
                    intent.putExtra(Constants.PARENT_DATA_KEY, finalData);

                    editTextParent.setText("");
                    textViewParent.setText(finalData);

                    startActivityForResult(intent, REQ_CODE_1);
                } else {
                    Toast.makeText(ParentActivity.this, "Cannot send empty chat", Toast.LENGTH_SHORT).show();
                }


                /*Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQ_CODE_CAMERA);*/

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_parent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_delete_parent){
            editTextParent.setText("");
            textViewParent.setText("");
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_1){
            if(resultCode == RESULT_OK){
                textViewParent.setText(data.getExtras().getString(Constants.CHILD_DATA_KEY));
            }

        } else if(requestCode == REQ_CODE_CAMERA){

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewParent.setText(savedInstanceState.getString(Constants.TEXT_VIEW_KEY));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.TEXT_VIEW_KEY, textViewParent.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
