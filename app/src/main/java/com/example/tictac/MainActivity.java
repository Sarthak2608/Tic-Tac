package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button b1;
    int[][] arr={ {0,0,0},{0,0,0},{0,0,0} };
    int c=0,l=0,winner=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void reset(View v)
    {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++) {
                arr[i][j] = 0;
                String s="a"+Integer.toString((i+1)*10+(j+1));
                try {
                    b1 = findViewById(getResources().getIdentifier(s,"id",getPackageName()));
                    b1.setBackgroundResource(R.drawable.white);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            winner=0;
            c=0;
            l=0;


    }
    public int chk(int x,int y)
    {
        if(arr[x][y]==0)
        {
            arr[x][y]=1;
            int t=0;
            for(int i=0;i<3;i++){
                if(arr[x][i]==1)
                {
                    t+=1;
                }
            }
            if(t==3)
                winner=1;

            t=0;
            for(int i=0;i<3;i++){
                if(arr[i][y]==1)
                {
                    t+=1;
                }
            }
            if(t==3)
                winner=1;

            t=0;
            for(int i=0;i<3;i++){
                if(arr[i][i]==1)
                {
                    t+=1;
                }
            }
            if(t==3)
                winner=1;

            t=0;
            for(int i=0;i<3;i++){
                if(arr[i][2-i]==1)
                {
                    t+=1;
                }
            }
            if(t==3)
                winner=1;

            return 1;
        }
        return 0;
    }


    public int findParticularMove(int z){
        int temp1,temp2;
        for(int i=0;i<3;i++)
        {
            temp1=0;
            for(int j=0;j<3;j++)
            {
                if(arr[i][j]==z)
                    temp1+=1;
            }
            if(temp1==2)
            {
                for(int j=0;j<3;j++)
                {
                    if(arr[i][j]==0) {
                        return (i + 1) * 10 + (j + 1);
                    }
                }
            }
        }

        for(int i=0;i<3;i++)
        {
            temp1=0;
            for(int j=0;j<3;j++)
            {
                if(arr[j][i]==z)
                    temp1+=1;
            }
            if(temp1==2)
            {
                for(int j=0;j<3;j++)
                {
                    if(arr[j][i]==0) {
                        return (j + 1) * 10 + (i + 1);
                    }
                }
            }
        }

        temp1=0;
        for(int i=0;i<3;i++)
        {
            if(arr[i][i]==z)
                temp1+=1;
        }
        if(temp1==2)
        {
            for(int i=0;i<3;i++)
            {
                if(arr[i][i]==0)
                    return (i+1)*10+(i+1);
            }
        }


        temp1=0;
        for(int i=0;i<3;i++)
        {
            if(arr[i][2-i]==z)
                temp1+=1;
        }
        if(temp1==2)
        {
            for(int i=0;i<3;i++)
            {
                if(arr[i][2-i]==0)
                    return (i+1)*10+(2-i+1);
            }
        }

         return 0;
    }
    public int findRandom(){
        int[] r= new int[9];
        int it=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(arr[i][j]==0) {
                    r[it] = (i + 1) * 10 + (j + 1);
                    it++;
                }
            }
        }
        if(it==0)
            return 0;
        int idx=new Random().nextInt(it);
        return r[idx];
    }
    public int findMove(){
        int res=findParticularMove(2);
        if(res!=0)
        {
            winner=2;
            return res;
        }
        res=findParticularMove(1);
        if(res!=0)
        {
            return res;
        }


        return findRandom();
    }
    public void playSystem(){

        int temp=findMove();
        if(temp==0)
        {
            Toast.makeText(this, "There is no other possible move", Toast.LENGTH_SHORT).show();
            return;
        }
        int x1=temp%10;
        int x2=temp/10;
        arr[x2-1][x1-1]=2;
        String s="a"+Integer.toString(temp);
        try {
            b1 = findViewById(getResources().getIdentifier(s,"id",getPackageName()));
            b1.setBackgroundResource(R.drawable.cross);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void winnerMessage(){
        if(winner==2)
        {
            Toast.makeText(this, "System is Winner", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "You are Winner", Toast.LENGTH_SHORT).show();
        }
    }
    public void query(View v,int x,int y){
        if(winner!=0)
        {
            winnerMessage();
            return ;
        }
        x=x-1;
        y=y-1;
        if(chk(x,y)==1)
        {
            l+=1;
            l%=2;
            c+=1;
            v.setBackgroundResource(R.drawable.circle);
            Toast.makeText(this, "Great !!", Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(500);
            }
            catch (Exception e)
            {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            //v.setBackgroundResource(R.drawable.cross);
            if( winner!=0)
            {
                winnerMessage();
                return;
            }
            l+=1;
            l%=2;
            c+=1;
            playSystem();

            if( winner!=0) {
                winnerMessage();
                return;
            }


        }
        else
        {
            Toast.makeText(this, "Already Filled", Toast.LENGTH_SHORT).show();
        }

    }
    public void q11(View v)
    {
        query(v,1,1);
    }
    public void q12(View v)
    {
        query(v,1,2);
    }
    public void q13(View v)
    {
        query(v,1,3);
    }
    public void q21(View v)
    {
        query(v,2,1);
    }
    public void q22(View v)
    {
        query(v,2,2);
    }
    public void q23(View v)
    {
        query(v,2,3);
    }
    public void q31(View v)
    {
        query(v,3,1);
    }
    public void q32(View v)
    {
        query(v,3,2);
    }
    public void q33(View v)
    {
        query(v,3,3);
    }
}
