package com.example.studentinfromation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Normalizer2;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.spec.ECField;

public class MainActivity extends AppCompatActivity {


    public EditText eid,ename;
    public Spinner estatus;
    public AutoCompleteTextView ecoursecode,esection;
    public Button add,show;
    public String[] registration_statuss;
    public String[] course_code;
    public String[] section;
    public ArrayAdapter<String> adapterforcourses,adapterforsection,adapterforstatus;

    DatabaseSource databaseSource;
    ModelDataClass modelDataClass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        databaseSource = new DatabaseSource(MainActivity.this);

        // edittext
        eid = findViewById(R.id.studentId);
        ename = findViewById(R.id.nameId);
        // autocomplete
        esection = findViewById(R.id.sectionId);
        ecoursecode = findViewById(R.id.coursenameId);
        // spinner
        estatus = findViewById(R.id.regstatusid);
        //button
        add = findViewById(R.id.addbutton);
        show = findViewById(R.id.showbutton);

        registration_statuss = getResources().getStringArray(R.array.registration_status);
        course_code = getResources().getStringArray(R.array.course_code);
        section = getResources().getStringArray(R.array.section);

        // courses

        adapterforcourses = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,course_code);

        ecoursecode.setThreshold(1);
        ecoursecode.setAdapter(adapterforcourses);



        // section

        adapterforsection = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,section);
        esection.setThreshold(1);
        esection.setAdapter(adapterforsection);



        // registration status spinner

        adapterforstatus = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,registration_statuss);
        estatus.setAdapter(adapterforstatus);


         modelDataClass = (ModelDataClass) getIntent().getSerializableExtra("STUDENT");
         if (modelDataClass!=null){
             // change button name
             add.setText("Update");

             eid.setText(modelDataClass.getStudentid()+"");
             ename.setText(modelDataClass.getName());
             esection.setText(modelDataClass.getSection());
             ecoursecode.setText(modelDataClass.getCourses());




         }



        // add button listener to save the data in database
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    if(modelDataClass!=null){
                        int id = modelDataClass.getId();
                        int updatedSid = Integer.parseInt(eid.getText().toString().trim());
                        String updatedname = ename.getText().toString().trim();
                        String updatedcoursecode = ecoursecode.getText().toString().trim();
                        String updatedSection = esection.getText().toString().trim();
                        String updatedStatus = estatus.getSelectedItem().toString().trim();

                        ModelDataClass UpdatedmodelDataClass = new ModelDataClass(id,updatedSid,updatedname,updatedcoursecode,updatedSection,updatedStatus);
                        boolean updatedstatus = databaseSource.updatedata(UpdatedmodelDataClass);
                        if(updatedstatus)
                            Toast.makeText(MainActivity.this,"ADD TO DATABASE",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"NO DATA TO ADD",Toast.LENGTH_SHORT).show();



                    }
                    else {
                        ModelDataClass modelDataClass = new ModelDataClass(Integer.parseInt(eid.getText().toString().trim()),ename.getText().toString().trim(),
                                ecoursecode.getText().toString().trim(),esection.getText().toString().trim(),estatus.getSelectedItem().toString().trim());


                        boolean status =  databaseSource.addData(modelDataClass);
                        if(status){
                            Toast.makeText(MainActivity.this,"ADD TO DATABASE",Toast.LENGTH_SHORT).show();
                            eid.setText("");
                            ename.setText("");
                            ecoursecode.setText("");
                            esection.setText("");



                        }else {
                            Toast.makeText(MainActivity.this,"NO DATA TO ADD",Toast.LENGTH_SHORT).show();

                        }
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"fill the box",Toast.LENGTH_SHORT).show();

                }
                }

        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowData.class);
                startActivity(intent);
            }
        });

    }
}
