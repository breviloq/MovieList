package com.example.movielist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.movielist.Movie;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> moviesList;
    private MoviesListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        moviesList = new ArrayList<>();
        adapter = new MoviesListAdapter(this, moviesList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMovieDialog();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });
    }

    private void showAddMovieDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_movie, null); // Используйте правильный макет здесь

        final EditText etTitle = dialogView.findViewById(R.id.etTitle);
        final EditText etDirector = dialogView.findViewById(R.id.etDirector);
        final EditText etYear = dialogView.findViewById(R.id.etYear);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Добавить фильм")
                .setView(dialogView)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = etTitle.getText().toString();
                        String director = etDirector.getText().toString();
                        int year = 0;
                        try {
                            year = Integer.parseInt(etYear.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Год должен быть числом", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (!title.isEmpty() && !director.isEmpty() && year > 0) {
                            Movie newMovie = new Movie(title, director, year, false);
                            moviesList.add(newMovie);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Фильм добавлен", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }


    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Удалить фильм")
                .setMessage("Вы уверены, что хотите удалить этот фильм?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moviesList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Фильм удален", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }
}
