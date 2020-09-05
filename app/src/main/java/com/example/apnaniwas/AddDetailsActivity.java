package com.example.apnaniwas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apnaniwas.ui.home.HomeFragment;

import java.util.Objects;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AddDetailsActivity extends AppCompatActivity {

    EditText flatno,block,noofmem,mem1name,mem2name,mem3name,mem4name,mem5name,mem6name,mem1relation,mem2relation,mem3relation,mem4relation,mem5relation,mem6relation;
    TextView mem1,mem2,mem3,mem4,mem5,mem6;
    LinearLayout member_details;
    int totalmembers;
    Button addbtn;
    final String MYPREFS="DETAILS";
    final String FLATNO="FLATNO";
    final String BLOCK ="BLOCK";
    final String TOTALMEMBERS ="TOTALMEMBERS";
    final String MEM1NAME="MEM1NAME";
    final String MEM2NAME="MEM2NAME";
    final String MEM3NAME="MEM3NAME";
    final String MEM4NAME="MEM4NAME";
    final String MEM5NAME="MEM5NAME";
    final String MEM6NAME="MEM6NAME";
    final String MEM1RELATION="MEM1RELATION";
    final String MEM2RELATION="MEM2RELATION";
    final String MEM3RELATION="MEM3RELATION";
    final String MEM4RELATION="MEM4RELATION";
    final String MEM5RELATION="MEM5RELATION";
    final String MEM6RELATION="MEM6RELATION";


    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        Toolbar toolbar = findViewById(R.id.toolbarr);
        sharedpreferences = getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);
        ((AppCompatActivity) Objects.requireNonNull(this)).setSupportActionBar(toolbar);
        flatno=findViewById(R.id.et_flateNumber);
        block=findViewById(R.id.et_block);
        noofmem=findViewById(R.id.et_noofmem);

        mem1=findViewById(R.id.tv_mem1);
        mem2=findViewById(R.id.tv_mem2);
        mem3=findViewById(R.id.tv_mem3);
        mem4=findViewById(R.id.tv_mem4);
        mem5=findViewById(R.id.tv_mem5);
        mem6=findViewById(R.id.tv_mem6);

        mem1name=findViewById(R.id.et_mem1name);
        mem2name=findViewById(R.id.et_mem2name);
        mem3name=findViewById(R.id.et_mem3name);
        mem4name=findViewById(R.id.et_mem4name);
        mem5name=findViewById(R.id.et_mem5name);
        mem6name=findViewById(R.id.et_mem6name);

        mem1relation=findViewById(R.id.et_mem1relation);
        mem2relation=findViewById(R.id.et_mem2relation);
        mem3relation=findViewById(R.id.et_mem3relation);
        mem4relation=findViewById(R.id.et_mem4relation);
        mem5relation=findViewById(R.id.et_mem5relation);
        mem6relation=findViewById(R.id.et_mem6relation);

        member_details=findViewById(R.id.member_details);

        final TextWatcher mTextEditorWatcher = new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String noofmembers=noofmem.getText().toString();
                if(!noofmembers.isEmpty())
                {
                    int nom= Integer.parseInt(noofmembers);
                    totalmembers=nom;
                    if(nom==0)
                    {
                        mem1.setVisibility(View.INVISIBLE);
                        mem1name.setVisibility(View.INVISIBLE);
                        mem1relation.setVisibility(View.INVISIBLE);
                        mem1name.setEnabled(false);
                        mem1relation.setEnabled(false);
                        mem2.setVisibility(View.INVISIBLE);
                        mem2name.setVisibility(View.INVISIBLE);
                        mem2relation.setVisibility(View.INVISIBLE);
                        mem2name.setEnabled(false);
                        mem2relation.setEnabled(false);
                        mem3.setVisibility(View.INVISIBLE);
                        mem3name.setVisibility(View.INVISIBLE);
                        mem3relation.setVisibility(View.INVISIBLE);
                        mem3name.setEnabled(false);
                        mem3relation.setEnabled(false);
                        mem4.setVisibility(View.INVISIBLE);
                        mem4name.setVisibility(View.INVISIBLE);
                        mem4relation.setVisibility(View.INVISIBLE);
                        mem4name.setEnabled(false);
                        mem4relation.setEnabled(false);
                        mem5.setVisibility(View.INVISIBLE);
                        mem5name.setVisibility(View.INVISIBLE);
                        mem5relation.setVisibility(View.INVISIBLE);
                        mem5name.setEnabled(false);
                        mem5relation.setEnabled(false);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else if(nom==1)
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.INVISIBLE);
                        mem2name.setVisibility(View.INVISIBLE);
                        mem2relation.setVisibility(View.INVISIBLE);
                        mem2name.setEnabled(false);
                        mem2relation.setEnabled(false);
                        mem3.setVisibility(View.INVISIBLE);
                        mem3name.setVisibility(View.INVISIBLE);
                        mem3relation.setVisibility(View.INVISIBLE);
                        mem3name.setEnabled(false);
                        mem3relation.setEnabled(false);
                        mem4.setVisibility(View.INVISIBLE);
                        mem4name.setVisibility(View.INVISIBLE);
                        mem4relation.setVisibility(View.INVISIBLE);
                        mem4name.setEnabled(false);
                        mem4relation.setEnabled(false);
                        mem5.setVisibility(View.INVISIBLE);
                        mem5name.setVisibility(View.INVISIBLE);
                        mem5relation.setVisibility(View.INVISIBLE);
                        mem5name.setEnabled(false);
                        mem5relation.setEnabled(false);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else if(nom==2)
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.VISIBLE);
                        mem2name.setVisibility(View.VISIBLE);
                        mem2relation.setVisibility(View.VISIBLE);
                        mem2name.setEnabled(true);
                        mem2relation.setEnabled(true);
                        mem3.setVisibility(View.INVISIBLE);
                        mem3name.setVisibility(View.INVISIBLE);
                        mem3relation.setVisibility(View.INVISIBLE);
                        mem3name.setEnabled(false);
                        mem3relation.setEnabled(false);
                        mem4.setVisibility(View.INVISIBLE);
                        mem4name.setVisibility(View.INVISIBLE);
                        mem4relation.setVisibility(View.INVISIBLE);
                        mem4name.setEnabled(false);
                        mem4relation.setEnabled(false);
                        mem5.setVisibility(View.INVISIBLE);
                        mem5name.setVisibility(View.INVISIBLE);
                        mem5relation.setVisibility(View.INVISIBLE);
                        mem5name.setEnabled(false);
                        mem5relation.setEnabled(false);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else if(nom==3)
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.VISIBLE);
                        mem2name.setVisibility(View.VISIBLE);
                        mem2relation.setVisibility(View.VISIBLE);
                        mem2name.setEnabled(true);
                        mem2relation.setEnabled(true);
                        mem3.setVisibility(View.VISIBLE);
                        mem3name.setVisibility(View.VISIBLE);
                        mem3relation.setVisibility(View.VISIBLE);
                        mem3name.setEnabled(true);
                        mem3relation.setEnabled(true);
                        mem4.setVisibility(View.INVISIBLE);
                        mem4name.setVisibility(View.INVISIBLE);
                        mem4relation.setVisibility(View.INVISIBLE);
                        mem4name.setEnabled(false);
                        mem4relation.setEnabled(false);
                        mem5.setVisibility(View.INVISIBLE);
                        mem5name.setVisibility(View.INVISIBLE);
                        mem5relation.setVisibility(View.INVISIBLE);
                        mem5name.setEnabled(false);
                        mem5relation.setEnabled(false);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else if(nom==4)
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.VISIBLE);
                        mem2name.setVisibility(View.VISIBLE);
                        mem2relation.setVisibility(View.VISIBLE);
                        mem2name.setEnabled(true);
                        mem2relation.setEnabled(true);
                        mem3.setVisibility(View.VISIBLE);
                        mem3name.setVisibility(View.VISIBLE);
                        mem3relation.setVisibility(View.VISIBLE);
                        mem3name.setEnabled(true);
                        mem3relation.setEnabled(true);
                        mem4.setVisibility(View.VISIBLE);
                        mem4name.setVisibility(View.VISIBLE);
                        mem4relation.setVisibility(View.VISIBLE);
                        mem4name.setEnabled(true);
                        mem4relation.setEnabled(true);
                        mem5.setVisibility(View.INVISIBLE);
                        mem5name.setVisibility(View.INVISIBLE);
                        mem5relation.setVisibility(View.INVISIBLE);
                        mem5name.setEnabled(false);
                        mem5relation.setEnabled(false);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else if(nom==5)
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.VISIBLE);
                        mem2name.setVisibility(View.VISIBLE);
                        mem2relation.setVisibility(View.VISIBLE);
                        mem2name.setEnabled(true);
                        mem2relation.setEnabled(true);
                        mem3.setVisibility(View.VISIBLE);
                        mem3name.setVisibility(View.VISIBLE);
                        mem3relation.setVisibility(View.VISIBLE);
                        mem3name.setEnabled(true);
                        mem3relation.setEnabled(true);
                        mem4.setVisibility(View.VISIBLE);
                        mem4name.setVisibility(View.VISIBLE);
                        mem4relation.setVisibility(View.VISIBLE);
                        mem4name.setEnabled(true);
                        mem4relation.setEnabled(true);
                        mem5.setVisibility(View.VISIBLE);
                        mem5name.setVisibility(View.VISIBLE);
                        mem5relation.setVisibility(View.VISIBLE);
                        mem5name.setEnabled(true);
                        mem5relation.setEnabled(true);
                        mem6.setVisibility(View.INVISIBLE);
                        mem6name.setVisibility(View.INVISIBLE);
                        mem6relation.setVisibility(View.INVISIBLE);
                        mem6name.setEnabled(false);
                        mem6relation.setEnabled(false);
                    }
                    else
                    {
                        mem1.setVisibility(View.VISIBLE);
                        mem1name.setVisibility(View.VISIBLE);
                        mem1relation.setVisibility(View.VISIBLE);
                        mem1name.setEnabled(true);
                        mem1relation.setEnabled(true);
                        mem2.setVisibility(View.VISIBLE);
                        mem2name.setVisibility(View.VISIBLE);
                        mem2relation.setVisibility(View.VISIBLE);
                        mem2name.setEnabled(true);
                        mem2relation.setEnabled(true);
                        mem3.setVisibility(View.VISIBLE);
                        mem3name.setVisibility(View.VISIBLE);
                        mem3relation.setVisibility(View.VISIBLE);
                        mem3name.setEnabled(true);
                        mem3relation.setEnabled(true);
                        mem4.setVisibility(View.VISIBLE);
                        mem4name.setVisibility(View.VISIBLE);
                        mem4relation.setVisibility(View.VISIBLE);
                        mem4name.setEnabled(true);
                        mem4relation.setEnabled(true);
                        mem5.setVisibility(View.VISIBLE);
                        mem5name.setVisibility(View.VISIBLE);
                        mem5relation.setVisibility(View.VISIBLE);
                        mem5name.setEnabled(true);
                        mem5relation.setEnabled(true);
                        mem6.setVisibility(View.VISIBLE);
                        mem6name.setVisibility(View.VISIBLE);
                        mem6relation.setVisibility(View.VISIBLE);
                        mem6name.setEnabled(true);
                        mem6relation.setEnabled(true);
                    }
                    member_details.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        };
        noofmem.addTextChangedListener(mTextEditorWatcher);
