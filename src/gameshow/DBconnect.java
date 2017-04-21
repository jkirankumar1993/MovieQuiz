/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameshow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jkira
 */
public class DBconnect {
    private Connection con;
        private Statement st;
        private ResultSet rs;
    public DBconnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movietrivia?useSSL=false","root","kirankumar");
            st = con.createStatement();
            
        }
        catch(Exception ex){
            System.out.println("Error" +ex);
        }
        
    }
    public ArrayList getquestions(){
        ArrayList<String> questions = new ArrayList<>();
try{
    
    String query = "select * from Questions";
    rs = st.executeQuery(query);
    while(rs.next()){
        String name = rs.getString("Question");
        questions.add(name);
    }
    
}
catch(Exception ex){
    
}
return questions;
}
    
    public ArrayList getoptions(){
        ArrayList<String> options = new ArrayList<>();
        try{
            String query = "select * from Questions";
            rs = st.executeQuery(query);
            while(rs.next()){
                String res = "";
                String option1=rs.getString("option1");
                res+=option1;
                res+=",";
                String option2=rs.getString("option2");
                res+=option2;
                res+=",";
                String option3=rs.getString("option3");
                res+=option3;
                res+=",";
                String option4=rs.getString("option4");
                res+=option4;               
                options.add(res);
            }
            
        }
        catch(Exception ex){
            
        }
        return options;
    }
    
    public ArrayList getcorrectanswer(){
        ArrayList<Integer> correct = new ArrayList<>();
        try{
            String query = "select * from Questions";
            rs = st.executeQuery(query);
            while(rs.next()){
                int c = rs.getInt("correctanswer");
                correct.add(c);
            }
        }
        catch(Exception ex){
            
        }
        return correct;
    }
    
    public int getquestioncount(){
        int count =0;
        try{
            String query = "select * from Questions";
            rs = st.executeQuery(query);
            while(rs.next()){
                count++;
            }
        }
        catch(Exception ex){
            
        }
        return count;
    }
    
    public void update(int score){
        try{
            
            String query = String.format("insert into highscore values('%s',%d)", User.getUsername(),score);
            st.executeUpdate(query);
            
        }
        catch(Exception ex){
            
        }
    }
    
    public void closedb(){
        try{
          con.close();  
        }
        catch(Exception ex){
        
    }
}
    
    public ResultSet gethighscores(){
       try{
           String query = "select * from highscore order by Score";
        rs = st.executeQuery(query);
        
       }
       catch(Exception ex){
           
       }
       return rs; 
    }
}
