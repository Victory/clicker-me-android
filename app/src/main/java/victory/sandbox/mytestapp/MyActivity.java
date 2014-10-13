package victory.sandbox.mytestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity {
    private Button toastThis; // toastes a thing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final TextView helloWorldText = (TextView)findViewById(R.id.helloWorld);
        Button clickToDo = (Button)findViewById(R.id.clickToDo);
        clickToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helloWorldText.getText().equals(getString(R.string.hello_world))) {
                    helloWorldText.setText(getString(R.string.iLikeToClick));
                } else {
                    helloWorldText.setText(getString(R.string.hello_world));
                }
            }
        });

        toastThis = (Button)findViewById(R.id.toastThis);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings || super.onOptionsItemSelected(item)) {
            return true;
        }
        else return false;
    }

    public void doToast(View view) {
        String theResponse = "This is a sweet response";
        Toast.makeText(this, theResponse, Toast.LENGTH_SHORT).show();
    }
}