//        noofmem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_details)
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            String fno=flatno.getText().toString();
            String blk=block.getText().toString();
            int tot=Integer.parseInt(noofmem.getText().toString());
            if(!fno.isEmpty() && !blk.isEmpty()) {
                editor.putString(FLATNO, fno);
                editor.putString(BLOCK, blk);
                editor.putInt(TOTALMEMBERS, tot);
                if(totalmembers<=6)
                {
                    editor.putString(MEM6NAME, mem6name.getText().toString());
                    editor.putString(MEM6RELATION, mem6relation.getText().toString());
                    if(totalmembers<=5)
                    {
                        editor.putString(MEM5NAME, mem5name.getText().toString());
                        editor.putString(MEM5RELATION, mem5relation.getText().toString());
                        if(totalmembers<=4)
                        {
                            editor.putString(MEM4NAME, mem4name.getText().toString());
                            editor.putString(MEM4RELATION, mem4relation.getText().toString());
                            if(totalmembers<=3)
                            {
                                editor.putString(MEM3NAME, mem3name.getText().toString());
                                editor.putString(MEM3RELATION, mem3relation.getText().toString());
                                if(totalmembers<=2) {
                                    editor.putString(MEM2NAME, mem2name.getText().toString());
                                    editor.putString(MEM2RELATION, mem2relation.getText().toString());
                                    if (totalmembers <= 1)
                                    {
                                        editor.putString(MEM1NAME, mem1name.getText().toString());
                                        editor.putString(MEM1RELATION, mem1relation.getText().toString());
                                    }
                                }
                            }
                        }
                    }
                }
                editor.commit();
            }
            Intent i =new Intent(this,BottomNavigationBar.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}