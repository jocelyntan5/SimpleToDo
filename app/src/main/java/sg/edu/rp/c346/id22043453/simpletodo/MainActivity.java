package sg.edu.rp.c346.id22043453.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spTask;
    EditText etTask;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTask;
    ArrayList<String> alTask = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTask = findViewById(R.id.idSpinner);
        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);

        ArrayAdapter aaTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = etTask.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                int position = Integer.parseInt(task);

                if (alTask.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    etTask.setText("");
                }
                else if (position >= 0 && position < alTask.size()) {
                    alTask.remove(position);
                    aaTask.notifyDataSetChanged();
                    etTask.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });
        spTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String getPosition = parent.getItemAtPosition(position).toString();

                // if the spinner value is equal to "Add a new task" will continue run the code
                if (getPosition.equals("Add a new task")) {
                    etTask.setHint("Type in a new task here");
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(false);
                }
                // if the spinner value is equal to "Remove a task" will continue run the code
                else if (getPosition.equals("Remove a task")) {
                    etTask.setHint("Type in the index of the task to be removed");
                    btnAdd.setEnabled(false);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}